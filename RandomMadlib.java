public class RandomMadlib {

    private static int numRand = 3; // num random words offered to user
    private static int lenRandList = 11; // num words from each word type list

    // function calls private helper function of wordType to get words
    public String[] getOptions(String wordType) {
        String[] options;
        if (wordType.equals("noun")) {
            options = getNouns();
        }
        else if (wordType.equals("verb")) {
            options = getVerbs();
        }
        else {
            options = getAdjectives();
        }

        return options;
    }

    // function to return 3 random nouns
    private static String[] getNouns() {
        String[] nounsList = {
                "branch", "wood", "desk", "furniture", "burger",
                "meat", "snowball", "snow", "dollar", "money",
                "mountain"
        };
        String[] options = new String[numRand];
        for (int i = 0; i < numRand; i++) {
            int random = StdRandom.uniform(0, lenRandList);
            options[i] = nounsList[random];
        }
        return options;
    }

    // function to return 3 random verbs
    private static String[] getVerbs() {
        String[] verbList = {
                "act", "break", "coach", "cry", "dance", "win",
                "write", "whistle", "yank", "zip", "yell"
        };
        String[] options = new String[numRand];
        for (int i = 0; i < numRand; i++) {
            int random = StdRandom.uniform(0, lenRandList);
            options[i] = verbList[random];
        }
        return options;
    }

    // function to return 3 random adjectives
    private static String[] getAdjectives() {
        String[] adjectiveList = {
                "abundant", "accurate", "addicted",
                "adorable", "adventurous", "afraid",
                "aggressive", "alcoholic", "alert",
                "aloof", "ambitious"
        };
        String[] options = new String[numRand];
        for (int i = 0; i < numRand; i++) {
            int random = StdRandom.uniform(0, lenRandList);
            options[i] = adjectiveList[random];
        }

        return options;
    }

    // main method tests getNouns, get Verbs, and get Adjectives
    // nonstatic method getOptions is tested in Madlib.java
    public static void main(String[] args) {

        System.out.print("Three nouns:");
        String[] nouns = getNouns();
        for (int i = 0; i < numRand; i++) {
            System.out.print(" " + nouns[i]);
        }
        System.out.println();

        System.out.print("Three verbs:");
        String[] verbs = getVerbs();
        for (int i = 0; i < numRand; i++) {
            System.out.print(" " + verbs[i]);
        }
        System.out.println();

        System.out.print("Three adjectives:");
        String[] adjectives = getAdjectives();
        for (int i = 0; i < numRand; i++) {
            System.out.print(" " + adjectives[i]);
        }
        System.out.println();
    }
}
