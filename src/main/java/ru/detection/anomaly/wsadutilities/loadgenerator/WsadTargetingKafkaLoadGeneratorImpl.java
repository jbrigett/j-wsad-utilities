package ru.detection.anomaly.wsadutilities.loadgenerator;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.detection.anomaly.wsadutilities.generator.DataBuilder;
import ru.detection.anomaly.wsadutilities.service.MessageSenderTargeting;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
@Profile("logs-generator")
public class WsadTargetingKafkaLoadGeneratorImpl implements LoadGenerator {

    private final DataBuilder<List<String>> dataBuilder;

    private final MessageSenderTargeting messageSender;
    private StopWatch stopWatch = new StopWatch();
    private int messagesSent = 0;

    @Override
    @SneakyThrows
    public void start() {
        stopWatch.start();
        while (!Thread.currentThread().isInterrupted()) {
            List<String> data = dataBuilder.build();
            data.forEach(messageSender::sendMessage);
            countMessages(data.size());
            Thread.sleep(150L);
            measureSpeed();
        }
    }

    private void countMessages(int count) {
        messagesSent += count;
    }

    private void measureSpeed() {
        stopWatch.split();
        if (stopWatch.getSplitTime() > 5000) {
            log.info("Send {} messages/s", String.format("%.3f", messagesSent / (stopWatch.getTime() / 1000.0d)));
            stopWatch.reset();
            stopWatch.start();
            messagesSent = 0;
        }
    }
}
