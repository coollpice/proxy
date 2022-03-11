package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }


    @Around("execution(* hello.proxy.app..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        TraceStatus traceStatus = null;

        try {
            String message = joinPoint.getSignature().toShortString();
            traceStatus = logTrace.begin(message);

            Object proceed = joinPoint.proceed();

            logTrace.end(traceStatus);
            return proceed;
        } catch (Exception e) {
            logTrace.exception(traceStatus , e);
            throw e;
        }
    }
}
