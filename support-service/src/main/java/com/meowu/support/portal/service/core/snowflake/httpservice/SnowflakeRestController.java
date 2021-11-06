package com.meowu.support.portal.service.core.snowflake.httpservice;

import com.meowu.commons.utils.security.response.Response;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import com.meowu.support.portal.service.core.snowflake.service.SnowflakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/snowflake")
public class SnowflakeRestController{

    @Autowired
    private SnowflakeService snowflakeService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Snowflake> get(String application, String ip, Integer port){
        Snowflake snowflake = snowflakeService.get(application, ip, port);

        return new Response<Snowflake>("get snowflake successfully", snowflake);
    }
}
