import java.awt.event.*;

public class InputHandler extends KeyAdapter implements MouseMotionListener, MouseWheelListener {
    private ShapePanel shapePanel;

    public InputHandler(ShapePanel shapePanel) {
        this.shapePanel = shapePanel ;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        shapePanel.handleKeyPress(key) ;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        shapePanel.handleMouseDrag(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        shapePanel.handleMouseMove(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        shapePanel.handleMouseWheel(e);
    }
}
