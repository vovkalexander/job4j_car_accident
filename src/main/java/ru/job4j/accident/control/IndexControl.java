package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = List.of("Vasiliy Pupkin", "Mario Rossi", "Juan Perez");
        model.addAttribute("lists", list);
        return "index";
    }
}
