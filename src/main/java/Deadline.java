public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, boolean isMarked, String deadline) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return "(by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + this.getDeadline();
    }

}
