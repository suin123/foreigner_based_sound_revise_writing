import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HangulDivider {
    
 
    public int[] getLetterPlace(char c)
    {
        int place[] = new int[3];
        //0xAC00  == '가'
        place[0] = (c - 0xAC00) / (21*28); //초성의 위치
        place[1] = ((c - 0xAC00) % (21*28)) / 28; //중성의 위치
        place[2] = (c - 0xAC00) % (28);//종성의 위치
        
        return place;
    } 
    
    public char[] combineHangul(Letter l)
    {    	
        char[] ch = new char[1];
        ch[0] = (char) (0xAC00 + (l.getInitial()*21*28) + (l.getMedial()*28) + l.getFinal());
        return ch;
    }
    
    public List<Letter> executeDivider(String msg)
    {
    	List<Letter> letterArray = new ArrayList<Letter>();
    	
    	char letter;
    	
    	int length = msg.length();

    	for(int i=0; i< length; i++)
    	{
    		int[] letterPosition = null;
    		letter = msg.charAt(i);
    		
    		if(letter >= 0xAC00)
    		{
    			letterPosition = getLetterPlace(letter);
    			int initalLetter = letterPosition[0];
    			int medialLetter =letterPosition[1];
    			int finalLetter = letterPosition[2];
    			
    			Letter lt = new Letter(initalLetter, medialLetter, finalLetter);
    			letterArray.add(lt);
    		}
    		else
    		{
    			Letter lt = new Letter(letter);
    			letterArray.add(lt);
    		}
    	}
    	
    	return letterArray;
    }
    
    public String executeCombiner(List<Letter> letterArray)
    {
    	int length = letterArray.size();
    	String sentence = "";
    	
    	for(int i=0; i<length; i++)
    	{
    		Letter l = letterArray.get(i);
    		
    		if(l.getInitial() != -1)
    		{	
	    		String msg = new String(combineHangul(l));
	    		sentence = sentence +msg;
    		}
    		else
    		{
    			sentence  = sentence + l.getSpecial();
    		}
    	}
    	return sentence;
    }
    
    public void printLetters(List<Letter> al)
    {
		for(int i=0; i<al.size(); i++)
		{
			Letter l = al.get(i);
			
			if(l.getInitial() != -1)
			{
				System.out.print("초성" + Hangul.InitialSound[l.getInitial()]);
				System.out.print("\t중성" + Hangul.MedialSound[l.getMedial()]);
				System.out.println("\t종성" + Hangul.FinalSound[l.getFinal()]);
			}
			else
			{
				System.out.println("한글이 아닌 말 : " + l.getSpecial());
			}
		}
    }
}
