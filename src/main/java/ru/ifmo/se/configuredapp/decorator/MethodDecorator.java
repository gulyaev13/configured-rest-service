package ru.ifmo.se.configuredapp.decorator;

import ru.ifmo.se.configuredapp.model.InvokeMethod;

public class MethodDecorator implements InvokeMethod {
    public int getOnSuccessCode() {
        return onSuccessCode;
    }

    public int getOnFailCode() {
        return onFailCode;
    }

    private int onSuccessCode;
    private int onFailCode;
    private InvokeMethod invokeMethod;


    public MethodDecorator(int onSuccessCode, int onFailCode, InvokeMethod invokeMethod) {
        this.onSuccessCode = onSuccessCode;
        this.onFailCode = onFailCode;
        this.invokeMethod = invokeMethod;
    }

    @Override
    public Object apply() {
        return invokeMethod.apply();
    }
}
