package ru.orders.ordersapi.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;
import ru.orders.base.config.BaseConfig;

@SpringBootConfiguration
@Import({BaseConfig.class, HibernateConfig.class})
public class AppConfig {
}
