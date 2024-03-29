package com.casestudy.myretail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This is the configuration class for MyRetail, which will load all the properties
 */
@Configuration
@ConfigurationProperties("myretail.product")
public class MyRetailConfig {
    private String url;
    private int timeout;

    public String getUrl(long productId) {
        return String.format(url, productId);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
