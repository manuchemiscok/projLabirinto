import java.util.ArrayList;
import java.util.List;

/**
 * Responsável pela validação do labirinto.
 */
public class Validador {

    /**
     * Matriz do labirinto.
     */
    public String[][] labirinto;

    /**
     * Erros ao validar o labirinto.
     */
    List<String> errors = new ArrayList<>();

    /**
     * Se o labirinto tem entrada.
     */
    private boolean temEntrada = false;

    /**
     * Se o labirinto tem saída.
     */
    private boolean temSaida = false;

    /**
     * Construtor da classe.
     *
     * @param labirinto Matriz do labirinto.
     */
    public Validador(String[][] labirinto) {
        this.labirinto = labirinto;
    }

    /**
     * Valida o labirinto.
     */
    public void validar() {
        this.percorrerLabirinto();
    }

    /**
     * Percorrer o labirinto para executar as validações.
     */
    protected void percorrerLabirinto() {
        for (int linha = 0; linha < this.labirinto.length; linha++) {
            boolean primeiraLinha = (linha == 0);
            boolean ultimaLinha = (linha > 0  && linha == (this.labirinto.length - 1));
            for (int coluna = 0; coluna < this.labirinto[linha].length; coluna++) {
                if (primeiraLinha || ultimaLinha || coluna == 0 || coluna == (this.labirinto[linha].length - 1)) {
                    this.validarBorda(linha, coluna, this.labirinto[linha][coluna]);
                    continue;
                }
                this.validarMiolo(linha, coluna, this.labirinto[linha][coluna]);
            }
        }

        if (!this.temEntrada) {
            this.errors.add("O labirinto não possui entrada");
        }

        if (!this.temSaida) {
            this.errors.add("O labirinto não possui saída");
        }
    }

    /**
     * Valida a borda do labirinto.
     *
     * @param linha Linha do labirinto.
     * @param coluna Coluna do labirinto.
     * @param caractere Caractere da borda à ser validado.
     */
    protected void validarBorda(int linha, int coluna, String caractere) {
        if (caractere.equals("#")) {
            return;
        }

        if (caractere.equals("E")) {
            if (this.temEntrada) {
                this.errors.add(this.montarMensagemDeErroComCoordenadas(linha, coluna,"O labirinto possui mais de 1 entrada"));
            }
            this.temEntrada = true;
            return;
        }

        if (caractere.equals("S")) {
            if (this.temSaida) {
                this.errors.add(this.montarMensagemDeErroComCoordenadas(linha, coluna,"O labirinto possui mais de 1 saída"));
            }
            this.temSaida = true;
            return;
        }

        this.errors.add(this.montarMensagemDeErroComCoordenadas(linha, coluna,"O borda do labirinto possui caractere inválido"));
    }

    /**
     * Valida o miolo do labirinto.
     *
     * @param linha Linha do labirinto.
     * @param coluna Coluna do labirinto.
     * @param caractere Caractere do miolo à ser validado.
     */
    protected void validarMiolo(int linha, int coluna, String caractere) {
        if (caractere.equals("#") || caractere.equals(" ")) {
            return;
        }

        this.errors.add(this.montarMensagemDeErroComCoordenadas(linha, coluna, "O caminho ou parede do labirinto possui caractere inválido"));
    }

    /**
     * Montar mensagem de erro com coordenadas.
     *
     * @param linha Linha do labirinto.
     * @param coluna Coluna do labirinto.
     * @param mensagemErro Mensagem de erro.
     *
     * @return Mensagem de erro com coordenadas.
     */
    protected String montarMensagemDeErroComCoordenadas(int linha, int coluna, String mensagemErro) {
        return mensagemErro + " (" + linha + "," + coluna + ")";
    }

}
