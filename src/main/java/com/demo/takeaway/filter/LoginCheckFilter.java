package com.demo.takeaway.filter;

import com.alibaba.fastjson.JSON;
import com.demo.takeaway.common.BaseContext;
import com.demo.takeaway.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//检查用户是否完成登陆
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符匹配
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("拦截到请求："+request.getRequestURL());
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };
        boolean check = check(urls,requestURI);
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        //后台登录拦截
        if(request.getSession().getAttribute("employee") != null){
            //给同一线程设置当前id   使得后面的类可以从线程中取出
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //前端用户登陆拦截
        if(request.getSession().getAttribute("user") != null){
            //给同一线程设置当前id   使得后面的类可以从线程中取出
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }
        //未登录
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }

    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean b = PATH_MATCHER.match(url, requestURI);
            if(b) return true;
        }
        return false;
    }
}
