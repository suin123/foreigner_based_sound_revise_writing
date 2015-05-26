import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.*;

public class reverseApplyArticle implements Hangul{

	ArrayList<Candidate> CandiWordList;
	
	public reverseApplyArticle(ArrayList<List<Letter>> SupportWordList)
	{
		CandiWordList = new ArrayList<Candidate>();
		
		for(int i=0; i<SupportWordList.size(); i++)
		{
			Candidate candi = new Candidate(SupportWordList.get(i));
			CandiWordList.add(candi);
		}
	}

	public void writeCandiList()
	{
		try
		{
			FileWriter fw = new FileWriter("reverse.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			HangulDivider hd= new HangulDivider();
			for(int i=0; i<CandiWordList.size(); i++)
			{
				Candidate candi = CandiWordList.get(i);
				bw.write(i + "번재 CandWordList");
				bw.newLine();
				if(candi.getCandiList().size() == 0)
				{
					//bw.write("입력 단어 : ");
					//hd.printLetters(candi.getOriginalLetter());
					String msg = hd.executeCombiner(candi.getOriginalLetter());
					bw.write("원래 단어 : "+ msg);
					bw.newLine();
					bw.write("후보 없음");
					bw.newLine();
				}
				else
				{
					bw.write("입력 단어 : ");
					bw.newLine();
					hd.printLetters(candi.getOriginalLetter());
					String msg = hd.executeCombiner(candi.getOriginalLetter());
					bw.write("원래 단어 : "+ msg);
					bw.newLine();
					bw.write("후보 개수 :  " + candi.getCandiList().size());
					bw.newLine();
					
					for(int j=0; j<candi.getCandiList().size(); j++)
					{
						String tmp = hd.executeCombiner(candi.getCandiList().get(j));
						bw.write("후보 단어 : " + tmp);
						bw.newLine();
					}
					bw.newLine();
				}
			}
			bw.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
	
	public void printCandiList()
	{
		HangulDivider hd= new HangulDivider();
		for(int i=0; i<CandiWordList.size(); i++)
		{
			Candidate candi = CandiWordList.get(i);
			System.out.println(i + "번재 CandWordList");
			
			if(candi.getCandiList().size() == 0)
			{
				System.out.print("원래 단어 : ");
				hd.printLetters(candi.getOriginalLetter());
				String msg = hd.executeCombiner(candi.getOriginalLetter());
				System.out.println("원래 단어 : "
				+ msg);
				System.out.println("후보 없음 ");
			}
			else
			{
				System.out.print("원래 단어 : ");
				hd.printLetters(candi.getOriginalLetter());
				System.out.println("후보 개수 :  " + candi.getCandiList().size());
				for(int j=0; j<candi.getCandiList().size(); j++)
				{
		//			hd.printLetters(candi.getCandiList().get(j));
					String msg = hd.executeCombiner(candi.getCandiList().get(j));
					System.out.println("후보 단어 : " + msg);
				}
				System.out.println();
			}
		}
	}
	
	public void executeArticle()
	{
		int size = CandiWordList.size();
		
		Queue<List<Letter>> que = new LinkedList<List<Letter>>();
		Queue<List<Letter>> candiQue = new LinkedList<List<Letter>>();
		
		for(int i=0; i<size; i++)
		{
			que.clear();
			List<Letter> list = CandiWordList.get(i).getOriginalLetter();
			
			for(int j=0; j < list.size(); j++)
			{
				Letter originalLetter = list.get(j);
				int finalSound = originalLetter.getFinal();
				
				
				//12항 'ㅎ'은 특수한 경우가 많다
				System.out.println("================ 12항  ================");
				twelevenArticleExecute(que, candiQue,originalLetter);
				System.out.println("================ 13항  ================");
				thirteenArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 14항  ================");
				FourteenArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 15항  ================");
				fifteenArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 17항  ================");
				seventeenArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 18항  ================");
				eightteenArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 19항  ================");
				nineteenArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 20항  ================");
				twentyArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 23항  ================");
				twentyThreeArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 24항  ================");
				twentyFourArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 25항  ================");
				twentyFiveArticleExecute(que, candiQue, originalLetter);
				System.out.println("================ 26항  ================");
				twentySixArticleExecute(que, candiQue, originalLetter);
//				System.out.println("================ 27항  ================");
	//			twentySevenArticleExecute(que, candiQue, originalLetter);
				
				if((j == list.size()-1 && finalSound != 0)||(j < list.size() && finalSound != 0 && 
						list.get(j+1).getInitial() != 11))//어말이거나, 받침뒤 초성 자음이 올경우
				{	
					System.out.println("================ 9항 ================");
					nineArticleExecute(finalSound,que, candiQue,originalLetter);
					System.out.println("================ 10항  ================");
					tenArticleExecute(finalSound,que, candiQue,originalLetter);
					System.out.println("================ 11항  ================");
					elevenArticleExecute(finalSound,que, candiQue,originalLetter);
					
					if(que.size() == 0 && candiQue.size() == 0)
					{
						Letter letter = new Letter();
						letter.copyLetter(originalLetter);
						List<Letter> letterList = new ArrayList<Letter>();
						letterList.add(letter);
						que.add(letterList);
					}
					else if(que.size() == 0 && candiQue.size() != 0 && candiQue.peek().size() == 1)
					{
						que.clear();
						que.addAll(candiQue);
						candiQue.clear();
						Letter letter = new Letter();
						letter.copyLetter(originalLetter);
						List<Letter> letterList = new ArrayList<Letter>();
						letterList.add(letter);
						que.add(letterList);
					}
					else
					{
						que.clear();
						que.addAll(candiQue);
						candiQue.clear();
					}
				}
				else
				{
					int queSize = que.size();
					if(queSize == 0 && candiQue.size() == 0)
					{
						Letter letter = new Letter();
						letter.copyLetter(originalLetter);
						List<Letter> letterList = new ArrayList<Letter>();
						letterList.add(letter);
						que.add(letterList);
					}
					else if(candiQue.size() !=0)
					{
						que.clear();
						que.addAll(candiQue);
						candiQue.clear();
					}
					else
					{
						for(int k=0; k<queSize; k++)
						{
							Letter letter = new Letter();
							letter.copyLetter(originalLetter);
							List<Letter> letterList = que.poll();
							letterList.add(letter);
							que.add(letterList);	
						}
					}
				}
			}

			while(!que.isEmpty())
			{
				CandiWordList.get(i).addCandiList(que.poll());
			}
			
		}
	}

	
	
/*	public void twentySevenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			if(finalSound == 8 && !(finalSound == originalLetter.getFinal() && initialSound == letter.getInitial() && letter.getMedial() == originalLetter.getMedial()))
			{				
				switch(initialSound)
				{
				case 4://ㄸ
					insertCandiListL(candiQue, list, letter, originalLetter, 4, 8);
					insertCandiListL(candiQue, list, letter, originalLetter, 3, 8);
					break;
				case 10:	//ㅆ
					insertCandiListL(candiQue, list, letter, originalLetter, 9, 8);
					insertCandiListL(candiQue, list, letter, originalLetter, 9, 8);
					break;
				case 13:	//ㅉ
					insertCandiListL(candiQue, list, letter, originalLetter, 12, 8);
					insertCandiListL(candiQue, list, letter, originalLetter, 12, 8);
					break;
				}
			}
			que.add(list);
		}
	}*/
	
	public void twentySixArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			if(finalSound == 8 && !(finalSound == originalLetter.getFinal() && initialSound == letter.getInitial() && letter.getMedial() == originalLetter.getMedial()))
			{				
				switch(initialSound)
				{
				case 4://ㄸ
					insertCandiListL(candiQue, list, letter, originalLetter, 4, 8);
					insertCandiListL(candiQue, list, letter, originalLetter, 3, 8);
					break;
				case 10:	//ㅆ
					insertCandiListL(candiQue, list, letter, originalLetter, 9, 8);
					insertCandiListL(candiQue, list, letter, originalLetter, 9, 8);
					break;
				case 13:	//ㅉ
					insertCandiListL(candiQue, list, letter, originalLetter, 12, 8);
					insertCandiListL(candiQue, list, letter, originalLetter, 12, 8);
					break;
				}
			}
			que.add(list);
		}
	}
	
	public void twentyFiveArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			if(finalSound == 8)
			{				
				switch(initialSound)
				{
				case 1://ㄲ
					insertCandiListL(candiQue, list, letter, originalLetter, 0, 11);
					insertCandiListL(candiQue, list, letter, originalLetter, 0, 13);
					break;
				case 4://ㄸ
					insertCandiListL(candiQue, list, letter, originalLetter, 3, 11);
					insertCandiListL(candiQue, list, letter, originalLetter, 3, 13);
					break;
				case 10:	//ㅆ
					insertCandiListL(candiQue, list, letter, originalLetter, 9, 11);
					insertCandiListL(candiQue, list, letter, originalLetter, 9, 13);
					break;
				case 13:	//ㅉ
					insertCandiListL(candiQue, list, letter, originalLetter, 12, 11);
					insertCandiListL(candiQue, list, letter, originalLetter, 12, 13);
					break;
				}
			}
			que.add(list);
		}
	}
	
	public void twentyFourArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{
		int medianSound = originalLetter.getMedial();
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			
			if(isExistList(finalSound, support24List))
			{				
					if(finalSound == 4 && initialSound == 1 && medianSound != 20)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 0, 4);
						insertCandiListL(candiQue, list, letter, originalLetter, 0, 5);
					}
					if(finalSound == 16 && initialSound == 1 && medianSound != 20)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 0, 16);
						insertCandiListL(candiQue, list, letter, originalLetter, 0, 10);
					}
					if(finalSound == 4&& initialSound == 4)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 3, 4);
						insertCandiListL(candiQue, list, letter, originalLetter, 3, 5);
					}
					if(finalSound == 16 && initialSound == 4)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 3, 16);
						insertCandiListL(candiQue, list, letter, originalLetter, 3, 10);
					}
					if(finalSound == 4&& initialSound == 10)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 9, 4);
						insertCandiListL(candiQue, list, letter, originalLetter, 9, 5);
					}
					if(finalSound == 16 && initialSound == 10)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 9, 16);
						insertCandiListL(candiQue, list, letter, originalLetter, 9, 10);
					}
					if(finalSound == 4&& initialSound == 13)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 12, 4);
						insertCandiListL(candiQue, list, letter, originalLetter, 12, 5);
					}
					if(finalSound == 16 && initialSound == 13)
					{
						insertCandiListL(candiQue, list, letter, originalLetter, 12, 16);
						insertCandiListL(candiQue, list, letter, originalLetter, 12, 10);
					}
				
			}
			que.add(list);
		}
	}
	
	public void twentyThreeArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			if(isExistList(finalSound, support23List))
			{				
				switch(initialSound)
				{
				case 1://ㄲ
					insertCandiListH(candiQue, list, letter, originalLetter, 0, kieick18List);
					insertCandiListH(candiQue, list, letter, originalLetter, 0, digenk15List);
					insertCandiListH(candiQue, list, letter, originalLetter, 0, bieb18List);
					break;
				case 4://ㄸ
					insertCandiListH(candiQue, list, letter, originalLetter, 3, kieick18List);
					insertCandiListH(candiQue, list, letter, originalLetter, 3, digenk15List);
					insertCandiListH(candiQue, list, letter, originalLetter, 3, bieb18List);
					break;
				case 8: //ㅃ
					insertCandiListH(candiQue, list, letter, originalLetter, 7, kieick18List);
					insertCandiListH(candiQue, list, letter, originalLetter, 7, digenk15List);
					insertCandiListH(candiQue, list, letter, originalLetter, 7, bieb18List);
					break;
				case 10:	//ㅆ
					insertCandiListH(candiQue, list, letter, originalLetter, 9, kieick18List);
					insertCandiListH(candiQue, list, letter, originalLetter, 9, digenk15List);
					insertCandiListH(candiQue, list, letter, originalLetter, 9, bieb18List);
					break;
				case 13:	//ㅉ
					insertCandiListH(candiQue, list, letter, originalLetter, 12, kieick18List);
					insertCandiListH(candiQue, list, letter, originalLetter, 12, digenk15List);
					insertCandiListH(candiQue, list, letter, originalLetter, 12, bieb18List);
					break;
				}
			}
			que.add(list);
		}
	}
	
	public boolean isExistList(int finalSound, int[] list)
	{
		for(int i=0; i<list.length; i++)
		{
			if(list[i] == finalSound)
			{
				return true;
			}
		}
		return false;
	}
	
	public void twentyArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			if(finalSound == 8 && initialSound == 5)
			{	
				insertCandiListL(candiQue, list, letter, originalLetter, 5, 4);
				insertCandiListL(candiQue, list, letter, originalLetter, 2, 8);
				insertCandiListL(candiQue, list, letter, originalLetter, 2, 15);
				insertCandiListL(candiQue, list, letter, originalLetter, 2, 13);
			}
			que.add(list);
		}
	}
	
	public void nineteenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			
			if(initialSound == 2)
			{	
				switch(finalSound)
				{
				case 16://ㅁ
					insertCandiListL(candiQue, list, letter, originalLetter, 5,finalSound);
					insertCandiListL(candiQue, list, letter, originalLetter, 5, 1);
					insertCandiListL(candiQue, list, letter, originalLetter, 5, 17);
					break;
				case 21://ㅇ
					insertCandiListL(candiQue, list, letter, originalLetter, 5, finalSound);
					insertCandiListL(candiQue, list, letter, originalLetter, 5, 1);
					insertCandiListL(candiQue, list, letter, originalLetter, 5, 17);
					break;
				}
			}
			que.add(list);
		}
	}
	
	public void seventeenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{
		int initialSound = originalLetter.getInitial();
		int medialSound = originalLetter.getMedial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			
			if(medialSound == 20)
			{	
				switch(initialSound)
				{
				case 12:	//ㅈ
					insertCandiListH(candiQue, list, letter, originalLetter, 11, digenk17List);
					break;
				case 14:	//ㅊ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,tigek17List);
					insertCandiListH(candiQue, list, letter, originalLetter, 18, digenk17List);
					break;
				}
			}
			que.add(list);
		}
	}
	
	public void eightteenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			if(initialSound == 2||initialSound == 6)
			{	
				switch(finalSound)
				{
				case 4://ㄴ
					insertCandiListH(candiQue, list, letter, originalLetter, initialSound, digen18List);
					break;
				case 16://ㅁ
					insertCandiListH(candiQue, list, letter, originalLetter, initialSound,bieb18List);
					break;
				case 21://ㅇ
					insertCandiListH(candiQue, list, letter, originalLetter, initialSound, kieick18List);
					break;
				}
			}
			que.add(list);
		}
	}
	
	public void fifteenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{	
		int initialSound = originalLetter.getInitial();
		int medialSound = originalLetter.getMedial();
		int queSize = que.size();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			
			if(medialSound == 0 ||medialSound == 4 ||medialSound == 8 ||medialSound == 13 ||medialSound == 16)
			{	
				switch(initialSound)
				{
				case 1:
					//ㄱ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,gieick15List);
					break;
				case 2://ㄴ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,nien15List);
					break;
				case 3://ㄷ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,digenk15List);
					break;
				case 5://ㄹ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,lieulk15List);
					break;
				case 6://ㅁ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,miem15List);
					break;
				case 7://ㅂ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,bieb15List);
					break;
				case 11://ㅇ
					insertCandiListH(candiQue, list, letter, originalLetter, 11,eong15List);
					break;
				}
			}
			que.add(list);
		}
	}
	
	public void FourteenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			
			switch(initialSound)
			{
			case 1:
				//ㄱ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,kieick14List);
				break;
			case 6://ㅁ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,miem14List);
				break;
			case 7://ㅂ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,bieb14List);
				break;
			case 10://ㅅ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,siock14List);
				break;
			case 12://ㅈ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,giockList);
				break;
			case 16://ㅌ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,tigek14List);
				break;
			case 17://ㅍ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,pieck14List);
				break;
			case 18://ㅎ
				insertCandiListH(candiQue, list, letter, originalLetter, 11,hieck14List);
				break;
		
			}
			que.add(list);
//			hd.printLetters(list);
		}
	}
	
	public void thirteenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			if(finalSound == 0)
			{
				Letter let = new Letter();
				Letter let2 = new Letter();
				let.copyLetter(originalLetter);
				let2.copyLetter(letter);
				List<Letter> temp = new ArrayList<Letter>();
				temp.addAll(list); temp.remove(temp.size()-1);
				for(int j =0; j<Hangul.FinalSound.length; j++)
				{
					if(Hangul.FinalSound[j] == Hangul.InitialSound[initialSound])
					{
						let2.setFinal(j);
						break;
					}
				}
				let.setInitial(11);
				temp.add(let2);
				temp.add(let);
				candiQue.add(temp);
			}
			
			que.add(list);
		}
	}
	public void twelevenArticleExecute(Queue<List<Letter>> que, Queue<List<Letter>> candiQue, Letter originalLetter)
	{
		int initialSound = originalLetter.getInitial();
		int queSize = que.size();
		
		HangulDivider hd= new HangulDivider();
		for(int i=0; i<queSize; i++)
		{
			List<Letter> list = que.poll();
			Letter letter = list.get(list.size()-1);
			int finalSound = letter.getFinal();
			
			switch(initialSound)
			{
			case 15:
				//12항 1절 ㅋ		//12항 붙임 1
				insertCandiListH(candiQue, list, letter, originalLetter, 1, Hangul.hieih12List);
				insertCandiListH(candiQue, list, letter, originalLetter, 18, Hangul.gieick11List);
				break;
			case 16:
				//12항 1절 ㅌ		//12항 붙임2,붙임1
				insertCandiListH(candiQue, list, letter, originalLetter, 4, Hangul.hieih12List);
				insertCandiListH(candiQue, list, letter, originalLetter, 18, Hangul.digenk9List);
				break;
			case 14:
				//12항 1절 ㅊ		//12항 붙임1
				insertCandiListH(candiQue, list, letter, originalLetter, 14, Hangul.hieih12List);
				insertCandiListH(candiQue, list, letter, originalLetter, 18, Hangul.gien12List);
				break;
			case 17:
				//12항 1절 붙임1 ㅍ
				insertCandiListH(candiQue, list, letter, originalLetter, 18, Hangul.bibenk11List);
				break;
			case 10:
				//12항 2절
				insertCandiListH(candiQue, list, letter, originalLetter, 9, Hangul.hieih12List);
				break;
			case 2 :	//12항 3절		12항 붙임
				if(finalSound == 4)
				{
					insertCandiListH(candiQue, list, letter, originalLetter, 2, Hangul.hieih12List2);
					insertCandiListH(candiQue, list, letter, originalLetter, 2, Hangul.hieih12List4);
				}
				else if(finalSound ==0)	//12항 4절
				{
				insertCandiListH(candiQue, list, letter, originalLetter, 11, Hangul.hieih12List2);
				}
				break;
			case 5:	//12항 3절 붙임
				if(finalSound == 11)
				{
					insertCandiListH(candiQue, list, letter, originalLetter, 2, Hangul.hieih12List3);
				}
				else if(finalSound ==0)
				{
				insertCandiListH(candiQue, list, letter, originalLetter, 11, Hangul.hieih12List3);
				}
				break;
			case 11 :
				if(finalSound ==0)
				{
				insertCandiListH(candiQue, list, letter, originalLetter, 11, Hangul.hieih12List4);
				}
				break;
			}
			int[] originalSupport = {letter.getFinal()};
			insertCandiListH(candiQue, list, letter, originalLetter, originalLetter.getInitial(), originalSupport);
			
			que.add(list);
			
		}
	}
	
	public void insertCandiListH(Queue<List<Letter>> candiQue,List<Letter> list,Letter letter,Letter originalLetter,int initialSound, int[] hangulList)
	{
		for(int j=0; j<hangulList.length;j++)
		{
			
			Letter let = new Letter();
			Letter let2 = new Letter();
			let.copyLetter(originalLetter);
			let2.copyLetter(letter);
			List<Letter> temp = new ArrayList<Letter>();
			temp.addAll(list); temp.remove(temp.size()-1);
			let2.setFinal(hangulList[j]);
			let.setInitial(initialSound);
			temp.add(let2);
			temp.add(let);
			candiQue.add(temp);
		}
	}
	public void insertCandiListL(Queue<List<Letter>> candiQue,List<Letter> list,Letter letter,Letter originalLetter,int initialSound, int finalLetter)
	{
			
			Letter let = new Letter();
			Letter let2 = new Letter();
			let.copyLetter(originalLetter);
			let2.copyLetter(letter);
			List<Letter> temp = new ArrayList<Letter>();
			temp.addAll(list); temp.remove(temp.size()-1);
			let2.setFinal(finalLetter);
			let.setInitial(initialSound);
			temp.add(let2);
			temp.add(let);
			candiQue.add(temp);
	}
	

	
	public void elevenArticleExecute(int finalSound, Queue que,Queue candiQue, Letter originalLetter)
	{
		if(finalSound == 1 || finalSound == 9)//받침이 ㄱ ㄳ
		{        
			insertCandiQueue(que, candiQue,originalLetter, Hangul.gieick9List);
		}
		if(finalSound == 10 ||finalSound == 16)	//받침이 ㅁ ㄻ
		{
			insertCandiQueue(que, candiQue,originalLetter, Hangul.digenk9List);
		}
		if(finalSound == 17||finalSound == 11)	//받침이 ㅂ ㄿ
		{
			insertCandiQueue(que,candiQue, originalLetter, Hangul.bibenk9List);
		}
	}
	
	public void tenArticleExecute(int finalSound, Queue que, Queue candiQue,Letter originalLetter)
	{

		if(finalSound == 1 || finalSound == 3)//받침이 ㄱ. ㄳ
		{        
			insertCandiQueue(que,candiQue, originalLetter, Hangul.gieick10List);
		}
		if(finalSound == 4 ||finalSound == 5)//받침이 ㄴ ㄵ
		{
			insertCandiQueue(que,candiQue, originalLetter, Hangul.nien10List);
		}
		if(finalSound == 8||finalSound == 11||finalSound == 12||finalSound == 13)	//받침이 ㄹ, ㄼ, ㄽ, ㄾ
		{
			insertCandiQueue(que, candiQue,originalLetter, Hangul.lieulk10List);
		}
		if(finalSound == 17||finalSound == 18)	//받침이 ㅂ, ㅄ
		{
			insertCandiQueue(que, candiQue,originalLetter, Hangul.bibenk10List);
		}
				
	}
	
	public void nineArticleExecute(int finalSound, Queue que, Queue candiQue,Letter originalLetter)
	{
		if(finalSound == 1 || finalSound == 2||finalSound == 24)//받침이 ㄱ , ㄲ, ㅋ
		{        
			insertCandiQueue(que,candiQue, originalLetter, Hangul.gieick9List);
		}
		if(finalSound == 7 ||finalSound == 19 ||finalSound == 20 ||	//받침이 ㅅ, ㅆ, ㅈ, ㅊ, ㅌ, ㄷ
				finalSound == 22||finalSound == 23||finalSound == 25)
		{
			insertCandiQueue(que,candiQue, originalLetter, Hangul.digenk9List);
		}
		if(finalSound == 17||finalSound == 26)	//받침이 ㅂ, ㅍ
		{
			insertCandiQueue(que,candiQue, originalLetter, Hangul.bibenk9List);
		}
	}
	
	public void insertCandiQueue(Queue<List<Letter>> que, Queue candiQue,Letter originalLetter, int[] finalSound)
	{
		int queSize = que.size();
		if(queSize != 0)
		{
			for(int k = 0; k<queSize; k++)
			{
				List<Letter> letterList = que.poll();
				
				for(int i=0; i<finalSound.length; i++)
				{
					List<Letter> temp = new ArrayList<Letter>();
					temp.addAll(letterList);
					Letter letter = new Letter();
					letter.copyLetter(originalLetter);
					letter.setFinal(finalSound[i]);
					temp.add(letter);
					candiQue.add(temp);
				}
			}
		}
		else if(candiQue.size() != 0)
		{
			
		}
		else
		{
			for(int i=0; i<finalSound.length; i++)
			{
				Letter letter = new Letter();
				letter.copyLetter(originalLetter);
				letter.setFinal(finalSound[i]);
				List<Letter> letterList = new ArrayList<Letter>();
				letterList.add(letter);
				candiQue.add(letterList);
			}
		}
	}
}
