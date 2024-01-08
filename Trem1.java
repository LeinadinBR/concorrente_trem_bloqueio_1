/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 14 de Abril de 2021
  Alteracao: 18 de Abril de 2021
  Nome.....: Trem1
  Funcao...: Uma classe que herda da classe 'Trem' e representa o trem que vai da esquerda para a direita
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Trem1 extends Trem {
  private Pista pista;      //objeto referencia para 'Pista'
  private Animacao animacao;    //objeto de 'Animacao'

  /* *********************
  * Metodo: Trem1
  * Funcao: Construtor de Trem1
  * Parametros: int x, int y, Pista pista
  ********************* */
  public Trem1(int x, int y, Pista pista) {
    super(x, y);
    this.velocidade = 1;
    this.pista = pista;

    animacao = new Animacao("res/Trem1.png", "res/Trem1_2.png", "res/Trem1_3.png", this);
    this.setImagem(animacao.getImagem());
  }

  /* *********************
  * Metodo: run
  * Funcao: realiza o algoritmo que o trem1 tem que fazer
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  @Override
  public void run(){
    variavelTravamento();
  }

  public void protocoloBandeiras(){

  }

  public void variavelTravamento(){
    pista.setDistancia1(0);  //coloca a distancia do trem1 para 0
    while (true){
      if (!animacao.isRunning())
        animacao.ligarTickAnimacao();  //inicia a animacao de troca de sprites do trem1

      if (x>1080){  //caso ele atinja o limite volta para o inicio
        x = 0;
        pista.setDistancia1(0);
      }

      if (velocidade>0){  //movimenta o trem de acordo com a velocidade
        this.x += 1;
        pista.setDistancia1(pista.getDistancia1()+1);
        esperar();
      }
      else {  //caso a velocidade seja 0, ele apenas espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico1 ja tenha um trem dentro, ele espera no final do trecho 1 (+90 eh para consertar o espacamento)
      while (pista.getDistancia1()+90 == pista.getTrecho1().getFim() && pista.isPontoCritico1()){ 
        esperar();
      } 

      //caso o trem1 esteja no ponto critico1
      if(pista.getTrecho2().estaDentro(pista.getDistancia1()+90)){
        pista.setPontoCritico1(true);
        pista.setTremNoCritico1("1");
      } //quando o trem1 sai do ponto critico1
      else if (pista.getDistancia1() == pista.getTrecho3().getInicio()){
        pista.setPontoCritico1(false);
        pista.setTremNoCritico1("");
      }

      //caso o ponto critico2 ja tenha um trem dentro, ele espera no final do trecho3
      while (pista.getDistancia1()+90 == pista.getTrecho3().getFim() && pista.isPontoCritico2()){ 
        esperar();
      } 

      //caso o trem1 esteja no ponto critico2
      if(pista.getTrecho4().estaDentro(pista.getDistancia1()+90)){
        pista.setPontoCritico2(true);
        pista.setTremNoCrititco2("1");
      } //quando o trem1 sai do ponto critico2
      else if (pista.getDistancia1() == pista.getTrecho5().getInicio()){
        pista.setPontoCritico2(false);
        pista.setTremNoCrititco2("");
      }
    }
  }

  public void estritaAlternacia(){

  }

  public void solucaoPeterson(){

  }
}
