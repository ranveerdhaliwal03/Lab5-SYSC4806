package com.example.lab5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab5Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab5Application.class, args);
    }

    @Bean
    public AddressBook addressBook() {
        return new AddressBook();
    }

    @Bean
    CommandLineRunner init(BuddyInfoRepository buddyRepo, AddressBookRepository bookRepo) {
        return args -> {
            BuddyInfo alice = new BuddyInfo("Alice", "123");
            BuddyInfo bob = new BuddyInfo("Bob", "456");

            AddressBook book = new AddressBook();
            book.getBuddies().add(alice);
            book.getBuddies().add(bob);

            bookRepo.save(book);

            System.out.println("=== All AddressBooks ===");
            bookRepo.findAll().forEach(System.out::println);

            System.out.println("=== All Buddies ===");
            buddyRepo.findAll().forEach(System.out::println);
        };
    }
}
