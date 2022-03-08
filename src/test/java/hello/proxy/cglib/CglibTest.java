package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {

        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // cglib 가 사용하는 객체
        enhancer.setSuperclass(ConcreteService.class); // 상속받아 사용할 Super Class
        enhancer.setCallback(new TimeMethodInterceptor(target));

        ConcreteService proxy = (ConcreteService)enhancer.create(); //proxy 생성
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());

        proxy.call();

    }
}
