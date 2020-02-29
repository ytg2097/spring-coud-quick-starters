package io.ac.starter.idempotency;

import io.ac.starter.autoconfigure.IdempotencyProperties;
import io.ac.starter.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class OpTokenRepository {

    private static final String KEY_PREFFIX = "optoken:";

    @Autowired
    private RedisClient redisClient;

    private IdempotencyProperties idempotencyProperties;

    public OpTokenRepository(IdempotencyProperties idempotencyProperties) {
        this.idempotencyProperties = idempotencyProperties;
    }

    /**
     * 存储 操作Token 并设置超时时间（毫秒）
     */
    public void store(String opToken) {
        redisClient.setWithTimeout(constructKey(opToken), opToken, idempotencyProperties.getTimeoutOfOpToken());
    }

    /**
     * 检查 操作Token 是否存在
     */
    public boolean exsits(String opToken) {
        return redisClient.exists(constructKey(opToken));
    }

    private String constructKey(String opToken) {
        return KEY_PREFFIX + idempotencyProperties.getAppName() + ":" + opToken;
    }
}
