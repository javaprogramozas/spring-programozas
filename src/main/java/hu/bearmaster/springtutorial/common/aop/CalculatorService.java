package hu.bearmaster.springtutorial.common.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);

    public int square(int number) {
        LOGGER.info("Calculating the square of {}", number);
        return number * number;
    }

}
