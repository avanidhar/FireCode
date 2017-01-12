import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Interval {
    int start;
    int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
   }
    
    
public class FireCode {
	
	
	public TreeNode decompressTree(String str){
	    if(str == null || str.equals("") || str.equals("*")) return null;
	    else {
	        ArrayList<Integer> nodes = new ArrayList<>();
	        String[] splits = str.split(",");
	        for(String s: splits){
	            Integer i = s.equals("*") ? null : Integer.valueOf(s);
	            nodes.add(i);
	        }
	        Queue<TreeNode> queue = new LinkedList<>();
	        int i = 0, size = nodes.size();
	        TreeNode root = new TreeNode(nodes.get(0));
	        queue.add(root);
	        while(i < nodes.size()){
	            TreeNode t = queue.poll();
	            if(t == null){
	                i+=2;
	                continue;
	            } else {
	                Integer leftVal = i+1 < size ? nodes.get(i+1) : null;
	                Integer rightVal = i+2 < size ? nodes.get(i+2) : null;
	                TreeNode leftChild = leftVal != null ? new TreeNode(leftVal) : null;
	                TreeNode rightChild = rightVal != null ? new TreeNode(rightVal) : null;
	                t.left = leftChild; t.right = rightChild;
	                queue.add(leftChild);
	                queue.add(rightChild);
	                i+=2;
	            }
	        }
	        return root;
	    }
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
	
	public static TreeNode delete(TreeNode root, int data) {                    
        if(root == null) { 
            return null;
        } else if(data < root.val) {
            root.left = delete(root.left, data);
        } else if(data > root.val) {
            root.right = delete(root.right, data);
        } else { //element found
            if(root.left != null && root.right != null) { //full node case
                //root.val = findMin(root.right).data;    
                root.right = delete(root.right, root.val);
            } else if(root.left == null && root.right == null) {
                root = null;
            }  
            else if(root.left == null) {
                root = root.right;
            } else if(root.right == null) {
                root = root.left;                
            }                
        }
        return root;
    }
	
	public int editDistance(String a, String b){
	    
	    int alen = a.length();
	    int blen = b.length();
	    int[][] result = new int[alen+1][blen+1];
	    
	    for(int i = 0; i<=alen; i++){
	    	result[i][0] = i;
	    }
	    
	    for(int i = 0; i<=blen; i++){
	    	result[0][i] = i;
	    }
	    
	    for(int i = 1; i<= alen; i++){
	    	for(int j = 1; j <= blen; j++){
	    		if(a.charAt(i-1) == b.charAt(j-1)){
	    			result[i][j] = result[i-1][j-1];
	    		}
	    		else{
	    			result[i][j] = 1 + Math.min(result[i-1][j-1], Math.min(result[i][j-1], result[i-1][j]));
	    		}
	    	}
	    }
	    return result[alen][blen];
	}
	
	public static int matrixMaxSumDP(int[][] grid) {
	    int rows = grid.length;
	    int cols = grid[0].length;
		int[][] result = new int[rows][cols];
		
		for(int i = 0; i<rows;i++){
			result[i][0] = i>0?result[i-1][0] + grid[i][0]:grid[i][0];
		}
		
		for(int j = 0; j<cols;j++){
			result[j][0] = j>0?result[j-1][0] + grid[j][0]:grid[j][0];
		}
		
		for(int i = 1; i< rows; i++){
			for(int j = 1; j< cols; j++){
				result[i][j] = grid[i][j] + Math.max(result[i-1][j], result[i][j-1]);
			}
		}
		
		return result[rows-1][cols-1];
	}
	
	public static int minWeightedPath(int[][] grid) {
        int rows = grid.length;
	    int cols = grid[0].length;
		int[][] result = new int[rows][cols];
		
		result[0][0] = grid[0][0];
		for(int i = 1; i<rows;i++){
			result[i][0] = result[i-1][0] + grid[i][0];
		}
		
		for(int j = 1; j<cols;j++){
			result[j][0] = result[j-1][0] + grid[j][0];
		}
		
		for(int i = 1; i< rows; i++){
			for(int j = 1; j< cols; j++){
				result[i][j] = grid[i][j] + Math.min(result[i-1][j], result[i][j-1]);
			}
		}
		
		return result[rows-1][cols-1];
	}
	
	public static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervalsList) {
	    ArrayList<Interval> output = new ArrayList<Interval>();
	    
	    int size = intervalsList.size();
	    if(intervalsList.size() == 1) return intervalsList;
	    Interval prev = intervalsList.get(0);
	    for(int i = 1; i<size;i++){
	    	Interval current = intervalsList.get(i);
	    	if(prev.end >= current.start){
	    		prev.end = Math.max(prev.end, current.end); 
	    	}
	    	else{
	    		output.add(prev);
	    		prev = current;
	    	}
	    }
	    
	    output.add(prev);
		return output;
	}
	
	public static ArrayList<Interval> insertRange(ArrayList<Interval> intervalsList, Interval insert) {
	    ArrayList<Interval> output = new ArrayList<Interval>();
	    if(intervalsList == null || intervalsList.size() == 0) {
	    	output.add(insert);
	    	return output;
	    }
	    
	    for(Interval i : intervalsList)
	    {
	    	if(insert.end < i.start)
	    	{
	    		output.add(insert);
	    		insert = i;
	    	}
	    	else if(insert.start > i.end){
	    		output.add(i);
	    	}
	    	else if(insert.end >= i.start || insert.start <= i.end){
	    		i.start = Math.min(insert.start, i.start);
	    		i.end = Math.max(insert.end, i.end);
	    		insert= i;
	    	}
	    }
	    
	    output.add(insert);
	    
	    return output;
	}
	
	public int bitSwapRequired(int a, int b) {
	    int res = a^b;
	    int count = 0;
	    
	    while(res != 0){
	    	res = res&(res-1);
	    	count++;
	    }
	    
	    return count;
	}
	
	public static int longestNRSubstringLen(String input) {
	    int left = 0, right = 0;
	    int maxLen = Integer.MIN_VALUE;
	    int len = input.length();
	    
	    if(len == 0) return 0;
	    HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
	    for(int i = 0; i< len;i++)
	    {
	    	if(hm.containsKey(input.charAt(i)))
	    	{
	    		int location = hm.get(input.charAt(i));
	    		maxLen = Math.max(maxLen, right - left + 1);
	    		if(location >= left){
	    			left = location + 1;
	    		}
	    	}
	    	
	    	hm.put(input.charAt(i), i);
	    	right = i;
	    }
	    
	    maxLen = Math.max(maxLen, right - left + 1);
		return maxLen;
	}
	
	public static  ArrayList<String> getStringsFromNums(String digits) {
	      ArrayList<String> result = new ArrayList<String>();
	      StringBuffer sb = new StringBuffer();
	      HashMap<Integer, String> hm = new HashMap<Integer, String>();
	      hm.put(2, "abc");
	      hm.put(3,  "def");
	      hm.put(4, "ghi");
	      hm.put(5, "jkl");
	      hm.put(6, "mno");
	      hm.put(7, "pqrs");
	      hm.put(8, "tuv");
	      hm.put(9, "wxyz");
	      
	      getStringsHelper(digits, 0, result, sb, hm);
	      
	      return result;
	}
	
	public static void getStringsHelper(String digits, int charIndex, ArrayList<String> result, StringBuffer sb, HashMap<Integer, String> hm)
	{
		if(charIndex == digits.length())
		{
			result.add(sb.toString());
			return;
		}
		
		String val = hm.get(digits.charAt(charIndex) - '0');
		for(Character c : val.toCharArray())
		{
			sb.append(c);
			getStringsHelper(digits, charIndex + 1, result, sb, hm);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	public static ArrayList<String> generateIPAddrs(String s) {
		ArrayList<String> result = new ArrayList<String>();
		if(s.length() < 4)
		{
			return result;
		}
		StringBuffer sb = new StringBuffer(s);
		
		generateIpHelper(sb, 0, result);
		
		return result;
	}
	
	public static void generateIpHelper(StringBuffer sb, int segments, ArrayList<String> result)
    {
        if (segments == 3)
        {
            if (isValidIp(sb.toString()))
            {
                result.add(sb.toString());
                return;
            }
        }

        int lastDot = sb.toString().lastIndexOf('.');

        for (int i = 2; i <= 4 && lastDot + i < sb.length(); i++)
        {
            sb.insert(lastDot + i, '.');
            generateIpHelper(sb, segments + 1, result);
            sb.deleteCharAt(lastDot + i);
        }
    }
	
	public static boolean isValidIp(String input)
	{
		String pattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		
		Pattern pat = Pattern.compile(pattern);
		Matcher m = pat.matcher(input);
		
		return m.find();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> levelorderRev(TreeNode root) { 
	    if(root == null) return null;
	    
	    Queue<TreeNode> cur = new LinkedList<TreeNode>();
	    Queue<TreeNode> next = new LinkedList<TreeNode>();
	    Stack<ArrayList<Integer>> st = new Stack<ArrayList<Integer>>();
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    cur.add(root);
	    
	    while(!cur.isEmpty())
	    {
	    	ArrayList<Integer> curLevel = new ArrayList<Integer>();
	    	TreeNode val = cur.remove();
	    	curLevel.add(val.val);
	    	if(val.left != null)
	    	{
	    		next.add(val.left);
	    	}
	    	
	    	if(val.right != null)
	    	{
	    		next.add(val.right);
	    	}
	    	
	    	if(cur.isEmpty())
	    	{
	    		st.push((ArrayList<Integer>) curLevel.clone());
	    		curLevel.clear();
	    		cur = next;
	    		next = new LinkedList<TreeNode>();
	    	}
	    	
	    }
	    
	    while(!st.isEmpty())
	    {
	    	result.addAll(st.pop());
	    }
	    return result;
	}
	
	public static ArrayList<String> getPermutations(String s) {
		ArrayList<String> result = new ArrayList<String>();
		result = permHelper(s);
		return result;
	}
	
	public static ArrayList<String> permHelper(String s)
	{
		ArrayList<String> result = new ArrayList<String>();
		if(s.isEmpty())
		{
			return result;
		}
		if(s.length() > 1)
		{
			char c = s.charAt(0);
			
			// Uncomment next line if you need permutations also
			// result.add("" + c);
			String remaining = s.substring(1);
			ArrayList<String> subResult = permHelper(remaining);
			for (String str : subResult) {
				for(int i = 0; i <= str.length(); i++)
				{
					String permutation = str.substring(0, i) + c + str.substring(i);
					result.add(permutation);
				}
			}
			
			// Uncomment next line if you need permutations also
			// result.addAll(subResult);
			
		}
		else
		{
			result.add(s);
		}
		
		return result;
	}
	
	public static boolean boggleSearch(char[][] board, String word){
	    StringBuffer sb = new StringBuffer();
	    if(word == null || word.length() == 0) return true;
	    int rows = board.length;
	    int cols = board[0].length;
	    boolean[][] visited = new boolean[rows][cols];
	    
	    for(int i = 0; i < rows; i++)
	    {
	    	for(int j = 0; j < cols; j++)
	    	{
	    		if(boggleSearchHelper(board, word, sb, i, j, rows, cols, visited) == true) return true;
	    	}
	    }
		return false;
	}
	
	public static boolean boggleSearchHelper(char[][] board, String word, StringBuffer sb, int row, int col, int rows, int cols, boolean[][] visited)
	{
		if(sb.toString().equals(word))
		{
			return true;
		}
		
		if(isSafe(row, rows, col, cols, visited))
		{
			visited[row][col] = true;
			sb.append(board[row][col]);
			if(boggleSearchHelper(board, word, sb, row + 1, col, rows, cols, visited)) return true;
			if(boggleSearchHelper(board, word, sb, row, col + 1, rows, cols, visited)) return true;
			if(boggleSearchHelper(board, word, sb, row - 1, col, rows, cols, visited)) return true;
			if(boggleSearchHelper(board, word, sb, row, col - 1, rows, cols, visited)) return true;
			sb.deleteCharAt(sb.length() - 1);
			visited[row][col] = false;
		}
		
		return false;
	}
	
	public static boolean isSafe(int row, int rows, int col, int cols, boolean[][] visited)
	{
		return ( row >= 0 && row < rows && col >=0 && col < cols && visited[row][col] == false);
	}
	
	public static boolean isBalanced(String input) {
		
		if(input == null || input.length() == 0) return true;
		Stack<Character> parenStack = new Stack<Character>();
		ArrayList<Character> opening = new ArrayList<Character>();
		opening.add('(');
		opening.add('[');
		opening.add('{');
		
		ArrayList<Character> closing = new ArrayList<Character>();
		closing.add(')');
		closing.add(']');
		closing.add('}');
		
		for(int i = 0; i < input.length(); i++)
		{
			Character c = input.charAt(i);
			if(opening.contains(c))
			{
				parenStack.push(c);
			}
			else if(closing.contains(c))
			{
				if(parenStack.isEmpty()){
					return false;
				}
				else
				{
					Character top = parenStack.pop();
					if(top != opening.get(closing.indexOf(c)))
					{
						return false;
					}
				}
			}
		}
		
		return parenStack.isEmpty();
	}
	
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
	    if (lists.size() == 0)  return null;
	    PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(),
	        new Comparator<ListNode>() {
	          public int compare(ListNode node1, ListNode node2) {
	           if (node1.val > node2.val)
	              return 1;
	            else if(node1.val == node2.val)
	              return 0;
	            else 
	              return -1;
	          }});
	    for (ListNode list : lists) {
	      if (list != null)
	        queue.add(list);
	    }
	    ListNode head = new ListNode(0), curr = head; 
	    while (queue.size() > 0) {
	      ListNode temp = queue.poll();
	      curr.next = temp;
	      if (temp.next != null)
	        queue.add(temp.next); 
	      curr = curr.next;
	    }
	    return head.next;
	}
	
	public static boolean groupSum(int[] arr, int target) {
		if(arr == null || arr.length == 0) return false;
		
		int sum = 0;
		boolean hasSum = groupSumHelper(arr, 0, target, sum);
		return hasSum;
	}
	
	public static boolean groupSumHelper(int[] arr, int startIdx, int target, int curSum)
	{
		if(curSum == target) return true;
		
		curSum += arr[startIdx];
		
		for(int i = startIdx+1; i<arr.length;i++)
		{
			if(groupSumHelper(arr, i, target, curSum) == true) return true;
		}
		
		return false;
	}
	
	public static int makeChange(int[] coins, int amount) {
	    if(coins.length == 0) return 0;
	    
	    int len = coins.length;
	    int[] dpArr = new int[amount + 1];
	    dpArr[0] = 1;
	    
	    for(int i = 1; i <len;i++)
	    {
	    	for(int j = coins[i]; j<=amount;j++){
	    		dpArr[j] = dpArr[j] + dpArr[j - coins[i]];
	    	}
	    }
	    
	    return dpArr[amount];
	}
	
	public static boolean groupSumWithNum(int[] arr, int must_have, int target) {
		if(arr == null || arr.length == 0) return false;
		return getSumHelper(arr, must_have, target, 0);
	}
	
	public static boolean getSumHelper(int[] arr, int must_have, int target, int index)
	{
		if(index >= arr.length)
		{
			return target == 0;
		}
		
		if(arr[index] == must_have)
		{
			return getSumHelper(arr, must_have, target - must_have, index + 1);
		}
		
		if(getSumHelper(arr, must_have, target, index + 1)) return true;
		
		if(getSumHelper(arr, must_have, target - arr[index], index + 1)) return true;
		
		return false;
		
	}
	
	public static int[] maxContSequence(int[] arr) {
		if(arr == null || arr.length == 0) return new int[]{0, 0, 0};
		
		int[] result = new int[3];
		int startIdx =0, endIdx=0, currentSum = 0, maxSum = Integer.MIN_VALUE, maxStart =0, maxend =0;
		
		for(int i= 0; i<arr.length; i++)
		{
			if(currentSum + arr[i] < arr[i])
			{
				startIdx = i; endIdx = i;currentSum = arr[i];
			}
			else
			{
				currentSum += arr[i];
				endIdx = i;
			}
			
			if(currentSum > maxSum)
			{
				maxSum = currentSum;
				maxStart = startIdx;
				maxend = endIdx;
			}
		}
		
		result[0] = maxSum;
		result[1] = maxStart;
		result[2] = maxend;
		
		return result;

	}
	
	public static int largestSquare(char[][] matrix) {
	    if(matrix == null || matrix.length == 0) return 0;
	    
	    int r = matrix.length;
	    int c = matrix[0].length;
	    
	    int[][] sum = new int[r][c];
	    
	    int maxVal = Integer.MIN_VALUE;
	    
	    for(int i = 0; i<r; i++)
	    {
	    	sum[i][0] = matrix[i][0] - '0';
	    }
	    
	    for(int j = 0; j<c; j++)
	    {
	    	sum[0][j] = matrix[0][j] - '0';
	    }
	    
	    for(int i = 1; i<r; i++)
	    {
	    	for(int j = 1; j<c; j++)
	    	{
	    		if(matrix[i][j] == '1')
	    		{
	    			sum[i][j] = Math.min(sum[i-1][j], Math.min(sum[i-1][j-1], sum[i][j-1])) + 1;
	    		}
	    		else
	    		{
	    			sum[i][j] = 0;
	    		}
	    	}
	    }
		
	    for(int i = 0; i<r;i++)
	    {
	    	for(int j = 0; j<c; j++)
	    	{
	    		if(sum[i][j] > maxVal)
	    		{
	    			maxVal = sum[i][j];
	    		}
	    	}
	    }
		return maxVal*maxVal;
	}
	
	public static int longestPalSubstr(String str){
	    if(str == null) return 0;
	    
	    int longestPalLen = Integer.MIN_VALUE;
	    int len = 0;
	    
	    for(int i = 0; i< str.length() - 1; i++)
	    {
	    	len = lengthOfPal(i, i, str);
	    	if(len > longestPalLen) longestPalLen = len;
	    	
	    	len = lengthOfPal(i, i+1, str);
	    	if(len > longestPalLen) longestPalLen = len;
	    }
	    
	    
	    return longestPalLen;
	}
	
	public static int lengthOfPal(int idx1, int idx2, String str)
	{
		if(str.charAt(idx1) != str.charAt(idx2)) return 0;
		int i = idx1, j = idx2, len = 0;
		while(i>=0 && j<str.length() &&(str.charAt(i) == str.charAt(j)))
		{
			len = j - i + 1;
			i--;
			j++;
		}
		
		return len;
	}
	
	public int removeElement(ArrayList<Integer> a, int b) {
		if(a.size() == 0) return 0;
		
		int writeIdx = 0;
		
		for(int i = 0; i< a.size(); i++){
			if(a.get(i) == b){
				a.set(writeIdx++, a.get(i));
			}
		}
		
		return writeIdx;
	}
	
	public static long reverse(long a) {
		long result = a;
		for(int i = 0, j = Long.SIZE - 1; i<j;i++,j--){
			if((1&(a>>i)) != (1&(a>>j))){
				long mask = (1<<i | 1<<j);
				result ^= mask;
			}
		}
		return result;
	}
	
	public static boolean validateBSTItr(TreeNode root) {

	    class TreeBoundaryNode{
	      TreeNode treeNode;
	      int leftBoundary;
	      int rightBoundary;
	      TreeBoundaryNode(TreeNode treeNode, int leftBoundary, int rightBoundary) {
	          this.treeNode = treeNode;
	          this.leftBoundary = leftBoundary;
	          this.rightBoundary = rightBoundary;
	      }
	    }
	    
	    if(root == null || (root.left == null && root.right == null)) return true;
	    
	    Queue<TreeBoundaryNode> q = new LinkedList<>();
	    q.add(new TreeBoundaryNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
	    while(!q.isEmpty()){
	        TreeBoundaryNode tbNode = q.poll();
	        TreeNode t = tbNode.treeNode;
	        if((t.val <= tbNode.leftBoundary) || (t.val >= tbNode.rightBoundary)) return false;
	        if(t.left != null){
	            q.add(new TreeBoundaryNode(t.left, tbNode.leftBoundary, t.val));
	        }
	        if(t.right != null){
	            q.add(new TreeBoundaryNode(t.right, t.val, tbNode.rightBoundary));
	        }
	    }

	    return true;
	}
}
