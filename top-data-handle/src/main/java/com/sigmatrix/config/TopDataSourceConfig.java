package com.sigmatrix.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sigmatrix.mapper.top", sqlSessionTemplateRef  = "topSqlSessionTemplate")
public class TopDataSourceConfig {

    @Bean(name = "topDataSource")
    @ConfigurationProperties(prefix = "top.spring.datasource")
    @Primary
    public DataSource topDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "topSqlSessionFactory")
    @Primary
    public SqlSessionFactory topSqlSessionFactory(@Qualifier("topDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/top/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "topTransactionManager")
    @Primary
    public DataSourceTransactionManager topTransactionManager(@Qualifier("topDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "topSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate topSqlSessionTemplate(@Qualifier("topSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
