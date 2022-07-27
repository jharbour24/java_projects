public class Madlib {

    private static Queue<String> madlib; // queue of words and blanks in madlib
    private static Queue<String> blanksList; // queue of wordType for blanks

    // function to check word and add it to madlib or blanksList queue
    public static void parseMadlib(String word) {
        int n = word.length();
        char lastChar = word.charAt(n - 1);
        // if word is a blank
        if (word.startsWith("_")) {
            String wordType = word.substring(1);
            // add wordType to blanksList (without any punctuation)
            if (isPunc(lastChar)) {
                blanksList.enqueue(word.substring(1, n - 1));
                madlib.enqueue("_" + lastChar);
            }
            else {
                blanksList.enqueue(wordType);
                madlib.enqueue("_");
            }
        }
        // if word isn't a blank, append to madlib
        else madlib.enqueue(word);
    }

    // function to check if last character of word is punctuation
    private static boolean isPunc(char c) {
        return (c == '.' || c == '!');
    }

    // function to throw error if user input is not in range
    private static void throwError() {
        throw new IllegalArgumentException(
                "The number entered is not between the specified parameters.");
    }

    // function to print intro of madlib game for user
    public static void gameIntro() {
        System.out.println("Welcome to MadLibs!");
        System.out.println("Would you like to input your own words or "
                                   + "be given choices to help with "
                                   + "your decision?");
        System.out.println("Enter 0 for your own words or 1 for a set list.");
    }

    // function to complete madlib with blanks with words from wordsList
    public static String completeStory(Queue<String> wordsList) {
        StringBuilder completedStory = new StringBuilder();
        for (String word : madlib) {
            if (word.startsWith("_")) {
                // replace blank with noun, verb, or adj
                completedStory.append(wordsList.dequeue());
                // add back punctuation if necessary
                char lastChar = word.charAt(word.length() - 1);
                if (isPunc(lastChar)) {
                    completedStory.append(lastChar);
                }
                completedStory.append(" ");
            }
            else completedStory.append(word + " ");
        }
        return completedStory.toString();
    }

    // main function to interact with madlib game user
    // tests parseMadlib, isPunc, throwError, gameIntro, completeStory
    // (also tests getOptions from RandomMadlib.java)
    public static void main(String[] args) {

        // used IN object to read in madlib
        In madlibFile = new In(args[0]);
        madlib = new Queue<String>();
        blanksList = new Queue<String>();

        // call parseMadlib for each word in madlib
        while (!madlibFile.isEmpty()) {
            // get next word in madlib
            String word = madlibFile.readString();
            parseMadlib(word);
        }

        // print game intro, ask user to choose gameType 0 or 1
        gameIntro();
        int gametype = StdIn.readInt();
        if (gametype > 1 || gametype < 0) throwError();
        // list of words to fill blanks
        Queue<String> wordsList = new Queue<String>();
        // for user-input version of game
        if (gametype == 0) {
            // get user input words for blanks
            for (String wordType : blanksList) {
                System.out.println("Enter a " + wordType);
                String blankWord = StdIn.readString();
                wordsList.enqueue(blankWord);
            }
        }
        // for set list version of game
        else {
            RandomMadlib rand = new RandomMadlib();
            for (String wordType : blanksList) {
                // get 3 word options from set list
                String[] options = rand.getOptions(wordType);
                System.out.println(
                        "Which " + wordType + " would you like to use?");
                System.out.println(
                        options[0] + ", " + options[1] + ", or " + options[2] +
                                "? Enter 1, 2, or 3 to indicate your choice.");
                int choice = StdIn.readInt();
                if (choice > 3 || choice < 1) throwError();
                wordsList.enqueue(options[choice - 1]);
            }
        }

        // create completed madlib with user's words
        String completedStory = completeStory(wordsList);
        System.out.println(completedStory);
    }

}
