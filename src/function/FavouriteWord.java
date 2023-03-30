package function;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FavouriteWord {
    private Map<String, Boolean> listFavouriteWord ;

    public FavouriteWord() {
        listFavouriteWord = new TreeMap<>();
    }

    public void addFavouriteWord(String word){
        listFavouriteWord.put(word, true);
    }

    public void removeFavouriteWord(String word) {
        if (listFavouriteWord.containsKey(word)) listFavouriteWord.remove(word);
    }

    public boolean isFavouriteWord(String word) {
        return listFavouriteWord.containsKey(word);
    }

    public ArrayList<String>  getListWord(){
        ArrayList<String> listWord = new ArrayList<>();
        for (String word : listFavouriteWord.keySet()) {
            listWord.add(word);
        }
        return listWord;
    }

    public void writeFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            file = new File(path);
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String word : listFavouriteWord.keySet()) {
            try {
                out.write(word.getBytes("UTF8"));
                out.write("\n".getBytes("UTF8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readFile(String path) {
        listFavouriteWord.clear();
        File file = new File(path);
        BufferedReader br = null;
        try {
            br =  new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "";
        while (true) {
            try {
                str = br.readLine();
                if (str == null) break;
                listFavouriteWord.put(str, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
