package com.trkgrn_theomer.bigdatathresholding.api.utility;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.LongData;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.SimilarityAverage;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class StringUtil {


    public static final List<String> STOPWORDS = Arrays.asList("a", "as", "able", "about",
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

        text = text.trim().replaceAll("\\s+", " "); // Arada birden fazla bosluk varsa tek bosluga indir
        String[] words = text.split(" ");

        for (String word : words) {
            wordsList.add(word);
        }

        wordsList = wordsList.stream().map(word -> {
            if (STOPWORDS.contains(word.toLowerCase(Locale.ENGLISH))) {
                return null; // Stopword varsa null döndür
            }
            return word;
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
        LongData longData = getLongData(data[0], data[1]);
        double wordCount = longData.getWordCount();
        int shortDataIndex = longData.getIndex() == 0 ? 1 : 0;

        for (String selectedWordByLongData : data[longData.getIndex()].split(" ")) {
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

    private static LongData getLongData(String data1, String data2) {
        String[] words1 = data1.split(" ");
        String[] words2 = data2.split(" ");
        int wordCount = words1.length >= words2.length ? words1.length : words2.length;
        int index = words1.length >= words2.length ? 0 : 1;
        return new LongData(index, wordCount);
    }

    public List<String> distinctColumn(List<Complaint> allData, String selectedColumn) {
        return allData.stream().parallel().map(data -> {
                    Field field = null;
                    try {
                        field = data.getClass().getDeclaredField(selectedColumn);
                        field.setAccessible(true);
                        return field.get(data).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList())
                .parallelStream()
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> distinctColumnWithSet(List<Complaint> allData, String selectedColumn) {
        Set<String> set = new HashSet<>();
        switch (selectedColumn) {
            case "Product":
                set = allData.parallelStream().map(data -> data.getProduct()).collect(Collectors.toSet());
                break;
            case "Issue":
                set = allData.parallelStream().map(data -> data.getIssue()).collect(Collectors.toSet());
                break;
            case "Company":
                set = allData.parallelStream().map(data -> data.getCompany()).collect(Collectors.toSet());
                break;
            case "State":
                set = allData.parallelStream().map(data -> data.getState()).collect(Collectors.toSet());
                break;
            case "ZIP Code":
                set = allData.parallelStream().map(data -> data.getZipCode()).collect(Collectors.toSet());
                break;
        }
        return set.stream().collect(Collectors.toList());
    }

    public List<Complaint> getComplaintsByPropertyWithSet(List<Complaint> allData, String selectedColumn, String value) {
        Set<Complaint> set = new HashSet<>();
        switch (selectedColumn) {
            case "Product":
                set = allData.parallelStream().map(data -> getComplaint(data, data.getProduct(), value)).collect(Collectors.toSet());
                break;
            case "Issue":
                set = allData.parallelStream().map(data -> getComplaint(data, data.getIssue(), value)).collect(Collectors.toSet());
                break;
            case "Company":
                set = allData.parallelStream().map(data -> getComplaint(data, data.getCompany(), value)).collect(Collectors.toSet());
                break;
            case "State":
                set = allData.parallelStream().map(data -> getComplaint(data, data.getState(), value)).collect(Collectors.toSet());
                break;
            case "ZIP Code":
                set = allData.parallelStream().map(data -> getComplaint(data, data.getZipCode(), value)).collect(Collectors.toSet());
                break;
        }
        set.remove(null);
        return set.stream().collect(Collectors.toList());
    }

    private Complaint getComplaint(Complaint complaint, String data, String value) {
        if (data.equals(value))
            return complaint;
        return null;
    }


    public List<Complaint> getComplaintsByProperty(List<Complaint> allData, String selectedColumn, String value) {

        return allData.parallelStream().map(data -> {
                    Field field = null;
                    try {
                        field = data.getClass().getDeclaredField(selectedColumn);
                        field.setAccessible(true);

                        if (field.get(data).toString().equals(value))
                            return data;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList())
                .parallelStream()
                .filter(complaint -> Objects.nonNull(complaint))
                .collect(Collectors.toList());
    }

    public List<SimilarityAverage> getSimilarityAverages(List<String> data, double minSimilarity) {
        List<SimilarityAverage> similarityAverages = new ArrayList<>();

        data.stream().forEach(data1 -> {
            data.stream().forEach(data2 -> {
                double similarity = getSimilarityAverage(new String[]{data1, data2});
                if (similarity >= minSimilarity)
                    similarityAverages.add(new SimilarityAverage(data1, data2, similarity));
            });
        });
        return similarityAverages;
    }


}
