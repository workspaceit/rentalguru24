package validator.form.class_file;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by mi on 11/3/16.
 */
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int identityTypeId;
    private Long identityDocToken;

    public String getFirstName() {

        return StringEscapeUtils.escapeHtml3(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {

        return StringEscapeUtils.escapeHtml3(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentityTypeId() {
        return identityTypeId;
    }

    public void setIdentityTypeId(int identityTypeId) {
        this.identityTypeId = identityTypeId;
    }

    public Long getIdentityDocToken() {
        return identityDocToken;
    }

    public void setIdentityDocToken(Long identityDocToken) {
        this.identityDocToken = identityDocToken;
    }
}
