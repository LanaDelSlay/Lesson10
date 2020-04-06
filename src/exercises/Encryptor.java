package exercises;

public class Encryptor {

	public static String encrypt(String encryptMe) {
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < encryptMe.length(); i++) {
			int numericalValue = encryptMe.charAt(i);
			numericalValue+=2;
			builder.append((char) numericalValue);
		}
		return builder.toString();
	}
	
	public String decrypt(String word) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			int numericalValue = word.charAt(i);
			numericalValue-=2;
			builder.append((char) numericalValue);
			System.out.println(builder.toString());
		} return builder.toString();
	}

}
