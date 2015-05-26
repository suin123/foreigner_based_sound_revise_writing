
public interface Hangul {
	
	// ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ 		6
	// ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ			12
	// ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ			18
	final char[] InitialSound = 
    {
    	0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 
        0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 
        0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e 
    };
		     
    // ㅏㅐㅑㅒㅓㅔㅕ			6
	// ㅖㅗ ㅘ ㅙ ㅚ ㅛ			12
	// ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ			18
	// ㅢ ㅣ					20
    final char[] MedialSound = 
    {
    	0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 
        0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 
        0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161,    
        0x3162, 0x3163 
    };
		 
    // ' ' ㄱㄲㄳㄴㄵㄶ 7		6
    //ㄷㄹㄺ ㄻ ㄼ ㄽ 6			12
    //ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ 6			18
    //ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ 6			24
    //ㅌ ㅍ ㅎ 3				27
    final char[] FinalSound  = 
    {
    	0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 
        0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 
        0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 
        0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 
        0x314c, 0x314d, 0x314e
    };

    //23항 받침
    final int[] support23List = {1,2,3,7,9,11,14,17,18,19,20,22,23,24,25,26};
    
    //24항 받침
    final int[] support24List = {4,5,16,10};
    
    //18항 받침
    final int[] kieick18List = {1,2,24,3,9};	//ㄱ ㄲ ㅋ ㄳ ㄺ
    final int[] digen18List = {7,19,20,22,23,25,27};	//ㄷ ㅅ ㅆ ㅈ ㅊ ㅌ ㅎ
    final int[] bieb18List = {17,18,11,24,26};	//ㅂ ㅍ ㄼ ㄿ ㅄ
    
    //17항 받침
    final int[] digenk17List = {7};	//ㄷ
    final int[] tigek17List = {14,25};		//ㅌ ㄾ
    
    //15항 대표음
    final int[] gieick15List = {1,2,24,3};	//ㄱ ㄲ ㄳ ㅋ
    final int[] nien15List = {4,5,6};		//ㄴ ㄵ ㄶ
    final int[] digenk15List = {7,19,20,22,23,25};		//ㄷ ㅅ ㅆ ㅈ ㅊ ㅌ
    final int[] lieulk15List = {8,9,10,11,12,13,14,15};		//ㄹ ㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ
    final int[] miem15List = {16};		//ㅁ
    final int[] bieb15List = {17,18};	//ㅂ ㅄ
    final int[] eong15List = {21};		//ㅇ
    
    //14항 받침
    final int[] kieick14List = {9};//ㄺ
    final int[] bieb14List = {11};//ㄼ
    final int[] miem14List = {10};//ㄻ
    final int[] siock14List = {3,12,18};//ㄳ ㄽ ㅄ
    final int[] giockList = {5};//ㄵ
    final int[] tigek14List = {13};//ㄾ
    final int[] pieck14List = {14};//ㄿ
    final int[] hieck14List = {6,15};//ㅀ ㄶ
    
    //11항 받침
    final int[] gieick11List = {1,9};	//ㄱ ㄺ
    final int[] mieum11List = {10,16};	//ㅁ ㄻ
    final int[] bibenk11List = {17,11};	//ㅂ ㄼ
   
    //10항 받침List15
    final int[] gieick10List = {1,3};	//ㄱ ㄳ
    final int[] nien10List = {4,5};	//ㄴ ㄵ
    final int[] lieulk10List = {8,11,12,13};	//ㄹ	ㄼ ㄽ ㄾ
    final int[] bibenk10List = {17,18};	//ㅂ	ㅄ
    
	//9항 받침List
    final int[] gieick9List = {1,2,24};	//ㄱ ㄲ ㅋ
    final int[] digenk9List = {7,19,20,22,23,25};	//ㄷ ㅅ ㅆ ㅈ ㅊ ㅌ
    final int[] bibenk9List = {17,26};	//ㅂ ㅠ
    
    //12항 받침List
    final int[] hieih12List = {27, 6, 15}; //ㅎ ㄶ ㅀ
    final int[] gien12List = {4,5}; //ㄴ ㄵ
    final int[] hieih12List2 = {6}; //ㄶ
    final int[] hieih12List3 = {15};//ㅀ
    final int[] hieih12List4 = {27};//ㅎ
 

}
