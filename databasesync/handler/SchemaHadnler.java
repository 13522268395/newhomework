package homework.databasesync.handler;

import homework.databasesync.enums.Action;
import homework.databasesync.pojo.ConnectDTO;
import homework.databasesync.pojo.SchemaActionDTO;

/**
 * @Author myy
 * @create 2019/7/30 21:44
 */
public class SchemaHadnler {
    public static void doAction(SchemaActionDTO actionDTO){
        ConnectDTO src = actionDTO.getSrc();

        ConnectDTO dst = actionDTO.getDst();

        Action action = actionDTO.getAction();

        if (Action.SYNC.equals(action)){
            SyncHandler.doSync(src,dst);
        }else if (Action.COPY.equals(action)){
            //TODO
        }else if (Action.DIFF.equals(action)){
            //TODO
        }else {
            throw new IllegalArgumentException("have not support this action");
        }
    }
}
