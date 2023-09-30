import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.awt.event.MouseAdapter;

public class GraphPanel extends JPanel {

    private List<Vertice> vertices;
    private List<List<Vertice>> adjStrct;
    private Vertice currV;

    public GraphPanel(List<Vertice> v, List<List<Vertice>> s) {

        vertices = v;
        adjStrct = s;
        
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setCurrV(e.getX(), e.getY());
                movev(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                movev(e.getX(), e.getY());
            }
        });
    }

    private void setCurrV(int x, int y) {
        for (Vertice v : vertices) {
            Ellipse2D ellipse = new Ellipse2D.Double(v.getX(), v.getY(), v.getSize(), v.getSize());
            if (ellipse.contains(x, y)) {
                currV = v;
                break;
            }
        }
    }

    private void movev(int x, int y) {

        final int vX = currV.getX();
        final int vY = currV.getY();
        final int vW = currV.getSize();
        final int vH = currV.getSize();
        final int OFFSET = 1;

        if ((vX!=x) || (vY!=y)) {
            repaint(vX, vY, vW+OFFSET, vH+OFFSET);
            currV.setX(x);
            currV.setY(y);
            repaint(currV.getX(), currV.getY(), currV.getSize()+OFFSET, currV.getSize()+OFFSET);
            repaint();
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        for (Vertice v : vertices) {
            for (Vertice v2: adjStrct.get(vertices.indexOf(v))) {
                v.paintEdge(g2d, v2);
                v2.paintVertice(g2d);
            }
            v.paintVertice(g2d);
        }

        if (currV != null) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(""+currV.getName(), currV.getX() + currV.getSize() + 5, currV.getY());
        }
    }

}
