package com.predictz.winningcommunitee.model;

import lombok.Data;

@Data
public class SubscribeUser{
    private Long userId;
    private Long subscriptionId;
    private int length;
}