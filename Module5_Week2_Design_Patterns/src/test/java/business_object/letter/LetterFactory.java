package business_object.letter;

import static business_object.letter.LetterData.EMAIL_ADDRESS;
import static business_object.letter.LetterData.EMAIL_BODY;
import static business_object.letter.LetterData.EMAIL_SUBJECT;

public class LetterFactory {

    private String emailAddress;
    private String emailSubject;
    private String emailBody;

    public static Letter createDefaultLetter() {
        Letter letter = new Letter();
        letter.setEmailAddress(EMAIL_ADDRESS);
        letter.setEmailSubject(EMAIL_SUBJECT);
        letter.setEmailBody(EMAIL_BODY);
        return letter;
    }

    public static Letter createLetterWithAddress(String emailAddress) {
        Letter letter = new Letter();
        letter.setEmailAddress(emailAddress);
        return letter;
    }

    public static Letter createLetterWithSubject(String emailSubject) {
        Letter letter = new Letter();
        letter.setEmailAddress(emailSubject);
        return letter;
    }

    public static Letter createLetterWithBody(String emailBody) {
        Letter letter = new Letter();
        letter.setEmailAddress(emailBody);
        return letter;
    }

    public static Letter createLetterWithAddressAndSubject(String emailAddress, String emailSubject) {
        Letter letter = new Letter();
        letter.setEmailAddress(emailAddress);
        letter.setEmailAddress(emailSubject);
        return letter;
    }

    public static Letter createLetterWithAddressAndBody(String emailAddress, String emailBody) {
        Letter letter = new Letter();
        letter.setEmailAddress(emailAddress);
        letter.setEmailAddress(emailBody);
        return letter;
    }

    public static Letter createLetterWithBodyAndSubject(String emailBody, String emailSubject) {
        Letter letter = new Letter();
        letter.setEmailAddress(emailBody);
        letter.setEmailAddress(emailSubject);
        return letter;
    }
}
