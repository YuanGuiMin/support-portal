package com.meowu.support.portal.service.core.snowflake.dao;

import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import com.meowu.commons.mybatis.mysql.criteria.Restrictions;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.commons.utils.utils.IDUtils;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import com.meowu.support.portal.service.core.snowflake.dao.mapper.SnowflakeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class SnowflakeDao{

    @Autowired
    private SnowflakeMapper snowflakeMapper;

    public void save(Snowflake snowflake){
        AssertUtils.notNull(snowflake, "snowflake must not be null");
        AssertUtils.hasText(snowflake.getIp(), "application ip must not be null");
        AssertUtils.notNull(snowflake.getPort(), "application port must not be null");
        AssertUtils.hasText(snowflake.getApplication(), "application name must not be null");
        AssertUtils.notNull(snowflake.getCenterId(), "snowflake center id must not be null");
        AssertUtils.notNull(snowflake.getWorkerId(), "snowflake worker id must not be null");

        snowflake.setId(IDUtils.uuid());
        snowflake.setCreateTime(new Date());
        snowflake.setUpdateTime(snowflake.getCreateTime());

        snowflakeMapper.save(snowflake);
    }

    public Snowflake get(String application, String ip, Integer port){
        AssertUtils.hasText(application, "application name must not be null");
        AssertUtils.hasText(ip, "application ip must not be null");
        AssertUtils.notNull(port, "application port must not be null");

        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("application", application));
        criteria.add(Restrictions.eq("ip", ip));
        criteria.add(Restrictions.eq("port", port));

        return snowflakeMapper.get(criteria);
    }

    public long countByApplication(String application){
        AssertUtils.hasText(application, "application name must not be null");

        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("application", application));

        Long total = snowflakeMapper.count(criteria);

        return (total == null ? 0 : total);
    }
}
