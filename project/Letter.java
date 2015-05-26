
public class Letter 
{
	char Special;
	int Initial;
	int Medial;
	int Final;
	
	public Letter(int initial, int medial, int final1)
	{
		Initial = initial;
		Medial = medial;
		Final = final1;
	}
	
	public Letter(char special) {
		Initial = -1;
		Medial = -1;
		Final = -1;
		Special = special;
	}

	public Letter() {
	}

	public char getSpecial() {
		return Special;
	}

	public void setSpecial(char special) {
		Special = special;
	}
	
	public int getInitial() {
		return Initial;
	}
	public void setInitial(int initial) {
		Initial = initial;
	}
	public int getMedial() {
		return Medial;
	}
	public void setMedial(int medial) {
		Medial = medial;
	}
	public int getFinal() {
		return Final;
	}
	public void setFinal(int final1) {
		Final = final1;
	}
	
	public void copyLetter(Letter l)
	{
		this.Initial = l.getInitial();
		this.Medial = l.getMedial();
		this.Final = l.getFinal();
	}
}
