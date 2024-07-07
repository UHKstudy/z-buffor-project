import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class ShapePanel extends JPanel {
    protected Camera camera;
    protected double[] rotation = {0, 0};
    protected int lastX, lastY;

    public ShapePanel() {
        camera = new Camera(new double[]{0, 0, 0}, new double[]{0, 0});
        InputHandler inputHandler = new InputHandler(this) ;
        addKeyListener(inputHandler);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }
        });
        addMouseMotionListener(inputHandler);
        addMouseWheelListener(inputHandler);
        setFocusable(true);
    }

    public void handleMousePress(MouseEvent e) {
        requestFocusInWindow();
    }

    public void handleMouseDrag(MouseEvent e) {
        int dx = e.getX() - lastX;
        int dy = e.getY() - lastY;

        rotation[0] += dy * 0.01;

        rotation[1] += dx * 0.01;

        lastX = e.getX();
        lastY = e.getY() ;
        repaint();
    }

    public void handleMouseMove(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    public void handleMouseWheel(MouseWheelEvent e) {
        camera.move(0, 0, e.getWheelRotation() * 0.5f);
        repaint();
    }

    public void handleKeyPress(char key) {
        switch (key) {
            case 'w':
                camera.move(0, -0.5f, 0);

                break;
            case 's':
                camera.move(0, 0.5f, 0);
                break;
            case 'a':
                camera.move(-0.5f, 0, 0);
                break;
            case 'd':
                camera.move(0.5f, 0, 0);
                break;
            case 'r':
                camera.move(0, 0, 0.5f);
                break;
            case 'f':
                camera.move(0, 0, -0.5f);
                break;
        }
        repaint();

    }

    protected abstract void drawShape(Graphics2D g2d);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g ;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        drawShape(g2d);
    }
}
