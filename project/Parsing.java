import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parsing{
	
	public void gangNamTwitterParse()
	{
		
	    Document doc;
		try {
			
			//http://search.twitter.com/search.json?&q=아이폰&rpp=15
			doc = Jsoup.connect("https://twitter.com/Kangnam11/search.json?&rpp=20").get();//강남 트위터 주소
			
			Elements e = doc.select("body");
			
			Elements sentence = doc.select(".ProfileTweet-text");//트위터 내용
			
			for(int i=0; i<sentence.size(); i++)
			{
				String text = sentence.get(i).text().trim();
				if(text.contains("pic.twitter.com/"))
				{
					text = text.substring(0, text.indexOf("pic.twitter.com/"));
				}
				if(text.contains("http://www.youtube.com"))
				{
					text = text.substring(0, text.indexOf("http://www.youtube.com"));
				}
				if(text.contains("http://youtu.be"))
				{
					text = text.substring(0, text.indexOf("http://youtu.be"));
				}
				if(text.contains("@"))
				{
					text = splitTag(text);
				}
						
				System.out.println(i + " : " + text);
				
			//m7qIsisuv4ehO2IvRUSHaTvPk			api key
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public String splitTag(String sentence)
	{
		String[] temp = sentence.split(" ");
		String tmp = "";
		for(int i=0; i<temp.length; i++)
		{
			if(temp[i].contains("@"))
			{
				temp[i] = "";
			}
			
			tmp = tmp + " " +temp[i];
		}
		return tmp;
	}
}
