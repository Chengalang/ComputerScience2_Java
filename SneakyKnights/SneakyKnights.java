// Kenny Cheng
// ke 351939
// COP 3503 Spring 2019
// SneakyKnights

import java.util.*;
import java.io.*;

public class SneakyKnights
{
	// Class that creates Coords Object to store
	// into HashSet based on (x, y).
	public static class Coords
	{
		private int xCol, yRow;

		// hashCode based on (x, y) coordinates
		// that also stops Integer overflow.
		public int hashCode()
		{	
			int hashVal; 

			hashVal = this.xCol - this.yRow;

			if (hashVal < 0)
				hashVal -= Integer.MIN_VALUE;

			return hashVal;
		}

		Coords(int col, int row)
		{
			this.xCol = col;
			this.yRow = row;
		}

		// Checks to see if object is already part of HashSet
		// and if it is, check individual (x, y) coordinates. 
		public boolean equals(Object o)
		{
			if (!(o instanceof Coords))
				return false;
			Coords test = (Coords)o;
			return this.xCol == test.xCol && this.yRow == test.yRow;
		}
	}

	// Method O(n) that takes in a list of Strings as positions for knights and boardsize to get  
	// possible (x, y) positions. Returns true if all knights placed are safe and false otherwise. 
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{

		HashSet<Coords> knightHash = new HashSet<Coords>();

		int numKnights = coordinateStrings.size(), strLen;
		int rowPos = 0, colPos = 0;

		if (numKnights == 0)
			return true;

		// Loop through each knight String and place on board.
		for (int i = 0; i < numKnights; i++)
		{	
			String knightStr = coordinateStrings.get(i);

			strLen = coordinateStrings.get(i).length();

			if (strLen == 0)
				continue;

			// Loop through the String O(k) and parse the Char for coordinates.
			for (int j = 0; j < strLen; j++)
			{
				if (knightStr.charAt(j) >= 'a' && knightStr.charAt(j) <= 'z')
				{
					colPos *= 26;
					colPos += knightStr.charAt(j) - 'a' + 1;
				}
				else if (knightStr.charAt(j) >= '0' && knightStr.charAt(j) <= '9')
				{
					rowPos *= 10;
					rowPos += knightStr.charAt(j) - '0';
				}
			}

			// Coord for knight based on (x, y) coordinates.
			Coords newCoord = new Coords(colPos, rowPos);

			// Check if knight is already placed in position
			// then add if location is open.
			if (knightHash.contains(newCoord))
				return false;
			else
				knightHash.add(new Coords(colPos, rowPos));

			// Check all possible moves current knight can make to see if any
			// other previous knight can attack.
			if (knightHash.contains(new Coords(colPos + 1, rowPos + 2)))
				return false;
			else if (knightHash.contains(new Coords(colPos - 1, rowPos + 2)))
				return false;
			else if (knightHash.contains(new Coords(colPos + 1, rowPos - 2)))
				return false;
			else if (knightHash.contains(new Coords(colPos - 1, rowPos - 2)))
				return false;
			else if (knightHash.contains(new Coords(colPos + 2, rowPos + 1)))
				return false;
			else if (knightHash.contains(new Coords(colPos + 2, rowPos - 1)))
				return false;
			else if (knightHash.contains(new Coords(colPos - 2, rowPos + 1)))
				return false;
			else if (knightHash.contains(new Coords(colPos - 2, rowPos - 1)))
				return false;

			// Reset coordinates.
			colPos = 0;
			rowPos = 0;
		}

		return true;
	}

	// Returns double based on x/5.0 for difficulty.
	public static double difficultyRating()
	{
		return 2.5;
	}

	// Returns double based on x/5.0 for time spent.
	public static double hoursSpent()
	{
		return 4.5;
	}
}
