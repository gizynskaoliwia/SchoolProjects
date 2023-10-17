package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Gameobject extends JTable implements DefaultGameobjectOperation, KeyListener {
    protected Game game;
    protected int x, y;

    public boolean ticked = false;


    public Gameobject(int x, int y, Game game){
        this.x = x;
        this.y = y;
        this.game = game;

        init();
    }

    public void init(){
        this.setSize(25,25);
        this.setLocation(x, y);

        if(this instanceof Empty)
            setBackground(Color.LIGHT_GRAY);

        if(this instanceof Pacman)
            setBackground(Color.YELLOW);

        if(this instanceof Obstacle)
            setBackground(Color.BLACK);
        if(this instanceof Ghost)
            setBackground(Color.RED);
        if(this instanceof EnhancementA)
            setBackground(Color.MAGENTA);
        if(this instanceof EnhancementB)
            setBackground(Color.PINK);
        if(this instanceof EnhancementC)
            setBackground(Color.BLUE);
        if(this instanceof EnhancementD)
            setBackground(Color.GREEN);

        game.getGameobjects().add(this);
        game.getFrame().add(this);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> { game.dir = 0; }
            case KeyEvent.VK_S -> { game.dir = 1; }
            case KeyEvent.VK_A -> { game.dir = 2; }
            case KeyEvent.VK_D -> { game.dir = 3; }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
