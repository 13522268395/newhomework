package homework.databasesync;

import homework.databasesync.enums.Action;
import homework.databasesync.handler.SchemaHadnler;
import homework.databasesync.pojo.ConnectDTO;
import homework.databasesync.pojo.SchemaActionDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author
 * @create 2019/7/28 11:07
 */
@Slf4j
public class App {

    /**
     * java -jar xxx.jar param1 param2 param3
     * start 校验参数个数 类型 格式
     * src  host:port:username:passwd:db
     * dst
     * action   sync    diff    copy
     */
    public static void main(String[] args) {
        log.info("do schema sync start, args={}", Arrays.toString(args));
        start(args);
    }

    private static void start(String[] args) {

        SchemaActionDTO actionDTO = parse(args);

        SchemaHadnler.doAction(actionDTO);
//        System.out.println(actionDTO);
//        SchemaActionDTO.doAction(actionDTO);
    }

    private static SchemaActionDTO parse(String[] args) {

        return SchemaActionDTO.builder()
                .src(ConnectDTO.builder()
                        .host("127.0.0.1")
                        .port(3306)
                        .username("root")
                        .passwd("123456")
                        .build())
                .dst(ConnectDTO.builder()
                        .host("127.0.0.1")
                        .port(3307)
                        .username("root")
                        .passwd("123456")
                        .build())
                .action(Action.SYNC)
                .build();
//        String[] srcData = args[0].split(":");
//
//        String[] dstData = args[1].split(":");
//
//        //参数个数不为3或者数据库信息个数不是4-5直接时，抛出错误信息
//        if (args.length != 3) {
//            throw new IllegalArgumentException("参数个数不正确");
//        } else if (srcData.length < 4 || srcData.length > 5) {
//            throw new IllegalArgumentException("源数据库信息参数不正确");
//        } else if (dstData.length < 4 || dstData.length > 5) {
//            throw new IllegalArgumentException("目标数据库信息参数不正确");
//        } else if (!Verification.isIpaddress(srcData[0]) || !Verification.isIpaddress(dstData[0])){
//            throw new IllegalArgumentException("数据库ip格式不正确");
//        } else if (null != srcData[4] && null!=dstData[4]){
//        //有没有传数据库信息,这里是分别处理吗，应该是要么都传，要么都不传，但如何校验呢
//            return SchemaActionDTO.builder()
//                    .src(ConnectDTO.builder()
//                            .host(srcData[0]).port(Integer.valueOf(srcData[1])).username(srcData[2]).passwd(srcData[3]).db(srcData[4]).build())
//                    .dst(ConnectDTO.builder()
//                            .host(dstData[0]).port(Integer.valueOf(dstData[1])).username(dstData[2]).passwd(dstData[3]).db(dstData[4]).build())
//                    .action(Action.of(args[2]))
//                    .build();
//        }else {
//
//            return SchemaActionDTO.builder()
//                    .src(ConnectDTO.builder()
//                            .host(srcData[0]).port(Integer.valueOf(srcData[1])).username(srcData[2]).passwd(srcData[3]).build())
//                    .dst(ConnectDTO.builder()
//                            .host(dstData[0]).port(Integer.valueOf(dstData[1])).username(dstData[2]).passwd(dstData[3]).build())
//                    .action(Action.of(args[2]))
//                    .build();
//        }
//    }
    }
}
