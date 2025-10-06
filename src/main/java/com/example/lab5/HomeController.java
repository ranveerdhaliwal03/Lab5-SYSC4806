package com.example.lab5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final AddressBookRepository bookRepo;

    public HomeController(AddressBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    // Example: http://localhost:8080/home?id=1
    @GetMapping("/home")
    public String addressBookView(@RequestParam("id") Long id, Model model) {
        AddressBook book = bookRepo.findById(id).orElse(null);

        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("buddies", book.getBuddies());
        }

        return "home"; // resolves to resources/templates/home.html
    }
}
