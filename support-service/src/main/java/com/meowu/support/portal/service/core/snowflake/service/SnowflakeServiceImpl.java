package com.meowu.support.portal.service.core.snowflake.service;

import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import com.meowu.support.portal.service.core.snowflake.manager.SnowflakeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Transactional(readOnly = true)
@Service
public class SnowflakeServiceImpl implements SnowflakeService{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private SnowflakeManager snowflakeManager;

    @Transactional
    @Override
    public Snowflake get(String application, String ip, Integer port){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            return snowflakeManager.get(jedis, application, ip, port);
        }
    }
}
