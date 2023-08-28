package com.predictz.predictz.model.btips;

import com.predictz.predictz.model.UUser;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String imgUrl;
    private String status;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private LocalDateTime createdAt;

    @PrePersist
    public void init(){
        createdAt = LocalDateTime.now();
    }

    public AppUser(){
        super();
    }
    public UUser getUUser(){
        List<com.predictz.predictz.model.Role> roleList
                = new ArrayList<>();
        roles.forEach(e -> {
            roleList.add(new com.predictz.predictz.model.Role(e.getId(), e.getName()));
        });

        return UUser.builder().id(this.id)
                .name(this.name)
                .email(this.email)
                .imgUrl(this.imgUrl)
                .status(this.status)
                .roles(roleList)
                .createdAt(createdAt).build();
    }
}
