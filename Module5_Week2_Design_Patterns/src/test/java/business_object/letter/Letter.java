package business_object.letter;

public class Letter {

    private String address;
    private String subject;
    private String bodyText;

    public String getAddress() {
        return address;
    }

    public String getSubject() {
        return subject;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setEmailBody(String emailBodyText) {
        this.bodyText = emailBodyText;
    }
}
