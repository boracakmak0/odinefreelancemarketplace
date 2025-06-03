package org.example.repository;

import org.example.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByFreelancerId(Long freelancerId);
}
