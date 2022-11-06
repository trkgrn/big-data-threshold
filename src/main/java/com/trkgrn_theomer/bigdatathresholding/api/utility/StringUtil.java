package com.trkgrn_theomer.bigdatathresholding.api.utility;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StringUtil {


    public static final List<String > STOPWORDS = Arrays.asList("a", "as", "able", "about",
            "above", "after", "afterwards", "again", "against", "aint", "all",
            "allow", "allows", "alone", "along", "already",
            "also", "although", "always", "am", "an",
            "and", "another", "any", "anybody", "anyhow", "anyone", "anything",
            "anyway", "anyways", "anywhere", "apart", "appear", "appreciate",
            "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking",
            "associated", "at", "available", "away", "awfully", "be", "became", "because",
            "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being",
            "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both",
            "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes",
            "certain", "certainly", "changes", "clearly", "come",
            "comes", "concerning", "consequently", "consider", "considering", "contain",
            "containing", "contains", "corresponding", "could", "couldnt", "course", "currently",
            "definitely", "described", "despite", "did", "didnt", "different", "do", "does",
            "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "eight", "either", "else",
            "elsewhere", "enough", "entirely", "especially",
            "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere",
            "ex", "exactly", "example", "except", "far", "few", "fifth", "first", "five", "followed",
            "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further",
            "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone"
            , "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have",
            "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter",
            "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully",
            "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "if", "ignored", "immediate", "in",
            "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into",
            "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept",
            "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let",
            "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may",
            "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must",
            "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never",
            "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not",
            "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on",
            "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours",
            "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps",
            "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "rather",
            "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively",
            "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem",
            "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously",
            "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody",
            "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry",
            "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "take", "taken",
            "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their",
            "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby",
            "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve",
            "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout",
            "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try",
            "trying", "twice", "two", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up",
            "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via",
            "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome",
            "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where",
            "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which",
            "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish",
            "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd",
            "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero");


    public String extractPunctuations(String text) {
        String newtext = "";

        for (String word : text.split("[,.;:?!/&() ]")) {
            if (!word.equals("")) {
                newtext = newtext + " " + word.replace("'", "");
            }
        }

        return newtext.trim();
    }

    public String extractStopwords(String text) {
        List<String> wordsList = new ArrayList<String>();

        text = text.trim().replaceAll("\\s+", " ");
        String[] words = text.split(" ");

        for (String word : words) {
            wordsList.add(word);
        }


        wordsList = wordsList.stream().map(it -> {
            if (STOPWORDS.contains(it.toLowerCase(Locale.ENGLISH))) {
                return null;
            }

            return it;
        }).collect(Collectors.toList());

        wordsList.removeIf(Objects::isNull);
        String newText = "";
        for (String str : wordsList) {
            newText = newText + str + " ";
        }

        return newText.trim();
    }

    public double getSimilarityAverage(String[] data) {
        double similarityAverage = 0.0;
        int similarWordCount = 0;
        double wordCount = getLongDataWordCount(data[0], data[1]);
        int longDataIndex = getLongDataIndex(data[0], data[1]);
        int shortDataIndex = longDataIndex == 0 ? 1 : 0;


        for (String selectedWordByLongData : data[longDataIndex].split(" ")) {
            for (String selectedWordByShortData : data[shortDataIndex].split(" ")) {
                if (selectedWordByLongData.toLowerCase(Locale.ENGLISH).equals(selectedWordByShortData.toLowerCase(Locale.ENGLISH))) {
                    similarWordCount++;
                    break;
                }
            }
        }

        similarityAverage = (similarWordCount / wordCount) * 100.0;
        return similarityAverage;
    }


    private static int getLongDataWordCount(String data1, String data2) {
        String[] words1 = data1.split(" ");
        String[] words2 = data2.split(" ");
        int wordCount = words1.length >= words2.length ? words1.length : words2.length;
        return wordCount;
    }

    private static int getLongDataIndex(String data1, String data2) {
        String[] words1 = data1.split(" ");
        String[] words2 = data2.split(" ");
        int index = words1.length >= words2.length ? 0 : 1;
        return index;
    }
}
