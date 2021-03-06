public class QuickSortTester {

    public static void main (String[]args) {
	int[] warm = QuickSort.buildArray(2000,1000);
	//warming up
	for (int x = 0; x < 1000; x++){
	    QuickSort.shuffle (warm);					
	    QuickSort.qsort(warm);
	}

	//testing
	System.out.println("n, time");
	double start;
	double end;
	double total = 0;
	for (int y = 1000; y <= 50000; y+= 100){//starting at a size 1000 arr go up til a size 50000 arr and test timings
	    warm = QuickSort.buildArray(y,1000);
	    start = System.currentTimeMillis();
	    for (int x = 0; x < 1000; x++){//run each test 1000 times for precision
		QuickSort.shuffle(warm);
		start = System.nanoTime();
		QuickSort.qsort(warm);
		end = System.nanoTime();    
		total += end - start;
	    }	    
	    
	    System.out.println(y + "," + total/1000.0);
	    total = 0;
	}
    }
}
