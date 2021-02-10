import java.util.*;

public class AlphaBeta
{
  static int max_profAB = 8;

  public static Node AB_MinValue(Node no, char p, int prof, int alpha, int beta)
  {
    int n = Integer.MAX_VALUE;
    int b = Integer.MAX_VALUE;
    int movimento = 0;

    // Caso Terminal
    if(no.quadro.isEmpate() || no.quadro.isWinner(p) || prof == max_profAB)
    {
      if(p == 'X')
      {
        no.pontuação = no.quadro.pontuaçaoTotal(p) - prof;
        return no;
      }
      else
      {
        no.pontuação = no.quadro.pontuaçaoTotal(p) + prof;
        return no;
      }
    }

    for(Node m : no.makeChildren())
    {
      Node temp = new Node(m.quadro, 'X');
      temp.cpyNode(AB_MaxValue(m, 'X', prof+1, alpha, beta));
      n = Math.min(n, temp.pontuação);
      if(n < b)
      {
        b = n;
        movimento = temp.movimento;
      }
      if(alpha >= beta)
      {
        no.pontuação = n;
        return no;
      }
      beta = Math.max(alpha, n);
    }
    no.pontuação = n;
    if(prof == 0)
    no.movimento = movimento;
    return no;
  }

  public static Node AB_MaxValue(Node no, char p, int prof, int alpha, int beta)
  {
    int n = Integer.MIN_VALUE;
    int b = Integer.MIN_VALUE;
    int movimento = 0;

    if(no.quadro.isEmpate() || no.quadro.isWinner(p) || prof == max_profAB)
    {
      // Caso Terminal
      if(p == 'X')
      {
        no.pontuação = no.quadro.pontuaçaoTotal(p) - prof;
        return no;
      }
      else
      {
        no.pontuação = no.quadro.pontuaçaoTotal(p) + prof;
        return no;
      }
    }

    for(Node m : no.makeChildren())
    {
      Node temp = new Node(m.quadro, 'O');
      temp.cpyNode(AB_MinValue(m, 'O', prof+1, alpha, beta));
      n = Math.max(n, temp.pontuação);
      if(n > b)
      {
        b = n;
        movimento = temp.movimento;
      }
      if(beta <= alpha)
      {
        no.pontuação = n;
        return no;
      }
      alpha = Math.min(beta, n);
    }
    no.pontuação = n;
    if(prof == 0)
    no.movimento = movimento;
    return no;
  }
}
