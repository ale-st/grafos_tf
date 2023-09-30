import java.util.ArrayList;
import java.util.List;

public class Graph {

  protected List<Vertice> vertices;
  protected int order, nEdges;
  protected List<List<Vertice>> adjStrct;
  protected GraphVisualization gv;

  // construtor
  public Graph() {
    vertices = new ArrayList<>();
    adjStrct = new ArrayList<>();
    nEdges = 0;
    gv = new GraphVisualization(vertices, adjStrct);
    updateOrder();
  }

  public List<Vertice> getVertices() {
    return vertices;
  }

  public List<List<Vertice>> getAdjStrct() {
    return adjStrct;
  }

  private void updateOrder() {
    order = vertices.size();
  }

  public Vertice getVertice(int pos) {
    return vertices.get(pos);
  }

  public int getDegree(int idx) {
    return vertices.get(idx).getDeg();
  }

  // Reseta o grafo para a condição inicial.
  public void resetGraph() {
    vertices.clear();
    adjStrct.clear();
    nEdges = 0;
    updateOrder();
  }

  // Adiciona Vertices
  public void addVertice(int name, int value, int line, int col, int posx, int posy) {
    Vertice v = new Vertice(name, value, line, col, posx, posy);
    if (!vertices.contains(v)) {
      vertices.add(v); // Adiciona o Vertice.
      adjStrct.add(new ArrayList<>()); // Cria uma lista de arestas que incidem neste vertice.
      updateOrder();
    }
  }

  // Adiciona Arestas
  public void addEdge(int v1, int v2) {
    if ((v1 < order && v2 < order) && (v1 >= 0 && v2 >= 0)&& !adjStrct.get(v1).contains(vertices.get(v2)) && !adjStrct.get(v2).contains(vertices.get(v1))) { // Analisa se existe o vertice dado
      adjStrct.get(v1).add(vertices.get(v2)); // Adiciona o v2 na estrutura adjcente de v1.
      adjStrct.get(v2).add(vertices.get(v1)); // Adiciona o v1 na estrutura adjcente de v2.
      nEdges += 1;
      vertices.get(v1).incDegree(); // Adiciona 1 ao grau de v1.
      vertices.get(v2).incDegree(); // Adiciona 1 ao grau de v2.
    }
  }

  public void addEdge(Vertice v1, Vertice v2) {
    addEdge(vertices.indexOf(v1), vertices.indexOf(v2));
  }

  // remove arestas
  public void removeEdge(int v1, int v2) {
    if ((v1 < order && v2 < order) && (v1 >= 0 && v2 >= 0)) { // Analisa se existe o vertice dado.
      adjStrct.get(v1).remove(vertices.get(v2)); // remove o v2 na estrutura adjcente de v1.
      adjStrct.get(v2).remove(vertices.get(v1)); // remove o v1 na estrutura adjcente de v2.
      nEdges -= 1;
      vertices.get(v1).decDegree(); // remove 1 no grau de v1
      vertices.get(v2).decDegree(); // remove 1 no grau de v2.
    }
  }

  // retorna true se o vertice na posicao v2 aparece na lista de adjacencia do v1
  public boolean areNeighbors(int v1, int v2) {
    return adjStrct.get(v1).contains(vertices.get(v2));
  }

  public boolean areNeighbors(Vertice v1, Vertice v2) {
    return adjStrct.get(vertices.indexOf(v1)).contains(v2) || adjStrct.get(vertices.indexOf(v2)).contains(v1);
  }


  //Mostra as propriedades do grafo no console
  public void showGraph() {
    String s = "Numero de vertices: " + order + "\nNumero de arestas: " + nEdges + "\n";
  
    if (order != 0) {
      for (Vertice v : vertices) {
        s += "g(" + v.getName() + "): " + v.getDeg() + "\n";
      }
    }
    System.out.println(s + "\n--------------------------------\n");
    //new GraphVisualization(vertices, adjStrct);
  }
}
