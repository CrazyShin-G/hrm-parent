package com.erocraft.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;


/**
 * @author 14179
 */

public enum RedisUtils {
    INSTANCE;
    static JedisPool jedisPool = null;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(1);
        config.setMaxTotal(11);
        config.setMaxWaitMillis(10 * 1000L);
        config.setTestOnBorrow(true);
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(RedisUtils.class.getClassLoader().getResourceAsStream("redis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = properties.getProperty("redis.host");
        String port = properties.getProperty("redis.port");
        String password = properties.getProperty("redis.password");
        String timeout = properties.getProperty("redis.timeout");
        System.out.println(host);
        System.out.println(port);
        System.out.println(password);
        System.out.println(timeout);
        jedisPool = new JedisPool(config, host, Integer.valueOf(port),Integer.valueOf(timeout), password);
    }

    public Jedis getSource() {
        return jedisPool.getResource();
    }

    public void closeSource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }
    /**
     * 设置值
     * @param key
     * @param
     */
    public void del(String key) {
        Jedis jedis = getSource();
        jedis.del(key);
        closeSource(jedis);
    }
    /**
     * 设置值
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    /**
     * 设置
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    /**
     *
     * @param key
     * @return
     */
    public byte[]  get(byte[] key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;

    }

    /**
     * 设置值
     * @param key
     */
    public String get(String key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }

        return null;

    }
}