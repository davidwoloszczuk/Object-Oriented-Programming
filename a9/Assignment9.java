import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ExerciseTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}

class MainWindow extends JFrame {
    private List<Exercise> exercises;
    private JTextField txtName;
    private JTextField txtDate;
    private JTextField txtDuration;
    private JTextField txtComment;
    private JTextField txtDistance;
    private JTextField txtWeight;
    private JTextField txtHeight;
    private JTextField txtRepetitions;
    private JTextArea txtSummary;

    public MainWindow() {
        exercises = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Exercise Tracker");
        setPreferredSize(new Dimension(600, 400));

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Date (MM/DD/YYYY):"));
        txtDate = new JTextField();
        inputPanel.add(txtDate);
        inputPanel.add(new JLabel("Duration (minutes):"));
        txtDuration = new JTextField();
        inputPanel.add(txtDuration);
        inputPanel.add(new JLabel("Comment:"));
        txtComment = new JTextField();
        inputPanel.add(txtComment);
        inputPanel.add(new JLabel("Distance (miles):"));
        txtDistance = new JTextField();
        inputPanel.add(txtDistance);
        inputPanel.add(new JLabel("Weight (pounds):"));
        txtWeight = new JTextField();
        inputPanel.add(txtWeight);
        inputPanel.add(new JLabel("Height (feet):"));
        txtHeight = new JTextField();
        inputPanel.add(txtHeight);
        inputPanel.add(new JLabel("Repetitions:"));
        txtRepetitions = new JTextField();
        inputPanel.add(txtRepetitions);
        mainPanel.add(inputPanel, BorderLayout.WEST);

        JButton btnAddExercise = new JButton("Add Exercise");
        btnAddExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddExerciseActionPerformed(e);
            }
        });
        mainPanel.add(btnAddExercise, BorderLayout.SOUTH);

        txtSummary = new JTextArea();
        txtSummary.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtSummary);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemLogin = new JMenuItem("Log in");
        menuItemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemLoginActionPerformed(e);
            }
        });
        menuFile.add(menuItemLogin);
        JMenuItem menuItemLogout = new JMenuItem("Log out");
        menuItemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemLogoutActionPerformed(e);
            }
        });
        menuFile.add(menuItemLogout);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        disableAll();
    }

    private void btnAddExerciseActionPerformed(ActionEvent e) {
        String name = txtName.getText();
        String date = txtDate.getText();
        double duration = Double.parseDouble(txtDuration.getText());
        String comment = txtComment.getText();

        Exercise exercise = null;
        if (!txtDistance.getText().isEmpty()) {
            double distance = Double.parseDouble(txtDistance.getText());
            exercise = new RunWalk(name, date, duration, comment, distance);
        } else if (!txtWeight.getText().isEmpty()) {
            double weight = Double.parseDouble(txtWeight.getText());
            exercise = new WeightLifting(name, date, duration, comment, weight);
        } else if (!txtHeight.getText().isEmpty() && !txtRepetitions.getText().isEmpty()) {
            double height = Double.parseDouble(txtHeight.getText());
            int repetitions = Integer.parseInt(txtRepetitions.getText());
            exercise = new RockClimbing(name, date, duration, comment, height, repetitions);
        }

        if (exercise != null) {
            exercises.add(exercise);
            updateSummary();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter valid exercise details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void menuItemLoginActionPerformed(ActionEvent e) {
        LoginFrame loginFrame = LoginFrame.V(this);
        loginFrame.setVisible(true);
    }

    private void menuItemLogoutActionPerformed(ActionEvent e) {
        disableAll();
        clearInputFields();
    }

    private void updateSummary() {
        Collections.sort(exercises);
        Collections.sort(exercises, new ExerciseCompareByCalories());
        txtSummary.setText("");
        ExerciseWriter.tabulateSummary(exercises);
    }

    private void clearInputFields() {
        txtName.setText("");
        txtDate.setText("");
        txtDuration.setText("");
        txtComment.setText("");
        txtDistance.setText("");
        txtWeight.setText("");
        txtHeight.setText("");
        txtRepetitions.setText("");
    }

    public void enableAll() {
        txtName.setEnabled(true);
        txtDate.setEnabled(true);
        txtDuration.setEnabled(true);
        txtComment.setEnabled(true);
        txtDistance.setEnabled(true);
        txtWeight.setEnabled(true);
        txtHeight.setEnabled(true);
        txtRepetitions.setEnabled(true);
    }

    private void disableAll() {
        txtName.setEnabled(false);
        txtDate.setEnabled(false);
        txtDuration.setEnabled(false);
        txtComment.setEnabled(false);
        txtDistance.setEnabled(false);
        txtWeight.setEnabled(false);
        txtHeight.setEnabled(false);
        txtRepetitions.setEnabled(false);
    }
}

class LoginFrame extends JDialog {
    private static LoginFrame v;
    private final JLabel lblUsername = new JLabel("Username");
    private final JLabel lblPassword = new JLabel("Password");
    private final JTextField txtUsername = new JTextField(15);
    private final JPasswordField txtPassword = new JPasswordField();
    private final JButton btnLogin = new JButton("Login");
    private final JButton btnCancel = new JButton("Cancel");

    private LoginFrame() {
        this(null);
    }

    private LoginFrame(final JFrame parent) {
        super(parent, "Log in window");
        setLayout(new GridLayout(3, 2));
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(btnCancel);
        pack();
        setLocationRelativeTo(parent);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strPassword = String.valueOf(txtPassword.getPassword());
                String strUsername = txtUsername.getText().trim();
                if (verifyLogin(strUsername, strPassword)) {
                    ((MainWindow) parent).enableAll();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username/password", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean verifyLogin(String strUsername, String strPassword) {
                return strUsername.equals("healthy") && strPassword.equals("donut");
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPassword.setText("");
                txtUsername.setText("");
                setVisible(false);
            }
        });
    }

    public static LoginFrame V(JFrame parent) {
        if (v == null)
            v = new LoginFrame(parent);
        return v;
    }
}

abstract class Exercise implements Comparable<Exercise> {
    private String name;
    private Date date;
    private double duration;
    private String comment;
    private SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate() {
        this.date = new Date();
    }

    public void setDate(String date) {
        try {
            this.date = df.parse(date);
        } catch (Exception ex) {
            this.date = new Date();
        }
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        if (duration < 0) {
            this.duration = 0;
        } else {
            this.duration = duration;
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Exercise() {
        name = "Exercise";
        setDate();
        setDuration(0);
        setComment("Unknown exercise");
    }

    public Exercise(String name, Date date, double duration, String comment) {
        setName(name);
        setDate(date);
        setDuration(duration);
        setComment(comment);
    }

    public Exercise(String name, String date, double duration, String comment) {
        setName(name);
        setDate(date);
        setDuration(duration);
        setComment(comment);
    }

    private String getDateAsString() {
        return df.format(date);
    }

    public abstract String toStringCustomInfo();

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%.2f\t%s\t%.2f\t%s", name, getType(), getDateAsString(), duration, toStringCustomInfo(), getCaloriesBurned(), comment);
    }

    public abstract String getType();

    public abstract double getCaloriesBurned();

    @Override
    public int compareTo(Exercise other) {
        return getDate().compareTo(other.getDate());
    }

    public String toSummaryString() {
        return String.format("%-20s%-25s%-15s%10.2f", getType(), name, getDateAsString(), getCaloriesBurned());
    }
}

class RunWalk extends Exercise {
    private double distance;

    public RunWalk(String name, Date date, double duration, String comment, double distance) {
        super(name, date, duration, comment);
        this.distance = distance;
    }

    public RunWalk(String name, String date, double duration, String comment, double distance) {
        super(name, date, duration, comment);
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toStringCustomInfo() {
        return String.format("%.2f", distance);
    }

    @Override
    public String getType() {
        return "RunWalk";
    }

    @Override
    public double getCaloriesBurned() {
        return distance / getDuration() * 9000;
    }
}

class WeightLifting extends Exercise {
    private double weight;

    public WeightLifting(String name, Date date, double duration, String comment, double weight) {
        super(name, date, duration, comment);
        this.weight = weight;
    }

    public WeightLifting(String name, String date, double duration, String comment, double weight) {
        super(name, date, duration, comment);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toStringCustomInfo() {
        return String.format("%.2f", weight);
    }

    @Override
    public String getType() {
        return "WeightLifting";
    }

    @Override
    public double getCaloriesBurned() {
        return weight / getDuration() * 50;
    }
}

class RockClimbing extends Exercise {
    private double height;
    private int repetitions;

    public RockClimbing(String name, Date date, double duration, String comment, double height, int repetitions) {
        super(name, date, duration, comment);
        this.height = height;
        this.repetitions = repetitions;
    }

    public RockClimbing(String name, String date, double duration, String comment, double height, int repetitions) {
        super(name, date, duration, comment);
        this.height = height;
        this.repetitions = repetitions;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toStringCustomInfo() {
        return String.format("%.2f\t%d", height, repetitions);
    }

    @Override
    public String getType() {
        return "RockClimbing";
    }

    @Override
    public double getCaloriesBurned() {
        return height * repetitions / getDuration() * 100;
    }
}

class ExerciseCompareByCalories implements Comparator<Exercise> {
    @Override
    public int compare(Exercise e1, Exercise e2) {
        return Double.compare(e1.getCaloriesBurned(), e2.getCaloriesBurned());
    }
}

class ExerciseWriter {
    public static void writeToScreen(List<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            System.out.println(exercise.toString());
        }
    }

    public static void writeToFile(List<Exercise> exercises, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Exercise exercise : exercises) {
                writer.write(exercise.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tabulateSummary(List<Exercise> exercises) {
        System.out.printf("%-20s%-25s%-15s%10s%n", "Type", "Name", "Date", "Calories");
        for (Exercise exercise : exercises) {
            System.out.println(exercise.toSummaryString());
        }
    }
}