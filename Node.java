import java.util.*;

public class Node
{
  static int nos = 0;
  Quadro quadro;
  char proxJogador;
  int movimento;
  int pontuação;

  // Nó pai (com movimento)
  public Node(Quadro quadro, char proxJogador, int movimento)
  {
    this.quadro = new Quadro(6,7);
    this.quadro.cpyQuadro(quadro);
    this.proxJogador = proxJogador;
    this.movimento = movimento;
    this.pontuação = 0;
  }

  // Nó filho (sem movimento)
  public Node(Quadro quadro, char proxJogador)
  {
    this.quadro = new Quadro(6, 7);
    this.quadro.cpyQuadro(quadro);
    this.proxJogador = proxJogador;
    this.movimento = 0;
    this.pontuação = 0;
  }

  // Fazer filhos
  public ArrayList<Node> makeChildren()
  {
    ArrayList<Node> childs = new ArrayList<Node>();
    Quadro quadrotp = new Quadro(6,7);
    quadrotp.cpyQuadro(quadro);
    for(int i=0; i<quadrotp.ncolunas; i++)
    {
      if(quadrotp.isPossivel(i))
      {
        quadrotp.cpyQuadro(quadro);
        quadrotp.NovoQuadro(i, proxJogador);
        if(proxJogador == 'X')
        {
          Node children = new Node(quadrotp,'O',i);
          childs.add(children);
        }
        else
        {
          Node children = new Node(quadrotp,'X',i);
          childs.add(children);
        }
      }
    }
    nos += childs.size();
    return childs;
  }

  public void cpyNode(Node n)
  {
    this.quadro.cpyQuadro(n.quadro);
    this.proxJogador = n.proxJogador;
    this.movimento = n.movimento;
    this.pontuação = n.pontuação;
  }
}
