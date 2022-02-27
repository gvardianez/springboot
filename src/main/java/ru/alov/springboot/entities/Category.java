package ru.alov.springboot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT a FROM Category a"),
        @NamedQuery(name = "Category.findByType", query = "SELECT a FROM Category a where a.categoryType = :categoryType")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", columnDefinition = "enum('TV','COMPUTER','PHONE','NONE')", nullable = false, updatable = false, unique = true)
    private Product.CategoryTypes categoryType;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryType=" + categoryType +
                ", productList=" + productList +
                '}';
    }
}
