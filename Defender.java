package SI;

import java.awt.*;

public class Defender extends GameObject implements Features {

    Window window;
    Handler handler;

    public Defender(Window w, Handler h) {
        super(0,0,0,0,new Sprite("defender.png"),GameObjectType.DEFENDER);
        this.window = w;
        this.handler = h;
        x = window.getWindowWidth()/2;
        y = window.getWindowHeight() * 9/10;
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
    }

    @Override
    public void update(){
        sprite.updateSprire("defender.png");
        setY(window.getWindowHeight() * 9/10);
        if (x + dx > 0 && x + dx < window.getWindowWidth() - sprite.getSprite().getWidth(null)){
            x = x + SPEED_OF_DEFENDER * dx;
        }
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
        //System.out.println("x: " + rect.x + " y: " + rect.y + " w: " + rect.width + " h: " + rect.height);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(sprite.getSprite(), getX(), getY() , null);

        //Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void terminate() {
        lives--;
        sprite.updateSprire("explosion.png");
        x = window.getWindowWidth()/2;
        y = window.getWindowHeight() * 9/10;
    }

    @Override
    public void updateMaxY(boolean maxY){}
}
