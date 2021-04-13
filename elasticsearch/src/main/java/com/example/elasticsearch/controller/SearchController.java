package com.example.elasticsearch.controller;

import com.example.elasticsearch.dto.BookDTO;
import com.example.elasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class SearchController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/search")
    public String searchPage(Model model) {
        List<BookDTO> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "search";
    }

    @GetMapping("/searchBook")
    public String searchBook(@RequestParam String query, Model model){
        List<BookDTO> books = bookService.searchBook(query);
        model.addAttribute("query", query);
        model.addAttribute("books", books);
        return "search";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        return "add";
    }

    @PostMapping("/save")
    public String saveBook(BookDTO bookDTO, RedirectAttributes redirectAttributes){
        bookService.saveBook(bookDTO);
        redirectAttributes.addFlashAttribute("success", "Book has been added successfully");
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updatePage(@RequestParam String bookId, Model model, RedirectAttributes redirectAttributes) {
        BookDTO bookDTO = bookService.findBookById(bookId);
        if(Objects.nonNull(bookDTO)){
            model.addAttribute("book", bookDTO);
            return "update";
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found");
            return "redirect:/";
        }
    }

    @PostMapping("/updateBook")
    public String updateBook(BookDTO bookDTO, RedirectAttributes redirectAttributes){
        bookService.updateBook(bookDTO);
        redirectAttributes.addFlashAttribute("success", "Book has been updated successfully");
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String clearPage(@RequestParam String bookId, Model model, RedirectAttributes redirectAttributes) {
        BookDTO bookDTO = bookService.findBookById(bookId);
        if(Objects.nonNull(bookDTO)){
            String message = "Are you sure you want to delete the book " + bookDTO.getName() + " ?";
            model.addAttribute("message", message);
            model.addAttribute("book", bookDTO);
            return "clear";
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found");
            return "redirect:/";
        }
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam String bookId, RedirectAttributes redirectAttributes){
        bookService.deleteBook(bookId);
        redirectAttributes.addFlashAttribute("success", "Book has been deleted successfully");
        return "redirect:/";
    }

}
