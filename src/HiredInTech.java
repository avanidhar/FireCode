import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HiredInTech {

	public static int jump_over_numbers(List<Integer> list) {
        // Write your code here
		int numJumps = 0;
		int i =0, size = list.size();
		
		while(i < size)
		{
			int val = list.get(i);
			if(val == 0) return -1;
			
			i += val;
			numJumps++;
		}
		
		return numJumps;
    }
	
	public static int majorityElement(final List<Integer> a) {
		int len = a.size();
		
		for(int i=0;i<len;i++){
			
			int num = a.get(i)%len;
			a.set(num, a.get(num) + len);
		}
		
		int maxValue = Integer.MIN_VALUE;
		int maxInt = Integer.MIN_VALUE;
		for (int i =0; i<a.size();i++) {
			if(a.get(i) > maxValue){
				maxValue = a.get(i);
				maxInt = i;
			}
		}
		return maxInt;
	}
	public static String compressString(String text) {
	    if(text.isEmpty()) return text;
	    StringBuffer sb = new StringBuffer();
	    char current;
	    int count = 1;
	    char[] inputArray = text.toCharArray();
	    current = inputArray[0];
	    for(int i=1;i<text.length();i++)
	    {
	    	if(inputArray[i] == current)
	    	{
	    		count++;
	    	}
	    	else
	    	{
	    		sb.append(current);
	    		if(count>1){
	    			sb.append(count);
	    		}
	    		current = inputArray[i];
	    		count = 1;
	    	}
	    }
	    
	    sb.append(current);
		if(count>1){
			sb.append(count);
		}
	    return sb.toString();
	}
	
	public static int[] merge(int[] arrLeft, int[] arrRight){
	    
	    if(arrLeft.length == 0) return arrRight;
	    if(arrRight.length == 0) return arrLeft;
	    
	    int finalSize = arrLeft.length + arrRight.length;
	    int[] result = new int[finalSize];
	    int i, leftIdx, rightIdx;
	    for(i =0, leftIdx =0, rightIdx =0; i< finalSize && leftIdx < arrLeft.length && rightIdx < arrRight.length; )
	    {
	    	if(arrLeft[leftIdx] < arrRight[rightIdx])
	    	{
	    		result[i] = arrLeft[leftIdx];
	    		i++; leftIdx++;
	    	}
	    	else if (arrLeft[leftIdx] > arrRight[rightIdx]){
	    		result[i] = arrRight[rightIdx]; i++; rightIdx++;
	    	}
	    	else{
	    		result[i] = arrLeft[leftIdx]; i++; leftIdx++;
	    		result[i] = arrRight[rightIdx]; i++; rightIdx++;
	    	}
	    }
	    
	    if(leftIdx < arrLeft.length)
	    {
	    	while(i<finalSize){
	    		result[i++] = arrLeft[leftIdx++];
	    	}
	    }
	    
	    if(rightIdx < arrRight.length)
	    {
	    	while(i<finalSize){
	    		result[i++] = arrRight[rightIdx++];
	    	}
	    }
	    
	    return result;

	}
	
	public static int[] coupleSum(int[] numbers, int target) {
	    Map<Integer, Integer> hm = new HashMap<>();
	    
	    for (int i = 0; i < numbers.length; i++) {
	    	if(hm.containsKey(target - numbers[i]))
	    	{
	    		return new int[] {hm.get(target - numbers[i]), i+1};
	    	}
	    	else{
	    		hm.put(numbers[i], i+1);
	    	}	    	
		}
	    
	    return new int[]{};	    
	}
	
	public static int gcd(int a, int b){
		if(b==0)return a;
		return gcd(b, a%b);
	}
	
	public static boolean isIsomorphic(String input1, String input2) {
		
		if(input1.length() != input2.length()) return false;
	    Map<Character, Character> hm = new HashMap<Character, Character>();
	    
	    int length = input1.length();
	    
	    for(int i = 0; i < length; i++){
	    	char c = input1.charAt(i);
	    	if(hm.containsKey(c)){
	    		if(hm.get(c) != input2.charAt(i)) return false;    		
	    	}
	    	else if(hm.containsValue(input2.charAt(i))){
	    		return false;
	    	}
	    	else{
	    		hm.put(input1.charAt(i), input2.charAt(i));
	    	}
	    }
	    
	    return true;
	}
	
	public static String computeBinary(int val) {
		if(val==0)return "0";
		
		StringBuffer sb = new StringBuffer();
		
		while(val != 0){
			int res = val&1;
			sb.append(res);
			val >>= 1;
		}
		
		return sb.reverse().toString();
	}
	
	public static void transposeMatrix(int[][] matrix) {
	    if(matrix.length == 0) return;
	    int length = matrix.length;
	    for(int offset = 0; offset < length; offset++ ){
	    	for(int j = offset; j<length;j++){
	    		int temp = matrix[offset][j];
	    		matrix[offset][j] = matrix[j][offset];
	    		matrix[j][offset] = temp;
	    	}
	    }
	}
	
	public static void rotateSquareImageCCW(int[][] matrix) {
	    if(matrix.length == 0) return;
	    int length = matrix.length;
	    for(int offset = 0; offset < length/2;offset++){
	    	for(int j = offset; j<length - offset-1; j++){
	    		int temp = matrix[offset][j];
	    		matrix[offset][j] = matrix[j][length - offset - 1];
	    		matrix[j][length - offset - 1] = matrix[length - offset -1][length - j - 1];
	    		matrix[length - offset -1][length - j - 1] = matrix[length - j - 1][offset];
	    		matrix[length - j - 1][offset] = temp;
	    	}
	    }
	}
	
	public static void rotateSquareImageCW(int[][] matrix) {
	    if(matrix.length == 0) return;
	    int length = matrix.length;
	    for(int offset = 0; offset < length/2;offset++){
	    	for(int j = offset; j<length - offset-1; j++){
	    		int temp = matrix[offset][j];
	    		matrix[offset][j] = matrix[length - j - 1][offset];
	    		matrix[length - j - 1][offset] = matrix[length - offset -1][length - j - 1];
	    		matrix[length - offset -1][length - j - 1] = matrix[j][length - offset - 1];
	    		matrix[j][length - offset - 1] = temp;
	    	}
	    }
	}
	
	public static ArrayList<Integer> findSpiral(int[][] arr) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(arr.length == 0 || arr == null) return result;
		
		int len = arr.length;
		int offset = 0, i =0, j = 0;
		
		for(offset = 0; offset < Math.ceil(arr.length/2); offset++){
			if(offset == len - 1 - offset ) result.add(arr[offset][offset]);
			
			for(j=0;j<len-offset-1;i++){
				result.add(arr[offset][j]);
			}
			
			for(i = offset; i < len - offset - 1;i++){
				result.add(arr[i][len - offset - 1]);
			}
			
			for(j = len - offset - 1; j>offset;j--){
				result.add(arr[len - offset - 1][j]);
			}
			
			for(i = len - offset - 1; i > offset; i --){
				result.add(arr[i][offset]);
			}
		}
		
		return result;
	}
	
	public static String insertPairStar(String s) {
		 
		 StringBuffer sb = new StringBuffer();
		 insertPairStarHelper(s, sb);
		 return sb.toString();
	}
	
	public static void insertPairStarHelper(String s, StringBuffer sb){
		if(s.isEmpty() || s == null) return;
		if(s.length() == 1){
			sb.append(s.charAt(0));
			return;
		}
		char c = s.charAt(0);
		sb.append(c);
		if(c == s.charAt(1)) sb.append('*');
		insertPairStarHelper(s.substring(1), sb);
	}
	
	public static List<Integer> longest_increasing_subsequence(List<Integer> sequence) {
        // Write your solution here
		
		if(sequence.size() == 0 || sequence.size() == 1) return sequence;
		int len = sequence.size();
		int[] lengthArr = new int[len];
		int[] prev = new int[len];
		
		for(int i = 0;i < len;i++)
		{
			lengthArr[i] = 1;
			prev[i] = -1;
			for(int j =0;j<i;j++){
				if((sequence.get(j) < sequence.get(i)) && (lengthArr[i] < lengthArr[j] + 1)){
					lengthArr[i] = lengthArr[j] + 1;
					prev[i] = j;
				}
			}
		}
		
		return null;
    }
	
	public static boolean is_numeric_palindrome(long number) {
	    // Write your code here

	    if(number < 0)return false;
	    
	    long copy = number;
	    long reconstruct = 0;
	    
	    while(copy != 0){
	        long lastDigit = copy%10;
	        reconstruct = reconstruct*10 + lastDigit;
	        copy /= 10;
	    }
	    
	    return reconstruct == number;
	  }
}
