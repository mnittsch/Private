

/**
 * Schnittstelle für ein Labyrinth, das einen ungerichteten Graphen als
 * unterliegendes Modell verwendet.
 * 
 * <p>
 * Eine konkrete Implementierung muss einen parameterlosen Konstruktor
 * bereitstellen.
 * 
 * Die erzeugten Labyrinthe werden als zweidimensionale Matrizen zurückgegeben.
 * 
 * Die Ausgänge sind dabei durch Zahlen kodiert:
 * </p>
 * 
 * <pre>
 * - Oben:   1
 * - Rechts: 2
 * - Unten:  4
 * - Links:  8
 * </pre>
 * 
 * <p>
 * Durch Addition der Ausgänge in die jeweilige Richtung ergibt eine einzige
 * Zahl, die das Feld des Labyrinthes komplett darstellt. Wenn ein Feld z. B.
 * Ausgänge nach Norden und Westen hat, so ergibt sich die repräsentierende Zahl
 * zu 1+8=9.
 * </p>
 * 
 * @author K. Hölscher
 * @version 2015-05
 */
public interface PI2LabyInterface {

   /**
    * Minimale Anzahl von Zeilen bzw. Spalten des unterliegenden Labyrinthes.
    */
   public static final int MIN_SIZE = 3;

   /**
    * Maximale Anzahl von Zeilen bzw. Spalten des unterliegenden Labyrinthes.
    */
   public static final int MAX_SIZE = 30;

   /**
    * Erzeugt ein quadratisches, langweiliges Labyrinth der gegebenen Größe, d.
    * h. die gegebene Zahl spezifiziert die Anzahl der Zeilen und Spalten des
    * Labyrinthes. Die gegebene Zahl muss größer oder gleich
    * {@link PI2LabyInterface#MIN_SIZE} und kleiner oder gleich
    * {@link PI2LabyInterface#MAX_SIZE} sein.
    * 
    * @param size
    *           die Größe des zu erzeugenden Labyrinthes
    * @return die Felder des Labyrinthes als Matrix. In jedem Eintrag steht eine
    *         Zahl, die die konkreten Ausgänge des Feldes beschreibt (siehe
    *         oben).
    * @throws IllegalArgumentException
    *            falls die gegebene Größe {@link PI2LabyInterface#MIN_SIZE}
    *            unterschreitet oder {@link PI2LabyInterface#MAX_SIZE}
    *            überschreitet
    */
   public int[][] createBoLa(final int size);

   /**
    * Erzeugt ein echtes quadratisches Labyrinth der gegebenen Größe, d. h. die
    * gegebene Zahl spezifiziert die Anzahl der Zeilen und Spalten des
    * Labyrinthes. Die gegebene Zahl muss größer oder gleich
    * {@link PI2LabyInterface#MIN_SIZE} und kleiner oder gleich
    * {@link PI2LabyInterface#MAX_SIZE} sein.
    * 
    * @param size
    *           die Größe des zu erzeugenden Labyrinthes
    * @return die Felder des Labyrinthes als Matrix. In jedem Eintrag steht eine
    *         Zahl, die die konkreten Ausgänge des Feldes beschreibt (siehe
    *         oben).
    * 
    * 
    * @throws IllegalArgumentException
    *            falls die gegebene Größe {@link PI2LabyInterface#MIN_SIZE}
    *            unterschreitet oder {@link PI2LabyInterface#MAX_SIZE}
    *            überschreitet
    */
   public int[][] createLabyrinth(final int size);

}
