package netty.dns.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 */
public class Util {
    public static byte[] ip2byte(String ipAddr) {
        byte[] bytes = new byte[4];
        String[] ipArr = ipAddr.split("\\.");
        bytes[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
        bytes[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
        bytes[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
        bytes[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
        return bytes;
    }

    public static boolean isIPv4(String ipString) {
        String ipRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";    //IP地址的正则表达式
        //如果前三项判断都满足，就判断每段数字是否都位于0-255之间
        if (ipString.matches(ipRegex)) {
            String[] ipArray = ipString.split("\\.");
            for (int i = 0; i < ipArray.length; i++) {
                int number = Integer.parseInt(ipArray[i]);
                //4.判断每段数字是否都在0-255之间
                if (number < 0 || number > 255) {
                    return false;
                }
            }
            return true;
        } else {
            return false;    //如果与正则表达式不匹配，则返回false
        }
    }

    public static String getNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return simpleDateFormat.format(new Date());
    }

    static String getNow(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    public static void main(String[] args) {
        //ip2byte("192.168.10.1");

    }
}
