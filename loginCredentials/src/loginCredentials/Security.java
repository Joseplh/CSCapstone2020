package loginCredentials;
/**
 * @Author Joseph Maxwell
 *
 *	This class is supposed to offer security features to user accounts and allow
 *	for the creation of new accounts.
 */
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
	private final static String salt="r4nd0m94r649373x7u53df0r7h354174ndi55upp053d7063r3411y10n9507h47i73n5ur35453cur3h45h";
	public String makeHash(String password) {
		String hash = "";
		if(password == null)
			return null;
		
		password = password + salt;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes(),0,password.length());
			hash = new BigInteger(1, digest.digest()).toString(16); 
		}catch(NoSuchAlgorithmException err) {
			err.printStackTrace();
		}
		return hash;
	}
}
