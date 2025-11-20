package arvbin;

import lista.ILista;
import lista.LSE;

import java.util.Comparator;
import java.util.function.Function;

public class AVL<T, K> implements IABB<T, K> {
    private No raiz;
    private int quantidade;
    private final Comparator<K> comparador;
    private final Function<T, K> recuperaChave;

    public AVL() {
        this.comparador = (o1, o2) -> { return ((Comparable<K>) o1).compareTo(o2); };
        this.recuperaChave = (o) -> (K) o;
    }

    public AVL(Comparator<K> comparador, Function<T, K> recuperaChave) {
        this.comparador = comparador;
        this.recuperaChave = recuperaChave;
    }

    private class No {
        private T item;
        private int h;
        private No esq;
        private No dir;

        public No (T item) {
            this.item = item;
        }
    }

    @Override
    public void inserir(T e) {
        raiz = inserir(raiz, e, recuperaChave.apply(e));
    }

    @Override
    public void remover(T e) {
        removerChave(recuperaChave.apply(e));
    }

    @Override
    public void removerChave(K key) {
        raiz = removerChave(raiz, key);
    }

    @Override
    public T buscar(K key) {
        No aux = raiz;

        while (aux != null) {
            // Compara a key do nó que está sendo procurado com a key do nó atual (aux)
            int c = comparador.compare(key, recuperaChave.apply(aux.item));

            if (c == 0)
                // key = aux.key -> achou
                break;
            else if (c < 0)
                // key < aux.key -> busca na esquerda
                aux = aux.esq;
            else
                // key > aux.key -> busca na direita
                aux = aux.dir;
        }

        return aux == null ? null : aux.item;
    }

    @Override
    public boolean contem(T e) {
        return buscar(recuperaChave.apply(e)) != null;
    }

    @Override
    public boolean contemChave(K key) {
        return buscar(key) != null;
    }

    @Override
    public T maior() {
        return maior(raiz);
    }

    @Override
    public T menor() {
        return menor(raiz);
    }

    @Override
    public int quantidade() {
        return quantidade;
    }

    @Override
    public boolean estaVazia() {
        return raiz != null;
    }

    @Override
    public void removeTodos() {
        raiz = null;
        quantidade = 0;
    }

    @Override
    public void emOrdem(Visitante<T> visitante) {
        emOrdem(raiz, visitante);
    }

    @Override
    public void emOrdemInvertida(Visitante<T> visitante) {
        emOrdemInvertida(raiz, visitante);
    }

    public void nivel(int n, Visitante<T> visitante){
        //se a arvore estiver vazia || se o nivel nao existe || ""
        if (raiz == null || n < 0 || n > raiz.h) {
            System.out.println("Nivel nao existe");
            return;
        }

        visitarNivelComAltura(raiz, n, 0, visitante);
    }

    private void visitarNivelComAltura(No r, int nivelDesejado, int nivelAtual, Visitante<T> visitante) {
        //se chegar num no vazio ou se ja passou do nível desejado, para a busca
        if (r == null || nivelAtual > nivelDesejado){
            return;
        }

        //vai visitando todos os niveis da arvore e compara com o desejado
        if (nivelAtual == nivelDesejado) {
            visitante.visita(r.item);
            return;
        }

        // visita primeiro a esquerda,depois pra direita
        visitarNivelComAltura(r.esq, nivelDesejado, nivelAtual + 1, visitante);
        visitarNivelComAltura(r.dir, nivelDesejado, nivelAtual + 1, visitante);
    }

    public LSE<T> menorCaminho(T a, T b){

        LSE<T> resultado = new LSE<>();
        //se nao existirem os itens na arvore, lista nula
        if (!contem(a) || !contem(b))
            return resultado;

        //comparar as keys
        K keyA = recuperaChave.apply(a);
        K keyB = recuperaChave.apply(b);
        if (comparador.compare(keyA, keyB) == 0){
            resultado.inserirFim(a);
            return resultado;
        }

        //caminho ate a
        LSE<T> caminhoA = new LSE<>();
        buscarCaminho(raiz, keyA, caminhoA);

        System.out.println(caminhoA);

        //caminho ate b
        LSE<T> caminhoB = new LSE<>();
        buscarCaminho(raiz, keyB, caminhoB);

        System.out.println(caminhoB);

        T lca = encontrarLCA(caminhoA, caminhoB);

        criaCaminho(caminhoA, caminhoB, lca, resultado);

        return resultado;

    }

    public String codigo(T a){

        if (!contem(a))
            return null;

        K keyA = recuperaChave.apply(a);
        String codigo = "";

        codigo = buscarCodigo(raiz, keyA);
        return codigo;
    }

    public MaiorSoma maxSoma(){

        return maxSomaRecursivo(raiz);
    }

    /**
     *
     * @param r raiz atual
     * @param key chave do item T
     * @param caminho caminho ate T que sera atualizado ao longo das chamdas
     */
    private void buscarCaminho(No r, K key, LSE<T> caminho){

        if (r == null)
            return;

        caminho.inserirFim(r.item);

        int cmp = comparador.compare(key, recuperaChave.apply(r.item));
        if (cmp == 0)
            return;

        else if (cmp < 0)
            buscarCaminho(r.esq, key, caminho);

        else
            buscarCaminho(r.dir, key, caminho);
    }

    /**
     * encontrar o menor LCA, para que seja possivel definir um caminho de
     * A ate B
     * @param camA caminho feito para chegar ate o No do item A
     * @param camB caminho feito para chegar ate o No do item B
     * @return retorna o Lowest Common Ancestor (menor ancestral comum)
     */
    private T encontrarLCA(LSE<T> camA, LSE<T> camB) {

        //procurar ate a lista de menor qtd
        int n = Math.min(camA.quantidade(), camB.quantidade());

        T noA, noB;
        K keyA,keyB;

        T lca = null;

        for (int i = 0; i<n; i++){
            noA = camA.getItem(i);
            noB = camB.getItem(i);

            keyA = recuperaChave.apply(noA);
            keyB = recuperaChave.apply(noB);

            int cmp = comparador.compare(keyA, keyB);

            //diferentes
            if(cmp !=0)
                break;

            lca = noA;
        }
        return lca;
    }

    private void criaCaminho(LSE<T> caminhoA, LSE<T> caminhoB, T lca, LSE<T> resultado) {
        K keyLCA = recuperaChave.apply(lca);

        //for para chegar ate o LCA
        int indiceLCA = -1;
        for (int i = 0; i < caminhoA.quantidade(); i++) {
            T item = caminhoA.getItem(i);
            if (comparador.compare(recuperaChave.apply(item), keyLCA) == 0) {
                indiceLCA = i;
                break;
            }
        }

        // Adiciona do caminho A ate lca (invertido) sem add lca
        for (int i = caminhoA.quantidade() - 1; i > indiceLCA; i--) {
            resultado.inserirFim(caminhoA.getItem(i));
        }


        //Adiciona de LCA ate B (so comeca a add quando achar o lca)
        boolean passouLCA = false;

        for (int i = 0; i < caminhoB.quantidade(); i++) {

            T item = caminhoB.getItem(i);
            //quando chegar no LCA, passou vira true e comeca a add na lista resultante
            if (comparador.compare(recuperaChave.apply(item), keyLCA) == 0) {
                passouLCA = true;
            }
            if (passouLCA){
                resultado.inserirFim(item);
            }
        }
    }

    /**
     * o metodo eh muito semelhante com o de buscar caminho
     * mas a cada comparacao (ida para a esquerda e direita)
     * ele adiciona um 0 ou 1 caso va para o caminho esq ou dir
     * (metodo recursivo)
     * @param r no atual
     * @param key key do item <T>e para comparacao
     * @return String com o codigo para o caminho do item <T>e
     */
    private String buscarCodigo(No r, K key){

        if (r == null)
            return "";

        int cmp = comparador.compare(key, recuperaChave.apply(r.item));
        if (cmp == 0)
            return "";

        else if (cmp < 0) {
            return "0" + buscarCodigo(r.esq, key);
        }

        else {
            return "1" + buscarCodigo(r.dir, key);
        }
    }
    private MaiorSoma maxSomaRecursivo(No r) {
        //no nulo
        if (r == null) {
            return new MaiorSoma("", 0);
        }

        //se o item do no atual nao for um inteiro
        if (!(r.item instanceof Integer)) {
            throw new UnsupportedOperationException("O metodo maxSoma aceita apenas arvores de inteiros.");
        }
        int valorNoAtual = (Integer) r.item;

        //folha (sem filhos) retorna o valor da folha
        if (r.esq == null && r.dir == null) {
            return new MaiorSoma("", valorNoAtual);
        }

        // calcula a maior soma a esquerda e a direita
        MaiorSoma esquerda = maxSomaRecursivo(r.esq);
        MaiorSoma direita = maxSomaRecursivo(r.dir);

        // Decide qual dos lados deu a maior soma
        if (esquerda.valor >= direita.valor) {
            return new MaiorSoma("E" + esquerda.caminho, valorNoAtual + esquerda.valor);
        }
        else {
            return new MaiorSoma("D" + direita.caminho, valorNoAtual + direita.valor);
        }
    }
    private No inserir(No r, T e, K key) {
        if (r == null) {
            // Encontrou a posição
            r = new No(e);
            quantidade++;
        }
        else {
            // Compara a key do nó que está sendo inserido com a key do nó atual (r)
            int c = comparador.compare(key, recuperaChave.apply(r.item));

            if (c < 0) {
                // key < r.key -> insere à esquerda
                r.esq = inserir(r.esq, e, key);
                // Recalcula a altura do nó e verifica o balanceamento (LL ou LR)
                r.h = altura(r);
                if (fb(r) > 1) {
                    if (comparador.compare(key, recuperaChave.apply(r.esq.item)) < 0)
                        r = rotateLL(r);
                    else
                        r = rotateLR(r);
                }
            }
            else if (c > 0) {
                // key > r.key -> insere à direita
                r.dir = inserir(r.dir, e, key);
                // Recalcula a altura do nó e verifica o balanceamento (RR ou RL)
                r.h = altura(r);
                if (fb(r) > 1) {
                    if (comparador.compare(key, recuperaChave.apply(r.dir.item)) > 0)
                        r = rotateRR(r);
                    else
                        r = rotateRL(r);
                }
            }
        }

        return r;
    }

    private No removerChave(No r, K key) {
        if (r == null)
            // key não encontrada -> retorna nulo
            return null;
        else {
            int c = comparador.compare(key, recuperaChave.apply(r.item));

            if (c < 0) {
                // key < r.key -> remove à esquerda
                r.esq = removerChave(r.esq, key);
                // Recalcula a altura do nó e verifica o balanceamento (RR ou RL)
                r.h = altura(r);
                if (fb(r) > 1) {
                    if (altura(r.dir.dir) > altura(r.dir.esq))
                        r = rotateRR(r);
                    else
                        r = rotateRL(r);
                }
            }
            else if (c > 0) {
                // key > r.key -> remove à direita
                r.dir = removerChave(r.dir, key);
                // Recalcula a altura do nó e verifica o balanceamento (LL ou LR)
                r.h = altura(r);
                if (fb(r) > 1) {
                    if (altura(r.esq.esq) > altura(r.esq.dir))
                        r = rotateLL(r);
                    else
                        r = rotateLR(r);
                }
            }
            else {
                // key = r.key -> remove r
                // 1o caso: r é folha
                if (r.esq == null && r.dir == null) {
                    r = null;
                    quantidade--;
                } // 2o caso A: r com apenas um filho à esquerda
                else if (r.esq != null && r.dir == null) {
                    r = r.esq;
                    quantidade--;
                } // 2o caso B: r com apenas um filho à direita
                else if (r.esq == null && r.dir != null) {
                    r = r.dir;
                    quantidade--;
                } // 3o caso: r tem 2 filhos
                else {
                    // Descobre o maior nó da SAE OU o menor nó da SAD (tanto faz!)
                    T m = maior(r.esq);
                    r = removerChave(r, recuperaChave.apply(m));
                    r.item = m;
                }
            }
        }

        return r;
    }

    private T maior(No r) {
        if (r == null)
            return null;

        while (r.dir != null)
            r = r.dir;

        return r.item;
    }

    private T menor(No r) {
        if (r == null)
            return null;

        while (r.esq != null)
            r = r.esq;

        return r.item;
    }

    private void emOrdem(No r, Visitante<T> visitante) {
        if (r == null)
            return;

        emOrdem(r.esq, visitante);
        visitante.visita(r.item);
        emOrdem(r.dir, visitante);
    }

    private void emOrdemInvertida(No r, Visitante<T> visitante) {
        if (r == null)
            return;

        emOrdem(r.dir, visitante);
        visitante.visita(r.item);
        emOrdem(r.esq, visitante);
    }

    private int altura(No r) {
        if (r == null)
            return -1;

        int he = r.esq == null ? -1 : r.esq.h;
        int hd = r.dir == null ? -1 : r.dir.h;

        return Math.max(he, hd) + 1;
    }

    private int fb(No r) {
        int he = r.esq == null ? -1 : r.esq.h;
        int hd = r.dir == null ? -1 : r.dir.h;

        return Math.abs(he - hd);
    }

    private No rotateLL(No r) {
        No no = r.esq;
        r.esq = no.dir;
        no.dir = r;

        r.h = altura(r);
        no.h = altura(no);

        return no;
    }

    private No rotateRR(No r) {
        No no = r.dir;
        r.dir = no.esq;
        no.esq = r;

        r.h = altura(r);
        no.h = altura(no);

        return no;
    }

    private No rotateLR(No r) {
        r.esq = rotateRR(r.esq);
        r = rotateLL(r);

        return r;
    }

    private No rotateRL(No r) {
        r.dir = rotateLL(r.dir);
        r = rotateRR(r);

        return r;
    }

    /**
     * Retorna uma string com os itens da árvore em formato de indentação.
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        imprime(raiz, 0, buffer);

        return buffer.toString();
    }

    private void imprime(No r, int nivel, StringBuilder buffer) {
        if (r == null)
            return;

        buffer.append("...".repeat(nivel));

        buffer.append(r.item.toString());
        buffer.append("\n");

        nivel++;

        imprime(r.esq, nivel, buffer);
        imprime(r.dir, nivel, buffer);
    }
}
