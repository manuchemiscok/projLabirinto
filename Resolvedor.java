import java.util.*;

/**
 * Resolvedor de labirintos.
 */
public class Resolvedor {

    /**
     * Caractere de entrada.
     */
    public static final String ENTRADA_CARACTERE = "E";

    /**
     * Caractere de espaço livre.
     */
    public static final String LIVRE_CARACTERE = " ";

    /**
     * Caractere de saída.
     */
    public static final String SAIDA_CARACTERE = "S";

    /**
     * Caractere de passo.
     */
    public static final String PASSO_CARACTERE = "*";

    /**
     * Caminho preenchido no labirinto.
     */
    public Stack<Coordenada> caminho = new Stack<>();

    /**
     * Possibilidades.
     */
    public Stack<Queue<Coordenada>> possibilidades = new Stack<>();

    /**
     * Matriz do labirinto.
     */
    public String[][] labirinto;

    /**
     * Erros ao resolver o labirinto.
     */
    List<String> errors = new ArrayList<>();

    /**
     * @param labirinto Matriz do labirinto.
     */
    public Resolvedor(String[][] labirinto) {
        this.labirinto = labirinto;
    }

    /**
     * Encontra coordenada de entrada no labirinto.
     *
     * @return Coordenada de entrada do labirinto.
     */
    public Coordenada encontrarEntrada() {
        for (int linha = 0; linha < this.labirinto.length; linha++) {
            for (int coluna = 0; coluna < this.labirinto[linha].length; coluna++) {
                if (this.labirinto[linha][coluna].equals(ENTRADA_CARACTERE)) {
                    return new Coordenada(linha, coluna);
                }
            }
        }

        return null;
    }

    /**
     * Verifica se uma coordenada existe no labirinto.
     *
     * @param coordenada Coordenada à ser verificada.
     * @return Se a coordenada existe no labirinto.
     */
    public boolean coordenadaExiste(Coordenada coordenada) {
        return (coordenada.getY() >= 0 && coordenada.getY() < this.labirinto.length && coordenada.getX() >= 0 && coordenada.getX() < this.labirinto[coordenada.getY()].length);
    }

    /**
     * Encontrar possiveis posições para andar/sair a partir da atual no labirinto..
     *
     * @param atual Coordenada atual para encontrar possiveis posições.
     * @return Possiveis posições.
     */
    public Queue<Coordenada> encontrarPossiveisPosicoes(Coordenada atual) {
        Coordenada[] posicoes = {
                new Coordenada(atual.getY() - 1, atual.getX()),  // À esquerda.
                new Coordenada(atual.getY() + 1, atual.getX()),  // À direita.
                new Coordenada(atual.getY(), atual.getX() + 1),  // Acima.
                new Coordenada(atual.getY(), atual.getX() - 1),  // Abaixo.
        };

        Queue<Coordenada> possiveisPosicoes = new LinkedList<Coordenada>();

        for (Coordenada posicao : posicoes) {
            if (coordenadaExiste(posicao) && (this.labirinto[posicao.getY()][posicao.getX()].equals(LIVRE_CARACTERE) || this.labirinto[posicao.getY()][posicao.getX()].equals(SAIDA_CARACTERE))) {
                possiveisPosicoes.add(posicao);
            }
        }

        return possiveisPosicoes;
    }

    /**
     * Preencher coordenada no labirinto com um caractere.
     *
     * @param coordenada Coordenada à ser preenchida.
     * @param caractere Caractere para preencher a coordenada.
     */
    protected void preencherCoordenada(Coordenada coordenada, String caractere) {
        if (this.coordenadaExiste(coordenada)) {
            this.labirinto[coordenada.getY()][coordenada.getX()] = caractere;
        }
    }

    /**
     * Resolve o labirinto.
     */
    public void resolver() {
        Coordenada atual = this.encontrarEntrada();

        if (atual == null) {
            this.errors.add("Não foi encontrado nenhuma entrada");
            return;
        }

        do {
            Queue<Coordenada> fila = this.encontrarPossiveisPosicoes(atual);

            // Não encontrou a saída e não tem como andar na posição atual.
            while (fila.isEmpty() && !this.possibilidades.isEmpty() && !this.caminho.isEmpty()) {
                atual = this.caminho.pop();
                this.preencherCoordenada(atual, LIVRE_CARACTERE);
                fila = this.possibilidades.pop();
            }

            atual = fila.poll();

            // Quando a fila continua vazia e não há mais possibilidades e caminho.
            if (atual == null) {
                this.errors.add("Não existe caminho que leva da entrada até a saída");
                return;
            }

            // Encontrou a saída.
            if (this.labirinto[atual.getY()][atual.getX()].equals(SAIDA_CARACTERE)) {
                return;
            }

            this.preencherCoordenada(atual, PASSO_CARACTERE);
            this.caminho.add(atual);
            this.possibilidades.add(fila);
        } while (true);
    }

    /**
     * Representação da classe.
     *
     * @return Matriz do labirinto para uma String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int linha = 0; linha < this.labirinto.length; linha++) {
            for (int coluna = 0; coluna < this.labirinto[linha].length; coluna++) {
                sb.append(this.labirinto[linha][coluna]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
