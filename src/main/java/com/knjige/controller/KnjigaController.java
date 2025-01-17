package com.knjige.controller;

import com.knjige.model.Knjiga;
import com.knjige.service.KnjigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/knjige")
@RequiredArgsConstructor
public class KnjigaController {

    private final KnjigaService knjigaService;
    
    @Value("${MYSQLHOST:not_set}")
    private String mysqlHost;
    
    @Value("${MYSQLPORT:not_set}")
    private String mysqlPort;
    
    @Value("${MYSQLDATABASE:not_set}")
    private String mysqlDatabase;
    
    @Value("${MYSQLUSER:not_set}")
    private String mysqlUser;

    @GetMapping("/search")
    public List<Knjiga> searchKnjige(
            @RequestParam(required = false) String razred,
            @RequestParam(required = false) String predmet,
            @RequestParam(required = false) String izdavac) {
        return knjigaService.findByFilters(razred, predmet, izdavac);
    }
    
    @GetMapping("/env")
    public Map<String, String> getEnvVariables() {
        Map<String, String> env = new HashMap<>();
        env.put("MYSQLHOST", mysqlHost);
        env.put("MYSQLPORT", mysqlPort);
        env.put("MYSQLDATABASE", mysqlDatabase);
        env.put("MYSQLUSER", mysqlUser);
        // Not including password for security
        return env;
    }
} 