package com.predictz.winningcommunitee.model;

import lombok.Data;

@Data
public class AuthToken {
    private String token;
    private AppUser user;
}
