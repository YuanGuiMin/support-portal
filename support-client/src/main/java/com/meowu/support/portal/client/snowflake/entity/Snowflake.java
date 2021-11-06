package com.meowu.support.portal.client.snowflake.entity;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Entity;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Snowflake extends Entity implements Creatable, Updatable{

    private Integer centerId;
    private Integer workerId;
    private String  ip;
    private Integer port;
    private String  application;
    private Date    createTime;
    private Date    updateTime;
}
