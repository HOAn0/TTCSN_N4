package BranchAndBound.SDJGraphT;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;

public class BranchAndBound {
    public static void findMaximalCliques(Graph<Integer, DefaultEdge> graph) {
        Set<Integer> candidates = new HashSet<>(graph.vertexSet());
        Set<Integer> currentClique = new HashSet<>();
        Set<Integer> excluded = new HashSet<>();

        findMaximalCliquesRecursive(graph, candidates, currentClique, excluded);
    }

    private static void findMaximalCliquesRecursive(Graph<Integer, DefaultEdge> graph, Set<Integer> candidates, Set<Integer> currentClique, Set<Integer> excluded) {
        if (candidates.isEmpty() && excluded.isEmpty()) {
            // In ra một maximal clique
            System.out.println("Maximal Clique: " + currentClique);
            return;
        }

        Set<Integer> candidatesToExplore = new HashSet<>(candidates);
        for (int v : candidatesToExplore) {
            currentClique.add(v);
            Set<Integer> newCandidates = new HashSet<>(candidates);
            newCandidates.retainAll(neighborsOf(graph, v));

            Set<Integer> newExcluded = new HashSet<>(excluded);
            newExcluded.retainAll(neighborsOf(graph, v));

            findMaximalCliquesRecursive(graph, newCandidates, currentClique, newExcluded);

            currentClique.remove(v);
            candidates.remove(v);
            excluded.add(v);
        }
    }

    private static Set<Integer> neighborsOf(Graph<Integer, DefaultEdge> graph, int vertex) {
        Set<Integer> neighbors = new HashSet<>();
        for (DefaultEdge edge : graph.edgesOf(vertex)) {
            neighbors.add(graph.getEdgeSource(edge).equals(vertex) ? graph.getEdgeTarget(edge) : graph.getEdgeSource(edge));
        }
        return neighbors;
    }
}