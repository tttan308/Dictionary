package function;

import java.util.ArrayList;
import java.util.TreeSet;

public class FavouriteWord {
    private TreeSet<Word> listFavouriteWord ;

    public FavouriteWord() {
        listFavouriteWord = new TreeSet<>();
    }
    public void addFavouriteWord(Word word) {
        listFavouriteWord.add(word);
    }
    public void removeFavouriteWord(Word word) {
        listFavouriteWord.remove(word);
    }

    public void showList(){
        for (Word w : listFavouriteWord) {
            System.out.println(w.getWord());
        }
    }

    public ArrayList<String>  getListWord(){
        ArrayList<String> listWord = new ArrayList<>();
        for (Word w : listFavouriteWord) {
            listWord.add(w.getWord());
        }
        return listWord;
    }
}
