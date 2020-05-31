package com.borlehandro.gui;

import com.borlehandro.Dealer;
import com.borlehandro.PropertiesManager;
import com.borlehandro.StoreProgressListener;
import com.borlehandro.Worker;
import com.borlehandro.stores.AccessoryStore;
import com.borlehandro.stores.BodyStore;
import com.borlehandro.stores.EngineStore;
import com.borlehandro.stores.cars.CarsStore;
import com.borlehandro.stores.cars.CarsStoreController;
import com.borlehandro.suppliers.AccessorySupplier;
import com.borlehandro.suppliers.BodySupplier;
import com.borlehandro.suppliers.EngineSupplier;
import com.borlehandro.threadpool.ThreadPool;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

import java.io.IOException;

public class Controller {

    @FXML
    private Slider bodySlider;
    @FXML
    private Slider engineSlider;
    @FXML
    private Slider accessorySlider;
    @FXML
    private Slider workerSlider;
    @FXML
    private Slider dealerSlider;

    @FXML
    private Label bodyLabel;
    @FXML
    private Label engineLabel;
    @FXML
    private Label accessoryLabel;
    @FXML
    private Label workerLabel;
    @FXML
    private Label dealerLabel;

    @FXML
    private ProgressBar bodyProgress;
    @FXML
    private ProgressBar engineProgress;
    @FXML
    private ProgressBar accessoryProgress;
    @FXML
    private ProgressBar carProgress;

    private static ThreadPool workersPool;
    private static EngineStore engineStore;
    private static AccessoryStore accessoryStore;
    private static BodyStore bodyStore;
    private static CarsStore carsStore;

    private static ThreadGroup bodySuppliers;
    private static ThreadGroup engineSuppliers;
    private static ThreadGroup accessorySuppliers;
    private static ThreadGroup dealers;

    public void initialize() {
        initModel();

        bodySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.err.println((Double.toString(newValue.intValue() * 10)));
            bodyLabel.setText(Double.toString(newValue.intValue() * 10));
            try {
                BodySupplier[] bodySuppliersArray = new BodySupplier[PropertiesManager.getValue("bodySuppliers")];
                bodySuppliers.enumerate(bodySuppliersArray);
                for (BodySupplier bodySupplier : bodySuppliersArray) {
                    System.err.println("Try to set new time");
                    bodySupplier.changeWorkTime(newValue.intValue() * 10);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        engineSlider.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            System.err.println((Double.toString(newValue.intValue() * 10)));
            engineLabel.setText(Double.toString(newValue.intValue() * 10));
            try {
                EngineSupplier[] engineSuppliersArray = new EngineSupplier[PropertiesManager.getValue("engineSuppliers")];
                engineSuppliers.enumerate(engineSuppliersArray);
                for (EngineSupplier engineSupplier : engineSuppliersArray) {
                    System.err.println("Try to set new time");
                    engineSupplier.changeWorkTime(newValue.intValue() * 10);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }));

        accessorySlider.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            System.err.println((Double.toString(newValue.intValue() * 10)));
            accessoryLabel.setText(Double.toString(newValue.intValue() * 10));
            try {
                AccessorySupplier[] accessorySuppliersArray = new AccessorySupplier[PropertiesManager.getValue("accessorySuppliers")];
                accessorySuppliers.enumerate(accessorySuppliersArray);
                for (AccessorySupplier accessorySupplier : accessorySuppliersArray) {
                    System.err.println("Try to set new time");
                    accessorySupplier.changeWorkTime(newValue.intValue() * 10);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }));

        workerSlider.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            System.err.println((Double.toString(newValue.intValue() * 10)));
            workerLabel.setText(Double.toString(newValue.intValue() * 10));
            Worker.changeWorkTime(newValue.intValue() * 10);
        }));

        dealerSlider.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            System.err.println((Double.toString(newValue.intValue() * 10)));
            dealerLabel.setText(Double.toString(newValue.intValue() * 10));
            try {
                Dealer[] dealersArray = new Dealer[PropertiesManager.getValue("dealers")];
                dealers.enumerate(dealersArray);
                for (Dealer dealer : dealersArray) {
                    System.err.println("Try to set new time");
                    dealer.changeDealingTime(newValue.intValue() * 10);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }));

        try {
            bodySlider.setValue(PropertiesManager.getValue("bodyMakingTime")/10d);
            engineSlider.setValue(PropertiesManager.getValue("engineMakingTime")/10d);
            accessorySlider.setValue(PropertiesManager.getValue("accessoryMakingTime")/10d);
            workerSlider.setValue(PropertiesManager.getValue("carMakingTime")/10d);
            dealerSlider.setValue(PropertiesManager.getValue("dealingTime")/10d);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        StoreProgressListener bodyListener = new StoreProgressListener(bodyStore, bodyProgress);
        bodyListener.start();
        StoreProgressListener engineListener = new StoreProgressListener(engineStore, engineProgress);
        engineListener.start();
        StoreProgressListener accessoryListener = new StoreProgressListener(accessoryStore, accessoryProgress);
        accessoryListener.start();
        StoreProgressListener carListener = new StoreProgressListener(carsStore, carProgress);
        carListener.start();

    }

    private void initModel() {
        try {
            workersPool = new ThreadPool(PropertiesManager.getValue("workers"));

            engineStore = new EngineStore(PropertiesManager.getValue("engineStoreSize"));
            accessoryStore = new AccessoryStore(PropertiesManager.getValue("accessoryStoreSize"));
            bodyStore = new BodyStore(PropertiesManager.getValue("bodyStoreSize"));
            carsStore = new CarsStore(PropertiesManager.getValue("carsStoreSize"));

            bodySuppliers = new ThreadGroup("bodySuppliers");
            engineSuppliers = new ThreadGroup("engineSuppliers");
            accessorySuppliers = new ThreadGroup("accessorySuppliers");
            dealers = new ThreadGroup("dealers");

            for (int i = 0; i < PropertiesManager.getValue("bodySuppliers"); i++) {
                new BodySupplier(bodySuppliers, "Body supplier #" + i, bodyStore).start();
            }
            for (int i = 0; i < PropertiesManager.getValue("engineSuppliers"); i++) {
                new EngineSupplier(engineSuppliers, "Engine supplier #" + i, engineStore).start();
            }
            for (int i = 0; i < PropertiesManager.getValue("accessorySuppliers"); i++) {
                new AccessorySupplier(accessorySuppliers, "Accessory supplier #" + i, accessoryStore).start();
            }
            for (int i = 0; i < PropertiesManager.getValue("dealers"); i++) {
                new Dealer(dealers, "Dealer #" + i, carsStore).start();
            }

            CarsStoreController controller = new CarsStoreController(carsStore, workersPool, engineStore, accessoryStore, bodyStore);
            controller.start();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}