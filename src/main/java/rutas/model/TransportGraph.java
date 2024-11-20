package rutas.model;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.Dijkstra;

public class TransportGraph {
    private Graph graph;

    public TransportGraph() {
        graph = new SingleGraph("Transport Network");
        graph.display();
    }

    public void addNode(String id) {
        if (graph.getNode(id) == null) {
            Node node = graph.addNode(id);
            node.setAttribute("ui.label", id + " \uD83D\uDE8D");
            node.setAttribute("ui.style", "fill-color: blue; size: 20px; text-size: 20px;");
        }
    }

    public void addEdge(String id, String source, String target, double weight) {
        if (graph.getEdge(id) == null) {
            Edge edge = graph.addEdge(id, source, target, true);
            edge.setAttribute("weight", weight);
            edge.setAttribute("ui.style", "fill-color: gray; size: 2px; text-size: 20px;");

            boolean isReverse = graph.getEdge(target + "-" + source) != null;

            if (isReverse) {
                // Reverse
                edge.setAttribute("ui.label", "       - " + weight);
            } else {
                // Normal
                edge.setAttribute("ui.label", weight);
            }
        }
    }

    public String findShortestPath(String start, String end) {
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode(start));
        dijkstra.compute();

        return "Distance: " + dijkstra.getPathLength(graph.getNode(end)) +
               "\nPath: " + dijkstra.getPath(graph.getNode(end));
    }

    public Graph getGraph() {
        return graph;
    }
}
