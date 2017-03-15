package meic.sec;

import java.security.Key;
import java.security.KeyStore;
import javax.jws.*;

@WebService
public interface PWMLibrary {
	
	 @WebMethod void init(KeyStore ks);
	 @WebMethod void register_user();
	 @WebMethod byte[] save_password(byte[] domain, byte[] username, byte[] password);
	 @WebMethod byte[] retrieve_password(Key publicKey, byte[] domain, byte[] username);
	 
	
	
	
	
	
	
	
	
	
	
	
}
