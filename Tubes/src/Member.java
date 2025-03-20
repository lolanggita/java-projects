public class Member {
    private String name;
    private String nimNip;
    private String major;
    private String phone;
    private String status;

    public Member( String nimNip, String name, String major, String phone, String status) {
        this.nimNip = nimNip;
        this.name = name;
        this.major = major;
        this.phone = phone;
        this.status = status;
    }

    public String getName() { return name; }
    public String getNimNip() { return nimNip; }
    public String getMajor() { return major; }
    public String getPhone() { return phone; }
    public String getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setNimNip(String nimNip) { this.nimNip = nimNip; }
    public void setMajor(String major) { this.major = major; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setStatus(String status) { this.status = status; }
}
