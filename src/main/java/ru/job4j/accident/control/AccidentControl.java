package ru.job4j.accident.control;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class AccidentControl {
    private final AccidentHibernate template;

    public AccidentControl(AccidentHibernate template) {
        this.template = template;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setRules(template.installRules(req.getParameterValues("rIds")));
        if (accident.getId() == 0) {
            template.addAccident(accident);
        }
        template.update(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", template.findById(id));
        return "accident/edit";
    }

    @ModelAttribute("types")
    public Collection<AccidentType> types() {
        return template.findAllTypes();
    }

    @ModelAttribute("rules")
    public Collection<Rule> rules() {
        return template.findAllRules();
    }
}
