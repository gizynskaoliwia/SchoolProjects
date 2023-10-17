package Game;

import java.awt.*;
import java.util.Random;

public class Empty extends Gameobject {
    public Empty(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void tick() {
        if(new Random().nextInt(500)==354)
        {
            int type = new Random().nextInt(4);
            if(type == 0)
            {
                game.replace(new EnhancementA(x,y, game));
            }
            if(type == 1)
            {
                game.replace(new EnhancementB(x,y, game));
            }
            if(type == 2)
            {
                game.replace(new EnhancementC(x,y, game));
            }
            if(type == 3)
            {
                game.replace(new EnhancementD(x,y, game));
            }

        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y,25,25);
    }
}
