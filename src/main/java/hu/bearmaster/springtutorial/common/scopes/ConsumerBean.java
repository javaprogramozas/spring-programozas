package hu.bearmaster.springtutorial.common.scopes;

public class ConsumerBean {

    private final CountingBean bean;

    public ConsumerBean(CountingBean bean) {
        this.bean = bean;
    }

    public String getBeanLabel() {
        return bean.getLabel();
    }
}
