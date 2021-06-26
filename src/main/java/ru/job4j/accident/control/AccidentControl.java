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
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepositoty;
import ru.job4j.accident.repository.RuleRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AccidentControl {
    private final AccidentRepository template;
    private final AccidentTypeRepositoty types;
    private final RuleRepository rules;

    public AccidentControl(AccidentRepository template, AccidentTypeRepositoty types,
                           RuleRepository rules) {
        this.template = template;
        this.types = types;
        this.rules = rules;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setRules(this.installRules(req.getParameterValues("rIds")));
        template.save(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", template.findById(id));
        return "accident/edit";
    }

    @ModelAttribute("types")
    public Collection<AccidentType> types() {
        return (Collection<AccidentType>) types.findAll();
    }

    @ModelAttribute("rules")
    public Collection<Rule> rules() {
        return (Collection<Rule>) rules.findAll();
    }

    public Set<Rule> installRules(String[] ids) {
        Set<Rule> rule = new HashSet<>();
        for (String id : ids) {
            System.out.println(id);
            rule.add(this.rules.findById(Integer.parseInt(id)));
        }
        return rule;
    }
}
