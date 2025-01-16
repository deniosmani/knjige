package com.knjige.service;

import com.knjige.model.Knjiga;
import com.knjige.repository.KnjigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KnjigaService {

    private final KnjigaRepository knjigaRepository;

    public List<Knjiga> findByFilters(String razred, String predmet, String izdavac) {
        return knjigaRepository.findByFilters(razred, predmet, izdavac);
    }
} 