

import java.util.Arrays;

public class QuickFindUnionUsingTree {
	int []id;
	
	QuickFindUnionUsingTree(int n){
		id = new int[n];
		for(int i=0;i<n;i++){
			id[i] =i;
		}
	}
	
	private int getRoot(int node){
		
		while(node!=id[node]){
			node = id[node];
		}
		return node;
	}
	
	public boolean isConnected(int p, int q){
		return getRoot(p) == getRoot(q);
	}
	
	public void union(int p, int q){
		int child = getRoot(p);
		int parent = getRoot(q);
		
		id[child] = parent;
		
	}
	
	
	public static void main(String [] args){
		QuickFindUnionUsingTree tree = new QuickFindUnionUsingTree(10);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union(4, 3);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union( 3,8);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union(6,5);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union(9,4);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union(2,1);
		
		System.out.println(Arrays.toString(tree.id));
		
		System.out.println(tree.isConnected(8,9));
		
		System.out.println(tree.isConnected(5,4));
		
		tree.union(5,0);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union(7,2);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union(6,2);
		
		System.out.println(Arrays.toString(tree.id));
		
		tree.union(7,3);
		
		System.out.println(Arrays.toString(tree.id));
	}

}
