package khshanovskyi.ktaiinterviewassistantrag.controllers;

import dev.langchain4j.service.TokenStream;
import khshanovskyi.ktaiinterviewassistantrag.assistants.InterviewAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("v1/interview")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewAssistant assistant;

    /**
     * Regular REST endpoint that produces the answer when AI fully collected it.
     * You can freely test it with Postman
     */
    @GetMapping("/chat")
    public String chat(@RequestParam("question") String question) {
        return assistant.chat(question);
    }

    /**
     * Provides answer as stream of strings. That allows to reduce time from 'question' to 'first answer words' and
     * simulate 'real' real conversation.
     * Pay attention that streaming doesn't work with Postman (or probably I haven't found a way how to reproduce it),
     * instead open <a href="http://localhost:8080">localhost</a>, in the resource persist <i>index.html</i> that allows
     * check streaming.
     */
    @GetMapping(value = "/chat-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<String> chatStream(@RequestParam("question") String question) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        TokenStream ts = assistant.chatStream(question);
        ts.onNext(sink::tryEmitNext)
                .onError(sink::tryEmitError)
                .start();

        return sink.asFlux();
    }

}
