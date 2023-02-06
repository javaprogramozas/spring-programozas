package hu.bearmaster.springtutorial.common.scopes;

import java.util.concurrent.atomic.AtomicInteger;

public class CountingBean {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private final String label;

    public CountingBean() {
        this.label = "Bean #" + COUNTER.addAndGet(1);
    }

    public String getLabel() {
        return label;
    }
}
