package homework.databasesync.handler;

import homework.databasesync.consts.ConnectConsts;
import homework.databasesync.dao.JdbcUtils;
import homework.databasesync.pojo.ConnectDTO;
import homework.databasesync.pojo.IndexInfoDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author myy
 * @create 2019/8/1 0:04
 */
@Slf4j
public class IndexHandler {
    public static void copyIndex(ConnectDTO src, ConnectDTO dst, String db, String table, Set<String> targetIndexs){
        for (String index : targetIndexs) {
            String queryIndexSql = String.format("select * from statistics where table_schema = '%s' and table_name = '%s' and index_name = '%s'",db,table,index);

            List<Map<String, Object>> indexEntities = JdbcUtils.read(src, ConnectConsts.INFO_SCHEMA_DB_NAME, queryIndexSql);
            for (Map<String, Object> indexEntity : indexEntities) {
                String indexColumnName = indexEntity.get("COLUMN_NAME").toString();
                log.info("do index sync start, args={}", indexColumnName);
                //多列索引怎么处理？？？
                //while (Integer.valueOf(indexSeq) == 1)判断先不做
                String indexSeq = indexEntity.get("SEQ_IN_INDEX").toString();

                //普通索引、主键索引、唯一索引

                if ("PRIMARY".equals(index)){
                        String createIndexSql = String.format("alter table %s add primary key (%s)",table,indexColumnName);
                        JdbcUtils.write(dst,db,createIndexSql);
                }else if (index.equals(indexColumnName)){
                        String createIndexSql = String.format("alter table %s add unique (%s)",table,indexColumnName);
                        JdbcUtils.write(dst,db,createIndexSql);
                }else {
                        String createIndexSql = String.format("alter table %s add index %s (%s)",table,index,indexColumnName);
                        JdbcUtils.write(dst,db,createIndexSql);
                }
            }
        }

    }

    public static void diffIndex(ConnectDTO src, ConnectDTO dst, String db, String table, Set<String> targetIndexs){
        for (String index : targetIndexs) {
            String queryIndexSql = String.format("select * from statistics where table_schema = '%s' and table_name = '%s' and index_name = '%s'", db, table, index);
            Set<IndexInfoDTO> srcIndexSet = JdbcUtils.read(src, ConnectConsts.INFO_SCHEMA_DB_NAME, queryIndexSql)
                    .stream()
                    .map(entity -> {
                        return IndexInfoDTO.builder()
                                .columnName(entity.get("COLUMN_NAME").toString())
                                .indexName(entity.get("INDEX_NAME").toString())
                                .seqIndex(entity.get("SEQ_IN_INDEX").toString())
                                .build();
                    }).collect(Collectors.toSet());
            Iterator<IndexInfoDTO> srcIterator = srcIndexSet.iterator();
            log.info("do index sync start, args={}",srcIterator.next().getColumnName());

            Set<IndexInfoDTO> dstIndexSet = JdbcUtils.read(dst, ConnectConsts.INFO_SCHEMA_DB_NAME, queryIndexSql)
                    .stream()
                    .map(entity -> {
                        return IndexInfoDTO.builder()
                                .columnName(entity.get("COLUMN_NAME").toString())
                                .indexName(entity.get("INDEX_NAME").toString())
                                .seqIndex(entity.get("SEQ_IN_INDEX").toString())
                                .build();
                    }).collect(Collectors.toSet());
            Iterator<IndexInfoDTO> dstIterator = dstIndexSet.iterator();
            //log.info("do index sync start, args={}",dstIterator.next().getColumnName());

            while (dstIterator.hasNext()){
                if ("PRIMARY".equals(dstIterator.next().getIndexName())){
                    String dropIndexSql = String.format("alter table %s drop primary key",table);
                    JdbcUtils.write(dst,db,dropIndexSql);
                }else if (index.equals(dstIterator.next().getColumnName())){
                    String dropIndexSql = String.format("alter table %s drop index `%s`",table,dstIterator.next().getColumnName());
                    JdbcUtils.write(dst,db,dropIndexSql);
                }else {
                    String dropIndexSql = String.format("alter table %s drop index %s ",table,index);
                    JdbcUtils.write(dst,db,dropIndexSql);
                }
            }
            while (srcIterator.hasNext()){
                if ("PRIMARY".equals(index)){
                    String createIndexSql = String.format("alter table %s add primary key (%s)",table,srcIterator.next().getColumnName());
                    JdbcUtils.write(dst,db,createIndexSql);
                }else if (index.equals(srcIterator.next().getColumnName())){
                    String createIndexSql = String.format("alter table %s add unique (%s)",table,srcIterator.next().getColumnName());
                    JdbcUtils.write(dst,db,createIndexSql);
                }else {

                    String createIndexSql = String.format("alter table %s add index %s (%s)",table,index,srcIterator.next().getColumnName());

                    JdbcUtils.write(dst,db,createIndexSql);
                }
            }
            //Set<IndexInfoDTO> differenceIndex = Sets.difference(srcIndexSet, dstIndexSet).immutableCopy();
            //如果有不同，应该是先删除dst原有索引，然后按照src的索引，再去建立索引
            //TODO
//            for (IndexInfoDTO indexInfoDTO : differenceIndex) {
//
//                if ("PRIMARY".equals(indexInfoDTO.getIndexName())){
//                    String dropIndexSql = String.format("alter table %s drop primary key",table);
//                    JdbcUtils.write(dst,db,dropIndexSql);
//                    String createIndexSql = String.format("alter table %s add primary key (%s)",table,indexInfoDTO.getColumnName());
//                    JdbcUtils.write(dst,db,createIndexSql);
//                }else if (index.equals(indexInfoDTO.getColumnName())){
//                    String dropIndexSql = String.format("alter table %s drop index `%s`",table,indexInfoDTO.getColumnName());
//                    JdbcUtils.write(dst,db,dropIndexSql);
//                    String createIndexSql = String.format("alter table %s add unique (%s)",table,indexInfoDTO.getColumnName());
//                    JdbcUtils.write(dst,db,createIndexSql);
//                }else {
//
//                }
//            }
        }
    }
}
