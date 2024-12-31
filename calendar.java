package Hotel;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Calander extends JFrame {
    private JPanel calendarPanel;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private Set<LocalDate> bookedDates;
    private JLabel selectedDateLabel;
    private LocalDate firstSelectedDate;

    public Calander() {
        bookedDates = new HashSet<>();
        setTitle("Booking Calendar");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        monthComboBox = new JComboBox<>(months);

        int currentYear = LocalDate.now().getYear();
        Integer[] years = new Integer[50];
        for (int i = 0; i < 25; i++) {
            years[i] = currentYear - 25 + i;
        }
        for (int i = 25; i < 50; i++) {
            years[i] = currentYear + i - 25;
        }
        yearComboBox = new JComboBox<>(years);

        monthComboBox.addActionListener(e -> updateCalendar());
        yearComboBox.addActionListener(e -> updateCalendar());

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7));

        JPanel topPanel = new JPanel();
        topPanel.add(monthComboBox);
        topPanel.add(yearComboBox);
        add(topPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);

        selectedDateLabel = new JLabel("Selected Dates: None");
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshDates());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveDates());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(selectedDateLabel);
        bottomPanel.add(refreshButton);
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);

        updateCalendar();
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        int selectedMonth = monthComboBox.getSelectedIndex() + 1;
        int selectedYear = (Integer) yearComboBox.getSelectedItem();
        LocalDate firstDayOfMonth = LocalDate.of(selectedYear, selectedMonth, 1);
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        String[] dayLabels = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayLabels) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        for (int i = 0; i < firstDayOfWeek % 7; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = LocalDate.of(selectedYear, selectedMonth, day);
            JButton dayButton = new JButton(String.valueOf(day));

            if (bookedDates.contains(currentDate)) {
                dayButton.setForeground(Color.RED);
            } else {
                dayButton.setForeground(Color.BLUE);
            }

            dayButton.addActionListener(e -> handleDateSelection(currentDate));
            calendarPanel.add(dayButton);
        }

        calendarPanel.invalidate();
        calendarPanel.validate();
        calendarPanel.updateUI();
    }

    private void handleDateSelection(LocalDate selectedDate) {
        if (bookedDates.contains(selectedDate)) {
            bookedDates.remove(selectedDate);
            if (selectedDate.equals(firstSelectedDate)) {
                firstSelectedDate = null;
            }
        } else {
            if (firstSelectedDate == null) {
                firstSelectedDate = selectedDate;
            }
            bookedDates.add(selectedDate);
        }

        updateBookedDatesRange(selectedDate);
        updateSelectedDateLabel();
        updateCalendar();
    }

    private void updateBookedDatesRange(LocalDate selectedDate) {
        if (firstSelectedDate != null) {
            LocalDate lastDate = selectedDate;
            LocalDate startDate = firstSelectedDate.isBefore(lastDate) ? firstSelectedDate : lastDate;
            LocalDate endDate = firstSelectedDate.isAfter(lastDate) ? firstSelectedDate : lastDate;

            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                bookedDates.add(currentDate);
                currentDate = currentDate.plusDays(1);
            }
        }
    }

    private void updateSelectedDateLabel() {
        if (bookedDates.isEmpty()) {
            selectedDateLabel.setText("Selected Dates: None");
            firstSelectedDate = null;
        } else {
            LocalDate lastDate = bookedDates.stream().max(LocalDate::compareTo).orElse(null);
            selectedDateLabel.setText("Selected Dates: from " + firstSelectedDate + " to " + lastDate);
        }
    }

    private void refreshDates() {
        bookedDates.clear();
        firstSelectedDate = null;
        selectedDateLabel.setText("Selected Dates: None");
        updateCalendar();
    }

    private void saveDates() {
        if (bookedDates.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No dates selected to save.", "Save Dates", JOptionPane.WARNING_MESSAGE);
        } else {
            LocalDate lastDate = bookedDates.stream().max(LocalDate::compareTo).orElse(null);
            String message = "Saved Dates: from " + firstSelectedDate + " to " + lastDate;
            JOptionPane.showMessageDialog(this, message, "Save Dates", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Calander bookingCalendar = new Calander();
        bookingCalendar.setVisible(true);
    }
}
