package homework.databasesync.handler;

import com.google.common.collect.Sets;
import homework.databasesync.consts.ConnectConsts;
import homework.databasesync.dao.JdbcUtils;
import homework.databasesync.pojo.ConnectDTO;
import org.apache.commons.lang3.tuple.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
/**
 * @Author myy
 * @create 2019/7/31 0:22
 */
public class DbHandler {

    public static void copyDb(ConnectDTO src, ConnectDTO dst, Set<String> target){

        for (String db : target) {
            log.info("start copy db={}", db);
            String tableSql = String.format("select * from TABLES where TABLE_SCHEMA='%s'",db);
            log.info("tablesSql = {}",tableSql);
            Set<String> tables = JdbcUtils.read(src, ConnectConsts.INFO_SCHEMA_DB_NAME,tableSql)
                    .stream().map(buf->buf.get("TABLE_NAME").toString()).collect(Collectors.toSet());
            log.info("tables={}", tables);

            String createDbSql = String.format("create database %s default charset utf8;", db);
            JdbcUtils.write(dst,null,createDbSql);

            for (String table : tables) {
                log.info("start copy table={}", table);
                String showCreateTableSql = String.format("show create table `%s`", table);

                log.info("showCreateTableSql={}", showCreateTableSql);

                String createTableSql = JdbcUtils.read(src,db,showCreateTableSql)
                        .stream().map(buf->buf.get("Create Table").toString()).findFirst().get();

                JdbcUtils.write(dst,db,createTableSql);
            }
        }
    }
    public static void diffDb(ConnectDTO src, ConnectDTO dst, Set<String> target){

        for (String db : target) {
            Pair<Set<String>,Set<String>> tablePair = parseTable(src,dst,db);

            TableHandler.copyTable(src,dst,db,tablePair.getLeft());
            TableHandler.diffTable(src,dst,db,tablePair.getRight());
        }
    }
    private static Pair<Set<String>,Set<String>> parseTable(ConnectDTO src,ConnectDTO dst,String db){
        String queryTablesSql = String.format("select * from tables where table_schema = '%s'",db);
        //取出src下db下的所有表
        Set<String> srcTables = JdbcUtils.read(src, ConnectConsts.INFO_SCHEMA_DB_NAME, queryTablesSql).stream()
                .map(entity -> entity.get("TABLE_NAME").toString()).collect(Collectors.toSet());
        //取出dst下db下的所有表
        Set<String> dstTables = JdbcUtils.read(dst, ConnectConsts.INFO_SCHEMA_DB_NAME, queryTablesSql).stream()
                .map(entity -> entity.get("TABLE_NAME").toString()).collect(Collectors.toSet());
        //对比差异
        Set<String> differenceTables = Sets.difference(srcTables, dstTables).immutableCopy();
        Set<String> sameTables = Sets.intersection(srcTables, dstTables).immutableCopy();

        return Pair.of(differenceTables,sameTables);
    }
}
