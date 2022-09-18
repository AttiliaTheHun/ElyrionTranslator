/**
* Version 1.0, irrelevant now
*/

package attilathehun.elyriontranslator;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import attilathehun.elyriontranslator.ToggleSwitch;

public class ElyrionTranslator {

    public String legal_alphabet = toUTF8(" \nabcdefghijklmnopqrstuvwxyz,.'\"!?()@$_&/*1234567890");
    public String elyrion_alphabet = toUTF8(" \nao#Δ∑₼þţiƒ§∫mŋȱπ¦r^Ŧ₺‡~eỹΩ,.'\"!?()@$_&/*1234567890");
    public String elyrion_decrytion = toUTF8(" \nao#δ∑₼þţiƒ§∫mŋȱπ¦r^ŧ₺‡~eỹω,.'\"!?()@$_&/*1234567890");



    public static boolean ignore_invalid_checked = true;
    public static boolean translate_to_elyrion = true;

    public static void main(String[] args){
        ElyrionTranslator translator = new ElyrionTranslator();
        if (!GraphicsEnvironment.isHeadless()) {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame frame = new JFrame("Elyrion Translator");
            frame.setSize(450,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                    translate_to_elyrion  = language_switch.isActivated();
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
                    ignore_invalid_checked = ignore_invalid.isSelected();
                }
            });

            exclude_invalid.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    ignore_invalid.setSelected(!exclude_invalid.isSelected());
                    ignore_invalid_checked = ignore_invalid.isSelected();
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
                    if(use_dictionary.isSelected()) {

                    } else {

                    };
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
            frame.add(main_box);
            frame.setVisible(true);
        }
    }

        public String translate(String input) {
        String first_alphabet;
        String second_alphabet;
        if(translate_to_elyrion){
           first_alphabet = legal_alphabet;
            second_alphabet = elyrion_alphabet;
        }else{
            first_alphabet = elyrion_decrytion;
            second_alphabet = legal_alphabet;
         }
            String output = "";
            for (int i = 0; i < input.length(); i++) {
                if (first_alphabet.indexOf(input.charAt(i)) == -1) {
                    if (ignore_invalid_checked) {
                        output = output + input.charAt(i);
                    }
                } else {
                    output = output + second_alphabet.charAt(first_alphabet.indexOf(input.charAt(i)));
                }
            }
            return output;
        }

        public String toUTF8(String input){
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        String output = new String(bytes, StandardCharsets.UTF_8);
        return output;
        }

}