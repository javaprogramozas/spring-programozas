package hu.bearmaster.springtutorial.common.config;

import hu.bearmaster.springtutorial.common.scopes.ConsumerBean;
import hu.bearmaster.springtutorial.common.scopes.CountingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class ScopeConfig {

    @Bean
    @Scope(scopeName = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CountingBean countingBean() {
        return new CountingBean();
    }

    @Bean
    public ConsumerBean first(CountingBean bean) {
        return new ConsumerBean(bean);
    }

    @Bean
    public ConsumerBean second(CountingBean bean) {
        return new ConsumerBean(bean);
    }

}
