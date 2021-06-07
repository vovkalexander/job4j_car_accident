package ru.job4j.accident.repository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
  private final HashMap<Integer, Accident> accidents = new HashMap<>();
  private AtomicInteger key = new AtomicInteger(3);
  private final HashMap<Integer, AccidentType> types = new HashMap<>();

  public AccidentMem() {
    types.put(1, AccidentType.of(1, "Две машины"));
    types.put(2, AccidentType.of(2, "Машина и человек"));
    types.put(3, AccidentType.of(3, "Машина и велосипед"));
    accidents.put(1, new Accident(1, "Pupkin", "was drunk", "Russian", types.get(1)));
    accidents.put(2, new Accident(2, "Rossi", "disappeared", "Italy", types.get(2)));
    accidents.put(3, new Accident(3, "Perez", "driving on the wrong side", "Mexico", types.get(3)));

  }

  public Collection<Accident> findAll() {
    return this.accidents.values();
  }

  public void addAccident(Accident accident) {
    if (accident.getId() == 0) {
      accident.setId(key.incrementAndGet());
    }
    accidents.put(accident.getId(), accident);
  }

  public Accident findById(int id) {
    return this.accidents.get(id);
  }

  public Collection<AccidentType> findAllTypes() {
    return  types.values();
  }
}
