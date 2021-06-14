package ru.job4j.accident.repository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements DaoAccident {
  private final HashMap<Integer, Accident> accidents = new HashMap<>();
  private AtomicInteger key = new AtomicInteger(3);
  private final HashMap<Integer, AccidentType> types = new HashMap<>();
  private final HashMap<Integer, Rule> rules = new HashMap<>();

  public AccidentMem() {
    types.put(1, AccidentType.of(1, "Две машины"));
    types.put(2, AccidentType.of(2, "Машина и человек"));
    types.put(3, AccidentType.of(3, "Машина и велосипед"));
    rules.put(1, Rule.of(1, "Статья. 1"));
    rules.put(2, Rule.of(2, "Статья. 2"));
    rules.put(3, Rule.of(3, "Статья. 3"));
    Set<Rule> claims = new HashSet<>(rules.values());
    accidents.put(1, new Accident(1, "Pupkin", "was drunk", "Russian", types.get(1), claims));
    accidents.put(2, new Accident(2, "Rossi", "disappeared", "Italy", types.get(2), claims));
    accidents.put(3, new Accident(3, "Perez", "driving on the wrong side", "Mexico", types.get(3), claims));

  }

  @Override
  public Collection<Accident> findAll() {
    return this.accidents.values();
  }

  @Override
  public void addAccident(Accident accident) {
    if (accident.getId() == 0) {
      accident.setId(key.incrementAndGet());
    }
    accidents.put(accident.getId(), accident);
  }

  @Override
  public Accident findById(int id) {
    return this.accidents.get(id);
  }

  @Override
  public AccidentType findTypeById(int id) {
    return null;
  }

  @Override
  public Rule findRuleById(int id) {
    return null;
  }

  @Override
  public Collection<AccidentType> findAllTypes() {
    return  types.values();
  }

  @Override
  public Collection<Rule> findAllRules() {
    return rules.values();
  }

  @Override
  public void update(Accident accident) {

  }

  @Override
  public Set<Rule> installRules(String[] ids) {
    Set<Rule> rules = new HashSet<>();
    for (String id : ids) {
      rules.add(this.rules.get(Integer.parseInt(id)));
    }
    return rules;
  }
}
