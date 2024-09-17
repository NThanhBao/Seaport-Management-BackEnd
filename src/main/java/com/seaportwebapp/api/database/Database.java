package com.seaportwebapp.api.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.seaportwebapp.api.models.Ships;
import com.seaportwebapp.api.repositories.GoMapRepository;
import com.seaportwebapp.api.repositories.ShipsRepository;
import com.seaportwebapp.api.repositories.UserRepository;

@Configuration
public class Database {
	private static final Logger logger = LoggerFactory.getLogger(Database.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, ShipsRepository shipsRepository,
			GoMapRepository goMapRepository) {
		return args -> {
		};
	}

}
