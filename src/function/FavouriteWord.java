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
        listFavouriteWord.remove(word);
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

    public void showList(){
        for (String word : listFavouriteWord.keySet()) {
            System.out.println(word);
        }
    }
    public void writeFile(String path) {
        File file = new File(path);
        //Delete file if exist
        if (file.exists()) {
            file.delete();
            file = new File(path);
        }
        FileOutputStream out = null;
        try {
            //write with utf8
            out = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Write favourite word to file
        for (String word : listFavouriteWord.keySet()) {
            try {
                out.write(word.getBytes("UTF8"));
                out.write("\n".getBytes("UTF8"));
                out.write("mahoadongnaydedocfiledehonabcxyz\n".getBytes("UTF8"));

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
            //read with utf8
            br =  new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Read favourite word from file
        String str = "";
        while (true) {
            try {
                str = br.readLine();
                if (str == null) {
                    break;
                }
                if (str.equals("mahoadongnaydedocfiledehonabcxyz")) {
                    continue;
                }
                listFavouriteWord.put(str, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
