import java.awt.Color;

public class Sudoku extends Graph {

  private static int offset = 40; //espaço entre os vertices

  private Vertice[][] mtrx = new Vertice[9][9]; //matriz de vertices que representam as celulas
  private int[][] initialGame; //matriz de valores iniciais (dados)
  public static int[][] colors = {
    { 138, 129, 124 },
    { 48, 48, 54 },
    { 58, 88, 103 },
    { 67, 127, 151 },
    { 90, 98, 45 },
    { 132, 147, 36 },
    { 194, 163, 26 },
    { 255, 179, 15 },
    { 246, 141, 12 },
    { 236, 102, 8 },
    { 56, 66, 58 },
    { 255, 0, 0 },
  }; //RGB das cores para GUI

  public Sudoku(int[][] initialGame, int time) {
    this.initialGame = initialGame;
    createVertices();
    setGroups();
    addEdges();
    new Coloring2(this, time); //faz o algoritmo funcionar neste grafo
    showGraph();
  }

  //cria os vertices e os posiciona na matriz de acordo com os valores iniciais (zero se nao foi dado nenhum)
  private void createVertices() {
    int x, y; //coordenadas de posicao para a GUI
    int count = 1; //nome do vertice para ordenacao, de 1 a 81
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        x = ((j + 1) * offset);
        y = ((i + 1) * offset);

        addVertice(count, initialGame[i][j], i + 1, j + 1, x, y); //vertice eh adicionado ao grafo
        mtrx[i][j] = vertices.get(vertices.size() - 1); //vertice eh adicionado a matriz
        count++;
      }
    }
  }

  //define as submatrizes de 3x3
  private void setGroups() {
    int group = 1;

    // itera por cada vertice da matriz 9x9
    for (int row = 0; row < 9; row += 3) {
      for (int col = 0; col < 9; col += 3) {
        // itera pelas posicoes dos vertices na submatriz 3x3
        for (int i = row; i < row + 3; i++) {
          for (int j = col; j < col + 3; j++) {
            Vertice v1 = mtrx[i][j];
            v1.setGroup(group);
            v1.setColor(
              new Color(
                colors[v1.getValue()][0],
                colors[v1.getValue()][1],
                colors[v1.getValue()][2]
              )
            ); //define a cor dos vertices com valores ja dados
          }
        }
        group = (group % 9) + 1; // incrementa o grupo para a proxima submatriz
      }
    }
  }

  //adiciona arestas entre os vertices que nao podem repetir cores
  private void addEdges() {
    //para cada vertice da matriz passa por todos os vertices
    for (Vertice[] line : mtrx) {
      for (Vertice v : line) {
        for (Vertice v2 : vertices) {
          //se forem do mesmo quadro 3x3, mesma linha ou mesma coluna e uma aresta nao incide sobre eles, adiciona uma
          if (
            (!v.equals(v2) && !areNeighbors(v, v2)) &&
            (
              (v.getGroup() == v2.getGroup()) ||
              (v.getLine() == v2.getLine()) ||
              (v.getCol() == v2.getCol())
            )
          ) {
            addEdge(v, v2);
          }
        }
      }
    }
  }

  public Vertice[][] getVerticesMatrix() {
    return mtrx;
  }
}
