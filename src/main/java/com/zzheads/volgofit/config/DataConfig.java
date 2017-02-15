package com.zzheads.volgofit.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
public class DataConfig {

    private class CustomDataSource extends BasicDataSource {
        public void init() {
            addConnectionProperty("useUnicode", "true");
            addConnectionProperty("characterEncoding", "UTF-8");
        }
    }

    @Bean
    public CustomDataSource dataSource() throws URISyntaxException {
//        Local dataSource creds
        String dbUrl = "jdbc:mysql://localhost/volgofit?useUnicode=yes&characterEncoding=UTF-8";
        String username = "root";
        String password = "xela1723014220";

//        Heroku deploing credentials
//        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + "?useUnicode=yes&characterEncoding=UTF-8";

        CustomDataSource customDataSource = new CustomDataSource();
        customDataSource.setUrl(dbUrl);
        customDataSource.setUsername(username);
        customDataSource.setPassword(password);

        return customDataSource;
    }
}
