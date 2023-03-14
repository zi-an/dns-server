package my;

public class Util {
    static byte[] ip2byte(String ipAddr) {
        byte[] bytes = new byte[4];
        String[] ipArr = ipAddr.split("\\.");
        bytes[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
        bytes[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
        bytes[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
        bytes[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
        return bytes;
    }
}
