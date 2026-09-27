package com.codepilot.controller;

import com.codepilot.model.CodeRequest;
import com.codepilot.model.FinalResult;
import com.codepilot.service.OrchestratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CodeController {

    private final OrchestratorService orchestratorService;

    public CodeController(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<FinalResult> analyze(@RequestBody CodeRequest request) {
        return ResponseEntity.ok(orchestratorService.analyze(request));
    }

    @GetMapping("/health")
    public String health() {
        return "CodePilot is running";
    }
}
