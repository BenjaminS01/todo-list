package com.bs.todo;

import com.bs.todo.todo.application.service.TodoItemService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
    ApplicationRunner dataInitializer(TodoItemService service) {
		return args -> {
			service.create("Öl wechsel", LocalDate.of(2026, 6, 20));
			service.create("Rasen mähen", LocalDate.of(2026, 6, 25));
			service.create("LLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
							"tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam " +
							"et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata",
					LocalDate.of(2026, 7, 10)
			);
			service.create("Einkaufen", LocalDate.of(2026, 6, 15));
			service.create("10 Liegestütze", LocalDate.of(2026, 7, 1));
			service.create("LLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
					"tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam " +
					"et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata",
					LocalDate.of(2026, 6, 21)
			);
		};
	}
}
