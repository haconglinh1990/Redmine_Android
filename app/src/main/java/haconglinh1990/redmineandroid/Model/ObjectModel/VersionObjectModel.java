package haconglinh1990.redmineandroid.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class VersionObjectModel {

    @SerializedName("versions")
    @Expose
    private ArrayList<Version> versions = new ArrayList<Version>();
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    /**
     * @return The versions
     */
    public ArrayList<Version> getVersions() {
        return versions;
    }

    /**
     * @param versions The versions
     */
    public void setVersions(ArrayList<Version> versions) {
        this.versions = versions;
    }

    /**
     * @return The totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount The total_count
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
