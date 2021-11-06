package com.meowu.support.portal.service.core.snowflake.consts;

import java.util.concurrent.TimeUnit;

public interface SnowflakeCache{

    String SNOWFLAKE_SAVE_LOCK_NAME = "snowflake:save:lock:";
    long   SNOWFLAKE_SAVE_EXPIRE    = TimeUnit.SECONDS.toSeconds(5);
}
