import java.util.ArrayList;
import java.util.List;


public class Candidate {
	List<Letter> originalLetter;//원래 단어
	int frequency;//단어의 빈도수
	ArrayList<List<Letter>> candiList;//후보 단어 List
	
	public Candidate(List<Letter> origin)
	{
		originalLetter = origin;
		frequency = 0;
		candiList = new ArrayList<List<Letter>>();
	}
	
	public int getFrequency()
	{
		return frequency;
	}
	public void setFrequency(int iFrequency)
	{
		this.frequency = iFrequency;
	}

	public List<Letter> getOriginalLetter() {
		return originalLetter;
	}

	public void setOriginalLetter(List<Letter> originalLetter) {
		this.originalLetter = originalLetter;
	}

	public ArrayList<List<Letter>> getCandiList() {
		return candiList;
	}

	public void setCandiList(ArrayList<List<Letter>> candiList) {
		this.candiList = candiList;
	}
	public void addCandiList(List<Letter> candi)
	{
		candiList.add(candi);
	}
	
}
