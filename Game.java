package SI;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.*;
import javax.sound.sampled.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.IOException;

public class Game extends Canvas implements Runnable,Features {

    private Window window;
    private Handler handler;
    private Thread thread; // thread to be used later
    private boolean running; //is the program running
    private boolean gameWon = false;

    public Game() {
        handler = new Handler();
        window = new Window(this);
        this.addKeyListener(new KeyInput(window,handler));
        while(window.getWindowWidth() < 1279){ System.out.println(window.getWindowWidth()); }
        createObjects(handler);
        start();
    }

    public synchronized void start(){

        thread = new Thread(this);
        thread.start();
        running  = true;
    }

    public void createObjects(Handler h) {
        h.addObject(new Defender(window,handler));

        boolean maxY;
        int colsi = COLS_OF_INVADERS - 1;
        int rowsi = ROWS_OF_INVADERS - 1;

        for(int x = 0 ; x <= colsi; x++){
            for(int y = 0; y <= rowsi; y ++){
                if(y == (rowsi)){ maxY = true; }
                else{ maxY = false; }
                handler.addObject(new Invader(window,handler, window.getWindowWidth()*INVADER_START_X/100+x*window.getWindowWidth()*(100-2*INVADER_START_X)/100/colsi, window.getWindowHeight()*INVADER_START_Y/100+y*window.getWindowHeight()*(100-2*INVADER_START_Y)/100/rowsi-window.getWindowWidth()*(INVADER_Y_POS_PLS-50)/100, maxY));
            }
        }

        for(int i = window.getWindowWidth()/2 - window.getWindowWidth()*BARRIER_SPACING/100; i <= window.getWindowWidth()/2 + window.getWindowWidth()*BARRIER_SPACING/100; i = i + window.getWindowWidth()*BARRIER_SPACING/100){
            for(int x = -2; x < 2; x++){
                for(int y = 0; y < 4; y++){
                    h.addObject(new Barrier(window,handler, i+x*BARRIER_WIDTH+BARRIER_WIDTH/2, ((window.getWindowHeight()*4/5) + y*BARRIER_WIDTH - BARRIER_CUSHION) ));
                }
            }
        }

        for(int j = 1; j <= DEFENDER_LIVES; j++){
            h.addObject(new Heart(window,handler,j));
        }

        h.addObject(new Score(window,handler));
    }

    public static void main(String[] args) throws InterruptedException {
        new Game();
    }

    public synchronized void run()
    {
        long lastLoopTime = System.nanoTime();
        final long TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        long lastFpsTime = 0;
        long fps = 0;
        // keep looping round til the game ends

        try {
            File yourFile = new File("ROTP.wav");
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

        while (running)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            double delta = updateLength / ((double)OPTIMAL_TIME);
            lastLoopTime = now;

            while(delta >= 1){
                update();
                delta--;
            }

            if(running){
                paint();
            }

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {
                //System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            if(handler.gameObjects.get(0).getLives() <= 0){
                running = false;
                update();
                paint();
                update();
                paint();
            }

            boolean invadersLeft = false;
            for(int i = 0; i < handler.gameObjects.size(); i++){
                if(handler.gameObjects.get(i).getObjectType() == GameObjectType.INVADER){
                    invadersLeft = true;
                }
            }

            if(!invadersLeft){
                running = false;
                gameWon = true;
                update();
                paint();
                update();
                paint();
            }

            try{
                thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            }
            catch(Exception e){
                System.out.println(e);
            };
        }
        stop();
    }

    private void update(){ handler.update(); }

    private void paint(){

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,window.getWindowWidth(),window.getWindowHeight());

        handler.paint(g);

        if(gameWon){
            g.setFont(new Font("Courier", Font.PLAIN, GAMEOVER_FONT_SIZE));
            int width = g.getFontMetrics().stringWidth("Game Won");
            g.drawString("Game Won", window.getWindowWidth()/2 - width/2, window.getWindowHeight()/2);

        }

        g.dispose();
        bs.show();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
