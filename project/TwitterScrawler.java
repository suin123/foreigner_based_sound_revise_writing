import java.util.List;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;


public class TwitterScrawler {
	private final String ACCESSTOKEN = "183920252-e1Ds1Qs2cM0fIKQu6JUJpC0dJJIhnPanmdVDMDHV";
	private final String ACCESSSECRET = "PW4KPOZoaVNjXkoXqbcAakd3ksy6HBBP7K7hMzwRJApzI";
	private final String REQUESTURL = "https://api.twitter.com/oauth/request_token";
	private final String CUSTOMERKEY = "m7qIsisuv4ehO2IvRUSHaTvPk";
	private final String CUSTOMERSECRET = "3FEllq4GM56pcTfe5Ei5Zf2YAlnEqsoni2gOOoBXiJEwvPLWbk";
	private final String OWNER = "luvunme";
	private final String OWNERID = "183920252";
	
	public void scrawlTwitter()
	{
		AccessToken accesstoken = new AccessToken(ACCESSTOKEN,ACCESSSECRET);	
        Twitter twitter = new TwitterFactory().getInstance();
        DBConnector dbc = new DBConnector();
        try 
        {
        	Paging page = new Paging(1, 50);
        	
        	twitter.setOAuthConsumer(CUSTOMERKEY, CUSTOMERSECRET );
        	twitter.setOAuthAccessToken(accesstoken);
        	User user = twitter.verifyCredentials();
        	
        	List<Status> list = twitter.getUserTimeline("Kangnam11", page);
        	System.out.println("타임라인 계정:"+user.getScreenName());
        	
        	for(Status status : list) 
        	{
        		System.out.println("작성자:"+status.getUser().getScreenName());
        		String text= status.getText();
        		text = splitTag(text);
        		System.out.println("타임라인내용:"+text);
        		dbc.insertQuery(text, "japan");
        		
        	}
        	System.out.println(list.size());
        	dbc.ExitDB();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public String splitTag(String text)
	{
		if(text.contains("pic.twitter.com/"))
		{
			text = text.substring(0, text.indexOf("pic.twitter.com/"));
		}
		if(text.contains("http://"))
		{
			text = text.substring(0, text.indexOf("http://"));
		}
		if(text.contains("@"))
		{
			String[] temp = text.split(" ");
			String tmp = "";
			for(int i=0; i<temp.length; i++)
			{
				if(temp[i].contains("@"))
				{
					temp[i] = "";
				}
				tmp = tmp + " " +temp[i];
			}
			text = tmp;
		}
		
		return text;
	}
	
}
