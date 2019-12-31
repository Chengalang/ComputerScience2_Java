// Kenny Cheng
// ke 351939
// COP 3503 Spring 2019
// RunLikeHell

import java.io.*;
import java.util.*;

public class RunLikeHell
{	
	public static int maxGain(int [] blocks)
	{	
		// No knowledge, you leave as dumb as you
		// were when you started. Like me.
		if (blocks.length == 0)
			return 0;

		// You have one choice. Pick it.
		if (blocks.length == 1)
			return blocks[0];

		// If block options are less than or 
		// equal to 2, we grab the max of the two.
		if (blocks.length <= 2)
			return Math.max(blocks[0], blocks[1]);

		// 1D array to hold results from previous attempts.
		int [] knowledgeGains = new int[blocks.length+1];

		// Initialize possible starting blocks.
		knowledgeGains[0] = blocks[0];
		knowledgeGains[1] = Math.max(blocks[0], blocks[1]);

		// Loop through and choose max between which possible
		// jump yields the highest 'knowledge' gain.
		for (int i = 2; i < blocks.length; i++)
		{
			knowledgeGains[i] = Math.max(
									blocks[i] + knowledgeGains[i-2],
									knowledgeGains[i-1]
					    	    );  // Spaces for allignment.
		}

		// Return result in array which holds
		// highest possible 'knowledge' gain.
		return knowledgeGains[blocks.length-1];
	}

	// Return double based on x/5 difficulty.
	public static double difficultyRating()
	{
		return 2.5;
	}

	// Return double based on hours spent x > 1.0.
	public static double hoursSpent()
	{
		return 5.0;
	}
}