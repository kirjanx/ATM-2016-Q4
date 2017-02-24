package business_object.letter;

public class LetterBuilder {

    private Letter letter;

    public LetterBuilder() {
        letter = LetterFactory.createDefaultLetter();
    }

    public LetterBuilder address(String emailAddress) {
        letter.setEmailAddress(emailAddress);
        return this;
    }

    public LetterBuilder subject(String emailSubject) {
        letter.setEmailSubject(emailSubject);
        return this;
    }

    public LetterBuilder bodyText(String emailBodyText) {
        letter.setEmailBody(emailBodyText);
        return this;
    }

    public Letter build() {
        return letter;
    }
}