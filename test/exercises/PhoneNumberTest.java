package exercises;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

 class PhoneNumberTest {

	 MailAndNumber mnn = new MailAndNumber();
	 
		@Test 
		void testPhone() {
			String phonebook[]={"619 3453453", "619278-6547", "619-569-4321", "(568) 456-3241" , "789562 3256" };
			for (String n: phonebook) {
			assertTrue(mnn.verifyNumber(n));
			}
		}
		
		@Test
		void testMail() {
			String emails[] = {"gmiller1902@gmail.com", "admin@fbi.gov", "random@website.net", "snoopdog@cox.edu"};
			for (String s: emails) {
				assertTrue(mnn.verifyMail(s));
			}
		}
		
		
}
