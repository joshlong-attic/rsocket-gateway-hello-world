package responder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

import static java.util.stream.Stream.generate;

@SpringBootApplication
public class ResponderApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResponderApplication.class, args);
	}
}

@Slf4j
@Controller
class GreetingsController {

	@MessageMapping("greetings")
	Flux<GreetingResponse> greet(GreetingRequest request) {
		return Flux
			.fromStream(generate(() -> new GreetingResponse("Hello " + request.getName() + " @ " + Instant.now().toString() + "!")))
			.delayElements(Duration.ofSeconds(1));
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingRequest {
	private String name;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingResponse {
	private String message;
}
