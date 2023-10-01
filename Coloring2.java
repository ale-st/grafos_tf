import java.awt.Color;
import java.util.List;

public class Coloring2 {

  private Sudoku g;
  private Vertice[][] mtrx;
  private List<List<Vertice>> adjStrct;

  public Coloring2(Sudoku g) {
    this.g = g;
    mtrx = g.getVerticesMatrix();
    adjStrct = g.getAdjStrct();
    solveSudoku(mtrx);
  }

  // verifica se eh seguro colocar a cor no vertice com posicao (row, col)
  private boolean isSafe(Vertice[][] matrix, int row, int col, int color) {
    Vertice v1 = matrix[row][col];
    //testa se algum vizinho de v1 tem a cor testada
    for (Vertice v2 : adjStrct.get(v1.getName() - 1)) {
      if (v2.getValue() == color) return false;
    }
    return true; //se nenhum tem retorna true
  }

  private boolean solveSudoku(Vertice[][] board) {
    g.gv.repaint(); // atualiza a GUI a cada tentativa de coloracao
    try {
      Thread.sleep(5); // um delay em milisegs para visualizacao
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    int row = -1;
    int col = -1;
    boolean allColored = true;
    //percorre as 81 celulas buscando celulas vazias
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        //se alguma delas nao tem cor definida
        if (board[i][j].getValue() == 0) {
          row = i;
          col = j;
  
          allColored = false;
          break;
        }
      }
      //se tem celula sem cor, para o loop
      if (!allColored) {
        break;
      }
    }

    // todas celulas tem cor
    if (allColored) {
      return true;
    }

    //se nao, define uma cor para essa celula
    for (int color = 1; color <= 9; color++) {
      Vertice v = board[row][col];
      if (isSafe(board, row, col, color)) { //assume que eh seguro definir a cor para esta celula
        v.setValue(color);
        v.setColor(
          new Color(
            Sudoku.colors[v.getValue()][0],
            Sudoku.colors[v.getValue()][1],
            Sudoku.colors[v.getValue()][2]
          ));
        if (solveSudoku(board)) { //busca por proxima celula sem cor
            return true; 
        }
        else {                  //se nao for seguro colocar, volta a ficar sem cor
          v.setValue(0);
          v.setColor(Color.BLACK);
        }
      }
    }
    return false; //nao tem solucao
  }
}

//Codigo do geek for geeks adaptado para solucao em grafos
