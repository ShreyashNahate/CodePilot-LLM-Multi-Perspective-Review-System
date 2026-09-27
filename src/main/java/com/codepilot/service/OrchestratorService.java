package com.codepilot.service;

import com.codepilot.model.AgentResult;
import com.codepilot.model.CodeRequest;
import com.codepilot.model.FinalResult;
import org.springframework.stereotype.Service;

@Service
public class OrchestratorService {

    private final CodeAnalysisAgent codeAnalysisAgent;
    private final BugDetectionAgent bugDetectionAgent;
    private final CodeQualityAgent codeQualityAgent;
    private final ResultCombiner resultCombiner;

    public OrchestratorService(
            CodeAnalysisAgent codeAnalysisAgent,
            BugDetectionAgent bugDetectionAgent,
            CodeQualityAgent codeQualityAgent,
            ResultCombiner resultCombiner) {
        this.codeAnalysisAgent = codeAnalysisAgent;
        this.bugDetectionAgent = bugDetectionAgent;
        this.codeQualityAgent = codeQualityAgent;
        this.resultCombiner = resultCombiner;
    }

    public FinalResult analyze(CodeRequest request) {
        if (request == null || request.getCode() == null || request.getCode().isBlank()) {
            throw new IllegalArgumentException("Code cannot be empty");
        }

        String language = request.getLanguage();
        if (language == null || language.isBlank()) {
            language = "Unknown";
        }

        AgentResult analysis = codeAnalysisAgent.analyze(language, request.getCode());
        AgentResult bugs = bugDetectionAgent.analyze(language, request.getCode());
        AgentResult quality = codeQualityAgent.analyze(language, request.getCode());

        return resultCombiner.combine(language, analysis, bugs, quality);
    }
}
