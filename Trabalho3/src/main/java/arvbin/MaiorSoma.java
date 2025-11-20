package arvbin;

public class MaiorSoma {
    public final String caminho;
    public final int valor;

    public MaiorSoma(String caminho, int valor) {
        this.caminho = caminho;
        this.valor = valor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MaiorSoma{");
        sb.append("caminho='").append(caminho).append('\'');
        sb.append(", valor=").append(valor);
        sb.append('}');
        return sb.toString();
    }
}
