import java.util.*;

public class MiniMax
{
  static int max_profMiniMax = 2;

  // Caso o PC jogue primeiro (X)
  public static Node MinValue(Node no, char p, int prof)
  {
    int n = Integer.MAX_VALUE;
    int b = Integer.MAX_VALUE;
    int movimento = 0;

    if(no.quadro.isEmpate() || no.quadro.isWinner(p) || prof == max_profMiniMax)
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
      temp.cpyNode(MaxValue(m, 'X', prof+1));
      n = Math.min(n, temp.pontuação);
      if(n < b)
      {
        b = n;
        movimento = temp.movimento;
      }
    }
    no.pontuação = n;
    if(prof == 0)
    no.movimento = movimento;
    return no;
  }

  // Caso o PC jogue em segundo (O)
  public static Node MaxValue(Node no, char p, int prof)
  {
    int n = Integer.MIN_VALUE;
    int b = Integer.MIN_VALUE;
    int movimento = 0;

    if(no.quadro.isEmpate() || no.quadro.isWinner(p) || prof == max_profMiniMax)
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
      Node temp = new Node(m.quadro, 'O');
      temp.cpyNode(MinValue(m, 'O', prof+1));
      n = Math.max(n, temp.pontuação);
      if(n > b)
      {
        b = n;
        movimento = temp.movimento;
      }
    }
    no.pontuação = n;
    if(prof == 0)
    no.movimento = movimento;
    return no;
  }
}
