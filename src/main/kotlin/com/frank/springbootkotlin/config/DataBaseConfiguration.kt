package com.frank.springbootkotlin.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

/**
 * configure and user multiple datasource
 */
@Configuration
class DataBaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.h2")
    fun h2DataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean
    fun h2DataSource(): DataSource = h2DataSourceProperties().initializeDataSourceBuilder().build()

    @Bean
    fun h2JdbcTemplate(@Qualifier("h2DataSource") dataSource: DataSource): JdbcTemplate = JdbcTemplate(dataSource)
}