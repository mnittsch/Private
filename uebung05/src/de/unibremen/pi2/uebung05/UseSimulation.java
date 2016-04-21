package de.unibremen.pi2.uebung05;

import java.awt.Point;

import de.unibremen.pi2.uebung05.gui.PI2SimulationGUI;

/**
 * Einfacher Simulator, der ein langweiliges Labyrinth der Größe 10 verwendet
 * und Lady von oben links nach unten rechts bewegt. Lady wird dabei abwechselnd
 * ein Feld weiter nach rechts bzw. nach unten gesetzt. Sinn und ZWeck ist die
 * Demonstration der Verwendung der PI2SimulationGUI.
 * 
 * @author K. Hölscher
 * @version 2015-05
 */
public final class UseSimulation implements PI2SimulatorInterface {

   /**
    * Feldkodierung für Ausgänge nach oben und rechts.
    */
   private static final int UR = 3;

   /**
    * Feldkodierung für Ausgänge nach unten und rechts.
    */
   private static final int DR = 6;

   /**
    * Feldkodierung für Ausgänge nach oben, unten und rechts.
    */
   private static final int UDR = 7;

   /**
    * Feldkodierung für Ausgänge nach oben und links.
    */
   private static final int LU = 9;

   /**
    * Feldkodierung für Ausgänge nach oben, rechts und links.
    */
   private static final int ULR = 11;

   /**
    * Feldkodierung für Ausgänge nach unten und links.
    */
   private static final int DL = 12;

   /**
    * Feldkodierung für Ausgänge nach oben, unten und links.
    */
   private static final int UDL = 13;

   /**
    * Feldkodierung für Ausgänge nach unten, links und rechts.
    */
   private static final int DLR = 14;

   /**
    * Feldkodierung für Ausgänge nach oben, unten, links und rechts.
    */
   private static final int UDLR = 15;

   /**
    * Anzahl ausgeführter Schritte.
    */
   private int ladyStep = 0;

   /**
    * Aktuell Koordinaten der Marienkäferdame als Punkt (y = Zeile, x = Spalte).
    */
   private Point ladyCoordinates;

   /**
    * Die Startkoordinaten der Marienkäferdame als Punkt (y = Zeile, x =
    * Spalte). Werden für das Zurücksetzen der Simulation benötigt.
    */
   private Point ladyStartCoordinates;

   /**
    * {@inheritDoc}
    * 
    * <p>
    * Diese Implementierung entspricht nicht der Spezifikation, da die
    * Parameterwerte und auch der interne Zustand nicht geprüft werden!
    * </p>
    * 
    * @author K. Hölscher
    */
   @Override
   public void initSimulation(final int[][] labyrinth, final Point pLadyCoordinates,
         final Point shamrockCoordinates) throws IllegalArgumentException,
         IllegalStateException {
      ladyStartCoordinates = pLadyCoordinates;
      ladyCoordinates = new Point(ladyStartCoordinates);
      // SimulationsGUI erzeugen
      PI2SimulationGUI gui =
            new PI2SimulationGUI(labyrinth, this, ladyCoordinates,
                  shamrockCoordinates);
      // SimulationsGUI anzeigen
      gui.setVisible(true);
   }

   /**
    * {@inheritDoc}
    * 
    * <p>
    * Setzt die Anzahl der ausgeführten Schritte auf null zurück und die
    * Koordinaten der Marienkäferdame auf das Ausgangsfeld.
    * </p>
    * 
    * <p>
    * Diese Implementierung entspricht nicht der Spezifikation, da der interne
    * Zustand nicht geprüft wird!
    * </p>
    * 
    * @author K. Hölscher
    */
   @Override
   public void reset() {
      ladyStep = 0;
      ladyCoordinates.setLocation(ladyStartCoordinates);
   }

   /**
    * {@inheritDoc}
    * 
    * <p>
    * Führt den nächsten Schritt der Simulation aus. Da das hier verwendete
    * Labyrinth ein langweiliges Labyrinth darstellt, bewegt sich Lady in jedem
    * ungeraden Schritt nach rechts und in jedem geraden Schritt nach unten. Da
    * das Labyrinth die Größe 10 hat, können also 9 Schritte nach rechts und 9
    * Schritte nach unten gemacht werden, d. h. ein weiterer Schritt ist nach 18
    * erfolgten Schritten nicht mehr möglich.
    * </p>
    * 
    * <p>
    * Diese Implementierung entspricht nicht der Spezifikation, da der interne
    * Zustand nicht geprüft wird!
    * </p>
    * 
    * 
    * @author K. Hölscher
    */
   @Override
   public boolean step() {
      ladyStep++;
      if (ladyStep > 18) {
         return false;
      }
      int currentRow = ladyCoordinates.y;
      int currentCol = ladyCoordinates.x;

      if (ladyStep % 2 == 0) {
         // "gerader" Schritt, also eine Bewegung nach unten
         currentRow++;
      } else {
         // "ungerader" Schritt, also eine Bewegung nach rechts
         currentCol++;
      }
      ladyCoordinates.setLocation(currentCol, currentRow);
      return true;
   }

   /**
    * {@inheritDoc}
    * 
    * @author K. Hölscher
    */
   @Override
   public Point getLadyCoordinates() {
      return ladyCoordinates;
   }

   /**
    * Erzeugt ein Simulationsobjekt und ein langweiliges Labyrinth der Größe 10.
    * Setzt Lady auf das Feld oben links und das Kleeblatt auf das Feld unten
    * rechts, erzeugt eine SimulationsGUI und zeigt diese an.
    * 
    * @param args
    *           werden ignoriert
    * 
    * @author K. Hölscher
    */
   public static void main(final String[] args) {
      // langweiliges Labyrinth direkt erzeugen
      final int[][] bolaMatrix =
            {
                  { DR, DLR, DLR, DLR, DLR, DLR, DLR, DLR, DLR, DL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UDR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDLR, UDL },
                  { UR, ULR, ULR, ULR, ULR, ULR, ULR, ULR, ULR, LU } };
      // Kleeblattkoordinaten
      final Point shamrockCoordinates =
            new Point(bolaMatrix[bolaMatrix.length - 1].length - 1,
                  bolaMatrix.length - 1);
      // Ladykoordinaten
      final Point ladyCoordinates = new Point(0, 0);

      final UseSimulation laby = new UseSimulation();
      laby.initSimulation(bolaMatrix, ladyCoordinates, shamrockCoordinates);
   }

}
