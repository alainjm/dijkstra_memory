/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra_memory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alain Janin-Manificat <alain.janinm@hotmail.fr>
 */
public class Main {
    
    public static PerformanceMonitor monitor = null;

    
    public static void main(String[] args) {
            String s = "ID1 : 0     ID2 : 214 TYPE : ERROR      DATE : 2012-01-11 14:08:07.432 CLASS : Maintenance    SUBCLASS : Operations";  
            Pattern pattern = Pattern.compile("(ID1 :\\s+\\d+|ID2 :\\s+\\d+|TYPE :\\s+\\w+|DATE :\\s+\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{3}|CLASS :\\s+\\w+|SUBCLASS :\\s+\\w+)");  
            Matcher matcher = pattern.matcher(s); 
            String res="";
            while(matcher.find()){
                res+=matcher.group(0)+System.getProperty("line.separator");
            }
            System.out.println(res);

        monitor = new PerformanceMonitor();
        System.out.println("Nb processor : "+monitor.availableProcessors);
        for(int i=0 ; i<10000 ; i++){
            start();
            double usage = monitor.getCpuUsage();
            if(usage!=0)System.out.println("Current CPU usage in pourcentage : "+usage);
        }
    }

    private static void start() {
        int count=0;
        for(int i=0 ; i<100000 ; i++){
           // count=(int) Math.random()*100;
        }
    }
}
