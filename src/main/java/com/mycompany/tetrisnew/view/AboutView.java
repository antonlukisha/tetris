package com.mycompany.tetrisnew.view;

import javax.swing.*;
import java.awt.*;

public class AboutView extends JPanel {
    
    public AboutView() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));
        JLabel titleLabel = new JLabel("About Tetris");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JTextArea aboutText = new JTextArea();
        aboutText.setText("""
                          Tetris is a tile-matching puzzle video game originally designed and programmed by Alexey Pajitnov. It was released on June 6, 1984, while he was working for the Dorodnicyn Computing Centre of the Academy of Science of the USSR in Moscow. He derived its name from the Greek numerical prefix tetra- (all of the game's pieces contain four segments) and tennis, Pajitnov's favorite sport.
                          
                          Tetris has been praised for its aesthetics, simplicity, and pervasiveness. It has been credited with popularizing puzzle games and inspiring a multitude of clones and variants.""");
        aboutText.setEditable(false);
        aboutText.setFont(new Font("Arial", Font.PLAIN, 16));
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(titleLabel, BorderLayout.NORTH);
        add(aboutText, BorderLayout.CENTER);
    }
}
