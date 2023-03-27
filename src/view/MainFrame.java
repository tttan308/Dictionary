package view;

import function.FavouriteWord;
import function.ListWord;
import function.StatisticsWord;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainFrame {
    JFrame frame;
    JPanel bodyPanel;
    private ListWord list = new ListWord() ;
    private boolean language = true; //true: English - Vietnamese, false: Vietnamese - English
    private boolean firstAction = true;
    private FavouriteWord listFavouriteWord = new FavouriteWord();
    private boolean isFavouriteWord = false;
    private String word = "";

    private StatisticsWord statisticsWord = new StatisticsWord();
    public MainFrame() throws ParserConfigurationException, IOException, SAXException {
        frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(!language){
            list.readXMLFile("src\\data\\Viet_Anh.xml");
            listFavouriteWord.writeFile("src\\data\\FavouriteWord_Viet.txt");
            listFavouriteWord.showList();
            listFavouriteWord.readFile("src\\data\\FavouriteWord_Anh.txt");
        }
        else {
            list.readXMLFile("src\\data\\Anh_Viet.xml");
            listFavouriteWord.writeFile("src\\data\\FavouriteWord_Eng.txt");
            listFavouriteWord.showList();
            listFavouriteWord.readFile("src\\data\\FavouriteWord_Viet.txt");
        }

        JPanel headerPanel = getHeaderFrame();
        JPanel bodyPanel = getBodyFrame();
        frame.add(headerPanel, BorderLayout.PAGE_START);

        frame.add(bodyPanel, BorderLayout.CENTER);
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#A1A2A9")));
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(650, 150));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel getHeaderFrame() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout());
        JLabel logoIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/research2.png"))));
        logoIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //Show JMenuItem when click on logo
        logoIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem item1 = new JMenuItem("Favorite list word");
                JMenuItem item2 = new JMenuItem("Statistics look up word");
                popupMenu.add(item1);
                popupMenu.add(item2);
                popupMenu.show(logoIcon, 30, 40);
                item1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame favouriteFrame = new JFrame("Favorite list word");
                        favouriteFrame.setLayout(new FlowLayout());
                        JPanel favouritePanel = showFavouriteList();
                        favouriteFrame.add(favouritePanel);
                        favouriteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        favouriteFrame.setSize(400, 400);
                        favouriteFrame.setLocationRelativeTo(null);
                        favouriteFrame.setVisible(true);
                    }
                });

                item2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame statisticFrame = new JFrame("Statistics look up word");
                        statisticFrame.setLayout(new FlowLayout());
                        JPanel statisticPanel = showStatistic();
                        statisticFrame.add(statisticPanel);
                        statisticFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        statisticFrame.setSize(400, 400);
                        statisticFrame.setLocationRelativeTo(null);
                        statisticFrame.setVisible(true);
                    }
                } );
            }
        });
        logoIcon.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 20));
        logoPanel.add(logoIcon);
        headerPanel.add(logoPanel, BorderLayout.LINE_START);

        //Search box
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        JTextField searchField = new RoundedTextField(10);
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel searchIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/search.png"))));
        searchIcon.setBorder(BorderFactory.createEmptyBorder(6, 5, 5, 5));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wordSearch = searchField.getText();
                if(list.searchWord(wordSearch) == true){
                    word = wordSearch;
                }
                else{
                    word = "";
                }
                isFavouriteWord = listFavouriteWord.isFavouriteWord(word);
                statisticsWord.addWord(word);
                statisticsWord.showList();
                if (word != null) {
                    frame.remove(bodyPanel);
                    bodyPanel = getBodyFrame();
                    frame.add(bodyPanel, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter a word to search!");
                }
            }
        });

        searchIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String wordSearch = searchField.getText();
                if(list.searchWord(wordSearch) == true){
                    word = wordSearch;
                }
                else{
                    word = "";
                }
                isFavouriteWord = listFavouriteWord.isFavouriteWord(word);
                System.out.println(isFavouriteWord);
                if (word != null) {
                    frame.remove(bodyPanel);
                    bodyPanel = getBodyFrame();
                    frame.add(bodyPanel, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter a word to search!");
                }
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchIcon);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 6, 5));
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel rightHeaderPanel = new JPanel();
        rightHeaderPanel.setLayout(new FlowLayout());

        JLabel addIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/add.png"))));
        addIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //action
        addIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //change img
                if(addIcon.getIcon().toString().contains("add-active.png"))
                    addIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/add.png"))));
                else if(addIcon.getIcon().toString().contains("add.png"))
                    addIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/add-active.png"))));

                JFrame addFrame = new JFrame("Add new word");
                addFrame.setLayout(new BorderLayout());
                addFrame.setSize(450, 250);
                JPanel addPanel = addPanel(addFrame, addIcon);
                addFrame.add(addPanel, BorderLayout.CENTER);
                addFrame.setLocationRelativeTo(null);
                addFrame.setVisible(true);
                addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //action when click close addFrame
                addFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        addIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/add.png"))));
                    }
                });
            }
        });

        JPanel changeLanguagesPanel = new JPanel();
        JLabel changeLanguages = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/world.png"))));
        changeLanguagesPanel.add(changeLanguages);

        JLabel changeLanguagesText = new JLabel("English - Vietnamese");
        changeLanguagesText.setFont(new Font("Arial", Font.PLAIN, 20));
        changeLanguages.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changeLanguagesPanel.add(changeLanguagesText);
        changeLanguagesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 20));

        rightHeaderPanel.add(addIcon);
        rightHeaderPanel.add(changeLanguagesPanel);
        headerPanel.add(rightHeaderPanel, BorderLayout.LINE_END);
        changeLanguages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //change img
                if(changeLanguages.getIcon().toString().contains("world-active.png")){
                    changeLanguages.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/world.png"))));
                    changeLanguagesText.setText("English - Vietnamese");
                    list.clearList();
                    try {
                        list.readXMLFile("src//data//Anh_Viet.xml");
                        listFavouriteWord.writeFile("src\\data\\FavouriteWord_Viet.txt");
                        listFavouriteWord.showList();
                        listFavouriteWord.readFile("src\\data\\FavouriteWord_Eng.txt");
                    } catch (ParserConfigurationException | IOException | SAXException ex) {
                        throw new RuntimeException(ex);
                    }
                    language = true;
                }
                else if(changeLanguages.getIcon().toString().contains("world.png")){
                    changeLanguages.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/world-active.png"))));
                    changeLanguagesText.setText("Vietnamese - English");
                    list.clearList();
                    try {
                        list.readXMLFile("src//data//Viet_Anh.xml");
                        listFavouriteWord.writeFile("src\\data\\FavouriteWord_Anh.txt");
                        listFavouriteWord.showList();
                        listFavouriteWord.readFile("src\\data\\FavouriteWord_Viet.txt");
                    } catch (ParserConfigurationException | IOException | SAXException ex) {
                        throw new RuntimeException(ex);
                    }
                    language = false;
                }
            }
        });

        //set a horizontal line with black color
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BorderLayout());
        horizontalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#A1A2A9")));
        headerPanel.add(horizontalPanel, BorderLayout.PAGE_END);
        return headerPanel;
    }

    public JPanel getBodyFrame() {
        bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        ArrayList<String> meaning;
        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new BorderLayout());
        wordPanel.setPreferredSize(new Dimension(400, 30));
        String vocabulary;
        if(word.equals("") && !firstAction ){
            vocabulary = " NOT FOUND!";
            meaning = new ArrayList<>();
            meaning.add("Please enter a word to search!");
        }
        else{
            vocabulary = word;
            meaning = list.getMeaning(word);
        }
        JLabel wordLabel = new JLabel(vocabulary);

        wordLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        wordLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        wordPanel.add(wordLabel, BorderLayout.LINE_START);
        JPanel reactionPanel = new JPanel();
        JLabel removeIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/remove.png"))));
        removeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //change icon
                if(removeIcon.getIcon().toString().contains("remove-active.png"))
                    removeIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/remove.png"))));
                else if(removeIcon.getIcon().toString().contains("remove.png"))
                    removeIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/remove-active.png"))));
                JOptionPane.showMessageDialog(null, "Remove successfully!");
                list.removeWord(word);
                bodyPanel.removeAll();
                bodyPanel.revalidate();
                bodyPanel.repaint();
            }
        });

        JLabel heartIcon;
        if(isFavouriteWord) heartIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/heart-active.png"))));
        else heartIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/heart.png"))));
        heartIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        heartIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        heartIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //change icon
                if(heartIcon.getIcon().toString().contains("heart-active.png")){
                    heartIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/heart.png"))));
                    JOptionPane.showMessageDialog(null, "Remove from favorite successfully!");
                    listFavouriteWord.removeFavouriteWord(word);
                }
                else if(heartIcon.getIcon().toString().contains("heart.png")){
                    heartIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("../img/heart-active.png"))));
                    JOptionPane.showMessageDialog(null, "Add to favorite successfully!");
                    listFavouriteWord.addFavouriteWord(word);
                }
            }
        });

        if(meaning != null  && meaning.size() != 0){
            reactionPanel.add(heartIcon);
            reactionPanel.add(removeIcon);
        }
        wordPanel.add(reactionPanel, BorderLayout.LINE_END);
        bodyPanel.add(wordPanel, BorderLayout.PAGE_START);
        //show mean
        JPanel meaningPanel = new JPanel();
        meaningPanel.setLayout(new BoxLayout(meaningPanel, BoxLayout.Y_AXIS));
        if(meaning != null){
            for (String s : meaning) {
                JLabel meaningLabel = new JLabel(s);
                meaningLabel.setLayout(new FlowLayout());
                meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                meaningLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
                meaningPanel.add(meaningLabel);
            }
        }
        if(!firstAction){
            JLabel meaningLabel = new JLabel("Từ này chưa có trong từ điển - This word is not in the dictionary");
            meaningLabel.setLayout(new FlowLayout());
            meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            meaningLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
            meaningPanel.add(meaningLabel);
        }
        bodyPanel.add(meaningPanel, BorderLayout.CENTER);
        firstAction = false;
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return bodyPanel;
    }

    public JPanel addPanel(Frame frame, JLabel addIcon){
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new FlowLayout());

        JPanel total = new JPanel();
        total.setLayout(new BorderLayout());
        //set height of JTextField
        JTextField wordField = new JTextField(20);
        wordField.setPreferredSize(new Dimension(20, 30));
        wordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        JTextArea meaningField = new JTextArea(5, 20);
        meaningField.setPreferredSize(new Dimension(20, 30));
        meaningField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        meaningField.setText(meaningField.getText() + "\n");
                    }
                }
        });
        JLabel wordLabel = new JLabel("Word: ");
        wordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        wordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        JLabel meaningLabel = new JLabel("Meaning: ");
        meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton addButton = new JButton("Add new word");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout());
        wordPanel.add(wordLabel);
        wordPanel.add(wordField);

        JPanel meaningPanel = new JPanel();
        meaningPanel.setLayout(new FlowLayout());
        meaningPanel.add(meaningLabel);
        meaningPanel.add(meaningField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> {
            String word = wordField.getText();
            String meaning = meaningField.getText();
            if(word.equals("") || meaning.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter word and meaning");
                return;
            }
            String [] meaningArray = meaning.split("\n");
            ArrayList<String> meaningList = new ArrayList<>();
            for(String i : meaningArray){
                meaningList.add(i);
            }
            try {
                list.addWord(word, meaningList);
                JOptionPane.showMessageDialog(null, "Add new word successfully!");
                addIcon.setIcon(new ImageIcon(getClass().getResource("../img/add.png")));
                frame.dispose();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "This word is already in the dictionary!");
                return;
            }
        });
        total.add(wordPanel, BorderLayout.PAGE_START);
        total.add(meaningPanel, BorderLayout.CENTER);
        total.add(buttonPanel, BorderLayout.PAGE_END);
        addPanel.add(total);
        return addPanel;
    }

    public JPanel showFavouriteList(){
        JPanel showFavouriteListPanel = new JPanel();
        showFavouriteListPanel.setLayout(new BorderLayout());
        JLabel titlePanel = new JLabel("LIST OF FAVOURITE WORDS");
        titlePanel.setFont(new Font("Arial", Font.BOLD, 20));

        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titlePanel.setHorizontalAlignment(JLabel.CENTER);
        String[] column = {"Word"};
        ArrayList<String> listWord = listFavouriteWord.getListWord();
        String[][] data = new String[listWord.size()][1];
        for(int i = 0; i < listWord.size(); i++){
            data[i][0] = listWord.get(i);
        }
        JTable table = new JTable(data, column);
        table.setRowHeight(40);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        JScrollPane scrollPane = new JScrollPane(table);
        showFavouriteListPanel.add(titlePanel, BorderLayout.PAGE_START);
        showFavouriteListPanel.add(scrollPane, BorderLayout.CENTER);
        return showFavouriteListPanel;
        }

        private JPanel showStatistic() {
            JPanel showStatisticPanel = new JPanel();
            return showStatisticPanel;
        }
}
