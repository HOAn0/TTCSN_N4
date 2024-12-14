package BranchAndBound.SDHashMap&HashSet;

package cach2;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        // Chạy các trường hợp kiểm thử
        testEmptyGraph();
        testSingleNodeGraph();
        testTwoNodeGraph();
        testCompleteGraph();
        testBipartiteGraph();
        testComplexGraph();
        testRandomGraph(100, 500); // Đồ thị ngẫu nhiên với 100 đỉnh và 500 cạnh
        testLargeGraph(1000, 5000); // Đồ thị lớn với 1000 đỉnh và 5000 cạnh
    }

    /**
     * Kiểm thử với đồ thị rỗng
     */
    public static void testEmptyGraph() {
        BranchAndBound emptyGraph = new BranchAndBound();
        HashSet<HashSet<String>> cliquesEmpty = emptyGraph.branch_and_bound();
        System.out.println("Kiểm tra Đồ thị rỗng: ");
        System.out.println("Maximal clique =" + cliquesEmpty);
        System.out.println("------------------");
    }

    /**
     * Kiểm thử với đồ thị chỉ có một đỉnh
     */
    public static void testSingleNodeGraph() {
        BranchAndBound singleNodeGraph = new BranchAndBound();
        singleNodeGraph.addEdge("A", "A"); // Thêm một đỉnh đơn lẻ
        HashSet<HashSet<String>> cliquesSingleNode = singleNodeGraph.branch_and_bound();
        System.out.println("Kiểm tra đồ thị chỉ có một đỉnh:");
        System.out.println("Maximal clique =" + cliquesSingleNode);
        System.out.println("------------------");
    }

    /**
     * Kiểm thử với đồ thị có hai đỉnh và một cạnh
     */
    public static void testTwoNodeGraph() {
        BranchAndBound twoNodeGraph = new BranchAndBound();
        twoNodeGraph.addEdge("A", "B"); // Thêm cạnh nối A và B
        HashSet<HashSet<String>> cliquesTwoNodes = twoNodeGraph.branch_and_bound();
        System.out.println("Đồ thị có 2 đỉnh và 1 cạnh: " + cliquesTwoNodes);
        System.out.println("------------------");
    }

    /**
     * Kiểm thử với đồ thị đầy đủ (complete graph)
     */
    public static void testCompleteGraph() {
        BranchAndBound completeGraph = new BranchAndBound();
        completeGraph.addEdge("A", "B");
        completeGraph.addEdge("A", "C");
        completeGraph.addEdge("B", "C");
        HashSet<HashSet<String>> cliquesComplete = completeGraph.branch_and_bound();
        System.out.println("Đồ thị đầy đủ (complete graph): " + cliquesComplete);
        System.out.println("------------------");
    }

    /**
     * Kiểm thử với đồ thị lưỡng phân (bipartite graph)
     */
    public static void testBipartiteGraph() {
        BranchAndBound bipartiteGraph = new BranchAndBound();
        bipartiteGraph.addEdge("A", "B");
        bipartiteGraph.addEdge("A", "C");
        bipartiteGraph.addEdge("B", "D");
        HashSet<HashSet<String>> cliquesBipartite = bipartiteGraph.branch_and_bound();
        System.out.println("Đồ thị lưỡng phân (bipartite graph): " + cliquesBipartite);
        System.out.println("------------------");
    }

    /**
     * Kiểm thử với đồ thị phức tạp
     */
    public static void testComplexGraph() {
        BranchAndBound complexGraph = new BranchAndBound();
        complexGraph.addEdge("A", "B");
        complexGraph.addEdge("A", "K");
        complexGraph.addEdge("B", "I");
        complexGraph.addEdge("C", "D");
        complexGraph.addEdge("C", "I");
        complexGraph.addEdge("D", "I");
        complexGraph.addEdge("D", "G");
        complexGraph.addEdge("D", "E");
        complexGraph.addEdge("E", "G");
        complexGraph.addEdge("E", "F");
        complexGraph.addEdge("F", "G");
        complexGraph.addEdge("G", "I");
        complexGraph.addEdge("G", "H");
        complexGraph.addEdge("H", "I");
        complexGraph.addEdge("H", "K");
        complexGraph.addEdge("I", "K");
        HashSet<HashSet<String>> cliquesComplex = complexGraph.branch_and_bound();
        System.out.println("Đồ thị phức tạp: " + cliquesComplex);
        System.out.println("------------------");
    }

    /**
     * Kiểm thử với đồ thị ngẫu nhiên
     * @param numVertices Số lượng đỉnh
     * @param numEdges Số lượng cạnh
     */
    private static void testRandomGraph(int numVertices, int numEdges) {
        BranchAndBound randomGraph = new BranchAndBound();
        Random rand = new Random();

        for (int i = 0; i < numVertices; i++) {
            String vertex = "V" + i;
            randomGraph.addEdge(vertex, vertex); // Đảm bảo các đỉnh được thêm vào đồ thị
        }

        for (int i = 0; i < numEdges; i++) {
            String u = "V" + rand.nextInt(numVertices);
            String v = "V" + rand.nextInt(numVertices);
            if (!u.equals(v)) {
                randomGraph.addEdge(u, v);
            }
        }

        long startTime = System.currentTimeMillis();
        HashSet<HashSet<String>> cliques = randomGraph.branch_and_bound();
        long endTime = System.currentTimeMillis();

        System.out.println("\nĐồ thị ngẫu nhiên với " + numVertices + " đỉnh và " + numEdges + " cạnh:");
        System.out.println("Số cliques tối đa: " + cliques.size());
        System.out.println("Thời gian thực thi (ms): " + (endTime - startTime));
        System.out.println("------------------");
}

    /**
     * Kiểm thử với đồ thị lớn
     * @param numVertices Số lượng đỉnh
     * @param numEdges Số lượng cạnh
     */
    private static void testLargeGraph(int numVertices, int numEdges) {
        BranchAndBound largeGraph = new BranchAndBound();
        Random rand = new Random();

        for (int i = 0; i < numVertices; i++) {
            String vertex = "V" + i;
            largeGraph.addEdge(vertex, vertex);
        }

        for (int i = 0; i < numEdges; i++) {
            String u = "V" + rand.nextInt(numVertices);
            String v = "V" + rand.nextInt(numVertices);
            if (!u.equals(v)) {
                largeGraph.addEdge(u, v);
            }
        }

        long startTime = System.currentTimeMillis();
        HashSet<HashSet<String>> cliques = largeGraph.branch_and_bound();
        long endTime = System.currentTimeMillis();

        System.out.println("\nĐồ thị lớn với " + numVertices + " đỉnh và " + numEdges + " cạnh:");
        System.out.println("Số cliques tối đa: " + cliques.size());
        System.out.println("Thời gian thực thi (ms): " + (endTime - startTime));
    }
}

