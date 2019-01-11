//package com.sigmatrix.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import tk.mybatis.spring.annotation.MapperScan;
//
//@Configuration
//@MapperScan(basePackages = "com.sigmatrix.mapper.middleware", sqlSessionTemplateRef  = "middlewareSqlSessionTemplate")
//public class MiddlewareDataSourceConfig {
//
//    @Bean(name = "middlewareDataSource")
//    @ConfigurationProperties(prefix = "middleware.spring.datasource")
//    public DataSource middlewareDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "middlewareSqlSessionFactory")
//    public SqlSessionFactory middlewareSqlSessionFactory(@Qualifier("middlewareDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/middleware/*.xml"));
//        return bean.getObject();
//    }
//
//    @Bean(name = "middlewareTransactionManager")
//    public DataSourceTransactionManager middlewareTransactionManager(@Qualifier("middlewareDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "middlewareSqlSessionTemplate")
//    public SqlSessionTemplate middlewareSqlSessionTemplate(@Qualifier("middlewareSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}
