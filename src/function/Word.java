package function;

import java.util.ArrayList;

public class Word implements Comparable<Word> {
    private String word;
    private ArrayList<String> meaning;
    private boolean isFavouriteWord;

    public Word() {
        this.word = "";
        this.meaning = new ArrayList<>();
    }

    public Word(String word, ArrayList<String> meaning, boolean isFavouriteWord) {
        this.word = word;
        this.meaning = meaning;
        this.isFavouriteWord = isFavouriteWord;
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
    public void setFavouriteWord(boolean isFavouriteWord) {
        this.isFavouriteWord = isFavouriteWord;
    }

    public boolean getFavouriteWord() {
        return isFavouriteWord;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.word);
    }
}
