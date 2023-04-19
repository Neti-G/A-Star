import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AStarAlgorithm AS = new AStarAlgorithm();
        Node Prishtina = new Node("Prishtina", 42.66346948260736, 21.164906106923294, 4);
        Node Gjilani = new Node("Gjilani", 42.464595512461514, 21.468965082478658, 2);
        Node Ferizaji = new Node("Ferizaji", 42.37435700225342, 21.14173856316859, 3);
        Node Prizreni = new Node("Prizreni", 42.22045350777072, 20.74105094521177, 4);
        Node Gjakova = new Node("Gjakova", 42.38187955382086, 20.426946602821765, 2);
        Node Peja = new Node("Peja", 42.66098482131376, 20.291994479683297, 3);
        Node Mitrovica = new Node("Mitrovica", 42.889254227002226, 20.84970766735983, 2);
        
        Prishtina.addNeighbor(Gjilani, 49);
        Prishtina.addNeighbor(Mitrovica, 41);
        Prishtina.addNeighbor(Ferizaji, 42);
        Prishtina.addNeighbor(Prizreni, 85);
        Gjilani.addNeighbor(Ferizaji, 34);
        Ferizaji.addNeighbor(Prizreni, 64);
        Prizreni.addNeighbor(Gjakova, 37);
        Prizreni.addNeighbor(Peja, 97);
        Gjakova.addNeighbor(Peja, 35);
        Peja.addNeighbor(Mitrovica, 70);

        ArrayList<Node> graph = new ArrayList<>();
        graph.add(Prishtina);
        graph.add(Gjilani);
        graph.add(Ferizaji);
        graph.add(Ferizaji);
        graph.add(Prizreni);
        graph.add(Gjakova);
        graph.add(Peja);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting location:");
        for (int i = 0; i < graph.size(); i++) {
            System.out.print(i+1+". "+graph.get(i).getName()+"  ");
        }
        System.out.println();
        int start = sc.nextInt()-1;

        System.out.println("Enter the destination:");
        for (int i = 0; i < graph.size(); i++) {
            System.out.print(i+1+". "+graph.get(i).getName()+"  ");
        }
        System.out.println();
        int goal = sc.nextInt()-1;
        
        sc.close();

        System.out.println();
        System.out.println("The shortest route is:");
        ArrayList<Node> path = AS.AStar(graph.get(start), graph.get(goal));
        for (int i = 0; i < path.size()-1; i++) {
            System.out.print(path.get(i).getName()+" => ");
        }
        System.out.println(path.get(path.size()-1).getName());
    }
}