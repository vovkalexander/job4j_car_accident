package ru.job4j.accident.control;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.accident.repository.AccidentRepository;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    private final AccidentRepository template;

    public IndexControl(AccidentRepository template) {
        this.template = template;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> rsl = new ArrayList<>();
        template.findAll().forEach(rsl::add);
        model.addAttribute("lists", rsl);
        return "index";
    }
}
