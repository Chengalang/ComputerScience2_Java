// Kenny Cheng
// ke 351939
// COP 3503 Spring 2019
// TopolALLgical

import java.io.*;
import java.util.*;

public class TopolALLgical
{
	// Process input file into array to represent digraph.
	public static HashSet<String> allTopologicalSorts(String filename) throws IOException
	{
		ArrayList<Integer> topoWait = new ArrayList<Integer>();
		HashSet<String> hashBrowns = new HashSet<String>();
		File testFile = new File(filename);

		// Test filename, if DNE then return empty HashSet.
		if (!testFile.isFile() && !testFile.canRead())
			return hashBrowns;

		Scanner lazer = new Scanner(new File(filename));

		int numVerts = lazer.nextInt();
		int vertex = 0, holDup = 0;

		// Arrange destination verts based on start vertex.
		// Incoming array based on vertex for index.
		int [][] vertDest = new int[numVerts + 1][numVerts + 1];
		int [] incoming = new int[numVerts + 1]; // length 7
		int numOut;

		// Insert vertex into vertDest with index
		// based on starting vertex and increase
		// incoming for vertex destinations.
		for (int i = 1; i <= numVerts; i++)
		{
			numOut = lazer.nextInt();

			for (int j = 0; j < numOut; j++)
			{
				vertDest[i][j] = lazer.nextInt();
				incoming[vertDest[i][j]]++;
			}
		}

		// Check every possible starting vertex with recursion initiated
		// by possible zero-incoming vertices.
		for (int i = 1; i <= numVerts; i++)
			backDatTrackUp(hashBrowns, topoWait, vertDest, incoming, i, 0);

		return hashBrowns;
	}

	// Recursive function that back tracks and checks all possible moves from vertex to vertex.
	public static HashSet<String> backDatTrackUp(HashSet<String> hashBrowns, 
		ArrayList<Integer> topoWait, int [][] topos, int [] incoming, int vertex, int holDup)
	{	
		// Base case to check for duplicate values or possible 
		// starting vertices based on current bt step.
		if (incoming[vertex] > 0 || topoWait.contains(vertex))
			return hashBrowns;

		// If vertex has 0 incoming 
		// and isn't already
		// in Array, add it.
		topoWait.add(vertex);

		// Temporary incoming array that doesn't affect
		// original incoming[] so changed states can check 
		// other possible moves.
		int [] tempIncoming = new int[incoming.length];

		for (int i = 0; i < incoming.length; i++)
			tempIncoming[i] = incoming[i];

		// Decrease all vertices that current vertex directs to.
		for (int i = 0; topos[vertex][i] != 0; i++)
			tempIncoming[topos[vertex][i]]--;

		// If holDup equals the number of vertices being tested
		// then a proper Topo has been found. 
		if ((holDup+1) == (incoming.length-1))
		{	
			String buildDatString = "";

			for (int i = 0; i < incoming.length-1; i++)
				buildDatString += (topoWait.get(i) + ((i == incoming.length-2) ? "" : " "));

			hashBrowns.add(buildDatString);
			topoWait.remove(topoWait.size()-1);

			return hashBrowns;
		}

		// Change state and check validity of next vertex.
		for (int i = 1; i <= incoming.length-1; i++)
			backDatTrackUp(hashBrowns, topoWait, topos, tempIncoming, i, holDup+1);

		// Remove current vertex when all possible 
		// states have been tested.
		topoWait.remove(topoWait.size()-1);

		return hashBrowns;
	}

	// Return double from 1.0 to 5.0 for difficulty.
	public static double difficultyRating()
	{
		return 3.0;
	}

	// Return double greater than 1.0 for hours spent.
	public static double hoursSpent()
	{
		return 8.0;
	}
}