package com.knjige.service;

import com.knjige.model.Knjiga;
import com.knjige.repository.KnjigaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KnjigaService {

    private final KnjigaRepository knjigaRepository;

    public List<Knjiga> findByFilters(String razred, String predmet, String izdavac) {
        log.info("Searching with filters - razred: {}, predmet: {}, izdavac: {}", razred, predmet, izdavac);

        if (razred == null && predmet == null && izdavac == null) {
            log.info("No filters provided, returning all books");
            return knjigaRepository.findAll();
        }

        List<Knjiga> allBooks = knjigaRepository.findAll();
        log.info("Total books before filtering: {}", allBooks.size());

        List<Knjiga> filteredBooks = allBooks.stream()
            .filter(knjiga -> {
                boolean matches = (razred == null || razred.equals(knjiga.getRazred())) &&
                                (predmet == null || predmet.equals(knjiga.getPredmet())) &&
                                (izdavac == null || izdavac.equals(knjiga.getIzdavac()));
                
                if (matches) {
                    log.debug("Book matches filters: {}", knjiga);
                }
                
                return matches;
            })
            .collect(Collectors.toList());

        log.info("Found {} books after filtering", filteredBooks.size());
        return filteredBooks;
    }
} 