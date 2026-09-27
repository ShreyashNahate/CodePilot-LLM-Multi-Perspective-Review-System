package com.codepilot.service;

import com.codepilot.model.AgentResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CodeAnalysisAgent {

    private final GroqService groq;

    public CodeAnalysisAgent(GroqService groq) {
        this.groq = groq;
    }

    public AgentResult analyze(String language, String code) {
        String prompt = """
                You are Agent 1 of a multi-agent code review system.
                Analyze the supplied source code only.
                Explain what the code does, its main components, and important implementation details.
                Return 3 to 5 concise findings as bullet points.
                Do not discuss time or space complexity unless necessary to explain the implementation.
                Language: %s

                CODE:
                %s
                """.formatted(language, code);

        String answer = groq.ask(
                "You are a precise senior software engineer performing code analysis.",
                prompt
        );

        if (!answer.isBlank()) {
            return new AgentResult(
                    "Code Analysis Agent",
                    "The agent analyzed the structure and purpose of the submitted code.",
                    Arrays.asList(answer.split("\\R"))
            );
        }

        return new AgentResult(
                "Code Analysis Agent",
                "Basic local analysis completed.",
                Arrays.asList(
                        "Language: " + language,
                        "Code contains " + countLines(code) + " non-empty lines.",
                        "Review the main classes, methods, inputs, outputs and control flow."
                )
        );
    }

    private int countLines(String code) {
        return (int) Arrays.stream(code.split("\\R"))
                .filter(line -> !line.trim().isEmpty())
                .count();
    }
}
