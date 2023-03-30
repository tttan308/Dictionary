package function;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ListWordAdded {
    private Map<String, ArrayList<String>> listWord;

    public ListWordAdded() {
        listWord = new TreeMap<>();
    }

    public Map<String, ArrayList<String>> getListWord() {
        return listWord;
    }

    public void addWord(String word, ArrayList<String> meaning) {
        if(!listWord.containsKey(word)){
            listWord.put(word, meaning);
        }
        else{
            throw new IllegalArgumentException("Word is already exist");
        }
    }

    public void removeWord(String word) {
        if(listWord.containsKey(word)){
            listWord.remove(word);
        }
    }

    public void writeFile(String path){
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
        for(String word: listWord.keySet()){
            try {
                out.write(word.getBytes("UTF8"));
                out.write("\t".getBytes("UTF8"));
                ArrayList<String> meaning = listWord.get(word);
                for (int i = 0; i < meaning.size(); i++) {
                    out.write(meaning.get(i).getBytes("UTF8"));
                    if (i != meaning.size() - 1) out.write("\t".getBytes("UTF8"));
                }
                out.write("\n".getBytes("UTF8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readFile(String path){
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
                String[] word = str.split("\t");
                ArrayList<String> meaning = new ArrayList<>();
                for (int i = 1; i < word.length; i++) {
                    meaning.add(word[i]);
                }
                listWord.put(word[0], meaning);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
