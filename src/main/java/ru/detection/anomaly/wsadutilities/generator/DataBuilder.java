package ru.detection.anomaly.wsadutilities.generator;

public interface DataBuilder<T> {

    T build(Object... args);
}
