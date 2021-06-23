package ru.job4j.accident.repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Repository
public class AccidentHibernate implements DaoAccident {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void addAccident(Accident accident) {
        this.tx(session -> session.save(accident));
    }

    @Override
    public Collection<Accident> findAll() {
        return this.tx(session -> session.
                    createQuery("select distinct a from Accident a left join fetch a.rules order by a.id",
                            Accident.class).list());
    }

    public   Accident findById(int id) {
        return (Accident) this.tx(session -> session.
                createQuery("select  a from Accident a left join fetch a.rules where a.id = :id").
                setParameter("id", id).getSingleResult());
    }

    public void update(Accident accident) {
        this.tx(session -> {
            session.update(accident);
            return null;
        });
    }

    public AccidentType findTypeById(int id) {
         return this.tx(session -> session.byId(AccidentType.class).load(id));
    }

    public Rule findRuleById(int id) {
        return this.tx(session -> session.byId(Rule.class).load(id));
    }

    public Collection<AccidentType> findAllTypes() {
        return this.tx(session -> session.createQuery("from AccidentType", AccidentType.class).
                list());
    }

    public Collection<Rule> findAllRules() {
        return this.tx(session -> session.createQuery("from Rule", Rule.class).
                list());
    }

    @Override
    public Set<Rule> installRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            rules.add(this.findRuleById(Integer.parseInt(id)));
        }
        return rules;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
