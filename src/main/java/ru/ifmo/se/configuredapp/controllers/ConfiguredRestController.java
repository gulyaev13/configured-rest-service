package ru.ifmo.se.configuredapp.controllers;

import javassist.ClassPool;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.se.configuredapp.model.MethodConfiguration;

@RestController
public class ConfiguredRestController {

    @RequestMapping(method = RequestMethod.POST, value = "/configure")
    public String configure(@RequestBody MethodConfiguration configuration){
        ClassPool cp = ClassPool.getDefault();
        try{
            cp.makeClass("ru.ifmo.se.configuredapp.model", cp.get("ru.ifmo.se.configuredapp.model.MethodImpl"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
