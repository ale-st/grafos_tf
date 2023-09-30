import java.util.List;
import javax.swing.JFrame;

public class GraphVisualization extends JFrame {

    public GraphVisualization(List<Vertice> v, List<List<Vertice>> s) {
        super("Graph Visualization");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GraphPanel(v, s));
        pack();
        setVisible(true);
    }
}
