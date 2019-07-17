package homework.day7.ordermanger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/18 0:47
 */
public interface ModifyConvert<T> {

    void convert(PreparedStatement statement,T t) throws SQLException;
}
