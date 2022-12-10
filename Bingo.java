import java.util.Random;
import java.util.Scanner;

public class Bingo {

  

    public static String[] jogadoresQtd (){
        System.out.println("///Bingo///");
        System.out.print("Numero de jogadores: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] jogadores = new String[n];
        System.out.print("Nomes: ");
        jogadores = input.next().split("-");
        return jogadores;
    }

    public static int[][] jogadoresCartela (String[] jogadores){
        int[][] tamanhoCartela = new int[jogadores.length][5];
        int[] cartela = new int[5];
        String[] escolherNumeros = new String[5];
        for(int i = 0; i < jogadores.length; i++){
            System.out.printf("%s, Cartela manual ou aleatória?\n", jogadores[i]);
            System.out.println("Digite M ou A");
            Scanner input = new Scanner(System.in);
            String escolha = input.next();

            if(escolha.equals("M")){
                System.out.println("Digite os 5 números da cartela com vírgula");
                escolherNumeros = input.next().split(",");
                for(int j = 0; j < 5; j++){
                    cartela[j] = Integer.parseInt(escolherNumeros[j]);
                    tamanhoCartela[i][j] = cartela[j];
                }
            }

            else{
                int[] aux = cartelaInicio();
                for(int j = 0; j < aux.length; j++){
                    tamanhoCartela[i][j] = aux[j];
                }
            }
        }
        return tamanhoCartela;
    }

    public static void mostraCartelas (String[] jogadores, int[][] cartelas){

        for(int i = 0; i < jogadores.length; i++){
            System.out.printf("Jogador %d: %s ->", i+1, jogadores[i]);
            for(int j = 0; j < 5; j++){
                System.out.printf(" %d ",cartelas[i][j]);
            }
            System.out.println(" ");
        }
    }

    public static int[] cartelaInicio(){
        int[] valores = new int[5];
        Random random = new Random();

        int i = 0;
        while(i < valores.length){
            valores[i] = random.nextInt(60);
            boolean x = false;
            for(int j = 0; j < i; j++){
                if(valores[i] == valores[j]){
                    x = true;
                    break;
                }
            }
            if(!x){
                i++;
            }
        }
        return valores;
    }

    public static void sorteio(String[] jogadores, int[][] cartelas){
        Scanner input = new Scanner(System.in);
        String escolha;
        int[][] cartelaZerada = zerar(jogadores);
        System.out.print("Sorteio manual ou aleatório? digite M ou A: ");
        escolha = input.next();
        System.out.println(" ");
        if(escolha.equals("M")){
            sorteio(jogadores, cartelas, input, cartelaZerada, escolha);
        }
        else{
            sorteio(jogadores, cartelas, input, cartelaZerada, escolha);
        }
    }

    public static int[][] zerar(String[] jogadores){
        int[][] aux = new int[jogadores.length][5];
        for(int i = 0; i < jogadores.length; i++){
            for(int j = 0; j < 5; j++){
                aux[i][j] = 0;
            }
        }
        return aux;
    }

    public static void sorteio(String[] jogadores, int[][] jogadorSorteio, Scanner input, int[][] cartelaZerada, String escolha){
        boolean flag = false;
        String[] escolherNumeros = new String[5];
        int[] cartelaSorteada = new int[5];
        int[][] acertados = cartelaZerada;
        int rodada = 1;
        int ganhador = 0;
        int[][] numAcertos = new int[jogadores.length][1];
        while(flag == false){

            if(escolha.equals("M")){
                System.out.print("Digite os 5 números separados por vírgula:");
                escolherNumeros = input.next().split(",");

                for(int j = 0; j < 5; j++){
                    cartelaSorteada[j] = Integer.parseInt(escolherNumeros[j]);
                    int valoresQuantidade = 0;
                    valoresQuantidade++;
                }
            }
            else if(escolha.equals("A")){
                cartelaSorteada = cartelaInicio();
                int valoresQuantidade = 0;
                valoresQuantidade += 5;
            }

            for(int i = 0; i < jogadores.length; i++){                     
                for(int k = 0; k < 5; k++){                                
                    for(int l = 0; l < 5; l++){                           
                        if(jogadorSorteio[i][k] == cartelaSorteada[l]){
                            acertados[i][k] = 1;
                            numAcertos[i][0]++;
                        }
                    }
                }

                if(numAcertos[i][0] == 5){
                    flag = true;
                    ganhador = i;
                }
            }


            System.out.printf("Rodada: %d\n", rodada);
            System.out.printf("Cartela sorteada:");
            for(int i = 0; i < 5; i++){
                System.out.printf(" %d ", cartelaSorteada[i]);
            }

            System.out.println(" ");

          
    }

    }

  public static void main(String[] args) {

        String[] jogadores = jogadoresQtd();
        int[][] cartelas = jogadoresCartela(jogadores);
        mostraCartelas(jogadores, cartelas);
        sorteio(jogadores, cartelas);
    }
}