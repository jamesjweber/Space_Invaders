package SI;

import java.awt.*;

public class Score extends GameObject implements Features {

    Window window;
    Handler handler;
    Font font;

    public Score(Window window, Handler handler){
        super(0,0,0,0,null,GameObjectType.SCORE);
        this.window = window;
        this.handler = handler;
        rect = new Rectangle(x,y,x,y);
        font = new Font("Courier", Font.PLAIN, SCORE_FONT_SIZE);
    }

    @Override
    public void update(){}

    @Override
    public void paint(Graphics g){
        g.setFont(font);
        g.setColor(Color.WHITE);
        String out = "Score: " + String.valueOf(score);
        int width = g.getFontMetrics().stringWidth(out);
        g.drawString(out, window.getWindowWidth() - width - 10, SCORE_FONT_SIZE + 5);

        g.setFont(new Font("Courier", Font.PLAIN, GAMEOVER_FONT_SIZE));
        width = g.getFontMetrics().stringWidth("Game Over");
        if(lives <= 0){
            g.drawString("Game Over", window.getWindowWidth()/2 - width/2, window.getWindowHeight()/2);
        }
    }

    @Override
    public void terminate(){}

    @Override
    public void updateMaxY(boolean maxY){}

}
