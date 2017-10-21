package ru.ifmo.se.configuredapp.factory;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ifmo.se.configuredapp.model.InvokeMethod;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class InvokeMethodAbstractFactoryImpl implements InvokeMethodAbstractFactory {
    private ClassPool pool;
    private AtomicLong atomicLong;
    public InvokeMethodAbstractFactoryImpl(){
        pool = ClassPool.getDefault();
        atomicLong = new AtomicLong(0);
    }
    @Override
    public InvokeMethod createInvokeMethod(String methodBody, String httpMethod, StringBuilder errors) {
        try{
            CtClass functionClass =  pool.makeClass(
                    "ru.ifmo.se.configuredapp.model.InvokeMethod$" + httpMethod + atomicLong.getAndIncrement());
            functionClass.setInterfaces(new CtClass[] {
                    pool.makeClass("ru.ifmo.se.configuredapp.model.InvokeMethod")
                }
            );
            functionClass.addMethod(
                    CtNewMethod.make("public Object apply()" + methodBody, functionClass)
            );
            return  (InvokeMethod)functionClass.toClass().newInstance();
        } catch (Throwable e) {
            errors.append(e.getMessage());
            //e.printStackTrace(System.err);
        }
        return null;
    }
}
