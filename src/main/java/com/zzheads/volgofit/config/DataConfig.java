package com.zzheads.volgofit.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.URI;
import java.net.URISyntaxException;

import static com.zzheads.volgofit.config.DataConfig.AppStatus.DEBUG;
import static com.zzheads.volgofit.config.DataConfig.AppStatus.DEPLOY;

@Configuration
public class DataConfig {
    private final Environment env;
    static String HOST;
    private AppStatus appStatus = DEBUG;

    enum AppStatus {
        DEBUG,
        DEPLOY,
        PRODUCTION
    }


    @Autowired
    public DataConfig(Environment env) {
        this.env = env;
        HOST = "https://localhost:8080";
    }

    private class CustomDataSource extends BasicDataSource {
        public void init() {
            addConnectionProperty("useUnicode", "true");
            addConnectionProperty("characterEncoding", "UTF-8");
        }
    }

    @SuppressWarnings("UnusedAssignment")
    @Bean
    public CustomDataSource dataSource() throws URISyntaxException {
        String dbUrl = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        if (appStatus == DEPLOY) {
            URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
            dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + "?autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            HOST = dbUri.getHost();
        }

        CustomDataSource customDataSource = new CustomDataSource();
        customDataSource.setUrl(dbUrl);
        customDataSource.setUsername(username);
        customDataSource.setPassword(password);

        return customDataSource;
    }
}
