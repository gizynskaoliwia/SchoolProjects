package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ghost extends Gameobject {
    public Ghost(int x, int y, Game game) {
        super(x, y, game);
    }

    private int dir = 2;

    @Override
    public void tick() {

        if(ticked)
            return;

        ticked = true;
        System.out.println("move");
        switch (dir) {
            case 0 -> {
                if(y-25 < 0) {
                    pickPath();
                    return;

                }
                Gameobject g = game.get(x,y-25);
                if(g instanceof Empty) {
                    game.replace(new Empty(x, y, game));
                    this.y -= 25;
                }
                process(g);

            }
            case 1 -> {
                if(y + 25 > 5*25) {
                    pickPath();
                    return;

                }
                Gameobject g = game.get(x,y+25);
                if(g instanceof Empty) {
                    game.replace(new Empty(x, y, game));
                    this.y += 25;
                }
                process(g);

            }
            case 2 -> {
                if(x - 25 < 0) {
                    pickPath();
                    return;
                }
                Gameobject g = game.get(x-25,y);
                if(g instanceof Empty) {
                    game.replace(new Empty(x, y, game));
                    this.x -= 25;
                }
                process(g);

            }
            case 3 -> {
                if(x + 25 > 14*25) {
                    pickPath();
                    return;

                }
                Gameobject g = game.get(x+25,y);
                if(g instanceof Empty) {
                    game.replace(new Empty(x, y, game));
                    this.x += 25;
                }
                process(g);

            }
        }
    }

    private void process(Gameobject gameobject){
        if(gameobject instanceof Empty) {
            game.replace(this);
        }
        if(gameobject instanceof Pacman && game.getPacman().time + 10000 < System.currentTimeMillis())
        {
            JOptionPane.showMessageDialog(this, "Przegrałeś!");
            game.work = false;
        }
        if(gameobject instanceof Obstacle)
            pickPath();

        if (gameobject instanceof EnhancementA || gameobject instanceof EnhancementB || gameobject instanceof EnhancementC || gameobject instanceof EnhancementD)
            game.replace(this);
    }

    public void pickPath() {
        dir = new Random().nextInt(4);
    }
    @Override
    public void draw(Graphics g) {

    }
}
