package function;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class StatisticsWord {
        private class Count{
            private int count;
            private Date date;

            private Count() {
                count = 0;
                date = new Date();
            }
        }

        private Map<String, ArrayList<Count>> listWord;

        public StatisticsWord() {
            listWord = new TreeMap<>();
        }

        public void addWord(String word) {
            if (listWord.containsKey(word)) {
                ArrayList<Count> listCount = listWord.get(word);
                Count count = listCount.get(listCount.size() - 1);
                if (count.date.getDate() == new Date().getDate()) {
                    count.count++;
                } else {
                    Count newCount = new Count();
                    newCount.count++;
                    listCount.add(newCount);
                }
            } else {
                ArrayList<Count> listCount = new ArrayList<>();
                Count count = new Count();
                count.count++;
                listCount.add(count);
                listWord.put(word, listCount);
            }
        }

        public void showList() {
            for (String word : listWord.keySet()) {
                System.out.print(word + " ");
                ArrayList<Count> listCount = listWord.get(word);
                for (Count count : listCount) {
                    System.out.println(count.count + " " + count.date);
                }
            }
        }
}
