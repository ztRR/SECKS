package meic.password_manager_client;

import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.swing.JPasswordField;

import meic.password_manager_server.PMWServer;


public class clientPWM implements PWMLibrary {
	private static JPasswordField passwordField;
	private static KeyStore _ks;
	private static X509Certificate[] _cert;
	private static PMWServer _server;
	private static PublicKey _publicKey;
	private static PrivateKey _privateKey;
	
	static void initKeyStore(char[] pass) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		_ks = KeyStore.getInstance("JCEKS");
		_ks.load(null, pass);
	}
	
	public void init(KeyStore ks, char[] pass) throws Exception {
		initKeyStore(pass);
		_server = new PMWServer();
		initKeyPairAndCert();
		putKeysInKeyStore(this._publicKey,this._privateKey,pass);
	}

	public void register_user() {
		_server.register(this._publicKey);
		// TODO Auto-generated method stub
		//MessageContext messageContext = webServiceContext.getMessageContext();
	}

	public void save_password(byte[] domain, byte[] username, byte[] password) {
		// TODO Auto-generated method stub
		_server.put(_publicKey, domain, username, password);
	}

	public byte[] retrieve_password(Key publicKey, byte[] domain, byte[] username) {
		byte[] result = _server.get(publicKey,domain,username);
		return result;
	}

	static void initKeyPairAndCert() throws Exception{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		keyGen.initialize(1024, random);
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		setPrivateKey(privateKey);
		PublicKey publicKey = pair.getPublic();
		setPublicKey(publicKey);
		_cert=GenCert.generateCertificate(pair);
	}
	private static void setPublicKey(PublicKey publicKey) {
		_publicKey=publicKey;
		
	}

	private static void setPrivateKey(PrivateKey privateKey) {
		_privateKey = privateKey;
		
	}
	
	private void close(){
		//fechar a merda da ligaçao e sessao
	}
	
	private static void putKeysInKeyStore(PublicKey publicKey,PrivateKey privateKey,char[] pass) throws KeyStoreException{
		KeyStore.PrivateKeyEntry pkEntry = new KeyStore.PrivateKeyEntry(privateKey, _cert);
		 KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(pass);
		_ks.setEntry("PrivateKey", pkEntry, protParam);
		_ks.setKeyEntry("PublicKey", publicKey, pass, _cert);
	}

	public void init(KeyStore ks) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
