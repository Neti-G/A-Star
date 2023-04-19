import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * The AStarAlgorithm class implements the A* algorithm for finding the shortest path between two nodes in a graph.
 */
public class AStarAlgorithm {

    /**
     * Finds the shortest path between the start node and the goal node using the A* algorithm.
     * @param start the starting node.
     * @param goal the goal node.
     * @return an ArrayList of nodes representing the shortest path from the start node to the goal node.
     * Returns null if no path is found.
     */
    public ArrayList<Node> AStar(Node start, Node goal){
        PriorityQueue<Node> openSet = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return Double.compare(n1.getF(), n2.getF());
            }
        });
        HashSet<Node> closedSet = new HashSet<Node>();
    
        start.setG(0);
        start.setH(start.calculateAirDistance(goal));
        start.setF(start.getG() + start.getH());
        openSet.add(start);
    
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.equals(goal)) {
                ArrayList<Node> path = new ArrayList<Node>();
                Node node = current;
                while (node != null) {
                    path.add(node);
                    node = node.getCameFrom();
                }
                Collections.reverse(path);
                return path;
            }
            closedSet.add(current);
            for (Map.Entry<Node, Integer> neighborEntry : current.getNeighbors().entrySet()) {
                Node neighbor = neighborEntry.getKey();
                int distance = neighborEntry.getValue();
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                double newG = current.getG() + distance;
                double newF = newG + neighbor.calculateAirDistance(goal);
                if (!openSet.contains(neighbor) || newF < neighbor.getF()) {
                    neighbor.setCameFrom(current);
                    neighbor.setG(newG);
                    neighbor.setH(neighbor.calculateAirDistance(goal));
                    neighbor.setF(newF);
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return null;
    }
}
