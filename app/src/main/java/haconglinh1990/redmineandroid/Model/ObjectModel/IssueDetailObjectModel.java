package haconglinh1990.redmineandroid.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class IssueDetailObjectModel {

    @SerializedName("issue")
    @Expose
    private Issue issue;

    /**
     * @return The issue
     */
    public Issue getIssue() {
        return issue;
    }

    /**
     * @param issue The issue
     */
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

}
