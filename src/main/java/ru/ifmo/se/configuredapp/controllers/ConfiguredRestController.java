package ru.ifmo.se.configuredapp.controllers;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.se.configuredapp.model.InvokeMethod;
import ru.ifmo.se.configuredapp.model.MethodConfiguration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfiguredRestController {

    private Map<String, MethodConfiguration> getMethods = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET, value = "/configure")
    public ResponseEntity<?> configure(/*@RequestBody MethodConfiguration configuration*/){
        ClassPool pool = ClassPool.getDefault();
        try{
            CtClass functionClass =  pool.makeClass("ru.ifmo.se.configuredapp.model.InvokeMethod$" + "get1");
            functionClass.setInterfaces(new CtClass[] { pool.makeClass("ru.ifmo.se.configuredapp.model.InvokeMethod") });
            functionClass.addMethod(CtNewMethod.make("public Object apply(){ return new Integer(3 + 10);}", functionClass));
            InvokeMethod invokeMethod = (InvokeMethod)functionClass.toClass().newInstance();
            System.err.println((Integer)invokeMethod.apply());
            return ResponseEntity.ok(invokeMethod.apply());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return ResponseEntity.badRequest().build();
    }


}
