package com.example.demo.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class User {

    private Integer id;
    private String name;
    private Integer age;
}
