package hello.proxy.config.v4_postprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String BASE_PACKAGE;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String BASE_PACKAGE, Advisor advisor) {
        this.BASE_PACKAGE = BASE_PACKAGE;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(BASE_PACKAGE)) {
            return bean;
        }
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);

        return proxyFactory.getProxy();
    }
}
