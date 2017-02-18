package com.zzheads.volgofit.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DataConfig {
    private final Environment env;
    private static final boolean DEPLOY = false;

    @Autowired
    public DataConfig(Environment env) {
        this.env = env;
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

        if (DEPLOY) {
            URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
            dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + "?useUnicode=yes&characterEncoding=UTF-8";
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
        }

        CustomDataSource customDataSource = new CustomDataSource();
        customDataSource.setUrl(dbUrl);
        customDataSource.setUsername(username);
        customDataSource.setPassword(password);

        return customDataSource;
    }
}
