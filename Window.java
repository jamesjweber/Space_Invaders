package SI;

import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;

import java.awt.event.KeyListener;

public class Window extends Canvas {
    JFrame frame;

    Window(Game game){
        frame = new JFrame("Space Invaders");

        frame.setPreferredSize(new Dimension(750, 400));

        game.setBackground(Color.black);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        //com.apple.eawt.FullScreenUtilities.setWindowCanFullScreen(frame,true);
        //com.apple.eawt.Application.getApplication().requestToggleFullScreen(frame);
    }

    public int getWindowWidth(){ return frame.getWidth(); }
    public int getWindowHeight(){ return frame.getHeight(); }
    public Graphics getGraphics(){ return frame.getGraphics(); }

}
