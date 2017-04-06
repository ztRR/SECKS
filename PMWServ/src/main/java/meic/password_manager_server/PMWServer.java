package meic.password_manager_server;

import java.security.Key;
import java.security.KeyStore;
import java.util.Hashtable;

import javax.jws.WebService;
import javax.xml.ws.handler.MessageContext;

@WebService(endpointInterface="password_manager_server.PMWServer_Interface")

public class PMWServer implements PMWServer_Interface  {
	Hashtable<Key, Hashtable> _users;
	Hashtable<String, String> _passes;
	
	public PMWServer(){
		_users = new Hashtable<Key, Hashtable>();
	}
	
	public void register(Key publicKey) {
		_users.put(publicKey, new Hashtable<String, byte[]>());

	}

	public void put(Key publicKey, byte[] domain, byte[] username, byte[] password) {
		String d = new String(domain);
		String u = new String(username);
		_users.get(publicKey).put(d, password);
	}

	public byte[] get(Key publicKey, byte[] domain, byte[] username) {
		return (byte[]) _users.get(publicKey).get(new String(domain));
	}


	
}
