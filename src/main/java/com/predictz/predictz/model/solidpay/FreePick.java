package com.predictz.predictz.model.solidpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class FreePick {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String home_team;
    private String away_team;
    private String market;
    private String competition_name;
    private String prediction;
    private String competition_cluster;
    private String status;
    private String federation;
    private boolean is_expired;
    private String season;
    private String result;
    private String start_date;
    private String last_update_at;
//    @ElementCollection
//    private Map<String, Float> odds;
}
