package com.epam.storozhuk.entities;

import com.epam.storozhuk.annotation.UserFriendlyNaming;
import com.epam.storozhuk.util.BundleKeys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Hardware implements Serializable {
    private static final long serialVersionUID = -1449370342364019127L;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.HARDWARE_PRICE)
    private BigDecimal price;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.HARDWARE_ARTICLE)
    private String article;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.HARDWARE_COUNTRY_OF_PRODUCTION)
    private String countryProduction;

    public Hardware() {
    }

    public Hardware(BigDecimal price, String article, String countryProduction) {
        super();
        setPrice(price);
        setArticle(article);
        setCountryProduction(countryProduction);
    }

    public String getCountryProduction() {
        return countryProduction;
    }

    public void setCountryProduction(String countryProduction) {
        this.countryProduction = countryProduction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "price=" + price +
                ", article=" + article +
                ", countryProduction='" + countryProduction + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return (this.price.compareTo(hardware.getPrice()) == 0) &&
                getArticle().equals(hardware.getArticle()) &&
                getCountryProduction().equals(hardware.getCountryProduction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getArticle(), getCountryProduction());
    }
}
