package com.codepilot.service;

import com.codepilot.model.AgentResult;
import com.codepilot.model.FinalResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultCombiner {

    public FinalResult combine(
            String language,
            AgentResult analysis,
            AgentResult bugs,
            AgentResult quality) {

        FinalResult result = new FinalResult();
        result.setLanguage(language);
        result.setCodeAnalysis(analysis);
        result.setBugDetection(bugs);
        result.setCodeQuality(quality);

        result.setOverallSummary(
                "Three specialized agents independently reviewed the code for behavior, " +
                "correctness and maintainability. Their findings were combined into this report."
        );

        List<String> issues = new ArrayList<>();
        issues.addAll(bugs.getFindings());
        result.setKeyIssues(issues);

        List<String> recommendations = new ArrayList<>();
        recommendations.addAll(quality.getFindings());
        recommendations.add("Validate the suggested changes with automated tests before using them in production.");
        result.setRecommendations(recommendations);

        return result;
    }
}
