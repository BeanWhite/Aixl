package com.aixl.m.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启webSocket支持
 */
@Configuration
public class WebSocketConfig {

    /**
     *
     * @return websocket启动
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
        return new ServerEndpointExporter();
    }
}
