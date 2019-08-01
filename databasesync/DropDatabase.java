package homework.databasesync;

import homework.databasesync.consts.ConnectConsts;
import homework.databasesync.dao.JdbcUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author myy
 * @create 2019/7/31 21:37
 */
public class DropDatabase {
    public static void main(String[] args) {
        List<String> dbs = JdbcUtils.read(ConnectConsts.DST, null, "show databases").stream()
                .map(entity -> entity.get("SCHEMA_NAME").toString())
                .filter(db -> !db.equals("information_schema"))
                .filter(db -> !db.equals("sys"))
                .filter(db -> !db.equals("performance_schema"))
                .filter(db -> !db.equals("mysql"))
                .collect(Collectors.toList());

        dbs.forEach(db -> {
            System.out.println(db);
            JdbcUtils.write(ConnectConsts.DST, null, "drop database " + db);
        });
    }
}
