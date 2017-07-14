import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class MyBenchmark {
    private int type;
    private String name;
    private long runTime;
    private double memUsed;
    private int processors;
    private double load;
    
    public MyBenchmark() {
        type = 0;
    }
    
    public MyBenchmark(int t) {
        type = t;
    }
    
    public void timeMyOperation(int reps) {
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        
        double beforeLoad = os.getProcessCpuLoad();
        //double[] before = EnergyCheckUtils.getEnergyStats();
        
        
        long startTime = System.currentTimeMillis();
        double startMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        for (int k = 0; k < 1000000; k++)
            switch (type) {
                case 1 : 
                    Factorial.iterative(20);
                    name = "Iteration";
                    break;
                case 2 : 
                    Factorial.iterativeObjects(20);
                    name = "Iterative Objects";
                    break;
                default: 
                    Factorial.recursive(20);
                    name = "Recursion";
                    break;
            }
        
        long endTime = System.currentTimeMillis();
        double endMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        double afterLoad = os.getProcessCpuLoad();
        
        runTime = endTime - startTime;
        memUsed = endMem - startMem;
        processors = Runtime.getRuntime().availableProcessors();
        load = afterLoad - beforeLoad;
        
      /*  double[] after = EnergyCheckUtils.getEnergyStats();
        System.out.println(
                "Power consumption of dram: " + (after[0] - before[0]) / 10.0 + 
                " power consumption of cpu: " + (after[1] - before[1]) / 10.0 + 
                " power consumption of package: " + (after[2] - before[2]) / 10.0);
        */
        
      
        System.out.println("Thread: " + name);
        System.out.println("======================");
        System.out.println("RunTime: " + runTime + " ms");
        System.out.println("MemoryUsed: " + memUsed + " bytes");
        System.out.println("Available Processors: " + processors);
        System.out.println("CPU Usage: " + load);
        
        System.out.println("\n");
    }
    
    public String toJSONArray() {
        String json = '"' + name + '"' + ':' + "[";
        json += '"' + "Runtime" + '"' + ':' + '"' + runTime + '"' + ',';
        json += '"' + "MemoryUsage" + '"' + ':' + '"' + memUsed + '"' + ',';
        json += '"' + "Processors" + '"' + ':' + '"' + processors + '"' + ',';
        json += '"' + "CPU Load" + '"' + ':' + '"' + load + '"' + ',';
        
        json += "]";
        return json;
    }
}