import java.io.*;
import java.util.Arrays;

class Task {
    int taskId, taskProfit, taskDeadline;

    Task(int id, int deadline, int profit) {
        this.taskId = id;
        this.taskDeadline = deadline;
        this.taskProfit = profit;
    }
}

class Scheduler {
    int[] scheduleTasks(Task tasks[], int count) {
        // Sort tasks by decreasing profit
        Arrays.sort(tasks, (a, b) -> (b.taskProfit - a.taskProfit));

        // Find the maximum deadline
        int maxDeadline = 0;
        for (int i = 0; i < count; i++) {
            maxDeadline = Math.max(maxDeadline, tasks[i].taskDeadline);
        }

        // Create an array to track available slots
        int[] schedule = new int[maxDeadline + 1];
        Arrays.fill(schedule, -1);

        int tasksCompleted = 0, totalProfit = 0;

        for (int i = 0; i < count; i++) {
            for (int j = tasks[i].taskDeadline; j > 0; j--) {
                if (schedule[j] == -1) {
                    schedule[j] = i;
                    tasksCompleted++;
                    totalProfit += tasks[i].taskProfit;
                    break;
                }
            }
        }

        // Print the task schedule
        System.out.println("Slot\tDeadline\tTask ID");
        for (int i = 1; i <= maxDeadline; i++) {
            if (schedule[i] != -1) {
                System.out.println(i + "\t" + i + "\t\tTask" + tasks[schedule[i]].taskId);
            } else {
                System.out.println(i + "\t" + i + "\t\tFree");
            }
        }

        return new int[]{tasksCompleted, totalProfit};
    }
}

public class TaskManager {
    public static void main(String[] args) throws IOException {
        Task[] tasks = new Task[5];
        System.out.println("Harsh Rawte 22101A0047");
        tasks[0] = new Task(1, 2, 100);
        tasks[1] = new Task(2, 1, 19);
        tasks[2] = new Task(3, 2, 27);
        tasks[3] = new Task(4, 1, 25);
        tasks[4] = new Task(5, 3, 15);

        Scheduler scheduler = new Scheduler();
        int[] result = scheduler.scheduleTasks(tasks, 5);

        System.out.println("No. of jobs scheduled: " + result[0]);
        System.out.println("The maximum profit: " + result[1]);
    }
}
