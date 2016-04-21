package de.unibremen.pi2.uebung05;

import java.util.ArrayList;

/**
 * BLABLA BLA
 * Diese Klasse implementiert den Graphen, welcher
 * zu einem minimal spannenden Baum gemacht werden kann.
 * Die Spezifikationen sind dabei durch das Interface
 * PI2GraphInterface gegeben.
 *
 * @author Lennart Abels, Marco Nittscher
 */
public class PI2Graph implements PI2GraphInterface {

	/**
	 * Die zu diesem Graphen zugehörige Adjazenzmatrix.
	 * Nach Einfügen von Knoten oder Kanten in den Graphen wird diese ungültig
	 * und muss aktualisiert werden.
	 *
	 * @author Marco Nittscher
	 */
	private int[][]						adjacencyMatrix;

	/**
	 * Wenn dieser Wert wahr ist, entspricht die Adjazenzmatrix der
	 * tatsächlichen Adjazenzmatrix des Graphen.
	 *
	 * @author Marco Nittscher
	 */
	private boolean						adMatIsUpdated;

	/**
	 * In dieser ArrayList werden alle Knoten mithilfe von Integern gespeichert.
	 *
	 * @author Lennart Abels
	 */
	private final ArrayList<Integer>	nodeList;

	/**
	 * In dieser ArrayList werden die Kanten in der Form
	 * {Knoten1, Knoten2, Gewichtung}
	 * gespeichert.
	 *
	 * @author Lennart Abels
	 */
	private final ArrayList<int[]>		edgeList;

	/**
	 * Der Standardkonstruktor der Klasse PI2Graph.
	 *
	 * @author Lennart Abels
	 */
	public PI2Graph() {
		nodeList = new ArrayList<Integer>();
		edgeList = new ArrayList<int[]>();
	}

	/**
	 * Dieser Konstruktor erstellt automatisch einen Baum mit der
	 * gegebenen Anzahl an Knoten.
	 *
	 * @param numberNodes
	 *            Die Anzahl an Knoten, welche
	 *            erzeugt werden sollen
	 *
	 * @author Lennart Abels
	 */
	public PI2Graph(final int numberNodes) {
		if (numberNodes < 0) {
			throw new IllegalArgumentException("Numbers below zero are not allowed!");
		}
		nodeList = new ArrayList<Integer>();
		edgeList = new ArrayList<int[]>();
		for (int i = 0; i < numberNodes; i++) {
			nodeList.add(i);
		}
		adMatIsUpdated = false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public int insertNode() {
		nodeList.add(nodeList.size());
		adMatIsUpdated = false;
		return nodeList.size() - 1;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public void insertEdge(final int src, final int target, final int weight) {
		// Überprüfen der Kriterien für IllegalArgumentExceptiuon
		if ((src >= nodeList.size()) || (src < 0)) {
			throw new IllegalArgumentException("The node " + src + " is not part of this graph!");
		}
		if ((target >= nodeList.size()) || (target < 0)) {
			throw new IllegalArgumentException("The node " + target + " is not part of this graph!");
		}
		if (src == target) {
			throw new IllegalArgumentException("The given nodes are the same!");
		}
		if (weight < 0) {
			throw new IllegalArgumentException("The weight must be positive");
		}

		// Überprüfen der Kriterien für IllegalStateException
		if (isConnected(src, target)) {
			throw new IllegalStateException("The given Nodes are already connected.");
		}
		
		final int[] vektor1 = { src, target, weight };
		edgeList.add(vektor1);

		final int[] vektor2 = { target, src, weight };
		edgeList.add(vektor2);

		adMatIsUpdated = false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public boolean isConnected(final int scr, final int target) {
		if ((scr >= nodeList.size()) || (target >= nodeList.size()) || (scr < 0) || (target < 0)) {
			return false;
		}
		final int[][] matrix = getAdjacencyMatrix();
		return matrix[scr][target] != -1;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public PI2GraphInterface createMinimumSpanningTree(final int startNode) {
		if ((startNode >= nodeList.size()) || (startNode < 0)) {
			throw new IllegalArgumentException("The given node is not given within this graph!");
		}
		// Der Knoten, der am Ende zurückgegeben wird
		final PI2Graph tree = new PI2Graph(nodeList.size());

		// Die Liste, in der die zu betrachtenden Nodes drinne stehen
		final ArrayList<Integer> toWatch = new ArrayList<Integer>();

		final int[][] matrixTree = getAdjacencyMatrix();
		toWatch.add(startNode);

		/*
		 * Hier wird das kleinste Element gespeichert
		 * Form:
		 * x-Koordinate, y-Koordinate, Gewichtung
		 */
		int[] minimum = new int[3];

		for (int i = 0; i < matrixTree.length; i++) {
			matrixTree[i][startNode] = -1;
		}
		
		while (!isAtEnd(matrixTree)) {
			minimum = getMinimum(matrixTree, toWatch);
			if(minimum[0] == -1 || minimum[1] == -1 || minimum[2] == -1) throw new IllegalStateException("There are connected Nodes in this graph, which are not connected to the main tree.");
			tree.insertEdge(minimum[0], minimum[1], minimum[2]);
			for (int i = 0; i < matrixTree[minimum[1]].length; i++) {
				matrixTree[i][minimum[1]] = -1;
			}
			matrixTree[minimum[1]][minimum[0]] = -1;
			toWatch.add(minimum[1]);
		}
		return tree;
	}

	/**
	 * Diese Methode überprüft, ob die angegebene Matrix
	 * nur "-1"-Einträge besitzt.
	 *
	 * @param input
	 *            Die zu überprüfende Matrix
	 * @return true, falls die Matrix nur aus "-1" Einträgen besteht,
	 *         false sonst.
	 *
	 * @author Lennart Abels
	 */
	private boolean isAtEnd(final int[][] input) {
		int calc = 0;
		for (final int[] element : input) {
			for (final int element2 : element) {
				calc += element2;
			}
		}
		return (calc / (input.length * input[0].length)) == -1;
	}

	/**
	 * Diese Methode gibt das kleinste Element der Matrix zurückgegeben
	 * unter der Berücksichtigung, dass nur die Zeilen überprüft werden,
	 * die in der mitgegebenen ArrayList vorhanden sind.
	 *
	 * @author Lennart Abels
	 */
	private int[] getMinimum(final int[][] matrix, final ArrayList<Integer> zeilen) {
		final int[] min = { -1, -1, -1 };
		for (final Integer a : zeilen) {
			for (int i = 0; i < matrix[a].length; i++) {
				if (matrix[a][i] > -1) {
					if (min[2] == -1) {
						min[0] = a;
						min[1] = i;
						min[2] = matrix[a][i];
					} else if (matrix[a][i] < min[2]) {
						min[0] = a;
						min[1] = i;
						min[2] = matrix[a][i];
					}
				}
			}
		}

		return min;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public boolean isEmpty() {
		return (nodeList.isEmpty() && edgeList.isEmpty());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public int getNumberOfNodes() {
		return nodeList.size();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public int getNumberOfEdges() {
		return edgeList.size() / 2;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels
	 */
	@Override
	public int getWeight(final int scr, final int target) {
		if ((scr >= nodeList.size()) || (scr < 0)) {
			throw new IllegalArgumentException("The node " + scr + " is not part of this graph!");
		}
		if ((target >= nodeList.size()) || (target < 0)) {
			throw new IllegalArgumentException("The node " + target + " is not part of this graph!");
		}
		if (scr == target) {
			throw new IllegalArgumentException("The given nodes are the same!");
		}

		final int[][] matrix = getAdjacencyMatrix();
		if (matrix[scr][target] == -1) {
			throw new IllegalArgumentException("No existing edge between node " + scr + " and " + target);
		}

		return matrix[scr][target];
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels, Marco Nittscher
	 */
	@Override
	public int[][] getAdjacencyMatrix() {
		if (!adMatIsUpdated) {
			updateAdjacencyMatrix();
		}
		final int[][] matrix = new int[adjacencyMatrix.length][adjacencyMatrix.length];

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				matrix[i][j] = adjacencyMatrix[i][j];
			}
		}

		return matrix;
	}

	/**
	 * Diese Methode aktualisiert die Adjazenzmatrix des Graphen.
	 *
	 * @author Marco Nittscher
	 */
	private void updateAdjacencyMatrix() {
		final int[][] matrix = new int[nodeList.size()][nodeList.size()];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = -1;
			}
		}

		for (final int[] edge : edgeList) {
			matrix[edge[0]][edge[1]] = edge[2];
		}

		adjacencyMatrix = matrix;
		adMatIsUpdated = true;
	}

}
