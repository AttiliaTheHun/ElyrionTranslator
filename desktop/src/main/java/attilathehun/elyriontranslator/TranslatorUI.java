package attilathehun.elyriontranslator;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

public class TranslatorUI extends JFrame {

    Translator translator;

    public TranslatorUI(String title, Translator translator){
        super(title);
        this.translator = translator;
    }

    public void initialize(){
        this.setSize(450,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main_box = new JPanel();
        main_box.setLayout(new FlowLayout());
        JPanel container1 = new JPanel();
        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JLabel first_language = new JLabel("Enter text in your language");
        panel1.add(first_language);
        container1.add(panel1);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JTextArea translate_from = new JTextArea("", 1, 20);
        JButton clear_button = new JButton("Clear");
        clear_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                translate_from.setText("");
            }
        });
        panel2.add(translate_from);
        panel2.add(clear_button);
        container1.add(panel2);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        JLabel label_lang_1 = new JLabel("Not Elyrion");
        JLabel label_lang_2 = new JLabel("Elyrion");
        ToggleSwitch language_switch = new ToggleSwitch();
        JButton copy_button = new JButton("Copy");
        JTextArea translate_to = new JTextArea("", 1, 30);
        copy_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                StringSelection selection = new StringSelection(translate_to.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
        });
        JButton translate_button = new JButton("Translate");
        translate_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                translate_from.setText(translate_from.getText().toLowerCase(Locale.ROOT));
                if(language_switch.isActivated()){
                    translator.setTranslateMode(Translator.TranslateModes.FROM_ELYRION);
                } else {
                    translator.setTranslateMode(Translator.TranslateModes.TO_ELYRION);
                }
                translate_to.setText(translator.translate(translate_from.getText()));
            }
        });
        panel3.add(label_lang_1);
        panel3.add(language_switch);
        panel3.add(label_lang_2);
        panel3.add(copy_button);
        panel3.add(translate_button);
        container1.add(panel3);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());

        translate_to.setEnabled(false);
        panel4.add(translate_to);
        container1.add(panel4);

        JPanel container2 = new JPanel();
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));

        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();
        JCheckBox ignore_invalid = new JCheckBox("Ignore Invalid Characters");
        ignore_invalid.setSelected(true);
        JCheckBox exclude_invalid = new JCheckBox("Exclude Invalid Character");
        JCheckBox use_dictionary = new JCheckBox("Use Dictionary");

        ignore_invalid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                exclude_invalid.setSelected(!ignore_invalid.isSelected());
                translator.getSettings().setIgnoreInvalidCharacters(ignore_invalid.isSelected());
            }
        });

        exclude_invalid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ignore_invalid.setSelected(!exclude_invalid.isSelected());
                translator.getSettings().setIgnoreInvalidCharacters(ignore_invalid.isSelected());
            }
        });

        JCheckBox play_music = new JCheckBox("Play Elyrion Music");
        play_music.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(play_music.isSelected()) {

                } else {

                };
            }
        });
        JCheckBox elyrion_mode = new JCheckBox("Elyrion mode");
        elyrion_mode.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(elyrion_mode.isSelected()) {

                } else {

                };
            }
        });

        use_dictionary.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                translator.getSettings().setUseDictionary(use_dictionary.isSelected());
            }
        });
        panel6.add(ignore_invalid);
        panel7.add(exclude_invalid);
        panel8.add(play_music);
        panel9.add(elyrion_mode);
        panel10.add(use_dictionary);
        container2.add(panel6);
        container2.add(panel7);
        container2.add(panel8);
        container2.add(panel9);
        container2.add(panel10);
        /* ts.setLocation(5, 135);*/
        main_box.add(container1);
        main_box.add(container2);
        this.add(main_box);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }
}
