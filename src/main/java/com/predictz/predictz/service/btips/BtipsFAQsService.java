package com.predictz.predictz.service.btips;

import com.predictz.predictz.model.btips.FAQ;
import com.predictz.predictz.repository.btips.BtipsFAQsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BtipsFAQsService {
    @Autowired
    BtipsFAQsRepo faQsRepo;

    public List<FAQ> getFAQs(){
        return faQsRepo.findAll();
    }

    public FAQ getFaQ(Long id){
        return faQsRepo.findById(id).orElse(null);
    }

    public FAQ saveFaQ(FAQ FAQ){
        return faQsRepo.save(FAQ);
    }

    public FAQ update(FAQ FAQ){
        Optional<FAQ> optionalFAQs = faQsRepo.findById(FAQ.getId());
        if (!optionalFAQs.isPresent())
            return null;
        return faQsRepo.save(FAQ);
    }

    public void delFaq(FAQ FAQ){
        faQsRepo.delete(FAQ);
    }

    public void delFaq(Long id){
        faQsRepo.deleteById(id);
    }
}
