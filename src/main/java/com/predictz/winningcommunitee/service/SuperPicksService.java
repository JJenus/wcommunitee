package com.predictz.winningcommunitee.service;

import com.predictz.winningcommunitee.model.ApiLimit;
import com.predictz.winningcommunitee.model.SuperPick;
import com.predictz.winningcommunitee.repository.ApiLimitRepo;
import com.predictz.winningcommunitee.repository.SuperPickRepo;
import com.predictz.winningcommunitee.util.api.SportsBettingPredictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class SuperPicksService {
    private SportsBettingPredictions predictions;
    @Autowired
    private SuperPickRepo superPickRepo;
    @Autowired
    private ApiLimitRepo apiLimitRepo;
    private int retries = 0;

    public  SuperPicksService(){
        predictions = new SportsBettingPredictions();
    }

    public List<SuperPick> getPicks() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println("Date: "+date);
        List<SuperPick> superPicks = superPickRepo.findByGameDate(date);

        if (superPicks.isEmpty()){
            ApiLimit limit = apiLimitRepo.findByApiName(SportsBettingPredictions.NAME);
            if (limit != null){
                if (limit.getStatus().equalsIgnoreCase("loading") || ! (limit.getCurrentRate() <= limit.getRateLimit()/2)){
                    return superPicks;
                }
            }
            else {
                limit = lockTransaction(
                        new ApiLimit(
                                SportsBettingPredictions.NAME,
                                0,
                                SportsBettingPredictions.limit,
                                "open"
                        )
                );
            }

            limit.setCurrentRate(0);
            lockTransaction(limit);
            superPicks.addAll(predictions.getSuperPicks(date));
            ApiLimit finalLimit = limit;
            new Thread(()->{
                superPickRepo.saveAll(superPicks);
                unlockTransaction(finalLimit);
            }).start();
        }
        return superPicks;
    }

    private void unlockTransaction(ApiLimit limit) {
        limit.setStatus("open");
        apiLimitRepo.save(limit);
    }

    private ApiLimit lockTransaction(ApiLimit limit) {
        limit.setCurrentRate(limit.getCurrentRate()+1);
        limit.setStatus("loading");

        return apiLimitRepo.save(limit);
    }

}
