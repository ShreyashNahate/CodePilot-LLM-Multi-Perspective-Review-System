# CodePilot — Multi-Agent AI Code Intelligence Platform

A simple interview-ready project built with Java, Spring Boot, Groq and HTML/CSS/JavaScript.

## Architecture

User Code -> Spring Boot Orchestrator -> 3 Agents -> Result Combiner -> UI

Agents:
1. Code Analysis Agent
2. Bug Detection Agent
3. Code Quality Agent

Each agent independently analyzes the same code. Groq is used when GROQ_API_KEY is configured. If no API key is available, the agents use simple deterministic fallback checks.

## Requirements

- Java 17+
- Maven 3.8+
- Optional: Groq API key

## Run

Set your Groq key as an environment variable:

macOS/Linux:

```bash
export GROQ_API_KEY="your_key_here"
```

Optional model:

```bash
export GROQ_MODEL="llama-3.3-70b-versatile"
```

Start:

```bash
mvn spring-boot:run
```

Open:

http://localhost:8080

## API

POST /api/analyze

Example:

```json
{
  "language": "Java",
  "code": "class Main { public static void main(String[] args) { System.out.println(\"Hello\"); } }"
}
```

Health:

GET /api/health
