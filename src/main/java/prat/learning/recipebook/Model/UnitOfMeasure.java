package prat.learning.recipebook.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="unitOfMeasure")
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uom;


}
