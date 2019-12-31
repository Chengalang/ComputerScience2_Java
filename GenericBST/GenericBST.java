// Kenny Cheng
// ke351939
// COP 3503 Spring 2019

import java.io.*;
import java.util.*;

// Node class that acts as 
// a struct in Java.
class Node<AnyType>
{
	AnyType data;
	Node<AnyType> left, right;

	// Constructor used to
	// initialize Node.
	Node(AnyType data)
	{
		this.data = data;
	}
}

// Main class that takes in AnyType variables that are
// viable with Comparable.
public class GenericBST<AnyType extends Comparable<AnyType>>
{
	private Node<AnyType> root;

	// Basic helper function to get
	// recursion of insert().
	public void insert(AnyType data)
	{
		root = insert(root, data);
	}

	// Insert method that takes the root of the Node and the data
	// and uses Comparable compareTo() to place the node depending
	// on the compareTo() value being negative or positive.
	private Node<AnyType> insert(Node<AnyType> root, AnyType data)
	{
		if (root == null)
		{
			return new Node<>(data);
		}
		else if (data.compareTo(root.data) < 0)
		{
			root.left = insert(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			root.right = insert(root.right, data);
		}
		else
		{
			// Stylistically, I have this here to explicitly state that we are
			// disallowing insertion of duplicate values. This is unconventional
			// and a bit cheeky.
			;
		}

		return root;
	}

	// Basic helper function to get
	// recursion of delete().
	public void delete(AnyType data)
	{
		root = delete(root, data);
	}

	// Similar to insert(), delete() uses compareTo() to traverse the
	// the tree but adds an additional else that determines which child
	// to replace the parent node being deleted. 
	private Node<AnyType> delete(Node<AnyType> root, AnyType data)
	{
		if (root == null)
		{
			return null;
		}
		else if (data.compareTo(root.data) < 0)
		{
			root.left = delete(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			root.right = delete(root.right, data);
		}
		else
		{
			if (root.left == null && root.right == null)
			{
				return null;
			}
			else if (root.left == null)
			{
				return root.right;
			}
			else if (root.right == null)
			{
				return root.left;
			}
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private AnyType findMax(Node<AnyType> root)
	{
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}

	// Basic helper function to get
	// recursion of contains().
	public boolean contains(AnyType data)
	{
		return contains(root, data);
	}

	// Using compareTo() to determine if the BST contains
	// a specific data type which can be AnyType.
	private boolean contains(Node<AnyType> root, AnyType data)
	{
		if (root == null)
		{
			return false;
		}
		else if (data.compareTo(root.data) < 0)
		{
			return contains(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			return contains(root.right, data);
		}
		else
		{
			return true;
		}
	}

	// Print statements in form of inorder, 
	// postorder, or preorder by using recursion
	// and helper function. 
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}

	private void inorder(Node<AnyType> root)
	{
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}

	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	private void preorder(Node<AnyType> root)
	{
		if (root == null)
			return;

		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}

	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}

	private void postorder(Node<AnyType> root)
	{
		if (root == null)
			return;

		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}

	// Standard difficulty and hours spent
	// the details how the assignment was. 
	public static double difficultyRating()
	{
		return 2.0;
	}

	public static double hoursSpent()
	{
		return 2.0;
	}
	
}
