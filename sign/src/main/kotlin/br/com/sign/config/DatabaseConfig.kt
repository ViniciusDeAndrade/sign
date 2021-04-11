package br.com.sign.config

import br.com.sign.constants.*
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig {

    @Bean
    fun getDataSource(): DataSource = DataSourceBuilder.create()
        .driverClassName(DB_DRIVER)
                .url(DB_URL + DB_DATABASE_NAME)
                .username(DB_USER)
                .password(DB_PASSWORD)
                .build()

}