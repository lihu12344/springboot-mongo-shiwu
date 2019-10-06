package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public String save(){
        for(int i=0;i<100;i++){
            User user=new User();
            user.setId(i);
            user.setName("瓜田李下"+i);
            user.setAge(i%10+15);

            userService.save(user);
        }

        return "success";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Integer id){
        try{
            userService.updateById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "success";
    }
}