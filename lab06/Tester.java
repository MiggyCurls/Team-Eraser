package lab06;

public class Tester {
	public static void main(String[] args) {
		//checking part One
		SortedString sOne = new SortedString("joy");
		SortedString sTwo = new SortedString("ski");
		System.out.println("Sorted string: " + sOne.getSorted());
		System.out.println("Unsorted string:  " + sOne.getUnsorted());
		System.out.println("Comparison with 'below' : " + sOne.compareTo(sTwo));
		//-----------------------------------------------------------
		//checking part Two
		System.out.println();
		SortedString sThree = new SortedString("miGuel");
		SortedString sFour = new SortedString("Miguel");
		AnagramUtil caseOne = new AnagramUtil();
		boolean state = caseOne.areAnagrams(sThree,  sFour);
		System.out.println("testing last name but reversed: " + state);
		//-----------------------------------------------------------
		//checking part Three
		System.out.println();
		InsertionSort<Integer> sFive = new InsertionSort<Integer>();
		Integer[] arr = {1,15,7,69};
		Integer[] sortedArr = sFive.sort(arr);
		for(int i = 0; i < sortedArr.length; i++) {
			System.out.print(" " + sortedArr[i].intValue() + " ");
		}
		InsertionSort<SortedString> sSix = new InsertionSort<SortedString>();
		SortedString stringOne = new SortedString("joy");
		SortedString stringTwo = new SortedString("ski");
		SortedString stringThree = new SortedString("fed");
		SortedString stringFour = new SortedString("cat");
		SortedString[] arrTwo = {stringOne, stringTwo, stringThree, stringFour};
		SortedString[] arrTwoSorted = sSix.sort(arrTwo);
		for(int i = 0; i < arrTwoSorted.length; i++) {
			System.out.print(" " + arrTwoSorted[i] + " ");
		}
		String[] myArray = {"joy", "ski", "fed", "cat"};
		SortedString[] testing = SortedString.toSortedString(myArray);
		testing = sSix.sort(testing);
		for(int i = 0; i < testing.length; i++) {
			System.out.print(" " + testing[i].getUnsorted() + " ");
		}
		//-----------------------------------------------------------
		//checking part Four
		System.out.println();
		InsertionSort<Integer> runTime = new InsertionSort<Integer>();
		Integer[] arrThree = {1,2,3,4,5,6,7,8,9,10};
		arrThree = runTime.sort(arrThree);
		runTime.fit(arrThree);
		System.out.println("testing runtime predict: " + runTime.predict(100000000));
		//------------------------------------------------------------
		//checking Assignment part One
		System.out.println();
		AnagramUtil caseTwo = new AnagramUtil();
		String[] s3 = caseTwo.getLargestAnagramGroup("sample_word_list.txt");
		for(int i = 0; i < s3.length; i++) {
			System.out.print(" " + s3[i] + " ");
		}
		System.out.println();
		//------------------------------------------------------------
		//checking Assignment part Two
		System.out.println();
		MergeSort<Integer> runTimeTwo = new MergeSort<Integer>();
		Integer[] arrFour = {1,2,3,4,5};
		arrFour = runTimeTwo.sort(arrFour);
		for(int i = 0; i < arrFour.length; i++) {
			System.out.print(" " + arrFour[i] + " ");
		}
		System.out.println();
		//-------------------------------------------------------------
		//checking Assignment part Three
		System.out.println();
		MergeSort<Integer> runTimeThree = new MergeSort<Integer>();
		Integer[] arrFive = {1,2,3,4,5,6,7,8,9,10};
		arrFive = runTimeThree.sort(arrFive);
		runTimeThree.fit(arrFive);
		System.out.println("testing part two runtime: " + runTimeThree.predict(1000000));
	}

}
