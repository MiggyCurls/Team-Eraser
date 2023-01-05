package lab05;

public class SortedBinarySet {
	/** represent the simple array that holds the list values */
	public double[] theData;
	/** holds the length of the storage array */
	private int capacity;
	/** holds the number of elements in the list */
	private int size;

	/** constant for initial storage array capacity */
	private static final int INITIAL_STORAGE_CAPACITY = 11;

	/** keep this FALSE for lab; we will play with this in the assignment **/
	public boolean usesBinarySearch = false;

	/** default constructor */
	public SortedBinarySet(){
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
		this.capacity = INITIAL_STORAGE_CAPACITY;
		this.size = 0;
	}

	public SortedBinarySet(double[] input){
		this();
		this.size = (this.size());
		for(int i = 0; i < input.length; i++) {
			boolean capture = this.insert(input[i]);
		}
	}

	public boolean empty(){
		for(int i = 0; i < this.theData.length; i++) {
			if(this.theData[i] != 0) {
				return false;
			}
		}
		return true; //placeholder
	}

	public int size(){
		int count = 0;
		for(int i = 0; i < this.theData.length; i++) {
			if(this.theData[i] != 0) {
				count++;
			}
		}
		return count; //placeholder
	}

	public void grow(){
		int length = this.theData.length;
		double[] temp = new double[length * 2];
		for(int i = 0; i < length; i++) {
			temp[i] = this.theData[i];
		}
		this.theData = temp;
		this.capacity *= 2;
	}

	public String toString(){
		String output = " ";
		for(int i  = 0; i < this.theData.length; i++) {
			output += this.theData[i] + ", ";
		}
		output += this.capacity + " " + this.size;
		return output; // placeholder
	}

	public void clear() {
		for(int i = 0; i < this.capacity; i++) {
			this.theData[i] = 0;
		}
		boolean state = this.empty();
	}

	public boolean insert(double newVal){
		// TODO
		if(this.capacity - this.size <= 0) {
			this.grow();
		}
		int pos = this.findIndex(newVal);
		if(pos < 0) {
			double[] copy = new double[this.capacity];
			for(int i = 0; i < -pos; i++) {
				copy[i] = this.theData[i];
			}
			copy[-pos] = newVal;
			for(int j = (-pos + 1); j < this.capacity; j++) {
				copy[j] = this.theData[j-1];
			}
			this.theData = copy;
			this.size += 1;
			return true;
		}
		else if(pos == 0) {
			this.theData[this.size] = newVal;
			this.size += 1;
			return true;
		}
		return false;
	}

	public boolean remove(double element){
		int num = findIndex(element);
		double[] copy = new double[this.capacity];
		if(num > 0) {
			for(int i = 0; i < num; i++) {
				copy[i] = this.theData[i];
			}
			for(int j = num; j < this.capacity - 1; j++) {
				copy[j] = this.theData[j + 1];
			}
			this.theData = copy;
			return true;
		}
		return false; // placeholder
	}


	private int sequentialFind(double target) {
		// TODO
		double[] arr = this.theData;
		int index = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == target) {
				return i;
			}
			else if(i != arr.length - 1) {
				if(target > arr[i] && target < arr[i+1]) {
					index  = (-i - 1);
				}
			}
		}
		return index;
	}

	private int binaryFind(double target) {
		//binary search
		int left = 0;
		int right = this.capacity - 1;
		if(!this.empty()) {
			while(left < right) {
				int mid = (left + right) / 2;
				if(this.theData[mid] == target) {
					return mid;
				}
				else if(this.theData[mid] < target) {
					right = mid - 1;
				}
				else{
					left = mid + 1;
				}
			}
		}
		
		int pos = sequentialFind(target);
		return pos;
	}

	public int findIndex(double target) {
		if (usesBinarySearch) {
			return binaryFind(target);
		} else {
			return sequentialFind(target);

		}
	}

	public boolean containsAll(double[] elements){
		for(int i = 0; i < elements.length; i++) {
			int index = binaryFind(elements[i]);
			if(index < 0) {
				return false;
			}
		}
		return true; // placeholder
	}

	public boolean contains(double element){
		int index = binaryFind(element);
		if(index < 0) {
			return false;
		}
		return true; // placeholder
	}

}
