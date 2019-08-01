package homework.databasesync.pojo;

import lombok.*;

/**
 * @Author myy
 * @create 2019/8/1 1:38
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class IndexInfoDTO {
    private String indexName;
    private String columnName;
    private String seqIndex;
}
