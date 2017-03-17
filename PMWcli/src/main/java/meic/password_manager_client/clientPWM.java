package meic.password_manager_client;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.swing.JPasswordField;

import meic.password_manager_server.PMWServer;

public class clientPWM implements PWMLibrary {
	private static JPasswordField passwordField;
	private static KeyStore _ks;
	private static X509Certificate[] _cert;
	
	
	public void init(KeyStore ks) throws Exception {
		_ks = ks;
		_cert = GenCert.generateCertificate(initKeyPair());
	}

	public void register_user() {
		_ks.
		// TODO Auto-generated method stub
		//MessageContext messageContext = webServiceContext.getMessageContext();
	}

	public byte[] save_password(byte[] domain, byte[] username, byte[] password) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] retrieve_password(Key publicKey, byte[] domain, byte[] username) {
		PMWServer pmws = new PMWServer();
		pmws.
		return null;
	}

	static KeyPair initKeyPair() throws Exception{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		keyGen.initialize(1024, random);
		KeyPair pair = keyGen.generateKeyPair();
		return pair;
		
	}
}
