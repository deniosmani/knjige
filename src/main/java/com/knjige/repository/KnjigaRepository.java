package com.knjige.repository;

import com.knjige.model.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
    
    @Query("SELECT k FROM Knjiga k WHERE " +
           "(:razred is null or k.razred = :razred) and " +
           "(:predmet is null or k.predmet = :predmet) and " +
           "(:izdavac is null or k.izdavac = :izdavac)")
    List<Knjiga> findByFilters(
        @Param("razred") String razred,
        @Param("predmet") String predmet,
        @Param("izdavac") String izdavac
    );
} 