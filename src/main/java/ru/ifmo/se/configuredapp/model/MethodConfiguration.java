package ru.ifmo.se.configuredapp.model;


public class MethodConfiguration {
    private int onSuccessCode;
    private int onFailCode;
    private String httpMethod;
    private String methodName;
    private String methodType;
    private String methodBody;
    private String returnValue;

    public MethodConfiguration(){}
    public MethodConfiguration(int onSuccessCode, int onFailCode, String httpMethod, String methodName, String methodType, String methodBody, String returnValue) {
        this.onSuccessCode = onSuccessCode;
        this.onFailCode = onFailCode;
        this.httpMethod = httpMethod;
        this.methodName = methodName;
        this.methodType = methodType;
        this.methodBody = methodBody;
        this.returnValue = returnValue;
    }
    public int getOnSuccessCode() {
        return onSuccessCode;
    }

    public void setOnSuccessCode(int onSuccessCode) {
        this.onSuccessCode = onSuccessCode;
    }

    public int getOnFailCode() {
        return onFailCode;
    }

    public void setOnFailCode(int onFailCode) {
        this.onFailCode = onFailCode;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
