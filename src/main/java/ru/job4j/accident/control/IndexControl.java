package ru.job4j.accident.control;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class IndexControl {
    private AccidentMem mem;

    public IndexControl(AccidentMem mem) {
        this.mem = mem;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("lists", mem.findAll());
        return "index";
    }
}
