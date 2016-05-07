package haconglinh1990.redmineandroid.models;

/**
 * Created by haconglinh1990 on 05/04/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Membership {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("project")
    @Expose
    private Project project;
    @SerializedName("roles")
    @Expose
    private ArrayList<Role> roles = new ArrayList<Role>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project The project
     */
    public void setProject(Project project) {
        this.project = project;
    }

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
