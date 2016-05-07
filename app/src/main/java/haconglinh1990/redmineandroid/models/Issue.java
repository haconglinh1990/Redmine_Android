package haconglinh1990.redmineandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Issue {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("project")
    @Expose
    private Project project;
    @SerializedName("tracker")
    @Expose
    private Tracker tracker;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("priority")
    @Expose
    private Priority priority;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("assigned_to")
    @Expose
    private AssignedTo assignedTo;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("done_ratio")
    @Expose
    private int doneRatio;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("estimated_hours")
    @Expose
    private double estimatedHours;

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
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
     * @return The tracker
     */
    public Tracker getTracker() {
        return tracker;
    }

    /**
     * @param tracker The tracker
     */
    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    /**
     * @return The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return The priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * @param priority The priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * @return The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return The assignedTo
     */
    public AssignedTo getAssignedTo() {
        return assignedTo;
    }

    /**
     * @param assignedTo The assigned_to
     */
    public void setAssignedTo(AssignedTo assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     * @return The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate The start_date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return The dueDate
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate The due_date
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return The doneRatio
     */
    public int getDoneRatio() {
        return doneRatio;
    }

    /**
     * @param doneRatio The done_ratio
     */
    public void setDoneRatio(int doneRatio) {
        this.doneRatio = doneRatio;
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
     * @return The updatedOn
     */
    public String getUpdatedOn() {
        return updatedOn;
    }

    /**
     * @param updatedOn The updated_on
     */
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * @return The estimatedHours
     */
    public double getEstimatedHours() {
        return estimatedHours;
    }

    /**
     * @param estimatedHours The estimated_hours
     */
    public void setEstimatedHours(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

}
