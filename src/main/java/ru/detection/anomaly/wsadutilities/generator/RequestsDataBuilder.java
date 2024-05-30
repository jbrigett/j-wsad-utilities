package ru.detection.anomaly.wsadutilities.generator;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.detection.anomaly.wsadutilities.db.RequestLogsService;
import ru.detection.anomaly.wsadutilities.db.ResourceReader;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Profile("logs-generator")
public class RequestsDataBuilder implements DataBuilder<List<String>> {

    private static final Resource template_res = new ClassPathResource("templates/requestTemplate.json");
    private String template;

    private final RequestLogsService requestLogsService;

    private final Random random = new Random();

    public RequestsDataBuilder(RequestLogsService requestLogsService) {
        this.requestLogsService = requestLogsService;
        template = ResourceReader.asString(template_res);
    }


    @Override
    public List<String> build(Object...s) {
        return requestLogsService.getRequestsBatch(0, 0)
                .stream()
                .map(this::buildOne)
                .collect(Collectors.toList());
    }

    public String buildOne(String request) {
        return String.format(template,  request);
    }
}
