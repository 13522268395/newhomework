package homework.databasesync.util;

import com.google.common.base.Strings;

/**
 * @Author myy
 * @create 2019/7/30 22:05
 * 借鉴于Objects requireNonNull
 */
public class StringUtils {

    public static String requiredNonNullOrEmpty(String str){
        if (Strings.isNullOrEmpty(str)){
            throw new IllegalArgumentException("should non null or empty");
        }
        return str;
    }
}
