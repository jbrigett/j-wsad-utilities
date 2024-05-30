package ru.detection.anomaly.wsadutilities.db;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Primary
public class RequestLogsServiceResourceImpl implements RequestLogsService {

    private static final Resource template_res = new ClassPathResource("static/access.log");
    private  List<String> requests = ResourceReader.asStrings(template_res);
    @PostConstruct
    public void init(){
        requests = requests.stream()
                .map(String::trim)
                .toList();
    }
    @Override
    public List<String> getRequestsBatch(int min, int max) {
        return requests;
    }
}
