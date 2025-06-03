package org.example.service;

import org.example.model.Freelancer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class FreelancerEvaluationService {

    @Async
    public CompletableFuture<Freelancer> evaluate(Freelancer freelancer) {
        try {
            // Random gecikme
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (freelancer.getFreelancerType().toString().equals("DESIGNER")) {
            int score = freelancer.getDesignTools() != null ? freelancer.getDesignTools().size() : 0;
            freelancer.setEvaluationScore(Math.min(score, 10));
        } else if (freelancer.getFreelancerType().toString().equals("SOFTWARE_DEVELOPER")) {
            int langCount = freelancer.getLanguagesKnown() != null ? freelancer.getLanguagesKnown().size() : 0;
            int specCount = freelancer.getSpecialties() != null ? freelancer.getSpecialties().size() : 0;
            int score = langCount * specCount;
            freelancer.setEvaluationScore(Math.min(score, 10));
        }

        return CompletableFuture.completedFuture(freelancer);
    }
}