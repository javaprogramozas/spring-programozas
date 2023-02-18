package hu.bearmaster.springtutorial.common.config;

import hu.bearmaster.springtutorial.common.lazy.Chicken;
import hu.bearmaster.springtutorial.common.lazy.Dog;
import hu.bearmaster.springtutorial.common.lazy.Egg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
//@ComponentScan("hu.bearmaster.springtutorial.common.lazy")
public class LazyConfig {

    @Bean
    public Chicken chicken(@Lazy Egg egg) {
        return new Chicken(egg);
    }

    @Bean
    public Egg egg(Chicken chicken) {
        return new Egg(chicken);
    }

    @Lazy
    @Bean
    public Dog dog() {
        return new Dog();
    }
}
