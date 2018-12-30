package ca.gingell.mooc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "insert into Users (username, password, enabled, details) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEnabled(), user.getDetails());
    }

    public List<User> loadAll() {
        return jdbcTemplate.query("select * from Users", (resultSet, i) -> {
            return toUser(resultSet);
        });
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("USERNAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setEnabled(resultSet.getBoolean("ENABLED"));
        user.setDetails(resultSet.getString("DETAILS"));
        return user;
    }
}

