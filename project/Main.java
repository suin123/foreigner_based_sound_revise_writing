import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main implements Hangul{

	public static void main(String[] args)
	{
/*		System.out.println("================ 강남 트위터 ================");
		
		TwitterScrawler ts = new TwitterScrawler();
		ts.scrawlTwitter();
		
		Parsing p = new Parsing();
		p.gangNamTwitterParse();
		
		IthubScrawler is = new IthubScrawler();
		is.IthubScrawl();*/
		
		String query = "실라의 달바미 멋쪘숨니다";
		System.out.println(query);
		
	//	System.out.println("================ 형태소 분석 ================");
	
	//	MorphologicalAnalsis mla = new MorphologicalAnalsis();
	//	mla.run(query);
		
		System.out.println("================ 초성   분리 ================");
			
		HangulDivider hdivider = new HangulDivider(); //한글 초성분리기
		List<Letter> al =  hdivider.executeDivider(query); //한글 초성분리 해서 letter씩 끊어 배열을 저장
		hdivider.printLetters(al); //배열 출력
		String msg = hdivider.executeCombiner(al); //배열 합치기
		
		System.out.println("조합 : " + msg);
		
		System.out.println("================ 받침 발음 뽑기 ================");
		AdjusterSupportSound  assound = new AdjusterSupportSound();
		assound.splitSpaceWord(al);
		assound.printList();
		
		reverseApplyArticle raa = new reverseApplyArticle(assound.getSupportWordList());

		raa.executeArticle();
		raa.printCandiList();
		raa.writeCandiList();
	}
}
