package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MongoTemplate mongoTemplate;

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
    public String update(){
        Query query=new Query(Criteria.where("id").lte(10));

        System.out.println("更新数据前为：");
        mongoTemplate.find(query,User.class).forEach(System.out::println);

        for (int i=0;i<=10;i++){
            try{
                userService.updateById(i);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("更新后数据为：");
        mongoTemplate.find(query,User.class).forEach(System.out::println);

        return "success";
    }

    @RequestMapping("/getAll")
    public List<User> getAll(){
        Query query=new Query();

        return mongoTemplate.find(query,User.class);
    }

    @RequestMapping("/get")
    public List<User> get(){
        Query query=new Query();
        query.with(Sort.by("id").ascending()).with(PageRequest.of(0,10));

        return mongoTemplate.find(query,User.class);
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        mongoTemplate.findAllAndRemove(new Query(),User.class);

        return "success";
    }
}