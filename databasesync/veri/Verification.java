package homework.databasesync.veri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author myy
 * @create 2019/7/30 0:57
 */
public class Verification {
    private static final String IPADDRESS = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."

            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."

            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."

            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    private static Pattern ipPattern = Pattern.compile(IPADDRESS);
    public static boolean isIpaddress(String str){
        Matcher matcher = ipPattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(Verification.isIpaddress("168.1.1.2"));
    }
}
