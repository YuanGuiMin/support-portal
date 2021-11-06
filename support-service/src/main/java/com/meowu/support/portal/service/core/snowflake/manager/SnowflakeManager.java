package com.meowu.support.portal.service.core.snowflake.manager;

import com.meowu.commons.redis.sharded.helper.ShardedJedisHelper;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.commons.utils.utils.ThreadUtils;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import com.meowu.support.portal.service.commons.security.stereotype.Manager;
import com.meowu.support.portal.service.core.snowflake.consts.SnowflakeCache;
import com.meowu.support.portal.service.core.snowflake.dao.SnowflakeDao;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

import java.util.concurrent.TimeUnit;

@Manager
public class SnowflakeManager{

    @Autowired
    private SnowflakeDao snowflakeDao;

    public Snowflake get(ShardedJedis jedis, String application, String ip, Integer port){
        AssertUtils.notNull(jedis, "redis client must not be null");

        // find snowflake
        Snowflake snowflake = snowflakeDao.get(application, ip, port);

        // create snowflake
        if(snowflake == null){
            // lock name
            String lock = SnowflakeCache.SNOWFLAKE_SAVE_LOCK_NAME + application;

            int counter = 0;
            int maxTry  = 3;

            while(counter < maxTry){
                if(ShardedJedisHelper.setIfNotExist(jedis, lock, "1", SnowflakeCache.SNOWFLAKE_SAVE_EXPIRE)){
                    int total    = ((Long) snowflakeDao.countByApplication(application)).intValue();
                    int centerId = total / 32;
                    int workerId = total % 32;

                    // create snowflake
                    snowflake = new Snowflake();
                    snowflake.setApplication(application);
                    snowflake.setIp(ip);
                    snowflake.setPort(port);
                    snowflake.setCenterId(centerId);
                    snowflake.setWorkerId(workerId);
                    snowflakeDao.save(snowflake);

                    // clear cache
                    ShardedJedisHelper.delete(jedis, lock);
                    break;

                }else{
                    // sleep 1 second and try again
                    ThreadUtils.sleep(TimeUnit.SECONDS, 1);
                    counter += 1;
                }
            }
        }

        return snowflake;
    }
}
