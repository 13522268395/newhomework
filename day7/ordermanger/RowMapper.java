package homework.day7.ordermanger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/18 0:17
 */
public interface RowMapper<T> {

    T mapper(ResultSet rs) throws SQLException;
}
