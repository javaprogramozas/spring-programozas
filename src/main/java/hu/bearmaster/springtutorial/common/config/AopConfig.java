package hu.bearmaster.springtutorial.common.config;

import hu.bearmaster.springtutorial.common.aop.CalculatorService;
import hu.bearmaster.springtutorial.common.aop.MyAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public CalculatorService calculator() {
        return new CalculatorService();
    }

    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }

}
