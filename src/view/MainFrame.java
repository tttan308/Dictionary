package view;

import function.WordList;
import function.Word;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Flow;

public class MainFrame {
    private JTextField searchField;
    private  JLabel searchIcon;
    private WordList list;
    private Word word = new Word();
    private boolean language = true; //true: English - Vietnamese, false: Vietnamese - English
    private boolean firstAction = true;
    private boolean addNewWordActive = false;
    public MainFrame() throws ParserConfigurationException, IOException, SAXException {
        JFrame frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list = new WordList();
        if(language == false )
        {
            list.readXMLFile("src\\data\\Viet_Anh.xml");
        }
        else {
            list.readXMLFile("src\\data\\Anh_Viet.xml");
        }

        JPanel headerPanel = getHeaderFrame();
        final JPanel[] bodyPanel = {getBodyFrame()};
        //reload bodyPanel when action searchField
        searchField.addActionListener(e -> {
            String text = searchField.getText();
            word = list.searchWord(text);
            frame.remove(bodyPanel[0]);
            bodyPanel[0] = getBodyFrame();
            frame.add(bodyPanel[0], BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
        //reload bodyPanel when click on search icon
        searchIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = searchField.getText();
                word = list.searchWord(text);
                super.mouseClicked(e);
                frame.remove(bodyPanel[0]);
                bodyPanel[0] = getBodyFrame();
                frame.add(bodyPanel[0], BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            }
        });
        frame.add(headerPanel, BorderLayout.PAGE_START);

        frame.add(bodyPanel[0], BorderLayout.CENTER);


        //add line border
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
        JLabel logoIcon = new JLabel(new ImageIcon(getClass().getResource("../img/dictionary.png")));
        logoIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoIcon.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 20));
        logoPanel.add(logoIcon);
        headerPanel.add(logoPanel, BorderLayout.LINE_START);

        //Search box
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new RoundedTextField(10);
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchIcon = new JLabel(new ImageIcon(getClass().getResource("../img/search.png")));
        searchIcon.setBorder(BorderFactory.createEmptyBorder(6, 5, 5, 5));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        searchPanel.add(searchField);
        searchPanel.add(searchIcon);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 6, 5));
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel rightHeaderPanel = new JPanel();
        rightHeaderPanel.setLayout(new FlowLayout());

        JLabel addIcon = new JLabel(new ImageIcon(getClass().getResource("../img/add.png")));
        addIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //action
        addIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //change img
                if(addIcon.getIcon().toString().contains("add-active.png"))
                    addIcon.setIcon(new ImageIcon(getClass().getResource("../img/add.png")));
                else if(addIcon.getIcon().toString().contains("add.png"))
                    addIcon.setIcon(new ImageIcon(getClass().getResource("../img/add-active.png")));

                JFrame addFrame = new JFrame("Add new word");
                addFrame.setLayout(new BorderLayout());
                addFrame.setSize(400, 300);
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
                        addIcon.setIcon(new ImageIcon(getClass().getResource("../img/add.png")));
                    }
                });
            }
        });

        JPanel changeLanguagesPanel = new JPanel();
        JLabel changeLanguages = new JLabel(new ImageIcon(getClass().getResource("../img/world.png")));
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
                    changeLanguages.setIcon(new ImageIcon(getClass().getResource("../img/world.png")));
                    changeLanguagesText.setText("English - Vietnamese");
                    list.clearList();
                    try {
                        list.readXMLFile("src//data//Anh_Viet.xml");
                    } catch (ParserConfigurationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (SAXException ex) {
                        throw new RuntimeException(ex);
                    }
                    language = true;
                }
                else if(changeLanguages.getIcon().toString().contains("world.png")){
                    changeLanguages.setIcon(new ImageIcon(getClass().getResource("../img/world-active.png")));
                    changeLanguagesText.setText("Vietnamese - English");
                    list.clearList();
                    try {
                        list.readXMLFile("src//data//Viet_Anh.xml");
                    } catch (ParserConfigurationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (SAXException ex) {
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
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        ArrayList<String> meaning = word.getMeaning();

        String vocabulary = word.getWord();
        JLabel wordLabel = new JLabel(vocabulary);
        wordLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        wordLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));

        bodyPanel.add(wordLabel);
        //show mean
        JPanel meaningPanel = new JPanel();
        meaningPanel.setLayout(new BoxLayout(meaningPanel, BoxLayout.Y_AXIS));
        if(meaning != null){
            for(int i = 0; i < meaning.size(); i++){
                JLabel meaningLabel = new JLabel(meaning.get(i));
                meaningLabel.setLayout(new FlowLayout());
                meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                meaningLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
                meaningPanel.add(meaningLabel);
            }
        }
        if(meaning.size() == 0 && firstAction == false){
            JLabel meaningLabel = new JLabel("Từ này chưa có trong từ điển - This word is not in the dictionary");
            meaningLabel.setLayout(new FlowLayout());
            meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            meaningLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
            meaningPanel.add(meaningLabel);
        }
        bodyPanel.add(meaningPanel);
        firstAction = false;
        return bodyPanel;
    }

    public JPanel addPanel(Frame frame, JLabel addIcon){
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

        JTextField wordField = new JTextField(20);
        JTextArea meaningField = new JTextArea(5, 20);
        meaningField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        meaningField.setText(meaningField.getText() + "\n");
                    }
                }
        });
        JLabel wordLabel = new JLabel("Word: ");
        JLabel meaningLabel = new JLabel("Meaning: ");
        JButton addButton = new JButton("Add new word");

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
        addPanel.add(wordPanel);
        addPanel.add(meaningPanel);
        addPanel.add(buttonPanel);
        return addPanel;
    }
}
