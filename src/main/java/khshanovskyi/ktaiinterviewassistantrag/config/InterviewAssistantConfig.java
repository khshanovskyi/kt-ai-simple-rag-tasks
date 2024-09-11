package khshanovskyi.ktaiinterviewassistantrag.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import khshanovskyi.ktaiinterviewassistantrag.assistants.InterviewAssistant;
import khshanovskyi.ktaiinterviewassistantrag.setting.DbSettings;
import khshanovskyi.ktaiinterviewassistantrag.utils.DbUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocuments;
import static khshanovskyi.ktaiinterviewassistantrag.utils.Utils.glob;
import static khshanovskyi.ktaiinterviewassistantrag.utils.Utils.toPath;

@Configuration
public class InterviewAssistantConfig {

    private final DbSettings dbSettings;

    public InterviewAssistantConfig(DbSettings dbSettings) {
        this.dbSettings = dbSettings;

        //Adds required extensions and perform DB clean
        DbUtils.initDB(dbSettings, dbSettings.getInterviewsTableName());
    }

    /**
     * Create Bean of {@link InterviewAssistant}. Under the hood will be created a proxy that will have all AI components.<br>
     * <p>
     * 0. Load list with documents via {@link FileSystemDocumentLoader#loadDocuments(Path, PathMatcher)}.
     * - To get path to documents use Utils method <i>toPath("documents/interview/")</i>
     * - As path matcher use Utils method <i>glob("*.txt")</i> <br>
     * <p>
     * 1. Use {@link AiServices} builder and pass {@link InterviewAssistant} class <br>
     * <p>
     * 2. Configure chat language model (TODO: need to configure this bean in {@link LangchaingConfig#chatLanguageModel()}) <br>
     * <p>
     * 3. Configure streaming chat language model (TODO: need to configure this bean in {@link LangchaingConfig#streamingChatLanguageModel()}) <br>
     * <p>
     * 4. Configure chat memory, can use {@link MessageWindowChatMemory#withMaxMessages(int)} where you can configure
     * amount of messages from history to pass with request <br>
     * <p>
     * 5. Configure content retriever (TODO: provide implementation of the {@link InterviewAssistantConfig#createContentRetriever(List, EmbeddingStore, EmbeddingModel, DocumentSplitter)}) <br>
     * <p>
     * 5.1 As document splitter fell free to use {@link DocumentSplitters#recursive(int, int)} with 300 characters <br>
     * <p>
     * 6. Build it <br>
     *
     * @param chatLanguageModel          Bean, instance of {@link ChatLanguageModel}
     * @param streamingChatLanguageModel Bean, instance of {@link StreamingChatLanguageModel}
     * @param embeddingModel             Bean, instance of {@link EmbeddingModel}
     * @see LangchaingConfig
     */
    @Bean
    InterviewAssistant interviewAssistant(ChatLanguageModel chatLanguageModel,
                                          StreamingChatLanguageModel streamingChatLanguageModel,
                                          EmbeddingModel embeddingModel) throws URISyntaxException {
        throw new RuntimeException("InterviewAssistant is not implemented yet, please provide implementation in `InterviewAssistantConfig#interviewAssistant`");
    }

    /**
     * Implement method that creates instance of embedding store<br>
     * Configure:<br>
     * - host <br>
     * - port <br>
     * - database <br>
     * - password <br>
     * - table <br>
     * - dimension (set it as 384) because we use small AI model<br>
     * <br>
     * <b>Almost all parameters are already preconfigured, use <i>dbSettings</i> Bean</b>
     *
     * @return instance of {@link PgVectorEmbeddingStore}
     */
    private static EmbeddingStore<TextSegment> embeddingStore(DbSettings dbSettings) {
        throw new RuntimeException("EmbeddingStore is not implemented yet, please provide implementation in `InterviewAssistantConfig#embeddingStore`");
    }

    /**
     * Implement method that creates instance of content retriever.<br>
     * <p>
     * 0. Create {@link EmbeddingStoreIngestor} via builder. <br>
     * - configure embedding model <br>
     * - configure embedding store <br>
     * - configure document splitter <br>
     * <p>
     * 1. Ingest documents into embeddingStore via ingestor, at this step we will cut documents on chunks. <br>
     * <p>
     * 2. Return content retriever via {@link EmbeddingStoreContentRetriever#from(EmbeddingStore)}
     *
     * @param documents        loaded files in {@link Document} representation
     * @param embeddingStore   created embedding store
     * @param embeddingModel   Bean of embedding model
     * @param documentSplitter that will split provided documents on smaller documents (chunks)
     * @return instance of {@link ContentRetriever}
     */
    private static ContentRetriever createContentRetriever(List<Document> documents,
                                                           EmbeddingStore<TextSegment> embeddingStore,
                                                           EmbeddingModel embeddingModel,
                                                           DocumentSplitter documentSplitter) {
        throw new RuntimeException("ContentRetriever is not implemented yet, please provide implementation in `InterviewAssistantConfig#createContentRetriever`");
    }


}
