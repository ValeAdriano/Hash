import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComparadorDeTabelasHash {
    public static void main(String[] args) {
        int tamanhoTabela = 1000;  // Define o tamanho da tabela hash
        EstruturaTabelaHash tabelaHashFuncao1 = new TabelaHashComFuncaoHash1(tamanhoTabela);
        EstruturaTabelaHash tabelaHashFuncao2 = new TabelaHashComFuncaoHash2(tamanhoTabela);

        // Carrega a lista de nomes do arquivo
        List<String> listaNomes = carregarNomesDoArquivo("female_names.csv");


        // Verificação se os nomes foram carregados corretamente
        System.out.println("Total de nomes carregados: " + listaNomes.size());
        if (listaNomes.isEmpty()) {
            System.out.println("Nenhum nome foi carregado do arquivo. Verifique o arquivo CSV.");
            return;
        }

        // Inserção para a tabela com a primeira função hash e cálculo do tempo
        long tempoInicial1 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao1.inserir(nome);
        }
        long tempoFinal1 = System.nanoTime();
        long tempoInsercao1 = tempoFinal1 - tempoInicial1;

        // Inserção para a tabela com a segunda função hash e cálculo do tempo
        long tempoInicial2 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao2.inserir(nome);
        }
        long tempoFinal2 = System.nanoTime();
        long tempoInsercao2 = tempoFinal2 - tempoInicial2;

        // Exibe o relatório simplificado no console
        System.out.println("Relatório de Comparação de Tabelas Hash:");
        System.out.println("----------------------------------------");

        // Resultados para a Tabela com Função Hash 1
        System.out.println("\nTabela Hash com Função Hash 1:");
        System.out.println("Tempo de Inserção: " + (tempoInsercao1 / 1_000_000) + " ms");
        System.out.println("Total de Colisões: " + tabelaHashFuncao1.getColisoes());
        System.out.println("Distribuição das Chaves:");
        tabelaHashFuncao1.imprimirDistribuicao();

        // Resultados para a Tabela com Função Hash 2
        System.out.println("\nTabela Hash com Função Hash 2:");
        System.out.println("Tempo de Inserção: " + (tempoInsercao2 / 1_000_000) + " ms");
        System.out.println("Total de Colisões: " + tabelaHashFuncao2.getColisoes());
        System.out.println("Distribuição das Chaves:");
        tabelaHashFuncao2.imprimirDistribuicao();
    }

    private static List<String> carregarNomesDoArquivo(String caminhoArquivo) {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {  // Verifica se a linha não está vazia
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
