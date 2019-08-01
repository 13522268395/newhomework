package homework.databasesync.pojo;

import homework.databasesync.enums.Action;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author myy
 * @create 2019/7/28 11:09
 */
@Setter
@Getter
@ToString
@Builder
public class SchemaActionDTO {
    private ConnectDTO src;
    private ConnectDTO dst;
    private Action action;
}
