package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello hello = new Hello();

        log.info("start");
        String result1 = hello.callA();
        log.info("result = {} " , result1);


        log.info("start");
        String result2 = hello.callB();
        log.info("result = {} " , result2);
    }


    @Test
    void reflection1() throws Exception {

        Hello hello = new Hello();
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        log.info("start");
        Method methodCallA = classHello.getMethod("callA");
        Object result = methodCallA.invoke(hello);
        log.info("result = {}" , result);

        log.info("start");
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(hello);
        log.info("result = {}" , result2);
    }

    @Test
    void reflection2() throws Exception{
        Hello hello = new Hello();
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(hello,methodCallA);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(hello,methodCallB);
    }

    private void dynamicCall(Object target, Method method) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result = {}" , result);
    }

    @Slf4j
    static class Hello{

        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }


}
