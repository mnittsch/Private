

import java.awt.Point;
import java.util.Random;
import java.util.Stack;



/**
 * Diese Klasse hilft der kleinen Lady aus dem Labyrinth (hoffentlich vor dem
 * Verhungern) und visualisiert die Wegfindung mithilfe der GUI.
 *
 * @author Stephan Hopfmüller
 */
public class PI2Simulator implements PI2SimulatorInterface {
	/**
	 * Code für die Richtung nach oben
	 */
	public static final int		U	= 1;

	/**
	 * Code für die Richtung nach rechts
	 */
	public static final int		R	= 2;

	/**
	 * Code für die Richtung nach unten
	 */
	public static final int		D	= 4;

	/**
	 * Code für die Richtung nach links
	 */
	public static final int		L	= 8;

	/**
	 * Aktuell Koordinaten der Marienkäferdame als Punkt (y = Zeile, x =
	 * Spalte).
	 */
	private Point				ladyCoordinates;

	/**
	 * Die Startkoordinaten der Marienkäferdame als Punkt (y = Zeile, x =
	 * Spalte). Werden für das Zurücksetzen der Simulation benötigt.
	 */
	private Point				ladyStartCoordinates;

	/**
	 * Referenz auf das bei der Initialisierung übergebene Labirynth.
	 */
	private int[][]				labyrinth;

	/**
	 * In diesem Stack weden die bereits besuchten Felder gespeichert (inklusive
	 * der zuletzt versuchten Richtung).
	 */
	private Stack<LadyState>	stack;

	/**
	 * {@inheritDoc}
	 *
	 * @author Stephan Hopfmüller
	 */
	@Override
	public void initSimulation(final int[][] pLabyrinth, final Point pLadyCoordinates, final Point pShamrockCoordinates)
			throws IllegalArgumentException, IllegalStateException {
		if (ladyCoordinates != null) {
			throw new IllegalStateException("The simulation has already been initialized!");
		}
		if ((pLabyrinth == null) || (pLadyCoordinates == null) || (pShamrockCoordinates == null)) {
			throw new IllegalArgumentException("null-References are not allowed!");
		}
		for (final int[] element : pLabyrinth) {
			if (element.length != pLabyrinth.length) {
				throw new IllegalArgumentException("The given labyrinth is not quadratic!");
			}
		}
		if (pLabyrinth.length < 3) {
			throw new IllegalArgumentException("The given labyrinth is too small!");
		}
		if (pLabyrinth.length > 30) {
			throw new IllegalArgumentException("The given labyrinth is too big!");
		}
		if ((pLadyCoordinates.getX() < 0) || (pLadyCoordinates.getX() >= pLabyrinth.length) || (pLadyCoordinates.getY() < 0)
				|| (pLadyCoordinates.getY() >= pLabyrinth.length)) {
			throw new IllegalArgumentException("The coordinates of the lady are not within the labyrinth!");
		}
		if ((pShamrockCoordinates.getX() < 0) || (pShamrockCoordinates.getX() >= pLabyrinth.length) || (pShamrockCoordinates.getY() < 0)
				|| (pShamrockCoordinates.getY() >= pLabyrinth.length)) {
			throw new IllegalArgumentException("The coordinates of the shamrock are not within the labyrinth!");
		}

		ladyStartCoordinates = pLadyCoordinates;
		ladyCoordinates = new Point(ladyStartCoordinates);
		stack = new Stack<LadyState>();
		labyrinth = pLabyrinth;
		// SimulationsGUI erzeugen
		//final PI2SimulationGUI gui = new PI2SimulationGUI(pLabyrinth, this, ladyCoordinates, pShamrockCoordinates);
		// SimulationsGUI anzeigen
	//	gui.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * Setzt die Koordinaten der Marienkäferdame auf das Ausgangsfeld und löscht
	 * den Stack, der die bereits besuchten Felder beinhaltet.
	 * </p>
	 *
	 * @author Stephan Hopfmüller
	 */
	@Override
	public void reset() {
		if (ladyCoordinates == null) {
			throw new IllegalStateException("The simulation hasn't been initialized!");
		}

		ladyCoordinates.setLocation(ladyStartCoordinates);
		stack.clear();
	}

	/**
	 * Container für den Zustand auf den bereits besuchten Feldern.
	 *
	 * @author Stephan Hopfmüller
	 */
	private static class LadyState {
		/**
		 * X-Koordinate von Lady
		 */
		int	x;

		/**
		 * Y-Koordinate von Lady
		 */
		int	y;

		/**
		 * Nächste zu testende Richtung.
		 * Hinweis: Hier gilt folgendes:
		 * 0 = oben
		 * 1 = rechts
		 * 2 = unten
		 * 3 = links
		 */
		int	nextDirection;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalStateException
	 *             Das Labyrinth hat keine Lösung
	 * @author Stephan Hopfmüller
	 */
	@Override
	public boolean step() {
		if (ladyCoordinates == null) {
			throw new IllegalStateException("The simulation hasn't been initialized!");
		}

		final LadyState state;
		if (!stack.isEmpty()) {
			state = stack.peek();
		} else {
			state = new LadyState();
			stack.push(state);
		}

		if ((labyrinth.length == (state.x + 1)) && (state.x == state.y)) {
			// beim Kleeblatt angekommen
			return false;
		}

		// an der vorherigen Stelle um eins weiter gedreht die Suche fortsetzen
		// (sofern Lady bereits dort war)
		for (int i = state.nextDirection; i < 4; i++) {
			if (((labyrinth[state.y][state.x] & (1 << i)) != 0)) {
				// Weg weiter gefunden
				final LadyState nextState = new LadyState();
				nextState.x = state.x;
				nextState.y = state.y;
				switch (1 << i) {
					case U:
						nextState.y--;
						break;
					case R:
						nextState.x++;
						break;
					case D:
						nextState.y++;
						break;
					default: // case L: // für ECLEMMA, sonst switch=gelb
						nextState.x--;
				}
				// wenn der Stack min. 2 Elemente besitzt, so wird das
				// vorherige Element abgefragt:
				final LadyState lastState = (stack.size() >= 2) ? stack.get(stack.size() - 2) : null;
				// hier wird überprüft, ob versucht wird, an die vorherige
				// Position zurück zu gehen:
				if ((lastState == null) || !((nextState.x == lastState.x) && (nextState.y == lastState.y))) {
					state.nextDirection = i + 1;
					stack.push(nextState);
					ladyCoordinates.setLocation(nextState.x, nextState.y);
					return true;
				}
			}
		}
		// Sackgasse
		stack.pop(); // einen Schritt zurück
		if (stack.isEmpty()) {
			throw new IllegalStateException("This labyrinth has no solution!");
		}
		final LadyState oldState = stack.peek();
		ladyCoordinates.setLocation(oldState.x, oldState.y);
		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Stephan Hopfmüller
	 */
	@Override
	public Point getLadyCoordinates() {
		return ladyCoordinates;
	}

	/**
	 * Mit dieser Methode wird Lady in einem dynamisch generierten Labyrinth
	 * ausgesetzt und versucht mithilfe eines Pathfinding-Algorithmus zu
	 * entkommen.
	 *
	 * @param args
	 *            werden ignoriert
	 *
	 * @author Stephan Hopfmüller
	 */
	public static void main(final String[] args) {
		final int[][] labyMatrix = new PI2Laby().createLabyrinth(new Random().nextInt((30 - 3) + 1) + 3);
		// Kleeblattkoordinaten
		final Point shamrockCoordinates = new Point(labyMatrix[labyMatrix.length - 1].length - 1, labyMatrix.length - 1);
		// Ladykoordinaten
		final Point ladyCoordinates = new Point(0, 0);

		final PI2Simulator laby = new PI2Simulator();
		laby.initSimulation(labyMatrix, ladyCoordinates, shamrockCoordinates);
	}
}
