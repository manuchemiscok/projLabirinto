import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Converte e valida o arquivo de labirinto para matriz.
 */
public class Arquivo {

    /**
     * Nome do arquivo do labirinto.
     */
    public String nomeArquivo;

    /**
     * Pasta com os labirintos.
     */
    public String pasta = "labirintos";

    /**
     * Número de linhas do labirinto.
     */
    public int linhas;

    /**
     * Número de caracteres de cada linha do labirinto.
     */
    public int caracteres;

    /**
     * Matriz do labirinto apos processamento.
     */
    public String[][] labirinto;

    /**
     * Erros ao processar o arquivo de labirinto.
     */
    List<String> errors = new ArrayList<>();

    /**
     *
     * Construtor da classe.
     *
     * @param nomeArquivo
     *    Nome do arquivo na pasta labirintos.
     */
    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    /**
     * Processa o arquivo de labirinto.
     */
    public void processar() {
        String caminho = this.obterCaminho();

        if (caminho == null) {
            return;
        }

        int arquivoLinha = 0;
        int labirintoLinha = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(caminho));
            String str;
            while ((str = in.readLine()) != null) {
                if (arquivoLinha == 0) {
                    try {
                        this.linhas = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        this.errors.add("Quantidade de linhas não informada");
                    }
                }
                else if (arquivoLinha == 1) {
                    try {
                        this.caracteres = Integer.parseInt(str);
                        this.labirinto = new String[this.linhas][this.caracteres];
                    } catch (NumberFormatException e) {
                        this.errors.add("Quantidade de colunas não informada");
                    }
                }
                else {
                    if (!this.errors.isEmpty() && arquivoLinha == 2) {
                        return;
                    }
                    char[] caracteres = str.toCharArray();
                    boolean temErro = false;
                    if ((labirintoLinha + 1) > this.linhas) {
                        this.errors.add("A linha (" + labirintoLinha + ") do labirinto excedeu a quantidade de linhas informada");
                        temErro = true;
                    }
                    if (caracteres.length > this.caracteres) {
                        this.errors.add("A linha (" + labirintoLinha + ") do labirinto excedeu a quantidade de colunas informada");
                        temErro = true;
                    }
                    if (caracteres.length < this.caracteres) {
                        this.errors.add("A linha (" + labirintoLinha + ") do labirinto possui menos colunas do que o informado");
                        temErro = true;
                    }
                    if (!temErro) {
                        int labirintoColuna = 0;
                        for (char c : caracteres) {
                            this.labirinto[labirintoLinha][labirintoColuna] = Character.toString(c);
                            labirintoColuna++;
                        }
                    }
                    labirintoLinha++;
                }
                arquivoLinha++;
            }
            in.close();
        } catch (Exception e) {
            this.errors.add("Não foi possivel ler o arquivo");
        }

        if (labirintoLinha < this.linhas) {
            this.errors.add("O labirinto possui menos linhas do que o informado");
        }
    }

    /**
     * Retorna o caminho absoluto onde está o arquivo.
     *
     * @return Caminho absoluto onde está o arquivo.
     */
    public String obterCaminho() {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resourceUrl = classLoader.getResource(this.pasta + "/" + this.nomeArquivo);

        try {
            return Paths.get(resourceUrl.toURI()).toString();
        } catch (Exception e) {
            this.errors.add("Arquivo não encontrado");
        }

        return null;
    }

}
