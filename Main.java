import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continuar;

        do {
            System.out.println("Digite o nome do arquivo texto que contém a estrutura do labirinto: ");
            String nomeArquivo = scanner.nextLine();

            processar(nomeArquivo);

            System.out.println("\nDeseja processar outro labirinto? y/n");
            continuar = scanner.nextLine();

        } while (continuar.equals("y"));
    }

    /**
     * Processar arquivo de labirinto.
     *
     * @param nomeArquivo Nome do arquivo de labirinto.
     */
    protected static void processar(String nomeArquivo) {
        Arquivo arquivo = new Arquivo(nomeArquivo);
        arquivo.processar();

        // Mostra os erros encontrados durante a leitura do arquivo de labirinto.
        if (!arquivo.errors.isEmpty()) {
            for (String error : arquivo.errors) {
                System.out.println(error);
            }
            return;
        }

        Validador validador = new Validador(arquivo.labirinto);
        validador.validar();

        // Mostra os erros encontrados durante a validação do labirinto.
        if (!validador.errors.isEmpty()) {
            for (String error : validador.errors) {
                System.out.println(error);
            }
            return;
        }

        Resolvedor resolvedor = new Resolvedor(arquivo.labirinto);
        resolvedor.resolver();

        // Mostra os erros encontrados durante a resolução do labirinto.
        if (!resolvedor.errors.isEmpty()) {
            for (String error : resolvedor.errors) {
                System.out.println(error);
            }
            return;
        }

        System.out.println("O caminho foi encontrado");
        System.out.println(resolvedor);

        Stack<Coordenada> inverso = new Stack<>();
        while (!resolvedor.caminho.isEmpty()) {
            inverso.add(resolvedor.caminho.pop());
        }

        System.out.println("O caminho da entrada até a saída será mostrado");

        while (!inverso.isEmpty()) {
            System.out.print(inverso.pop() + " ");
        }
    }

}