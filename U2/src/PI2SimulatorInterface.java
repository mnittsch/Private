

import java.awt.Point;

/**
 * Definiert die Schnittstelle für den Simulator zur Benutzung mit der
 * PI2SimulationGUI.
 * 
 * @author K. Hölscher
 * @version 2015-05
 */
public interface PI2SimulatorInterface {

   /**
    * Initialisiert die Simulation mit dem übergebenen Labyrinth (als Matrix aus
    * ganzen Zahlen, die die Kodierung der Ausgänge der Labyrinthfelder (siehe
    * {@link PI2LabyInterface}) enthalten), den gegebenen Koordinaten für die
    * Marienkäferdame und den gegebenen Koordinaten für das Kleeblatt.
    * 
    * @param labyrinth
    *           das Labyrinth als Matrix aus ganzen Zahlen
    * @param ladyCoordinates
    *           die Koordinaten der Marienkäferdame (als Punkt mit x = Spalte, y
    *           = Zeile)
    * @param shamrockCoordinates
    *           die Koordinaten des Kleeblattes (als Punkt mit x = Spalte, y =
    *           Zeile)
    * @throws IllegalArgumentException
    *            falls mindestens einer der Parameterwerte {@code null} ist oder
    *            falls das Labyrinth nicht quadratisch ist oder falls die Anzahl
    *            der Zeilen bzw. Spalten des Labyrinthes
    *            {@link PI2LabyInterface#MIN_SIZE} unterschreitet oder
    *            {@link PI2LabyInterface#MAX_SIZE}überschreitet oder die
    *            gegebenen Koordinaten außerhalb des Labyrinthes liegen
    * @throws IllegalStateException
    *            falls die Simulation bereits initialisiert wurde, d. h. diese
    *            Methode wurde bereits erfolgreich aufgerufen
    */
   public void initSimulation(final int[][] labyrinth,
         final Point ladyCoordinates, final Point shamrockCoordinates);

   /**
    * Führt den nächsten Schritt der Simulation aus (falls es einen solchen
    * Schritt gibt) und gibt {@code true} zurück. Falls es keinen Schritt mehr
    * gibt, wird {@code false} zurückgegeben.
    * 
    * @return {@code true} falls ein Schritt ausgeführt werden konnte, sonst
    *         {@code false}
    * @throws IllegalStateException
    *            falls die Methode
    *            {@link PI2SimulatorInterface#initSimulation(int[][], Point, Point)}
    *            noch nicht aufgerufen wurde
    */
   public boolean step();

   /**
    * Setzt die Simulation auf den Ursprungszustand zurück.
    * 
    * @throws IllegalStateException
    *            falls die Methode
    *            {@link PI2SimulatorInterface#initSimulation(int[][], Point, Point)}
    *            noch nicht aufgerufen wurde
    */
   public void reset();

   /**
    * Gibt die aktuelle Zeile und Spalte der Marienkäferdame zurück (als Punkt
    * mit y = Zeile, x = Spalte).
    * 
    * @return die aktuellen Koordinaten der Marienkäferdame oder {@code null}
    *         falls es keine aktuellen Koordinaten gibt
    */
   public Point getLadyCoordinates();

}
