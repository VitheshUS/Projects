package DOB_class;

import java.io.Serializable;
import java.util.Map;

public class owner_dob implements Serializable {
    String name;
    String email;
    String phone;
    String verification;
    Map<String,String> mp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public Map<String, String> getMp() {
        return mp;
    }

    public void setMp(Map<String, String> mp) {
        this.mp = mp;
    }

    public owner_dob(String name, String email, String phone, String verification, Map<String, String> mp) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.verification = verification;
        this.mp = mp;
    }
}
