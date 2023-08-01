package prat.learning.recipebook.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name="ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;


    @OneToOne
    private UnitOfMeasure uom;
    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public String toString() {
        return "Ingredient(id=" + this.getId() + ", description=" + this.getDescription() + ", amount=" + this.getAmount() + ", uom=" + this.getUom() + ", recipe=" + this.getRecipe() + ")";
    }
}
