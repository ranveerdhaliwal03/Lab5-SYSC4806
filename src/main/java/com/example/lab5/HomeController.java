package com.example.lab5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final AddressBookRepository bookRepo;

    public HomeController(AddressBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    // Example: http://localhost:8080/home?id=1
    @GetMapping("/home")
    public String addressBookView(@RequestParam(value = "id", required = false) Long id, Model model) {
        AddressBook book;
        if  (id != null) {
            book = bookRepo.findById(id).orElse(null);
        } else {
            book = new AddressBook();
            bookRepo.save(book);
        }

        model.addAttribute("book", book);
        model.addAttribute("buddies", book.getBuddies());
        model.addAttribute("buddy", new BuddyInfo());


        return "home"; // resolves to resources/templates/home.html
    }

    @PostMapping("/home")
    public String addBuddy(@RequestParam("bookId") Long bookId, @ModelAttribute BuddyInfo buddy) {
        AddressBook book = bookRepo.findById(bookId).orElse(null);
        if (book != null) {
            book.addBuddy(buddy);
            bookRepo.save(book);
        }
        System.out.println(bookRepo);
        return "redirect:/addressbook?id=" + bookId;
    }


    @GetMapping("/addressbook")
    public String showAddressBook(@RequestParam Long id, Model model) {
        AddressBook book = bookRepo.findById(id).orElse(null);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("buddies", book.getBuddies());
        }
        return "addressbook";
    }

}
