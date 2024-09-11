package khshanovskyi.ktaiinterviewassistantrag.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration with AI models. Here we configure chat, streaming, and embedding model
 */
@Configuration
public class LangchaingConfig {

    @Value("${ollama.model.name}")
    private String modelName;

    @Value("${ollama.host.url}")
    private String baseUrl;

    /**
     * Provides Bean of ollama chat model ({@link OllamaChatModel}, it has builder) with uts model and base url
     */
    @Bean
    ChatLanguageModel chatLanguageModel() {
        return OllamaChatModel.builder()
                .modelName(modelName)
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * Provides Bean of ollama streaming chat model ({@link OllamaStreamingChatModel}, it has builder) with uts model and base url
     */
    @Bean
    StreamingChatLanguageModel streamingChatLanguageModel() {
        return OllamaStreamingChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .build();
    }

    /**
     * Been that will be used for creating <a href="https://python.langchain.com/v0.2/docs/concepts/#embedding-models">embeddings</a> from provided documents
     *
     * @return {@link AllMiniLmL6V2EmbeddingModel}
     */
    @Bean
    EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }

}
