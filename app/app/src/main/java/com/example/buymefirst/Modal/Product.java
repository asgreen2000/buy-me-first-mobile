package com.example.buymefirst.Modal;

public class Product {

    private String name, imageURL, type, description;
    private Integer price, inStock;
    private String id;
    private Integer idInList;

    public Integer getIdInList() {
        return idInList;
    }

    public void setIdInList(Integer idInList) {
        this.idInList = idInList;
    }

    public Product(String id, String name, String imageURL,
                   String type, String description,
                   Integer price, Integer inStock) {
        this.name = name;
        this.imageURL = imageURL;
        this.type = type;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
}
