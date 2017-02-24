package business_object.letter;

public class Letter {

    private String emailAddress;
    private String emailSubject;
    private String emailBodyText;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailBodyText() {
        return emailBodyText;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public void setEmailBody(String emailBodyText) {
        this.emailBodyText = emailBodyText;
    }
}