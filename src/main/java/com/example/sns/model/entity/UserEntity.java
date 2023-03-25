package com.example.sns.model.entity;

import com.example.sns.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
//user테이블이 이미 존재하는 경우도 있기 때문에 " 를 붙여줌
@Table(name = "\"user\"")
//삭제 sql이 불러지면 deleted_at update
@SQLDelete(sql = "UPDATE \"user\" SET deleted_at = NOW() where id=?")
//select 할때는 deleted_at null인 것만 불러오기
@Where(clause = "deleted_at is NULL")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;
    private String password;

    //admin, 일반 user 구분
    //Enum 사용할때 EnumType String 중요
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "register_at")
    private Timestamp registerAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @PrePersist
    void registerAt(){
        this.registerAt = Timestamp.from(Instant.now());
    }

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserEntity() {
    }

    @PreUpdate
    void updatedAt(){
        this.updatedAt = Timestamp.from(Instant.now());
    }

    //dto -> entity 변환
    public static UserEntity of(String userName, String password){
        return new UserEntity(userName,password);
    }
}
