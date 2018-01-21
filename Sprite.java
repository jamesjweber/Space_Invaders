package SI;

import javax.swing.*;
import java.awt.*;

public class Sprite {

    private ImageIcon ii;
    private Image i;

    public Sprite(String s){
        ii = new ImageIcon(s);
        i = ii.getImage();
    }

    public Image getSprite(){
       return i;
    }

    public int getHalfSpriteX(){
        return i.getWidth(null)/2;
    }

    public int getHalfSpriteY(){
        return i.getHeight(null)/2;
    }

    public Image updateSprire(String s){
        ii = new ImageIcon(s);
        return ii.getImage();
    }
}
