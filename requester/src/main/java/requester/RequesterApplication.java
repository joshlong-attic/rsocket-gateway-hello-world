package requester;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.rsocket.client.BrokerClient;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class RequesterApplication {
	public static void main(String[] args) {
		SpringApplication.run(RequesterApplication.class, args);
	}
}

@Log4j2
@Service
@RequiredArgsConstructor
class GreetingClient {

	private final BrokerClient client;

	@EventListener
	public void ready(PayloadApplicationEvent<RSocketRequester> event) {
		RSocketRequester requester = event.getPayload();
		requester
			.route("greetings")
			.metadata(this.client.forwarding("responder"))
			.data(new GreetingRequest("World"))
			.retrieveFlux(GreetingResponse.class)
			.subscribe(gr -> log.info("new message: " + gr.getMessage()));
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