package ru.detection.anomaly.wsadutilities.db;

import java.util.List;

public interface RequestLogsService {
    List<String> getRequestsBatch(int min, int max);
}
