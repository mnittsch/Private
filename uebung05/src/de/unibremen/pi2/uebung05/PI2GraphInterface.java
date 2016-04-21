package de.unibremen.pi2.uebung05;

/**
 * Schnittstelle für einen ungerichteten Graphen, dessen Knoten natürliche
 * Zahlen von 0 bis AnzahlKnoten-1 sind. Knoten können hinzugefügt, aber nicht
 * entfernt werden. Kanten verbinden hier je zwei verschiedene Knoten (d. h.
 * Schleifen sind nicht erlaubt) und haben eine natürliche Zahl (einschl. Null)
 * als Gewicht. Kanten werden gerichtet eingefügt, in dem ein Quell- und ein
 * Zielknoten angegeben wird. Der Zustand des Graphen kann über diverse Methoden
 * abgefragt werden. Es kann abgefragt werden, ob der Graph leer ist (d. h.
 * weder Knoten noch Kanten enthält). Es kann die Anzahl der Knoten und die
 * Anzahl der Kanten abgefragt werden und ob zwei gegebene Knoten durch eine
 * Kante verbunden sind. Ebenso ist es möglich, dass Gewicht der Kante zwischen
 * zwei Knoten direkt zu erfragen. Der Gesamtzustand des Graphen lässt sich als
 * zweidimensionale Adjazenzmatrix ermitteln, in der die Kantengewichte zwischen
 * den Knoten oder {@code -1} an den entsprechenden Indizes eingetragen sind.
 * 
 * @author K. Hölscher
 * @version 2015-05
 */
public interface PI2GraphInterface {

   /**
    * Fügt einen neuen Knoten in diesen Graphen ein. Da Knoten in diesem Graphen
    * natürliche Zahlen (einschl. Null) sind und keine Knoten entfernt werden
    * können, ist der Knoten die nächste natürliche Zahl. Diese Zahl wird
    * zurückgegeben.
    * 
    * @return die natürliche Zahl, die diesem Knoten entspricht
    */
   public int insertNode();

   /**
    * Fügt eine ungerichtete Kante mit dem gegebenen Gewicht zwischen den
    * gegebenen Knoten in diesen Grahpen ein. Die Knoten müssen verschieden sein
    * und bereits in diesem Graphen vorhanden sein und es darf keine Kante
    * zwischen ihnen existieren. Das Gewicht darf eine beliebige positive Zahl
    * einschließlich Null sein.
    * 
    * @param src
    *           ein Knoten in diesem Graph, der durch eine Kante mit einem
    *           anderen Knoten verbunden werden soll
    * @param target
    *           ein Knoten in diesem Graph, der durch eine Kante mit einem
    *           anderen Knoten verbunden werden soll
    * @param weight
    *           das Gewicht der einzufügenden Kante
    * @throws IllegalArgumentException
    *            falls mind. einer der Knoten nicht Teil dieses Graphen ist, die
    *            Knoten identisch sind oder das Gewicht eine negative Zahl ist
    * @throws IllegalStateException
    *            falls es bereits eine Kante zwischen den beiden gegebenen
    *            Knoten gibt
    */
   public void insertEdge(final int src, final int target, final int weight);

   /**
    * Gibt genau dann {@code true} zurück, falls es eine Kante zwischen den
    * gegebenen Knoten gibt.
    * 
    * @param src
    *           der eine Knoten
    * @param target
    *           der andere Knoten
    * @return {@code true} falls es eine Kante zwischen den beiden Knoten gibt,
    *         {@code false} sonst
    */
   public boolean isConnected(final int src, final int target);

   /**
    * Erzeugt einen minimal aufspannenden Baum aus diesem Graphen mit dem
    * gegebenen Knoten als Startknoten. Der gegebene Startknoten muss dazu Teil
    * dieses Graphen sein. Der Baum wird als ungerichteter Graph zurückgegeben.
    * 
    * @param startNode
    *           der Startknoten für den minimal aufspannenden Baum
    * @return einen neuen Graphen, der ein minimal aufspannenden Baum zu diesem
    *         Graphen ist
    * @throws IllegalArgumentException
    *            falls der gegebene Startknoten nicht Teil dieses Graphen ist
    * @throws IllegalStateException
    *            falls der Graph nicht zusammenhängend ist, d. h. isolierte
    *            Knoten enthält
    */
   public PI2GraphInterface createMinimumSpanningTree(final int startNode);

   /**
    * Gibt genau dann {@code true} zurück, falls dieser Graph leer ist, d. h.
    * weder Knoten noch Kanten enthält.
    * 
    * @return {@code true} falls der Graph leer ist, {@code false} sonst
    * 
    */
   public boolean isEmpty();

   /**
    * Gibt die Anzahl der Knoten dieses Graphen zurück.
    * 
    * @return die Anzahl der Knoten dieses Graphen
    */
   public int getNumberOfNodes();

   /**
    * Gibt die Anzahl der ungerichteten Kanten dieses Graphen zurück.
    * 
    * @return die Anzahl der ungerichteten Kanten dieses Graphen
    */
   public int getNumberOfEdges();

   /**
    * Gibt das Gewicht der Kante zurück, die die beiden gegebenen Knoten
    * miteinander verbindet.
    * 
    * @param src
    *           der eine Knoten dieses Graphen
    * @param target
    *           der andere Knoten dieses Graphen
    * @return das Gewicht der Kante, die die beiden gegebenen Knoten miteinander
    *         verbindet
    * @throws IllegalArgumentException
    *            falls mind. einer der gegebenen Knoten nicht Teil dieses
    *            Graphen ist oder falls es keine Kante zwischen den beiden
    *            gegebenen Knoten gibt
    */
   public int getWeight(final int src, final int target);

   /**
    * Gibt die Adjazenzmatrix (als zweidimensionales Array) zu diesem Graphen
    * zurück. Die Indizes der Zeilen und Spalten bilden die Knoten und die
    * Einträge der Matrix bilden hier die Kantengewichte bzw. {@code -1} falls
    * es keine Kante zwischen den Knoten gibt. Falls der Graph leer ist, hat die
    * Matrix weder Zeilen noch Spalten, d. h. das resultierende Array ist leer.
    * 
    * @return die Adjazenzmatrix dieses Graphen als zweidimensionales Array
    */
   public int[][] getAdjacencyMatrix();

}