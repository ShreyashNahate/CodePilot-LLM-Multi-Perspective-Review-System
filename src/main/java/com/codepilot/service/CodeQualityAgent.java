package com.codepilot.service;

import com.codepilot.model.AgentResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CodeQualityAgent {

    private final GroqService groq;

    public CodeQualityAgent(GroqService groq) {
        this.groq = groq;
    }

    public AgentResult analyze(String language, String code) {
        String prompt = """
                You are Agent 3 of a multi-agent code review system.
                Review the supplied source code for maintainability and code quality.
                Look at naming, structure, duplication, readability, separation of responsibilities,
                error handling and testability.
                Return 3 to 5 concise recommendations.
                Language: %s

                CODE:
                %s
                """.formatted(language, code);

        String answer = groq.ask(
                "You are a senior engineer focused on clean, maintainable production code.",
                prompt
        );

        if (!answer.isBlank()) {
            return new AgentResult(
                    "Code Quality Agent",
                    "The agent reviewed readability and maintainability.",
                    Arrays.asList(answer.split("\\R"))
            );
        }

        List<String> findings = new ArrayList<>();

        if (code.length() > 500) {
            findings.add("Consider splitting large methods or responsibilities into smaller units.");
        }

        findings.add("Use descriptive names for classes, methods and variables.");
        findings.add("Keep each class or method focused on one responsibility.");
        findings.add("Add unit tests for normal cases and edge cases.");

        return new AgentResult(
                "Code Quality Agent",
                "Basic local code-quality checks completed.",
                findings
        );
    }
}
