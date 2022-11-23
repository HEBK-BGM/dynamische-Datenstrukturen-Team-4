package de.hebk.model.list;

import de.hebk.model.node.Node;


/**
 * Objekte der Klasse List verwalten beliebig viele, linear angeordnete Objekte. Auf
 * höchstens ein Listenobjekt, aktuelles Objekt genannt, kann jeweils zugegriffen
 * werden. Wenn eine Liste leer ist, vollständig durchlaufen wurde oder das aktuelle
 * Objekt am Ende der Liste gelöscht wurde, gibt es kein aktuelles Objekt. Das erste
 * oder das letzte Objekt einer Liste können durch einen Auftrag zum aktuellen Objekt
 * gemacht werden. Außerdem kann das dem aktuellen Objekt folgende Listenobjekt
 * zum neuen aktuellen Objekt werden.
 * Das aktuelle Objekt kann gelesen, verändert oder gelöscht werden. Außerdem kann
 * vor dem aktuellen Objekt ein Listenobjekt eingefügt werden
 * @param <T>
 */
public class List<T> {
    private Node<T> first;
    private Node<T> current;


    /**
     * Die Anfrage liefert den Wert true, wenn die Liste keine Objekte
     * enthält, sonst liefert sie den Wert false.
     * @return
     */
    public boolean isEmpty(){
        return first == null;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn es ein aktuelles Objekt
     * gibt, sonst liefert sie den Wert false.
     * @return
     */
    public  boolean hasAccess(){
        if(current != null){
            return true;
        }
        return false;
    }

    /**
     * Falls die Liste nicht leer ist, es ein aktuelles Objekt gibt und
     * dieses nicht das letzte Objekt der Liste ist, wird das dem
     * aktuellen Objekt in der Liste folgende Objekt zum aktuellen
     * Objekt, andernfalls gibt es nach Ausführung des Auftrags kein
     * aktuelles Objekt.
     */
    public void next(){
        if(isEmpty()) {
            return;
        }
        if (hasAccess()){
            current = first;
            return;
        }
        if (current.getNext()!=null) {
            current = current.getNext();

        }
    }

    /**
     * Falls die Liste nicht leer ist, wird das erste Objekt der Liste
     * aktuelles Objekt. Ist die Liste leer, geschieht nichts
     */
    public void toFirst(){
        if (isEmpty()) {
            return;
        }
        current = first;
    }


    /**
     * Falls die Liste nicht leer ist, wird das letzte Objekt der Liste
     * aktuelles Objekt. Ist die Liste leer, geschieht nichts.
     */
    public void toLast(){
        if(isEmpty()) {
            return;
        }
        Node<T> tmp =first;
        while(tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        current = tmp;
    }

    /**
     * Falls es ein aktuelles Objekt gibt, wird das aktuelle Objekt
     * zurückgegeben, andernfalls gibt die Anfrage den Wert null
     * zurück.
     * @return
     */
    public T getObject(){
        if(isEmpty()){
            return null;
        }
        return current.getContext();
    }

    /**
     * Falls es ein aktuelles Objekt gibt und pObject ungleich null ist,
     * wird das aktuelle Objekt durch pObject ersetzt.
     */
    public void setObject(T pContext){
        if (pContext== null || isEmpty() || hasAccess()) {
            return;
        }
        if (first.equals(current)) {
            Node<T> tmp = first ;

            first = new Node<T>(pContext);
            first.setNext(tmp.getNext());
            current = first;
            return;
        }
        Node<T> tmp = first;
        while (tmp.getNext() != null) {
            if(tmp.getNext()==(current)) {
                Node<T> newNode = new Node<T>(pContext);
                newNode.setNext(tmp.getNext().getNext());
                tmp.setNext(new Node<T>(pContext));
                break;
            }
            tmp = tmp.getNext();
        }
    }

    /**
     * Ein neues Objekt pObject wird am Ende der Liste angefügt.
     * Das aktuelle Objekt bleibt unverändert. Wenn die Liste leer ist,
     * wird das Objekt pObject in die Liste eingefügt und es gibt
     * weiterhin kein aktuelles Objekt.
     * Falls pObject gleich null ist, bleibt die Liste unverändert.
     */
    public void append(T pContext){
        if (pContext ==null) {
            return;
        }
        if(isEmpty()){
            first = new Node<T>(pContext);
            return;
        }

        Node<T> tmp = first;
        while(tmp.getNext() !=null){
            tmp = tmp .getNext();
        }
        tmp.setNext(new Node<T>(pContext));
    }

    /**
     * Falls es ein aktuelles Objekt gibt, wird ein neues Objekt vor
     * dem aktuellen Objekt in die Liste eingefügt. Das aktuelle Objekt
     * bleibt unverändert. Falls die Liste leer ist und es somit kein
     * aktuelles Objekt gibt, wird pObject in die Liste eingefügt und es
     * gibt weiterhin kein aktuelles Objekt. Falls es kein aktuelles
     * Objekt gibt und die Liste nicht leer ist oder pObject gleich null
     * ist, bleibt die Liste unverändert.
     */
    public void insert(T pContext){
        if(pContext ==null){
            return;
        }
        if(isEmpty()) {
            first =new Node<T>(pContext);
        }
    }


    /**
     * Die Liste pList wird an die Liste angehängt. Das aktuelle Objekt
     * bleibt unverändert. Falls pList null oder eine leere Liste ist,
     * bleibt die Liste unverändert
     */
    public void concat(List<T> pList){
        if(pList == null  || pList.isEmpty())  {
            return;
        }
        if (isEmpty()) {
            first= pList.first;
        }
        Node<T> tmp =first;
        while(tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        tmp.setNext(pList.first);
    }

    /**
     * Falls es ein aktuelles Objekt gibt, wird das aktuelle Objekt
     * gelöscht und das Objekt hinter dem gelöschten Objekt wird
     * zum aktuellen Objekt. Wird das Objekt, das am Ende der Liste
     * steht, gelöscht, gibt es kein aktuelles Objekt mehr. Wenn die
     * Liste leer ist oder es kein aktuelles Objekt gibt, bleibt die Liste
     * unverändert.
     */
    public void remove(){
        if (isEmpty() || !hasAccess()) {
            return;
        } else if(first == current) {
            first = first.getNext();
            return;
        }

        Node<T> tmp=first;
        while(tmp.getNext() != null) {
            if(tmp == current) {
                if(current.getNext()!=null) {
                    tmp.setNext(current.getNext());
                }
                else {
                    tmp.setNext(null);
                }
                return;
            }
            tmp=tmp.getNext();
        }
    }
    public String toString() {
        String ret = "[";

        Node<T> tmp = first;
        while (tmp != null) {
            ret += tmp.getContext();

            if (tmp.getNext() != null) {
                ret += ";";
            }

            tmp = tmp.getNext();
        }

        ret += "]";

        return ret;
    }

}
