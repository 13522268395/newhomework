package homework.databasesync.enums;

/**
 * @Author myy
 * @create 2019/7/29 23:54
 */
public enum  Action {
    /**
     *
     */
    SYNC,
    DIFF,
    COPY;

    public static Action of(String action){
        if ("sync".equals(action.toLowerCase())){
            return SYNC;
        }else if ("diff".equals(action.toLowerCase())){
            return DIFF;
        }else if ("copy".equals(action.toLowerCase())){
            return COPY;
        }else {
            throw new IllegalArgumentException("have not support this action" + action);
        }
    }
}
