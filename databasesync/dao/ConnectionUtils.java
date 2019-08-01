package homework.databasesync.dao;

import homework.databasesync.pojo.ConnectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Objects;

/**
 * @Author myy
 * @create 2019/7/30 22:20
 * protected static final Logger logger = LoggerFactory.getLogger(XXX.class);
 * 使用指定的类XXX初始化日志对象，方便在日志输出的时候，可以打印出日志信息所属的类。
 */
public class ConnectionUtils {

    private final static Logger LOGGER  = LoggerFactory.getLogger(ConnectionUtils.class);

    private ConnectionUtils(){

    }

    private static class ClassHolder{

        public final static ConnectionUtils INSTANCE = new ConnectionUtils();
    }

    public static ConnectionUtils of(){
        //为什么用这种写法
        return ClassHolder.INSTANCE;
    }

    public Connection getConn(ConnectDTO connectDTO){
        try {
            String url = String.format("jdbc:mysql://%s:%s?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", connectDTO.getHost(), connectDTO.getPort());
            return DriverManager.getConnection(url,connectDTO.getUsername(),connectDTO.getPasswd());
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    public static final void release(ResultSet resultSet, Statement statement,Connection connection){
        if (!Objects.isNull(resultSet)){
            try {
                resultSet.close();
            }catch (SQLException e){
                LOGGER.error("close resultset err {}",e);
            }
        }
        if (!Objects.isNull(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                // 关不掉 往下走
                LOGGER.error("close statement error {}", e);
            }
        }
        if (!Objects.isNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                // 关不掉不能往下走
                LOGGER.error("close connection error {}", e);
                throw new RuntimeException(e);
            }
        }
    }
}
