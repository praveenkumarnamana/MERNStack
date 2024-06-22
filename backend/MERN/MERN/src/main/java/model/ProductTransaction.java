package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTransaction {
    public ProductTransaction(Object object, String string, String string2, double d, Date date, boolean b,
			String string3) {
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;
    @Temporal(TemporalType.DATE)
    private Date dateOfSale;
    private boolean sold;
    private String category;
	public ProductTransaction() {
		super();
	}
	public ProductTransaction(Long id, String title, String description, double price, Date dateOfSale, boolean sold,
			String category) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.dateOfSale = dateOfSale;
		this.sold = sold;
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDateOfSale() {
		return dateOfSale;
	}
	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}
	public boolean isSold() {
		return sold;
	}
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "ProductTransaction [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", dateOfSale=" + dateOfSale + ", sold=" + sold + ", category=" + category + ", getId()=" + getId()
				+ ", getTitle()=" + getTitle() + ", getDescription()=" + getDescription() + ", getPrice()=" + getPrice()
				+ ", getDateOfSale()=" + getDateOfSale() + ", isSold()=" + isSold() + ", getCategory()=" + getCategory()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
}
