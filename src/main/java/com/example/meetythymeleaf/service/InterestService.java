package com.example.meetythymeleaf.service;


import com.example.meetythymeleaf.model.Interest;
import com.example.meetythymeleaf.repository.InterestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InterestService {
    InterestRepository interestRepository;


    public List<Interest> findAll() {
        return interestRepository.findAll();
    }

    public void saveInterest(Interest interest) {
        interestRepository.save(interest);
    }

    public Interest getInterestById(Long id) {
        return interestRepository.findById(id).orElse(null);
    }

    public void updateInterest(Long id, Interest updatedInterest) {
        Interest interest = interestRepository.findById(id).orElse(null);
        interest.setName(updatedInterest.getName());
        interestRepository.save(interest);


    }

    public void deleteInterest(Long id) {
        interestRepository.deleteById(id);
    }

    public List<Interest> getByIds(List<Long> interestsIds) {
       return interestRepository.findAllById(interestsIds);
    }
}
