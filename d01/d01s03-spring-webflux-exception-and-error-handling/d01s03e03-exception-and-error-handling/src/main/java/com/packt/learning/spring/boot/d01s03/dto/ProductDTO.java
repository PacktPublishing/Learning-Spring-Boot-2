package com.packt.learning.spring.boot.d01s03.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO (Data Transfer Object) used to serialize / deserialize {@link com.packt.learning.spring.boot.domain.model.Product}
 * objects
 *
 * @author bogdan.solga
 */
public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private final int id;
    private final String productName;
    private final double price;

    public ProductDTO(final int id, final String productName, final double price) {
        this.id = id; this.productName = productName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price);
    }
}
