package com.example.sns.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table
public class UserEntity {
    @Id
    private Integer id;

    private String userName;
    private String password;
}
