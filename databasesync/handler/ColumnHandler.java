package homework.databasesync.handler;

import com.google.common.collect.Sets;
import homework.databasesync.consts.ConnectConsts;
import homework.databasesync.dao.JdbcUtils;
import homework.databasesync.pojo.ColumnInfoDTO;
import homework.databasesync.pojo.ConnectDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author myy
 * @create 2019/7/31 22:34
 */
public class ColumnHandler {

    public static void copyColumn(ConnectDTO src,ConnectDTO dst,String db,String table,Set<String> targetColumns){
        for (String column : targetColumns) {
            String queryColumnInfoSql = String.format("select * from COLUMNS where TABLE_SCHEMA='%s' and TABLE_NAME='%s' AND COLUMN_NAME='%s'",
                    db, table,column);

            List<Map<String, Object>> entities = JdbcUtils.read(src, ConnectConsts.INFO_SCHEMA_DB_NAME, queryColumnInfoSql);

            for (Map<String, Object> entity : entities) {
                String columnType = entity.get("COLUMN_TYPE").toString();
                String columnComment = entity.get("COLUMN_COMMENT").toString();
                String isNullable = entity.get("IS_NULLABLE").toString();
                String columnDefault = entity.get("COLUMN_DEFAULT").toString();

                String sql = String.format("alter table %s add column %s %s %s %s comment '%s'",
                        table,
                        column,
                        columnType,
                        isNullableSet(isNullable),
                        isDefaultSet(columnDefault),
                        columnComment
                );

                JdbcUtils.write(dst,db,sql);
            }
        }
    }

    private static String isDefaultSet(String columnDefault){
        if ("NULL".equals(columnDefault)){
            return "";
        }
        return String.format("default '%s'", columnDefault);
    }

    private static String isNullableSet(String isNullable){
        if ("YES".equals(isNullable)) {
            return "";
        } else if ("NO".equals(isNullable)) {
            return "not null";
        } else {
            throw new IllegalArgumentException("这特么是啥" + isNullable);
        }
    }

    public static void diffColumn(ConnectDTO src,ConnectDTO dst,String db,String table,Set<String> targetColumns){
        for (String column : targetColumns) {
            String queryColumnInfoSql = String.format("select * from COLUMNS where TABLE_SCHEMA='%s' and TABLE_NAME='%s' AND COLUMN_NAME='%s'",
                    db, table, column);
            Set<ColumnInfoDTO> srcColumnSet = JdbcUtils.read(src,ConnectConsts.INFO_SCHEMA_DB_NAME,queryColumnInfoSql)
                    .stream()
                    .map(entity->{
                        return ColumnInfoDTO.builder()
                                .columnComment(entity.get("COLUMN_COMMENT").toString())
                                .columnDefault(StringUtils.defaultString(String.valueOf(entity.get("COLUMN_DEFAULT")), ""))
                                .columnType(entity.get("COLUMN_TYPE").toString())
                                .isNullable(entity.get("IS_NULLABLE").toString())
                                .build();
                    }).collect(Collectors.toSet());

            Set<ColumnInfoDTO> dstColumnSet = JdbcUtils.read(dst, ConnectConsts.INFO_SCHEMA_DB_NAME, queryColumnInfoSql)
                    .stream()
                    .map(entity -> {
                        return ColumnInfoDTO.builder()
                                .columnComment(entity.get("COLUMN_COMMENT").toString())
                                .columnDefault(StringUtils.defaultString(String.valueOf(entity.get("COLUMN_DEFAULT")), ""))
                                .columnType(entity.get("COLUMN_TYPE").toString())
                                .isNullable(entity.get("IS_NULLABLE").toString())
                                .build();
                    }).collect(Collectors.toSet());

            Set<ColumnInfoDTO> differenceColumn = Sets.difference(srcColumnSet, dstColumnSet)
                    .immutableCopy();

            for (ColumnInfoDTO infoDTO : differenceColumn) {
                String sql = String.format("alter table %s modify column %s %s %s %s comment '%s'",
                        table,
                        column,
                        infoDTO.getColumnType(),
                        isNullableSet(infoDTO.getIsNullable()),
                        isDefaultSet(infoDTO.getColumnDefault()),
                        infoDTO.getColumnComment()
                );

                JdbcUtils.write(dst, db, sql);
            }
        }

    }

}
