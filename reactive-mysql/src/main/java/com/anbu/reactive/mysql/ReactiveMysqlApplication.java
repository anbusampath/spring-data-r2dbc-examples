package com.anbu.reactive.mysql;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class ReactiveMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMysqlApplication.class, args);
	}
	
	@Bean
	ApplicationRunner run(OrderRepository repository) {
		return args -> repository.findAll().subscribe(log::info);
	}

}

@Table("orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
class Order {

	@Id
	private Long id;
	private String name;
}

interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}