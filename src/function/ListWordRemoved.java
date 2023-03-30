package function;

import java.io.*;
import java.util.ArrayList;

public class ListWordRemoved {
    private ArrayList<String> listWord;

    public ListWordRemoved() {
        listWord = new ArrayList<>();
    }

    public ArrayList<String> getListWord() {
        return listWord;
    }

    public void addWord(String word) {
        if(!listWord.contains(word)){
            listWord.add(word);
        }
    }

    public void writeFile(String path){
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            file = new File(path);
        }
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String word: listWord){
            try {
                out.write(word.getBytes("UTF8"));
                out.write("\n".getBytes("UTF8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readFile(String path) {
        File file = new File(path);
        listWord.clear();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "";
        while(true){
            try {
                str = br.readLine();
                if (str == null) break;
                listWord.add(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
