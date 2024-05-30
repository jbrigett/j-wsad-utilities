package ru.detection.anomaly.wsadutilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.detection.anomaly.wsadutilities.loadgenerator.LoadGenerator;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@Log
@RequiredArgsConstructor
public class WsadUtilitiesApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WsadUtilitiesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadGenerator.start();
	}

	@Autowired
	private LoadGenerator loadGenerator;

}
