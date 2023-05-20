package com.demo.takeaway.DTO;

import com.demo.takeaway.entity.Setmeal;
import com.demo.takeaway.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
