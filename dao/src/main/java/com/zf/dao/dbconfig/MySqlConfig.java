package com.zf.dao.dbconfig;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * annotationClass = Repository.class默认扫描com.demo.dao.mysql包的Repository注解类来注入到Spring
 */
@Configuration
@MapperScan(basePackages = "com.zf.dao.mysql", annotationClass = Repository.class, sqlSessionTemplateRef = "mySqlSessionTemplate")
@EnableApolloConfig
public class MySqlConfig {

    @Bean(name = "mySqlDatSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory mySqlSessionFactory(@Qualifier("mySqlDatSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "mySqlTransactionManager")
    public DataSourceTransactionManager mySqlTransactionManager(@Qualifier("mySqlDatSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mySqlSessionTemplate")
    public SqlSessionTemplate mySqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory mySqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(mySqlSessionFactory);
    }

}