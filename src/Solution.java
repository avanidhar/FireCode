import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  
	
	class ListNode {
	     public int val;
	     public ListNode next;
	     ListNode(int x) { val = x; next = null; }
	 }
	
public class Solution {
	public int isSameTree(TreeNode a, TreeNode b) {
	    if((a==null)&&(b==null))
	    {
	        return 1;
	    }
	    
	    else if ((a != null)&&(b != null))
	    {
	        if((a.val == b. val)
	        && (isSameTree(a.left, b.left) == 1)
	        && (isSameTree(a.right, b.right) == 1))
	        return 1;
	    }

	    return 0;
	}
	
	public int findMax(TreeNode root) {
	    
	    if(root == null) return 0;
	    
	    int leftMax = 0, rightMax = 0, rootVal = root.val;
	    
	    leftMax = findMax(root.left);
	    rightMax = findMax(root.right);
	    
	    return Math.max(rootVal, Math.max(leftMax, rightMax));    
	}
	
	public TreeNode findNode(TreeNode root, int val){
		if(root.val == val) return root;
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		
		q.add(root);
		
		while(!q.isEmpty()){
			TreeNode qval = (TreeNode) q.remove();
			if(qval.val == val) return qval;
			if(qval.left != null)q.add(qval.left);
			if(qval.right != null)q.add(qval.right);
		}
		
		return null;
	}
	
	
	public static ListNode detectCycle(ListNode a) {
	    
		if(a==null || a.next == null) return a;
		
		ListNode slow = a;
		ListNode fast = a;
		boolean hasCycle = false;
		
		while(fast!=null && fast.next!= null){
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast){
				hasCycle = true;
				break;
			}
		}
		
		if(hasCycle){
			slow = a;
			while(slow!=fast){
				slow = slow.next;
				fast = fast.next;
			}
			
			return slow;
		}
		
		return null;
	}
	
	public static int[] merge(int[] arrLeft, int[] arrRight){
		if(arrLeft.length == 0) return arrRight;
		if(arrRight.length == 0) return arrLeft;
		
		int r = arrRight.length;
		int l = arrLeft.length;
		
		int mLength = r + l;
		int[] merged = new int[mLength];
		int i =0, j =0, k =0;
		
		while(i<l && j < r){
			if(arrLeft[i] <= arrRight[j]){
				merged[k++] = arrLeft[i++];
			}
			else{
				merged[k++] = arrRight[j++];
			}
		}
		
		while(i<l){
			merged[k++] = arrLeft[i++];
		}
		
		while(j<r){
			merged[k++] = arrRight[j++];
		}
			
		return merged;
	}
	
	public static boolean isHappyNumber(int n){
		if(n<=0) return false;
		
		while(n>9){
			n = getDigitSum(n);
		}
		
		return n==1;
	}
	
	public static int getDigitSum(int n){
		int sum = 0;
		
		while(n != 0){
			int digit = n%10;
			sum += digit*digit;
			n /= 10;
		}
		
		return sum;
	}
	
	public int[] diameterAndHeight(TreeNode root) {
		int heightDiameter[] = { 0, 0 };          // initialize the diameter and height 
		if (root != null) {
			int[] leftResult = diameterAndHeight(root.left);
			int[] rightResult = diameterAndHeight(root.right);
			int height = Math.max(leftResult[1], rightResult[1]) + 1;
			int leftDiameter = leftResult[0];
			int rightDiameter = rightResult[0];
			int rootDiameter = leftResult[1] + rightResult[1] + 1;
			int finalDiameter = Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
			heightDiameter[0] = finalDiameter; 
			heightDiameter[1] = height;
		} 
		return heightDiameter;
}
	
public int diameter(TreeNode root) {
    int[] result = diameterAndHeight(root);
    return result[0];
}

	public static int lengthOfLastWord(String s){
		int len = s.length();
		int idx = len -1;
		while(idx >= 0 && s.charAt(idx) == ' '){
			idx--;
		}
		
		int endIndex = idx;
		
		while(idx >= 0 && s.charAt(idx)!= ' '){
			idx--;
		}
		
		return endIndex - idx;
	}
	
	public static ArrayList<String> combParenthesis(int pairs) {
	    ArrayList<String> result = new ArrayList<String>();
	    StringBuffer sb = new StringBuffer();
	    parenHelper(2*pairs, 0, result, sb);
	    return result;
	}
	
	public static void parenHelper(int remainingCharacters, int unmatchedLeftParens, ArrayList<String> result, StringBuffer sb){
		if(remainingCharacters == 0){
			result.add(sb.toString());
		}
		
		if(unmatchedLeftParens < remainingCharacters){
			sb.append('(');
			parenHelper(remainingCharacters -1, unmatchedLeftParens + 1, result, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
		
		if(unmatchedLeftParens > 0){
			sb.append(')');
			parenHelper(remainingCharacters - 1, unmatchedLeftParens - 1, result, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	public static int maxSumPath(TreeNode root) {
		int[] maxSum = new int[1];
		maxSum[0] = Integer.MIN_VALUE;
		maxSumPathHelper(root, maxSum);
		return maxSum[0];
	}
	
	public static int maxSumPathHelper(TreeNode root, int[] maxSoFar){
		
		if(root == null) return 0;
		
		int lSum = maxSumPathHelper(root.left, maxSoFar);
		int rSum = maxSumPathHelper(root.right, maxSoFar);
		
		int maxAtNode = Math.max(lSum, rSum) + root.val;
		maxSoFar[0] = Math.max(maxSoFar[0], root.val + lSum + rSum);
		return maxAtNode;
	}
	
	public static int maxProfit(int[] a) {
		   if(a.length == 0) return 0;
			    
			    int maxProfit = 0;
			    int maxProfitSoFar = 0;
			    int length = a.length;
			    int start = 0;
			    
			    for(int i = 1; i<length;i++){
			    	if(a[i] > a[start]){
			    		maxProfitSoFar = Math.max(maxProfitSoFar, a[i] - a[start]);
			    	}
			    	else{
			    		maxProfit += maxProfitSoFar;
			    		start = i;
			    		maxProfitSoFar = 0;
			    	}
			    }
			    
			    return maxProfit + maxProfitSoFar;
		}
	
	public String serializeTree(TreeNode root){
	    StringBuffer sb = new StringBuffer();
	    serializeHelper(root, sb);
	    sb.deleteCharAt(sb.length() - 1);
	    return sb.toString();
	}
	
	public void serializeHelper(TreeNode root, StringBuffer sb){
		if(root == null) 
		{
			sb.append("null,");
			return;
		}
		
		sb.append(root.val).append(',');
		serializeHelper(root.left, sb);
		serializeHelper(root.right, sb);
	}

	public TreeNode restoreTree(String str){
		if(str == null || str.isEmpty()) return null;
		
		String[] result = str.split(",");
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(result));
		
		return restoreHelper(list);
	}
	
	public TreeNode restoreHelper(LinkedList<String> input)
	{
		String val = input.remove();
		if(val.equals("null")) return null;
		TreeNode t = new TreeNode(Integer.valueOf(val));
		t.left = restoreHelper(input);
		t.right = restoreHelper(input);
		return t;
	}
	public static int countPaths(int m, int n){
	    int[][] matrix = new int[m][n];
	    
	    for(int i = 0; i < m; i++){
	    	matrix[i][0] = 1;
	    }
	    
	    for(int j = 0; j < n; j++){
	    	matrix[0][j] = 1;
	    }
	    
	    for(int i = 1; i < m; i++){
	    	for(int j = 1; j < n; j++){
	    		matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
	    	}
	    }
	    
	    return matrix[m-1][n-1];
	}
	
	public static ArrayList<String> printPaths(char[][] board){
	    ArrayList<String> result = new ArrayList<String>();
	    int rows = board.length;
	    int columns = board[0].length;
	    StringBuffer sb = new StringBuffer();
	    printPathsHelper(board, 0, 0, sb, result, rows, columns);
	    return result;
	}
	
	public static void printPathsHelper(char[][] board, int row, int col, StringBuffer sb, ArrayList<String> result, int rows, int columns)
	{
		if(row == rows -1 && col == columns - 1){
			sb.append(board[row][col]);
			result.add(sb.toString());
			sb.deleteCharAt(sb.length() - 1);
		}
		
		if(isSafe(row, col, rows, columns)){
			sb.append(board[row][col]);
			printPathsHelper(board, row + 1, col, sb, result, rows, columns);
			printPathsHelper(board, row, col + 1, sb, result, rows, columns);
			sb.deleteCharAt(sb.length() - 1);
		}
		
	}
	
	public static boolean isSafe(int row, int col, int rows, int columns){
		return row>=0 && row<rows && col>=0 && col < columns;
	}
	
}