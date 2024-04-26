package com.jpvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"com.jpvendas.gestaovendas.entidades"}) //Indica onde estão as entidades
@EnableJpaRepositories(basePackages = {"com.jpvendas.gestaovendas.repositorio"}) //indica onde esta o repositorio
@ComponentScan(basePackages = {"com.jpvendas.gestaovendas.servico", "com.jpvendas.gestaovendas.controlador", "com.jpvendas.gestaovendas.excecao"}) //Indica onde estão os bins, classes de serviço e controllers
@SpringBootApplication
public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
