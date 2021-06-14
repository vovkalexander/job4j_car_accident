package ru.job4j.accident.repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import java.util.Collection;
import java.util.Set;

public interface DaoAccident {
    void addAccident(Accident accident);

    Collection<Accident> findAll();

    Accident findById(int id);

    void update(Accident accident);

    AccidentType findTypeById(int id);

    Rule findRuleById(int id);

    Collection<AccidentType> findAllTypes();

    Collection<Rule> findAllRules();

    Set<Rule> installRules(String[] ids);
}
