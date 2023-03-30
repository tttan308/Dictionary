package function;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

public class ListWord {
    private Map<String, ArrayList<String>> listWord;
    public ListWord() {
        listWord = new TreeMap<>();
    }

    public void readXMLFile(String path) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
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
        return listWord.containsKey(word.trim());
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

    public ArrayList<String> getMeaning(String word){
        return listWord.get(word);
    }

    public void removeListWordRemoved(ListWordRemoved listWordRemoved){
        for(String word: listWordRemoved.getListWord()){
            listWord.remove(word);
        }
    }

    public void addListWordAdded(ListWordAdded listWordAdded){
        for(String word: listWordAdded.getListWord().keySet()){
            if(!listWord.containsKey(word)){
                listWord.put(word, listWordAdded.getListWord().get(word));
            }
            else{
                listWord.remove(word);
                listWord.put(word, listWordAdded.getListWord().get(word));
            }
        }
    }
}
