package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spa_employee_procedure")
@Getter
@Setter
public class SpaEmployeeProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private SpaEmployee spa_employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_id", referencedColumnName = "id" )
    private SpaProcedure spa_procedure;

    private int price;

    public SpaEmployeeProcedure() {}
}
