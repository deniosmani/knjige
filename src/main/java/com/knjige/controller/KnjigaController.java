package com.knjige.controller;

import com.knjige.model.Knjiga;
import com.knjige.service.KnjigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/knjige")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class KnjigaController {
    private static final Logger logger = LoggerFactory.getLogger(KnjigaController.class);
    
    private final KnjigaService knjigaService;
    
    @Value("${spring.datasource.url:not_set}")
    private String dbUrl;
    
    @Value("${MYSQLHOST:not_set}")
    private String mysqlHost;
    
    @Value("${MYSQLPORT:not_set}")
    private String mysqlPort;
    
    @Value("${MYSQLDATABASE:not_set}")
    private String mysqlDatabase;
    
    @Value("${MYSQLUSER:not_set}")
    private String mysqlUser;

    @PostConstruct
    public void init() {
        logger.error("=== DEBUGGING DATABASE CONNECTION ===");
        logger.error("Database URL: {}", dbUrl);
        logger.error("Host: {}", mysqlHost);
        logger.error("Port: {}", mysqlPort);
        logger.error("Database: {}", mysqlDatabase);
        logger.error("User: {}", mysqlUser);
        logger.error("=====================================");
    }

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