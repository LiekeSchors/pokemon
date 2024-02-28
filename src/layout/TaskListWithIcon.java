package layout;

import javax.swing.*;

public class TaskListWithIcon extends JFrame {
    public TaskListWithIcon() {
        // Set frame properties
        setTitle("Task List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an icon
        ImageIcon icon = new ImageIcon("ultra-ball.png"); // Replace "path/to/icon.png" with the actual path to your icon image

        // Set the icon for the tasklist
        setIconImage(icon.getImage());

        // Add other components and layout as needed

        // Display the frame
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskListWithIcon());
    }
}