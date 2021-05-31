package ru.job4j.accident.repository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem {
  private HashMap<Integer, Accident> accidents = new HashMap<>();

  public AccidentMem() {
    accidents.put(1, new Accident(1, "Pupkin", "was drunk", "Russian"));
    accidents.put(2, new Accident(2, "Rossi", "disappeared", "Italy"));
    accidents.put(3, new Accident(3, "Perez", "driving on the wrong side", "Mexico"));
  }

  public Collection<Accident> findAll() {
    return this.accidents.values();
  }
}