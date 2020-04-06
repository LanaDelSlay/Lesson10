package exercises;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import examples.FileHelper;

public class MailAndNumber {

	List<String> tlds = FileHelper.loadFileContentsIntoArrayList("resource/tlds.txt");
	
	public boolean verifyMail(String email) {
		
		if (email.length() >= 6) { 
			//Short email could be a@g.cn
			if(email.contains("@")) { 
				if(legitDomain(email)) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public boolean legitDomain(String email) {
		int myAt = email.indexOf("@");
		String emailIntersection = email.substring(myAt, email.length());
		int myDomain = emailIntersection.indexOf(".");
		String domainExtension = emailIntersection.substring(myDomain, emailIntersection.length());
		
		for (String s: tlds) {
			if (domainExtension.contains(s.toLowerCase())) {
				return true;
			}
		}  return false;
		
	}
	
	Pattern pattern = Pattern.compile("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");
	
	public boolean verifyNumber(String number) {
		Matcher matcher = pattern.matcher(number);
		if(matcher.matches()) {
			return true;
		} else return false;
	}
	
}
