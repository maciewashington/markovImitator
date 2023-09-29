import java.io.*;
import java.util.*;
//import java.io.File;


public class markov {
    private static Random r = new Random();

     static final int MAXGEN = 100;
    // static final int MINGEN = 200;
    // static final int LINE_LENGTH = 75;
     static final String DEFAULT_INPUT_FILE = "Ch1WizardOfOz.txt";
    static int NPREF = 3;

    private static String markov(String filePath, int keySize, int outputSize) throws IOException {
        if (keySize < 1) throw new IllegalArgumentException("Key size can't be less than 1");
        // List<String> listOfStrings = new ArrayList<String>();
    
        // Scanner sc = new Scanner(new FileReader(filePath)).useDelimiter(",\\s*");
        // String str;
       
        // // checking end of file
        // while (sc.hasNext()) {
        //     str = sc.next();
           
        //     // adding each string to arraylist
        //     listOfStrings.add(str);
        // }
       
        // convert any arraylist to array
        // String[] words = listOfStrings.toArray(new String[0]);


        ArrayList<String> wordList = MacieLibrary.readTheFileToArrayList(DEFAULT_INPUT_FILE);
        //ArrayList<String> wordList = MacieLibrary.seperateIntoWords(fileReader);
        String[] words = wordList.toArray(new String[0]);
        // String[] words = new String[wordList.size()];
        // words = wordList.toArray(words);
    
      
        if (outputSize < keySize || outputSize >= words.length) {
            throw new IllegalArgumentException("Output size is out of range");
        }
        Map<String, List<String>> dict = new HashMap<>();

        for (int i = 0; i < (words.length - keySize); ++i) {
            StringBuilder key = new StringBuilder(words[i]);
            for (int j = i + 1; j < i + keySize; ++j) {
                key.append(' ').append(words[j]);
            }
            String value = (i + keySize < words.length) ? words[i + keySize] : "";
            if (!dict.containsKey(key.toString())) {
                ArrayList<String> list = new ArrayList<>();
                list.add(value);
                dict.put(key.toString(), list);
            } else {
                dict.get(key.toString()).add(value);
            }
        }

        int n = 0;
        int rn = r.nextInt(dict.size());
        String prefix = (String) dict.keySet().toArray()[rn];
        List<String> output = new ArrayList<>(Arrays.asList(prefix.split(" ")));

        while (true) {
            List<String> suffix = dict.get(prefix);
            if (suffix.size() == 1) {
                if (Objects.equals(suffix.get(0), "")) return output.stream().reduce("", (a, b) -> a + " " + b);
                output.add(suffix.get(0));
            } else {
                rn = r.nextInt(suffix.size());
                output.add(suffix.get(rn));
            }
            if (output.size() >= outputSize) return output.stream().limit(outputSize).reduce("", (a, b) -> a + " " + b);
            n++;
            prefix = output.stream().skip(n).limit(keySize).reduce("", (a, b) -> a + " " + b).trim();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(markov(DEFAULT_INPUT_FILE, NPREF, MAXGEN));
    }
}

