public class Pair {
	public int Aindex;
	public int Bindex;
	public Pair(int str1, int str2){
		this.Aindex = str1;
		this.Bindex = str2;
	}

	public String toString(){
	  return  "<" + Aindex + ", " + Bindex + ">"; 
	}
	public boolean equals(Pair t) {
        if(this.Aindex == t.Aindex && this.Bindex == t.Bindex)
        	return true;
        return false;
    }



}