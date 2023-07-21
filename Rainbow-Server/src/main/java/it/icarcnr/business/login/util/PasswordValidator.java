package it.icarcnr.business.login.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	private Pattern pattern;
	private Matcher matcher;

	/*	
	 * This regular expression pattern implements a strong and complex password.
	 * Password must be between
	 * 6 to 20 characters 
	 * with at least one digit, 
	 * one upper case letter, --- deleted
	 * one lower case letter 
	 * and one special symbol !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ 
	 * 
	 */
	
	private static final String PASSWORD_PATTERN = 
		"((?=.*\\d)(?=.*[a-z])(?=.*[\\p{Punct}]).{6,20})";

	public PasswordValidator(){
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	/**
	 * Validate password with regular expression
	 * @param password password for validation
	 * @return true valid password, false invalid password
	 */
	public boolean validate(final String password){
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

}
