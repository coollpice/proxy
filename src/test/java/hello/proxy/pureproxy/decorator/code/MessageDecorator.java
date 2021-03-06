package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{

    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("데코레이터 호출");
        String result = component.operation();
        String decoResult = "****" + result + "****";
        return decoResult;
    }
}
