package SI;

import com.sun.tools.javadoc.Start;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Invader extends GameObject implements Features {

    Window window;
    Handler handler;
    static boolean hasShot = false;
    double StartTime;
    boolean maxY;
    boolean left = false;
    boolean right = true;
    boolean yUpdated = true;
    int pos = 0;
    int t = 0;

    public Invader(Window w, Handler h, int x, int y, boolean maxY) {
        super(x,y,X_MOVE_INVADERS,Y_MOVE_INVADERS,new Sprite("invader.jpg"),GameObjectType.INVADER);
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
        this.window = w;
        this.handler = h;
        this.maxY = maxY;
        StartTime = System.nanoTime();
    }

    public void shoot(){
        handler.addObject(new Bomb(window, handler,x+sprite.getHalfSpriteX(),y));
    }

    @Override
    public void update(){
        if((System.nanoTime() - StartTime)/1000000 > 1000 * RATE_OF_INVADERS / 16) {
            //System.out.println("Pos: " + pos + " x: " + x + " y: " + y);
            if (pos <= 8 && yUpdated || pos >= -8 && yUpdated) {
                updateX();
                if(left){
                    x -= dx;
                }
                else{
                    x += dx;
                }
                StartTime = System.nanoTime();
            } else {
                updateY();
                StartTime = System.nanoTime();
            }
            if(maxY){
                if(t++ <= INV_RATE_OF_FIRE *  16 / RATE_OF_INVADERS){
                    Random r = new Random();
                    int n = r.nextInt(2 * COLS_OF_INVADERS) + 1;
                    if (n == 1) {
                        if (!hasShot) {
                            shoot();
                            try {
                                File yourFile = new File("Bomb.wav");
                                AudioInputStream stream;
                                AudioFormat format;
                                DataLine.Info info;
                                Clip clip;

                                stream = AudioSystem.getAudioInputStream(yourFile);
                                format = stream.getFormat();
                                info = new DataLine.Info(Clip.class, format);
                                clip = (Clip) AudioSystem.getLine(info);
                                clip.open(stream);
                                clip.start();
                            }
                            catch (Exception e) {
                                System.out.println(e);
                                //whatevers
                            }
                            hasShot = true;
                        }
                    }
                }
                else{
                    t = 0;
                    hasShot = false;
                }
            }
        }
        rect = new Rectangle(x,y,sprite.getSprite().getWidth(null),sprite.getSprite().getHeight(null));
    }

    public void updateX(){
        if(left && pos > -8){
            pos--;
            return;
        }
        if(left && pos == -8){
            yUpdated = false;
            return;
        }
        if(right && pos < 8){
            pos++;
            return;
        }
        if(right && pos == 8){
            yUpdated = false;
            return;
        }

    }

    public void updateY(){
        y += dy;
        if(left){
            left = false;
            right = true;
        }
        else{
            right = false;
            left = true;
        }
        yUpdated = true;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(sprite.getSprite(), getX(), getY(), null);
    }

    @Override
    public void terminate(){ score++; }

    @Override
    public void updateMaxY(boolean maxY){
        this.maxY = maxY;
    }
}
