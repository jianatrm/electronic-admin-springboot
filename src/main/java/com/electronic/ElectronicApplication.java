package com.electronic;

import com.electronic.properties.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = "com.electronic.dao")
@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties({
        FileProperties.class
})
public class ElectronicApplication  {

    /**
     * Configure the application. Normally all you would need to do is to add sources
     * (e.g. config classes) because other settings have sensible defaults. You might
     * choose (for instance) to add default command line arguments, or set an active
     * Spring profile.
     *
     * @param builder a builder for the application context
     * @return the application builder
     * @see SpringApplicationBuilder
     */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(ElectronicApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(ElectronicApplication.class, args);
    }

}
