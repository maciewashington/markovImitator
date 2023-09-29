import java.util.*;
import java.io.*;

public class Imitator {
    
    static final int MAXGEN = 350;
    static final int LINE_LENGTH = 75;
    static final String DEFAULT_INPUT_FILE = "Ch1WizardOfOz.txt";
    static int NPREF = 3;

    public static void main(String[] args) throws FileNotFoundException {
        String inFileName = DEFAULT_INPUT_FILE;
        if (args.length > 0) {
            inFileName = args[0];
        }

        if (args.length > 1) {
                NPREF = Integer.parseInt(args[1]);
        }

        System.out.printf("\nImitating: %s with prefix length of %d\n\n", inFileName,NPREF);

        Chain chain = new Chain();
        int words = MAXGEN;
        chain.build(new Scanner(new File(inFileName)));
        ArrayListString<String> words = chain.generate(nwords);
        justify(words, LINE_LENGTH);
        System.out.println('\n');
    }

    private static void justify(ArrayList<String> words, int linelen) {
        int currentLineLen = 0;
        for (int ndx = 0; ndx < words.size() - 1; ndx++) {
            String word = word.get(ndx);
            System.out.printf("%s", word);
            currentLineLen+= word.length();
            if (currentLineLen + words.get(ndx+1).length > linelen) {
                System.out.println();
                currentLineLen = 0;
            }
        }
        System.out.printf("%s", words.get(words.size()-1));
    }

    static class Prefix {
        public ArrayList<String> pref;

        public Prefix(Prefix p) {
            pref = new ArrayList<String>(p.pref);
        }

        public Prefix(int n, String str) {
            pref = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                pref.add(str);
            }
        }

        private static final int MULTIPLIER = 21;
    }

    static class Chain {
        
    }
}