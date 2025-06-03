package org.example.controller;

import org.example.model.Freelancer;
import org.example.model.FreelancerType;
import org.example.repository.FreelancerRepository;
import org.example.service.FreelancerEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/freelancers")
public class FreelancerController {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private FreelancerEvaluationService evaluationService;

    @PostMapping
    public Freelancer createFreelancer(@RequestBody Freelancer freelancer) {

        freelancer.setEvaluationScore(0);
        Freelancer savedFreelancer = freelancerRepository.save(freelancer);

        //async
        evaluationService.evaluate(savedFreelancer).thenAcceptAsync(evaluated -> {
            freelancerRepository.save(evaluated); // geç update
        });

        return savedFreelancer;
    }

    @GetMapping
    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Freelancer getFreelancer(@PathVariable Long id) {
        return freelancerRepository.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Freelancer> searchFreelancers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String designTool,
            @RequestParam(required = false) String languageKnown,
            @RequestParam(required = false) String specialty
    ) {
        if (name != null) return freelancerRepository.findByNameContainingIgnoreCase(name);
        if (city != null) return freelancerRepository.findByCityIgnoreCase(city);
        if (type != null) {
            try {
                FreelancerType freelancerType = FreelancerType.valueOf(type.toUpperCase());
                return freelancerRepository.findByFreelancerType(freelancerType);
            } catch (IllegalArgumentException e) {
                return List.of();
            }
        }
        if (designTool != null) return freelancerRepository.findByDesignTool(designTool);
        if (languageKnown != null) return freelancerRepository.findByLanguageKnown(languageKnown);
        if (specialty != null) return freelancerRepository.findBySpecialty(specialty);
        return freelancerRepository.findAll();
    }
}
