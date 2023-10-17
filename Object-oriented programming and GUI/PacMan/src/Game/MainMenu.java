package Game;

import javax.swing.*;

public class MainMenu {
    private MainMenu menu = this;
    private JFrame frame;
    public MainMenu(){
        init();
    }

    private void init(){
        frame = new JFrame();
        frame.setSize(240,320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setName("Główne menu");
        createComponents();
        frame.setVisible(true);
    }

    private void createComponents(){
        JButton run;
        run = new JButton("Graj");
        run.setSize(150,36);
        run.setLocation(frame.getWidth()/2-run.getWidth()/2, 30);
        run.addActionListener(e -> {
            frame.dispose();
            new Thread(() -> new Game()).start();
        });

        frame.add(run);

        JButton scores;
        scores = new JButton("Tabela wyników");
        scores.setSize(150,36);
        scores.setLocation(frame.getWidth()/2-run.getWidth()/2, 110);
        scores.addActionListener(e -> {
            frame.dispose();
            new Thread(() -> new HighScores(true)).start();
        });
        frame.add(scores);

        JButton exit;

        exit = new JButton("Wyjście");
        exit.setSize(150,36);
        exit.setLocation(frame.getWidth()/2-run.getWidth()/2, 190);
        exit.addActionListener(e -> {
            frame.dispose();
        });
        frame.add(exit);
    }

}
