public class Task {

    private String description;
    private Boolean isMarked;

    public Task(String description, Boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public String getDescription() {
        return this.description;
    }

    public void setIsMarked(Boolean bool) {
        this.isMarked = bool;
    }

    public String checked() {
        if (this.isMarked) {
            return "X";
        } else {
            return " ";
        }
    }
}
