package vo;

public class UserVo {
    private String idUser;
    private String namaUser;
    private String firstName;
    private String lastName;
    private String password;
    private String noTlp;

    // Constructor Kosong
    public UserVo() {
    }

    // Constructor Lengkap untuk mempermudah instansiasi
    public UserVo(String idUser, String namaUser, String firstName, String lastName, String password, String noTlp) {
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.noTlp = noTlp;
    }

    // Getter dan Setter
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getNamaUser() { return namaUser; }
    public void setNamaUser(String namaUser) { this.namaUser = namaUser; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNoTlp() { return noTlp; }
    public void setNoTlp(String noTlp) { this.noTlp = noTlp; }
}