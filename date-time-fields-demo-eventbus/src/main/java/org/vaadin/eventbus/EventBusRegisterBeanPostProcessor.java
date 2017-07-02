package org.vaadin.eventbus;

import com.google.common.eventbus.EventBus;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 */
public class EventBusRegisterBeanPostProcessor implements BeanPostProcessor {

    private Object primitiveBean;

    private EventBus eventBus;

    public EventBusRegisterBeanPostProcessor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public Object getPrimitiveBean() {
        return primitiveBean;
    }

    public void setPrimitiveBean(Object primitiveBean) {
        this.primitiveBean = primitiveBean;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        this.primitiveBean = bean;
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // EventListener
        if (bean instanceof EventListener) {
            if (AopUtils.isAopProxy(bean)) {
                registerListener((EventListener) bean);
            } else {
                registerListener((EventListener) bean);
            }
        }
        return bean;
    }

    private void registerListener(EventListener listener) {
        eventBus.register(listener);
    }
}
