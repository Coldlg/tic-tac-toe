public class Engine {
  public char[][] board = new char[3][3];
  public char player;
  
  public Engine() {
    player = 'X';

    for (int i = 0; i < 3; i++){
      for (int l = 0; l < 3; l++){
        board[i][l] = '-';
      }
    }
  }

  public void start() {
    for(int i = 0; i < 3; i++){
      for(int l = 0; l < 3; l++){
        System.out.print(board[i][l] + " ");
      }
      System.out.println();
    }
  }
}
