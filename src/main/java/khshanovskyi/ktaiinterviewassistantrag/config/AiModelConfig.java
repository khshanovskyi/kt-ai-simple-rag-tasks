package khshanovskyi.ktaiinterviewassistantrag.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration with AI models. Here we configure chat, streaming, and embedding model
 */
@Configuration
public class AiModelConfig {

    @Value("${ollama.model.name}")
    private String ollamaModelName;

    @Value("${ollama.host.url}")
    private String ollamaBaseUrl;


    @Value("${open-ai.api-key}")
    private String openAIApiKey;

    @Value("${open-ai.model.name}")
    private String openAIModelName;

    /**
     * Provides Bean of {@link ChatLanguageModel}: <br>
     *
     * EITHER ollama chat model ({@link OllamaChatModel}, it has builder) with uts model and base url (that is free and should run locally)<br>
     * OR open-ai chat model ({@link OpenAiChatModel}, it has builder) with uts model and api key (it is not for free, you need to pay credit on site)<br>
     *
     * @see <a href="https://docs.langchain4j.dev/integrations/language-models/ollama/">Langchain4j Ollama</a>
     * @see <a href="https://docs.langchain4j.dev/integrations/language-models/open-ai/">Langchain4j OpenAI</a>
     * @see <a href="https://platform.openai.com/docs/models/gpt-3-5-turbo">About models in OpenAI</a>
     */
    @Bean
    ChatLanguageModel chatLanguageModel() {
//        return OllamaChatModel.builder()
//                .modelName(ollamaModelName)
//                .baseUrl(ollamaBaseUrl)
//                .build();

        return OpenAiChatModel.builder()
                .modelName(openAIModelName)
                .apiKey(openAIApiKey)
                .build();
    }

    /**
     * Provides Bean of {@link StreamingChatLanguageModel}: <br>
     *
     * EITHER ollama streaming chat model ({@link OllamaStreamingChatModel}, it has builder) with uts model and base url (that is free and should run locally)<br>
     * OR open-ai streaming chat model ({@link OpenAiStreamingChatModel}, it has builder) with uts model and api key (it is not for free, you need to pay credit on site)<br>
     *
     * @see <a href="https://docs.langchain4j.dev/integrations/language-models/ollama/">Langchain4j Ollama</a>
     * @see <a href="https://docs.langchain4j.dev/integrations/language-models/open-ai/">Langchain4j OpenAI</a>
     * @see <a href="https://platform.openai.com/docs/models/gpt-3-5-turbo">About models in OpenAI</a>
     */
    @Bean
    StreamingChatLanguageModel streamingChatLanguageModel() {
//        return OllamaStreamingChatModel.builder()
//                .baseUrl(ollamaBaseUrl)
//                .modelName(ollamaModelName)
//                .build();

        return OpenAiStreamingChatModel.builder()
                .modelName(openAIModelName)
                .apiKey(openAIApiKey)
                .build();
    }

    /**
     * Been that will be used for creating <a href="https://docs.langchain4j.dev/category/embedding-models/">embeddings</a> from provided documents <br>
     *
     * @return {@link AllMiniLmL6V2EmbeddingModel} (just new AllMiniLmL6V2EmbeddingModel()) this model is running in
     * memory and doesn't use any LLM's <br><br>
     * <b>Also, you can use {@link OllamaEmbeddingModel} or {@link OpenAiEmbeddingModel}</b><br>
     * @see <a href="https://docs.langchain4j.dev/integrations/embedding-models/in-process">About onnx AllMiniLmL6V2EmbeddingModel model</a>
     */
    @Bean
    EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }

}
