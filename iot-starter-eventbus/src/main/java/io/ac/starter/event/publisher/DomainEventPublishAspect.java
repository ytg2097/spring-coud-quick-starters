package io.ac.starter.event.publisher;

import io.ac.starter.logging.AutoNamingLoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@Aspect
@Component
public class DomainEventPublishAspect {

    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private TaskExecutor taskExecutor;
    private DomainEventPublisher publisher;

    public DomainEventPublishAspect(TaskExecutor taskExecutor,
                                    DomainEventPublisher publisher) {
        this.taskExecutor = taskExecutor;
        this.publisher = publisher;
    }

    @After("@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.kafka.annotation.KafkaListener) ||" +
            "@annotation(org.springframework.kafka.annotation.KafkaListeners) ||" +
            "@annotation(org.springframework.cloud.stream.annotation.StreamListener)")
    public void publishEvents(JoinPoint joinPoint) {

        logger.info("Trigger domain event publish process.");
        taskExecutor.execute(() -> publisher.publish());
    }
}
