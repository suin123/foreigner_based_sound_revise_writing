import java.util.ArrayList;
import java.util.List;


public class CandidateTree {

	Node root;
	
	public CandidateTree(int size)
	{
		root = new Node(null, size);
	}
	
	public boolean isEmpty()
	{
		if(root.getData() == null)
		{
			return true;
		}
		return false;
	}
	public void printTree()
	{
		
	}
	//node의 i번째 리스트에 새로운노드삽입, 이때 size는 새로 만들어진 newNode의 리스트의 사이즈이다.
	public void insertNode(int i, Node node, Letter letter, int size, int count)
	{
		Node newNode = new Node(letter, size);
		newNode.setCount(count);
		node.setLinkList(i, newNode);
	}
	
}
