package homework.databasesync.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author
 * 连接对象封装
 * @create 2019/7/28 11:11
 */
@Setter
@Getter
@ToString
@Builder
public class ConnectDTO {
    private String host;
    private Integer port;
    private String username;
    private String passwd;
    private String db;
}
