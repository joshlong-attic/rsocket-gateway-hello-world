package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		//ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);
		Hooks.onOperatorDebug();
		SpringApplication.run(GatewayApplication.class, args);
	}

}
