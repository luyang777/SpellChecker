import java.io.*;
import java.util.ArrayList;

public class SpellingChecker {
	
	 BloomFilter <String> dictionary;   
	 boolean suggestwords ;  
	 public ArrayList<String> myList;
 
 public String  res;
	
 public static void main(String [] args) 
 {
    
 }
//Removing characters different than a-z or A-Z 
	 private static String removePunctuation(String token) {
		 if(token == "Sorry but no possible corrections found!"){
		 return token;
		 }
		 else {
		    token = token.replaceAll("[^a-zA-Z]", "");
		    return token;
		 }
		  }
 
 public  SpellingChecker(String Path,String [] List ,String filename) 
 {
 	 myList = new ArrayList();
     dictionary = new BloomFilter<String>(0.24,99000);      // Assigning a false positive probability of 0.24
     
     
     try 
     {
         
         //Read and store the words of the dictionary in memory 
         BufferedReader dictionaryReader = new BufferedReader(new FileReader(Path + "dictionary.txt"));
         
         while (dictionaryReader.ready()) 
         {
             String dictionaryInput = dictionaryReader.readLine() ;
             String [] dictValue = dictionaryInput.split("\\s");
             
             for(int k = 0; k < dictValue.length;k++) 
             {
                 // key and value are identical
                 dictionary.add(dictValue[k]);
             }
         }
         dictionaryReader.close();
        //end of dictionary reading 
         
		 //input file name acceptor
         String file = filename;
        // Read and check the input from the text file 
         BufferedReader inputFile = new BufferedReader(new FileReader(file));
       
         // Initializing a spelling suggest object
         spellingsuggest suggestProbableWord = new spellingsuggest(Path + "corpus-challenge4.txt");
     
         // Reads input lines one by one
         while ( inputFile.ready() ) 
         {
         	//Check if the file name is empty
             String s = inputFile.readLine() ;
             if(s.length()==1)
             	continue;
             if(s.isEmpty())
             	continue;
          
             String[] result = s.split("\\s");
           
              for (int x=0; x<result.length; x++) 
             {
                 suggestwords = true;
                 String outputwords = checkMywords(result[x]);
                 List = result;
                 if(suggestwords)
                 {  
                      res = suggestProbableWord.correct(removePunctuation(outputwords)).toString();
                      List =res.split("\n");
                 }
             }
                                            
              for (int sub = 0; sub < List.length; sub++){
                  myList.add(List[sub]);
              }
                         
         }
         String show = new String();
         for (int sub = 0; sub < myList.size(); sub++)
             show += removePunctuation(myList.get(sub))+ "\n";
     	
			 
			
         spellckeckerGUI.SpelligCheckerTA.setText(show);
         inputFile.close();
     }
     catch (IOException e) 
     {
         System.out.println("IOException Occured! ");
         
         e.printStackTrace();
   
         filename=null;
     }
 }
 


	public String checkMywords(String wordsToCheck) 
 {
     String removepunctwords;
     String words = wordsToCheck.toLowerCase();
            words=removePunctuation(words);
     
     // if words is found in dictionary then it is spell correctly, so return as it is.
     //note: erroneous like "ing" provided in the dictionary itself.
     if ( dictionary.contains(words) )
     {
         suggestwords = false;            //  suggestion for a correct words not required.
         return wordsToCheck;
     }
     
     // Removing punctuation at end of words and giving it a try ("." or "." or "?!")
     int length = words.length();
     
     // Checking if an extra 'es' or "ed" or " ,"" is the problem (example: watches when watch is present in the dictionary)
     if (length > 2 && words.substring(length-2).equals(",\"")  || words.substring(length-2).equals(".\"") 
         || words.substring(length-2).equals("?\"") || words.substring(length-2).equals("!\"") )
     {
         removepunctwords = words.substring(0, length-2);
    	          
         if ( dictionary.contains(removepunctwords) )
         {
             suggestwords = false;            // no need to ask for suggestion for a correct words.
             return wordsToCheck ;
         }
         else // not found
             return removepunctwords;                  // removing the inflection and returning
     }
     
     
  
     return words;
     
 }
	
}

