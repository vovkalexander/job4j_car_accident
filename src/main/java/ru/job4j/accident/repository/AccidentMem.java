package ru.job4j.accident.repository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
  private final HashMap<Integer, Accident> accidents = new HashMap<>();
  private AtomicInteger key = new AtomicInteger(3);

  public AccidentMem() {
    accidents.put(1, new Accident(1, "Pupkin", "was drunk", "Russian"));
    accidents.put(2, new Accident(2, "Rossi", "disappeared", "Italy"));
    accidents.put(3, new Accident(3, "Perez", "driving on the wrong side", "Mexico"));
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
}
