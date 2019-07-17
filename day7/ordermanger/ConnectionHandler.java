package homework.day7.ordermanger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/17 21:41
 */
public final class ConnectionHandler {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/cakes?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWD = "123456";

    private ConnectionHandler() {
    }

    public static ConnectionHandler of(){
        return new ConnectionHandler();
    }

    public Connection getConnection(){
        try{
            return DriverManager.getConnection(URL,USER_NAME,PASSWD);
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }
}
