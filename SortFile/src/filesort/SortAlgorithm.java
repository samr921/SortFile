package filesort;

import java.util.Arrays;
import java.util.Random;

/*
 * vc:20111005
 * */
public class SortAlgorithm {
	
	public static final Random RND = new Random();
	
	public static void saSwap(int[] s, int x, int y){
		int t = s[x];
		s[x] = s[y];
		s[y] = t;
	}
	
	public static void saPrint(int[] s){
		if(s.length < 40){
			for(int i = 0; i < s.length; i++){
				System.out.print(s[i] + " ");
			}
			System.out.println();
		}
		else{
			for(int i = 0; i < 40; i++){
				System.out.print(s[i] + " ");
			}
			System.out.println();
		}
	}
	
	//插入排序
	public static void saInsertSort(int[] src){
		for(int i = 0; i < src.length; i++){
			for(int j = i; j>0 && src[j-1]>src[j]; j--){
				saSwap(src, j-1, j);
			}
		}
	}

	//希尔排序
    public static void saShellSort(int[] a){
    	for(int interval = a.length/2; interval > 0; interval /= 2){	//interval为分组间隔
    		for(int i = 0; i < interval; i ++){
    			for(int j = i; j < a.length; j += interval){
    				for(int t = j; t > interval - 1 && a[t-interval] > a[t]; t -= interval){
    					saSwap(a, t - interval, t);
    				}
    			}
    		}
    	}
    }
    
    //冒泡排序
    public static void saBubblingSort(int[] a){
    	boolean flag = true;
    	for(int i = 0; i < a.length; i++){
    		for(int j = a.length - 1; j > i; j--){
    			if(a[j-1] > a[j]){
    				saSwap(a, j-1, j);
    				flag = false;
    			}
    		}
    		if(flag)break;
    	}
    }
    
    //选择排序
    public static void saSelectionSort(int[] a){
    	int min = 0;
    	for(int i = 0; i < a.length - 1; i++){
    		min = i;
    		for(int j = i + 1; j < a.length; j++){
    			if(a[j] < a[min]){
    				min = j;
    			}
    		}
    		saSwap(a, min, i);
    	}
    }
    
	//快速排序
	private static int partition(int[] array, int begin, int end) {
        int index = begin + RND.nextInt(end - begin + 1);
        int pivot = array[index];
        saSwap(array, index, end);     
        for (int i = index = begin; i < end; i ++) {	//将小于pivot的数全部换到index前面
            if (array[i] <= pivot) {
                saSwap(array, index++, i);
            }
        }
        saSwap(array, index, end);     
        return index;
    }
 
    private static void qsort(int[] array, int begin, int end) {
        if (end > begin) {
            int index = partition(array, begin, end);
            qsort(array, begin, index - 1);
            qsort(array, index + 1,  end);
        }
    }
 
    public static void saQuickSort(int[] array) {
        qsort(array, 0, array.length - 1);
    }
   
    
    //归并排序
    private static void mergeSort(int[] src,int[] c, int low, int high) {
    	int length = high - low;
		
    	// Insertion sort on smallest arrays
    	if (length < 7) {
    		for (int i=low; i<high; i++)
    			for (int j=i; j>low && src[j-1] > src[j]; j--)
    				saSwap(src, j, j-1);
    			return;
    	}
    	// Recursively sort halves of src into c
    	int mid = (low + high) >>> 1;
    	mergeSort(c, src, low, mid);
    	mergeSort(c, src, mid, high);
				
    	// If list is already sorted, just copy from src to dest.  This is an
    	// optimization that results in faster sorts for nearly ordered lists.
    	if (c[mid-1] <= c[mid]){
    		System.arraycopy(c, low, src, low, length);
    		return;
    	}
    	// Merge sorted halves (now in src) into c
    	for(int i = low, p = low, q = mid; i < high; i++) {
    		if (q >= high || p < mid && c[p] <= c[q])
    			src[i] = c[p++];
    		else
    			src[i] = c[q++];
    	}
	}
    public static void saMergeSort(int[] a) {
    	int[] c = a.clone();
    	mergeSort(a, c, 0, a.length);
    }

				    
	public static void main(String args[]){
		int[] s = new int[100000];
		for(int i = 0; i < s.length; i++){
			s[i] = RND.nextInt(10000);
//			s[i] = i;
		}
		
		saPrint(s);
		int[] s1 = Arrays.copyOf(s, s.length);
		int[] s2 = Arrays.copyOf(s, s.length);
		int[] s3 = Arrays.copyOf(s, s.length);
		int[] s4 = Arrays.copyOf(s, s.length);
		int[] s5 = Arrays.copyOf(s, s.length);
		int[] s6 = Arrays.copyOf(s, s.length);
		
		long isortstart = System.nanoTime();
		saInsertSort(s1);
		long isortend = System.nanoTime();
		saPrint(s1);
		
		long shsortstart = System.nanoTime();
		saShellSort(s2);
		long shsortend = System.nanoTime();
		saPrint(s2);
		
		long busortstart = System.nanoTime();
		saBubblingSort(s3);
		long busortend = System.nanoTime();
		saPrint(s3);
		
		long sesortstart = System.nanoTime();
		saSelectionSort(s4);
		long sesortend = System.nanoTime();
		saPrint(s4);
		
		long qusortstart = System.nanoTime();
		saQuickSort(s5);
		long qusortend = System.nanoTime();
		saPrint(s5);
		
		long mesortstart = System.nanoTime();
		saMergeSort(s6);
		long mesortend = System.nanoTime();
		saPrint(s6);
		
		System.out.println("InsertSort	: " + (isortend - isortstart)/1000000.0);
		System.out.println("ShellSort	: " + (shsortend - shsortstart)/1000000.0);
		System.out.println("BubblingSort	: " + (busortend - busortstart)/1000000.0);
		System.out.println("SelectionSort	: " + (sesortend - sesortstart)/1000000.0);
		System.out.println("QuickSort	: " + (qusortend - qusortstart)/1000000.0);
		System.out.println("MergeSort	: " + (mesortend - mesortstart)/1000000.0);

	}

}
