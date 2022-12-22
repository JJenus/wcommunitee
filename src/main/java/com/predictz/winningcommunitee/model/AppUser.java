package com.predictz.winningcommunitee.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String imgUrl;
    private String status;
    @ManyToMany
    private List<Role> roles;
    @ManyToMany
    private List<Subscription> subscriptions;
    private LocalDateTime createdAt;

    @PrePersist
    public void init(){
        createdAt = LocalDateTime.now();
    }

}
