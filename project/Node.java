
public class Node {
	private Letter data;
	private int count;
	private Node[] linkList;
	
	public Node(Letter iData, int size)
	{
		linkList = new Node[size];
		data = iData;
	}
	
	public int getCount()
	{
		return count;
	}

	public void setCount(int iCount)
	{
		count = iCount;
	}

	public Letter getData() {
		return data;
	}

	public void setData(Letter data) {
		this.data = data;
	}

	public Node getLinkList(int i) {
		return linkList[i];
	}

	public void setLinkList(int i, Node node) {
		this.linkList[i] = node;
	}
}
