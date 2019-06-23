package kr.pe.yrseo.api.alpr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import kr.pe.yrseo.api.alpr.property.AlprProperties;
import kr.pe.yrseo.api.alpr.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class, AlprProperties.class
})
public class AlprApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlprApiApplication.class, args);
	}

}
