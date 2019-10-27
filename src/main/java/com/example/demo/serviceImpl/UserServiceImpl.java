package com.example.demo.serviceImpl;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public void save(User user) {
        mongoTemplate.save(user);
    }

    @Override
    @Transactional
    public void updateById(Integer id) {
        Query query= Query.query(Criteria.where("id").is(id));
        Update update=Update.update("name","海贼王");
        mongoTemplate.updateFirst(query,update, User.class);

        if(id.equals(2)){
            throw new RuntimeException("id为："+id+" 的user对象更新失败，事务回滚");
        }
    }
}