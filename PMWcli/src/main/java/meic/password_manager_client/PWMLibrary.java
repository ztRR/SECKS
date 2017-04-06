package meic.password_manager_client;

import java.security.Key;
import java.security.KeyStore;
import javax.jws.*;

@WebService
public interface PWMLibrary {
	
	 @WebMethod void init(KeyStore k,char[] pass) throws Exception;
	 @WebMethod void register_user();
	 @WebMethod void save_password(byte[] domain, byte[] username, byte[] password);
	 @WebMethod byte[] retrieve_password(Key publicKey, byte[] domain, byte[] username);
	 
	
	
	
	
	
	
	
	
	
	
	
}
