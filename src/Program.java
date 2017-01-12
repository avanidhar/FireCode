import java.util.ArrayList;


public class Program {

	public static void main(String[] args) {
		
		ListNode head = new ListNode(1);
		ListNode a = new ListNode(2);
		ListNode b = new ListNode(3);
		ListNode c = new ListNode(4);
		
		head.next = a;
		a.next = b;
		b.next = c;
		c.next = b;
		
		// ListNode result = Solution.detectCycle(head);
		
		// boolean happy = Solution.isHappyNumber(19);
			
		// String s = "   ";
		// int last = Solution.lengthOfLastWord(s);
		
		// ArrayList<String> result = Solution.combParenthesis(2);
		
		int[] arr = {1, 2, 3, 6, 5, 10};
		//int maxProfit = Solution.maxProfit(arr);
		
		/*int [][] in ={
			{1, 2, 3},{4, 5, 6},{7, 8, 9}
		};*/
		
		// char[][] input = {{'a', 'b', 'c'},{'d', 'e', 'f'},{'g', 'h', 'i'}};
		
		// ArrayList<String> result = Solution.printPaths(input);
		// HiredInTech.rotateSquareImageCW(input);
		// int result = FireCode.matrixMaxSumDP(input);
		
		ArrayList<Interval> input = new ArrayList<Interval>();
		input.add(new Interval(3, 5));
		input.add(new Interval(7,  10));
		
		// ArrayList<Interval> result = FireCode.insertRange(input, new Interval(1,2));
		// ArrayList<String> result = FireCode.getStringsFromNums("34");
		
		// ArrayList<String> result = FireCode.generateIPAddrs("1234567");
		
		ArrayList<String> result = FireCode.getPermutations("cat");
		// char[][] board = {{'a', 'o', 'l'},{'d', 'e', 'l'},{'g', 'h', 'i'}};
		// boolean result = FireCode.boggleSearch(board, "helo");
		// boolean result = FireCode.groupSum(arr, 20);
		
		// int[] inputArray = {1, 2, 3, -6, 5};
		// int[] result = FireCode.maxContSequence(inputArray);
		
		// char[][] someArr = {{'1', '1', '0', '1'},{'1', '1', '0', '1'},{'1', '1', '1', '1'}};
		// int result = FireCode.largestSquare(someArr);
		
		// int result = FireCode.longestPalSubstr("bccc");
		
		// long result = FireCode.reverse(3);
		
		System.out.println(result);
		//HeapPractice.CalculateMedian();
	}
}
