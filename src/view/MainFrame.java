package view;

import function.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainFrame {
    JFrame frame;
    JPanel bodyPanel;
    private ListWord listEng = new ListWord() ;
    private ListWord listViet = new ListWord();
    private ListWordRemoved listRemovedEng = new ListWordRemoved();
    private ListWordRemoved listRemovedViet = new ListWordRemoved();
    private ListWordAdded listAddedEng = new ListWordAdded();
    private ListWordAdded listAddedViet = new ListWordAdded();
    private FavouriteWord listFavouriteWordEng = new FavouriteWord();
    private FavouriteWord listFavouriteWordViet = new FavouriteWord();
    private StatisticsWord statisticsWordEng = new StatisticsWord();
    private StatisticsWord statisticsWordViet = new StatisticsWord();
    private boolean language = true; //true: English - Vietnamese, false: Vietnamese - English
    private static boolean changeLanguage = true;
    private static boolean changeStatistics = true;
    private boolean firstAction = true;
    private boolean isFavouriteWord = false;
    private String word = "";
    public MainFrame() {
        frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Read data from XML file
        try {
            listEng.readXMLFile("data//Anh_Viet.xml");
            listViet.readXMLFile("data//Viet_Anh.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        //Read data from favourite word file
        listFavouriteWordEng.readFile("data//FavouriteWord_Eng.txt");
        listFavouriteWordViet.readFile("data//FavouriteWord_Viet.txt");

        //Read data from statistics word file
        statisticsWordEng.readFile("data//StatisticsWord_Eng.txt");
        statisticsWordViet.readFile("data//StatisticsWord_Viet.txt");

        //Read data from removed word file
        listRemovedEng.readFile("data//RemovedWord_Eng.txt");
        listRemovedViet.readFile("data//RemovedWord_Viet.txt");
        listEng.removeListWordRemoved(listRemovedEng);
        listViet.removeListWordRemoved(listRemovedViet);

        //Read data from added word file
        listAddedEng.readFile("data//AddedWord_Eng.txt");
        listAddedViet.readFile("data//AddedWord_Viet.txt");
        listEng.addListWordAdded(listAddedEng);
        listViet.addListWordAdded(listAddedViet);


        //Get container
        JPanel headerPanel = getHeaderFrame();
        JPanel bodyPanel = getBodyFrame();

        frame.add(headerPanel, BorderLayout.PAGE_START);
        frame.add(bodyPanel, BorderLayout.CENTER);

        //Set up something for frame
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#A1A2A9")));
        frame.setSize(800, 800);
        frame.setMinimumSize(new Dimension(650, 150));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Action when close frame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                listFavouriteWordEng.writeFile("data//FavouriteWord_Eng.txt");
                listFavouriteWordViet.writeFile("data//FavouriteWord_Viet.txt");
                statisticsWordEng.writeFile("data//StatisticsWord_Eng.txt");
                statisticsWordViet.writeFile("data//StatisticsWord_Viet.txt");
                listRemovedEng.writeFile("data//RemovedWord_Eng.txt");
                listRemovedViet.writeFile("data//RemovedWord_Viet.txt");
                listAddedEng.writeFile("data//AddedWord_Eng.txt");
                listAddedViet.writeFile("data//AddedWord_Viet.txt");
            }
        });
    }

    public JPanel getHeaderFrame() {
        //Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        //Container
        ////Logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout());
        JLabel logoIcon = new JLabel(new ImageIcon("img//research2.png"));
        logoIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoIcon.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 20));
        logoPanel.add(logoIcon);
        headerPanel.add(logoPanel, BorderLayout.LINE_START);

        ///Menu
        logoIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem item1 = new JMenuItem("Favorite list word");
                JMenuItem item2 = new JMenuItem("Statistics look up word");
                popupMenu.add(item1);
                popupMenu.add(item2);
                popupMenu.show(logoIcon, 30, 40);
                item1.addActionListener(e1 -> {
                    JFrame favouriteFrame = new JFrame("Favorite list word");
                    favouriteFrame.setLayout(new FlowLayout());
                    JPanel favouritePanel = showFavouriteList(favouriteFrame);
                    favouriteFrame.add(favouritePanel);
                    favouriteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    favouriteFrame.setResizable(false);
                    favouriteFrame.setSize(400, 400);
                    favouriteFrame.setLocationRelativeTo(null);
                    favouriteFrame.setVisible(true);
                    //when close favourite frame
                    favouriteFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                        }
                    });
                });

                item2.addActionListener(e1 -> {
                    JFrame statisticFrame = new JFrame("Statistics look up word");
                    statisticFrame.setLayout(new FlowLayout());
                    JPanel statisticPanel = showStatistic(statisticFrame);
                    statisticFrame.add(statisticPanel);
                    statisticFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    statisticFrame.setResizable(false);
                    statisticFrame.setSize(400, 200);
                    statisticFrame.setLocationRelativeTo(null);
                    statisticFrame.setVisible(true);
                    //when close statistic frame
                    statisticFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                        }
                    });
                });
            }
        });

        //Search box
        ///Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        ////Search field
        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchField.addActionListener(e -> {
            String wordSearch = searchField.getText();
            wordSearch = wordSearch.trim();
            if(language){
                if(listEng.searchWord(wordSearch)){
                    word = wordSearch;
                    isFavouriteWord = listFavouriteWordEng.isFavouriteWord(word);
                    statisticsWordEng.addWord(word);
                }
                else word = "";
            }
            else{
                if(listViet.searchWord(wordSearch)){
                    word = wordSearch;
                    isFavouriteWord = listFavouriteWordViet.isFavouriteWord(word);
                    statisticsWordViet.addWord(word);
                }
                else word = "";
            }
            frame.remove(bodyPanel);
            bodyPanel = getBodyFrame();
            frame.add(bodyPanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        ////Search icon
        JLabel searchIcon = new JLabel(new ImageIcon("img//search.png"));
        searchIcon.setBorder(BorderFactory.createEmptyBorder(6, 5, 5, 5));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String wordSearch = searchField.getText();
                wordSearch = wordSearch.trim();
                if(language){
                    if(listEng.searchWord(wordSearch)){
                        word = wordSearch;
                        isFavouriteWord = listFavouriteWordEng.isFavouriteWord(word);
                        statisticsWordEng.addWord(word);
                    }
                    else word = "";
                }
                else{
                    if(listViet.searchWord(wordSearch)){
                        word = wordSearch;
                        isFavouriteWord = listFavouriteWordViet.isFavouriteWord(word);
                        statisticsWordViet.addWord(word);
                    }
                    else word = "";
                }
                frame.remove(bodyPanel);
                bodyPanel = getBodyFrame();
                frame.add(bodyPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchIcon);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 6, 5));
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        //Right header panel
        JPanel rightHeaderPanel = new JPanel();
        rightHeaderPanel.setLayout(new FlowLayout());

        ///Add icon
        JLabel addIcon = new JLabel(new ImageIcon("img//add.png"));
        addIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(addIcon.getIcon().toString().contains("add-active.png"))
                    addIcon.setIcon(new ImageIcon("img//add.png"));
                else if(addIcon.getIcon().toString().contains("add.png"))
                    addIcon.setIcon(new ImageIcon("img//add-active.png"));
                JFrame addFrame = new JFrame("Add new word");
                addFrame.setLayout(new BorderLayout());
                JPanel addPanel = addPanel(addFrame, addIcon);
                addFrame.add(addPanel, BorderLayout.CENTER);
                addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addFrame.setResizable(false);
                addFrame.setSize(430, 230);
                addFrame.setLocationRelativeTo(null);
                addFrame.setVisible(true);
                ///close addFrame when click close button
                addFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        addIcon.setIcon(new ImageIcon("img//add.png"));
                    }
                });
            }
        });

        ///Change languages
        JPanel changeLanguagesPanel = new JPanel();
        JLabel changeLanguages = new JLabel(new ImageIcon("img//world.png"));
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
                if(changeLanguages.getIcon().toString().contains("world-active.png")){
                    changeLanguages.setIcon(new ImageIcon("img//world.png"));
                    changeLanguagesText.setText("English - Vietnamese");
                    word = "";
                    searchField.setText("");
                    frame.remove(bodyPanel);
                    firstAction = true;
                    bodyPanel = getBodyFrame();
                    frame.add(bodyPanel, BorderLayout.CENTER);
                    frame.revalidate();
                    changeLanguage = true;
                    changeStatistics = true;
                    language = true;
                }
                else if(changeLanguages.getIcon().toString().contains("world.png")){
                    changeLanguages.setIcon(new ImageIcon("img//world-active.png"));
                    changeLanguagesText.setText("Vietnamese - English");
                    word = "";
                    searchField.setText("");
                    frame.remove(bodyPanel);
                    firstAction = true;
                    bodyPanel = getBodyFrame();
                    frame.add(bodyPanel, BorderLayout.CENTER);
                    frame.revalidate();
                    changeLanguage = false;
                    changeStatistics = false;
                    language = false;
                }
            }
        });

        //set a horizontal line
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BorderLayout());
        horizontalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#A1A2A9")));
        headerPanel.add(horizontalPanel, BorderLayout.PAGE_END);
        return headerPanel;
    }

    public JPanel getBodyFrame() {
        bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());

        ArrayList<String> meaning = new ArrayList<>();
        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new BorderLayout());
        wordPanel.setPreferredSize(new Dimension(400, 30));
        String vocabulary;
        if(firstAction) meaning.add("");
        if(word.equals("") && !firstAction ){
            vocabulary = "NOT FOUND!";
            meaning = new ArrayList<>();
        }
        else{
            vocabulary = word;
            if(language) meaning = listEng.getMeaning(word);
            else meaning = listViet.getMeaning(word);
        }
        JLabel wordLabel = new JLabel(vocabulary);

        wordLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        wordLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        wordPanel.add(wordLabel, BorderLayout.LINE_START);
        JPanel reactionPanel = new JPanel();
        JLabel removeIcon = new JLabel(new ImageIcon("img//remove.png"));
        removeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!word.equals("") && language) listEng.removeWord(word);
                else if(!word.equals("") && !language) listViet.removeWord(word);
                if(removeIcon.getIcon().toString().contains("remove-active.png"))
                {
                    removeIcon.setIcon(new ImageIcon("img//remove.png"));
                }
                else if(removeIcon.getIcon().toString().contains("remove.png")) {
                    removeIcon.setIcon(new ImageIcon("img//remove-active.png"));
                    if(language) {
                        listEng.removeWord(word);
                        listFavouriteWordEng.removeFavouriteWord(word);
                        listRemovedEng.addWord(word);
                        listAddedEng.removeWord(word);
                        statisticsWordEng.removeWord(word);
                        JOptionPane.showMessageDialog(null, "Remove successfully!");
                    }
                    else {
                        listViet.removeWord(word);
                        listFavouriteWordViet.removeFavouriteWord(word);
                        listRemovedViet.addWord(word);
                        listAddedViet.removeWord(word);
                        statisticsWordViet.removeWord(word);
                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    }
                }
                bodyPanel.removeAll();
                bodyPanel.revalidate();
                bodyPanel.repaint();
            }
        });

        JLabel heartIcon;
        if(isFavouriteWord) heartIcon = new JLabel(new ImageIcon("img//heart-active.png"));
        else heartIcon = new JLabel(new ImageIcon("img//heart.png"));
        heartIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        heartIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        heartIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //change icon
                if(heartIcon.getIcon().toString().contains("heart-active.png")){
                    heartIcon.setIcon(new ImageIcon("img//heart.png"));
                    if(language) listFavouriteWordEng.removeFavouriteWord(word);
                    else listFavouriteWordViet.removeFavouriteWord(word);
                }
                else if(heartIcon.getIcon().toString().contains("heart.png")){
                    heartIcon.setIcon(new ImageIcon("img//heart-active.png"));
                    if(language) listFavouriteWordEng.addFavouriteWord(word);
                    else listFavouriteWordViet.addFavouriteWord(word);
                }
            }
        });

        if(meaning != null  && meaning.size() != 0){
            reactionPanel.add(heartIcon);
            reactionPanel.add(removeIcon);
        }
        wordPanel.add(reactionPanel, BorderLayout.LINE_END);
        bodyPanel.add(wordPanel, BorderLayout.PAGE_START);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

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
        if(!firstAction && meaning.size() == 0){
            JLabel meaningLabel = new JLabel("Từ này chưa có trong từ điển - This word is not in the dictionary");
            meaningLabel.setLayout(new FlowLayout());
            meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            meaningLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
            meaningPanel.add(meaningLabel);
        }
        scrollPane.setViewportView(meaningPanel);
        bodyPanel.add(scrollPane, BorderLayout.CENTER);
        firstAction = false;
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return bodyPanel;
    }

    public JPanel addPanel(Frame frame, JLabel addIcon){
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new FlowLayout());

        JPanel total = new JPanel();
        total.setLayout(new BorderLayout());
        JTextField wordField = new JTextField(20);
        wordField.setPreferredSize(new Dimension(20, 30));
        wordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        JScrollPane scrollPane = new JScrollPane();
        JTextArea meaningField = new JTextArea(5, 20);
        meaningField.setPreferredSize(new Dimension(20, 40));
        meaningField.setBorder(BorderFactory.createEmptyBorder(2, 5, 0, 5));
        scrollPane.setViewportView(meaningField);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        meaningField.setLineWrap(true);
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
        meaningPanel.add(scrollPane);

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
            Collections.addAll(meaningList, meaningArray);
            try{
                if(language){
                    listEng.addWord(word, meaningList);
                    listAddedEng.addWord(word, meaningList);
                    JOptionPane.showMessageDialog(null, "Add word successfully");
                }
                else{
                    listViet.addWord(word, meaningList);
                    listAddedViet.addWord(word, meaningList);
                    JOptionPane.showMessageDialog(null, "Thêm từ thành công");
                }
            }catch (Exception ex){
                if(language) JOptionPane.showMessageDialog(null, "This word is already in the dictionary");
                else JOptionPane.showMessageDialog(null, "Từ này đã có trong từ điển");
                return;
            }
            addIcon.setIcon(new ImageIcon("img//add.png"));
            frame.dispose();
        });
        total.add(wordPanel, BorderLayout.PAGE_START);
        total.add(meaningPanel, BorderLayout.CENTER);
        total.add(buttonPanel, BorderLayout.PAGE_END);
        addPanel.add(total);
        return addPanel;
    }

    public JPanel showFavouriteList(Frame frameX){
        JPanel showFavouriteListPanel = new JPanel();
        showFavouriteListPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        JLabel titlePanel = new JLabel();
        titlePanel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titlePanel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titlePanel, BorderLayout.PAGE_START);

        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new FlowLayout());
        JLabel languageIcon = new JLabel();
        JLabel titleLanguageIcon = new JLabel();

        if(!changeLanguage) {
            titlePanel.setText("DANH SÁCH NHỮNG TỪ YÊU THÍCH");
            languageIcon.setIcon(new ImageIcon("img//world-active.png"));
            titleLanguageIcon.setText("Vietnamese");
        }
        else {
            titlePanel.setText("LIST OF FAVOURITE WORDS");
            languageIcon.setIcon(new ImageIcon("img//world.png"));
            titleLanguageIcon.setText("English");
        }
        titleLanguageIcon.setFont(new Font("Arial", Font.PLAIN, 15));
        titleLanguageIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        languagePanel.add(languageIcon);
        languagePanel.add(titleLanguageIcon);
        headerPanel.add(languagePanel, BorderLayout.CENTER);
        languageIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ArrayList<String> listWord = new ArrayList<>();
        if(changeLanguage)  listWord = listFavouriteWordEng.getListWord();
        else listWord = listFavouriteWordViet.getListWord();
        String[][] column = {{"Word"}};
        String[][] data = new String[listWord.size()][1];
        for(int i = 0; i < listWord.size(); i++){
            data[i][0] = listWord.get(i);
        }

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(data, column[0]));
        table.setRowHeight(40);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setDefaultEditor(Object.class, null);

        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        rowSorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                return collator.compare(o1, o2);
            }
        });
        table.setRowSorter(rowSorter);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        scrollPane.setPreferredSize(new Dimension(200, 250));
        showFavouriteListPanel.add(headerPanel, BorderLayout.PAGE_START);
        showFavouriteListPanel.add(scrollPane, BorderLayout.CENTER);
        languageIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(changeLanguage) {
                    languageIcon.setIcon(new ImageIcon("img//world-active.png"));
                    titleLanguageIcon.setText("Vietnamese");
                    changeLanguage = false;
                }
                else {
                    languageIcon.setIcon(new ImageIcon("img//world.png"));
                    titleLanguageIcon.setText("English");
                    changeLanguage = true;
                }
                //reload frame
                frameX.remove(showFavouriteListPanel);
                frameX.add(showFavouriteList(frameX));
                frameX.revalidate();
                frameX.repaint();
            }
        });
        return showFavouriteListPanel;
    }
    private JPanel showStatistic(JFrame frame)  {
        JPanel showStatisticPanel = new JPanel();
        showStatisticPanel.setLayout(new BorderLayout());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        JPanel dateBeginPanel = new JPanel();
        dateBeginPanel.setLayout(new FlowLayout());

        JLabel dateBegin = new JLabel("Date begin: ");
        dateBegin.setFont(new Font("Arial", Font.PLAIN, 20));
        dateBegin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JFormattedTextField dateBeginField = new JFormattedTextField(dateFormat);
        dateBeginField.setPreferredSize(new Dimension(200, 30));
        dateBeginField.setFont(new Font("Arial", Font.PLAIN, 20));
        dateBeginPanel.add(dateBegin);
        dateBeginPanel.add(dateBeginField);

        JPanel dateEndPanel = new JPanel();
        dateEndPanel.setLayout(new FlowLayout());
        JLabel dateEnd = new JLabel("Date end: ");
        dateEnd.setFont(new Font("Arial", Font.PLAIN, 20));
        dateEnd.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        JFormattedTextField dateEndField = new JFormattedTextField(dateFormat);
        dateEndField.setPreferredSize(new Dimension(200, 30));
        dateEndField.setFont(new Font("Arial", Font.PLAIN, 20));
        dateEndPanel.add(dateEnd);
        dateEndPanel.add(dateEndField);
        //nếu người dùng nhập 2 kí tự đầu tiên là số thì sẽ tự động thêm dấu / vào
        dateBeginField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = dateBeginField.getText();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter V alid");
                    e.consume();
                }
            }
        });

        dateEndField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid");
                    e.consume();
                }
            }
        });
        JPanel searchPanel = new JPanel();
        JButton statisticsButton = new JButton("Statistics");
        statisticsButton.setPreferredSize(new Dimension(200, 30));
        statisticsButton.setFont(new Font("Arial", Font.PLAIN, 20));
        searchPanel.add(statisticsButton);
        showStatisticPanel.add(dateBeginPanel, BorderLayout.PAGE_START);
        showStatisticPanel.add(dateEndPanel, BorderLayout.CENTER);
        showStatisticPanel.add(searchPanel, BorderLayout.PAGE_END);

        statisticsButton.addActionListener(e -> {
            //Close frame and open new frame
            String begin = dateBeginField.getText();
            String end = dateEndField.getText();
            if(begin.equals("") || end.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter date begin and date end");
            }
            else {
                JFrame statisticsFrame = new JFrame("Statistics");
                statisticsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                statisticsFrame.add(statisticsPanel(begin, end));
                statisticsFrame.pack();
                statisticsFrame.setResizable(false);
                statisticsFrame.setLocationRelativeTo(null);
                statisticsFrame.setVisible(true);
                frame.dispose();
            }
        });
        return showStatisticPanel;
    }

    private JPanel statisticsPanel(String begin, String end){
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BorderLayout());
        JLabel title = new JLabel();
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        title.setHorizontalAlignment(JLabel.CENTER);
        JLabel titleLanguageIcon = new JLabel();
        JLabel languageIcon = new JLabel();
        if(changeStatistics){
            titleLanguageIcon.setText("English");
            languageIcon.setIcon(new ImageIcon("img//world.png"));
            title.setText("Statistics from " + begin + " to " + end);
        }
        else {
            titleLanguageIcon.setText("Vietnamese");
            languageIcon.setIcon(new ImageIcon("img//world-active.png"));
            title.setText("Thống kê từ " + begin + " đến " + end);
        }
        titleLanguageIcon.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLanguageIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        languageIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        languageIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.PAGE_START);
        JPanel changeLanguagePanel = new JPanel();
        changeLanguagePanel.setLayout(new FlowLayout());
        changeLanguagePanel.add(languageIcon);
        changeLanguagePanel.add(titleLanguageIcon);
        titlePanel.add(changeLanguagePanel, BorderLayout.CENTER);
        statisticsPanel.add(titlePanel, BorderLayout.PAGE_START);

        JPanel tablePanel = new JPanel();
        String[] column = {"Word", "Times"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(30);
        Map<String, Integer> word;
        if(changeStatistics) word = statisticsWordEng.getStatisticsWord(begin, end);
        else word = statisticsWordViet.getStatisticsWord(begin, end);
        for (Map.Entry<String, Integer> entry : word.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            model.addRow(new Object[]{key, value});
        }
        //center text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);

        languageIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(changeStatistics){
                    languageIcon.setIcon(new ImageIcon("img//world-active.png"));
                    titleLanguageIcon.setText("Vietnamese");
                    title.setText("Thống kê từ " + begin + " đến " + end);
                    column[0] = "Từ";
                    column[1] = "Số lần";
                    changeStatistics = false;
                }
                else {
                    languageIcon.setIcon(new ImageIcon("img//world.png"));
                    titleLanguageIcon.setText("English");
                    title.setText("Statistics from " + begin + " to " + end);
                    column[0] = "Word";
                    column[1] = "Times";
                    changeStatistics = true;
                }
                //  reload table
                model.setRowCount(0);
                Map<String, Integer> word ;
                if(!changeStatistics) word = statisticsWordViet.getStatisticsWord(begin, end);
                else word = statisticsWordEng.getStatisticsWord(begin, end);
                for (Map.Entry<String, Integer> entry : word.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    model.addRow(new Object[]{key, value});
                }
            }
        });
        scrollPane.setPreferredSize(new Dimension(400, 400));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        statisticsPanel.add(tablePanel, BorderLayout.CENTER);
        return statisticsPanel;
    }
}
