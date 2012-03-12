package filesort;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import filesort.Hanzi2Pinyin;

//
public class FileNameSort {
	
	public static void main(String args[]){
		String foldername = "F:/";
		File folder = new File(foldername);
		File[] files = folder.listFiles();
		String[] filenames = folder.list();
		
		long starttime = System.nanoTime(); 
		
		Hanzi2Pinyin aa = new Hanzi2Pinyin();
		Arrays.sort(filenames, aa);
		
		long endtime = System.nanoTime();
		
		System.out.println(Arrays.toString(filenames));
		System.out.println("Time cost: "+(endtime - starttime)/1000000.0 + " ms");
	}

}
