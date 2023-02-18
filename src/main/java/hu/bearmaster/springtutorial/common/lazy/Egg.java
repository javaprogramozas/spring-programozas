package hu.bearmaster.springtutorial.common.lazy;

import org.springframework.stereotype.Component;

@Component
public class Egg {

    private Chicken chicken;

    public Egg(Chicken chicken) {
        this.chicken = chicken;
    }

    @Override
    public String toString() {
        return "Produced by " + chicken;
    }

}
