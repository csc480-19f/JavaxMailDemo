import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class Mailer {
	
	// Singleton idea
	
	private static Session session;
	private static Store storage;
	private final static String HOST = "imap.gmail.com";
	private final static int PORT = 995;
	private final static boolean TLS_ENABLED = true;
		
	public static Session getConnection() {
		if (session == null) {
			Properties properties = new Properties();
			properties.put("mail.imap.host", HOST);
			properties.put("mail.imap.port", PORT);
			properties.put("mail.imap.starttls.enable", TLS_ENABLED);
			session = Session.getDefaultInstance(properties);
		}
		
		return session;
	}
	
	public static Store getStorage(String user, String password) {
		if (storage == null) {
			try {
				storage = getConnection().getStore("imaps");
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			}
		}
		
		if (!storage.isConnected()) {
			try {
				storage.connect(HOST, user, password);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		return storage;
	}

}
