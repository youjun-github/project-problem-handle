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
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import tk.mybatis.spring.annotation.MapperScan;
//
//@Configuration
//@MapperScan(basePackages = "com.sigmatrix.mapper.erp", sqlSessionTemplateRef  = "erpSqlSessionTemplate")
//public class ErpDataSourceConfig {
//
//    @Bean(name = "erpDataSource")
//    @ConfigurationProperties(prefix = "erp.spring.datasource")
//    @Primary
//    public DataSource erpDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "erpSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory erpSqlSessionFactory(@Qualifier("erpDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/erp/*.xml"));
//        return bean.getObject();
//    }
//
//    @Bean(name = "erpTransactionManager")
//    @Primary
//    public DataSourceTransactionManager erpTransactionManager(@Qualifier("erpDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "erpSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate erpSqlSessionTemplate(@Qualifier("erpSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}
