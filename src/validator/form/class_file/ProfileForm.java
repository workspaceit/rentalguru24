package validator.form.class_file;

/**
 * Created by mi on 8/25/16.
 */
public class ProfileForm {
    private String firstName;
    private String lastName;
    private String email;
    private String oldPassword;
    private String newPassword;

    public String getFirstName() {
        return (firstName==null)?"":firstName.trim();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return (lastName==null)?"":lastName.trim();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return (email==null)?"":email.trim();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return (oldPassword ==null)?"": oldPassword.trim();
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return (newPassword ==null)?"": newPassword.trim();
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
