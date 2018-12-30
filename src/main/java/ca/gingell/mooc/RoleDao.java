package ca.gingell.mooc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Role role) {
        String sql = "insert into Authorities (username, authority) values (?, ?)";
        jdbcTemplate.update(sql, role.getUsername(), role.getAuthority());
    }

    public List<Role> loadAll() {
        return jdbcTemplate.query("select * from Authorities", (resultSet, i) -> {
            return toAuths(resultSet);
        });
    }

    private Role toAuths(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setUsername(resultSet.getString("USERNAME"));
        role.setAuthority(resultSet.getString("AUTHORITY"));
        return role;
    }
}

