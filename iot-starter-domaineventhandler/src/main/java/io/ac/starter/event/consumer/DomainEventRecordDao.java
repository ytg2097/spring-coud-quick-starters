package io.ac.starter.event.consumer;

import io.ac.starter.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import static com.google.common.collect.ImmutableMap.of;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@Component
public class DomainEventRecordDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void recordEvent(DomainEvent event) {
        String sql = "INSERT INTO DOMAIN_EVENT_RECEIVE_RECORD (EVENT_ID) VALUES (:eventId);";
        jdbcTemplate.update(sql, of("eventId", event.get_id()));
    }
}
