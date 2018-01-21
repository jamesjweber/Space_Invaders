package SI;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;

public class Heart extends GameObject implements Features {

    int HeartNum;
    Window window;
    Handler handler;

    public Heart(Window window, Handler handler, int num){
        super(0,0,0,0,new Sprite("Heart.png"),GameObjectType.HEART);
        this.window = window;
        this.handler = handler;
        this.HeartNum = num;
        x = sprite.getSprite().getWidth(null) * (HeartNum-1) + 5;
        y = 5;
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
    }

    public void update(){
        if (HeartNum > lives){
            System.out.println("Lives: " + lives + " Heartnum: " + HeartNum);
            handler.removeObject(this);
        }
    }

    public void paint(Graphics g){
        g.drawImage(sprite.getSprite(),x,y,null);
    }

    public void terminate(){}

    public void updateMaxY(boolean maxY){}
}
