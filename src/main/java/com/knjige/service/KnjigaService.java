package com.knjige.service;

import com.knjige.model.Knjiga;
import com.knjige.repository.KnjigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KnjigaService {

    private final KnjigaRepository knjigaRepository;

    public List<Knjiga> findByFilters(String razred, String predmet, String izdavac) {
        if (razred == null && predmet == null && izdavac == null) {
            return knjigaRepository.findAll();
        }

        return knjigaRepository.findAll().stream()
            .filter(knjiga -> 
                (razred == null || razred.equals(knjiga.getRazred())) &&
                (predmet == null || predmet.equals(knjiga.getPredmet())) &&
                (izdavac == null || izdavac.equals(knjiga.getIzdavac()))
            )
            .collect(Collectors.toList());
    }
} 