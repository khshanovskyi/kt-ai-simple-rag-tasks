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
        throw new RuntimeException("ChatLanguageModel is not implemented yet, please provide implementation in `LangchaingConfig#chatLanguageModel`");
    }

    /**
     * Provides Bean of ollama streaming chat model ({@link OllamaStreamingChatModel}, it has builder) with uts model and base url
     */
    @Bean
    StreamingChatLanguageModel streamingChatLanguageModel() {
        throw new RuntimeException("StreamingChatLanguageModel is not implemented yet, please provide implementation in `LangchaingConfig#streamingChatLanguageModel`");
    }

    /**
     * Been that will be used for creating <a href="https://docs.langchain4j.dev/category/embedding-models/">embeddings</a> from provided documents
     *
     * @return {@link AllMiniLmL6V2EmbeddingModel} (just new AllMiniLmL6V2EmbeddingModel()) this model is running in
     * memory and doesn't use any LLM's
     * @see <a href="https://docs.langchain4j.dev/integrations/embedding-models/in-process">About onnx AllMiniLmL6V2EmbeddingModel model</a>
     */
    @Bean
    EmbeddingModel embeddingModel() {
        throw new RuntimeException("EmbeddingModel is not implemented yet, please provide implementation in `LangchaingConfig#embeddingModel`");
    }

}
