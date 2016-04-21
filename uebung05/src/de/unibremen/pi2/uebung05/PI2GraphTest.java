package de.unibremen.pi2.uebung05;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Dies ist die Testklasse für die Klasse
 * PI2Graph.
 *
 * @author Lennart
 *
 */
public class PI2GraphTest {

	private PI2GraphInterface	testGraph1;
	private PI2GraphInterface	testGraph2;
	private PI2GraphInterface	testGraph3;

	/**
	 * Diese Methode stellt drei verschiedene Objekte
	 * der Klasse PI2Graph für die Tests zur Verfügung.
	 *
	 * @throws Exception
	 *             Diese Exception wird geworfen wenn
	 *             unerklärliche Fehler auftreten
	 * 
	 * @author Lennart Abels
	 */
	@Before
	public void setUp() throws Exception {
		testGraph1 = new PI2Graph();
		testGraph2 = new PI2Graph();
		testGraph3 = new PI2Graph();
	}

	/**
	 * Test 1
	 *
	 * Dieser Test testet den parameterlosen Konstruktor der
	 * Klasse PI2Graph.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void constructorTest1Valid() {
		try {
			new PI2Graph();
		} catch (final Exception e) {
			fail("Should not have thrown an Exception!");
		}
	}

	/**
	 * Test 2
	 *
	 * Dieser Test testet den zweiten Konstruktor der
	 * Klasse PI2Graph mit gültigen Parametern.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void constructorTest2Valid() {
		try {
			new PI2Graph(4);
		} catch (final Exception e) {
			fail("Should not have thrown an Exception!");
		}

		try {
			new PI2Graph(0);
		} catch (final Exception e) {
			fail("Should not have thrown an Exception!");
		}
	}

	/**
	 * Test 3
	 *
	 * Dieser Test testet den zweiten Konstruktor der
	 * Klasse PI2Graph mit gültigen Parametern
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void constructorTest2Invalid() {
		try {
			new PI2Graph(-3);
			fail("Should have thrown a IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
		}
	}

	/**
	 * Test 4
	 *
	 * Dieser Test überprüft, ob die Methode
	 * insertNode() korrekt funktioniert und die
	 * richtigen Werte zurückgibt.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void insertNodeTestValid() {
		assertTrue(testGraph1.insertNode() == 0);
		testGraph2.insertNode();
		testGraph2.insertNode();
		assertTrue(testGraph2.insertNode() == 2);
	}

	/**
	 * Test 5
	 *
	 * Dieser Test testet die Methode
	 * insertEdge() mit ungültigen Parametern.
	 *
	 * @author Lennart Abels, Jessica Winter
	 */
	@Test
	public void insertEdgeTestInvalid() {
		testGraph1.insertNode();
		testGraph1.insertNode();

		try {
			testGraph1.insertEdge(2, 1, 1);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.insertEdge(1, 2, 1);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.insertEdge(-1, 1, 1);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.insertEdge(1, -1, 1);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.insertEdge(0, 1, -1);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.insertEdge(1, 1, 1);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (final IllegalArgumentException i) {
		}

		testGraph1.insertEdge(1, 0, 1);
		try {
			testGraph1.insertEdge(1, 0, 1);
			fail("Should have thrown an IllegalStateException!");
		} catch (final IllegalStateException i) {
		}
	}

	/**
	 * Test 6
	 *
	 * Dieser Test überprüft, ob die Methode
	 * insertEdge() richtig funktioniert, sobald
	 * gültige Parameter übergeben werden.
	 *
	 * @author Lennart Abels, Jessica Winter
	 */
	@Test
	public void insertEdgeTestValid() {
		testGraph1.insertNode();
		testGraph1.insertNode();
		testGraph1.insertEdge(0, 1, 2);

		try {
			testGraph1.insertEdge(1, 0, 2);
			fail("Should have thrown an IllegalStateException!");
		} catch (final IllegalStateException i) {
		}

		assertTrue(testGraph1.isConnected(0, 1));
		assertTrue(testGraph1.isConnected(1, 0));
	}

	/**
	 * Test 7
	 *
	 * Dieser Test testet die Methode isConnected()
	 * auf Korrektheit ihrer Funktionsweise, wenn
	 * die Vorraussetzungen korrekt sind.
	 *
	 * @author Lennart Abels, Jessica Winter
	 */
	@Test
	public void isConnectedTestValid() {
		testGraph1.insertNode();
		testGraph1.insertNode();
		testGraph1.insertEdge(0, 1, 2);

		try {
			testGraph1.isConnected(0, 1);
			testGraph1.isConnected(1, 0);
		} catch (final Exception E) {
			fail("Should not have thrown an Exception!");
		}

		assertTrue(testGraph1.isConnected(0, 1));
		assertTrue(testGraph1.isConnected(1, 0));
		assertFalse(testGraph1.isConnected(0, 0));
		assertFalse(testGraph1.isConnected(1, 1));
	}

	/**
	 * Test 8
	 *
	 * Diese Methode testet die Methode isConnected()
	 * auf Korrektheit ihrer Funktionsweise, wenn
	 * die Vorraussetzungen inkorrekt sind.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void isConnectedTestInvalid() {
		testGraph1.insertNode();
		assertFalse(testGraph1.isConnected(0, 1));
		assertFalse(testGraph1.isConnected(0, -1));
		assertFalse(testGraph1.isConnected(1, 0));
		assertFalse(testGraph1.isConnected(-1, 0));
	}

	/**
	 * Test 9
	 *
	 * Dieser Test überprüft, ob die Methode isEmpty()
	 * richtig funktioniert.
	 *
	 * @author Lennart Abels, Julian Gebken
	 */
	@Test
	public void isEmptyTestValid() {
		testGraph2.insertNode();

		testGraph3.insertNode();
		testGraph3.insertNode();
		testGraph3.insertEdge(0, 1, 2);

		assertTrue(testGraph1.isEmpty());
		assertFalse(testGraph2.isEmpty());
		assertFalse(testGraph3.isEmpty());
	}

	/**
	 * Test 10
	 *
	 * Dieser Test überprüft, ob die Methode
	 * getNumberOfNodes() die richtige Anzahl an
	 * Knoten zurück gibt.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void getNumberOfNodesTestValid() {
		testGraph2.insertNode();

		testGraph3.insertNode();
		testGraph3.insertNode();
		testGraph3.insertEdge(0, 1, 2);

		assertTrue(testGraph1.getNumberOfNodes() == 0);
		assertTrue(testGraph2.getNumberOfNodes() == 1);
		assertTrue(testGraph3.getNumberOfNodes() == 2);
	}

	/**
	 * Test 11
	 *
	 * Dieser Test überprüft, ob die Methode
	 * getNumberOfEdges() die richtige Anzahl
	 * an Kanten zurück gibt.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void getNumberOfEdgesTestValid() {
		testGraph2.insertNode();
		testGraph2.insertNode();
		testGraph2.insertEdge(0, 1, 2);

		testGraph3.insertNode();
		testGraph3.insertNode();
		testGraph3.insertNode();
		testGraph3.insertEdge(0, 1, 2);
		testGraph3.insertEdge(0, 2, 1);
		testGraph3.insertEdge(1, 2, 0);

		assertTrue(testGraph1.getNumberOfEdges() == 0);
		assertTrue(testGraph2.getNumberOfEdges() == 1);
		assertTrue(testGraph3.getNumberOfEdges() == 3);
	}

	/**
	 * Test 12
	 *
	 * Dieser Test überprüft, ob die Methode
	 * getWeight() die richtige Gewichtung zurück
	 * gibt, sofern gültige Werte übergeben werden.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void getWeightTestValid() {
		testGraph1.insertNode();
		testGraph1.insertNode();
		testGraph1.insertEdge(0, 1, 2);

		assertTrue(testGraph1.getWeight(0, 1) == 2);
		assertTrue(testGraph1.getWeight(1, 0) == 2);
	}

	/**
	 * Test 13
	 *
	 * Dieser Test überprüft, wie die Methode
	 * getWeight() reagiert, wenn ungültige Werte
	 * übergeben werden.
	 *
	 * @author Lennart Abels, Jessica Winter
	 */
	@Test
	public void getWeightTestInvalid() {
		testGraph1.insertNode();
		testGraph1.insertNode();
		try {
			testGraph1.getWeight(-1, 0);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.getWeight(2, 0);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.getWeight(0, -1);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.getWeight(0, 2);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.getWeight(0, 0);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.getWeight(1, 1);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.getWeight(0, 1);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.getWeight(1, 0);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}
	}

	/**
	 * Test 14
	 *
	 * Dieser Test überprüft, ob die Methode
	 * getAdjacencyMatrix eine korrekte Matrix zurück gibt.
	 *
	 * @author Lennart Abels
	 */
	@Test
	public void getAdjacencyMatrixTestValid() {
		final int[][] testMatrix1 = testGraph1.getAdjacencyMatrix();

		testGraph1.insertNode();
		final int[][] testMatrix2 = testGraph1.getAdjacencyMatrix();

		testGraph1.insertNode();
		final int[][] testMatrix3 = testGraph1.getAdjacencyMatrix();

		testGraph1.insertEdge(0, 1, 1);
		final int[][] testMatrix4 = testGraph1.getAdjacencyMatrix();

		assertTrue(testMatrix1.length == 0);

		assertTrue(testMatrix2.length == 1);
		assertTrue(testMatrix2[0].length == 1);
		assertTrue(testMatrix2[0][0] == -1);

		assertTrue(testMatrix3.length == 2);
		assertTrue(testMatrix3[0].length == 2);
		assertTrue(testMatrix3[1].length == 2);
		assertTrue(testMatrix3[0][0] == -1);
		assertTrue(testMatrix3[0][1] == -1);
		assertTrue(testMatrix3[1][0] == -1);
		assertTrue(testMatrix3[1][1] == -1);

		assertTrue(testMatrix4.length == 2);
		assertTrue(testMatrix4[0].length == 2);
		assertTrue(testMatrix4[1].length == 2);
		assertTrue(testMatrix4[0][0] == -1);
		assertTrue(testMatrix4[0][1] == 1);
		assertTrue(testMatrix4[1][0] == 1);
		assertTrue(testMatrix4[1][1] == -1);
	}

	/**
	 * Test 15
	 *
	 * Dieser Test überprüft, ob die Methode
	 * createMinimumSpanningTree() einen korrekten
	 * Baum zurückgibt für verschiedene Startwerte.
	 * Die erwarteten Bäume wurden per Hand
	 * errechnet.
	 *
	 * @author Lennart Abels, Julian Gebken
	 */
	@Test
	public void createMinimumSpanningTreeTestValid() {
		final int[][] testMatrix1 = { { -1, 4, -1, 3, -1, -1, -1, -1, -1 }, { 4, -1, 2, -1, 5, -1, -1, -1, -1 },
				{ -1, 2, -1, -1, -1, 10, -1, -1, -1 }, { 3, -1, -1, -1, 6, -1, 2, -1, -1 }, { -1, 5, -1, 6, -1, 7, -1, 4, -1 },
				{ -1, -1, 10, -1, 7, -1, -1, -1, 3 }, { -1, -1, -1, 2, -1, -1, -1, 4, -1 }, { -1, -1, -1, -1, 4, -1, 4, -1, 6 },
				{ -1, -1, -1, -1, -1, 3, -1, 6, -1 } };

		final int[][] testMatrix2 = { { -1, 4, -1, 3, -1, -1, -1, -1, -1 }, { 4, -1, 2, -1, -1, -1, -1, -1, -1 },
				{ -1, 2, -1, -1, -1, -1, -1, -1, -1 }, { 3, -1, -1, -1, -1, -1, 2, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, 4, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, 3 }, { -1, -1, -1, 2, -1, -1, -1, 4, -1 }, { -1, -1, -1, -1, 4, -1, 4, -1, 6 },
				{ -1, -1, -1, -1, -1, 3, -1, 6, -1 } };

		final int[][] testMatrix3 = { { -1, 4, -1, 3, -1, -1, -1, -1, -1 }, { 4, -1, 2, -1, -1, -1, -1, -1, -1 },
				{ -1, 2, -1, -1, -1, -1, -1, -1, -1 }, { 3, -1, -1, -1, -1, -1, 2, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, 4, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, 3 }, { -1, -1, -1, 2, -1, -1, -1, 4, -1 }, { -1, -1, -1, -1, 4, -1, 4, -1, 6 },
				{ -1, -1, -1, -1, -1, 3, -1, 6, -1 }

		};

		testGraph1 = new PI2Graph(9);
		testGraph1.insertEdge(0, 1, 4);
		testGraph1.insertEdge(3, 0, 3);
		testGraph1.insertEdge(1, 4, 5);
		testGraph1.insertEdge(1, 2, 2);
		testGraph1.insertEdge(2, 5, 10);
		testGraph1.insertEdge(3, 4, 6);
		testGraph1.insertEdge(3, 6, 2);
		testGraph1.insertEdge(4, 5, 7);
		testGraph1.insertEdge(4, 7, 4);
		testGraph1.insertEdge(5, 8, 3);
		testGraph1.insertEdge(6, 7, 4);
		testGraph1.insertEdge(7, 8, 6);

		final int[][] matrix1 = testGraph1.getAdjacencyMatrix();

		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1[0].length; j++) {
				if (matrix1[i][j] != testMatrix1[i][j]) {
					fail(i + " und " + j + " Ist " + matrix1[i][j] + " aber sollte " + testMatrix1[i][j]);
				}
			}
		}

		testGraph2 = testGraph1.createMinimumSpanningTree(0);
		final int[][] matrix2 = testGraph2.getAdjacencyMatrix();

		for (int i = 0; i < matrix2.length; i++) {
			for (int j = 0; j < matrix2[0].length; j++) {
				if (matrix2[i][j] != testMatrix2[i][j]) {
					fail(i + " und " + j + " Ist " + matrix2[i][j] + " aber sollte " + testMatrix2[i][j]);
				}
			}
		}

		testGraph3 = testGraph1.createMinimumSpanningTree(4);
		final int[][] matrix3 = testGraph3.getAdjacencyMatrix();

		for (int i = 0; i < matrix3.length; i++) {
			for (int j = 0; j < matrix3[0].length; j++) {
				if (matrix3[i][j] != testMatrix3[i][j]) {
					fail(i + " und " + j + " Ist " + matrix3[i][j] + " aber sollte " + testMatrix3[i][j]);
				}
			}
		}
	}

	/**
	 * Test 16
	 *
	 * Dieser Test überprüft, ob die Methode
	 * createMinimumSpanningTree() richtig reagiert,
	 * wenn ungültige Werte übergeben werden.
	 *
	 * @author Lennart Abels, Jessica Winter
	 */
	@Test
	public void createMinimumSpanningTreeTestInvalid() {
		testGraph1.insertNode();
		try {
			testGraph1.createMinimumSpanningTree(-1);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (final IllegalArgumentException i) {
		}

		try {
			testGraph1.createMinimumSpanningTree(1);
			fail("Should have thrown an IllegalArgumentException");
		} catch (final IllegalArgumentException i) {
		}
		
		testGraph2.insertNode();
		testGraph2.insertNode();
		testGraph2.insertNode();
		testGraph2.insertNode();
		testGraph2.insertEdge(0, 1, 1);
		testGraph2.insertEdge(2, 3, 1);
		try{
			testGraph2.createMinimumSpanningTree(0);
			fail("Should have thrown an IllegalStateException");
		}catch(IllegalStateException i){
		}
		try{
			testGraph2.createMinimumSpanningTree(1);
			fail("Should have thrown an IllegalStateException");
		}catch(IllegalStateException i){
		}
		try{
			testGraph2.createMinimumSpanningTree(2);
			fail("Should have thrown an IllegalStateException");
		}catch(IllegalStateException i){
		}
		try{
			testGraph2.createMinimumSpanningTree(3);
			fail("Should have thrown an IllegalStateException");
		}catch(IllegalStateException i){
		}
	}
}
