package com.predictz.predictz.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthToken {
    private String token;
    private UUser user;
}
