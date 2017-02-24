package business_object.letter;

public class LetterBuilder {

    private Letter letter;

    public LetterBuilder() {
        letter = LetterFactory.createDefaultLetter();
    }

    public LetterBuilder address(String address) {
        letter.setAddress(address);
        return this;
    }

    public LetterBuilder subject(String subject) {
        letter.setSubject(subject);
        return this;
    }

    public LetterBuilder bodyText(String bodyText) {
        letter.setEmailBody(bodyText);
        return this;
    }

    public Letter build() {
        return letter;
    }
}