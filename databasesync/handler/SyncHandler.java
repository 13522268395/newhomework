package homework.databasesync.handler;

import com.google.common.collect.Sets;
import homework.databasesync.consts.ConnectConsts;
import homework.databasesync.dao.JdbcUtils;
import homework.databasesync.pojo.ConnectDTO;
import org.apache.commons.lang3.tuple.Pair;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author myy
 * @create 2019/7/30 23:41
 */
public class SyncHandler {

    public static void doSync(ConnectDTO src,ConnectDTO dst){
        //1.解析src和dst中db的差异，相同的和不同的
        Pair<Set<String>, Set<String>> dbPair = parseDb(src, dst);

        //2.src有 dst无

        DbHandler.copyDb(src,dst,dbPair.getLeft());

        //3.src有 dst有

        DbHandler.diffDb(src,dst,dbPair.getRight());


    }
    private static Pair<Set<String>,Set<String>> parseDb(ConnectDTO src, ConnectDTO dst){
        //取出源数据库实例中的数据库名字
        List<Map<String, Object>> SrcResponse = JdbcUtils.read(src, ConnectConsts.INFO_SCHEMA_DB_NAME, "select * from SCHEMATA where SCHEMA_NAME not in('information_schema','mysql','performance_schema','sys');");
        Set<String> srcDbSet = SrcResponse.stream().map(res -> res.get("SCHEMA_NAME").toString()).collect(Collectors.toSet());
        //取出目标数据库实例中的数据库名字
        List<Map<String, Object>> DstResponse = JdbcUtils.read(dst, ConnectConsts.INFO_SCHEMA_DB_NAME, "select * from SCHEMATA where SCHEMA_NAME not in('information_schema','mysql','performance_schema','sys');");
        Set<String> dstDbSet = DstResponse.stream().map(res -> res.get("SCHEMA_NAME").toString()).collect(Collectors.toSet());
        //对比找到不同的
        Set<String> differenceDbSet = Sets.difference(srcDbSet, dstDbSet).immutableCopy();
        //对比找到相同的
        Set<String> sameDbSet = Sets.intersection(srcDbSet, dstDbSet).immutableCopy();
        return Pair.of(differenceDbSet,sameDbSet);
    }
}
