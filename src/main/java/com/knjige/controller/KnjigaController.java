package com.knjige.controller;

import com.knjige.model.Knjiga;
import com.knjige.service.KnjigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knjige")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class KnjigaController {

    private final KnjigaService knjigaService;

    @GetMapping("/search")
    public ResponseEntity<List<Knjiga>> searchKnjige(
            @RequestParam(required = false) String razred,
            @RequestParam(required = false) String predmet,
            @RequestParam(required = false) String izdavac) {
        
        return ResponseEntity.ok(knjigaService.findByFilters(razred, predmet, izdavac));
    }
} 