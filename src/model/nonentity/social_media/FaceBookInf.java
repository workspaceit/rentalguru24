package model.nonentity.social_media;

/**
 * Created by mi on 9/2/16.
 */
public class FaceBookInf {
    private String name = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String id = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String[] nameArray = name.split(" ");

        int i =0 ;
        for(String partialName : nameArray){
//
            if(i==0){
                this.firstName = partialName;
            }else{
                this.lastName += partialName;
            }
            i++;
        }
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
