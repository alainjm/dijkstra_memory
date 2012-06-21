package dijkstra_memory;

    
 import java.lang.management.ManagementFactory;

public class PerformanceMonitor {
        static long lastSystemTime      = 0;
        static long lastProcessCpuTime  = 0;
        public static int  availableProcessors = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
        public synchronized double getCpuUsage()
        {
            ManagementFactory.getThreadMXBean().setThreadCpuTimeEnabled(true);
            if ( lastSystemTime == 0 )
            {
                baselineCounters();
              //  return ;
            }

            long systemTime     = System.nanoTime();
            long processCpuTime = 0;

            processCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
            double cpuUsage = (double) (processCpuTime - lastProcessCpuTime ) / ( systemTime - lastSystemTime )*100.0;

            lastSystemTime     = systemTime;
            lastProcessCpuTime = processCpuTime;

            return cpuUsage / availableProcessors;
        }

        private void baselineCounters()
        {
            lastSystemTime = System.nanoTime();

            lastProcessCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
        }

    }
