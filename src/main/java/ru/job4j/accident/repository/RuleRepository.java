package ru.job4j.accident.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    @Query("select r  from Rule r where r.id = :id")
    Rule findById(@Param("id") int id);
}
