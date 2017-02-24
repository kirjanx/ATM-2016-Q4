package business_object.letter;

import static business_object.letter.LetterData.ADDRESS;
import static business_object.letter.LetterData.BODY;
import static business_object.letter.LetterData.SUBJECT;

public class LetterFactory {

    private String address;
    private String subject;
    private String body;

    public static Letter createDefaultLetter() {
        Letter letter = new Letter();
        letter.setAddress(ADDRESS);
        letter.setSubject(SUBJECT);
        letter.setEmailBody(BODY);
        return letter;
    }

    public static Letter createLetterWithAddress(String address) {
        Letter letter = new Letter();
        letter.setAddress(address);
        return letter;
    }

    public static Letter createLetterWithSubject(String subject) {
        Letter letter = new Letter();
        letter.setAddress(subject);
        return letter;
    }

    public static Letter createLetterWithBody(String body) {
        Letter letter = new Letter();
        letter.setAddress(body);
        return letter;
    }

    public static Letter createLetterWithAddressAndSubject(String address, String subject) {
        Letter letter = new Letter();
        letter.setAddress(address);
        letter.setAddress(subject);
        return letter;
    }

    public static Letter createLetterWithAddressAndBody(String address, String body) {
        Letter letter = new Letter();
        letter.setAddress(address);
        letter.setAddress(body);
        return letter;
    }

    public static Letter createLetterWithBodyAndSubject(String body, String subject) {
        Letter letter = new Letter();
        letter.setAddress(body);
        letter.setAddress(subject);
        return letter;
    }
}