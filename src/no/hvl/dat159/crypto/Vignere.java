/**
 * 
 */
package no.hvl.dat159.crypto;

/**
 * @author tdoy
 *
 */
public class Vignere {

	private char[][] encoder;			// encryption array
	private char[][] decoder;			// decryption array
	public static int keyLength;
	/**
	 * 
	 */
	public Vignere(char[] key) {
		encoder = new char[key.length][26];
		decoder = new char[key.length][26];
		for(int i=0; i<26; i++) {
			for(int j=0; j<key.length; j++) {
				encoder[j][i] = (char) ('A' + (i + (key[j] - 'A')) % 26);
				decoder[j][i] = (char) ('A' + (i - (key[j] - 'A') + 26) % 26);
			}
		}
		keyLength = key.length;
	}
	
	public void showEncoder() {
		for(int j=0;j<keyLength;j++) {
			for(int i=0;i<26;i++) {
				System.out.print(encoder[j][i]);
			}
			
			System.out.println();
		}
	}

	public void showDecoder() {
		for(int j=0;j<keyLength;j++) {
			for(int i=0;i<26;i++) {
				System.out.print(decoder[j][i]);
			}
			
			System.out.println();
		}
	}
	
	
	/**
	 * 
	 * @param plaintext
	 * @return
	 */
	public String encrypt(String plaintext) {

		return operation(plaintext, encoder);
	}

	/**
	 * 
	 * @param ciphertext
	 * @return
	 */
	public String decrypt(String ciphertext) {

		return operation(ciphertext, decoder);
	}

	/**
	 * 
	 * @param input
	 * @param code
	 * @return
	 */
	private String operation(String input, char[][] code) {

		
		char[] msg = input.toCharArray();
		
		for(int i=0; i<msg.length; i++) {
			if(Character.isUpperCase(msg[i])) {
				int col = msg[i] - 'A';
				msg[i] = code[i%keyLength][col];
			}
		}

		return new String(msg);
	}

	/**
	 * @return the encoder
	 */
	public char[][] getEncoder() {
		return encoder;
	}

	/**
	 * @return the decoder
	 */
	public char[][] getDecoder() {
		return decoder;
	}
	
	
	public static void main(String[] args) {
		String key = "NULE";
		Vignere v = new Vignere(key.toCharArray());
		System.out.println("-------------- Encoder --------------- ");
		v.showEncoder();
		System.out.println("-------------- Decoder -------------- ");
		v.showDecoder();
		System.out.println("-------------- TESTS ---------------- ");
		String msg = "ATTACKATDAWN";
		System.out.println("msg: "+msg);
		System.out.println("key: "+key);
		String encypted = v.encrypt(msg);
		String decrypted = v.decrypt(encypted);
		System.out.println("Encryption: "+encypted);
		System.out.println("Decryption: "+decrypted);
	}

}
