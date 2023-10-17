package Game;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private JFrame frame;
    private ArrayList<Gameobject> gameobjects = new ArrayList<Gameobject>();
    private ArrayList<Gameobject> newgameobjects = new ArrayList<Gameobject>();
    public int points;
    private JLabel pointL;
    public int dir = 1;
    public boolean work = true;
    public double point_multiplier = 1;

    public Game() {
        frame = new JFrame();
        frame.setSize(500, 190);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setName("Gra");
        init();

        pointL = new JLabel();

        pointL.setText("Punkty:" + this.points);
        pointL.setLocation(380, 5);
        pointL.setSize(100,40);

        frame.add(pointL);

        frame.setVisible(true);
        gameLoop();

    }

    private void init() {
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 15; x++) {
                new Empty(x * 25, y * 25, this);
            }
        }
        newgameobjects.addAll(gameobjects);
        for (int x = 1; x < 14; x++) {
            replace(new Obstacle(x * 25, 25, this));
            replace(new Obstacle(x * 25, 75, this));
        }

        replace(new Pacman(0, 0, this));
        replace(new Ghost(14 * 25, 0, this));
        replace(new Ghost(14 * 25, 5 * 25, this));
        replace(new Ghost(7 * 25, 2 * 25, this));
        gameobjects.clear();
        gameobjects.addAll(newgameobjects);
    }

    public void replace(Gameobject gameobject) {
        Gameobject object = newgameobjects.stream().filter(x -> x.x == gameobject.x && x.y == gameobject.y).findFirst().get();
        gameobject.setLocation(gameobject.x, gameobject.y);
        int index = newgameobjects.indexOf(object);
        newgameobjects.remove(object);
        frame.remove(object);
        newgameobjects.add(index, gameobject);
        frame.add(gameobject);
        frame.repaint();
    }

    public Gameobject get(int x, int y) {
        System.out.println("x->" + x + " y->" + y);
        Gameobject object = gameobjects.stream().filter(e -> e.x == x && e.y == y).findFirst().get();
        return object;
    }

    private void gameLoop() {
        while (work) {

            newgameobjects.clear();
            newgameobjects.addAll(gameobjects);
            for (int i = 0; i < gameobjects.size(); i++) {

                gameobjects.get(i).tick();
                gameobjects.get(i).ticked = false;
            }
            gameobjects.clear();
            gameobjects.addAll(newgameobjects);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            points += point_multiplier;
            pointL.setText("Punkty:" + points);

        }
        HighScores.UserDataComb.addUserData(points);
        frame.dispose();
        new MainMenu();
    }

    public JFrame getFrame() {
        return frame;
    }

    public ArrayList<Gameobject> getGameobjects() {
        return gameobjects;
    }

    public Pacman getPacman() {
        Pacman p = (Pacman)gameobjects.stream().filter(e->e instanceof Pacman).findFirst().get();
        return p;
    }
}
