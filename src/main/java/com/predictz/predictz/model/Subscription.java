package com.predictz.predictz.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class Subscription {
    public Long id;
    public String title;
    public String amount;
    public String description;
    public String duration;
    public String type;
}
