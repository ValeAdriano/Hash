import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class EstruturaTabelaHash {
    protected List<LinkedList<String>> tabela;
    protected int tamanho;
    protected int colisoes;

    public EstruturaTabelaHash(int tamanho) {
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
            System.out.println("Posição " + i + ": " + tabela.get(i).size() + " elementos");
        }
    }
}
