package ru.job4j.accident.repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import java.sql.Types;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate implements DaoAccident {
    private static final String SQL_INSERT_ACCIDENT =
            "INSERT INTO accident (name, text, address, type_id) VALUES (?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL =
            "select accident.id, accident.name, text, address, type_id, ARRAY_AGG(rule.id) from accident" +
                    " inner join accident_rule" +
                    " on accident.id = accident_rule.accident_id" +
                    " inner join rule" +
                    " on accident_rule.rule_id = rule.id" +
                    " group by accident.id";

    private static final String SQL_SELECT_BY_ID =
            SQL_SELECT_ALL + " WHERE id = ?";
    private final JdbcTemplate jdbc;

    public  AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void addAccident(Accident accident) {
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                SQL_INSERT_ACCIDENT, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.INTEGER);
        factory.setGeneratedKeysColumnNames("id");
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(
                factory.newPreparedStatementCreator(new Object[]
                        {accident.getName(), accident.getText(), accident.getAddress(),
                                accident.getType().getId()}),
                kh);
        int id = kh.getKey().intValue();
        accident.setId(id);
        this.fillMediumTable(accident);
    }

    private void fillMediumTable(Accident accident) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accident_rule values(?,?)",
                    accident.getId(),
                    rule.getId());
        }
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbc.query(SQL_SELECT_ALL,
                (rs, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(this.findTypeById(rs.getInt("type_id")));
                    Integer[] pgArray = (Integer[]) rs.getArray("array_agg").getArray();
                    Set<Rule> rules = new HashSet<>();
                    for (Integer id : pgArray) {
                        rules.add(this.findRuleById(id));
                    }
                    accident.setRules(rules);
                    return accident;
                });

    }

    @Override
    public Accident findById(int id) {
        return jdbc.queryForObject(SQL_SELECT_BY_ID,
                (rs, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(this.findTypeById(rs.getInt("type_id")));
                    return accident;
                }, id);
    }

    @Override
    public void update(Accident accident) {
        jdbc.update("UPDATE accident SET name = ?, text = ?, address = ?, type_id = ? where id = ?",
                new Object[] {accident.getName(),
                        accident.getText(),
                        accident.getAddress(),
                        accident.getType().getId(),
                        accident.getId()});
    }

    @Override
    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("SELECT id, name from accident_type where id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(AccidentType.class));
    }

    @Override
    public Rule findRuleById(int id) {
        return jdbc.queryForObject("SELECT id, name from rule where id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return jdbc.query("SELECT id, name FROM accident_type",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    @Override
    public Collection<Rule> findAllRules() {
        return jdbc.query("SELECT id, name FROM rule", new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public Set<Rule> installRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            rules.add(this.findRuleById(Integer.parseInt(id)));
        }
        return rules;
    }
}
