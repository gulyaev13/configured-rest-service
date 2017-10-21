package ru.ifmo.se.configuredapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.se.configuredapp.decorator.MethodDecorator;
import ru.ifmo.se.configuredapp.factory.InvokeMethodAbstractFactory;
import ru.ifmo.se.configuredapp.model.InvokeMethod;
import ru.ifmo.se.configuredapp.model.MethodConfiguration;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfiguredRestController {
    @Autowired
    InvokeMethodAbstractFactory factory;
    private Map<String, MethodDecorator> getMethods = new HashMap<>();
    private Map<String, MethodDecorator> postMethods = new HashMap<>();
    private Map<String, MethodDecorator> putMethods = new HashMap<>();
    private Map<String, MethodDecorator> deleteMethods = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET, value = "/configure")
    public ResponseEntity<?> configure1(/*@RequestBody MethodConfiguration configuration*/){
        StringBuilder errors = new StringBuilder();
        InvokeMethod invokeMethod = factory.createInvokeMethod("{ return new Integer(3 + 11)}", "GET", errors);
        if(invokeMethod == null){
            return ResponseEntity.status(404).body(errors);
        }
        Object response = invokeMethod.apply();
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(errors);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/configure")
    public ResponseEntity<?> configure(@RequestBody MethodConfiguration configuration){
        String httpMethod = configuration.getHttpMethod();
        String methodName = configuration.getMethodName();
        String methodBody = configuration.getMethodBody();

        MethodConfiguration methodConfiguration ;
        if(httpMethod == null ||!httpMethod.toUpperCase().matches("(GET|POST|PUT|DELETE)")){
            return ResponseEntity.status(404).body("Wrong HTTP method! Supports methods: GET, POST, PUT, DELETE");
        }
        if(methodBody == null){
            return ResponseEntity.status(404).body("Miss method body!");
        }
        if(methodName == null){
            return ResponseEntity.status(404).body("Miss method name!");
        }
        StringBuilder errors = new StringBuilder();
        InvokeMethod method = factory.createInvokeMethod(methodBody, httpMethod, errors);
        if(method == null){
            return ResponseEntity.status(404).body(errors);
        }
        MethodDecorator methodDecorator = new MethodDecorator(configuration.getOnSuccessCode(),
                configuration.getOnFailCode(), method);
        MethodDecorator prevMethodDecorator = null;
        switch (httpMethod.toUpperCase()){
            case"GET":
                prevMethodDecorator = getMethods.put(methodName, methodDecorator);
                break;
            case"POST":
                prevMethodDecorator = postMethods.put(methodName, methodDecorator);
                break;
            case"PUT":
                prevMethodDecorator = putMethods.put(methodName, methodDecorator);
                break;
            case"DELETE":
                prevMethodDecorator = deleteMethods.put(methodName, methodDecorator);
                break;
        }
        if(prevMethodDecorator == null){
            return ResponseEntity.ok().body("create method :" + methodName + "\nbind on address: /api/" + methodName
                    + "\nHTTP method: " + httpMethod);
        }
        return ResponseEntity.ok().body("replace method :" + methodName + "\nbind on address: /api/" + methodName
                + "HTTP method: " + httpMethod);
    }

}
