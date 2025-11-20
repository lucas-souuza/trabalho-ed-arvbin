package arvbin;

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
}
