// Kenny Cheng
// ke351939
// COP 3503 Spring 2019
// SkipList

import java.util.*;
import java.io.*;

class Node<AnyType>
{
	int height;
	AnyType data;
	Node<AnyType> next;
	ArrayList<Node<AnyType>> nodeArr;

	Node(int height)
	{
		ArrayList<Node<AnyType>> nodeArr = new ArrayList<Node<AnyType>>();
		this.height = height;
		
		for (int i = 1; i <= height; i++)
		{			
			nodeArr.add(null);
		}
	}

	Node(AnyType data, int height)
	{
		ArrayList<Node<AnyType>> nodeArr = new ArrayList<Node<AnyType>>();
		this.data = data;
		this.height = height;

		for (int i = 1; i <= height; i++)
			nodeArr.add(null);
	}

	public AnyType value()
	{
		return this.data;
	}

	public int height()
	{
		return this.height;
	}

	public Node<AnyType> next(int level)
	{
		if (level < 0 || level >= height())
			return null;

		return nodeArr.get(level).next; 
	}	

	// Suggest methods

	public void setNext(int level, Node<AnyType> node)
	{
		Node<AnyType> tempNode = nodeArr.get(level);

		while (tempNode.next != null)
		{
			tempNode = tempNode.next;
		}

		tempNode.next = node;
	}

	public void grow()
	{
		nodeArr.add(null);
	}

	public void maybeGrow(Node<AnyType> node)
	{
		Random ran = new Random();

		if (ran.nextInt(2) == 0)
		{
			// do nothing.
		}
		else
		{
			node.grow();
		}
	}

	public void trim(int height)
	{
		for (int i = 0; height() != height; i++)
		{
			nodeArr.remove(height);
			height--;
		}
	}
}

public class SkipList<AnyType extends Comparable<AnyType>>
{
	int height, size;
	Node<AnyType> headNode;
	Node<AnyType> newNode;
	Node<AnyType> tempNode;

	SkipList()
	{
		Node<AnyType> headNode = new Node<AnyType>(1);
		this.height = 1;		
	}

	SkipList(int height)
	{
		if (height < 1)
		{
			Node<AnyType> headNode = new Node<AnyType>(1);
			this.height = 1;
		}
		else
		{
			Node<AnyType> headNode = new Node<AnyType>(height);
			this.height = height;
		}
	}

	public int size()
	{
		return this.size;
	}
	
	public int height()
	{
		return this.height;
	}

	public Node<AnyType> head()
	{
		return headNode;
	}

	public void insert(AnyType data)
	{
		Node<AnyType> tempNode = headNode;
		int height = height();
		Random ran = new Random();
		Boolean aFlag = true, bFlag = true;

		if (contains(data))
		{
			while (aFlag)
			{
				if (tempNode.next == null || data.compareTo(tempNode.next.data) > 0 && height > 1)
				{
					tempNode = tempNode.nodeArr.get(--height);
				}
				else if (data.compareTo(tempNode.next.data) == 0)
				{
					for (int i = 1; bFlag; i++)
					{
						if (ran.nextInt(2) == 0)
						{
							Node<AnyType> newNode = new Node<AnyType>(data, i);
							bFlag = false;
						}	
					}

					newNode.next = tempNode.next;
					tempNode.next = newNode;
					aFlag = false;
				}
				else if (data.compareTo(tempNode.next.data) > 0)
				{
					tempNode = tempNode.next;
				}
			}
		}
		else
		{
			while (aFlag)
			{
				if (tempNode.next == null || data.compareTo(tempNode.next.data) > 0 && height > 1)
				{
					tempNode = tempNode.nodeArr.get(--height);
				}
				else if (data.compareTo(tempNode.next.data) > 0 
							&& data.compareTo(tempNode.data) < 0)
				{
					for (int i = 1; bFlag; i++)
					{
						if (ran.nextInt(2) == 0)
						{
							Node<AnyType> newNode = new Node<AnyType>(data, i);
							bFlag = false;
						}	
					}

					newNode.next = tempNode.next;
					tempNode.next = newNode;
					aFlag = false;
				}
				else if (data.compareTo(tempNode.next.data) > 0)
				{
					tempNode = tempNode.next;
				}
			}
		}
	}

	public void insert(AnyType data, int height)
	{
		Node<AnyType> tempNode = headNode;
		int testHeight = height();
		Boolean aFlag = true, bFlag = true;

		if (contains(data))
		{
			while (aFlag)
			{
				if (tempNode.next == null || data.compareTo(tempNode.next.data) > 0 & testHeight > 1)
				{
					tempNode = tempNode.nodeArr.get(--testHeight);
				}
				else if (data.compareTo(tempNode.next.data) == 0)
				{
					Node<AnyType> newNode = new Node<AnyType>(data, height);

					newNode.next = tempNode.next;
					tempNode.next = newNode;
					aFlag = false;
				}
				else if (data.compareTo(tempNode.next.data) > 0)
				{
					tempNode = tempNode.next;
				}
			}
		}
		else
		{
			while (aFlag)
			{
				if (tempNode.next == null || data.compareTo(tempNode.next.data) > 0 & testHeight > 1)
				{
					tempNode = tempNode.nodeArr.get(--testHeight);
				}
				else if (data.compareTo(tempNode.next.data) > 0 
							&& data.compareTo(tempNode.data) < 0)
				{
					Node<AnyType> newNode = new Node<AnyType>(data, height);
		
					newNode.next = tempNode.next;
					tempNode.next = newNode;
					aFlag = false;
				}
				else if (data.compareTo(tempNode.next.data) > 0)
				{
					tempNode = tempNode.next;
				}
			}
		}
	}

	public void delete(AnyType data)
	{
		if (contains(data))
		{
			Boolean flag = true;
			Node<AnyType> tempNode = headNode;
			int testHeight = height();

			while (flag)
			{
				if (tempNode.next == null || data.compareTo(tempNode.next.data) > 0 & testHeight > 1)
				{
					tempNode = tempNode.nodeArr.get(--testHeight);
				}
				else if (data.compareTo(headNode.data) == 0)
				{
					headNode = tempNode.next;
				}
				else if (data.compareTo(tempNode.next.data) == 0)
				{
					tempNode.next = tempNode.next.next;
					flag = false;
				}
				else if (data.compareTo(tempNode.next.data) > 0)
				{
					tempNode = tempNode.next;
				}
			}
		}
	}

	public boolean contains(AnyType data)
	{			
		Boolean cFlag = true;

		while (cFlag)
		{
			if (data.compareTo(tempNode.data) == 0)
				return true;

			if (tempNode.next == null || data.compareTo(tempNode.next.data) > 0 && height > 1)
			{
				tempNode = tempNode.nodeArr.get(--height);
			}
			else if (data.compareTo(tempNode.next.data) > 0)
			{
				tempNode = tempNode.next;
			}
			else if (tempNode.next == null && height == 1)
			{
				cFlag = false;
			}
		}

		return false;
	}

	public Node<AnyType> get(AnyType data)
	{
		Boolean flag = true;
		Node<AnyType> tempNode = headNode;

		while (flag)
		{
			if (data.compareTo(tempNode.data) == 0)
				return tempNode;

			if (tempNode.next == null || data.compareTo(tempNode.next.data) > 0 && height > 1)
			{
				tempNode = tempNode.nodeArr.get(--height);
			}
			else if (data.compareTo(tempNode.next.data) > 0)
			{
				tempNode = tempNode.next;
			}
			else if (tempNode.next == null && height == 1)
			{
				flag = false;
			}
		}

		return null;
	}

	public static double difficultyRating()
	{
		return 5.0;
	}

	public static double hoursSpent()
	{
		return 30.0;
	}

	// Suggested Methods

	private static int getMaxHeight(int n)
	{
		return 0;
	}

	private static int generateRandomHeight(int maxHeight)
	{
		return 0;
	}

	public void growSkipList()
	{

	}

	public void trimSkipList()
	{
		
	}
}
