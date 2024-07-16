/**
 * Representação de uma coordenada.
 */
public class Coordenada {

    /**
     * Linha.
     */
    private int y;

    /**
     * Coluna.
     */
    private int x;

    /**
     * Construtor da classe.
     *
     * @param y Linha.
     * @param x Coluna.
     */
    public Coordenada(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * Obtém linha.
     *
     * @return Coordenada da linha.
     */
    public int getY() {
        return y;
    }

    /**
     * Setar linha.
     *
     * @param y Linha.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtém coluna.
     *
     * @return coluna.
     */
    public int getX() {
        return x;
    }

    /**
     * Setar coluna.
     *
     * @param x Coluna.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Representação da classe.
     *
     * @return Coordenada para uma String.
     */
    @Override
    public String toString() {
        return "(" + y + "," + x + ")";
    }

}