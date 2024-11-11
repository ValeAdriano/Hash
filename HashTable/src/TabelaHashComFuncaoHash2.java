public class TabelaHashComFuncaoHash2 extends EstruturaTabelaHash {
    public TabelaHashComFuncaoHash2(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int calcularIndiceHash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = (hash * 17 + chave.charAt(i)) % tamanho;
        }
        return hash;
    }
}