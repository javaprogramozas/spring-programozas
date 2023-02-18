package hu.bearmaster.springtutorial.common.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Chicken {

    private Egg egg;

    public Chicken(@Lazy Egg egg) {
        this.egg = egg;
    }

    @Override
    public String toString() {
        return "Chicken" + (egg != null ? " with an egg" : "");
    }

    public Egg getEgg() {
        return egg;
    }

}
