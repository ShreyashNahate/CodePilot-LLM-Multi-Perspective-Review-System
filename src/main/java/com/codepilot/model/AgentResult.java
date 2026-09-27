package com.codepilot.model;

import java.util.List;

public class AgentResult {
    private String agent;
    private String summary;
    private List<String> findings;

    public AgentResult() {}

    public AgentResult(String agent, String summary, List<String> findings) {
        this.agent = agent;
        this.summary = summary;
        this.findings = findings;
    }

    public String getAgent() { return agent; }
    public void setAgent(String agent) { this.agent = agent; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public List<String> getFindings() { return findings; }
    public void setFindings(List<String> findings) { this.findings = findings; }
}
