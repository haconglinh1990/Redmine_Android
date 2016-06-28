package haconglinh1990.redmineandroid.Model.ObjectModel;

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
    @SerializedName("user")
    @Expose
    private User user;
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
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
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