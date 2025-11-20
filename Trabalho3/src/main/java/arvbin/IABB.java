package arvbin;

import lista.LSE;

import java.util.function.Predicate;

/**
 * Define as operações básicas da estrutura de dados ÁRVORE BINÁRIA DE BUSCA
 *
 * @param <T> Tipo a ser armazenado na árvore
 */
public interface IABB<T, K> {

    /**
     * Insere o item e na árvore.
     *
     * @param e Item a ser inserido
     */
    public void inserir(T e);

    /**
     * Remove da árvore o item e.
     *
     * @param e Item a ser removido
     */
    public void remover(T e);

    /**
     * Remove da árvore o item cuja chave é key.
     *
     * @param key Chave do item a ser removido
     */
    public void removerChave(K key);

    /**
     * Percorre a ABB "em ordem" e busca o item cuja chave é key
     *
     * @param key Chave do item procurado
     * @return Nó que possui a chave definida; ou null, se não foi encontrado
     */
    T buscar(K key);
    
	/**
	 * Verifica se a árvore contém o item e.
	 * 
	 * @param e Item a ser buscado.
	 * @return Verdadeiro se a árvore contém o elemento e; falso, caso contrário.
	 */
	public boolean contem(T e);

    /**
     * Verifica se a árvore contém o item cuja chave é k.
     *
     * @param key Chave a ser buscada.
     * @return Verdadeiro se a árvore contém o elemento com chave k; falso, caso contrário.
     */
    public boolean contemChave(K key);

    /**
     * Recupera o maior item da árvore
     *
     * @return Maior item da árvore; ou null, se a árvore estiver vazia
     */
    public T maior();

    /**
     * Recupera o menor item da árvore
     * 
     * @return Menor item da árvore; ou null, se a árvore estiver vazia
     */
    public T menor();

	/**
	 * Retorna a quantidade de itens da árvore.
	 * 
	 * @return Quantidade de itens da árvore.
	 */
	public int quantidade();
	
	/**
	 * Verifica se a árvore está vazia.
	 * 
	 * @return Verdadeiro se a árvore está vazia; falso se tem algum elemento.
	 */
	public boolean estaVazia();
	
	/**
	 * Remove todos os itens da árvore
	 */
	public void removeTodos();

    /**
     * Percorre a ABB no percurso "em ordem", chamando o visitante para cada item
     *
     * @param visitante Visitante do nó
     */
    void emOrdem(Visitante<T> visitante);

    /**
     * Percorre a ABB no percurso "em ordem" invertendo a SAE com SAD, chamando o visitante para cada item
     *
     * @param visitante Visitante do nó
     */
    void emOrdemInvertida(Visitante<T> visitante);

	/**
	 * o metodo recebe um
	 * valor inteiro n (n ≥ 0) e um visitante
	 * e deve visitar, da esquerda para a direita, todos os
	 * itens dos nós nível n. Se o nível n não existir, deverá
	 * ser impresso "Nível não existe".
	 */
	void nivel(int n, Visitante<T> visitante);

	/**
	 * o metodo recebe dois itens a e b da árvore
	 * e deve retornar uma lista encadeada de itens com o
	 * menor caminho saindo de a e chegando até b
	 * (a lista deverá estar na ordem que o caminho deve ser seguido).
	 * Se a ou b não existirem, o metodo deverá retornar uma lista vazia.
	 * Se a = b, o metodo deverá retornar uma lista somente com a.
	 */
	LSE<T> menorCaminho(T a, T b);

	/**
	 * o metodo deve retornar uma string com o código do item a
	 * ou nulo, se a não existe. O código é formado por 0s e 1s,
	 * onde 0 significa caminhar para a SAE e 1 representa caminhar para a SAD.
	 */
	String codigo(T a);

	/**
	 * assumindo uma árvore de valores inteiros, o metodo deve
	 * retornar um objeto da classe MaiorSoma com o caminho da raiz até um
	 * nó folha de maior somatório e o valor do somatório. O caminho deve ser
	 * formado pelas letras ‘E’ e ‘D’, indicando o percurso da raiz até a folha.
	 */

	MaiorSoma maxSoma();
}

