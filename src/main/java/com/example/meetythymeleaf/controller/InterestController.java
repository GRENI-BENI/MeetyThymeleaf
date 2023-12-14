package com.example.meetythymeleaf.controller;


import com.example.meetythymeleaf.model.Interest;
import com.example.meetythymeleaf.service.InterestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/interests")
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public String showAllInterests(Model model) {
        List<Interest> interests = interestService.findAll();
        model.addAttribute("interests", interests);
        return "interests/list";
    }

    @GetMapping("/new")
    public String showInterestForm(Model model) {
        model.addAttribute("interest", new Interest());
        return "interests/new";
    }

    @PostMapping("/new")
    public String saveInterest(@ModelAttribute Interest interest) {
        interestService.saveInterest(interest);
        return "redirect:/interests";
    }

    @GetMapping("/{id}/edit")
    public String editInterest(@PathVariable Long id, Model model) {
        Interest interest = interestService.getInterestById(id);
        model.addAttribute("interest", interest);
        return "interests/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateInterest(@PathVariable Long id, @ModelAttribute Interest updatedInterest) {
        interestService.updateInterest(id, updatedInterest);
        return "redirect:/interests";
    }

    @GetMapping("/{id}/delete")
    public String deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
        return "redirect:/interests";
    }

}
