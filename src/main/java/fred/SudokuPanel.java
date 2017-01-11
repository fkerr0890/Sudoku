package fred;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {
    public SudokuPanel(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        Double width=(double)this.getWidth()/3;
        Double height=(double)this.getHeight()/3;
        for (int x = 0; x < 3; x++) {
            g2.drawLine((int)Math.round(width*x), 0, (int)Math.round(width*x), this.getHeight());
            g2.drawLine(0,(int)Math.round(height*x),this.getWidth(),(int)Math.round(height*x));
        }
    }
}
