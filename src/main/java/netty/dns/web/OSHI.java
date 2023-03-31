package netty.dns.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

@RestController
public class OSHI {
    @RequestMapping("/cpu")
    @CrossOrigin
    String cpuInfo() {
        ObjectMapper mapper = new ObjectMapper();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        try {
            CentralProcessor cpu = hal.getProcessor();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cpu);
        } catch (JsonProcessingException e) {
            System.out.println("Exception encountered: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping("/mem")
    @CrossOrigin //允许跨域调试
    Mem menInfo() {
        ObjectMapper mapper = new ObjectMapper();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory globalMemory = hal.getMemory();
        Mem mem=new Mem(globalMemory.getTotal(),globalMemory.getAvailable(),Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        return mem;
    }
    class Mem {
        private long total;
        private long available;
        private long jvm;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public long getAvailable() {
            return available;
        }

        public void setAvailable(long available) {
            this.available = available;
        }

        public long getJvm() {
            return jvm;
        }

        public void setJvm(long jvm) {
            this.jvm = jvm;
        }

        public Mem(long total, long available, long jvm) {
            this.total = total/1024/1024;
            this.available = available/1024/1024;
            this.jvm = jvm/1024/1024;
        }
    }
}
