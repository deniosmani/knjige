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

        List<Knjiga> allBooks = knjigaRepository.findAll();
        log.info("Total books before filtering: {}", allBooks.size());

        List<Knjiga> filteredBooks = allBooks.stream()
            .filter(knjiga -> {
                boolean razredMatch = razred == null || razred.isEmpty() || razred.equals(knjiga.getRazred());
                boolean predmetMatch = predmet == null || predmet.isEmpty() || predmet.equalsIgnoreCase(knjiga.getPredmet());
                boolean izdavacMatch = izdavac == null || izdavac.isEmpty() || izdavac.equalsIgnoreCase(knjiga.getIzdavac());
                
                boolean matches = razredMatch && predmetMatch && izdavacMatch;
                
                if (matches) {
                    log.debug("Book matches filters: {}", knjiga);
                    log.debug("Comparing - razred: '{}' with '{}', predmet: '{}' with '{}', izdavac: '{}' with '{}'",
                            razred, knjiga.getRazred(),
                            predmet, knjiga.getPredmet(),
                            izdavac, knjiga.getIzdavac());
                }
                
                return matches;
            })
            .collect(Collectors.toList());

        log.info("Found {} books after filtering", filteredBooks.size());
        return filteredBooks;
    }
} 