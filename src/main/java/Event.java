public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, boolean isMarked, String duration) {
        super(description, isMarked);
        this.formatDuration(duration);
    }

    public void formatDuration(String duration) {
        String[] durationList = duration.split("/to");
        this.start = durationList[0];
        this.end = durationList[1];
    }

    public String getDuration() {
        return String.format("(from:%sto:%s",
                this.start,
                this.end);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + this.getDuration();
    }
}
