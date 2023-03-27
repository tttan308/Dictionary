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

public class ListWord {
    private Map<String, ArrayList<String>> listWord;
    public ListWord() {
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
            String[] arrMeaning = meaning.split("\n");
            ArrayList<String> listMeaning = new ArrayList<>(Arrays.asList(arrMeaning));
            listWord.put(word, listMeaning);
        }
    }

    public boolean searchWord(String word) {
        return listWord.containsKey(word);
    }

    public void clearList() {
        listWord.clear();
    }

    public void addWord(String word, ArrayList<String> meaning) {
        listWord.put(word, meaning);
    }

    public void removeWord(String word) {
        listWord.remove(word);
    }

    public ArrayList<String> getMeaning(String word){
        ArrayList<String> listMeaning = listWord.get(word);
        return listMeaning;
    }
}
