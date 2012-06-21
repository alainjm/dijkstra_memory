/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra_memory;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

//import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
//import java.lang.management.ThreadMXBean;
//import java.util.Date;

/**
 *
 * @author Alain Janin-Manificat <alain.janinm@hotmail.fr>
 */

//-verbose:gc -XX:+PrintGCDetails -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime
public class Dijkstra_memory {

    static int totalNbTest = 0;
    static int nbUnitTest = 0;
    static PerformanceMonitor monitor=null;



    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        monitor = new PerformanceMonitor();
        if(args.length<2){
            System.out.println("Need 2 args : nbTotalTests and nbUnitTest!");
            return;
        }
        try{
            totalNbTest=Integer.parseInt(args[0]);
            nbUnitTest=Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e){
            System.out.println("Args are invalid, have to be integer!");
            return;
        }
        
        printInitRuntimeInfo();
        MemoryUsage mu =ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        MemoryUsage muNH =ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        System.out.println(
                "Init :"+mu.getInit()+
                "\nMax :"+mu.getMax()+
                "\nUsed :"+mu.getUsed()+
                "\nCommited :"+mu.getCommitted()+
                "\nInit NH :"+muNH.getInit()+
                "\nMax NH :"+muNH.getMax()+
                "\nUsed NH:"+muNH.getUsed()+
                "\nCommited NH:"+muNH.getCommitted());
        
        long start=0;
        long end=0;
        long total=0;
        
        for (int i=0;i <totalNbTest ; i++ ){
//                        
//            long nanoBefore = System.nanoTime();
//            long cpuBefore = ( (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean() ).getProcessCpuTime();

            start=System.nanoTime();
            
            benchMark();
            
            end=System.nanoTime();
            total+=end-start;
            
            //current memory usage in kilobytes V1
            //double currentMemory = ( (double)((double)(Runtime.getRuntime().totalMemory()/1024.0)))-
            //        ((double)((double)(Runtime.getRuntime().freeMemory()/1024.0)));
            
            mu = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
            muNH = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
            
            //Human readable
//            System.out.println(
//                    "Current memory usage in byte :"+(mu.getUsed()+muNH.getUsed())+
//                    //"\nCurrent memory usage in kilobytes :"+currentMemory+
//                    //"\nTotal allocated mem :"+Runtime.getRuntime().totalMemory()+
//                    "\nTotal Commited :"+(mu.getCommitted()+muNH.getCommitted()));
            
            //For log
            //System.out.println(total/(1000000.0)+";"+(mu.getUsed()+muNH.getUsed())+";"+(mu.getCommitted()+muNH.getCommitted()));
            System.out.println("Commited ; "+(mu.getCommitted()+muNH.getCommitted()));
            
            double mem2 = monitor.getCpuUsage();
            if(mem2!=0)System.out.println("Current CPU usage in pourcentage : "+mem2);
        }
        
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean tmpGC : gcList){
            
            System.out.println("\nName: " + tmpGC.getName());
            System.out.println("Collection count: " + tmpGC.getCollectionCount());
            System.out.println("Collection time: " + tmpGC.getCollectionTime());
            System.out.println("Memory Pools: ");
            
            String[] memoryPoolNames = tmpGC.getMemoryPoolNames();
            for(String mpnTmp : memoryPoolNames){
                System.out.println("\t" + mpnTmp);
            }
            
        }
          
        System.out.println( "Memory Pools Info" );
        List<MemoryPoolMXBean> memoryList = ManagementFactory.getMemoryPoolMXBeans();
        for(MemoryPoolMXBean tmpMem : memoryList){
            
            System.out.println("\nName: " + tmpMem.getName());
            System.out.println("Usage: " + tmpMem.getUsage());
            System.out.println("Collection Usage: " + tmpMem.getCollectionUsage());
            System.out.println("Peak Usage: " + tmpMem.getPeakUsage());
            System.out.println("Type: " + tmpMem.getType());
            System.out.println("Memory Manager Names: ") ;
            
            String[] memManagerNames = tmpMem.getMemoryManagerNames();
            for(String mmnTmp : memManagerNames){
                System.out.println("\t" + mmnTmp);
            }
            System.out.println("\n");
        }
        
        System.out.println("Nombre de tests réalisés : "+totalNbTest+"\n\n"
                + "Nombre de création de graphes et resolution : "+nbUnitTest+"\n");
    }
    public static void benchMark(){

        for(int i=0;i<nbUnitTest;i++){

            Graph g = new Graph(17);
            g.addVertex("A");
            g.addVertex("B");
            g.addVertex("C");
            g.addVertex("D");
            g.addVertex("E");
            g.addVertex("F");
            g.addVertex("G");
            g.addVertex("H");
            g.addVertex("I");
            g.addVertex("J");
            g.addVertex("K");
            g.addVertex("L");
            g.addVertex("M");
            
            g.addVertex("N");
            g.addVertex("O");
            g.addVertex("P");
            g.addVertex("Q");

            g.addEdge ("A", "B", 5);
            g.addEdge ("A", "C", 4);
            g.addEdge ("A", "D", 3);
            g.addEdge ("B", "C", 2);
            g.addEdge ("B", "E", 5);
            g.addEdge ("C", "B", 1);
            g.addEdge ("C", "E", 4);
            g.addEdge ("E", "F", 3);
            g.addEdge ("E", "G", 7);
            g.addEdge ("E", "H", 3);
            g.addEdge ("F", "G", 2);
            g.addEdge ("F", "I", 7);
            g.addEdge ("G", "I", 6);
            g.addEdge ("H", "I", 5);
            g.addEdge ("I", "J", 3);
            g.addEdge ("I", "K", 4);
            g.addEdge ("I", "L", 5);
            g.addEdge ("J", "M", 4);
            g.addEdge ("K", "M", 7);
            g.addEdge ("L", "M", 3);
            
            g.addEdge ("I", "M", 2);
            g.addEdge ("I", "C", 9);
            g.addEdge ("I", "F", 2);
            g.addEdge ("I", "N", 6);
            g.addEdge ("J", "K", 2);
            g.addEdge ("J", "L", 3);
            g.addEdge ("K", "L", 5);
            g.addEdge ("K", "E", 7);
            g.addEdge ("K", "G", 2);
            g.addEdge ("K", "J", 9);
            g.addEdge ("L", "B", 2);
            g.addEdge ("L", "E", 3);
            g.addEdge ("L", "H", 7);
            g.addEdge ("A", "H", 23);
            g.addEdge ("A", "K", 54);
            g.addEdge ("B", "F", 29);
            g.addEdge ("B", "K", 17);
            g.addEdge ("C", "F", 7);
            g.addEdge ("C", "G", 12);
            g.addEdge ("C", "L", 18);
            g.addEdge ("M", "N", 3);
            g.addEdge ("M", "O", 7);
            g.addEdge ("M", "P", 8);
            g.addEdge ("N", "L", 1);
            g.addEdge ("O", "K", 8);
            g.addEdge ("P", "G", 2);
            g.addEdge ("P", "K", 3);
            g.addEdge ("P", "C", 2);
            g.addEdge ("P", "F", 29);
            g.addEdge ("P", "Q", 9);
                       
            ArrayList<String> path = g.resolveDijkstra("A", "Q");


           // System.out.println(path);
        }
        //System.out.println("Nombre d'exécution : "+nbUnitTest+"\nCréation du graphe : "+graphTime/1000000.0+"ms\nRésolution :"+algoTime/1000000.0+"ms");

    }

    private static void printInitRuntimeInfo() {
        /* Total number of processors or cores available to the JVM */
    System.out.println("Total number of processors or cores available to the JVM (cores): " + 
        Runtime.getRuntime().availableProcessors());

    /* Total amount of free memory available to the JVM */
    System.out.println("Total amount of free memory available to the JVM (bytes): " + 
        Runtime.getRuntime().freeMemory());

    /* This will return Long.MAX_VALUE if there is no preset limit */
    long maxMemory = Runtime.getRuntime().maxMemory();
    /* Maximum amount of memory the JVM will attempt to use */
    System.out.println("Maximum amount of memory the JVM will attempt to use (bytes): " + 
        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

    /* Total memory currently in use by the JVM */
    System.out.println("Total memory currently in use by the JVM - heap size (bytes): " + 
        Runtime.getRuntime().totalMemory());
    
    }
 
}
