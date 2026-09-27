package com.codepilot.model;

import java.util.List;

public class FinalResult {
    private String language;
    private String overallSummary;
    private List<String> keyIssues;
    private List<String> recommendations;
    private AgentResult codeAnalysis;
    private AgentResult bugDetection;
    private AgentResult codeQuality;

    public FinalResult() {}

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getOverallSummary() { return overallSummary; }
    public void setOverallSummary(String overallSummary) { this.overallSummary = overallSummary; }

    public List<String> getKeyIssues() { return keyIssues; }
    public void setKeyIssues(List<String> keyIssues) { this.keyIssues = keyIssues; }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }

    public AgentResult getCodeAnalysis() { return codeAnalysis; }
    public void setCodeAnalysis(AgentResult codeAnalysis) { this.codeAnalysis = codeAnalysis; }

    public AgentResult getBugDetection() { return bugDetection; }
    public void setBugDetection(AgentResult bugDetection) { this.bugDetection = bugDetection; }

    public AgentResult getCodeQuality() { return codeQuality; }
    public void setCodeQuality(AgentResult codeQuality) { this.codeQuality = codeQuality; }
}
