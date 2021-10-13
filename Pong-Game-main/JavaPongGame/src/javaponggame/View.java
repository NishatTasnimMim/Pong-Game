package javaponggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class View extends JPanel {

    Model model;
    Rectangle bounds;

    public View() {
        setBackground(Color.BLUE);
        bounds = new Rectangle(0, 0, 705, 670);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void addKeyBinding(String name, int keyEvent, boolean pressed, AbstractAction action) {
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyEvent, 0, !pressed), name);
        actionMap.put(name, action);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (model.getEntities() != null) {
            for (Entity entity : model.getEntities()) {
                entity.paint(g);
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", 1, 20));
            g.drawString(model.getPaddleScore(1) + " : " + model.getPaddleScore(2), 350, 20);
        } else {
            System.out.println("Something is wrong with the entities...");
        }
    }

    @Override
    public Rectangle getBounds() {
        return bounds;

    }
}
    

