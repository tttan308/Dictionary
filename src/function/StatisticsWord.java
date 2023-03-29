package function;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;

public class StatisticsWord {
        private class Word{
            private String word;
            private int count;

            public Word(String word, int count) {
                this.word = word;
                this.count = count;
            }
        }

        private Map<String, Map<LocalDate, Integer>> listWord;

        public StatisticsWord() {
            listWord = new TreeMap<>();
        }

        public void addWord(String word) {
            LocalDate date = LocalDate.now();
            if (listWord.containsKey(word)) {
                Map<LocalDate, Integer> listCount = listWord.get(word);
                if (listCount.containsKey(date)) {
                    int count = listCount.get(date);
                    listCount.put(date, count + 1);
                } else {
                    listCount.put(date, 1);
                }
            } else {
                Map<LocalDate, Integer> listCount = new TreeMap<>();
                listCount.put(date, 1);
                listWord.put(word, listCount);
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
                                out.write("\n".getBytes("UTF8"));
                                Map<LocalDate, Integer> listCount = listWord.get(word);
                                for(LocalDate date: listCount.keySet()){
                                        out.write(listCount.get(date).toString().getBytes("UTF8"));
                                        out.write("\t".getBytes("UTF8"));
                                        out.write(date.toString().getBytes("UTF8"));
                                        out.write("\t".getBytes("UTF8"));
                                }
                                out.write("\n".getBytes("UTF8"));
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        }
        public void readFile(String path) {
            listWord.clear();
            File file = new File(path);
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String str = "";
            String word = "";
            while (true) {
                try {
                    str = br.readLine();
                    if (str == null) break;
                    word = str;
                    str = br.readLine();
                    String[] arrStr = str.split("\t");
                    Map<LocalDate, Integer> listCount = new TreeMap<>();
                    for (int i = 0; i < arrStr.length; i += 2) {
                        int count = Integer.parseInt(arrStr[i]);
                        LocalDate date = LocalDate.parse(arrStr[i + 1]);
                        listCount.put(date, count);
                    }
                    listWord.put(word, listCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public Map<String, Integer> getStatisticsWord(String dateBegin, String dateEnd){
            //date : dd/mm/yyyy
            Map<String, Integer> listStatisticsWord = new TreeMap<>();
            String[] arrDateBegin = dateBegin.split("/");
            String[] arrDateEnd = dateEnd.split("/");
            LocalDate begin = LocalDate.of(Integer.parseInt(arrDateBegin[2]), Integer.parseInt(arrDateBegin[1]), Integer.parseInt(arrDateBegin[0]));
            LocalDate end = LocalDate.of(Integer.parseInt(arrDateEnd[2]), Integer.parseInt(arrDateEnd[1]), Integer.parseInt(arrDateEnd[0]));
            for(String word: listWord.keySet()){
                Map<LocalDate, Integer> listCount = listWord.get(word);
                int count = 0;
                for(LocalDate date: listCount.keySet()){
                    if (date.compareTo(begin) >= 0 && date.compareTo(end) <= 0) {
                        count += listCount.get(date);
                    }
                }
                if (count > 0) {
                    listStatisticsWord.put(word, count);
                }
            }
            //print
            for(String word: listStatisticsWord.keySet()){
                System.out.println(word + " " + listStatisticsWord.get(word));
            }
            return listStatisticsWord;
        }
}
