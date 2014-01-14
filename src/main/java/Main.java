import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import be.vdab.rest.FiliaalListItemREST;
import be.vdab.rest.FiliaalListREST;
import be.vdab.restClient.FiliaalClient;
import be.vdab.restClient.ForbiddenException;
import be.vdab.restClient.UserNamePasswordException;

public class Main {

	public static void main(String[] args) {
		/*
		 * Scanner scanner = new Scanner(System.in); ShaPasswordEncoder encoder
		 * = new ShaPasswordEncoder(); System.out.print("User name:"); String
		 * userName = scanner.nextLine(); System.out.print("Password:"); String
		 * origineelPaswoord = scanner.nextLine();
		 * System.out.print("Encrypted password:");
		 * System.out.println(encoder.encodePassword(origineelPaswoord,
		 * userName)); scanner.close();
		 */

		/*
		 * try (ClassPathXmlApplicationContext context = new
		 * ClassPathXmlApplicationContext( "spring/restClient.xml")) {
		 * FiliaalClient filiaalClient = context.getBean(FiliaalClient.class);
		 * FiliaalListREST filiaalListREST = filiaalClient.findAll(); for
		 * (FiliaalListItemREST filiaalItem : filiaalListREST .getFilialen()) {
		 * System.out.println(filiaalItem.getId() + ":" +
		 * filiaalItem.getNaam()); } }
		 */

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring/restClient.xml");
		FiliaalClient filiaalClient = context.getBean(FiliaalClient.class);
		try {
			FiliaalListREST filiaalListREST = filiaalClient.findAll();
			for (FiliaalListItemREST filiaalItem : filiaalListREST
					.getFilialen()) {
				System.out.println(filiaalItem.getId() + ":"
						+ filiaalItem.getNaam());
			}
		} catch (UserNamePasswordException ex) {
			System.out.println("Verkeerde gebruikersnaam/paswoord");
		} catch (ForbiddenException ex) {
			System.out.println("Je hebt niet de nodige rechten");
		} catch (Throwable ex) {
			System.out.println(ex.getMessage());
		}
	}

}
