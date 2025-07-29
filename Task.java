import java.time.LocalDate;
public class Task implements Comparable<Task> {
 private String title;
 private int priority;
 private LocalDate deadline;
 public Task(String title, int priority, LocalDate deadline) {
 this.title = title;
 this.priority = priority;
 this.deadline = deadline;
 }
 public String getTitle() { return title; }
 public int getPriority() { return priority; }
 public LocalDate getDeadline() { return deadline; }
 @Override
 public int compareTo(Task other) {
 return Integer.compare(this.priority, other.priority);
 }
 @Override
 public String toString() {
 return title + " | Priority: " + priority + " | Deadline: " + deadline;
 }
}
