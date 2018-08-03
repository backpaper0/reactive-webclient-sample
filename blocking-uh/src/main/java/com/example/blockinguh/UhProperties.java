package com.example.blockinguh;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "example")
public class UhProperties {

    private String appleUrl = "http://localhost:8082";
    private String penUrl = "http://localhost:8083";

    public String getAppleUrl() {
        return appleUrl;
    }

    public void setAppleUrl(final String appleUrl) {
        this.appleUrl = appleUrl;
    }

    public String getPenUrl() {
        return penUrl;
    }

    public void setPenUrl(final String penUrl) {
        this.penUrl = penUrl;
    }
}
