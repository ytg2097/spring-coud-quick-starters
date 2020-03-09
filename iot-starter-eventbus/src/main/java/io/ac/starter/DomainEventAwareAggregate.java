package io.ac.starter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
public abstract class DomainEventAwareAggregate {

    private final List<DomainEvent> events = new ArrayList<>();

    protected void raiseEvent(DomainEvent event){
        this.events.add(event);
    }

    void clearEvents(){
        this.events.clear();
    }

    List<DomainEvent> getEvents(){

        return unmodifiableList(events);
    }
}
