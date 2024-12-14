package BranchAndBound.SDHashMapHashSet;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class BranchAndBound {
	private HashMap<String, HashSet<String>> graph; // Danh sách kề lưu đỉnh và các đỉnh kề
	private HashSet<HashSet<String>> allCliques; // Danh sách các clique cực đại (HashSet để tránh trùng lặp)

	// Hàm Khởi tạo
	public BranchAndBound() {
	        graph = new HashMap<>();		//Dùng HashMap để lưu đồ thị
	        allCliques = new HashSet<>();  // Dùng HashSet để lưu các clique cực đại
	    }

	// Hàm thêm cạnh vào đồ thị
	public void addEdge(String u, String v) {
	    if (u.equals(v)) return; // Bỏ qua cạnh tự lặp
	    graph.putIfAbsent(u, new HashSet<>());
	    graph.putIfAbsent(v, new HashSet<>());
	    graph.get(u).add(v);
	    graph.get(v).add(u);
	}

	// Hàm lấy các đỉnh kề của một đỉnh
	private HashSet<String> getNeighbors(String v) {
		return graph.getOrDefault(v, new HashSet<>());
	}

	// Hàm Branch and Bound để tìm clique cực đại
	private void BNB(HashSet<String> currentClique, HashSet<String> candidates) {
		if (candidates.isEmpty()) {
			if (!currentClique.isEmpty()) {
				// Thêm currentClique vào allCliques
				allCliques.add(new HashSet<>(currentClique)); // Dùng HashSet để tự động loại bỏ trùng lặp
			}
			return;
		}
		// Duyệt qua các đỉnh trong candidates
		for (String v : candidates) {
			HashSet<String> newCurrentClique = new HashSet<>(currentClique); // Sao chép currentClique vào
																				// newCurrentClique
			newCurrentClique.add(v); // Thêm đỉnh v vào newCurrentClique
			HashSet<String> newCandidates = new HashSet<>(candidates); // Sao chép candidates vào newCandidates
			newCandidates.retainAll(getNeighbors(v)); // Tìm giao của newCandidates với các đỉnh kề của v
			BNB(newCurrentClique, newCandidates); // Gọi đệ quy
		}
	}

	public HashSet<HashSet<String>> branch_and_bound() {
		allCliques.clear(); // Xóa danh sách clique trước khi tìm kiếm
		HashSet<String> currentClique = new HashSet<>(); // Clique hiện tại
		HashSet<String> candidates = new HashSet<>(graph.keySet()); // Danh sách các đỉnh có thể thêm vào clique
		BNB(currentClique, candidates); // Bắt đầu tìm kiếm
		return allCliques; // Trả về danh sách các clique cực đại
	}
}
