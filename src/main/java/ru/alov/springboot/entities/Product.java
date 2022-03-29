package ru.alov.springboot.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT a FROM Product a"),
        @NamedQuery(name = "Product.findProductsByCategoryId", query = "SELECT a FROM Product a WHERE a.category.id = :id")
})
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "cost",nullable = false)
    private Double cost;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToMany()
//    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
//    @JoinTable(
//            name = "products_orders",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id")
//    )
//    private List<Order> orderList;

    @Transient
    private CategoryTypes categoryType;

    public Product(Long id, String name, Double cost, CategoryTypes categoryType) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.categoryType = categoryType;
    }

    public enum CategoryTypes {
        TV("Tv"),
        PHONE("Phone"),
        COMPUTER("Computer"),
        NONE("None");

        private final String displayName;

        CategoryTypes(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", categoryType=" + categoryType +
                '}';
    }
}
