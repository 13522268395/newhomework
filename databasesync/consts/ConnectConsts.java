package homework.databasesync.consts;

import homework.databasesync.pojo.ConnectDTO;

/**
 * @Author myy
 * @create 2019/7/29 23:56
 */
public interface ConnectConsts {

    ConnectDTO SRC = ConnectDTO.builder()
            .host("127.0.0.1")
            .port(3306)
            .username("root")
            .passwd("123456")
            .build();

    ConnectDTO DST = ConnectDTO.builder()
            .host("127.0.0.1")
            .port(3307)
            .username("root")
            .passwd("123456")
            .build();

    String INFO_SCHEMA_DB_NAME = "information_schema";
}
