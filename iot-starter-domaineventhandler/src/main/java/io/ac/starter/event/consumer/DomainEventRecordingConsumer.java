package io.ac.starter.event.consumer;

import io.ac.starter.DomainEvent;
import io.ac.starter.logging.AutoNamingLoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@Component
public class DomainEventRecordingConsumer {

    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private DomainEventRecordDao domainEventRecordDao;

    public DomainEventRecordingConsumer(DomainEventRecordDao domainEventRecordDao) {
        this.domainEventRecordDao = domainEventRecordDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object recordAndConsume(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Optional<Object> optionalEvent = Arrays.stream(args)
                .filter(o -> o instanceof DomainEvent)
                .findFirst();

        if (optionalEvent.isPresent()) {
            DomainEvent event = (DomainEvent) optionalEvent.get();
            try {
                domainEventRecordDao.recordEvent(event);
            } catch (DuplicateKeyException dke) {
                logger.warn("Duplicated {} skipped.", event);
                return null;
            }

            return joinPoint.proceed();
        }

        return joinPoint.proceed();
    }
}
