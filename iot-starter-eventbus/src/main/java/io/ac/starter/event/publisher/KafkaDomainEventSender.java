package io.ac.starter.event.publisher;

import io.ac.starter.DomainEvent;
import io.ac.starter.util.DefaultObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@Component
public class KafkaDomainEventSender {

    @Autowired
    private BinderAwareChannelResolver resolver;

    @Autowired
    private DefaultObjectMapper objectMapper;

    public void send(DomainEvent event) {

        resolver.resolveDestination(event.get_type()).send(new GenericMessage(objectMapper.writeValueAsString(event)) );
    }
}
