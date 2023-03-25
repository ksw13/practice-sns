package com.example.sns.fixture;

import com.example.sns.model.entity.UserEntity;

public class UserEntityFixture {

    public static UserEntity get(String userName, String password){
        UserEntity result= new UserEntity(userName,password);
        result.setId(1);

        return result;
    }
}
