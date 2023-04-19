import java.util.HashMap;
import java.util.Map;

/**
 * The Node class represents a location in a graph.
 */
public class Node {
    /**
     * The name of the node(location).
     */
    private final String name;

    /**
     * The map of all the neighbor nodes and the distances to them.
     */
    private final Map<Node, Integer> neighborNodes;

    /**
     * The latitude of the node(location).
     */
    private final double latitude;

    /**
     * The longitude of the node(location).
     */
    private final double longitude;

    /**
     * Constructs a new Node object with the given name, latitude, longitude,
     * and an empty map with the initial capacity of the number of neighbors.
     * 
     * @param name      the name of the node(location).
     * @param latitude  the latitude of the node(location).
     * @param longitude the longitude of the node(location).
     * @param neighbors the number of neighbors the node has.
     */
    public Node(String name, double latitude, double longitude, int neighbors) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        neighborNodes = new HashMap<>(neighbors);
    }

//  NEVER USED
//    /**
//     * Constructs a new Node object with the given name, latitude, longitude,
//     * and map of neighbor nodes.
//     *
//     * @param name          the name of the node(location).
//     * @param latitude      the latitude of the node(location).
//     * @param longitude     the longitude of the node(location).
//     * @param neighborNodes the map of all the neighbor nodes and their distances.
//     */
//    public Node(String name, double latitude, double longitude, Map<Node, Integer> neighborNodes) {
//        this.name = name;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.neighborNodes = new HashMap<>(neighborNodes.size());
//        for (Map.Entry<Node, Integer> e : neighborNodes.entrySet()) {
//            this.addNeighbor(e.getKey(), e.getValue());
//        }
//    }

    /**
     * Returns the name of this node(location).
     * 
     * @return the name of this node.
     */
    public String getName() {
        return name;
    }

//  NEVER USED
//    /**
//     * Returns the latitude of this node(location).
//     *
//     * @return the latitude of this node.
//     */
//    public double getLatitude() {
//        return latitude;
//    }

//  NEVER USED
//    /**
//     * Returns the longitude of this node(location).
//     *
//     * @return the longitude of this node.
//     */
//    public double getLongitude() {
//        return longitude;
//    }

    /**
     * The f-score of this node, which is the sum of the g-score and h-score.
     */
    private double f;

    /**
     * The g-score of this node, which represents the cost of the path from the
     * start node to this node.
     */
    private double g;

    /**
     * The h-score of this node, which represents the estimated cost of the path
     * from this node to the goal node.
     */
    private double h;

    /**
     * The node that this node came from in the A* algorithm.
     */
    private Node cameFrom;

    /**
     * Gets the f-score of this node, which is the sum of the g-score and h-score.
     * 
     * @return the f-score of this node.
     */
    public double getF() {
        return f;
    }

    /**
     * Sets the f-score of this node, which is the sum of the g-score and h-score.
     * 
     * @param f the new f-score of this node.
     */
    public void setF(double f) {
        this.f = f;
    }

    /**
     * Gets the g-score of this node, which represents the cost of the path from the
     * start node to this node.
     * 
     * @return the g-score of this node.
     */
    public double getG() {
        return g;
    }

    /**
     * Sets the g-score of this node, which represents the cost of the path from the
     * start node to this node.
     * 
     * @param g the new g-score of this node.
     */
    public void setG(double g) {
        this.g = g;
    }

    /**
     * Gets the h-score of this node, which represents the estimated cost of the
     * path from this node to the goal node.
     * 
     * @return the h-score of this node.
     */
    public double getH() {
        return h;
    }

    /**
     * Sets the h-score of this node, which represents the estimated cost of the
     * path from this node to the goal node.
     * 
     * @param h the new h-score of this node.
     */
    public void setH(double h) {
        this.h = h;
    }

    /**
     * Gets the node that this node came from in the A* algorithm.
     * 
     * @return the node that this node came from.
     */
    public Node getCameFrom() {
        return cameFrom;
    }

    /**
     * Sets the node that this node came from in the A* algorithm.
     * 
     * @param cameFrom the node that this node came from.
     */
    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * Adds neighbor nodes to the map that keeps track of all the neighbors to this
     * node.
     * 
     * @param otherNode the node to be added.
     * @param distance  the distance from the node.
     * @return 1 if the otherNode is added, 0 if the node already is in the neighbors map.
     */
    public int addNeighbor(Node otherNode, int distance) {
        if (!neighborNodes.containsKey(otherNode)) {
            neighborNodes.put(otherNode, distance);
            otherNode.addNeighbor(this, distance);
            return 1;
        }
        return 0;
    }

    /**
     * Returns the map with all the neighbor nodes of this node.
     * 
     * @return map of the neighbors of this node.
     */
    public Map<Node, Integer> getNeighbors() {
        Map<Node, Integer> newNeighborNodes = new HashMap<>(neighborNodes.size());
        newNeighborNodes.putAll(neighborNodes);
        return newNeighborNodes;
    }

    /**
     * Calculates the air distance from one node to the other.
     * 
     * @param otherNode the node to which the air distance will be calculated.
     * @return the air distance from this node to the other node.
     */
    public double calculateAirDistance(Node otherNode) {
        return Math.acos(Math.sin(Math.toRadians(this.latitude)) * Math.sin(Math.toRadians(otherNode.latitude))
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(otherNode.latitude))
                    * Math.cos(Math.toRadians(otherNode.longitude) - Math.toRadians(this.longitude)))
                        * 6371;
    }
}