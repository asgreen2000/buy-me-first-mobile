package com.example.buymefirst.Modal;

public class ProductType {

    private String id;
    private String typeImage;
    private String typeName;

    public ProductType(String id,  String typeName, String typeImage) {
        this.id = id;
        this.typeImage = typeImage;
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

