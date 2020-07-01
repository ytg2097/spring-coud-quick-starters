package io.ac.starter.event.consumer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@Aspect
@Component
public class DomainEventRecordingConsumerAspect {

    private DomainEventRecordingConsumer domainEventRecordingConsumer;

    public DomainEventRecordingConsumerAspect(DomainEventRecordingConsumer domainEventRecordingConsumer) {
        this.domainEventRecordingConsumer = domainEventRecordingConsumer;
    }

    @Around(/*"@annotation(org.springframework.kafka.annotation.KafkaListener) || " +
            "@annotation(org.springframework.kafka.annotation.KafkaListeners)|| " +*/
            "@annotation(org.springframework.cloud.stream.annotation.StreamListener)")
    public Object recordEvents(ProceedingJoinPoint joinPoint) throws Throwable {

        return domainEventRecordingConsumer.recordAndConsume(joinPoint);
    }
}
