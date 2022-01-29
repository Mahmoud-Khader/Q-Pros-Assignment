package com.qpros.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qpros.connectors.APIConnector;
import com.qpros.models.Category;
import com.qpros.models.Pet;
import com.qpros.models.PetPojo;
import com.qpros.models.Tags;
import com.qpros.utils.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PetsController implements Initializable {

    @FXML
    private TextField updatePhotoTF;

    @FXML
    private TextField addTagIdTF;

    @FXML
    private TextField updateTagIdTF;

    @FXML
    private Button deletePhotoUrlsBtn;

    @FXML
    private Button clearUpdateBtn;

    @FXML
    private Button deletePetBtn;

    @FXML
    private TextField addCategoryIdTF;

    @FXML
    private Button addTagBtn;

    @FXML
    private ListView<Tags> updateTagsLV;

    @FXML
    private ListView<Tags> tagsLV;

    @FXML
    private TextField updateTagNameTF;

    @FXML
    private Button findPetBtn;

    @FXML
    private Pane pane;

    @FXML
    private TextField addPetNameTF;

    @FXML
    private AnchorPane addPetPane;

    @FXML
    private TextField addStatusTF;

    @FXML
    private Button addPetBtn;

    @FXML
    private Button updatePetBtn;

    @FXML
    private AnchorPane addPetPane1;

    @FXML
    private TextField updateCategoryNameTF;

    @FXML
    private TextField updatePetNameTF;

    @FXML
    private TextField updatePetIdTF;

    @FXML
    private Button updateDeleteTagsBtn;

    @FXML
    private TextField updateCategoryIdTF;

    @FXML
    private TextField addCategoryNameTF;

    @FXML
    private TextField addPhotoUrlTF;

    @FXML
    private ListView<String> updatePhotoLV;

    @FXML
    private TextField findPetByIdTF;

    @FXML
    private Button clearAddBtn;

    @FXML
    private TextField addTagNameTF;

    @FXML
    private ListView<String> photoLV;

    @FXML
    private Button addPhotoUrlsBtn;

    @FXML
    private Button updateAddTagsBtn;

    @FXML
    private Button updatePhotoUrlsBtn;

    @FXML
    private Button deleteUpdatePhotoUrlsBtn;

    @FXML
    private TextField updateStatusTF;

    @FXML
    private Button deleteTagBtn;

    @FXML
    private PieChart statusPC;

    @FXML
    private TextField addPetIdTF;

    @FXML
    private TabPane tabPane;

    @FXML
    private ComboBox<String> findPetByCB;

    @FXML
    private ComboBox<String> findPetByStatusCB;

    @FXML
    private TableView<PetPojo> tableView;

    @FXML
    private TableColumn<PetPojo, String> petStatusColumn;

    @FXML
    private TableColumn<PetPojo, String> petImageColumn;

    @FXML
    private TableColumn<PetPojo, Integer> petIDColumn;

    @FXML
    private TableColumn<PetPojo, String> petNameColumn;

    @FXML
    private TableColumn<Pet, String> categoryNameColumn;

    private final ObservableList<PetPojo> petList = FXCollections.observableArrayList();

    ObservableList<String> findPetByList = FXCollections.observableArrayList();
    ObservableList<String> findPetByStatusList = FXCollections.observableArrayList();
    String petStatus = null;
    ObservableList<String> addPhotoUrlsList = FXCollections.observableArrayList();

    ObservableList<String> updatePhotoUrlsList = FXCollections.observableArrayList();
    ObservableList<Tags> addTagsList = FXCollections.observableArrayList();
    ObservableList<Tags> updateTagsList = FXCollections.observableArrayList();


    public Object getPetAPI(String petInfo, String stringUrl) throws MalformedURLException {
        APIConnector apiPetsConnector = new APIConnector(stringUrl);
        Object petsJsonObject = apiPetsConnector.getPetAPI(petInfo);
        return petsJsonObject;
    }

    public void postPetAPI(Pet petInfo, String stringUrl) throws IOException {
        APIConnector apiPetsConnector = new APIConnector(stringUrl);
        apiPetsConnector.addUpdatePetAPI(petInfo, "POST");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null);
        alert.setContentText("Pet has been added successfully");
        alert.setTitle("Add new pet");
        alert.show();
    }

    public void putPetAPI(Pet petInfo, String stringUrl) throws IOException {
        APIConnector apiPetsConnector = new APIConnector(stringUrl);
        apiPetsConnector.addUpdatePetAPI(petInfo, "PUT");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null);
        alert.setContentText("Pet has been updated successfully");
        alert.setTitle("Update new pet");
        alert.show();
    }


    public void deletePetAPI(Integer petId, String stringUrl) throws IOException {
        APIConnector apiPetsConnector = new APIConnector(stringUrl);
        apiPetsConnector.deletePetAPI(petId);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null);
        alert.setContentText("Pet has been added successfully");
        alert.setTitle("Add new pet");
        alert.show();
    }

    private void setCellValueFactory() {
        petIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        petNameColumn.setCellValueFactory(new PropertyValueFactory<>("petName"));
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        petStatusColumn.setCellValueFactory(new PropertyValueFactory<>("petStatus"));
        petImageColumn.setCellValueFactory(new PropertyValueFactory<>("petImage"));
    }

    @FXML
    void findPetActionButton(ActionEvent event) {
        System.out.println("Working ...");
        try {
            petList.clear();
            if (findPetByCB.getSelectionModel().getSelectedIndex() == 0) {
                Object petsJsonObject = getPetAPI(findPetByIdTF.getText(), URL.PET_URL);
                Pet petData = new Gson().fromJson(String.valueOf(petsJsonObject), new TypeToken<Pet>() {
                }.getType());
                if (petData == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, null);
                    alert.setContentText("Pet Not Found!");
                    alert.setTitle("Error Pet Not Found!");
                    tableView.setItems(petList);
                    alert.show();
                } else {
                    PetPojo pet = new PetPojo();
                    pet.setId(petData.getId());
                    pet.setPetName(petData.getName());
                    pet.setCategoryName(petData.getCategory() != null ? petData.getCategory().getName() : null);
                    pet.setPetImage(String.join(", ", petData.getPhotoUrls()));
                    pet.setPetStatus(petData.getStatus());
                    petList.add(pet);
                    tableView.setItems(petList);
                }
            } else {
                Object petsJsonObject = getPetAPI(petStatus, URL.FIND_PET_BY_STATUS);
                List<Pet> petsData = new Gson().fromJson(String.valueOf(petsJsonObject), new TypeToken<List<Pet>>() {
                }.getType());
                for (Pet pets : petsData) {
                    PetPojo pet = new PetPojo();
                    pet.setId(pets.getId());
                    pet.setPetName(pets.getName());
                    pet.setCategoryName(pets.getCategory() != null ? pets.getCategory().getName() : null);
                    pet.setPetImage(String.join(", ", pets.getPhotoUrls()));
                    pet.setPetStatus(pets.getStatus());
                    petList.add(pet);
                }
                tableView.setItems(petList);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        pane.setPrefWidth(screenBounds.getWidth() - screenBounds.getWidth() / 5.8);
        tabPane.setPrefWidth(screenBounds.getWidth() - screenBounds.getWidth() / 5.8);
        tableView.setPrefWidth(screenBounds.getWidth() - screenBounds.getWidth() / 5.8);
        List<String> list = new ArrayList<>();
        list.add("FindById");
        list.add("FindByStatus");
        findPetByList.addAll(list);
        findPetByCB.setItems(findPetByList);
        List<String> status = new ArrayList<>();
        status.add("available");
        status.add("pending");
        status.add("sold");
        findPetByStatusList.addAll(status);
        findPetByStatusCB.setItems(findPetByStatusList);

        findPetByIdTF.textProperty().addListener((observable, oldValue, newValue) -> {
            intValue(findPetByIdTF, newValue, oldValue);
            if (findPetByIdTF.getText().isEmpty()) {
                findPetBtn.setDisable(true);
            } else {
                findPetBtn.setDisable(false);
            }
            findPetByStatusCB.getSelectionModel().clearSelection();
            petStatus = null;
        });

        findPetByStatusCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            petStatus = findPetByStatusCB.getSelectionModel().getSelectedItem();
            findPetByIdTF.setText("");
            findPetBtn.setDisable(false);
        });

        findPetByCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int index = findPetByCB.getSelectionModel().getSelectedIndex();
            if (index == 0) {
                findPetByIdTF.setDisable(false);
                findPetByIdTF.setVisible(true);
                findPetByStatusCB.setDisable(true);
                findPetByStatusCB.setVisible(false);
                findPetByStatusCB.getSelectionModel().clearSelection();
                findPetBtn.setDisable(true);
            } else {
                findPetByIdTF.setDisable(true);
                findPetByIdTF.setVisible(false);
                findPetByStatusCB.setDisable(false);
                findPetByStatusCB.setVisible(true);
                findPetByIdTF.setText("");
            }
        });
        setCellValueFactory();
        tableView.setItems(petList);

        tagsLV.setItems(addTagsList);
        updateTagsLV.setItems(updateTagsList);

        addTagNameTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0 && addTagIdTF.getText().length() >0) {
                addTagBtn.setDisable(false);
            } else {
                addTagBtn.setDisable(true);
            }
        });
        addTagIdTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0 && addTagNameTF.getText().length() >0) {
                addTagBtn.setDisable(false);
            } else {
                addTagBtn.setDisable(true);
            }
        });
        updateTagNameTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0 && updateTagIdTF.getText().length() >0) {
                updateAddTagsBtn.setDisable(false);
            } else {
                updateAddTagsBtn.setDisable(true);
            }
        });
        updateTagIdTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0 && updateTagNameTF.getText().length() >0) {
                updateAddTagsBtn.setDisable(false);
            } else {
                updateAddTagsBtn.setDisable(true);
            }
        });

        updateTagsLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (updateTagsLV.getSelectionModel().getSelectedIndex() == -1) {
                updateDeleteTagsBtn.setDisable(true);
            } else {
                updateTagNameTF.setText(updateTagsLV.getSelectionModel().getSelectedItem().getName());
                updateTagIdTF.setText(String.valueOf(updateTagsLV.getSelectionModel().getSelectedItem().getId()));
                updateDeleteTagsBtn.setDisable(false);
            }
        });

        tagsLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (tagsLV.getSelectionModel().getSelectedIndex() == -1) {
                deleteTagBtn.setDisable(true);
            } else {
                addTagIdTF.setText(String.valueOf(tagsLV.getSelectionModel().getSelectedItem().getId()));
                addTagNameTF.setText(tagsLV.getSelectionModel().getSelectedItem().getName());
                deleteTagBtn.setDisable(false);
            }
        });


        addPhotoUrlTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                addPhotoUrlsBtn.setDisable(false);
            } else {
                addPhotoUrlsBtn.setDisable(true);
            }
        });
        updatePhotoLV.setItems(updatePhotoUrlsList);

        updatePhotoTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                updatePhotoUrlsBtn.setDisable(false);
            } else {
                updatePhotoUrlsBtn.setDisable(true);
            }
        });

        updatePhotoLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (updatePhotoLV.getSelectionModel().getSelectedIndex() == -1) {
                deleteUpdatePhotoUrlsBtn.setDisable(true);
            } else {
                updatePhotoTF.setText(updatePhotoLV.getSelectionModel().getSelectedItem().toString());
                deleteUpdatePhotoUrlsBtn.setDisable(false);
            }
        });

        photoLV.setItems(addPhotoUrlsList);

        photoLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (photoLV.getSelectionModel().getSelectedIndex() == -1) {
                deletePhotoUrlsBtn.setDisable(true);
            } else {
                addPhotoUrlTF.setText(photoLV.getSelectionModel().getSelectedItem().toString());
                deletePhotoUrlsBtn.setDisable(false);
            }
        });

        updatePetIdTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                deletePetBtn.setDisable(false);
            } else {
                deletePetBtn.setDisable(true);
            }
        });


        try {
            Object availablePets = getPetAPI("available", URL.FIND_PET_BY_STATUS);
            List<Pet> availablePetsData = new Gson().fromJson(String.valueOf(availablePets), new TypeToken<List<Pet>>() {
            }.getType());
            Object pendingPets = getPetAPI("pending", URL.FIND_PET_BY_STATUS);
            List<Pet> pendingPetsData = new Gson().fromJson(String.valueOf(pendingPets), new TypeToken<List<Pet>>() {
            }.getType());
            Object soldPets = getPetAPI("sold", URL.FIND_PET_BY_STATUS);
            List<Pet> soldPetsData = new Gson().fromJson(String.valueOf(soldPets), new TypeToken<List<Pet>>() {
            }.getType());
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            pieChartData.add(new PieChart.Data("Available pets", availablePetsData.size()));
            pieChartData.add(new PieChart.Data("Pending pets", pendingPetsData.size()));
            pieChartData.add(new PieChart.Data("Sold pets", soldPetsData.size()));
            statusPC.setTitle("Pets Status");
            statusPC.setClockwise(true);
            statusPC.setLabelLineLength(50);
            statusPC.setLabelsVisible(true);
            statusPC.setStartAngle(180);
            statusPC.setData(pieChartData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addPhotoUrlAction(ActionEvent event) {
        addPhotoUrlsList.add(addPhotoUrlTF.getText());
        addPhotoUrlTF.clear();
    }

    @FXML
    void deletePhotoUrlAction(ActionEvent event) {
        addPhotoUrlsList.remove(photoLV.getSelectionModel().getSelectedItem());
        addPhotoUrlTF.clear();
    }

    @FXML
    void addTagAction(ActionEvent event) {
        Tags tags = new Tags();
        tags.setId(Integer.valueOf(addTagIdTF.getText()));
        tags.setName(addTagNameTF.getText());
        addTagsList.add(tags);
        addTagIdTF.clear();
        addTagNameTF.clear();

    }

    @FXML
    void deleteTagAction(ActionEvent event) {
        addTagsList.remove(tagsLV.getSelectionModel().getSelectedItem());
        addTagIdTF.clear();
        addTagNameTF.clear();
    }

    @FXML
    void addPetAction(ActionEvent event) {
        Pet pet = new Pet();
        Category category = new Category();
        category.setId(Integer.valueOf(addCategoryIdTF.getText()));
        category.setName(addCategoryNameTF.getText());
        pet.setId(Integer.valueOf(addPetIdTF.getText()));
        pet.setName(addPetNameTF.getText());
        pet.setCategory(category);
        pet.setPhotoUrls(photoLV.getItems());
        pet.setStatus(addStatusTF.getText());
        pet.setTags(tagsLV.getItems());
        try {
            postPetAPI(pet, URL.PET_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void clearAddPetAction(ActionEvent event) {
        addPetIdTF.setText("");
        addPetNameTF.setText("");
        addCategoryIdTF.setText("");
        addCategoryNameTF.setText("");
        addStatusTF.setText("");
        addPhotoUrlTF.setText("");
        addTagIdTF.setText("");
        addTagNameTF.setText("");
        photoLV.getItems().clear();
        tagsLV.getItems().clear();
    }

    @FXML
    void deletePetAction(ActionEvent event) {
        try {
            deletePetAPI(Integer.valueOf(updatePetIdTF.getText()), URL.PET_URL);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, null);
            alert.setContentText("Can't delete Pet, Pet Not Found!");
            alert.setTitle("Error Pet Not Found!");
            alert.show();
        }
    }

    @FXML
    void clearUpdatePetAction(ActionEvent event) {
        updatePetIdTF.setText("");
        updatePetNameTF.setText("");
        updateCategoryIdTF.setText("");
        updateCategoryNameTF.setText("");
        updateStatusTF.setText("");
        updatePhotoTF.setText("");
        updateTagIdTF.setText("");
        updateTagNameTF.setText("");
        updatePhotoLV.getItems().clear();
        updateTagsLV.getItems().clear();
    }

    @FXML
    void updatePetAction(ActionEvent event) {
        Pet pet = new Pet();
        Category category = new Category();
        category.setId(Integer.valueOf(updateCategoryIdTF.getText()));
        category.setName(updateCategoryNameTF.getText());
        pet.setId(Integer.valueOf(updatePetIdTF.getText()));
        pet.setName(updatePetNameTF.getText());
        pet.setCategory(category);
        pet.setPhotoUrls(updatePhotoLV.getItems());
        pet.setStatus(updateStatusTF.getText());
        pet.setTags(updateTagsLV.getItems());
        try {
            putPetAPI(pet, URL.PET_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void updatePhotoUrlAction(ActionEvent event) {
        updatePhotoUrlsList.add(updatePhotoTF.getText());
        updatePhotoTF.clear();
    }

    @FXML
    void deleteUpdatePhotoUrlAction(ActionEvent event) {
        updatePhotoUrlsList.remove(updatePhotoLV.getSelectionModel().getSelectedItem());
        updatePhotoTF.clear();
    }

    @FXML
    void updateTagAction(ActionEvent event) {
        Tags tags = new Tags();
        tags.setId(Integer.valueOf(updateTagIdTF.getText()));
        tags.setName(updateTagNameTF.getText());
        updateTagsList.add(tags);
        System.out.println(tags);
        updateTagIdTF.clear();
        updateTagNameTF.clear();
    }

    @FXML
    void deleteUpdateTagAction(ActionEvent event) {
        updateTagsList.remove(updateTagsLV.getSelectionModel().getSelectedItem());
        updateTagIdTF.clear();
        updateTagNameTF.clear();
    }

    public void intValue(TextField textField, String newValue, String oldValue) {
        if (newValue.matches("[0-9]*")) {
            textField.setText(newValue);
        } else {
            textField.setText(oldValue);
        }

    }

}
