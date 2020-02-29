package io.ac.starter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@JsonTypeInfo(use = NAME, include = EXISTING_PROPERTY, property = "_type", visible = true)
public abstract class DomainEvent {
    private final String _id;
    private final String _type;
    private final Date _createdAt;

    @JsonCreator
    protected DomainEvent(@JsonProperty("_id") String _id,
                          @JsonProperty("_type") String _type,
                          @JsonProperty("_createdAt") Date _createdAt) {
        this._id = _id;
        this._type = _type;
        this._createdAt = _createdAt;
    }

    protected DomainEvent(String _type) {
        this._id = gen32();
        this._type = _type;
        this._createdAt = new Date();
    }

    public String toTopic(){

        return _type;
    }

    public String get_id() {
        return _id;
    }

    public String get_type() {
        return _type;
    }

    public Date get_createdAt() {
        return _createdAt;
    }

    @Override
    public String toString() {
        return "DomainEvent{" + "_id='" + _id + '\'' + ", _type=" + _type + '}';
    }
}
