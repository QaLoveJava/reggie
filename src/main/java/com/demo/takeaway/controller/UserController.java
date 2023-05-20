package com.demo.takeaway.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.takeaway.common.R;
import com.demo.takeaway.entity.User;
import com.demo.takeaway.service.UserService;
import com.demo.takeaway.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone = user.getPhone();
        if(!phone.isEmpty()){
            //生成验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info(code.toString());

            //调用阿里云的api发送短信
            //SMSUtils.sendMessage("阿里云短信测试","*****",phone,code);

            //将生成的验证码保存到Session
            //session.setAttribute(phone,code);

            //将验证码缓存到redis中  设置有效期300s
            redisTemplate.opsForValue().set(phone,code,300, TimeUnit.SECONDS);

            return R.success("手机验证码发送成功");
        }
        return R.error("验证码发送失败");
    }

    /**
     *移动端用户登录
     * @param map
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
        log.info(map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从session中获取保存的验证码
        //Object codeInSession = session.getAttribute(phone);

        //从redis中获取验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        //比对验证码（页面提交的和session对比）
        if(codeInSession != null && codeInSession.equals(code)){
            //查询当前手机号对应的用户是否在数据库中，不在则自动完成注册
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                user = new User();
                user.setPhone(phone);
                user.setName(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());

            //如果用户登录成功  删除redis缓存的验证码
            redisTemplate.delete(phone);

            return R.success(user);
        }

        return R.error("登陆失败");
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @PostMapping("/loginout")
    public R<String> loginout(HttpServletRequest request){
        //清理Session中保存的当前用户登录的id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
}
