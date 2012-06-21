/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra_memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Alain Janin-Manificat <alain.janinm@hotmail.fr>
 */
public class Graph {

    final int size;
    private Map<String,Vertex> vertex;
   
    public Graph(int size){
        vertex = new HashMap<>(size);
        this.size=size;
    }
    
    public void addVertex(String label) {
        vertex.put(label,new Vertex(label,size));
    }

    public void addEdge(String v1, String v2, int weight) {
        vertex.get(v1).addNeighbour(vertex.get(v2), weight);
    }

    public ArrayList<String> resolveDijkstra(String source, String end) {
        
        final Vertex endV = vertex.get(end);
        vertex.get(source).setMinDistanceToSource(0);
        
        for (int i=0; i<vertex.size(); i++) {
            final Vertex next = minVertex();
            if(next==endV)break;
            next.setVisited();
            
            final Map<Vertex, Integer> neighbours = next.getNeighbours();
            for (Entry<Vertex,Integer> e : neighbours.entrySet()) {
                final Vertex v = e.getKey();
                final int distance = next.getMinDistanceToSource() + e.getValue();
                if (v.getMinDistanceToSource() > distance) {
                    v.setMinDistanceToSource(distance);
                    v.setPreviousVertex(next);
                }
            }
        }
        ArrayList<String> res = new ArrayList<>();
        res.add(endV.getLabel());
        Vertex previous =endV;
        while((previous = previous.getPreviousVertex())!=null){
            res.add(previous.getLabel());
        }      
        
        return res;
        
    }

    //vertex in graph with smallest distance to source ;
    private Vertex minVertex() {
        int x = Integer.MAX_VALUE;
        Vertex y = null;   // graph not connected, or no unvisited vertex
        for(Vertex v : vertex.values()){
            if (!v.isVisited() && v.getMinDistanceToSource()<x) {y=v; x=v.getMinDistanceToSource();}
        }
        return y;
    }
}
