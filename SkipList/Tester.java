import java.io.*;
import java.util.*;

public class Tester
{
	public static void main(String [] args)
	{
		SkipList<Integer> skipL = new SkipList<Integer>(5);

		System.out.println(skipL.height());

		// for (int i = 0; i < 8; i++)
		// {
		// 	System.out.println(skipL.get(i));
		// }
	}
}