package org.example.controller;

import org.example.model.Job;
import org.example.repository.JobRepository;
import org.example.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    // Builder
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        if (job.getFreelancerId() == null || !freelancerRepository.existsById(job.getFreelancerId())) {
            throw new RuntimeException("Freelancer ID is missing or does not exist.");
        }

        if (job.getDescription() == null || job.getDescription().trim().isEmpty()) {
            throw new RuntimeException("Job description cannot be empty.");
        }

        job.setCreatedDate(LocalDate.now());

        return jobRepository.save(job);
    }

    // Freelancer' ın işi
    @GetMapping("/freelancer/{freelancerId}")
    public List<Job> getJobsByFreelancer(@PathVariable Long freelancerId) {
        return jobRepository.findByFreelancerId(freelancerId);
    }

    // ID den işi bulmak için
    @GetMapping("/{id}")
    public Job getJob(@PathVariable Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    // Update
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        Optional<Job> jobOpt = jobRepository.findById(id);
        if (jobOpt.isPresent()) {
            Job job = jobOpt.get();
            job.setJobStatus(updatedJob.getJobStatus());
            job.setDescription(updatedJob.getDescription());
            return jobRepository.save(job);
        } else {
            return null;
        }
    }
}
