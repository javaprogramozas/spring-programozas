package hu.bearmaster.springtutorial.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class MyAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAspect.class);

    private Map<Object, Integer> cache = new HashMap<>();

    @Pointcut("execution(* hu.bearmaster.springtutorial.common.aop.CalculatorService.square(..)) && args(a)")
    private void methodWithIntArgument(int a) {}

    @Before("hu.bearmaster.springtutorial.common.aop.MyAspect.methodWithIntArgument(arg)")
    public void logCall(int arg) {
        LOGGER.info("square() method called with argument {}", arg);
    }

    //@Around("hu.bearmaster.springtutorial.common.aop.MyAspect.methodWithIntArgument(a)")
    @Around("execution(* hu.bearmaster.springtutorial.common.aop.CalculatorService.square(..))")
    public Object cacheResult(ProceedingJoinPoint pjp) throws Throwable {
        Object arg = pjp.getArgs()[0];
        Integer cachedValue = cache.get(arg);

        if (cachedValue == null) {
            Integer calculatedValue = (Integer)pjp.proceed();
            cache.put(arg, calculatedValue);
            cachedValue = calculatedValue;
        }
        return cachedValue;
    }

}
