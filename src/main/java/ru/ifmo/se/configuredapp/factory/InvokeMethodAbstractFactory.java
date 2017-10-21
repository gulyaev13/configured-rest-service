package ru.ifmo.se.configuredapp.factory;

import ru.ifmo.se.configuredapp.model.InvokeMethod;

public interface InvokeMethodAbstractFactory {
    InvokeMethod createInvokeMethod(String methodBody, String httpMethod, StringBuilder errors);
}
