package inputs;

import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    /*
    We need to import GamePanel so that we can directly control movements within the GUI, or gamePanel
     */
    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
        // we are instantiating our own instance of gamePanel with this call
        this.gamePanel = gamePanel;

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
       gamePanel.setRectPos(e.getX(), e.getY());
    }
}
