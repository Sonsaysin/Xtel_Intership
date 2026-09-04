import java.util.ArrayList;
import java.util.List;

public class Course {

    private String id;
    private String title;
    private float durationHours;
    private float fee;
    private List<String> tags;

    public Course() {
        this.tags = new ArrayList<>();
    }

    public Course(String id, String title, float durationHours,
                  float fee, List<String> tags) {

        this.id = id;
        setTitle(title);
        setDurationHours(durationHours);
        setFee(fee);

        this.tags = tags != null
                ? new ArrayList<>(tags)
                : new ArrayList<>();
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
        if (title == null || title.trim().length() < 5) {
            throw new IllegalArgumentException(
                    "Title must contain at least 5 characters"
            );
        }

        this.title = title;
    }

    public float getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(float durationHours) {
        if (durationHours <= 0) {
            throw new IllegalArgumentException(
                    "Duration hour must be greater than 0"
            );
        }

        this.durationHours = durationHours;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        if (fee <= 50) {
            throw new IllegalArgumentException(
                    "Fee must be greater than $50"
            );
        }

        this.fee = fee;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags != null
                ? new ArrayList<>(tags)
                : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "-------------------------\n" +
                "ID: " + id + "\n" +
                "Title: " + title + "\n" +
                "Duration: " + durationHours + "\n" +
                "Fee: " + fee + "\n" +
                "Tags: " + String.join(", ", tags) + "\n" +
                "-------------------------";
    }
}

