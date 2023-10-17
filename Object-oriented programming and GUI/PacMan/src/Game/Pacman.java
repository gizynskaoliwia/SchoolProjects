package Game;

import javax.swing.*;
import java.awt.*;

public class Pacman extends Gameobject {
    public Pacman(int x, int y, Game game) {
        super(x, y, game);
    }

    public long time;
    @Override
    public void tick() {

        if(ticked)
            return;

        ticked = true;
        System.out.println("move");
        switch (game.dir) {
            case 0 -> {
                if(y-25 < 0)
                    return;
                Gameobject gameobject = game.get(x,y-25);
                if(gameobject instanceof Empty || gameobject instanceof EnhancementA || gameobject instanceof EnhancementB || gameobject instanceof EnhancementC || gameobject instanceof EnhancementD) {
                    game.replace(new Empty(x, y, game));
                    this.y -= 25;
                }
                process(gameobject);

            }
            case 1 -> {
                if(y + 25 > 5*25)
                    return;
                Gameobject gameobject = game.get(x,y+25);
                if(gameobject instanceof Empty || gameobject instanceof EnhancementA || gameobject instanceof EnhancementB || gameobject instanceof EnhancementC || gameobject instanceof EnhancementD) {
                    game.replace(new Empty(x, y, game));
                    this.y += 25;
                }
                process(gameobject);

            }
            case 2 -> {
                if(x - 25 < 0)
                    return;
                Gameobject gameobject = game.get(x-25,y);
                if(gameobject instanceof Empty || gameobject instanceof EnhancementA || gameobject instanceof EnhancementB || gameobject instanceof EnhancementC || gameobject instanceof EnhancementD) {
                    game.replace(new Empty(x, y, game));
                    this.x -= 25;
                }
                process(gameobject);

            }
            case 3 -> {
                if(x + 25 > 14*25)
                    return;
                Gameobject gameobject = game.get(x+25,y);
                if(gameobject instanceof Empty || gameobject instanceof EnhancementA || gameobject instanceof EnhancementB || gameobject instanceof EnhancementC || gameobject instanceof EnhancementD) {
                    game.replace(new Empty(x, y, game));
                    this.x += 25;
                }
                process(gameobject);

            }
        }
    }

    private void process(Gameobject gameobject){
        System.out.println("process" + gameobject.toString());
        if(gameobject instanceof Empty) {
            System.out.println("process");
            game.replace(this);
        }

        if(gameobject instanceof Ghost && time + 10000 < System.currentTimeMillis())
        {
            JOptionPane.showMessageDialog(this, "Przegrałeś!");
            game.work = false;
        }

        if (gameobject instanceof EnhancementA || gameobject instanceof EnhancementD) {
            game.points += 100;
            game.replace(this);
        }

        if(gameobject instanceof EnhancementB)
        {
            game.point_multiplier += 1;
            game.replace(this);

        }

        if(gameobject instanceof EnhancementC)
        {
            time = System.currentTimeMillis();
            game.replace(this);

        }
    }
    @Override
    public void draw(Graphics g) {

    }
}

