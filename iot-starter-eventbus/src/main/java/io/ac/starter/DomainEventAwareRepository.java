package io.ac.starter;

import io.ac.starter.event.publisher.DomainEventDao;
import io.ac.starter.util.BeanUtil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
public interface DomainEventAwareRepository<T extends DomainEventAwareAggregate,ID> extends JpaRepository<T,ID> {

    default void doSave(T aggregate){

        eventDao().insert(aggregate.getEvents());
        aggregate.clearEvents();
        save(aggregate);
    }

    default void doSave(List<T> aggregateList){

        List<DomainEvent> events = newArrayList();

        for (T t : aggregateList){
            events.addAll(t.getEvents());
        }

        eventDao().insert(events);

        for (T t : aggregateList){
            t.clearEvents();
        }

        saveAll(aggregateList);
    }

    default DomainEventDao eventDao(){

        return BeanUtil.getBean(DomainEventDao.class);
    }
}
