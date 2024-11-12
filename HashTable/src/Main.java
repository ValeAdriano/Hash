import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int tamanhoTabela = 1000;

        EstruturaTabela tabelaHashFuncao1 = new Funcao1(tamanhoTabela);
        EstruturaTabela tabelaHashFuncao2 = new Funcao2(tamanhoTabela);

        List<String> listaNomes = carregarNomesDoArquivo("HashTable\\\\src\\\\female_names.csv");

        if (listaNomes.isEmpty()) {
            System.out.println("Nenhum nome foi carregado do arquivo. Verifique o arquivo CSV.");
            return;
        }

        long tempoInicial1 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao1.inserir(nome);
        }
        long tempoFinal1 = System.nanoTime();
        long tempoInsercao1 = tempoFinal1 - tempoInicial1;

        long tempoInicial2 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao2.inserir(nome);
        }
        long tempoFinal2 = System.nanoTime();
        long tempoInsercao2 = tempoFinal2 - tempoInicial2;

        long tempoInicialBusca1 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao1.buscar(nome);
        }
        long tempoFinalBusca1 = System.nanoTime();
        long tempoBusca1 = tempoFinalBusca1 - tempoInicialBusca1;

        long tempoInicialBusca2 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao2.buscar(nome);
        }
        long tempoFinalBusca2 = System.nanoTime();
        long tempoBusca2 = tempoFinalBusca2 - tempoInicialBusca2;

        System.out.println("\n==================== RELATORIO FINAL ====================");
        System.out.println("Total de nomes carregados: " + listaNomes.size());
        System.out.println("\n--- Funçao Hash 1 ---");
        tabelaHashFuncao1.imprimirDistribuicao();
        tabelaHashFuncao1.imprimirColisoesPorPosicao();
        System.out.println("Numero de colisoes: " + tabelaHashFuncao1.getColisoes());
        System.out.println("Tempo de inserçao: " + tempoInsercao1 + " nanosegundos");
        System.out.println("Tempo de busca: " + tempoBusca1 + " nanosegundos");
        System.out.println("\n--- Funçao Hash 2 ---");
        tabelaHashFuncao2.imprimirDistribuicao();
        tabelaHashFuncao2.imprimirColisoesPorPosicao();
        System.out.println("Numero de colisoes: " + tabelaHashFuncao2.getColisoes());
        System.out.println("Tempo de inserçao: " + tempoInsercao2 + " nanosegundos");
        System.out.println("Tempo de busca: " + tempoBusca2 + " nanosegundos");
        System.out.println("=========================================================");
    }

    private static List<String> carregarNomesDoArquivo(String nomeArquivo) {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    nomes.add(linha.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return nomes;
    }
}