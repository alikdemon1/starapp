package kz.alisher.greetgo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by alisher
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
@MapperScan("kz.alisher.greetgo.mappers")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}