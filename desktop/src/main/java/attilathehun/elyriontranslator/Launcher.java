package attilathehun.elyriontranslator;

import java.awt.*;

public class Launcher {

    public static void main(String[] args){
        Translator translator = new Translator();
        if (!GraphicsEnvironment.isHeadless()) {
            TranslatorUI frame = new TranslatorUI("Elyrion Translator", translator);
            frame.initialize();
        } else {
            System.out.println("Can not launch the GUI translator, headless here.");
        }

    }
}
