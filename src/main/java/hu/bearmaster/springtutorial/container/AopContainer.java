package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.aop.CalculatorService;
import hu.bearmaster.springtutorial.common.config.AopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopContainer.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        CalculatorService calculator = context.getBean(CalculatorService.class);

        callCalculator(calculator, 5);
        callCalculator(calculator, 5);
        callCalculator(calculator, 6);
    }

    private static void callCalculator(CalculatorService calculator, int input) {
        int square = calculator.square(input);
        LOGGER.info("The square of {} is {}", input, square);
    }
}
