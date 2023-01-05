package lab05;

public class Tester {
	
	public static void main(String[] args) {
		SortedBinarySet s = new SortedBinarySet();
		boolean state = s.empty();
		System.out.println(state);
		long num = 0;
		int k = 0;
		while(k < 1000) {
			s.insert(k);
			k++;
			num = System.nanoTime();
		}
		System.out.println("num: " + num);
		System.out.println(s);
		int pos = s.findIndex(10);
		System.out.println(pos);
		s.insert(3.3);
		System.out.println(s);
		boolean condition = s.remove(9.5);
		System.out.println(s);
		System.out.println(condition);
		
		pos = s.findIndex(11);
		System.out.println(pos);
		boolean truth = s.contains(9);
		System.out.println(truth);
		
		double[] elements = {1.0, 2.0, 3.0,4,5,6,7,8,9,10,11,12,};
		truth = s.containsAll(elements);
		System.out.println(truth);
		
		SortedBinarySet s2 = new SortedBinarySet(elements);
		System.out.println(s2);
	}
}
