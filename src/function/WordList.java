package function;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.TreeSet;

public class WordList {
    private Set<Word> listWord;
    public WordList() {
        listWord = new TreeSet<>();
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
            String[] arrMeaning = meaning.split("\n");
            ArrayList<String> listMeaning = new ArrayList<>(Arrays.asList(arrMeaning));
            Word w = new Word(word, listMeaning, false);
            listWord.add(w);
        }
    }

    public Word searchWord(String word) {
        Word res = new Word();
        for (Word w : listWord) {
            if (w.getWord().equals(word)) {
                res = w;
                break;
            }
        }
        return res;
    }

    public void clearList() {
        listWord.clear();
    }


    public void addWord(String word, ArrayList<String> meaning) {
        Word w = new Word(word, meaning, false);
        listWord.add(w);
    }

    public void removeWord(Word word) {
        listWord.remove(word);
        word.setWord("");
        word.setMeaning(new ArrayList<>());
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        WordList list = new WordList();
        list.readXMLFile("src\\data\\Anh_Viet.xml");
}
}
