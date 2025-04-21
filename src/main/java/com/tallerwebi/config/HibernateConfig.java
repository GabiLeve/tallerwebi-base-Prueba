package com.tallerwebi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {


    /*con @Bean se crea un objeto concreto de tipo DataSource (en este caso, una instancia de DriverManagerDataSource)*/
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:db_");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    /* 1.Crea y configura un SessionFactory, que es el corazón de Hibernate: el objeto que se encarga de generar sesiones para interactuar con la base de datos.

    2. Usa como base el DataSource (definido arriba), es decir, le dice a Hibernate dónde está la base de datos y cómo conectarse.

    3.También se le dice que escanee las clases del paquete com.tallerwebi.dominio, donde están tus entidades.*/
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.tallerwebi.dominio");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /*Crea un HibernateTransactionManager, que es quien se encarga de manejar las transacciones (begin, commit, rollback) cuando accedés a la base de datos con Hibernate.
    Usa el SessionFactory para saber cómo manejar esas transacciones.*/
    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory(dataSource()).getObject());
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        return properties;
    }
}
