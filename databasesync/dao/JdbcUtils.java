package homework.databasesync.dao;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import homework.databasesync.pojo.ConnectDTO;
import homework.databasesync.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author myy
 * @create 2019/7/30 22:01
 */
@Slf4j
public class JdbcUtils {
    public static Integer write(ConnectDTO connectDTO, String db, String sql){
        Objects.requireNonNull(connectDTO);
        StringUtils.requiredNonNullOrEmpty(sql);

        Connection conn = null;
        Statement statement = null;

        try {
            conn = ConnectionUtils.of().getConn(connectDTO);
            conn.setAutoCommit(false);
            //如果db不为空就把db set进入 如果为空就拉倒
            if (!Strings.isNullOrEmpty(db)){
                //use db
                conn.setCatalog(db);
            }
            statement = conn.createStatement();
            int rows = statement.executeUpdate(sql);
            conn.commit();
            return rows;
        }catch (Exception e){
            log.error("JdbcUtils->write error sql={}", sql);
            throw new IllegalStateException(e);
        }finally {
            ConnectionUtils.release(null,statement,conn);
        }
    }

    public static List<Map<String,Object>> read(ConnectDTO connectDTO, String db, String sql){
        Objects.requireNonNull(connectDTO);
        StringUtils.requiredNonNullOrEmpty(sql);

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> results = Lists.newArrayListWithExpectedSize(1024);

        try {
            conn = ConnectionUtils.of().getConn(connectDTO);

            if (!Strings.isNullOrEmpty(db)){
                conn.setCatalog(db);
            }

            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()){
                Map<String, Object> result = Maps.newHashMapWithExpectedSize(64);
                for (int i = 1; i <= columnCount ; i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    result.put(columnName,columnValue);
                }
                results.add(result);
            }

            return results;
        }catch (Exception e){
            log.error("JdbcUtils->query error sql={}",sql);
            throw new RuntimeException(e);
        }finally {
            ConnectionUtils.release(resultSet,statement,conn);
        }
    }
}
