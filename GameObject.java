package SI;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject extends JPanel implements Features {
    protected int x, y;
    protected int dx, dy;
    protected static int lives = DEFENDER_LIVES;
    protected static int score = 0;
    protected GameObjectType objectType;
    protected Sprite sprite;
    protected Rectangle rect;

    public GameObject(int x, int y, int dx, int dy, Sprite sprite, GameObjectType objectType){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.sprite = sprite;
        this.objectType = objectType;
    }

    public abstract void update();
    public abstract void paint(Graphics g);
    public abstract void terminate();
    public abstract void updateMaxY(boolean maxY);
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){ this.x = x; }
    public void setY(int y){
        this.y = y;
    }
    public int getDy(){ return dy; }
    public int getDx(){ return dx; }
    public void setDx(int dx){
        this.dx = dx;
    }
    public void setDy(int dy){
        this.dy = dy;
    }
    public GameObjectType getObjectType(){ return objectType; }
    public void setObjectType(GameObjectType o) { objectType = o; }
    public int getLives() { return lives; }

}
