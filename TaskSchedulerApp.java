import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
public class TaskSchedulerApp extends Application {
 private PriorityQueue<Task> taskQueue = new PriorityQueue<>();
 private ObservableList<Task> taskList = FXCollections.observableArrayList();
 private ListView<Task> listView = new ListView<>(taskList);
 @Override
 public void start(Stage primaryStage) {
 TextField titleField = new TextField();
 titleField.setPromptText("Enter task title");
 DatePicker deadlinePicker = new DatePicker();
 Spinner<Integer> prioritySpinner = new Spinner<>(1, 10, 5);
 Button addButton = new Button("Add Task");
 addButton.setOnAction(e -> {
 Task task = new Task(
 titleField.getText(),
 prioritySpinner.getValue(),
 deadlinePicker.getValue()
 );
 taskQueue.add(task);
 updateList();
 });
 VBox layout = new VBox(10, titleField, deadlinePicker, prioritySpinner, addButton, listView);
 layout.setPadding(new Insets(10));
 primaryStage.setScene(new Scene(layout, 400, 500));
 primaryStage.setTitle("Smart Task Scheduler");
 primaryStage.show();
 startReminder();
 }
 private void updateList() {
 taskList.setAll(taskQueue.stream().sorted().toList());
 }
 private void startReminder() {
 Timer timer = new Timer();
 timer.schedule(new TimerTask() {
 public void run() {
 for (Task task : taskQueue) {
 if (task.getDeadline().equals(LocalDate.now())) {
 System.out.println("Reminder: " + task.getTitle());
 }
 }
 }
 }, 0, 60000); // every 60 seconds
 }
 public static void main(String[] args) {
 launch(args);
 }
}
