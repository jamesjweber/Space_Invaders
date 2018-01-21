package SI;

import java.awt.*;

public class Shot extends GameObject implements Features {

    private Window window;
    private Handler handler;

    public Shot(Window window, Handler handler, int x, int y) {
        super(x, y, 0, -SPEED_OF_SHOT, new Sprite("shot.png"), GameObjectType.SHOT);
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
        this.window = window;
        this.handler = handler;
    }

    @Override
    public void update(){
        if(y < -10){
            handler.removeObject(this);
        }
        y = y + dy;
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
    }

    @Override
    public void paint(Graphics g){ g.drawImage(sprite.getSprite(),x,y,null); }

    @Override
    public void terminate(){}

    @Override
    public void updateMaxY(boolean maxY){}
}
