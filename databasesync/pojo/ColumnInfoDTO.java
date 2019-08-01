package homework.databasesync.pojo;

import lombok.*;

/**
 * @Author myy
 * @create 2019/7/31 23:35
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ColumnInfoDTO {
    private String columnType;
    private String columnComment;
    private String isNullable;
    private String columnDefault;
}
