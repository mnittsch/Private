package de.unibremen.pi2.uebung05;

import java.util.Random;

/**
 * Diese Klasse erzeugt echte quadratische Labyrinthe, die einen 
 * ungerichteten Graphen als unterliegendes Modell verwenden.
 *
 * @author Marco Nittscher, Lennart Abels
 *
 */
public class PI2Laby implements PI2LabyInterface {

	/**
	 * Der geforderte parameterlose Konstruktor für diese Klasse
	 * 
	 * @author Marco Nittscher
	 *
	 */
	public PI2Laby() {

	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels, Marco Nittscher
	 *
	 */
	@Override
	public int[][] createBoLa(final int size) {
		if ((size < MIN_SIZE) || (size > MAX_SIZE)) {
			throw new IllegalArgumentException("Labyrinth size out of bounds!");
		}

		final int[][] boLa = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int entry = 0;
				if (i > 0) {
					entry += 1;
				}
				if (i < (size - 1)) {
					entry += 4;
				}
				if (j > 0) {
					entry += 8;
				}
				if (j < (size - 1)) {
					entry += 2;
				}
				boLa[i][j] = entry;
			}
		}
		return boLa;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author Lennart Abels, Marco Nittscher
	 *
	 */
	@Override
	public int[][] createLabyrinth(final int size) {
		final int intcap = 10;
		if ((size < MIN_SIZE) || (size > MAX_SIZE)) {
			throw new IllegalArgumentException("Labyrinth size out of bounds!");
		}

		final int[][] laby = new int[size][size];

		final PI2Graph sourceGraph = new PI2Graph(size * size);

		final Random rnd = new Random();
		for (int i = 0; i < (size * size); i++) {

			if (((i + 1) % size) != 0) {// wenn der Knoten nicht am rechten Rand
										// ist
				sourceGraph.insertEdge(i, i + 1, rnd.nextInt(intcap) + 1); // nach
																			// rechts
			}
			if (i < (size * (size - 1))) {// wenn der Knoten nicht am unteren
											// Rand ist
				sourceGraph.insertEdge(i, i + size, rnd.nextInt(intcap) + 1); // nach
																				// unten
			}
		}

		final PI2GraphInterface minSpanGraph = sourceGraph.createMinimumSpanningTree(0);

		int c;
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				c = i + (size * j);
				// ungülige Knoten werden in isConnected gefangen.
				int entry = 0;
				if (minSpanGraph.isConnected(c, c - size)) {
					entry += 1; // oben
				}
				if ((((i + 1) % size) != 0) && minSpanGraph.isConnected(c, c + 1)) {
					entry += 2; // rechts
				}
				if (minSpanGraph.isConnected(c, c + size)) {
					entry += 4; // unten
				}
				if ((((i) % size) != 0) && minSpanGraph.isConnected(c, c - 1)) {
					entry += 8; // links
				}
				laby[j][i] = entry;
			}
		}

		return laby;
	}
}
