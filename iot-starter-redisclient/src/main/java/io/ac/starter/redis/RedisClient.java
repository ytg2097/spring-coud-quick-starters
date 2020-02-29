package io.ac.starter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class RedisClient {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public void deleteKeys(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setWithTimeout(String key, String value, long timeoutMs) {
        redisTemplate.opsForValue().set(key, value, timeoutMs, TimeUnit.MILLISECONDS);
    }

    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<String> get(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public void hset(String key, String column, Object value) {
        redisTemplate.opsForHash().put(key, column, value);
    }

    public Object hget(String key, String column) {
        return redisTemplate.opsForHash().get(key, column);
    }

    public void hmset(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public boolean zadd(String key, String value, long score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<String> zrangeByScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScore(key, start, end);
    }

    public void zremRangeByScore(String key, long start, long end) {
        redisTemplate.opsForZSet().removeRangeByScore(key, start, end);
    }

    public void zremByMembers(String key, String... members) {
        redisTemplate.opsForZSet().remove(key, members);
    }

    public void expire(String key, long timeoutMs) {
        redisTemplate.expire(key, timeoutMs, TimeUnit.MILLISECONDS);
    }

    public Long increment(String key, long count) {
        return redisTemplate.opsForValue().increment(key, count);
    }

    public void rename(String oldKey,String newKey){
        redisTemplate.boundSetOps(oldKey).rename(newKey);
    }
}
