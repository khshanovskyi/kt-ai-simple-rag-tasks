# Simple RAG model with Spring, Ollama and PGVector


## Prerequisites:
### Choose LLM that you will use:
- **Ollama:** Run locally `llama3.1`, **PAY ATTENTION THAT IT CAN WORK SLOW DEPENDING ON MACHINE**
    - On Windows/Linux or Mac with Intel:
      - pull image and run ollama `docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama`
      - run llama3.1 `docker exec -it ollama ollama run llama3.1`
    - On Mac with M-series processors:
      - install ollama from [link](https://ollama.com/blog/ollama-is-now-available-as-an-official-docker-image) 
      - run `ollama run llama3.1`
      - 
- **OpenAI: PAY ATTENTION THAT YOU NEED TO PAY CREDIT TO USE ITS API** [OpenAI Platform](https://platform.openai.com/)
  - add your API_KEY to `application.yaml` -> `open-ai.api-key`
  - by default, we are using `gpt-3.5-turbo` model

### Run PG Vector DB 
Run locally PGVector DB, **Pay attention, pg vector port will be routed to 6024, I've done it intentionally in case of port forwarding issues with Postgres**
   - `docker run --name pgvector-container -e POSTGRES_USER=pgvector_user -e POSTGRES_PASSWORD=pgvector_pass -e POSTGRES_DB=kt-tasks -p 6024:5432 -d ankane/pgvector`

## Task:
1. Implement methods in `InterviewAssistantConfig` and `LangchaingConfig` according to the documentation
2. Run application
3. Test the flow with Postman `localhost:8080/v1/interview/chat?question=I need 10 questions about python` and in browser
4. **Take a look at database content, DB name: `kt-tasks`, table: `interviews`. Take a look at embeddings, text and metadata**
5. Try to change system prompt. For instance change expected output and say it to provide answers where it possible

### In case if you stuck with smth, you can check `completed` branch, or search Lanchain4j documentation
