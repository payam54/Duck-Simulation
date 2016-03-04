package ducksim;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * DuckSimView is a JPanel that will serve as the content pane to the main
 * JFrame window.
 *
 * @author gregory
 */
public class DuckSimView extends JPanel {

    // ==========================================================
    // Private fields
    // ==========================================================
    private final DuckSimModel model;

    // Field for escape key action
    private Action escapeAction;

    // Fields for popup menu
    private JPopupMenu popup;

    // Fields for flying animation
    private final Timer flyTimer;
    private int animationX = -50;

    // Fields for quacking animation
    private final Timer quackTimer;
    private int quackCounter;

    public DuckSimView(DuckSimModel model) {

        this.model = model;

        flyTimer = new Timer(25, e -> {
            animationX = animationX + 5;
            repaint();
        });

        quackTimer = new Timer(100, e -> repaint());
        quackCounter = 0;

        getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "doEscapeAction");
        getActionMap().put("doEscapeAction", escapeAction);

    }

    public void setEscapeAction(Action action) {
        escapeAction = action;
    }

    public Timer getFlyTimer() {
        return flyTimer;
    }

    public Timer getQuackTimer() {
        return quackTimer;
    }

    public JPopupMenu getPopup() {
        return popup;
    }

    public void initializePopup() {
        popup = new JPopupMenu();
    }

    public boolean clickedNewDuckButton(MouseEvent e) {
        return isWithinRect(e.getX(), e.getY(), 30, 500, 50, 50);
    }

    public int getClickedDuck(MouseEvent e) {
        for (int i = 0; i < Math.min(model.currDuckCount(), 4); i++) {
            if (isWithinRect(e.getX(), e.getY(), 150 + i * 125, 325, 100, 100)) {
                return i;
            }
        }
        for (int i = 4; i < Math.min(model.currDuckCount(), 8); i++) {
            if (isWithinRect(e.getX(), e.getY(), 150 + (i - 4) * 125, 425, 100, 100)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isWithinRect(int x, int y, int rX, int rY, int rW, int rH) {
        return (x >= rX && x <= rX + rW && y >= rY && y <= rY + rH);
    }

    // ==========================================================
    // Paint methods
    // ==========================================================
    public void paintQuack(Graphics g) {
        int currentDuck = model.getCurrentDuck();
        if (currentDuck == -1 || !quackTimer.isRunning()) {
            return;
        }
        g.setColor(Color.BLACK);

        String quack = model.getDuck(currentDuck).getQuack();

        g.setFont(new Font("Verdana", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        int totalWidth = fm.stringWidth(quack);
        g.drawString(quack, 400 - totalWidth / 2, 100);

        quackCounter = quackCounter + 100;

        if (quackCounter > 2000) {
            quackTimer.stop();
            model.getDuck(currentDuck).swim();
            quackCounter = 0;
            repaint();
        }
    }

    public void paintFlyingDuck(Graphics g) {
        int currentDuck = model.getCurrentDuck();
        if (currentDuck == -1) {
            return;
        }
        g.setColor(model.getDuck(currentDuck).getColor());
        g.fillRect(animationX, 100, 50, 25);
        if (animationX == 800) {
            flyTimer.stop();
            model.getDuck(currentDuck).swim();
            animationX = -50;
            repaint();
        }
    }

    public void paintDuck(Graphics g, int pos) {
        int x = 0;
        int y = 0;

        if (model.containsDuck(pos)) {
            switch (pos) {
                case 0:
                case 1:
                case 2:
                case 3:
                    x = 150 + (pos) * 125;
                    y = 325;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    x = 150 + (pos - 4) * 125;
                    y = 450;
                    break;
                default:
                    assert false;
            }
            if (model.getCurrentDuck() == pos && (flyTimer.isRunning() || quackTimer.isRunning())) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x, y, 100, 100);
            g.setColor(Color.GRAY);
            g.fillRect(x + 5, y + 5, 90, 90);

            if (model.isSelected(pos)) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x + 10, y + 10, 80, 80);

            if (model.isSelected(pos)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }

            String[] text = model.getDuck(pos).display().split(":");

            // Draw duck text
            g.setFont(new Font("Verdana", Font.BOLD, 14));
            FontMetrics fm = g.getFontMetrics();
            int totalWidth;

            g.setColor(Color.BLUE);
            if (model.getDuck(pos).isOnDSWC()) {
                if (model.getDuck(pos).getState() == State.WELCOMING) { 
                    totalWidth = fm.stringWidth("Welcome!");
                    g.drawString("Welcome!", x + 50 - totalWidth / 2, y + 35);
                    model.getDuck(pos).swim();
                }
            }
            
            
            if (model.isSelected(pos)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }
            totalWidth = fm.stringWidth(text[0]);
            g.drawString(text[0], x + 50 - totalWidth / 2, y + 55);
            
            
            // Draw bling text
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < text.length; i++) {
                if (i != 1) {
                    sb.append(" ");
                }
                sb.append(text[i]);
            }
            String bling = sb.toString();
            totalWidth = fm.stringWidth(bling);
            g.drawString(bling, x + 50 - totalWidth / 2, y + 75);

            if (!model.getDuck(pos).isFree()) {
                g.setColor(Color.RED);
                g.drawString("X", x + 75, y + 85);
            }
            
            if (model.getDuck(pos).isWelcoming()) {
            	String welcomeMsg = model.getDuck(pos).getWelcomeMsg();
            	totalWidth = fm.stringWidth(welcomeMsg);
            	g.drawString(welcomeMsg, x + 50 - totalWidth / 2, y + 30);
            }

        }
    }

    private void paintAddDuckButton(Graphics g) {
        int x = 30;
        int y = 500;

        g.setColor(Color.BLACK);
        g.fillOval(x, y, 50, 50);
        g.setColor(Color.GRAY);
        g.fillOval(x + 2, y + 2, 46, 46);
        g.setColor(Color.BLACK);
        g.fillOval(x + 5, y + 5, 40, 40);
        g.setColor(Color.GRAY);
        g.fillRect(x + 20, y + 10, 10, 30);
        g.fillRect(x + 10, y + 20, 30, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int i = 0; i < model.currDuckCount(); i++) {
            paintDuck(g, i);
            model.getDuck(i).unWelcome();
        }

        paintAddDuckButton(g);
        paintQuack(g);
        paintFlyingDuck(g);
    }

}
