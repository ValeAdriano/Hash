import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComparadorDeTabelasHash {
    public static void main(String[] args) {
        int tamanhoTabela = 1000;  // Define o tamanho da tabela hash

        // Criacao das tabelas hash com diferentes funcoes de hash
        EstruturaTabelaHash tabelaHashFuncao1 = new TabelaHashComFuncaoHash1(tamanhoTabela);
        EstruturaTabelaHash tabelaHashFuncao2 = new TabelaHashComFuncaoHash2(tamanhoTabela);

        // Carrega a lista de nomes do arquivo
        List<String> listaNomes = carregarNomesDoArquivo("HashTable\\\\src\\\\female_names.csv");

        // Verificacao se os nomes foram carregados corretamente
        System.out.println("Total de nomes carregados: " + listaNomes.size());
        if (listaNomes.isEmpty()) {
            System.out.println("Nenhum nome foi carregado do arquivo. Verifique o arquivo CSV.");
            return;
        }

        // Insercao para a tabela com a primeira funcao hash e calculo do tempo
        long tempoInicial1 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao1.inserir(nome);
        }
        long tempoFinal1 = System.nanoTime();
        long tempoInsercao1 = (tempoFinal1 - tempoInicial1) / 1_000_000; // Converte para milissegundos
        System.out.println("Tempo de insercao para a tabela com a primeira funcao hash: " + tempoInsercao1 + " milissegundos");

        // Insercao para a tabela com a segunda funcao hash e calculo do tempo
        long tempoInicial2 = System.nanoTime();
        for (String nome : listaNomes) {
            tabelaHashFuncao2.inserir(nome);
        }
        long tempoFinal2 = System.nanoTime();
        long tempoInsercao2 = (tempoFinal2 - tempoInicial2) / 1_000_000; // Converte para milissegundos
        System.out.println("Tempo de insercao para a tabela com a segunda funcao hash: " + tempoInsercao2 + " milissegundos");

        // Relatorio final
        System.out.println("Relatorio Final:");
        System.out.println("Total de nomes carregados: " + listaNomes.size());
        System.out.println("Tempo de insercao para a tabela com a primeira funcao hash: " + tempoInsercao1 + " milissegundos");
        System.out.println("Tempo de insercao para a tabela com a segunda funcao hash: " + tempoInsercao2 + " milissegundos");
    }

    // Metodo para carregar nomes do arquivo CSV
    private static List<String> carregarNomesDoArquivo(String nomeArquivo) {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
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
