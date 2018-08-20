package com.wintelia.projectCommon;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientPool implements JedisClient {

    private JedisPool jedisPool;

    public JedisClientPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 设置新键值
     * @param key
     * @param value
     * @return
     */
    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    /**
     * 读取Key的值
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        Jedis jedis=jedisPool.getResource();
        String result=jedis.get(key);
        jedis.close();
        return result;
    }

    /**
     * 判断Key是否存在
     * @param key
     * @return
     */
    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    /**
     * 设置Key的过期时间
     * @param key
     * @param seconds
     * @return
     */
    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    /**
     * 读取Key的剩余时间
     * @param key
     * @return
     */
    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    /**
     * 自增键
     * @param key
     * @return
     */
    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    /**
     * 设置HashMap的键值
     * @param key
     * @param field
     * @param value
     * @return
     */
    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    /**
     * 读取HashMap的键
     * @param key
     * @param field
     * @return
     */
    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    /**
     * 删除jhashMap键
     * @param key
     * @param field
     * @return
     */
    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }
}
