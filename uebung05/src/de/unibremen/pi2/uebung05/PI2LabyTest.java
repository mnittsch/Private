package de.unibremen.pi2.uebung05;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Dies ist die Testklasse für die Klasse
 * PI2Laby.
 *
 * @author Lennart
 *
 */
public class PI2LabyTest {

	private PI2Laby	laby;

	/**
	 * Diese Methode stellt ein Objekt der Klasse PI2Graph
	 * für die Tests zur Verfügung.
	 *
	 * @throws Exception
	 *             Diese Exception wird geworfen wenn
	 *             unerklärliche Fehler auftreten
	 *
	 * @author Lennart Abels
	 */
	@Before
	public void setUp() throws Exception {
		laby = new PI2Laby();
	}

	/**
	 * Test 1
	 *
	 * Dieser Test überprüft, ob die Methode
	 * createBoLa() korrekt funktioniert,
	 * wenn korrekte Werte übergeben werden.
	 * Hier werden alle Größen ausgetestet.
	 *
	 * @author Lennart Abels, Julian Gebken
	 */
	@Test
	public void createBoLaTestValid() {
		for (int a = 3; a <= 30; a++) {
			final int[][] bola = laby.createBoLa(a);
			for (int i = 0; i < bola.length; i++) {
				for (int j = 0; j < bola[i].length; j++) {
					if ((i == 0) && (j == 0)) {
						assertTrue(bola[i][j] == 6);
					} else if ((i == 0) && (j == (bola.length - 1))) {
						assertTrue(bola[i][j] == 12);
					} else if ((i == (bola.length - 1)) && (j == 0)) {
						assertTrue(bola[i][j] == 3);
					} else if ((i == (bola.length - 1)) && (j == (bola.length - 1))) {
						assertTrue(bola[i][j] == 9);
					} else if (i == 0) {
						assertTrue(bola[i][j] == 14);
					} else if (j == 0) {
						assertTrue(bola[i][j] == 7);
					} else if (i == (bola.length - 1)) {
						assertTrue(bola[i][j] == 11);
					} else if (j == (bola.length - 1)) {
						assertTrue(bola[i][j] == 13);
					} else {
						assertTrue(bola[i][j] == 15);
					}

				}
			}
		}
	}

	/**
	 * Test 2
	 *
	 * Dieser Test überprüft, wie die Methode
	 * createBoLa() reagiert, wenn ungültige Werte
	 * übergeben werden.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void createBoLaTestInvalid() {
		try {
			laby.createBoLa(-1);
		} catch (final IllegalArgumentException i) {
		}
		try {
			laby.createBoLa(2);
		} catch (final IllegalArgumentException i) {
		}
		try {
			laby.createBoLa(31);
		} catch (final IllegalArgumentException i) {
		}
		try {
			laby.createBoLa(42);
		} catch (final IllegalArgumentException i) {
		}
	}

	/**
	 * Test 3
	 *
	 * Dieser Test überprüft, ob die Methode
	 * createLabyrinth korrekt funktioniert, wenn
	 * gültige Werte übergeben werden.
	 * Hier werden alle verschiedenen Größen
	 * der Labyrinthe durchgetestet.
	 *
	 * @author Lennart Abels, Julian Gebken
	 */
	@Test
	public void createLabyrinthTestValid() {
		System.out.println("Achtung dieser Test kann etwas länger dauern :)");
		for (int a = 3; a <= 30; a++) {
			final int[][] la = laby.createLabyrinth(a);

			for (int i = 0; i < (la.length - 1); i++) {
				for (int j = 0; j < (la.length - 1); j++) {
					int t = la[i][j];
					if (j != 0) {
						if (t >= 8) {
							int tj = la[i][j - 1];
							if (tj > 8) {
								tj -= 8;
							}
							if (tj > 4) {
								tj -= 4;
							}
							assertTrue(tj >= 2);
							t -= 8;
						}
					}
					if (i != (la.length - 1)) {
						if (t >= 4) {
							int tj = la[i + 1][j];
							if (tj > 8) {
								tj -= 8;
							}
							if (tj > 4) {
								tj -= 4;
							}
							if (tj > 2) {
								tj -= 2;
							}
							assertTrue(tj == 1);
							t -= 4;
						}
					}
					if (j != (la.length - 1)) {
						if (t >= 2) {
							final int tj = la[i][j + 1];
							assertTrue(tj >= 8);
							t -= 2;
						}
					}
					if (i != 0) {
						if (t >= 1) {
							int tj = la[i - 1][j];
							if (tj > 8) {
								tj -= 8;
							}
							assertTrue(tj >= 4);
							t -= 1;
						}
					}
					assertTrue(t == 0);
					assertTrue(la[i][j] != 0);
				}
			}
		}
	}

	/**
	 * Test 4
	 *
	 * Dieser Test überprüft, wie die Methode
	 * createLabyrinth() reagiert, wenn
	 * ungültige Werte übergeben werden.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void createLabyrinthTestInvalid() {
		try {
			laby.createLabyrinth(-1);
		} catch (final IllegalArgumentException i) {
		}
		try {
			laby.createLabyrinth(2);
		} catch (final IllegalArgumentException i) {
		}
		try {
			laby.createLabyrinth(31);
		} catch (final IllegalArgumentException i) {
		}
		try {
			laby.createLabyrinth(42);
		} catch (final IllegalArgumentException i) {
		}
	}
}
