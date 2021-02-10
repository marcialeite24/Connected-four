public class Quadro
{
  char[][] quadro;
  int nlinhas, ncolunas;

  // Construtor do Quadro
  public Quadro(int rows, int cols)
  {
    this.nlinhas = rows;
    this.ncolunas = cols;
    quadro = new char[rows][cols];

    for(int i = 0; i < rows; i++)
    {
      for(int j = 0; j < cols; j++)
        quadro[i][j] = '·';
    }
  }

  // Verificar se é um movimento possível ou não
  public boolean isPossivel(int cols)
  {
      if(cols < 0 || cols > nlinhas || !(quadro[0][cols] == '·'))
        return false;

    return true;
  }

  // Imprime o quadro de jogo
  public void printQuadro()
  {
    for(int i = 0; i < nlinhas; i++)
    {
      System.out.print("\t");
      for(int j = 0; j < ncolunas; j++)
        System.out.print(quadro[i][j] + " ");
      System.out.println();
    }
    System.out.println("\t0 1 2 3 4 5 6\n");
  }

  public void NovoQuadro(int cols, char c)
  {
    for(int i = nlinhas-1; i >= 0; i--)
    {
      if(quadro[i][cols] == '·') {
        quadro[i][cols] = c;
        break;
      }
    }
  }

  // Copiar o quadro de jogo
  public void cpyQuadro(Quadro q)
  {
    for(int i = 0; i < q.nlinhas; i++)
      for(int j = 0; j < q.ncolunas; j++)
        quadro[i][j] = q.quadro[i][j];
  }

  // Verifica se o jogador é vencedor ou não
  public boolean isWinner(char p)
  {
    int l = 0;
    // Linhas
    for(int i = 0; i < nlinhas; i++)
    {
      for(int j = 0; j < ncolunas; j++)
      {
        if(quadro[i][j] == p) l++;
        else l = 0;
        if(l == 4) return true;
      }
      l = 0;
    }

    // Colunas
    for(int i = 0; i < ncolunas; i++)
    {
      for(int j = 0; j < nlinhas; j++)
      {
        if(quadro[j][i] == p) l++;
        else l = 0;
        if(l == 4) return true;
      }
      l = 0;
    }

    // Diagonais para a direita(/)
    for(int i = 3; i < nlinhas; i++)
    {
      for(int j = 0; j < ncolunas - 3; j++)
        if(quadro[i][j] == p && quadro[i-1][j+1] == p && quadro[i-2][j+2] == p && quadro[i-3][j+3] == p)
          return true;
    }

    // Diagonais para a esquerda(\)
    for(int i = 3; i < nlinhas; i++)
    {
      for(int j = ncolunas - 1; j >= ncolunas - 4; j--)
        if(quadro[i][j] == p && quadro[i-1][j-1] == p && quadro[i-2][j-2] == p && quadro[i-3][j-3] == p)
          return true;
    }
    return false;
  }

  // Verificar se é um empate
  public boolean isEmpate()
  {
    int e = 0;
    for(int i = 0; i < nlinhas; i++)
    {
      for(int j = 0; j < ncolunas; j++)
        if(quadro[i][j] == '·')
          e++;
    }
    if(e == 0) return true;
    return false;
  }

  // Calcular a pontuação para um pequena parte do quadro do jogo
  public int valor_pont(int sX, int sO)
  {
    // Se não encontrar nenhum O
    if(sO == 0)
    {
      if(sX == 1)
        return 1;
      else if(sX == 2)
        return 10;
      else if(sX == 3)
        return 50;
      else if(sX == 4)
        return 512;
    }

    // Se não encontrar nenhum X
    else if(sX == 0)
    {
      if(sO == 1)
        return -1;
      else if(sO == 2)
        return -10;
      else if(sO == 3)
        return -50;
      else if(sO == 4)
        return -512;
    }
    return 0;
  }

  // Linhas
  public int valor_linhas(char p)
  {
    int sLinhasX = 0, sLinhasO = 0, sLinhas = 0;
    for(int i = 0; i < nlinhas; i++)
    {
      for(int j = 0; j < ncolunas-3; j++)
      {
        // Para X
        if(quadro[i][j] == 'X')
          sLinhasX++;
        if(quadro[i][j+1] == 'X')
          sLinhasX++;
        if(quadro[i][j+2] == 'X')
          sLinhasX++;
        if(quadro[i][j+3] == 'X')
          sLinhasX++;

        // Para O
        if(quadro[i][j] == 'O')
          sLinhasO++;
        if(quadro[i][j+1] == 'O')
          sLinhasO++;
        if(quadro[i][j+2] == 'O')
          sLinhasO++;
        if(quadro[i][j+3] == 'O')
          sLinhasO++;

        sLinhas += valor_pont(sLinhasX, sLinhasO);
        sLinhasX = 0;
        sLinhasO = 0;
      }
    }
    return sLinhas;
  }

  // Colunas
  public int valor_colunas(char p)
  {
    int sColunasX = 0, sColunasO = 0, sColunas = 0;
    for(int i = 0; i < ncolunas-4; i++)
    {
      for(int j = 0; j < nlinhas+1; j++)
      {
        // Para X
        if(quadro[i][j] == 'X')
          sColunasX++;
        if(quadro[i+1][j] == 'X')
          sColunasX++;
        if(quadro[i+2][j] == 'X')
          sColunasX++;
        if(quadro[i+3][j] == 'X')
          sColunasX++;

        // Para O
        if(quadro[i][j] == 'O')
          sColunasO++;
        if(quadro[i+1][j] == 'O')
          sColunasO++;
        if(quadro[i+2][j] == 'O')
          sColunasO++;
        if(quadro[i+3][j] == 'O')
          sColunasO++;

        sColunas += valor_pont(sColunasX, sColunasO);
        sColunasX = 0;
        sColunasO = 0;
      }
    }
    return sColunas;
  }

  // Diagonais para a direita
  public int valor_dDireita(char p)
  {
    int sDireitaX = 0, sDireitaO = 0, sDireita = 0;
    for(int i = 3; i < nlinhas; i++)
    {
      for(int j = 0; j < ncolunas-3; j++)
      {
        // Para X
        if(quadro[i][j] == 'X')
          sDireitaX++;
        if(quadro[i-1][j+1] == 'X')
          sDireitaX++;
        if(quadro[i-2][j+2] == 'X')
          sDireitaX++;
        if(quadro[i-3][j+3] == 'X')
          sDireitaX++;

        // Para O
        if(quadro[i][j] == 'O')
          sDireitaO++;
        if(quadro[i-1][j+1] == 'O')
          sDireitaO++;
        if(quadro[i-2][j+2] == 'O')
          sDireitaO++;
        if(quadro[i-3][j+3] == 'O')
          sDireitaO++;

        sDireita += valor_pont(sDireitaX, sDireitaO);
        sDireitaX = 0;
        sDireitaO = 0;
      }
    }
    return sDireita;
  }

  // Diagonais para a esquerda
  public int valor_dEsquerda(char p)
  {
    int sEsquerdaX = 0, sEsquerdaO = 0, sEsquerda = 0;
    for(int i = 3; i < nlinhas; i++)
    {
      for(int j = ncolunas-1; j >= ncolunas-4; j--)
      {
        // Para X
        if(quadro[i][j] == 'X')
          sEsquerdaX++;
        if(quadro[i-1][j-1] == 'X')
          sEsquerdaX++;
        if(quadro[i-2][j-2] == 'X')
          sEsquerdaX++;
        if(quadro[i-3][j-3] == 'X')
          sEsquerdaX++;

        // Para O
        if(quadro[i][j] == 'O')
          sEsquerdaO++;
        if(quadro[i-1][j-1] == 'O')
          sEsquerdaO++;
        if(quadro[i-2][j-2] == 'O')
          sEsquerdaO++;
        if(quadro[i-3][j-3] == 'O')
          sEsquerdaO++;

        sEsquerda += valor_pont(sEsquerdaX, sEsquerdaO);
        sEsquerdaX = 0;
        sEsquerdaO = 0;
      }
    }
    return sEsquerda;
  }

  // Calcula a pontuação total do quadro do jogo
  public int pontuaçaoTotal(char p)
  {
    if(isWinner(p) && p == 'X') return 512;
    else if(isWinner(p) && p == 'O') return -512;
    else if(isEmpate()) return 0;
    if(p == 'X')
    return ((valor_linhas(p) + valor_colunas(p) + valor_dDireita(p) + valor_dEsquerda(p)) + 16);
    else
    return ((valor_linhas(p) + valor_colunas(p) + valor_dDireita(p) + valor_dEsquerda(p)) - 16);
  }
}
