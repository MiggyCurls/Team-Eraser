package lab06;

public class InsertionSort<E extends Comparable<E>> {

    /** The constant in the formula t = c * O() */
    private double c;

    /** The order O() of the implementation.
        If your implementation is in O(n^2), use Math.pow().
	    @param n index
	    @return the function of n inside the O()
	 */
    public double O(int n) {
        return (Math.pow(n, 2));
    }

    /** Calculates the constant c using a given input array of type E.
        Units of time should be converted to microseconds
    */
    public void fit(E[] array) {
       //time = c * O(n);
    	// c = time / O(n);
    	long initialTime = System.nanoTime();
    	array = sort(array);
    	long finalTime = System.nanoTime();
    	long time = (finalTime - initialTime)/1000;
    	this.c = (time/O(array.length));
    }

    /** Predicts the running time of an insertion sort for some index n
        @param n
        @return the estimated amount of time in unit microseconds
    */
    public double predict(int n) {
        //time = c * O(n)
    	return(this.c * O(n));
    }

    /** Performs an insertion sort using a given input array
        @param array the (unsorted) array
        @return the sorted array
    */
    public E[] sort(E[] array) {
        // EXERCISE

        /* Handle case where length of array is 1 or less */

        /* make a copy of the array to sort */
        E[] sorted = array.clone();

        /* Perform the insertion sort */
        for(int i=1; i < sorted.length; i++)
        {
            E position = sorted[i];
            int j = i;
            while(j > 0 && sorted[j-1].compareTo(position) > 0) {
            	sorted[j] = sorted[j-1];
            	j--;
            }
            sorted[j] = position;
        }
        return sorted;
       // return null; // not right
    }

}
