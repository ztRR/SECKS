package meic.password_manager_server;

import java.security.Key;
import java.security.KeyStore;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PMWServer_Interface {
	 
	 @WebMethod void register(Key publicKey);
	 @WebMethod void put(Key publicKey, byte[] domain, byte[] username, byte[] password);
	 @WebMethod byte[] get(Key publicKey, byte[] domain, byte[] username);
	
}
