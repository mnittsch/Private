/**
 *
 */
package de.unibremen.pi2.uebung05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse testet die Simulationsklasse PI2Simulation
 *
 * @author Stephan Hopfmüller
 */
public class PI2SimulatorTest {
	/**
	 * Code für die Richtung nach oben
	 */
	public static final int			U							= 1;

	/**
	 * Code für die Richtung nach rechts
	 */
	public static final int			R							= 2;

	/**
	 * Code für die Richtung nach unten
	 */
	public static final int			D							= 4;

	/**
	 * Code für die Richtung nach links
	 */
	public static final int			L							= 8;

	private PI2Simulator			simulator;

	// langweiliges 10 x 10 Labyrinth
	private static final int[][]	bolaMatrix					= new int[10][10];

	private final int[][]			simpleLabyMatrix			= { { D + R, L + R, L + D }, { U + D, R + D, L + U }, { U, U + R, L } };
	private final int[][]			deadEndLabyMatrix			= { { D + R, L + R, L }, { U + R + D, L + R, L + D }, { U + R, L, U } };
	private final int[][]			multipleOptionsLabyMatrix	= { { D, D, 0 }, { U + R + D, U + D + L, 0 }, { U + R, U + L + R, L } };
	private final int[][]			circleLabyMatrix			= { { D + R, L + D, 0 }, { U + R, U + L, 0 }, { 0, 0, 0 } };
	private final int[][]			impossibleLabyMatrix		= { { R, L + R, L }, { 0, 0, 0 }, { 0, 0, 0 } };

	/**
	 * Richtet die Test-Umgebung ein.
	 *
	 * @throws java.lang.Exception
	 *             Sollte normalerweise nicht geworfen werden
	 *
	 * @author Stephan Hopfmüller
	 */
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < bolaMatrix.length; i++) {
			for (int j = 0; j < bolaMatrix[0].length; j++) {
				bolaMatrix[i][j] = U + R + D + L;
				if (i == 0) {
					bolaMatrix[i][j] -= U;
				}
				if (i == (bolaMatrix.length - 1)) {
					bolaMatrix[i][j] -= D;
				}
				if (j == 0) {
					bolaMatrix[i][j] -= L;
				}
				if (j == (bolaMatrix[0].length - 1)) {
					bolaMatrix[i][j] -= R;
				}
			}
		}
	}

	/**
	 * Testet die Initialisierung mit einem "langweiligen Labyrinth" der Größe
	 * 10 (also 10*10, da quadratisch).
	 *
	 * Testmethode für
	 * {@link de.unibremen.pi2.uebung05.PI2Simulator#initSimulation}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulation() {
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(0, 0));
			final Point point = simulator.getLadyCoordinates();
			assertEquals(point.getX(), 0, 0.0001);
			assertEquals(point.getY(), 0, 0.0001);
		} catch (final Exception e) {
			fail("Should not have thrown an Exception!");
		}
	}

	/**
	 * Testet die doppelte Initialisierung.
	 *
	 * Testmethode für
	 * {@link de.unibremen.pi2.uebung05.PI2Simulator#initSimulation}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulationDouble() {
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(0, 0));
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(0, 0));
			fail("Should have thrown an IllegalStateException");
		} catch (final IllegalStateException e) {
		}
	}

	/**
	 * Testet <code>null</code> als Argumente für die Initialisierung.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulationNull() {
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(null, new Point(0, 0), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, null, new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), null);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
	}

	/**
	 * Testet <code>null</code> als Argumente für die Initialisierung.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulationNonQuadratic() {
		simulator = new PI2Simulator();
		final int[][] nonRectangularMat = { { 1, 2, 3 }, { 4 }, { 5 } };
		try {
			simulator.initSimulation(nonRectangularMat, new Point(0, 0), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {

		}
	}

	/**
	 * Testet die Initialisierung mit einem zu kleinem Labyrinth.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulationToSmall() {
		simulator = new PI2Simulator();
		final int[][] toSmallMat = { { 1, 2 }, { 3, 4 } };
		try {
			simulator.initSimulation(toSmallMat, new Point(0, 0), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
	}

	/**
	 * Testet die Initialisierung mit einem zu kleinem Labyrinth.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulationToLarge() {
		simulator = new PI2Simulator();
		final int[][] toLargeMat = new int[100][100];
		try {
			simulator.initSimulation(toLargeMat, new Point(0, 0), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
	}

	/**
	 * Testet die Initialisierung mit Lady außerhalb des Labyrinths.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulationLadyOutOfBounds() {
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, -3), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(-1, 0), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 50), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(50, 0), new Point(0, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
	}

	/**
	 * Testet die Initialisierung mit dem Kleeblatt außerhalb des Labyrinths.
	 *
	 * Testmethode für
	 * {@link de.unibremen.pi2.uebung05.PI2Simulator#initSimulation}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testInitSimulationShamrockOutOfBounds() {
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(-1, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(0, -3));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(50, 0));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(0, 50));
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
	}

	/**
	 * Testet das Zurücksetzen
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#reset}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testReset() {
		simulator = new PI2Simulator();
		try {
			simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(0, 0));
			simulator.step();
			simulator.reset();
			final Point point = simulator.getLadyCoordinates();
			assertEquals(point.getX(), 0, 0.0001);
			assertEquals(point.getY(), 0, 0.0001);
			while (simulator.step()) {
			}
			assertEquals(point.getX(), bolaMatrix.length, 0.0001);
			assertEquals(point.getY(), bolaMatrix[0].length, 0.0001);
		} catch (final Exception e) {
			fail("Should not have thrown an Exception!");
		}
	}

	/**
	 * Testet das Zurücksetzen
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#reset}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testResetWithoutInit() {
		simulator = new PI2Simulator();
		try {
			simulator.reset();
			fail("Should have thrown an IllegalStateException");
		} catch (final IllegalStateException e) {
		}
	}

	/**
	 * Testet step() mit einem "BoLa"
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#step}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testStep() {
		simulator = new PI2Simulator();
		simulator.initSimulation(bolaMatrix, new Point(0, 0), new Point(bolaMatrix.length - 1, bolaMatrix[0].length - 1));
		while (simulator.step()) {
			;
		}
		final Point point = simulator.getLadyCoordinates();
		assertEquals(point.getX(), bolaMatrix.length - 1, 0.0001);
		assertEquals(point.getY(), bolaMatrix[0].length - 1, 0.0001);
	}

	/**
	 * Testet die step-Methode ohne eine vorherige Initialisierung der
	 * Simulation
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#step()}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testStepWithoutInit() {
		simulator = new PI2Simulator();
		try {
			simulator.step();
			fail("Should have thrown an IllegalStateException");
		} catch (final IllegalStateException e) {
		}
	}

	/**
	 * Testet step() mit einem simplen Labyrinth
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#step}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testStepSimpleLaby() {
		simulator = new PI2Simulator();
		simulator.initSimulation(simpleLabyMatrix, new Point(0, 0), new Point(simpleLabyMatrix.length - 1, simpleLabyMatrix[0].length - 1));
		while (simulator.step()) {
			;
		}
		final Point point = simulator.getLadyCoordinates();
		assertEquals(point.getX(), simpleLabyMatrix.length - 1, 0.0001);
		assertEquals(point.getY(), simpleLabyMatrix[0].length - 1, 0.0001);
	}

	/**
	 * Testet step() mit einem Labyrinth, dass eine Sackgasse enthält.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#step}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testStepDeadEndLaby() {
		simulator = new PI2Simulator();
		simulator.initSimulation(deadEndLabyMatrix, new Point(0, 0),
				new Point(deadEndLabyMatrix.length - 1, simpleLabyMatrix[0].length - 1));
		while (simulator.step()) {
			;
		}
		final Point point = simulator.getLadyCoordinates();
		assertEquals(point.getX(), deadEndLabyMatrix.length - 1, 0.0001);
		assertEquals(point.getY(), deadEndLabyMatrix[0].length - 1, 0.0001);
	}

	/**
	 * Testet step() mit einem Labyrinth, dass mehrere Optionen und eine
	 * Sackgasse nach oben enthält.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#step}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testStepMultipleOptions() {
		simulator = new PI2Simulator();
		simulator.initSimulation(multipleOptionsLabyMatrix, new Point(0, 0), new Point(multipleOptionsLabyMatrix.length - 1,
				multipleOptionsLabyMatrix[0].length - 1));
		while (simulator.step()) {
			;
		}
		final Point point = simulator.getLadyCoordinates();
		assertEquals(point.getX(), multipleOptionsLabyMatrix.length - 1, 0.0001);
		assertEquals(point.getY(), multipleOptionsLabyMatrix[0].length - 1, 0.0001);
	}

	/**
	 * Testet step() mit einem Labyrinth, dass nur aus einem Kreis besteht.
	 * Da eine Überprüfung auf diesen Sonderfall im Simulator laut Tutor nicht
	 * erforderlich ist, wird hier davon ausgegangen, dass Lady im Kreis rennt,
	 * bei einem Kreis bestehend aus 4 Feldern also bei jedem 4. Schritt wieder
	 * am Ausgangspunkt ist.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#step}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testStepCircle() {
		simulator = new PI2Simulator();
		simulator.initSimulation(circleLabyMatrix, new Point(0, 0), new Point(circleLabyMatrix.length - 1, circleLabyMatrix[0].length - 1));
		for (int i = 0; i < 8; i++) {
			simulator.step();
		}
		final Point point = simulator.getLadyCoordinates();
		assertEquals(point.getX(), 0, 0.0001);
		assertEquals(point.getY(), 0, 0.0001);
	}

	/**
	 * Testet die Reaktion von step() auf ein unmögliches Labyrinth. (kein Weg
	 * von links oben nach rechts unten.
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#step}.
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testStepImpossible() {
		try {
			simulator = new PI2Simulator();
			simulator.initSimulation(impossibleLabyMatrix, new Point(0, 0), new Point(impossibleLabyMatrix.length - 1,
					circleLabyMatrix[0].length - 1));
			for (int i = 0; i < 20; i++) {
				simulator.step();
			}
			fail("Should have thrown an IllegalStateException");
		} catch (final IllegalStateException e) {
		}
	}

	/**
	 * Testet die main-Methode. Da main-Methoden statisch sind, kann dieser
	 * Test unter Einhaltung der Konventionen nicht komplett automatisiert
	 * werden, daher sollte nach dem Start eine Prüfung per Hand erfolgen. (der
	 * Aufruf von step() kann nur auf einem Objekt erfolgen, statische Methoden
	 * sollten jedoch auf einer Klasse und nicht auf einem Objekt ausgeführt
	 * werden)
	 *
	 * Testmethode für {@link de.unibremen.pi2.uebung05.PI2Simulator#main} .
	 *
	 * @author Stephan Hopfmüller
	 */
	@Test
	public final void testMain() {
		try {
			PI2Simulator.main(null);
		} catch (final Exception e) {
			fail("Should not have thrown an Exception");
		}
	}
}
