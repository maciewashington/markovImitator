import java.util.*;
import java.io.*;

public class MacieLibrary {
    public static ArrayList<String> readTheFileToArrayList(String fileName) {
        ArrayList<String> file_contents = new ArrayList<>();
        File theFile = new File(fileName);
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while(fileScanner.hasNextLine()){
                String oneLineFromFile = fileScanner.nextLine();
                file_contents.add(oneLineFromFile);

            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error accessing file");
            System.exit(0);
        }
        return file_contents;
    }

      public static ArrayList<String> seperateIntoWords(ArrayList<String> lines){
        ArrayList<String> words = new ArrayList<>();
        for (String singleLine : lines) {
            String[] wordsFromLine = singleLine.toLowerCase().split(" ");
            // for (String w : wordsFromLine) {
            //     String noPunct = stripMethod(w);
            //     //noPunct.toLowerCase()
            //     words.add(noPunct); //noPunct.toLowercase();
            // }
        }
        
        return words;
      }
}