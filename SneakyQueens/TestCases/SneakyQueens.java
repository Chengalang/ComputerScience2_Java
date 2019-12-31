// Kenny Cheng
// ke351939
// COP 3503 Spring 2019

import java.io.*;
import java.util.*;

public class SneakyQueens
{
	// Returns true if all Queens are safe, otherwise return false.
	public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		// Used various array sizes to hold enough space for the different diagonal
		// pathes and also rows and columns. 
		int numQueens = coordinateStrings.size(), rowPos = 0, colPos = 0, strLength;
		int [] rowUsed = new int [boardSize + 1];
		int [] colUsed = new int [boardSize + 1];
		int [] backslashDiag = new int [boardSize * 2 + 1];
		int [] forwardSlashDiag = new int [boardSize * 2];

		if (numQueens == 0)
			return false;

		// Loop through number of Queens O(n)
		// to parse the strings and increment used counts.
		for (int i = 0; i < numQueens; i++)
		{
			strLength = coordinateStrings.get(i).length();

			if (strLength == 0)
				continue;

			// Loop through various defined string lengths,
			// making this overall for() O(nk) time.
			for (int j = 0; j < strLength; j++)
			{	
				// Need to go over regex again to avoid using
				// this way of parsing the strings.
				if (coordinateStrings.get(i).charAt(j) >= 'a' 
					&& coordinateStrings.get(i).charAt(j) <= 'z')
				{
					colPos *= 26;
					colPos += coordinateStrings.get(i).charAt(j) - 'a' + 1;
				}
				else if (coordinateStrings.get(i).charAt(j) >= '0' 
					&& coordinateStrings.get(i).charAt(j) <= '9')
				{
					rowPos *= 10;
					rowPos += coordinateStrings.get(i).charAt(j) - '0';
				}
			}

			// After parsing the string, check to see if the 
			// newly placed Queen is safe.
			if (++rowUsed[rowPos] > 1 || ++colUsed[colPos] > 1)
				return false;

			if (++backslashDiag[rowPos + colPos] > 1)
				return false;

			if (rowPos - colPos < 0)
			{
				if (++forwardSlashDiag[(colPos - rowPos) + (boardSize - 1)] > 1)
				{
					return false;
				}
			}
			else
			{
				if (++forwardSlashDiag[rowPos - colPos] > 1)
				{
					return false;
				}
			}

			// Reset index.
			rowPos = 0;
			colPos = 0;
		}

		return true;
	}

	public static double difficultyRating()
	{
		return 2.0;
	}

	public static double hoursSpent()
	{
		return 5.0;
	}
}