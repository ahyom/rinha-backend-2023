package com.soave.rinhabackend.configurations

import com.zaxxer.hikari.HikariDataSource
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

private val logger = KotlinLogging.logger {}

@Configuration
@Profile("!local")
class DatabaseConfig {

    @Bean
    fun dataSource(): DataSource {
        return HikariDataSource(dataSourceConfig)
    }

    private val dataSourceConfig: HikariDataSource
        get() {
            logger.info("Instantiating new psql connection")
            val config = HikariDataSource()
            config.jdbcUrl = "jdbc:postgresql://db:5432/rinhabackend"
            config.username = "rinha"
            config.password = "123456"
            config.maximumPoolSize = 10
            config.connectionTimeout = 30000
            return config
        }
}
