// Модель записи на прием
public class Appointment {
    private int id;
    private int clientId;
    private int serviceId;
    private int employeeId;
    private String appointmentDate;

    public Appointment(int id, int clientId, int serviceId, int employeeId, String appointmentDate) {
        this.id = id;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.employeeId = employeeId;
        this.appointmentDate = appointmentDate;
    }

    public int getId() { return id; }
    public int getClientId() { return clientId; }
    public int getServiceId() { return serviceId; }
    public int getEmployeeId() { return employeeId; }
    public String getAppointmentDate() { return appointmentDate; }

    public void setId(int id) { this.id = id; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }
}

