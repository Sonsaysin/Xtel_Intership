import java.util.List;

public class Course {
    private String id;
    private String title;
    private float durationHours;
    private float fee;
    private List<String> tags;

    public Course() {
    }

    public Course(String id, String title, float durationHours, float fee, List<String> tags) {
        this.id = id;
        setTitle(title);
        setDurationHours(durationHours);
        setFee(fee);
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().length() < 5){
            throw new IllegalArgumentException("Title must constant at least 5 character");
        }
        this.title = title;
    }

    public float getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(float durationHours) {
        if (durationHours <= 0){
            throw new IllegalArgumentException("duration hour must be greater than 0");
        }
        this.durationHours = durationHours;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        if (fee <= 50){
            throw new IllegalArgumentException("fee must be greater than 50$");
        }
        this.fee = fee;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}