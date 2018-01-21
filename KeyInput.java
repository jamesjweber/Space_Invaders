package SI;

import javax.sound.sampled.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class KeyInput extends KeyAdapter {

    private Window window;
    private Handler handler;

    public KeyInput(Window window ,Handler handler){
        this.handler = handler;
        this.window = window;
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.gameObjects.size(); i++){
            GameObject tempObject  = handler.gameObjects.get(i);
            if(tempObject.getObjectType() == GameObjectType.DEFENDER){
                if (key == KeyEvent.VK_LEFT){
                    tempObject.setDx(-1);
                }
                if (key== KeyEvent.VK_RIGHT){
                    tempObject.setDx(1);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.gameObjects.size(); i++){
            GameObject tempObject  = handler.gameObjects.get(i);
            if(tempObject.getObjectType() == GameObjectType.DEFENDER){
                if (key == KeyEvent.VK_LEFT){
                    tempObject.setDx(0);
                }
                if (key== KeyEvent.VK_RIGHT){
                    tempObject.setDx(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    handler.addObject(new Shot(window,handler,tempObject.x + tempObject.sprite.getHalfSpriteX(), tempObject.y));
                    try {
                        File yourFile = new File("Shot.wav");
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
                    catch (Exception ee) {
                        System.out.println(ee);
                        //whatevers
                    }
                }
            }
        }
    }

}
