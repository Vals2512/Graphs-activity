package rutas.runner;

import rutas.model.TransportGraph;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.setProperty("org.graphstream.ui", "swing"); 

        TransportGraph graph = new TransportGraph();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Transport Network System!");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Node");
            System.out.println("2. Add Edge");
            System.out.println("3. Find Shortest Path");
            System.out.println("4. Exit");
            System.out.print("Option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter node name: ");
                    String node = scanner.nextLine();
                    if (!node.isEmpty()) {
                        graph.addNode(node);
                        System.out.println("Node " + node + " added successfully!");
                    } else {
                        System.out.println("Node name cannot be empty.");
                    }
                    break;

                case "2":
                    System.out.print("Enter source node: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter target node: ");
                    String target = scanner.nextLine();
                    System.out.print("Enter edge weight: ");
                    String weightInput = scanner.nextLine();
                    try {
                        double weight = Double.parseDouble(weightInput);
                        graph.addNode(source);
                        graph.addNode(target);
                        graph.addEdge(source + "-" + target, source, target, weight);
                        System.out.println("Edge added successfully!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid weight. Please enter a valid number.");
                    }
                    break;

                case "3":
                    System.out.print("Enter start node: ");
                    String startNode = scanner.nextLine();
                    System.out.print("Enter end node: ");
                    String endNode = scanner.nextLine();
                    if (graph.getGraph().getNode(startNode) != null && graph.getGraph().getNode(endNode) != null) {
                        String result = graph.findShortestPath(startNode, endNode);
                        System.out.println(result);
                    } else {
                        System.out.println("One or both nodes do not exist.");
                    }
                    break;

                case "4":
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
