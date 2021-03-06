package com.meowu.support.portal.service.core.snowflake.dao.mapper;

import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SnowflakeMapper{

    void save(Snowflake snowflake);

    Snowflake get(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
