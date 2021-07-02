package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.User;

@SuppressWarnings("checkstyle:LineLength")
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select case when count(u) > 0 then true else false end from User u where u.username like %:username%")
    Boolean existsByName(@Param("username") String name);
}
