package com.database.migration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MigrationApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MigrationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MigrationApplication.class, args);

		LOGGER.warn("Example Warn Level");
	}
}
