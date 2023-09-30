import java.awt.Color;
import java.util.List;

public class Coloring {
    
    private Sudoku g;
    private int[][] initialGame;
    private Vertice[][] mtrx;
    private List<List<Vertice>> adjStrct;

    public Coloring(Sudoku g) {
        this.g = g;
        initialGame = g.getInitialGame();
        mtrx = g.getVerticesMatrix();
        adjStrct = g.getAdjStrct();
        solveSudoku(mtrx, 0, 0);
    }


  // verifica se eh seguro colocar a cor no vertice com posicao (row, col)
  private boolean isSafe(Vertice[][] matrix, int row, int col, int color) {
    //se a cor for a mesma definida no inicio
    if (initialGame[row][col] == color) return true;
    //se for diferente de zero e nao for a mesma cor
    if (
      initialGame[row][col] != 0 && initialGame[row][col] != color
    ) return false;

    Vertice v1 = matrix[row][col];
    //testa se algum vizinho de v1 tem a cor testada
    for (Vertice v2 : adjStrct.get(v1.getName() - 1)) {
      if (v2.getValue() == color) return false;
    }
    return true;
  }

  //resolve o sudoku usando um algoritmo de backtracking
  private boolean solveSudoku(Vertice grid[][], int row, int col) {
    try {
      Thread.sleep(5); // um delay de milisegs para visualizacao
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //se eh a ultima linha e ultima coluna, retorna true (chegou no ultimo vertice)
    if (row == 9 - 1 && col == 9) return true;

    // se chega ao ultimo valor da coluna, passa para proxima linha
    if (col == 9) {
      row++;
      col = 0;
    }

    Vertice v = grid[row][col];

    // se a posicao atual ja esta colorida, passa para proxima coluna
    if (v.getValue() != 0) return solveSudoku(grid, row, col + 1);

    for (int color = 1; color <= 9; color++) {
      // se for seguro colocar a cor  no vertice da posicao atual
      if (isSafe(grid, row, col, color)) {
        //define a cor para o vertice atual, assumindo que esta correta
        v.setValue(color);
        v.setColor(
          new Color(
            Sudoku.colors[v.getValue()][0],
            Sudoku.colors[v.getValue()][1],
            Sudoku.colors[v.getValue()][2]
          )
        );
        g.gv.repaint(); // atualiza a GUI a cada tentativa de coloracao

        // verifica a proxima coluna
        if (solveSudoku(grid, row, col + 1)) return true;
      }
      //remove a cor definida, e tenta com a proxima
      v.setValue(0);
      v.setColor(Color.BLACK);
    }
    return false;
  }
}
