import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class IthubScrawler {

	
	public void IthubScrawl()
	{
		
		Document doc;
		try {
			//2015pwgb423eydvk001286
			
			doc = Jsoup.connect("http://ithub.korean.go.kr/user/total/database/corpusManager.do").get();
			Elements e = doc.select("body");
			
			Elements sentence = doc.select(".lf");//트위터 내용
			
			for(int i=0; i<sentence.size(); i++)
			{
				String text = sentence.get(i).text().trim();
				System.out.println(text);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
