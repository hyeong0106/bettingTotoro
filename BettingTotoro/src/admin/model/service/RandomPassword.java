package admin.model.service;

public class RandomPassword {
   public String randomPassword (int lenght) {
	int index =0;
	char[] charSet = new char[] {
		'0','1','2','3','4','5','6','7','8','9','0','A','B','C',
		'D','E','F','G','H','I','J','K','L','N','M','O','P','Q',
		'R','S','T','U','V','W','X','Y','Z','a','b','c','d','e',
		'f','g','h','i','j','k','l','n','m','o','p','q','r','x',
		't','u','v','w','x','y','z'
	};
	StringBuffer sb =new StringBuffer();
	for(int i=0; i<lenght; i++) {
		index = (int)(charSet.length * Math.random());
		sb.append(charSet[index]);
	}
	   
	   return sb.toString();
	     
   }
 
}