import java.util.*;
import java.lang.*;

public class Jogo4emLinha
{
  // Limpar o Terminal
  public static void clear()
  {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // Atrasa um segundo
  public static void atraso()
  {
    try
    {
      Thread.sleep(1000);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  // Vez do PC
  public static void PlayPC(char p, int a, Quadro q)
  {
    // Minimax
    if(a == 1)
    {
      // Vez do PC - Minimax (MaxValue)
      if(p == 'X')
      {
        System.out.print("\n-> PC - Minimax(X): ");
        Node quadro = new Node(q,'X');
        long timeI = System.currentTimeMillis(); // Começa o Minimax (Tempo Inicial)
        Node colunaPC = MiniMax.MaxValue(quadro,'X',0);
        long timeF = (long) (System.currentTimeMillis()); // Termina o Minimax (Tempo Final)
        System.out.println(colunaPC.movimento + "\n");
        q.NovoQuadro(colunaPC.movimento,'X');
        q.printQuadro();
        System.out.println("Pontuação do PC: " + q.pontuaçaoTotal('X')); // Pontuação de X (Se ganhar fica com 512 pontos)
        System.out.printf("Tempo de Execução: %.3f ms%n", (timeF - timeI)/1000d); // Tempo de execução do PC com o Minimax (Tempo Final - Tempo Inicial)
        System.out.println("Nós gerados: " + colunaPC.nos + "\n"); // Número de nós gerados para o PC fazer a sua jogada
      }
      // Vez do PC - Minimax (MinValue)
      else if(p == 'O')
      {
        System.out.print("\n-> PC - Minimax(O): ");
        Node quadro = new Node(q,'O');
        long timeI = System.currentTimeMillis(); // Começa o Minimax (Tempo Inicial)
        Node colunaPC = MiniMax.MinValue(quadro,'O',0);
        long timeF = (long) (System.currentTimeMillis()); // Termina o Minimax (Tempo Final)
        System.out.println(colunaPC.movimento + "\n");
        q.NovoQuadro(colunaPC.movimento,'O');
        q.printQuadro();
        System.out.println("Pontuação do PC: " + q.pontuaçaoTotal('O')); // Pontuação de O (Se ganhar fica com -512 pontos)
        System.out.printf("Tempo de Execução: %.3f ms%n", (timeF - timeI)/1000d); // Tempo de execução do PC com o Minimax (Tempo Final - Tempo Inicial)
        System.out.println("Nós gerados: " + colunaPC.nos + "\n"); // Número de nós gerados para o PC fazer a sua jogada
      }
    }
    // AlphaBeta
    else if(a == 2)
    {
      // Vez do PC - Alphabeta (AB_MaxValue)
      if(p == 'X')
      {
        System.out.print("\n-> PC - Alphabeta(X): ");
        Node quadroX = new Node(q,'X');
        long timeI = System.currentTimeMillis(); // Começa o Alphabeta (Tempo Inicial)
        Node colunaPC = AlphaBeta.AB_MaxValue(quadroX,'X',0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        long timeF = (long) (System.currentTimeMillis()); // Termina o Alphabeta (Tempo Final)
        System.out.println(colunaPC.movimento + "\n");
        q.NovoQuadro(colunaPC.movimento,'X');
        q.printQuadro();
        System.out.println("Pontuação do PC: " + q.pontuaçaoTotal('X')); // Pontuação de X (Se ganhar fica com 512 pontos)
        System.out.printf("Tempo de Execução: %.3f ms%n", (timeF - timeI)/1000d); // Tempo de execução do PC com o Alphabeta (Tempo Final - Tempo Inicial)
        System.out.println("Nós gerados: " + colunaPC.nos + "\n"); // Número de nós gerados para o PC fazer a sua jogada
      }
      // Vez do PC - Alphabeta (AB_MinValue)
      else if(p == 'O')
      {
        System.out.print("\n-> PC - Alphabeta(O): ");
        Node quadroO = new Node(q,'O');
        long timeI = System.currentTimeMillis(); // Começa o Alphabeta (Tempo Inicial)
        Node colunaPC = AlphaBeta.AB_MinValue(quadroO,'O',0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        long timeF = (long) (System.currentTimeMillis()); // Termina o Alphabeta (Tempo Final)
        System.out.println(colunaPC.movimento + "\n");
        q.NovoQuadro(colunaPC.movimento,'O');
        q.printQuadro();
        System.out.println("Pontuação do PC: " + q.pontuaçaoTotal('O')); // Pontuação de O (Se ganhar fica com -512 pontos)
        System.out.printf("Tempo de Execução: %.3f ms%n", (timeF - timeI)/1000d); // Tempo de execução do PC com o Alphabeta (Tempo Final - Tempo Inicial)
        System.out.println("Nós gerados: " + colunaPC.nos + "\n"); // Número de nós gerados para o PC fazer a sua jogada
      }
    }
  }

  // Jogar contra o PC
  public static void ContraPC(int s)
  {
    Scanner stdin = new Scanner(System.in);
    int coluna, a;

    // Escolher o algoritmo que o PC ai usar
    System.out.print("Escolhe o algoritmo com que o PC vai jogar:\n(1) Minimax\n(2) Alphabeta\n(3) Voltar para o menu do jogo\n-> ");
    while(true)
    {
      a = stdin.nextInt();
      if(a == 1 || a == 2 || a == 3)
        break;
      System.out.print("Digite 1, 2 ou 3: ");
    }

    // Quadro inicial
    Quadro q = new Quadro(6,7);

    // Minimax
    if(a == 1)
    {
      // Se escolher X joga primeiro
      if(s == 1)
      {
        // Começa o Jogo
        System.out.println("\n\t LET'S PLAY\n");
        q.printQuadro();
        while(true)
        {
          // Sua Vez
          System.out.print("-> Sua Jogada (X): ");
          // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
          while(true)
          {
            coluna = stdin.nextInt();
            System.out.println();
            if(q.isPossivel(coluna))
            {
              q.NovoQuadro(coluna, 'X');
              q.printQuadro();
              break;
            }
            System.out.print("Jogada Inválida!\nTente Novamente\n-> Sua Jogada (X): ");
          }
          System.out.println("Sua Pontuação: " + q.pontuaçaoTotal('X') + "\n"); // Pontuação de X (Se ganhar fica com 512 pontos)

          if(q.isWinner('X'))
          {
            atraso();
            clear();
            System.out.println("Boa, ganhaste!");
            return;
          }
          else if(q.isEmpate())
          {
            atraso();
            clear();
            System.out.println("Empate meus caros Jogadores");
            return;
          }

          // Vez do PC - Minimax (MinValue)
          PlayPC('O', a, q);

          if(q.isWinner('O'))
          {
            atraso();
            clear();
            System.out.println("Ganhou o PC - Minimax!");
            return;
          }
          else if(q.isEmpate())
          {
            atraso();
            clear();
            System.out.println("Empate meus caros Jogadores");
            return;
          }
        }
      }
      // Se escolher O joga depois do PC
      else if(s == 2)
      {
        while(true)
        {
          // Vez do PC - Minimax (MaxValue)
          PlayPC('X', a, q);

          if(q.isWinner('X'))
          {
            atraso();
            clear();
            System.out.println("Ganhou o PC - Minimax!");
            return;
          }
          else if(q.isEmpate())
          {
            atraso();
            clear();
            System.out.println("Empate meus caros Jogadores");
            return;
          }

          // Sua Vez
          System.out.print("-> Sua Jogada (O): ");
          // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
          while(true)
          {
            coluna = stdin.nextInt();
            System.out.println();
            if(q.isPossivel(coluna))
            {
              q.NovoQuadro(coluna, 'O');
              q.printQuadro();
              break;
            }
            System.out.print("Jogada Inválida!\nTente Novamente\n-> Sua Jogada (O): ");
          }
          System.out.println("Sua Pontuação: " + q.pontuaçaoTotal('O') + "\n"); // Pontuação de O (Se ganhar fica com -512 pontos)
          if(q.isWinner('O'))
          {
            atraso();
            clear();
            System.out.println("Boa, ganhaste!");
            return;
          }
          else if(q.isEmpate())
          {
            atraso();
            clear();
            System.out.println("Empate meus caros Jogadores");
            return;
          }
        }
      }
    }

    // AlphaBeta
    else if(a == 2)
    {
      // Se escolher X joga primeiro
      if(s == 1)
      {
        while(true)
        {
          // Começa o Jogo
          System.out.println("\n\t LET'S PLAY\n");
          q.printQuadro();
          while(true)
          {
            // Sua Vez
            System.out.print("-> Sua Jogada (X): ");
            // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
            while(true)
            {
              coluna = stdin.nextInt();
              System.out.println();
              if(q.isPossivel(coluna))
              {
                q.NovoQuadro(coluna, 'X');
                q.printQuadro();
                break;
              }
              System.out.print("Jogada Inválida!\nTente Novamente\n-> Sua Jogada (X): ");
            }
            System.out.println("Sua Pontuação: " + q.pontuaçaoTotal('X') + "\n"); // Pontuação de X (Se ganhar fica com 512 pontos)
            if(q.isWinner('X'))
            {
              atraso();
              clear();
              System.out.println("Boa, ganhaste!");
              return;
            }
            else if(q.isEmpate())
            {
              atraso();
              clear();
              System.out.println("Empate meus caros Jogadores");
              return;
            }

            // Vez do PC - Alphabeta (AB_MinValue)
            PlayPC('O', a, q);

            if(q.isWinner('O'))
            {
              atraso();
              clear();
              System.out.println("Ganhou o PC - Alphabeta!");
              return;
            }
            else if(q.isEmpate())
            {
              atraso();
              clear();
              System.out.println("Empate meus caros Jogadores");
              return;
            }
          }
        }
      }
      // Se escolher O joga depois do PC
      else if(s == 2)
      {
        while(true)
        {
          // Vez do PC - Alphabeta (AB_MaxValue)
          PlayPC('X', a, q);

          if(q.isWinner('X'))
          {
            atraso();
            clear();
            System.out.println("Ganhou o PC - Alphabeta!");
            return;
          }
          else if(q.isEmpate())
          {
            atraso();
            clear();
            System.out.println("Empate meus caros Jogadores");
            return;
          }

          // Sua Vez
          System.out.print("-> Sua Jogada (O): ");
          // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
          while(true)
          {
            coluna = stdin.nextInt();
            System.out.println();
            if(q.isPossivel(coluna))
            {
              q.NovoQuadro(coluna, 'O');
              q.printQuadro();
              break;
            }
            System.out.print("Jogada Inválida!\nTente Novamente\n-> Sua Jogada (O): ");
          }
          System.out.println("Sua Pontuação: " + q.pontuaçaoTotal('O') + "\n"); // Pontuação de O (Se ganhar fica com -512 pontos)
          if(q.isWinner('O'))
          {
            atraso();
            clear();
            System.out.println("Boa, ganhaste!");
            return;
          }
          else if(q.isEmpate())
          {
            atraso();
            clear();
            System.out.println("Empate meus caros Jogadores");
            return;
          }
        }
      }
    }

    // Voltar para o menu do jogo
    else if(a == 3)
    {
      atraso();
      clear();
      return;
    }
  }

  // Jogar contra um(a) Amigo(a)
  public static void TwoPlayers(int s)
  {
    Scanner stdin = new Scanner(System.in);
    int coluna;

    // Começa o jogo
    System.out.println("\n\t LET'S PLAY\n");
    Quadro q = new Quadro(6,7);
    q.printQuadro();

    // Se escolher X
    if(s == 1)
    {
      while(true)
      {
        // Jogador 1 (Sua Vez)
        System.out.print("······ Sua Vez ······\n\n-> Jogador 1 (X): ");
        // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
        while(true)
        {
          coluna = stdin.nextInt();
          System.out.println();
          if(q.isPossivel(coluna))
          {
            q.NovoQuadro(coluna, 'X');
            q.printQuadro();
            break;
          }
          System.out.print("Jogada Inválida!\nTente Novamente\n-> Jogador 1 (X): ");
        }
        System.out.println("Pontuação do Jogador 1: " + q.pontuaçaoTotal('X') + "\n"); // Pontuação de X (Se ganhar fica com 512 pontos)
        if(q.isWinner('X'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o Jogador 1!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }

        // Jogador 2
        System.out.print("-> Jogador 2 (O): ");
        // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
        while(true)
        {
          coluna = stdin.nextInt();
          System.out.println();
          if(q.isPossivel(coluna))
          {
            q.NovoQuadro(coluna, 'O');
            q.printQuadro();
            break;
          }
          System.out.print("Jogada Inválida!\nTente Novamente\n-> Jogador 2 (O): ");
        }
        System.out.println("Pontuação do Jogador 2: " + q.pontuaçaoTotal('O') + "\n"); // Pontuação de O (Se ganhar fica com -512 pontos)
        if(q.isWinner('O'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o Jogador 2!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }
      }
    }
    // Se escolher O
    else if(s == 2)
    {
      while(true)
      {
        // Jogador 1
        System.out.print("-> Jogador 1 (X): ");
        // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
        while(true)
        {
          coluna = stdin.nextInt();
          System.out.println();
          if(q.isPossivel(coluna))
          {
            q.NovoQuadro(coluna, 'X');
            q.printQuadro();
            break;
          }
          System.out.print("Jogada Inválida!\nTente Novamente\n-> Jogador 1 (X): ");
        }
        System.out.println("Pontuação do Jogador 1: " + q.pontuaçaoTotal('X') + "\n"); // Pontuação de X (Se ganhar fica com 512 pontos)
        if(q.isWinner('X'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o Jogador 1!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }

        // Jogador 2 (Sua Vez)
        System.out.print("······ Sua Vez ······\n\n-> Jogador 2 (O): ");
        // Ciclo que acaba só quando digitar uma coluna válida e que ainda não esteja totalmente preenchida
        while(true)
        {
          coluna = stdin.nextInt();
          System.out.println();
          if(q.isPossivel(coluna))
          {
            q.NovoQuadro(coluna, 'O');
            q.printQuadro();
            break;
          }
          System.out.print("Jogada Inválida!\nTente Novamente\n-> Jogador 2 (O): ");
        }
        System.out.println("Pontuação do Jogador 2: " + q.pontuaçaoTotal('O') + "\n"); // Pontuação de O (Se ganhar fica com -512 pontos)
        if(q.isWinner('O'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o Jogador 2!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }
      }
    }
  }

  // PC contra PC
  public static void TwoPCs()
  {
    Scanner stdin = new Scanner(System.in);
    int a;
    // Escolher o algoritmo que o PC vai usar
    System.out.print("Escolhe o algoritmo com que o PC vai jogar:\n(1) Minimax x Minimax\n(2) Alphabeta x Alphabeta\n(3) Minimax x Alphabeta\n(4) Voltar para o menu do jogo\n-> ");
    while(true)
    {
      a = stdin.nextInt();
      if(a == 1 || a == 2 || a == 3 || a == 4)
      break;
      System.out.print("Digite 1, 2, 3 ou 4: ");
    }

    // Quadro inicial
    Quadro q = new Quadro(6,7);

    // Minimax x Minimax
    if(a == 1)
    {
      while(true)
      {
        // Vez do PC 1 - Minimax (MaxValue)
        PlayPC('X', 1, q);

        if(q.isWinner('X'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o PC 1 - Minimax!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }

        // Vez do PC 2 - Minimax (MinValue)
        PlayPC('O', 1, q);

        if(q.isWinner('O'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o PC 2 - Minimax!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }
      }
    }
    // Alphabeta x Alphabeta
    else if(a == 2)
    {
      while(true)
      {
        // Vez do PC 1 - Alphabeta (AB_MaxValue)
        PlayPC('X', 2, q);

        if(q.isWinner('X'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o PC 1 - Alphabeta!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }

        // Vez do PC 2 - Alphabeta (AB_MinValue)
        PlayPC('O', 2, q);

        if(q.isWinner('O'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o PC 2 - Alphabeta!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }
      }
    }
    // Minimax x Alphabeta
    else if(a == 3)
    {
      while(true)
      {
        // Vez do PC 1 - Minimax (MaxValue)
        PlayPC('X', 1, q);

        if(q.isWinner('X'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o PC 1 - Minimax!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }

        // Vez do PC 2 - Alphabeta (AB_MinValue)
        PlayPC('O', 2, q);

        if(q.isWinner('O'))
        {
          atraso();
          clear();
          System.out.println("Ganhou o PC 2 - Alphabeta!");
          return;
        }
        else if(q.isEmpate())
        {
          atraso();
          clear();
          System.out.println("Empate meus caros Jogadores");
          return;
        }
      }
    }
    // Voltar para o menu do jogo
    else if(a == 4)
    {
      atraso();
      clear();
      return;
    }
  }

  public static void main(String[] args)
  {
    System.out.println("\t _______________________________");
    System.out.println("\t|                               |");
    System.out.println("\t|         SEJA BEM VINDO        |");
    System.out.println("\t|          AO 4 EM LINHA        |");
    System.out.println("\t|_______________________________|");
    System.out.println("\n");

    while(true)
    {
      Scanner stdin = new Scanner(System.in);
      // Escolher se quer jogar contra o PC, 2 jogadores, PC contra PC ou Sair do Jogo
      System.out.print("Escolhe que tipo de jogo pretendes fazer:\n(1) Contra o PC\n(2) Contra um(a) Amigo(a)\n(3) PC contra PC\n(4) Sair do Jogo\n-> ");
      int jog, s;
      while(true)
      {
        jog = stdin.nextInt();
        if(jog == 1 || jog == 2 || jog == 3 || jog == 4)
          break;
        System.out.print("Digite 1, 2, 3 ou 4: ");
      }
      System.out.println();

      // Jogar contra o PC
      if(jog == 1)
      {
        // Escolher o símbolo que quer para jogar
        System.out.print("Escolhe o símbolo com que pretendes jogar:\n(1) X\n(2) O\n(3) Voltar para o menu do jogo\n-> ");
        while(true)
        {
          s = stdin.nextInt();
          if(s == 1 || s == 2 || s == 3)
            break;
          System.out.print("Digite 1, 2 ou 3: ");
        }
        atraso();
        clear();
        if(s == 1 || s == 2)
          ContraPC(s);
        System.out.println();
      }

      // 2 Jogadores
      else if(jog == 2)
      {
        // Escolher o símbolo que quer para jogar
        System.out.print("Escolhe o símbolo com que pretendes jogar:\n(1) X\n(2) O\n(3) Voltar para o menu do jogo\n-> ");
        while(true)
        {
          s = stdin.nextInt();
          if(s == 1 || s == 2 || s == 3)
            break;
          System.out.print("Digite 1, 2 ou 3: ");
        }
        atraso();
        clear();
        if(s == 1 || s == 2)
          TwoPlayers(s);
        System.out.println();
      }

      // PC contra PC
      else if(jog == 3)
      {
        TwoPCs();
        System.out.println();
      }

      // Sair do Jogo
      else if(jog == 4)
      {
        System.out.println("Até à próxima\n");
        break;
      }
    }
  }
}
