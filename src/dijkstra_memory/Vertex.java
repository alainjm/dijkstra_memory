/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra_memory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alain Janin-Manificat <alain.janinm@hotmail.fr>
 */
public class Vertex {
    private Map<Vertex, Integer> neighbours;
    private int minDistToSource=Integer.MAX_VALUE;
    private boolean visited=false;
    private Vertex previousVertex=null;
    private final String label;
    
    public Vertex(String label, int maxNeighbours) {
        this.label=label;
        neighbours=new HashMap<>(maxNeighbours);
    }
    
    public String getLabel(){
        return label;
    }
    
    public void addNeighbour(Vertex label, int weight){
        neighbours.put(label, weight);
    }
    
    public Map<Vertex, Integer> getNeighbours(){
        return neighbours;
    }

    public void setMinDistanceToSource(int dist) {
        minDistToSource=dist;
    }

    public int getMinDistanceToSource() {
        return minDistToSource;
    }
    
    public boolean isVisited(){
        return visited;
    }
    
    public void setVisited(){
        visited=true;
    }
    
    public void setPreviousVertex(Vertex v){
        previousVertex=v;
    }
    
    public Vertex getPreviousVertex(){
        return previousVertex;
    }
    
}
