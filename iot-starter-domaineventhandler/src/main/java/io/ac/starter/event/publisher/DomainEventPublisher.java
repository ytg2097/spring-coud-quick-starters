package io.ac.starter.event.publisher;

import io.ac.starter.DomainEvent;
import io.ac.starter.distributedlock.DistributedLockExecutor;
import io.ac.starter.logging.AutoNamingLoggerFactory;
import net.javacrumbs.shedlock.core.LockConfiguration;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@Component
public class DomainEventPublisher {

    private static final Logger logger = AutoNamingLoggerFactory.getLogger();
    private DomainEventDao eventDao;
    private DistributedLockExecutor lockExecutor;
    private KafkaDomainEventSender eventSender;

    public DomainEventPublisher(DomainEventDao eventDao, DistributedLockExecutor lockExecutor, KafkaDomainEventSender eventSender) {
        this.eventDao = eventDao;
        this.lockExecutor = lockExecutor;
        this.eventSender = eventSender;
    }

    public void publish() {
        Instant now = Instant.now();
        LockConfiguration configuration = new LockConfiguration("domain-event-publisher", now.plusSeconds(10));
        lockExecutor.execute(this::doPublish, configuration);
    }

    private Void doPublish() {
        List<DomainEvent> newestEvents = eventDao.toBePublishedEvents();
        newestEvents.forEach(event -> {
            try {
                eventDao.increasePublishTries(event.get_id());
                eventSender.send(event);
                eventDao.delete(event.get_id());
            } catch (Throwable t) {
                logger.error("Error while publish domain event {}:{}", event, t.getMessage());
            }
        });
        return null;
    }
}
