package com.predictz.predictz.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UUser {
    private Long id;
    private String name;
    private String email;
    private String imgUrl;
    private String status;
    private LocalDateTime createdAt;
    private List<Role> roles;
}
