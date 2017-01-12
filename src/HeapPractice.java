import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class HeapPractice {

	public static void CalculateMedian()
	{
		Scanner in = new Scanner(System.in);
		int numElements = in.nextInt();
		
		
		// If you need to change this to an incoming stream, this array will not exist.
		int[] input = new int[numElements];
		
		for(int i = 0; i<numElements; i++){
			input[i] = in.nextInt();
		}
		PriorityQueue<Integer> min = new PriorityQueue<Integer>(5);
		PriorityQueue<Integer> max = new PriorityQueue<Integer>(5, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// Change default and make max element come first
				if(o1 < o2) return 1;
				else if(o1 > o2) return -1;
				else return 0;
			} 
		});
		
		for(int i =0;i<input.length;i++)
		{
			if(!max.isEmpty() && input[i] > max.peek())
			{
				min.offer(input[i]);
			}
			else
			{
				max.offer(input[i]);
			}
			
			if(min.size() > max.size() + 1){
				max.offer(min.poll());
			}
			else if(max.size() > min.size() + 1){
				min.offer(max.poll());
			}
			
			if(min.size() == max.size())
			{
				System.out.println((min.peek() + max.peek())*0.5);
			}
			else if (min.size() > max.size()){
				System.out.println(min.peek());
			}
			else{
				System.out.println(max.peek());
			}
		}
	}
}
