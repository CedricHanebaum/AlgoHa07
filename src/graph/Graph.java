package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	private ArrayList<ArrayList<Integer>> adjecentList;
	private ArrayList<Boolean> helper;

	public Graph(int v) {
		adjecentList = new ArrayList<ArrayList<Integer>>();
		helper = new ArrayList<Boolean>();
		for (int i = 0; i < v + 1; ++i) {
			adjecentList.add(new ArrayList<Integer>());
			helper.add(false);
		}
	}

	public Graph(int[] list) {
		adjecentList = new ArrayList<ArrayList<Integer>>();
		helper = new ArrayList<Boolean>();

		for (int i = 0; i < list[0] + 1; ++i) {
			adjecentList.add(new ArrayList<Integer>());
			helper.add(false);
		}

		for (int i = 2; i < list.length; i += 2) {
			adjecentList.get(list[i]).add(list[i + 1]);
		}
	}

	public int getVertexCount() {
		return adjecentList.size() - 1;
	}

	public int getEdgeCount() {
		int count = 0;
		for (int i = 1; i < adjecentList.size(); ++i) {
			count += adjecentList.get(i).size();
		}
		return count;
	}

	public void addEdge(int from, int to) {
		adjecentList.get(from).add(to);
	}

	public ArrayList<Integer> getAdjecent(int v) {
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (int i = 0; i < adjecentList.get(v).size(); ++i) {
			copy.add(adjecentList.get(v).get(i));
		}
		return copy;
	}

	@Override
	public String toString() {
		return adjecentList.toString();
	}

	public ArrayList<Integer> dfs(int start) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		this.initHelper();
//		helper.set(start, true);
		result.add(start);
		dfsHelper(start, result);
		return result;
	}

	private void dfsHelper(int start, ArrayList<Integer> al) {
		helper.set(start, true);
		for (int i = adjecentList.get(start).size() - 1; i >= 0; --i) {
			if (!helper.get(adjecentList.get(start).get(i))) {
				al.add(adjecentList.get(start).get(i));
				dfsHelper(adjecentList.get(start).get(i), al);
			}
		}
	}

	private void initHelper() {
		for (int i = 0; i < helper.size(); ++i) {
			this.helper.set(i, false);
		}
	}

	public ArrayList<Integer> bfs(int start) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		initHelper();
		q.add(start);
		result.add(start);
		helper.set(start, true);
		while (!q.isEmpty()) {
			int u = q.remove();
			for (int i = adjecentList.get(u).size() - 1; i >= 0; --i) {
				if (!helper.get(adjecentList.get(u).get(i))) {
					q.add(adjecentList.get(u).get(i));
					helper.set(adjecentList.get(u).get(i), true);
					result.add(adjecentList.get(u).get(i));
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] vlist = { 6, 10, 1, 5, 1, 4, 2, 3, 2, 6, 3, 4, 3, 5, 4, 5, 4, 2,
				5, 6, 6, 1 };
		Graph g = new Graph(vlist);
		System.out.println(g);
		System.out.println(g.dfs(1));
		System.out.println(g.bfs(1));
	}
}
