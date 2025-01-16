package com.knjige.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "knjige")
public class Knjiga {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String razred;
    
    @Column(nullable = false)
    private String predmet;
    
    @Column(nullable = false)
    private String izdavac;
    
    @Column(nullable = false)
    private String slikaUrl;
} 