import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//받침 발음 추출해서 ArrayList<Candidate> CandiWordList에 삽입
public class AdjusterSupportSound implements Hangul{

	ArrayList<List<Letter>> SupportWordList;
	
	public AdjusterSupportSound()
	{
		SupportWordList = new ArrayList<List<Letter>>();
	}
	
	public ArrayList<List<Letter>> getSupportWordList()
	{
		return SupportWordList;
	}
	
	//리스트 출력
	public void printList()
	{
		int size = SupportWordList.size();
		
		for(int i=0; i < size; i++)
		{	
			List<Letter> let = SupportWordList.get(i);
			for(Letter l : let)
			{
				System.out.println(Hangul.InitialSound[l.getInitial()] + " + " + Hangul.MedialSound[l.getMedial()] + " + " + Hangul.FinalSound[l.getFinal()] );
			}
			System.out.println();
			
		}
	}
	
	//띄어쓰기 위주로 받침있는 단어 분리
	public void extractSupportWord(List<Letter> al)
	{
		int size = al.size();
		int spaceIndex = 0; // 띄어쓰기 index
		boolean findSupport = false;
		
		for(int i=0; i<size;i++)
		{
			Letter l =  al.get(i);
			
			if(!findSupport && l.getInitial() != -1 && l.getFinal() != 0) //받침이 있을 경우에 
			{
				findSupport = true;
			}
			if(findSupport && ((l.getInitial() == -1)||(size-1 == i))) //받침이 있는 단어를 찾았고, 현재 i가 배열의 크기거나 문자일 경우 split
			{
				 List<Letter> letList =  al.subList(spaceIndex, i);
				 SupportWordList.add(letList);
				 findSupport = false;
				 spaceIndex = i+1;
			}
			if(!findSupport && l.getInitial() == -1)
			{
				spaceIndex = i+1;
			}
		}
	}
	
	//띄어쓰기 위주로 단어 분리
	public void splitSpaceWord(List<Letter> al)
	{
		int size = al.size();
		int spaceIndex = 0;
		for(int i=0; i<size; i++)
		{
			Letter l = al.get(i);
			if(size-1 == i)
			{
				List<Letter> letList =  al.subList(spaceIndex, i+1);
				SupportWordList.add(letList);
				spaceIndex = i+1;
			}
			else if(l.getInitial() == -1)
			{
				List<Letter> letList =  al.subList(spaceIndex, i);
				SupportWordList.add(letList);
				spaceIndex = i+1;
			}
		}
	}
}
