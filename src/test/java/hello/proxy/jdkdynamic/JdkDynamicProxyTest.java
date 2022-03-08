package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {

        AInterface aInterface = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(aInterface);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();

        log.info("targetClass = {}" , aInterface.getClass());
        log.info("proxyClass = {}" , proxy.getClass());

    }

    @Test
    void dynamicB() {

        BInterface bInterface = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(bInterface);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        proxy.call();

        log.info("targetClass = {}" , bInterface.getClass());
        log.info("proxyClass = {}" , proxy.getClass());

    }

}
