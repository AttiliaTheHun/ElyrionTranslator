package attilathehun.elyriontranslator;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Translator {

    private final String latin_alphabet = toUTF8(" \nabcdefghijklmnopqrstuvwxyz,.'\"!?()@$_&/*1234567890");
    private final String elyrion_alphabet = toUTF8(" \nao#Δ∑₼þţiƒ§∫mŋȱπ¦r^Ŧ₺‡~eỹΩ,.'\"!?()@$_&/*1234567890");
    private final String elyrion_decode_alphabet = toUTF8(" \nao#δ∑₼þţiƒ§∫mŋȱπ¦r^ŧ₺‡~eỹω,.'\"!?()@$_&/*1234567890");

    private  TranslatorSettings settings = new TranslatorSettings();


    private TranslateModes translate_mode;

    enum TranslateModes {
        TO_ELYRION,
        FROM_ELYRION
    }

    public void setTranslateMode(TranslateModes translate_mode){
        this.translate_mode = translate_mode;
    }

    public TranslateModes getTranslateMode() {
        return translate_mode;
    }

    public TranslatorSettings getSettings(){
        return this.settings;
    }

    public String translate(String text){
        if(this.settings.isUseDictionary()){
            return translateUsingDictionary(text);
        } else {
            return translateUsingSubstitution(text);
        }
    }

    private String translateUsingSubstitution(String text){
        String source_alphabet = elyrion_alphabet;
        String substitution_alphabet = latin_alphabet;
        if(getTranslateMode() == TranslateModes.TO_ELYRION){
            source_alphabet = latin_alphabet;
            substitution_alphabet = elyrion_alphabet;
        }
        String output = "";
        for (int i = 0; i < text.length(); i++) {
            if (source_alphabet.indexOf(text.charAt(i)) == -1) {
                if (this.settings.isIgnoreInvalidCharacters()) {
                    output = output + text.charAt(i);
                }
            } else {
                output = output + substitution_alphabet.charAt(source_alphabet.indexOf(text.charAt(i)));
            }
        }
        return output;
    }

    private String translateUsingDictionary(String text){

            String path = "english_dictionary.txt";
            String output = "";
            if (getTranslateMode() == TranslateModes.FROM_ELYRION) {
                text = translateUsingSubstitution(text);
                path = "/elyrion_dictionary.txt";
            }
            String[] text_in_array = text.split(" ");
            InputStream dictionary;
            BufferedReader reader;
            Boolean match;
            String line, word, definition_string;
            String[] definitions;
        try {
            for (String item : text_in_array) {
                match = false;
                dictionary = Translator.class.getResourceAsStream(path);
                reader = new BufferedReader(new InputStreamReader(dictionary));
                while((line = reader.readLine()) != null){
                    word = line.substring(0, line.indexOf(":"));
                    if(word.equals(item)){
                        definition_string = line.substring(line.indexOf(":") + 1);
                        definitions = definition_string.split(",");
                        output += " " + definitions[0];
                        match = true;
                        break;
                    }
                }
                reader.close();
                dictionary.close();

                if(!match){
                    output += " " + translateUsingSubstitution(item);
                }
            }

        }catch(IOException ioe){

        }

        return output;
    }


    public class TranslatorSettings {
        private boolean ignore_invalid_characters;
        private boolean exclude_invalid_characters;
        private boolean use_dictionary;

        public TranslatorSettings(){
            this.resetSettings();
        }

        public void setExcludeInvalidCharacters(boolean exclude_invalid_characters) {
            this.exclude_invalid_characters = exclude_invalid_characters;
            this.ignore_invalid_characters = !exclude_invalid_characters;
        }

        public boolean isExcludeInvalidCharacters() {
            return exclude_invalid_characters;
        }

        public void setIgnoreInvalidCharacters(boolean ignore_invalid_characters) {
            this.ignore_invalid_characters = ignore_invalid_characters;
            this.exclude_invalid_characters = !exclude_invalid_characters;
        }

        public boolean isIgnoreInvalidCharacters() {
            return ignore_invalid_characters;
        }

        public boolean isUseDictionary() {
            return use_dictionary;
        }

        public void setUseDictionary(boolean use_dictionary) {
            this.use_dictionary = use_dictionary;
        }

        public void resetSettings(){
            ignore_invalid_characters = true;
            exclude_invalid_characters = false;
            use_dictionary = false;
        }
    }

    public String toUTF8(String input){
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        String output = new String(bytes, StandardCharsets.UTF_8);
        return output;
    }
}
