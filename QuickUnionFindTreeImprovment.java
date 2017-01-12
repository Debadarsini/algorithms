
import java.util.Arrays;

public class QuickUnionFindTreeImprovment {

	int[] id;
	int [] sz;

	QuickUnionFindTreeImprovment(int n){
		id = new int[n];
		sz = new int[n];
		for(int i=0;i<n;i++){
			id[i] =i;
			sz[i]=1;
		}
	}
	
	//private int find(int i){
		
	//}

	private int getRoot(int node) {
		while (node != id[node]) {
			id[node]=id[id[node]];//this flatens the tree
			node = id[node];
		}
		return node;
	}

	public boolean isConnected(int p, int q) {
		return getRoot(p) == getRoot(q);
	}

	public void union(int p, int q) {
		int idp = getRoot(p);
		int idq = getRoot(q);
		if( idp == idq){
			return;
		}
		System.out.println("size of p "+sz[idp] + "size of q " +sz[idq]);
		if(sz[idp]<sz[idq]){
			id[idp] = idq;
			sz[idq] +=sz[idp];
		}else{
			id[idq] = idp;
			sz[idp] +=sz[idq];
		}
			
	//	id[child] = parent;

	}

	public static void main(String[] args) {
		QuickUnionFindTreeImprovment tree = new QuickUnionFindTreeImprovment(10);

		System.out.println(Arrays.toString(tree.id));
		
	
		tree.union(4, 3);

		System.out.println("tree.union(4, 3)");

		System.out.println(Arrays.toString(tree.id));

		tree.union(3, 8);

		System.out.println("tree.union(3, 8)");

		System.out.println(Arrays.toString(tree.id));

		tree.union(6, 5);
		
		System.out.println("tree.union(6, 5)");


		System.out.println(Arrays.toString(tree.id));

		tree.union(9, 4);

		System.out.println("tree.union(9, 4)");

		System.out.println(Arrays.toString(tree.id));

		tree.union(2, 1);

		System.out.println("tree.union(2, 1)");

		System.out.println(Arrays.toString(tree.id));

		System.out.println(tree.isConnected(8, 9));

		
		System.out.println(tree.isConnected(5, 4));

		tree.union(5, 0);

		System.out.println("tree.union(5, 0)");

		System.out.println(Arrays.toString(tree.id));

		tree.union(7, 2);

		System.out.println("tree.union(7, 2)");

		System.out.println(Arrays.toString(tree.id));

		tree.union(6, 2);

		System.out.println("tree.union(6, 2)");

		System.out.println(Arrays.toString(tree.id));

		tree.union(7, 3);

		System.out.println("tree.union(7, 3)");

		System.out.println(Arrays.toString(tree.id));
	}

}
