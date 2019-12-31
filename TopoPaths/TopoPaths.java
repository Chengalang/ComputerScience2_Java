// Kenny Cheng
// ke 351939
// COP 3503 Spring 2019
// TopoPaths

import java.util.*;
import java.io.*;

public class TopoPaths
{	

	// Main function for checking if digraph has cycles, valid paths, or
	// a proper topological sort. Takes in a text file containing vertices.
	public static int countTopoPaths(String filename) throws IOException
	{	
		Scanner scanner = new Scanner(new File(filename));
		int numVerts = scanner.nextInt();

		int [][] aList = new int[numVerts][numVerts];
		int [] vertCount = new int[numVerts];

		// Nested for loop to insert text file vertices
		// into a 2D and 1D array to access later.
		for (int i = 0; i < numVerts; i++)
		{	
			vertCount[i] = scanner.nextInt();

			for (int j = 0; j < vertCount[i]; j++)
			{
				if (scanner.hasNextInt())
				{
					aList[i][j] = scanner.nextInt();
				}
				else
				{
					continue;
				}
			}
		}

		// Check to see if there are multiple vertices
		// with zero incoming edges. If there are more
		// than two, then it cannot be a valid path.
		int [] incoming = new int[numVerts + 1];
		int endCount = 0;

		// Set incoming all to zero.
		for (int i = 0; i < numVerts + 1; i++)
			incoming[i] = (i == 0 ? 1 : 0);

		// Add up all incoming edges to vertices.
		for (int i = 0; i < numVerts; i++)
			for (int j = 0; j < numVerts; j++)
				incoming[aList[i][j]]++;

		// If there are more than two vertices with 
		// no incoming edges then return 0.
		for (int i = 0; i < numVerts + 1; i++)
			if (incoming[i] == 0)
				if (++endCount > 1)
					return 0;

		// Check to see if there are any cycles. If there are 
		// any repeated beginning and ending vertices then there
		// must be a cycle.
		int [][] cycle = new int[numVerts + 1][numVerts + 1];

		// Set all possible cycles to 0.
		for (int i = 0; i < numVerts + 1; i++)
			for (int j = 0; j < numVerts + 1; j++)
				cycle[i][j] = 0;

		// Increment start and end point pairings.
		for (int i = 0; i < numVerts; i++)
			for (int j = 0; j < numVerts; j++)
				if (aList[i][j] > 0)
					cycle[vertCount[i]][aList[i][j]]++;

		// Check for multiple instances and return 0
		// if 2 or more are found. 
		for (int i = 0; i < numVerts + 1; i++)
			for (int j = 0; j < numVerts + 1; j++)
				if (cycle[i][j] >= 2)
					return 0;

		// Return 1 if all passes.
		return 1;
	}

	// Returns a double for diffculty 1.0 - 5.0.
	public static double difficultyRating()
	{
		return 3.0;
	}

	// Returns a double for hours spent > 0.0.
	public static double hoursSpent()
	{
		return 8.5;
	}
}