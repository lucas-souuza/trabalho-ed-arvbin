package arvbin;

public interface Visitante<T> {
    /**
     * Esse método é chamado pelos métodos de
     * percurso para que um item seja
     * visitado (processado)
     *
     * @param item Ítem a ser visitado
     */
    void visita(T item);
}
