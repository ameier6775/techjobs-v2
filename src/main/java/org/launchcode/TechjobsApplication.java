package org.launchcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@SpringBootApplication
public class TechjobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechjobsApplication.class, args);
	}

	public static <T> Collector<T, ?, T> toSingleton() {
		return Collectors.collectingAndThen(
				Collectors.toList(),
				list -> {
					if (list.size() != 1) {
						throw new IllegalStateException();
					}
					return list.get(0);
				}
		);
	}
}
