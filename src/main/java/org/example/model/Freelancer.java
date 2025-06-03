package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String city;

    @Enumerated(EnumType.STRING)
    private FreelancerType freelancerType;


    private String portfolioUrl;  // sadece Designer için

    @ElementCollection
    private List<String> designTools;


    @ElementCollection
    private List<String> languagesKnown;  // Sadece Dev için

    @ElementCollection
    private List<String> specialties;

    private int evaluationScore;
}