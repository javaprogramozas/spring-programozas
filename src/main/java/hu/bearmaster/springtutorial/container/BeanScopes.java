package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.config.ScopeConfig;
import hu.bearmaster.springtutorial.common.scopes.ConsumerBean;
import hu.bearmaster.springtutorial.common.scopes.CountingBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

public class BeanScopes {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanScopes.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getDefaultListableBeanFactory().registerScope("thread", new SimpleThreadScope());
        context.register(ScopeConfig.class);
        context.refresh();

        CountingBean countingBean = context.getBean("countingBean", CountingBean.class);
        LOGGER.info("Counting bean: {}", countingBean.getLabel());

        CountingBean countingBean2 = context.getBean("countingBean", CountingBean.class);
        LOGGER.info("Counting bean2: {}", countingBean2.getLabel());

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                ConsumerBean first = context.getBean("first", ConsumerBean.class);
                ConsumerBean second = context.getBean("second", ConsumerBean.class);

                LOGGER.info("First consumer has {}", first.getBeanLabel());
                LOGGER.info("Second consumer has {}", second.getBeanLabel());
            }).start();
        }
    }
}
