package haconglinh1990.redmineandroid.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RoleObjectModel {

    @SerializedName("roles")
    @Expose
    private ArrayList<Role> roles = new ArrayList<Role>();

    /**
     * @return The roles
     */
    public ArrayList<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles The roles
     */
    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

}
