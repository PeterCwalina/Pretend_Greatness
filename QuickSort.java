//Jason Kim
//APCS2 pd8
//HW18 -- So So Quick
//2018-03-13
  
/*****************************************************
 * class QuickSort
 * Implements quicksort algo to sort an array of ints in place
 *
 * 1. Summary of QuickSort algorithm:
 * QSort(arr): 
 * Using method partition(), we can recursively sort an array. 
 * We choose an integer pivot, which will be the last element within the boundary.
 * From there, we recall the method twice, with the differnt boundaries set. 
 * - One boundary is [left, pvtPos-1]
 * - Other is [pvtPos+1, right]
 * Each call recursively calls the next, until the boundaries overlap, stopping the process.
 *
 * 2a. Worst pivot choice and associated runtime: 
 * The worst pivot choice is to get the highest or lowest value within the boundaries.
 * If you were to get this every single time, your partition would be very ineffective. 
 * Ex:
 * {0,1,2,3,4,5,6}
 * In this case, the worst pivot choice is 6 as the next call would only consist of {0,1,2,3,4,5}.
 * The division of labor is not here like it could be.
 * O(n^2)
 *
 * 2b. Best pivot choice and associated runtime:
 * The best pivot choice would be the median of the dataset.
 * When you partition with this pivot, you are dividing up the labor as efficiently as possible.
 * Ex:
 * {0,1,2,3,4,5,6}
 * In this case, the best pivot would be 3 as it equally divides the array into sections of {0,1,2} and {4,5,6}
 * O(nlogn)
 *
 * 3. Approach to handling duplicate values in array:
 * Duplicates seem to work with no edits. There is nothing needed to be added in the method to handle this situation.
 * This is most likely because when we partition, even if there are duplicates, they stay at their original side. 
 * When the method is called on the smaller sub-arrays, these duplicates will then be placed in their appropriate positions.
 * Once all parts are combined, the whole array is sorted like it should be
 *****************************************************/

public class QuickSort
{
    //--------------v  HELPER METHODS  v--------------
    //swap values at indices x, y in array o
    public static void swap( int x, int y, int[] o ) {
	int tmp = o[x];
	o[x] = o[y];
	o[y] = tmp;
    }

    //print input array 
    public static void printArr( int[] a ) {
	for ( int o : a )
	    System.out.print( o + " " );
	System.out.println();
    }

    //shuffle elements of input array
    public static void shuffle( int[] d ) {
	int tmp;
	int swapPos;
	for( int i = 0; i < d.length; i++ ) {
	    tmp = d[i];
	    swapPos = i + (int)( (d.length - i) * Math.random() );
	    swap( i, swapPos, d );
	}
    }

    //return int array of size s, with each element fr range [0,maxVal)
    public static int[] buildArray( int s, int maxVal ) {
	int[] retArr = new int[s];
	for( int i = 0; i < retArr.length; i++ )
	    retArr[i] = (int)( maxVal * Math.random() );
	return retArr;
    }
    //--------------^  HELPER METHODS  ^--------------
    
    public static int partition (int[] arr, int left, int right, int pvtPos) {
	int pvtVal = arr[pvtPos];
	swap(pvtPos, right, arr);
	int storVal = left;
	
	for (int i = left; i < right; i++) {
	    if (arr[i] <= pvtVal) {
		swap(i, storVal, arr);
		storVal++;
	    }
	}
	swap(storVal, right, arr);
	
	return storVal;
    }//end partition()


    /*****************************************************
     * void qsort(int[])
     * @param d -- array of ints to be sorted in place
     *****************************************************/
    public static void qsort( int[] d )
    { 
	qsorth(d,0,d.length-1); //call helper with appropriate params
    }

    //you may need a helper method...
    //qsorth() comes into play!
    public static void qsorth(int[]arr, int left, int right) {
	if (left < right) { //as long as the boundaries don't overlap
	    int pvtPos = partition(arr,left,right,right); //taking the rightmost element
	    qsorth(arr, left, pvtPos-1); //call setting boundaries [left, pvtPos-1]
	    qsorth(arr, pvtPos+1, right); //call setting boundaries [pvtPos+1, right]
	}
    }

    //main method for testing
    public static void main( String[] args )
    {
	int[]warm = buildArray(2000,1000);
	
        //warming up
	for (int x = 0; x < 1000; x++){
	    shuffle (warm);					
	    qsort(warm);
	} 

	//time testing
	double start;
	double end;
	double total = 0;	
	for (int y = 2000; y < 5000; y+= 100){//starting at a size 2000 arr go up til a size 5000 arr and test timings
	    warm = buildArray(y,1000);
	    for (int x = 0; x < 1000; x++){//run each test 1000 times for precision
		shuffle (warm);
		start = System.currentTimeMillis();						
		qsort(warm);
		end = System.currentTimeMillis();
		total += end - start;
	    }
	    System.out.println("Printing tests for arrs of size" + y + "....");
	    System.out.println(total/1000.0);
	    total = 0;

	}

    }//end main

}//end class QuickSort
