
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * from
 * http://aub.iteye.com/blog/1131504
 * */
public class DESCoder {
	private static final String KEY_ALGORITHM = "DES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
//	private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/ISO10126Padding";
	
	public static byte[] initSecretKey() throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(56);
		SecretKey  secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}
	
	private static Key toKey(byte[] key) throws Exception{
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		SecretKey  secretKey = skf.generateSecret(dks);
		return secretKey;
	}
	
	public static byte[] encrypt(byte[] data,Key key) throws Exception{
		return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}
	
	public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
		return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}
	public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}
	public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}
	public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
		return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}
	public static byte[] decrypt(byte[] data,Key key) throws Exception{
		return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}
	public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm);
	}
	public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}
	private static String  showByteArray(byte[] data){
		if(null == data){
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for(byte b:data){
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("}");
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		byte[] key = initSecretKey();
		System.out.println("key："+ showByteArray(key));
		Key k = toKey(key);
		String data ="DES数据";
		System.out.println("加密前数据: string:"+data);
		System.out.println("加密前数据: byte[]:"+showByteArray(data.getBytes()));
		System.out.println();
		byte[] encryptData = encrypt(data.getBytes(), k);
		System.out.println("加密后数据: byte[]:"+showByteArray(encryptData));
		System.out.println("加密后数据: hexStr:"+Hex.encodeHexStr(encryptData));
		System.out.println();
		byte[] decryptData = decrypt(encryptData, k);
		System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));
		System.out.println("解密后数据: string:"+new String(decryptData));
	}
}


