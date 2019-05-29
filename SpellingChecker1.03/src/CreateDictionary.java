//package main.java;
/*import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class CreateDictionary {
	
	private static final LinkedHashMap<String, Integer> dictionary = null;
	public static void main(String [] args) 
	 {
	    
	 }
	public CreateDictionary(String Path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(Path + "corpus-challenge4.txt")));
	
		//FileWriter FileWriter;	    // create your FileWriter and BufferedWriter
		 //FileWriter = new FileWriter(Path+"DictionaryF.txt");
		 BufferedWriter out = new BufferedWriter(new FileWriter(new File(Path + "dictionary.txt")));
		//String [] List = null ;
		String inputLine = null;
	
		LinkedHashMap<String, Integer> dictionary = new LinkedHashMap<String, Integer>();
		//ArrayList <String> mylist=new ArrayList <String>();
		
		while((inputLine = reader.readLine()) != null) {
			// Split the input line.
			
			String[] words = inputLine.split("\\s+");
					
			for(String word: words) {
					// Remove any commas and dots.
				word=removePunctuation(word);
			
				if(dictionary.containsKey(word.toLowerCase())) {
					Integer val = dictionary.get(word.toLowerCase());
					dictionary.put(word.toLowerCase(), val + 1);
					
				}
				else
					dictionary.put(word.toLowerCase(), 1);
			}
			
			
		}
	
		// Printing all words stored in the map.
		
		Set<Entry<String, Integer>> entries = dictionary.entrySet();
		Iterator<Entry<String, Integer>> iter = entries.iterator();
		
		while(iter.hasNext()) {
			//if (true) {
			Entry<String, Integer> entry = iter.next();
			//System.out.println(entry.getKey() + ": " + entry.getValue());
			 out.write(entry.getKey()+ " : "  + entry.getValue()+"\n");
			}
		//check Heap memory utilization for testing the code efficiency
		/*	for (MemoryPoolMXBean mpBean: ManagementFactory.getMemoryPoolMXBeans()) {
		    if (mpBean.getType() == MemoryType.HEAP) {
		        System.out.printf(
		            "Name: %s: %s\n",
		            mpBean.getName(), mpBean.getUsage()
		        );
		    }
		}*/
		
		 //close and flush buffer reader and writer   
		out.flush();
		out.close();
		reader.close();
	
	}
	//Removing characters different than a-z or A-Z 
	 private static String removePunctuation(String token) {
		    token = token.replaceAll("[^a-zA-Z]", "");
		    return token;
		  }
}