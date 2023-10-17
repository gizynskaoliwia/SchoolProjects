package Game;

import java.awt.*;

public class Obstacle extends Gameobject {
    public Obstacle(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y, 25,25);
    }
}
