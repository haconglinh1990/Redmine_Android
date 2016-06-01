
package haconglinh1990.redmineandroid.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class PermissionObjectModel {

    @SerializedName("role")
    @Expose
    private Role role;

    /**
     * 
     * @return
     *     The role
     */
    public Role getRole() {
        return role;
    }

    /**
     * 
     * @param role
     *     The role
     */
    public void setRole(Role role) {
        this.role = role;
    }

}
