package model;


//import jakarta.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company",uniqueConstraints = { @UniqueConstraint(name = "company_name_uindex",
        columnNames = {"company_name","founding_date"})})
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id",unique = true)
    private int companyId;
    @Column(name = "founding_date",nullable = false)
    private LocalDate foundingDate;
    @Column(name = "company_name",length = 50,nullable = false)
    private String companyName;
    @OneToMany(mappedBy = "company")
    Set<Trip> trips = new HashSet<>();
    public Company(int companyId, LocalDate foundingDate, String companyName) {
        this.companyId = companyId;
        this.foundingDate = foundingDate;
        this.companyName = companyName;
    }

    public Company(LocalDate foundingDate, String companyName) {
        this.foundingDate = foundingDate;
        this.companyName = companyName;
    }
    public Company(Date foundingDate, String companyName) {
        this.foundingDate =LocalDate.parse(foundingDate.toString());
        this.companyName = companyName;
    }

    public Company() {

    }


    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", foundingDate=" + foundingDate +
                ", companyName='" + companyName + '\'' +
                '}'+'\n';
    }
}
