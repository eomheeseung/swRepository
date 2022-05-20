package hello.se.web.Form;

import org.springframework.stereotype.Component;

@Component
public class CancelForm {
    private String cancelName;
    private String cancelPhoneNumber;

    public String getCancelName() {
        return cancelName;
    }

    public void setCancelName(String cancelName) {
        this.cancelName = cancelName;
    }

    public String getCancelPhoneNumber() {
        return cancelPhoneNumber;
    }

    public void setCancelPhoneNumber(String cancelPhoneNumber) {
        this.cancelPhoneNumber = cancelPhoneNumber;
    }
}
