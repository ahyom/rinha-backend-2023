package com.soave.rinhabackend.configurations

import com.zaxxer.hikari.HikariDataSource
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

private val logger = KotlinLogging.logger {}

@Configuration
class DatabaseConfig {

    @Bean
    @Profile("prod")
    fun dataSource(): DataSource {
        return HikariDataSource(dataSourceConfigProd)
    }

    @Bean
    @Profile("default")
    fun dataSourceDefault(): DataSource {
        return HikariDataSource(dataSourceConfigH2)
    }

    // TODO -> improve this class

    private val dataSourceConfigH2: HikariDataSource
        get() {
            logger.info("Instantiating new h2 connection")
            val config = HikariDataSource()
            config.jdbcUrl = "jdbc:h2:mem:testdb"
            config.username = "sa"
            config.password = ""
            config.maximumPoolSize = 10
            config.connectionTimeout = 30000
            return config
        }

    private val dataSourceConfigProd: HikariDataSource
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
