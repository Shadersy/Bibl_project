package ru.gvozdilin.bibl.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.gvozdilin.bibl.dao.BooksDao;
import ru.gvozdilin.bibl.dao.BooksDaoImpl;
import ru.gvozdilin.bibl.service.BooksService;
import ru.gvozdilin.bibl.service.BooksServiceImpl;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate(){

        return  new JdbcTemplate(getDataSource());
    }



    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/bibliary?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");


        return dataSource;
    }

    @Bean
    public BooksDao getBooksDao() {return new BooksDaoImpl(getJdbcTemplate());
    }

   @Bean
   public BooksService getBooksService(){
       return new BooksServiceImpl();
   }





}
