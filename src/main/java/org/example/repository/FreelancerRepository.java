package org.example.repository;

import org.example.model.Freelancer;
import org.example.model.FreelancerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {

    List<Freelancer> findByNameContainingIgnoreCase(String name);

    List<Freelancer> findByCityIgnoreCase(String city);

    List<Freelancer> findByFreelancerType(FreelancerType type);



    @Query("SELECT f FROM Freelancer f WHERE :tool MEMBER OF f.designTools")
    List<Freelancer> findByDesignTool(String tool);

    @Query("SELECT f FROM Freelancer f WHERE :specialty MEMBER OF f.specialties")
    List<Freelancer> findBySpecialty(String specialty);

    @Query("SELECT f FROM Freelancer f WHERE :languageKnown MEMBER OF f.languagesKnown")
    List<Freelancer> findByLanguageKnown(String languageKnown);
}
