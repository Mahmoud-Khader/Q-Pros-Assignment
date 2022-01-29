package com.qpros.models;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class PetPojo {

    SimpleLongProperty id = new SimpleLongProperty();
    SimpleStringProperty categoryName = new SimpleStringProperty(),
            petName = new SimpleStringProperty(),
            petStatus = new SimpleStringProperty(),
            petImage = new SimpleStringProperty();

    public PetPojo() {
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public SimpleStringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getPetName() {
        return petName.get();
    }

    public SimpleStringProperty petNameProperty() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName.set(petName);
    }

    public String getPetStatus() {
        return petStatus.get();
    }

    public SimpleStringProperty petStatusProperty() {
        return petStatus;
    }

    public void setPetStatus(String petStatus) {
        this.petStatus.set(petStatus);
    }

    public String getPetImage() {
        return petImage.get();
    }

    public SimpleStringProperty petImageProperty() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage.set(petImage);
    }
}
