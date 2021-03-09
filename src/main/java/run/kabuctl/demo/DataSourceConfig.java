package run.kabuctl.demo;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() throws Exception {
        GetMysqlCreds getMysqlCreds = new GetMysqlCreds();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(getMysqlCreds.getCreds()[0]);
        dataSourceBuilder.password(getMysqlCreds.getCreds()[1]);
        dataSourceBuilder.url("jdbc:mysql://35.221.93.48:3306/handson?useSSL=false");
        return dataSourceBuilder.build();
    }
}
