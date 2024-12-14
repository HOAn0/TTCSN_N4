package Bron_Kerbosch;

import java.util.*;

public class BronKerbosch {
    public static List<Set<Character>> findMaximalCliques(Map<Character, Set<Character>> graph) {
        // Khởi tạo tham số
        List<Set<Character>> cliques = new ArrayList<>();
        Set<Character> r = new HashSet<>(); // r rỗng
        Set<Character> p = new HashSet<>(graph.keySet()); // p là tập các đỉnh trong đồ thị
        Set<Character> x = new HashSet<>(); // x rỗng

        bronKerbosch(r, p, x, cliques, graph);
        return cliques;
    }

    private static void bronKerbosch(Set<Character> r, Set<Character> p, Set<Character> x,
                                      List<Set<Character>> cliques, Map<Character, Set<Character>> graph) {
        // Kiểm tra dừng khi p và x đồng thời rỗng
        if (p.isEmpty() && x.isEmpty()) {
            cliques.add(new HashSet<>(r)); // Thêm một bản sao của r
            return;
        }

        Set<Character> temp_p = new HashSet<>(p);
        for (Character c : temp_p) {
            Set<Character> r2 = new HashSet<>(r);
            r2.add(c); // Thêm đỉnh tiềm năng vào tệp các đỉnh đang xét

            Set<Character> neighbors = graph.getOrDefault(c, new HashSet<>());
            Set<Character> p2 = new HashSet<>(p);
            p2.retainAll(neighbors); // Cắt tỉa nhánh

            Set<Character> x2 = new HashSet<>(x);
            x2.retainAll(neighbors); // Cắt tỉa nhánh

            bronKerbosch(r2, p2, x2, cliques, graph); // Đệ quy

            p.remove(c); // Loại bỏ đỉnh vừa xét
            x.add(c); // Thêm đỉnh vào tập đã xét
        }
    }

    public static void main(String[] args) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        graph.put('A', Set.of('B', 'C'));
        graph.put('B', Set.of('A', 'C'));
        graph.put('C', Set.of('A', 'B', 'D'));
        graph.put('D', Set.of('C'));

        List<Set<Character>> cliques = BronKerbosch.findMaximalCliques(graph);
        System.out.println("Maximal Cliques: " + cliques);
    }
}

    

