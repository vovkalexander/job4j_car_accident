package ru.job4j.accident.control;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class IndexControl {
    private final AccidentJdbcTemplate template;

    public IndexControl(AccidentJdbcTemplate template) {
        this.template = template;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("lists", template.findAll());
        return "index";
    }
}
