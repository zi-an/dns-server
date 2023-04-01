import java.io.IOException;
import java.io.PrintStream;
import java.time.ZoneId;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pid {
    public static void main(String[] args)   {

        //以下是获取当前进程pid,main函数启动后会退出
        System.out.println("我的pid"+ProcessHandle.current().pid());



    }
}

