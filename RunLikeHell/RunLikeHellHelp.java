import java.io.*;
import java.util.*;

public class RunLikeHellHelp
{
	public static int maxGain(int [] blocks)
	{
		int currentBlock = 0;
		return maxGainRec(blocks, currentBlock);
	}

	private static int maxGainRec(int [] blocks, int current)
	{
		if (current >= blocks.length)
			return 0;

		return Math.max(
			blocks[current] + maxGainRec(blocks, current+2),
			maxGainRec(blocks, current+1)
		);
	}

	public static int maxGainMemo (int [] blocks)
	{
		int currentBlock = 0;
		int [] memo = new int[blocks.length];

		for (int i = 0; i < blocks.length; i++)
			memo[i] = -1;

		return maxGainMemoHelper(blocks, currentBlock, memo);
	}

	private static int maxGainMemoHelper (int [] blocks, int current, int [] memo)
	{
		int result;

		if (current >= blocks.length)
			return 0;

		if (memo[current] != -1)
			return memo[current];

		result = Math.max(
			blocks[current] + maxGainMemoHelper(blocks, current+2, memo),
			maxGainMemoHelper(blocks, current+1, memo)
		);

		memo[current] = result;

		return memo[current];
	}
}
