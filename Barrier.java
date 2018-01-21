package SI;

import java.awt.*;

public class Barrier extends GameObject implements Features {

    Window window;
    Handler handler;

    public Barrier(Window w, Handler h, int x, int y) {
        super(x,y,0,0,new Sprite("BARRIER.png"),GameObjectType.BARRIER);
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
        this.window = w;
        this.handler = h;
    }

    @Override
    public void update(){}

    @Override
    public void paint(Graphics g){
        g.drawImage(sprite.getSprite(), x, y, null);
    }

    @Override
    public void terminate(){}

    @Override
    public void updateMaxY(boolean maxY){}
}
