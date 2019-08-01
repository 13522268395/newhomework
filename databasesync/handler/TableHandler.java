package homework.databasesync.handler;

import com.google.common.collect.Sets;
import homework.databasesync.consts.ConnectConsts;
import homework.databasesync.dao.JdbcUtils;
import homework.databasesync.pojo.ConnectDTO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author myy
 * @create 2019/7/31 21:57
 */
public class TableHandler {

    public static void copyTable(ConnectDTO src, ConnectDTO dst, String db, Set<String> targetTables){

        for (String table : targetTables) {
            String creatTableSql = String.format("show create table %s",table);
            String sql = JdbcUtils.read(src,db,creatTableSql).stream()
                    .map(entity->entity.get("Create Table").toString()).findFirst().get();

            JdbcUtils.write(dst,db,sql);
        }
    }
    /**
     *  分析相同表的字段和索引
     */
    public static void diffTable(ConnectDTO src, ConnectDTO dst, String db, Set<String> targetTables){

        for (String table : targetTables) {
            // 1.分析差异字段
            Pair<Set<String>, Set<String>> columnPair = parseColumn(src, dst, db, table);
            // 2.复制src有,dst无
            ColumnHandler.copyColumn(src, dst, db, table, columnPair.getLeft());
            // 3.分析src有,dst有
            ColumnHandler.diffColumn(src, dst, db, table, columnPair.getRight());



            Pair<Set<String>, Set<String>> indexPair = parseIndex(src,dst,db,table);

            IndexHandler.copyIndex(src, dst, db, table, indexPair.getLeft());

            IndexHandler.diffIndex(src, dst, db, table, indexPair.getRight());


        }

    }

    private static Pair<Set<String>, Set<String>> parseColumn(ConnectDTO src, ConnectDTO dst, String db, String table){
        String queryColumnSql = String
                .format("select * from COLUMNS where TABLE_SCHEMA='%s' and TABLE_NAME='%s'", db, table);
        Set<String> srcColumnSet = JdbcUtils
                .read(src, ConnectConsts.INFO_SCHEMA_DB_NAME, queryColumnSql).stream()
                .map(entity -> entity.get("COLUMN_NAME").toString()).collect(
                        Collectors.toSet());
        Set<String> dstColumnSet = JdbcUtils
                .read(dst, ConnectConsts.INFO_SCHEMA_DB_NAME, queryColumnSql).stream()
                .map(entity -> entity.get("COLUMN_NAME").toString()).collect(
                        Collectors.toSet());
        Set<String> differenceColumns = Sets.difference(srcColumnSet, dstColumnSet).immutableCopy();
        Set<String> sameColumns = Sets.intersection(srcColumnSet, dstColumnSet).immutableCopy();

        return Pair.of(differenceColumns, sameColumns);
    }

    private static Pair<Set<String>, Set<String>> parseIndex(ConnectDTO src, ConnectDTO dst, String db, String table){

        String queryIndexSql = String
                .format("select * from statistics where TABLE_SCHEMA='%s' and TABLE_NAME='%s'", db, table);

        Set<String> srcIndexSet = JdbcUtils.read(src, ConnectConsts.INFO_SCHEMA_DB_NAME, queryIndexSql).stream()
                .map(entity -> entity.get("INDEX_NAME").toString()).collect(Collectors.toSet());

        Set<String> dstIndexSet = JdbcUtils.read(dst, ConnectConsts.INFO_SCHEMA_DB_NAME, queryIndexSql).stream()
                .map(entity -> entity.get("INDEX_NAME").toString()).collect(Collectors.toSet());

        Set<String> differenceIndexs = Sets.difference(srcIndexSet, dstIndexSet).immutableCopy();
        Set<String> sameIndexs = Sets.difference(srcIndexSet, dstIndexSet).immutableCopy();

        return Pair.of(differenceIndexs, sameIndexs);
    }
}
