package io.ac.starter.event.publisher;

import io.ac.starter.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Component
public class KafkaDomainEventBackupPublishScheduler {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private DomainEventPublisher publisher;

    public KafkaDomainEventBackupPublishScheduler(DomainEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 120000)
    public void run() {
        publisher.publish();
    }

}