package com.codepilot.service;

import com.codepilot.model.AgentResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BugDetectionAgent {

    private final GroqService groq;

    public BugDetectionAgent(GroqService groq) {
        this.groq = groq;
    }

    public AgentResult analyze(String language, String code) {
        String prompt = """
                You are Agent 2 of a multi-agent code review system.
                Find likely correctness bugs, unsafe assumptions, null/reference issues,
                incorrect conditions, edge cases, and missing validation in the supplied code.
                Do not invent bugs. If the code appears correct, say so.
                Return 3 to 5 concise findings.
                Language: %s

                CODE:
                %s
                """.formatted(language, code);

        String answer = groq.ask(
                "You are a careful bug-finding engineer. Be conservative and evidence-based.",
                prompt
        );

        if (!answer.isBlank()) {
            return new AgentResult(
                    "Bug Detection Agent",
                    "The agent checked the code for correctness and edge-case problems.",
                    Arrays.asList(answer.split("\\R"))
            );
        }

        List<String> findings = new ArrayList<>();

        String lower = code.toLowerCase();

        if (lower.contains("null") && lower.contains(".")) {
            findings.add("Check nullable references before dereferencing them.");
        }

        if (lower.contains("array") || lower.contains("[") ) {
            findings.add("Check array or collection boundaries for empty and out-of-range inputs.");
        }

        if (lower.contains("while") || lower.contains("for")) {
            findings.add("Verify loop termination conditions for all edge cases.");
        }

        if (findings.isEmpty()) {
            findings.add("No obvious issue was detected by the local checks.");
            findings.add("Run unit tests with boundary, empty and invalid inputs.");
        }

        return new AgentResult(
                "Bug Detection Agent",
                "Basic local bug checks completed.",
                findings
        );
    }
}
