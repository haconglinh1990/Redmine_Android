package haconglinh1990.redmineandroid.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("last_login_on")
    @Expose
    private String lastLoginOn;
    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("memberships")
    @Expose
    private ArrayList<Membership> memberships = new ArrayList<Membership>();

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
     * @return The login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login The login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return The createdOn
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn The created_on
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return The lastLoginOn
     */
    public String getLastLoginOn() {
        return lastLoginOn;
    }

    /**
     * @param lastLoginOn The last_login_on
     */
    public void setLastLoginOn(String lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }

    /**
     * @return The apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey The api_key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return The memberships
     */

    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Membership> getMemberships() {
        return memberships;
    }

    /**
     * @param memberships The memberships
     */
    public void setMemberships(ArrayList<Membership> memberships) {
        this.memberships = memberships;
    }

}