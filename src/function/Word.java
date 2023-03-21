package function;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<String> meaning;

    public Word() {
        this.word = "";
        this.meaning = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getMeaning() {
        return meaning;
    }

    public void setMeaning(ArrayList meaning) {
        this.meaning = meaning;
    }

}
