package function;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class WordList {
    private TreeMap<String, ArrayList<String>> listWord ;
    public WordList() {
        listWord = new TreeMap<>();
    }

    public void readXMLFile(String path) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList list = doc.getElementsByTagName("record");
        for (int i = 0; i < list.getLength(); i++) {
            String word = list.item(i).getChildNodes().item(1).getTextContent();
            String meaning = list.item(i).getChildNodes().item(3).getTextContent();
            meaning = meaning.substring(0, meaning.lastIndexOf("\n"));
            meaning = meaning.replace("+", ":");
            ArrayList<String> listMeaning = new ArrayList<>();
            String[] arrMeaning = meaning.split("\n");
            for (String s : arrMeaning) {
                listMeaning.add(s);
            }
            listWord.put(word, listMeaning);
        }
    }

    public Word searchWord(String word) {
        Word res = new Word();
        ArrayList<String> meaning = new ArrayList<>();
        meaning = listWord.get(word);
        if (meaning != null) {
            res.setWord(word);
            res.setMeaning(meaning);
        }
        return res;
    }

    public void clearList() {
        listWord.clear();
    }

    public boolean checkExists(String word) {
        return listWord.containsKey(word);
    }
    public void addWord(String word, ArrayList<String> meaning) {
        if(!checkExists(word)) {
            listWord.put(word, meaning);
        }
        else{
            throw new RuntimeException("Word already exists");
        }
    }

    public void removeWord(Word word) {
        listWord.remove(word.getWord());
        word.setWord("");
        word.setMeaning(new ArrayList<>());
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        WordList list = new WordList();
        list.readXMLFile("src\\data\\Anh_Viet.xml");
}
}
