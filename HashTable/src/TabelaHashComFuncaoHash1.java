public class TabelaHashComFuncaoHash1 extends EstruturaTabelaHash {
    public TabelaHashComFuncaoHash1(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int calcularIndiceHash(String chave) {
        int hash = 0;
        for (char c : chave.toCharArray()) {
            hash = (31 * hash + c) % tamanho;
        }
        return hash;
    }
}
