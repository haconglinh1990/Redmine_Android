package haconglinh1990.redmineandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class IssuesObjectModel {

    @SerializedName("issues")
    @Expose
    private ArrayList<Issue> issues = new ArrayList<Issue>();
    @SerializedName("total_count")
    @Expose
    private int totalCount;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("limit")
    @Expose
    private int limit;

    /**
     * @return The issues
     */
    public ArrayList<Issue> getIssues() {
        return issues;
    }

    /**
     * @param issues The issues
     */
    public void setIssues(ArrayList<Issue> issues) {
        this.issues = issues;
    }

    /**
     * @return The totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount The total_count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return The offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset The offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return The limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit The limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

}
