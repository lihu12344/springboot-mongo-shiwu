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
    public void updateById(Integer id) throws Exception {
        Query query= Query.query(Criteria.where("id").is(id));
        User user=mongoTemplate.findOne(query, User.class);
        System.out.println("更新钱的user为："+user);

        Update update=Update.update("name","海贼王");
        mongoTemplate.upsert(query,update, User.class);

        if(id.equals(2)){
            throw new Exception("事务回滚");
        }

        System.out.println("更新后的user为："+mongoTemplate.findOne(query, User.class));
    }
}
