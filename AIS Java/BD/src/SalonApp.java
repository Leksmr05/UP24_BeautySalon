import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SalonApp extends JFrame {

    // Таблицы для отображения данных
    private JTable clientsTable, servicesTable, employeesTable, appointmentsTable;

    // Модели данных для таблиц
    private DefaultTableModel clientsModel, servicesModel, employeesModel, appointmentsModel;

    // Кнопки управления
    private JButton addClientBtn, editClientBtn, deleteClientBtn;
    private JButton addServiceBtn, editServiceBtn, deleteServiceBtn;
    private JButton addEmployeeBtn, editEmployeeBtn, deleteEmployeeBtn;
    private JButton addAppointmentBtn, editAppointmentBtn, deleteAppointmentBtn;

    public SalonApp() {
        setTitle("Салон Красоты");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Создаем табы
        JTabbedPane tabbedPane = new JTabbedPane();

        // Таблицы и модели данных
        clientsModel = new DefaultTableModel(new String[]{"ID", "Имя", "Телефон", "Email"}, 0);
        clientsTable = new JTable(clientsModel);

        servicesModel = new DefaultTableModel(new String[]{"ID", "Услуга", "Цена"}, 0);
        servicesTable = new JTable(servicesModel);

        employeesModel = new DefaultTableModel(new String[]{"ID", "Имя", "Должность"}, 0);
        employeesTable = new JTable(employeesModel);

        appointmentsModel = new DefaultTableModel(new String[]{"ID", "Клиент", "Услуга", "Работник", "Дата"}, 0);
        appointmentsTable = new JTable(appointmentsModel);

        // Заполняем данные из базы
        loadData();

        // Панели с таблицами
        tabbedPane.addTab("Клиенты", createTabWithPanel(clientsTable, createClientControlPanel()));
        tabbedPane.addTab("Услуги", createTabWithPanel(servicesTable, createServiceControlPanel()));
        tabbedPane.addTab("Работники", createTabWithPanel(employeesTable, createEmployeeControlPanel()));
        tabbedPane.addTab("Записи", createTabWithPanel(appointmentsTable, createAppointmentControlPanel()));

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Метод для получения соединения с базой данных
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ssalon", "root", "leksmrleksmr");
    }

    // Метод для загрузки данных в таблицы
    private void loadData() {
        try (Connection conn = getConnection()) {
            // Загрузка клиентов
            loadTableData(conn, "SELECT * FROM clients", clientsModel);
            // Загрузка услуг
            loadTableData(conn, "SELECT * FROM services", servicesModel);
            // Загрузка работников
            loadTableData(conn, "SELECT * FROM employees", employeesModel);
            // Загрузка записей
            loadTableData(conn, "SELECT a.id, c.name AS client, s.name AS service, e.name AS employee, a.appointment_date FROM appointments a " +
                    "JOIN clients c ON a.client_id = c.id " +
                    "JOIN services s ON a.service_id = s.id " +
                    "JOIN employees e ON a.employee_id = e.id", appointmentsModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки данных: " + e.getMessage());
        }
    }

    // Метод для загрузки данных в таблицу
    private void loadTableData(Connection conn, String query, DefaultTableModel model) throws SQLException {
        model.setRowCount(0);  // Очищаем таблицу
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
        }
    }

    // Метод для создания панели с таблицей и кнопками управления
    private JPanel createTabWithPanel(JTable table, JPanel controlPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);
        return panel;
    }

    // Панель с кнопками управления для клиентов
    private JPanel createClientControlPanel() {
        JPanel controlPanel = new JPanel();
        addClientBtn = new JButton("Добавить клиента");
        editClientBtn = new JButton("Редактировать клиента");
        deleteClientBtn = new JButton("Удалить клиента");

        controlPanel.add(addClientBtn);
        controlPanel.add(editClientBtn);
        controlPanel.add(deleteClientBtn);

        // Обработчики кнопок
        addClientBtn.addActionListener(e -> addClient());
        editClientBtn.addActionListener(e -> editClient());
        deleteClientBtn.addActionListener(e -> deleteClient());

        return controlPanel;
    }

    // Панель с кнопками управления для услуг
    private JPanel createServiceControlPanel() {
        JPanel controlPanel = new JPanel();
        addServiceBtn = new JButton("Добавить услугу");
        editServiceBtn = new JButton("Редактировать услугу");
        deleteServiceBtn = new JButton("Удалить услугу");

        controlPanel.add(addServiceBtn);
        controlPanel.add(editServiceBtn);
        controlPanel.add(deleteServiceBtn);

        // Обработчики кнопок
        addServiceBtn.addActionListener(e -> addService());
        editServiceBtn.addActionListener(e -> editService());
        deleteServiceBtn.addActionListener(e -> deleteService());

        return controlPanel;
    }

    // Панель с кнопками управления для работников
    private JPanel createEmployeeControlPanel() {
        JPanel controlPanel = new JPanel();
        addEmployeeBtn = new JButton("Добавить работника");
        editEmployeeBtn = new JButton("Редактировать работника");
        deleteEmployeeBtn = new JButton("Удалить работника");

        controlPanel.add(addEmployeeBtn);
        controlPanel.add(editEmployeeBtn);
        controlPanel.add(deleteEmployeeBtn);

        // Обработчики кнопок
        addEmployeeBtn.addActionListener(e -> addEmployee());
        editEmployeeBtn.addActionListener(e -> editEmployee());
        deleteEmployeeBtn.addActionListener(e -> deleteEmployee());

        return controlPanel;
    }

    // Панель с кнопками управления для записей
    private JPanel createAppointmentControlPanel() {
        JPanel controlPanel = new JPanel();
        addAppointmentBtn = new JButton("Добавить запись");
        editAppointmentBtn = new JButton("Редактировать запись");
        deleteAppointmentBtn = new JButton("Удалить запись");

        controlPanel.add(addAppointmentBtn);
        controlPanel.add(editAppointmentBtn);
        controlPanel.add(deleteAppointmentBtn);

        // Обработчики кнопок
        addAppointmentBtn.addActionListener(e -> addAppointment());
        editAppointmentBtn.addActionListener(e -> editAppointment());
        deleteAppointmentBtn.addActionListener(e -> deleteAppointment());

        return controlPanel;
    }

    // Методы для работы с клиентами
    private void addClient() {
        String name = JOptionPane.showInputDialog(this, "Введите имя клиента:");
        String phone = JOptionPane.showInputDialog(this, "Введите телефон клиента:");
        String email = JOptionPane.showInputDialog(this, "Введите email клиента:");

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO clients (name, phone, email) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, phone);
                stmt.setString(3, email);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Клиент добавлен");
                loadData();  // Обновляем данные в таблице
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка добавления клиента: " + e.getMessage());
        }
    }

    private void editClient() {
        int selectedRow = clientsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите клиента для редактирования");
            return;
        }

        int clientId = (int) clientsModel.getValueAt(selectedRow, 0);
        String newName = JOptionPane.showInputDialog(this, "Новое имя клиента:", clientsModel.getValueAt(selectedRow, 1));
        String newPhone = JOptionPane.showInputDialog(this, "Новый телефон клиента:", clientsModel.getValueAt(selectedRow, 2));
        String newEmail = JOptionPane.showInputDialog(this, "Новый email клиента:", clientsModel.getValueAt(selectedRow, 3));

        try (Connection conn = getConnection()) {
            String query = "UPDATE clients SET name = ?, phone = ?, email = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newName);
                stmt.setString(2, newPhone);
                stmt.setString(3, newEmail);
                stmt.setInt(4, clientId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Клиент обновлен");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка редактирования клиента: " + e.getMessage());
        }
    }

    private void deleteClient() {
        int selectedRow = clientsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите клиента для удаления");
            return;
        }

        int clientId = (int) clientsModel.getValueAt(selectedRow, 0);
        try (Connection conn = getConnection()) {
            String query = "DELETE FROM clients WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, clientId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Клиент удален");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка удаления клиента: " + e.getMessage());
        }
    }

    // Методы для работы с услугами
    private void addService() {
        String serviceName = JOptionPane.showInputDialog(this, "Введите название услуги:");
        String servicePrice = JOptionPane.showInputDialog(this, "Введите цену услуги:");

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO services (name, price) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, serviceName);
                stmt.setString(2, servicePrice);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Услуга добавлена");
                loadData();  // Обновляем данные в таблице
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка добавления услуги: " + e.getMessage());
        }
    }

    private void editService() {
        int selectedRow = servicesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите услугу для редактирования");
            return;
        }

        int serviceId = (int) servicesModel.getValueAt(selectedRow, 0);
        String newName = JOptionPane.showInputDialog(this, "Новое название услуги:", servicesModel.getValueAt(selectedRow, 1));
        String newPrice = JOptionPane.showInputDialog(this, "Новая цена услуги:", servicesModel.getValueAt(selectedRow, 2));

        try (Connection conn = getConnection()) {
            String query = "UPDATE services SET name = ?, price = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newName);
                stmt.setString(2, newPrice);
                stmt.setInt(3, serviceId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Услуга обновлена");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка редактирования услуги: " + e.getMessage());
        }
    }

    private void deleteService() {
        int selectedRow = servicesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите услугу для удаления");
            return;
        }

        int serviceId = (int) servicesModel.getValueAt(selectedRow, 0);
        try (Connection conn = getConnection()) {
            String query = "DELETE FROM services WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, serviceId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Услуга удалена");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка удаления услуги: " + e.getMessage());
        }
    }

    // Методы для работы с работниками
    private void addEmployee() {
        String name = JOptionPane.showInputDialog(this, "Введите имя работника:");
        String position = JOptionPane.showInputDialog(this, "Введите должность работника:");

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO employees (name, position) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, position);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Работник добавлен");
                loadData();  // Обновляем данные в таблице
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка добавления работника: " + e.getMessage());
        }
    }

    private void editEmployee() {
        int selectedRow = employeesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите работника для редактирования");
            return;
        }

        int employeeId = (int) employeesModel.getValueAt(selectedRow, 0);
        String newName = JOptionPane.showInputDialog(this, "Новое имя работника:", employeesModel.getValueAt(selectedRow, 1));
        String newPosition = JOptionPane.showInputDialog(this, "Новая должность работника:", employeesModel.getValueAt(selectedRow, 2));

        try (Connection conn = getConnection()) {
            String query = "UPDATE employees SET name = ?, position = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newName);
                stmt.setString(2, newPosition);
                stmt.setInt(3, employeeId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Работник обновлен");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка редактирования работника: " + e.getMessage());
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите работника для удаления");
            return;
        }

        int employeeId = (int) employeesModel.getValueAt(selectedRow, 0);
        try (Connection conn = getConnection()) {
            String query = "DELETE FROM employees WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, employeeId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Работник удален");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка удаления работника: " + e.getMessage());
        }
    }

    // Методы для работы с записями
    private void addAppointment() {
        String clientName = JOptionPane.showInputDialog(this, "Введите имя клиента:");
        String serviceName = JOptionPane.showInputDialog(this, "Введите услугу:");
        String employeeName = JOptionPane.showInputDialog(this, "Введите имя работника:");
        String appointmentDate = JOptionPane.showInputDialog(this, "Введите дату записи:");

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO appointments (client_id, service_id, employee_id, appointment_date) VALUES (" +
                    "(SELECT id FROM clients WHERE name = ?), " +
                    "(SELECT id FROM services WHERE name = ?), " +
                    "(SELECT id FROM employees WHERE name = ?), ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, clientName);
                stmt.setString(2, serviceName);
                stmt.setString(3, employeeName);
                stmt.setString(4, appointmentDate);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Запись добавлена");
                loadData();  // Обновляем данные в таблице
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка добавления записи: " + e.getMessage());
        }
    }

    private void editAppointment() {
        int selectedRow = appointmentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите запись для редактирования");
            return;
        }

        int appointmentId = (int) appointmentsModel.getValueAt(selectedRow, 0);
        String newClientName = JOptionPane.showInputDialog(this, "Новое имя клиента:", appointmentsModel.getValueAt(selectedRow, 1));
        String newServiceName = JOptionPane.showInputDialog(this, "Новая услуга:", appointmentsModel.getValueAt(selectedRow, 2));
        String newEmployeeName = JOptionPane.showInputDialog(this, "Новый работник:", appointmentsModel.getValueAt(selectedRow, 3));
        String newAppointmentDate = JOptionPane.showInputDialog(this, "Новая дата записи:", appointmentsModel.getValueAt(selectedRow, 4));

        try (Connection conn = getConnection()) {
            String query = "UPDATE appointments SET client_id = (SELECT id FROM clients WHERE name = ?), " +
                    "service_id = (SELECT id FROM services WHERE name = ?), " +
                    "employee_id = (SELECT id FROM employees WHERE name = ?), " +
                    "appointment_date = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newClientName);
                stmt.setString(2, newServiceName);
                stmt.setString(3, newEmployeeName);
                stmt.setString(4, newAppointmentDate);
                stmt.setInt(5, appointmentId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Запись обновлена");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка редактирования записи: " + e.getMessage());
        }
    }

    private void deleteAppointment() {
        int selectedRow = appointmentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите запись для удаления");
            return;
        }

        int appointmentId = (int) appointmentsModel.getValueAt(selectedRow, 0);
        try (Connection conn = getConnection()) {
            String query = "DELETE FROM appointments WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, appointmentId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Запись удалена");
                loadData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка удаления записи: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SalonApp::new);
    }
}
