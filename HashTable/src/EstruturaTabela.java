import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class EstruturaTabela {
    protected List<LinkedList<String>> tabela;
    protected int tamanho;
    protected int colisoes;

    public EstruturaTabela(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ArrayList<>(tamanho);
        for (int i = 0; i < tamanho; i++) {
            tabela.add(new LinkedList<>());
        }
        this.colisoes = 0;
    }

    protected abstract int calcularIndiceHash(String chave);

    public void inserir(String chave) {
        int indice = calcularIndiceHash(chave);
        LinkedList<String> lista = tabela.get(indice);

        if (!lista.isEmpty()) {
            colisoes++;
        }

        lista.add(chave);
    }

    public boolean buscar(String chave) {
        int indice = calcularIndiceHash(chave);
        LinkedList<String> lista = tabela.get(indice);
        return lista.contains(chave);
    }

    public int getColisoes() {
        return colisoes;
    }

    public void imprimirDistribuicao() {
        for (int i = 0; i < tabela.size(); i++) {
            System.out.println("Posiçao " + i + ": " + tabela.get(i).size() + " elementos");
        }
    }

    public void imprimirColisoesPorPosicao() {
        System.out.println("Distribuiçao de colisoes por posiçao:");
        for (int i = 0; i < tabela.size(); i++) {
            int colisoesPosicao = tabela.get(i).size() > 1 ? tabela.get(i).size() - 1 : 0;
            System.out.println("Posiçao " + i + ": " + colisoesPosicao + " colisoes");
        }
    }
}