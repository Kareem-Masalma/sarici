package com.sarisi;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class MainPage {
    @FXML
    private Button btBranch;
    @FXML
    private Button btBranchEmployees;
    @FXML
    private Button btProducts;
    @FXML
    private Button btCustomers;
    @FXML
    private Button btWarehouse;
    @FXML
    private Button btWarehouseEmployees;
    @FXML
    private Button btDelivery;
    @FXML
    private Button btCars;
    @FXML
    private Button btOrders;
    @FXML
    private Button btSuppliers;
    @FXML
    private Button btNumberOfWarehouseEmployees;
    @FXML
    private Button btNumberOfBranchEmployees;
    @FXML
    private Button btNumberOfDeliveryOrders;
    @FXML
    private Button btReport;

    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/sarisi";

    //  Database credentials
    String USER = "root";
    String PASS = "kareem2004";
    String port = "3306";
    String host = "localhost";
    Connection con;
    String dbURL = "jdbc:mysql://" + host + ":" + port + "/sarisi" + "?verifyServerCertificate=false";
    Properties p = new Properties();

    public void initialize() {
        btBranch.setOnMouseEntered(event -> btBranch.setStyle("-fx-background-color: grey;"));
        btBranch.setOnMouseExited(event -> btBranch.setStyle("-fx-background-color:  #171717;"));
        btWarehouse.setOnMouseEntered(event -> btWarehouse.setStyle("-fx-background-color: grey;"));
        btWarehouse.setOnMouseExited(event -> btWarehouse.setStyle("-fx-background-color:  #171717;"));
        btProducts.setOnMouseEntered(event -> btProducts.setStyle("-fx-background-color: grey;"));
        btProducts.setOnMouseExited(event -> btProducts.setStyle("-fx-background-color:  #171717;"));
        btBranchEmployees.setOnMouseEntered(event -> btBranchEmployees.setStyle("-fx-background-color: grey;"));
        btBranchEmployees.setOnMouseExited(event -> btBranchEmployees.setStyle("-fx-background-color:  #171717;"));
        btCustomers.setOnMouseEntered(event -> btCustomers.setStyle("-fx-background-color: grey;"));
        btCustomers.setOnMouseExited(event -> btCustomers.setStyle("-fx-background-color:  #171717;"));
        btWarehouseEmployees.setOnMouseEntered(event -> btWarehouseEmployees.setStyle("-fx-background-color: grey;"));
        btWarehouseEmployees.setOnMouseExited(event -> btWarehouseEmployees.setStyle("-fx-background-color:  #171717;"));
        btDelivery.setOnMouseEntered(event -> btDelivery.setStyle("-fx-background-color: grey;"));
        btDelivery.setOnMouseExited(event -> btDelivery.setStyle("-fx-background-color:  #171717;"));
        btCars.setOnMouseEntered(event -> btCars.setStyle("-fx-background-color: grey;"));
        btCars.setOnMouseExited(event -> btCars.setStyle("-fx-background-color:  #171717;"));
        btOrders.setOnMouseEntered(event -> btOrders.setStyle("-fx-background-color: grey;"));
        btOrders.setOnMouseExited(event -> btOrders.setStyle("-fx-background-color:  #171717;"));
        btSuppliers.setOnMouseEntered(event -> btSuppliers.setStyle("-fx-background-color: grey;"));
        btSuppliers.setOnMouseExited(event -> btSuppliers.setStyle("-fx-background-color:  #171717;"));
        btNumberOfWarehouseEmployees.setOnMouseEntered(event -> btNumberOfWarehouseEmployees.setStyle("-fx-background-color: grey;"));
        btNumberOfWarehouseEmployees.setOnMouseExited(event -> btNumberOfWarehouseEmployees.setStyle("-fx-background-color:  #171717;"));
        btNumberOfBranchEmployees.setOnMouseEntered(event -> btNumberOfBranchEmployees.setStyle("-fx-background-color: grey;"));
        btNumberOfBranchEmployees.setOnMouseExited(event -> btNumberOfBranchEmployees.setStyle("-fx-background-color:  #171717;"));
        btNumberOfDeliveryOrders.setOnMouseEntered(event -> btNumberOfDeliveryOrders.setStyle("-fx-background-color: grey;"));
        btNumberOfDeliveryOrders.setOnMouseExited(event -> btNumberOfDeliveryOrders.setStyle("-fx-background-color:  #171717;"));
        btReport.setOnMouseEntered(event -> btReport.setStyle("-fx-background-color: grey;"));
        btReport.setOnMouseExited(event -> btReport.setStyle("-fx-background-color:  #171717;"));


        p.setProperty("user", USER);
        p.setProperty("password", PASS);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        try {
            con = DriverManager.getConnection(dbURL, p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btBranch.setOnAction(event -> {
            branch();
        });
        btBranchEmployees.setOnAction(event -> {
            branchEmployee();
        });
        btProducts.setOnAction(event -> {
            products();
        });
        btCustomers.setOnAction(event -> {
            customers();
        });
        btWarehouse.setOnAction(event -> {
            warehouse();
        });
        btWarehouseEmployees.setOnAction(event -> {
            warehouseEmployees();
        });
        btDelivery.setOnAction(event -> {
            delivery();
        });
        btCars.setOnAction(event -> {
            cars();
        });
        btOrders.setOnAction(event -> {
            orders();
        });
        btSuppliers.setOnAction(event -> {
            suppliers();
        });

        btNumberOfWarehouseEmployees.setOnAction(event -> {
            numberOfWarehouseEmployees();
        });

        btNumberOfBranchEmployees.setOnAction(event -> {
            numberOfBranchEmployees();
        });

        btNumberOfDeliveryOrders.setOnAction(event -> {
            numberOfDeliveryOrders();
        });

        btReport.setOnAction(event -> {
            reportMethod();
        });
    }

    private void reportMethod() {
        try {
            String query = "select count(orderID) as orders\n" +
                    "from delievery_order\n" +
                    "where orderDate like \"2024-5-%\";";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int orders = 0;
            while (rs.next()) {
                orders = rs.getInt("orders");
            }

            query = "select sum(price) as income\n" +
                    "from delievery_order\n" +
                    "where orderDate like \"2024-5-%\";";
            rs = stmt.executeQuery(query);

            double income = 0;
            while (rs.next()) {
                income = rs.getDouble("income");
            }

            query = "select count(supplier) as supplied\n" +
                    "from supplier_warehouse\n" +
                    "where supplyDate like \"2024-05-%\";";
            rs = stmt.executeQuery(query);

            int supplied = 0;
            while (rs.next()) {
                supplied = rs.getInt("supplied");
            }

            query = "select count(phone) as customer\n" +
                    "from customer;";

            rs = stmt.executeQuery(query);
            int customer = 0;
            while (rs.next()) {
                customer = rs.getInt("customer");
            }

            query = "select count(plateNumber) as cars\n" +
                    "from car;";

            rs = stmt.executeQuery(query);
            int cars = 0;
            while (rs.next()) {
                cars = rs.getInt("cars");
            }

            query = "select count(ssn) as employees\n" +
                    "from employee;";

            rs = stmt.executeQuery(query);

            int employees = 0;
            while (rs.next()) {
                employees = rs.getInt("employees");
            }

            Report report = new Report(orders, income, employees, customer, cars, supplied);

            TableView<Report> table = new TableView<>();
            TableColumn<Report, Integer> ordersCol = new TableColumn<>("Orders");
            ordersCol.setCellValueFactory(new PropertyValueFactory<Report, Integer>("orders"));
            table.getColumns().add(ordersCol);

            TableColumn<Report, Double> incomeCol = new TableColumn<>("Income");
            incomeCol.setCellValueFactory(new PropertyValueFactory<Report, Double>("ordersIncome"));
            table.getColumns().add(incomeCol);

            TableColumn<Report, Integer> employeesCol = new TableColumn<>("Employees");
            employeesCol.setCellValueFactory(new PropertyValueFactory<Report, Integer>("employees"));
            table.getColumns().add(employeesCol);

            TableColumn<Report, Integer> customerCol = new TableColumn<>("Customers");
            customerCol.setCellValueFactory(new PropertyValueFactory<Report, Integer>("customers"));
            table.getColumns().add(customerCol);

            TableColumn<Report, Integer> carsCol = new TableColumn<>("Cars");
            carsCol.setCellValueFactory(new PropertyValueFactory<Report, Integer>("cars"));
            table.getColumns().add(carsCol);

            TableColumn<Report, Integer> suppliedCol = new TableColumn<>("Supplied");
            suppliedCol.setCellValueFactory(new PropertyValueFactory<Report, Integer>("supplied"));
            table.getColumns().add(suppliedCol);

            table.getItems().add(report);

            BorderPane root = new BorderPane();
            root.setCenter(table);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 400, 300);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Report");
            stage.show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void numberOfDeliveryOrders() {
        try {
            String query = "SELECT e.employeeName, count(o.orderId) AS number_of_orders\n" +
                    "FROM employee e, delievery_order o\n" +
                    "WHERE e.ssn = o.employee\n" +
                    "group by e.employeeName;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<NumberOfDeliveryOrders> employees = new ArrayList<>();
            while (rs.next()) {
                String employeeName = rs.getString("employeeName");
                int numberOfOrders = rs.getInt("number_of_orders");
                employees.add(new NumberOfDeliveryOrders(employeeName, numberOfOrders));
            }

            TableView<NumberOfDeliveryOrders> table = new TableView<>();
            TableColumn<NumberOfDeliveryOrders, String> employeeNameCol = new TableColumn<>("Employee Name");
            employeeNameCol.setCellValueFactory(new PropertyValueFactory<NumberOfDeliveryOrders, String>("employee"));
            table.getColumns().add(employeeNameCol);

            TableColumn<NumberOfDeliveryOrders, Integer> numberOfOrdersCol = new TableColumn<>("Number of Orders");
            numberOfOrdersCol.setCellValueFactory(new PropertyValueFactory<NumberOfDeliveryOrders, Integer>("numberOfOrders"));
            table.getColumns().add(numberOfOrdersCol);

            table.getItems().addAll(employees);

            BorderPane root = new BorderPane();
            root.setCenter(table);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 400, 300);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Number of Delivery Orders");
            stage.show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void numberOfBranchEmployees() {
        try {
            String query = "SELECT b.address, count(be.employee) AS number_of_employess\n" +
                    "FROM branch b , branchemployee be\n" +
                    "WHERE b.address = be.branch\n" +
                    "group by b.address;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<NumberOfBranchEmployees> employees = new ArrayList<>();
            while (rs.next()) {
                String address = rs.getString("address");
                int numberOfEmployees = rs.getInt("number_of_employess");
                employees.add(new NumberOfBranchEmployees(address, numberOfEmployees));
            }

            TableView<NumberOfBranchEmployees> table = new TableView<>();
            TableColumn<NumberOfBranchEmployees, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<NumberOfBranchEmployees, String>("branchAddress"));
            table.getColumns().add(addressCol);

            TableColumn<NumberOfBranchEmployees, Integer> numberOfEmployeesCol = new TableColumn<>("Number of Employees");
            numberOfEmployeesCol.setCellValueFactory(new PropertyValueFactory<NumberOfBranchEmployees, Integer>("numberOfEmployees"));
            table.getColumns().add(numberOfEmployeesCol);

            table.getItems().addAll(employees);

            BorderPane root = new BorderPane();
            root.setCenter(table);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 400, 300);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Number of Branch Employees");
            stage.show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void numberOfWarehouseEmployees() {
        try {
            String query = "SELECT w.address, count(we.employee) AS number_of_employess\n" +
                    "FROM warehouse w , warehouseemployee we\n" +
                    "WHERE w.address = we.warehouse\n" +
                    "group by w.address;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<NumberOfWarehouseEmployees> employees = new ArrayList<>();
            while (rs.next()) {
                String address = rs.getString("address");
                int numberOfEmployees = rs.getInt("number_of_employess");
                employees.add(new NumberOfWarehouseEmployees(address, numberOfEmployees));
            }

            TableView<NumberOfWarehouseEmployees> table = new TableView<>();
            TableColumn<NumberOfWarehouseEmployees, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<NumberOfWarehouseEmployees, String>("warehouseAddress"));
            table.getColumns().add(addressCol);

            TableColumn<NumberOfWarehouseEmployees, Integer> numberOfEmployeesCol = new TableColumn<>("Number of Employees");
            numberOfEmployeesCol.setCellValueFactory(new PropertyValueFactory<NumberOfWarehouseEmployees, Integer>("numberOfEmployees"));
            table.getColumns().add(numberOfEmployeesCol);

            table.getItems().addAll(employees);

            BorderPane root = new BorderPane();
            root.setCenter(table);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 400, 300);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Number of Warehouse Employees");
            stage.show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void suppliers() {
        try {
            ArrayList<Supplier> suppliers = new ArrayList<>();
            String query = "select * from supplier;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                int ssn = rs.getInt("ssn");
                String name = rs.getString("supplierName");
                String company = rs.getString("company");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                suppliers.add(new Supplier(ssn, name, company, address, phone));
            }

            TableView<Supplier> table = new TableView<>();
            table.setEditable(true);

            TableColumn<Supplier, Integer> ssnCol = new TableColumn<>("SSN");
            ssnCol.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("ssn"));
            table.getColumns().add(ssnCol);
            ssnCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            ssnCol.setOnEditCommit(event -> {
                Supplier supplier = event.getRowValue();
                int oldSSN = supplier.getSsn();
                int newSSN = event.getNewValue();
                supplier.setSsn(newSSN);
                try {
                    String updateQuery = "update supplier \n" +
                            "set ssn = " + newSSN + "\n" +
                            "where ssn = " + oldSSN + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the ssn");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Supplier, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
            table.getColumns().add(nameCol);
            nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameCol.setOnEditCommit(event -> {
                Supplier supplier = event.getRowValue();
                String newName = event.getNewValue();
                supplier.setName(newName);
                try {
                    String updateQuery = "update supplier \n" +
                            "set supplierName = \'" + newName + "\'\n" +
                            "where supplierName = \'" + supplier.getName() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the name");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Supplier, String> companyCol = new TableColumn<>("Company");
            companyCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("company"));
            table.getColumns().add(companyCol);
            companyCol.setCellFactory(TextFieldTableCell.forTableColumn());
            companyCol.setOnEditCommit(event -> {
                Supplier supplier = event.getRowValue();
                String newCompany = event.getNewValue();
                supplier.setCompany(newCompany);
                try {
                    String updateQuery = "update supplier \n" +
                            "set company = \'" + newCompany + "\'\n" +
                            "where supplierName = \'" + supplier.getName() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the company");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Supplier, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
            table.getColumns().add(addressCol);
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(event -> {
                Supplier supplier = event.getRowValue();
                String newAddress = event.getNewValue();
                supplier.setAddress(newAddress);
                try {
                    String updateQuery = "update supplier \n" +
                            "set address = \'" + newAddress + "\'\n" +
                            "where supplierName = \'" + supplier.getName() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the address");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Supplier, String> phoneCol = new TableColumn<>("Phone");
            phoneCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));
            table.getColumns().add(phoneCol);
            phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneCol.setOnEditCommit(event -> {
                Supplier supplier = event.getRowValue();
                String newPhone = event.getNewValue();
                supplier.setPhone(newPhone);
                try {
                    String updateQuery = "update supplier \n" +
                            "set phone = \'" + newPhone + "\'\n" +
                            "where supplierName = \'" + supplier.getName() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the phone");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            table.getItems().addAll(suppliers);

            BorderPane root = new BorderPane();
            root.setCenter(table);

            TextField tfSSN = new TextField();
            tfSSN.setPromptText("SSN");
            TextField tfName = new TextField();
            tfName.setPromptText("Name");
            TextField tfCompany = new TextField();
            tfCompany.setPromptText("Company");
            TextField tfAddress = new TextField();
            tfAddress.setPromptText("Address");
            TextField tfPhone = new TextField();
            tfPhone.setPromptText("Phone");

            Button btAdd = new Button("Add");
            Button btDelete = new Button("Delete");

            btAdd.setOnAction(event -> {
                int ssn = Integer.parseInt(tfSSN.getText());
                String name = tfName.getText();
                String company = tfCompany.getText();
                String address = tfAddress.getText();
                String phone = tfPhone.getText();
                Supplier supplier = new Supplier(ssn, name, company, address, phone);
                try {
                    String insertQuery = "insert into supplier values(?, ?, ?, ?, ?);";
                    PreparedStatement statement = con.prepareStatement(insertQuery);
                    statement.setInt(1, ssn);
                    statement.setString(2, name);
                    statement.setString(3, company);
                    statement.setString(4, address);
                    statement.setString(5, phone);
                    statement.executeUpdate();
                    suppliers.add(supplier);
                    table.getItems().add(supplier);
                } catch (SQLException e) {
                    Label label = new Label("Error adding the supplier");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            btDelete.setOnAction(event -> {
                Supplier supplier = table.getSelectionModel().getSelectedItem();

                if (supplier == null) {
                    Label label1 = new Label("Please select a supplier to delete");
                    label1.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label1, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();

                } else {
                    Button btYes = new Button("Yes");
                    Button btNo = new Button("No");
                    Label label = new Label("Are you sure you want to delete the supplier?");
                    label.setAlignment(Pos.CENTER);
                    HBox hBox = new HBox(10);
                    hBox.getChildren().addAll(btNo, btYes);
                    hBox.setAlignment(Pos.CENTER);
                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(label, hBox);
                    vBox.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(vBox, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Delete");
                    stage.show();

                    btYes.setOnAction(event1 -> {
                        try {
                            String deleteQuery = "delete from supplier where ssn = ?;";
                            PreparedStatement statement = con.prepareStatement(deleteQuery);
                            statement.setInt(1, supplier.getSsn());
                            statement.executeUpdate();
                            suppliers.remove(supplier);
                            table.getItems().remove(supplier);
                            stage.close();
                        } catch (SQLException e) {
                            Label label1 = new Label("Error deleting the supplier");
                            label1.setAlignment(Pos.CENTER);
                            Scene scene1 = new Scene(label1, 300, 100);
                            Stage stage1 = new Stage();
                            stage1.setScene(scene1);
                            stage1.setTitle("Error");
                            stage1.show();
                        }
                    });

                    btNo.setOnAction(event1 -> {
                        stage.close();
                    });
                }
            });

            HBox hBox = new HBox(10);
            hBox.getChildren().addAll(btAdd, btDelete);
            hBox.setAlignment(Pos.CENTER);


            GridPane gridPane = new GridPane();
            gridPane.add(tfSSN, 0, 0);
            gridPane.add(tfName, 1, 0);
            gridPane.add(tfCompany, 0, 1);
            gridPane.add(tfAddress, 1, 1);
            gridPane.add(tfPhone, 0, 2);
            gridPane.add(hBox, 1, 2);

            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            root.setBottom(gridPane);


            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 600, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Suppliers");
            stage.show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void orders() {
        try {
            ArrayList<Order> orders = new ArrayList<>();
            String query = "select o.orderId, e.employeeName, o.car, o.price, o.address, o.orderDate, o.customer\n" +
                    "from delievery_order o, employee e\n" +
                    "where o.employee = e.ssn;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                String employee = rs.getString("employeeName");
                String car = rs.getString("car");
                double price = rs.getDouble("price");
                String address = rs.getString("address");
                String orderDate = rs.getString("orderDate");
                String customer = rs.getString("customer");
                orders.add(new Order(orderId, employee, car, price, address, orderDate, customer));
            }

            TableView<Order> table = new TableView<>();

            TableColumn<Order, Integer> orderIdCol = new TableColumn<>("Order ID");
            orderIdCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
            table.getColumns().add(orderIdCol);

            TableColumn<Order, String> employeeCol = new TableColumn<>("Employee");
            employeeCol.setCellValueFactory(new PropertyValueFactory<Order, String>("employee"));
            table.getColumns().add(employeeCol);


            TableColumn<Order, String> carCol = new TableColumn<>("Car");
            carCol.setCellValueFactory(new PropertyValueFactory<Order, String>("car"));
            table.getColumns().add(carCol);

            TableColumn<Order, Double> priceCol = new TableColumn<>("Price");
            priceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
            table.getColumns().add(priceCol);


            TableColumn<Order, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
            table.getColumns().add(addressCol);

            TableColumn<Order, String> orderDateCol = new TableColumn<>("Order Date");
            orderDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
            table.getColumns().add(orderDateCol);


            TableColumn<Order, String> customerCol = new TableColumn<>("Customer");
            customerCol.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
            table.getColumns().add(customerCol);

            table.getItems().addAll(orders);

            BorderPane root = new BorderPane();
            root.setCenter(table);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 750, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Orders");
            stage.show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cars() {
        try {
            ArrayList<Car> cars = new ArrayList<>();
            String query = "select * from car;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                String plateNumber = rs.getString("plateNumber");
                String purchaseDate = rs.getString("purchaseDate");
                String warehouse = rs.getString("warehouse");
                cars.add(new Car(plateNumber, purchaseDate, warehouse));
            }

            TableView<Car> table = new TableView<>();
            table.setEditable(true);

            TableColumn<Car, String> plateNumberCol = new TableColumn<>("Plate Number");
            plateNumberCol.setCellValueFactory(new PropertyValueFactory<Car, String>("plateNumber"));
            table.getColumns().add(plateNumberCol);
            plateNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
            plateNumberCol.setOnEditCommit(event -> {
                Car car = event.getRowValue();
                String oldPlateNumber = car.getPlateNumber();
                String newPlateNumber = event.getNewValue();

                if (cars.contains(new Car(newPlateNumber))) {
                    Label label = new Label("Error updating the plate number");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }

                car.setPlateNumber(newPlateNumber);
                try {
                    String updateQuery = "update car \n" +
                            "set plateNumber = " + newPlateNumber + "\n" +
                            "where plateNumber = " + oldPlateNumber + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the plate number");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Car, String> purchaseDateCol = new TableColumn<>("Purchase Date");
            purchaseDateCol.setCellValueFactory(new PropertyValueFactory<Car, String>("purchaseDate"));
            table.getColumns().add(purchaseDateCol);
            purchaseDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            purchaseDateCol.setOnEditCommit(event -> {
                Car car = event.getRowValue();
                String newPurchaseDate = event.getNewValue();
                car.setPurchaseDate(newPurchaseDate);
                try {
                    String updateQuery = "update car \n" +
                            "set purchaseDate = \'" + newPurchaseDate + "\'\n" +
                            "where plateNumber = " + car.getPlateNumber() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the purchase date");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Car, String> warehouseCol = new TableColumn<>("Warehouse");
            warehouseCol.setCellValueFactory(new PropertyValueFactory<Car, String>("warehouse"));
            table.getColumns().add(warehouseCol);
            warehouseCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warehouseCol.setOnEditCommit(event -> {
                Car car = event.getRowValue();
                String newWarehouse = event.getNewValue();
                car.setWarehouse(newWarehouse);
                try {
                    String updateQuery = "update car \n" +
                            "set warehouse = \'" + newWarehouse + "\'\n" +
                            "where plateNumber = " + car.getPlateNumber() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the warehouse");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            table.getItems().addAll(cars);

            BorderPane root = new BorderPane();
            root.setCenter(table);

            Scene scene = new Scene(root, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cars");
            stage.show();
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void delivery() {
        try {
            ArrayList<Delivery> employees = new ArrayList<>();
            String query = "select *\n" +
                    "from employee e, delivery d\n" +
                    "where e.ssn = d.employee;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                int ssn = rs.getInt("ssn");
                String name = rs.getString("employeeName");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int supervisor = rs.getInt("supervisor");
                String hiringDate = rs.getString("hiringDate");
                double salary = rs.getDouble("salary");
                employees.add(new Delivery(ssn, name, age, address, phone, supervisor, hiringDate, salary));
            }

            TableView<Delivery> table = new TableView<>();
            table.setEditable(true);

            TableColumn<Delivery, Integer> ssnCol = new TableColumn<>("SSN");
            ssnCol.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("ssn"));
            table.getColumns().add(ssnCol);
            ssnCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            ssnCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                int oldSsn = employee.getSsn();
                int newSSN = event.getNewValue();

                if (employees.contains(new Employee(newSSN))) {
                    Label label = new Label("Error updating the ssn");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }

                employee.setSsn(newSSN);
                try {
                    String updateQuery = "update employee \n" +
                            "set ssn = " + newSSN + "\n" +
                            "where ssn = " + oldSsn + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the ssn");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Delivery, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<Delivery, String>("name"));
            table.getColumns().add(nameCol);
            nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                String newName = event.getNewValue();
                employee.setName(newName);
                try {
                    String updateQuery = "update employee \n" +
                            "set employeeName = \'" + newName + "\'\n" +
                            "where ssn = \'" + employee.getSsn() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the name");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Delivery, Integer> ageCol = new TableColumn<>("Age");
            ageCol.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("age"));
            table.getColumns().add(ageCol);
            ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            ageCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                int newAge = event.getNewValue();
                employee.setAge(newAge);
                try {
                    String updateQuery = "update employee \n" +
                            "set age = " + newAge + "\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the age");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Delivery, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<Delivery, String>("address"));
            table.getColumns().add(addressCol);
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                String newAddress = event.getNewValue();
                employee.setAddress(newAddress);
                try {
                    String updateQuery = "update employee \n" +
                            "set address = \'" + newAddress + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the address");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Delivery, String> phoneCol = new TableColumn<>("Phone");
            phoneCol.setCellValueFactory(new PropertyValueFactory<Delivery, String>("phone"));
            table.getColumns().add(phoneCol);
            phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                String newPhone = event.getNewValue();
                employee.setPhone(newPhone);
                try {
                    String updateQuery = "update employee \n" +
                            "set phone = \'" + newPhone + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the phone");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Delivery, Integer> supervisorCol = new TableColumn<>("Supervisor");
            supervisorCol.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("supervisor"));
            table.getColumns().add(supervisorCol);
            supervisorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            supervisorCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                int newSupervisor = event.getNewValue();
                employee.setSupervisor(newSupervisor);
                try {
                    String updateQuery = "update employee \n" +
                            "set supervisor = " + newSupervisor + "\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the supervisor");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Delivery, String> hiringDateCol = new TableColumn<>("Hiring Date");
            hiringDateCol.setCellValueFactory(new PropertyValueFactory<Delivery, String>("hiringDate"));
            table.getColumns().add(hiringDateCol);
            hiringDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            hiringDateCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                String newHiringDate = event.getNewValue();
                employee.setHiringDate(newHiringDate);
                try {
                    String updateQuery = "update employee \n" +
                            "set hiringDate = \'" + newHiringDate + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the hiring date");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<Delivery, Double> salaryCol = new TableColumn<>("Salary");
            salaryCol.setCellValueFactory(new PropertyValueFactory<Delivery, Double>("salary"));
            table.getColumns().add(salaryCol);
            salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            salaryCol.setOnEditCommit(event -> {
                Delivery employee = event.getRowValue();
                double newSalary = event.getNewValue();
                employee.setSalary(newSalary);
                try {
                    String updateQuery = "update delivery \n" +
                            "set salary = " + newSalary + "\n" +
                            "where employee = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the salary");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            table.getItems().addAll(employees);

            BorderPane root = new BorderPane();
            root.setCenter(table);

            Scene scene = new Scene(root, 750, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Delivery Employees");
            stage.show();

            Button btAdd = new Button("Add");
            Button btDelete = new Button("Delete");
            TextField tfSSN = new TextField();
            tfSSN.setPromptText("SSN");
            TextField tfName = new TextField();
            tfName.setPromptText("Name");
            TextField tfAge = new TextField();
            tfAge.setPromptText("Age");
            TextField tfAddress = new TextField();
            tfAddress.setPromptText("Address");
            TextField tfPhone = new TextField();
            tfPhone.setPromptText("Phone");
            TextField tfSupervisor = new TextField();
            tfSupervisor.setPromptText("Supervisor");
            TextField tfHiringDate = new TextField();
            tfHiringDate.setPromptText("Hiring Date");
            TextField tfSalary = new TextField();
            tfSalary.setPromptText("Salary");
            HBox hbButtons = new HBox(10);
            hbButtons.getChildren().addAll(btDelete, btAdd);
            hbButtons.setAlignment(Pos.CENTER);

            GridPane gridPane = new GridPane();
            gridPane.add(tfSSN, 0, 0);
            gridPane.add(tfName, 1, 0);
            gridPane.add(tfAge, 0, 1);
            gridPane.add(tfAddress, 1, 1);
            gridPane.add(tfPhone, 0, 2);
            gridPane.add(tfSupervisor, 1, 2);
            gridPane.add(tfHiringDate, 0, 3);
            gridPane.add(tfSalary, 1, 3);
            gridPane.add(hbButtons, 1, 4);
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            root.setBottom(gridPane);

            btAdd.setOnAction(event -> {
                int ssn = Integer.parseInt(tfSSN.getText());
                String name = tfName.getText();
                int age = Integer.parseInt(tfAge.getText());
                String address = tfAddress.getText();
                String phone = tfPhone.getText();
                int supervisor = Integer.parseInt(tfSupervisor.getText());
                String hiringDate = tfHiringDate.getText();
                double salary = Double.parseDouble(tfSalary.getText());
                Delivery employee = new Delivery(ssn, name, age, address, phone, supervisor, hiringDate, salary);

                if (employees.contains(employee)) {
                    Label label = new Label("Error adding the employee");
                    label.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();
                    return;
                }

                try {
                    String insertQuery = "insert into employee() " +
                            "values(" + ssn + ", \'" + name + "\', " + age + ", \'" + address + "\', \'" + phone + "\', " + supervisor + ", \'" + hiringDate + "\');";
                    PreparedStatement statement = con.prepareStatement(insertQuery);
                    statement.executeUpdate();
                    String insertQuery1 = "insert into delivery() " +
                            "values(" + ssn + ", " + salary + ");";
                    PreparedStatement statement1 = con.prepareStatement(insertQuery1);
                    statement1.executeUpdate();
                    employees.add(employee);
                    table.getItems().add(employee);
                } catch (SQLException e) {
                    Label label = new Label("Error adding the employee");
                    label.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();
                }
            });

            btDelete.setOnAction(event -> {
                Delivery employee = table.getSelectionModel().getSelectedItem();

                if (employee == null) {
                    Label label = new Label("Please select an employee to delete");
                    label.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();
                    return;
                } else {

                    Button btYes = new Button("Yes");
                    Button btNo = new Button("No");
                    Label label = new Label("Are you sure you want to delete this employee?");
                    label.setAlignment(Pos.CENTER);
                    HBox hbButtons1 = new HBox(10);
                    hbButtons1.getChildren().addAll(btYes, btNo);
                    hbButtons1.setAlignment(Pos.CENTER);
                    VBox root1 = new VBox(10);
                    root1.getChildren().addAll(label, hbButtons1);
                    root1.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(root1, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Delete");
                    stage1.show();
                    btYes.setOnAction(event1 -> {
                        try {
                            String deleteQuery = "delete from employee \n" +
                                    "where ssn = " + employee.getSsn() + ";";
                            PreparedStatement statement = con.prepareStatement(deleteQuery);
                            statement.executeUpdate();
                            employees.remove(employee);
                            table.getItems().remove(employee);
                            stage1.close();
                        } catch (SQLException e) {
                            Label label1 = new Label("Error deleting the employee");
                            label1.setAlignment(Pos.CENTER);
                            Scene scene2 = new Scene(label1, 300, 100);
                            Stage stage2 = new Stage();
                            stage2.setScene(scene2);
                            stage2.setTitle("Error");
                            stage2.show();
                        }
                    });
                    btNo.setOnAction(event1 -> {
                        stage1.close();
                    });
                }
            });

            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void warehouseEmployees() {
        /* To read data from the database and present them in a TableView,
         also we used setOnEditCommit to edit on data from the cells */
        try {
            ArrayList<WarehouseEmployee> employees = new ArrayList<>();
            String query = "select *\n" +
                    "from employee e, warehouseemployee w\n" +
                    "where e.ssn = w.employee;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                int ssn = rs.getInt("ssn");
                String name = rs.getString("employeeName");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int supervisor = rs.getInt("supervisor");
                String hiringDate = rs.getString("hiringDate");
                String warehouseAddress = rs.getString("warehouse");
                double salary = rs.getDouble("salary");
                employees.add(new WarehouseEmployee(ssn, name, age, address, phone, supervisor, hiringDate, warehouseAddress, salary));
            }

            TableView<WarehouseEmployee> table = new TableView<>();
            table.setEditable(true);

            TableColumn<WarehouseEmployee, Integer> ssnCol = new TableColumn<>("SSN");
            ssnCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, Integer>("ssn"));
            table.getColumns().add(ssnCol);
            ssnCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            ssnCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                int oldSsn = employee.getSsn();
                int newSSN = event.getNewValue();

                if (employees.contains(new Employee(newSSN))) {
                    Label label = new Label("Error updating the ssn");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }

                employee.setSsn(newSSN);
                try {
                    String updateQuery = "update employee \n" +
                            "set ssn = " + newSSN + "\n" +
                            "where ssn = " + oldSsn + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the ssn");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, String>("name"));
            table.getColumns().add(nameCol);
            nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                String newName = event.getNewValue();
                employee.setName(newName);
                try {
                    String updateQuery = "update employee \n" +
                            "set employeeName = \'" + newName + "\'\n" +
                            "where ssn = \'" + employee.getSsn() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the name");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, Integer> ageCol = new TableColumn<>("Age");
            ageCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, Integer>("age"));
            table.getColumns().add(ageCol);
            ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            ageCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                int newAge = event.getNewValue();
                employee.setAge(newAge);
                try {
                    String updateQuery = "update employee \n" +
                            "set age = " + newAge + "\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the age");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, String>("address"));
            table.getColumns().add(addressCol);
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                String newAddress = event.getNewValue();
                employee.setAddress(newAddress);
                try {
                    String updateQuery = "update employee \n" +
                            "set address = \'" + newAddress + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the address");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, String> phoneCol = new TableColumn<>("Phone");
            phoneCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, String>("phone"));
            table.getColumns().add(phoneCol);
            phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                String newPhone = event.getNewValue();
                employee.setPhone(newPhone);
                try {
                    String updateQuery = "update employee \n" +
                            "set phone = \'" + newPhone + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the phone");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, Integer> supervisorCol = new TableColumn<>("Supervisor");
            supervisorCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, Integer>("supervisor"));
            table.getColumns().add(supervisorCol);
            supervisorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            supervisorCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                int newSupervisor = event.getNewValue();
                employee.setSupervisor(newSupervisor);
                try {
                    String updateQuery = "update employee \n" +
                            "set supervisor = " + newSupervisor + "\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the supervisor");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, String> hiringDateCol = new TableColumn<>("Hiring Date");
            hiringDateCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, String>("hiringDate"));
            table.getColumns().add(hiringDateCol);
            hiringDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            hiringDateCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                String newHiringDate = event.getNewValue();
                employee.setHiringDate(newHiringDate);
                try {
                    String updateQuery = "update employee \n" +
                            "set hiringDate = \'" + newHiringDate + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the hiring date");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, String> warehouseAddressCol = new TableColumn<>("Warehouse Address");
            warehouseAddressCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, String>("warehouseAddress"));
            table.getColumns().add(warehouseAddressCol);
            warehouseAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warehouseAddressCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                String newWarehouseAddress = event.getNewValue();
                employee.setWarehouseAddress(newWarehouseAddress);
                try {
                    String updateQuery = "update warehouseemployee \n" +
                            "set warehouse = \'" + newWarehouseAddress + "\'\n" +
                            "where employee = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the warehouse address");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<WarehouseEmployee, Double> salaryCol = new TableColumn<>("Salary");
            salaryCol.setCellValueFactory(new PropertyValueFactory<WarehouseEmployee, Double>("salary"));
            table.getColumns().add(salaryCol);
            salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            salaryCol.setOnEditCommit(event -> {
                WarehouseEmployee employee = event.getRowValue();
                double newSalary = event.getNewValue();
                employee.setSalary(newSalary);
                try {
                    String updateQuery = "update warehouseemployee \n" +
                            "set salary = " + newSalary + "\n" +
                            "where employee = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the salary");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            table.getItems().addAll(employees);

            BorderPane root = new BorderPane();
            root.setCenter(table);

            Scene scene = new Scene(root, 750, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Warehouse Employees");
            stage.show();

            Button btAdd = new Button("Add");
            Button btDelete = new Button("Delete");
            TextField tfSSN = new TextField();
            tfSSN.setPromptText("SSN");
            TextField tfName = new TextField();
            tfName.setPromptText("Name");
            TextField tfAge = new TextField();
            tfAge.setPromptText("Age");
            TextField tfAddress = new TextField();
            tfAddress.setPromptText("Address");
            TextField tfPhone = new TextField();
            tfPhone.setPromptText("Phone");
            TextField tfSupervisor = new TextField();
            tfSupervisor.setPromptText("Supervisor");
            TextField tfHiringDate = new TextField();
            tfHiringDate.setPromptText("Hiring Date");
            TextField tfWarehouseAddress = new TextField();
            tfWarehouseAddress.setPromptText("Warehouse Address");
            TextField tfSalary = new TextField();
            tfSalary.setPromptText("Salary");
            HBox hbButtons = new HBox(10);
            hbButtons.getChildren().addAll(btDelete, btAdd);
            hbButtons.setAlignment(Pos.CENTER);

            GridPane gridPane = new GridPane();
            gridPane.add(tfSSN, 0, 0);
            gridPane.add(tfName, 1, 0);
            gridPane.add(tfAge, 0, 1);
            gridPane.add(tfAddress, 1, 1);
            gridPane.add(tfPhone, 0, 2);
            gridPane.add(tfSupervisor, 1, 2);
            gridPane.add(tfHiringDate, 0, 3);
            gridPane.add(tfWarehouseAddress, 1, 3);
            gridPane.add(tfSalary, 0, 4);
            gridPane.add(hbButtons, 1, 4);
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            root.setBottom(gridPane);

            btAdd.setOnAction(event -> {
                int ssn = Integer.parseInt(tfSSN.getText());
                String name = tfName.getText();
                int age = Integer.parseInt(tfAge.getText());
                String address = tfAddress.getText();
                String phone = tfPhone.getText();
                int supervisor = Integer.parseInt(tfSupervisor.getText());
                String hiringDate = tfHiringDate.getText();
                String warehouseAddress = tfWarehouseAddress.getText();
                double salary = Double.parseDouble(tfSalary.getText());
                WarehouseEmployee employee = new WarehouseEmployee(ssn, name, age, address, phone, supervisor, hiringDate, warehouseAddress, salary);

                if (employees.contains(employee)) {
                    Label label = new Label("This employee already exists");
                    label.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();
                    return;
                }


                try {
                    String insertQuery = "insert into employee() " +
                            "values(" + ssn + ", \'" + name + "\', " + age + ", \'" + address + "\', \'" + phone + "\', " + supervisor + ", \'" + hiringDate + "\');";
                    PreparedStatement statement = con.prepareStatement(insertQuery);
                    statement.executeUpdate();
                    String insertQuery1 = "insert into warehouseemployee() " +
                            "values(" + ssn + ", \'" + warehouseAddress + "\' + salary);";
                    PreparedStatement statement1 = con.prepareStatement(insertQuery1);
                    statement1.executeUpdate();
                    employees.add(employee);
                    table.getItems().add(employee);
                } catch (SQLException e) {
                    Label label = new Label("Error adding the employee");
                    label.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();
                }
            });

            btDelete.setOnAction(event -> {
                WarehouseEmployee employee = table.getSelectionModel().getSelectedItem();

                if (employee == null) {
                    Label label = new Label("Please select an employee to delete");
                    label.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();
                    return;
                } else {
                    Button btNo = new Button("No");
                    Button btYes = new Button("Yes");
                    Label label = new Label("Are you sure you want to delete this employee?");
                    HBox hBox = new HBox(10);
                    hBox.getChildren().addAll(btNo, btYes);
                    hBox.setAlignment(Pos.CENTER);
                    BorderPane root1 = new BorderPane();
                    root1.setCenter(label);
                    root1.setBottom(hBox);
                    Scene scene1 = new Scene(root1, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Delete");
                    stage1.show();
                    btYes.setOnAction(event1 -> {
                        try {
                            String deleteQuery = "delete from employee \n" +
                                    "where ssn = " + employee.getSsn() + ";";
                            PreparedStatement statement = con.prepareStatement(deleteQuery);
                            statement.executeUpdate();
                            employees.remove(employee);
                            table.getItems().remove(employee);
                        } catch (SQLException e) {
                            Label label1 = new Label("Error deleting the employee");
                            label1.setAlignment(Pos.CENTER);
                            Scene scene2 = new Scene(label1, 300, 100);
                            Stage stage2 = new Stage();
                            stage2.setScene(scene2);
                            stage2.setTitle("Error");
                            stage2.show();
                        }
                        stage1.close();
                    });
                    btNo.setOnAction(event1 -> {
                        stage1.close();
                    });
                }
            });
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void warehouse() {
        /* To read data from the database and present them in a TableView,
         also we used setOnEditCommit to edit on data from the cells */
        try {
            ArrayList<Warehouse> warehouses = new ArrayList<>();
            String query = "select w.address, w.landline, e.employeeName, w.capacity, w.space " +
                    "from warehouse w, employee e where manager = ssn;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                String address = rs.getString("address");
                String landline = rs.getString("landline");
                String manager = rs.getString("employeeName");
                int capacity = rs.getInt("capacity");
                int space = rs.getInt("space");
                warehouses.add(new Warehouse(address, landline, capacity, space, manager));
            }

            TableView<Warehouse> table = new TableView<>();
            table.setEditable(true);

            TableColumn<Warehouse, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("address"));
            table.getColumns().add(addressCol);
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(event -> {
                Warehouse warehouse = event.getRowValue();
                String oldAddress = warehouse.getAddress();
                String newAddress = event.getNewValue();

                if (warehouses.contains(new Warehouse(newAddress, null, 0, 0, null))) {
                    Label label = new Label("This address already exists");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }

                warehouse.setAddress(newAddress);
                try {
                    String updateQuery = "update warehouse \n" +
                            "set address = \'" + newAddress + "\'\n" +
                            "where address = \'" + oldAddress + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            TableColumn<Warehouse, String> landlineCol = new TableColumn<>("Landline");
            landlineCol.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("landline"));
            table.getColumns().add(landlineCol);
            landlineCol.setCellFactory(TextFieldTableCell.forTableColumn());
            landlineCol.setOnEditCommit(event -> {
                Warehouse warehouse = event.getRowValue();
                String newLandLine = event.getNewValue();
                warehouse.setLandline(newLandLine);
                try {
                    String updateQuery = "update warehouse \n" +
                            "set landline = \'" + newLandLine + "\'\n" +
                            "where address = \'" + warehouse.getAddress() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            TableColumn<Warehouse, String> managerCol = new TableColumn<>("Manager");
            managerCol.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("manager"));
            table.getColumns().add(managerCol);
            managerCol.setCellFactory(TextFieldTableCell.forTableColumn());
            managerCol.setOnEditCommit(event -> {
                Warehouse warehouse = event.getRowValue();
                String newManager = event.getNewValue();

                try {
                    String updateQuery = "update warehouse \n" +
                            "set manager = (select e.ssn from employee e where e.employeeName = \'" + newManager + "\')\n" +
                            "where address = \'" + warehouse.getAddress() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                    warehouse.setManager(newManager);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            TableColumn<Warehouse, Integer> capacityCol = new TableColumn<>("Capacity");
            capacityCol.setCellValueFactory(new PropertyValueFactory<Warehouse, Integer>("capacity"));
            table.getColumns().add(capacityCol);
            capacityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            capacityCol.setOnEditCommit(event -> {
                Warehouse warehouse = event.getRowValue();
                int newCapacity = event.getNewValue();

                try {
                    String updateQuery = "update warehouse \n" +
                            "set capacity = " + newCapacity + "\n" +
                            "where address = \'" + warehouse.getAddress() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                    warehouse.setCapacity(newCapacity);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            TableColumn<Warehouse, Integer> spaceCol = new TableColumn<>("Space");
            spaceCol.setCellValueFactory(new PropertyValueFactory<Warehouse, Integer>("space"));
            table.getColumns().add(spaceCol);
            spaceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            spaceCol.setOnEditCommit(event -> {
                Warehouse warehouse = event.getRowValue();
                int newSpace = event.getNewValue();

                try {
                    String updateQuery = "update warehouse \n" +
                            "set space = " + newSpace + "\n" +
                            "where address = \'" + warehouse.getAddress() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                    warehouse.setSpace(newSpace);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            table.getItems().addAll(warehouses);

            BorderPane root = new BorderPane();
            root.setCenter(table);

            TextField tfAddress = new TextField();
            tfAddress.setPromptText("Address");
            TextField tfLandline = new TextField();
            tfLandline.setPromptText("Landline");
            TextField tfManager = new TextField();
            tfManager.setPromptText("Manager");
            TextField tfCapacity = new TextField();
            tfCapacity.setPromptText("Capacity");
            TextField tfSpace = new TextField();
            tfSpace.setPromptText("Space");
            Button btAdd = new Button("Add");
            Button btDelete = new Button("Delete");
            HBox hbButtons = new HBox(10);
            hbButtons.getChildren().addAll(btAdd, btDelete);
            hbButtons.setAlignment(Pos.CENTER);

            GridPane gridPane = new GridPane();
            gridPane.add(tfAddress, 0, 0);
            gridPane.add(tfLandline, 1, 0);
            gridPane.add(tfManager, 0, 1);
            gridPane.add(tfCapacity, 1, 1);
            gridPane.add(tfSpace, 0, 2);
            gridPane.add(hbButtons, 1, 2);
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            root.setBottom(gridPane);

            btAdd.setOnAction(event -> {
                String address = tfAddress.getText();
                String landline = tfLandline.getText();
                String manager = tfManager.getText();
                int capacity = Integer.parseInt(tfCapacity.getText());
                int space = Integer.parseInt(tfSpace.getText());
                Warehouse warehouse = new Warehouse(address, landline, capacity, space, manager);

                if (warehouses.contains(warehouse)) {
                    Label label = new Label("This warehouse already exists");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }


                try {
                    String insertQuery = "insert into warehouse()" +
                            " values(\'" + address + "\', \'" + landline + "\', " + capacity + ", " + space + ", " +
                            "(select e.ssn " +
                            "from employee e " +
                            "where e.employeeName = \'" + manager + "\'));";
                    PreparedStatement statement = con.prepareStatement(insertQuery);
                    warehouses.add(warehouse);
                    table.getItems().add(warehouse);

                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            btDelete.setOnAction(event -> {
                Warehouse warehouse = table.getSelectionModel().getSelectedItem();
                if (warehouse == null) {
                    Label label = new Label("Please select a warehouse to delete");
                    label.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(label, 300, 100);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Error");
                    stage1.show();
                    return;
                } else {
                    Button btYes = new Button("Yes");
                    Button btNo = new Button("No");
                    Label label = new Label("Are you sure you want to delete this warehouse?");
                    HBox hBox = new HBox(10);
                    hBox.getChildren().addAll(btNo, btYes);
                    hBox.setAlignment(Pos.CENTER);
                    BorderPane root1 = new BorderPane();
                    root1.setCenter(label);
                    root1.setBottom(hBox);
                    Scene scene = new Scene(root1, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Delete");
                    stage.show();
                    btYes.setOnAction(event1 -> {
                        try {
                            String deleteQuery = "delete from warehouse \n" +
                                    "where address = \'" + warehouse.getAddress() + "\';";
                            PreparedStatement statement = con.prepareStatement(deleteQuery);
                            statement.executeUpdate();
                            warehouses.remove(warehouse);
                            table.getItems().remove(warehouse);
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        stage.close();
                    });
                    btNo.setOnAction(event1 -> {
                        stage.close();
                    });
                }

            });

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Warehouse");
            stage.show();
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void customers() {
        /* To read data from the database and present them in a TableView,
         also we used setOnEditCommit to edit on data from the cells */
        try {
            ArrayList<Customer> customers = new ArrayList<>();
            String query = "select * from customer;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                String name = rs.getString("customerName");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                customers.add(new Customer(phone, name, address));
            }

            TableView<Customer> table = new TableView<>();


            /* Creating the TableView and setting it editable */
            TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
            table.getColumns().add(nameCol);


            TableColumn<Customer, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
            table.getColumns().add(addressCol);

            TableColumn<Customer, String> phoneCol = new TableColumn<>("Phone");
            phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNum"));
            table.getColumns().add(phoneCol);

            table.getItems().addAll(customers);

            BorderPane root = new BorderPane();
            root.setCenter(table);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 400, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Customers");
            stage.show();
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void products() {
        /* To read data from the database and present them in a TableView,
         also we used setOnEditCommit to edit on data from the cells */
        try {
            ArrayList<Product> products = new ArrayList<>();
            String query = "select * from product;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("productName");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id, name, quantity, price));
            }

            TableView<Product> table = new TableView<>();
            table.setEditable(true);

            TableColumn<Product, Integer> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
            table.getColumns().add(idCol);

            TableColumn<Product, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            table.getColumns().add(nameCol);
            nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

            TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
            priceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            table.getColumns().add(priceCol);
            priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            priceCol.setOnEditCommit(event -> {
                Product product = event.getRowValue();
                double newPrice = event.getNewValue();

                try {
                    String updateQuery = "update product \n" +
                            "set price = " + newPrice + "\n" +
                            "where id = " + product.getId() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                    product.setPrice(newPrice);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            TableColumn<Product, Integer> quantityCol = new TableColumn<>("Quantity");
            quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
            table.getColumns().add(quantityCol);

            table.getItems().addAll(products);

            BorderPane root = new BorderPane();
            root.setCenter(table);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Scene scene = new Scene(root, 400, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Products");
            stage.show();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    private void branchEmployee() {
         /* To read data from the database and present them in a TableView,
         also we used setOnEditCommit to edit on data from the cells */
        try {
            ArrayList<BranchEmployee> employees = new ArrayList<>();
            String query = "select *\n" +
                    "from employee e, branchemployee b\n" +
                    "where e.ssn = b.employee;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                int ssn = rs.getInt("ssn");
                String name = rs.getString("employeeName");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int supervisor = rs.getInt("supervisor");
                String hiringDate = rs.getString("hiringDate");
                String branchAddress = rs.getString("branch");
                double salary = rs.getDouble("salary");
                employees.add(new BranchEmployee(ssn, name, age, address, phone, supervisor, hiringDate, branchAddress, salary));
            }

            TableView<BranchEmployee> table = new TableView<>();
            table.setEditable(true);

            TableColumn<BranchEmployee, Integer> ssnCol = new TableColumn<>("SSN");
            ssnCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, Integer>("ssn"));
            table.getColumns().add(ssnCol);
            ssnCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            ssnCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                int oldSsn = employee.getSsn();
                int newSSN = event.getNewValue();

                if (employees.contains(new Employee(newSSN))) {
                    Label label = new Label("This SSN already exists");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }

                try {
                    String updateQuery = "update employee \n" +
                            "set ssn = " + newSSN + "\n" +
                            "where ssn = " + oldSsn + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                    employee.setSsn(newSSN);
                } catch (SQLException e) {
                    Label label = new Label("Error updating the ssn");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<BranchEmployee, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, String>("name"));
            table.getColumns().add(nameCol);
            nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                String newName = event.getNewValue();
                employee.setName(newName);
                try {
                    String updateQuery = "update employee \n" +
                            "set employeeName = \'" + newName + "\'\n" +
                            "where ssn = \'" + employee.getSsn() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the name");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<BranchEmployee, Integer> ageCol = new TableColumn<>("Age");
            ageCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, Integer>("age"));
            table.getColumns().add(ageCol);
            ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            ageCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                int newAge = event.getNewValue();
                employee.setAge(newAge);
                try {
                    String updateQuery = "update employee \n" +
                            "set age = " + newAge + "\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the age");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });


            TableColumn<BranchEmployee, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, String>("address"));
            table.getColumns().add(addressCol);
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                String newAddress = event.getNewValue();
                employee.setAddress(newAddress);
                try {
                    String updateQuery = "update employee \n" +
                            "set address = \'" + newAddress + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the address");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<BranchEmployee, String> phoneCol = new TableColumn<>("Phone");
            phoneCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, String>("phone"));
            table.getColumns().add(phoneCol);
            phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                String newPhone = event.getNewValue();
                employee.setPhone(newPhone);
                try {
                    String updateQuery = "update employee \n" +
                            "set phone = \'" + newPhone + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the phone number");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<BranchEmployee, Integer> supervisorCol = new TableColumn<>("Supervisor");
            supervisorCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, Integer>("supervisor"));
            table.getColumns().add(supervisorCol);
            supervisorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            supervisorCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                int newSupervisor = event.getNewValue();
                employee.setSupervisor(newSupervisor);
                try {
                    String updateQuery = "update employee \n" +
                            "set supervisor = " + newSupervisor + "\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the supervisor");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<BranchEmployee, String> hiringDateCol = new TableColumn<>("Hiring Date");
            hiringDateCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, String>("hiringDate"));
            table.getColumns().add(hiringDateCol);
            hiringDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            hiringDateCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                String newHiringDate = event.getNewValue();
                employee.setHiringDate(newHiringDate);
                try {
                    String updateQuery = "update employee \n" +
                            "set hiringDate = \'" + newHiringDate + "\'\n" +
                            "where ssn = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the hiring date");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<BranchEmployee, String> branchAddressCol = new TableColumn<>("Branch Address");
            branchAddressCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, String>("branchAddress"));
            table.getColumns().add(branchAddressCol);
            branchAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            branchAddressCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                String newBranchAddress = event.getNewValue();
                employee.setBranchAddress(newBranchAddress);
                try {
                    String updateQuery = "update branchemployee \n" +
                            "set branch = \'" + newBranchAddress + "\'\n" +
                            "where employee = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the branch address");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            TableColumn<BranchEmployee, Double> salaryCol = new TableColumn<>("Salary");
            salaryCol.setCellValueFactory(new PropertyValueFactory<BranchEmployee, Double>("salary"));
            table.getColumns().add(salaryCol);
            salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            salaryCol.setOnEditCommit(event -> {
                BranchEmployee employee = event.getRowValue();
                double newSalary = event.getNewValue();
                employee.setSalary(newSalary);
                try {
                    String updateQuery = "update branchemployee \n" +
                            "set salary = " + newSalary + "\n" +
                            "where employee = " + employee.getSsn() + ";";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    Label label = new Label("Error updating the salary");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                }
            });

            table.getItems().addAll(employees);

            BorderPane root = new BorderPane();
            root.setCenter(table);


            TextField tfSSN = new TextField();
            tfSSN.setPromptText("SSN");
            TextField tfName = new TextField();
            tfName.setPromptText("Name");
            TextField tfAge = new TextField();
            tfAge.setPromptText("Age");
            TextField tfAddress = new TextField();
            tfAddress.setPromptText("Address");
            TextField tfPhone = new TextField();
            tfPhone.setPromptText("Phone");
            TextField tfSupervisor = new TextField();
            tfSupervisor.setPromptText("Supervisor");
            TextField tfHiringDate = new TextField();
            tfHiringDate.setPromptText("Hiring Date");
            TextField tfBranchAddress = new TextField();
            tfBranchAddress.setPromptText("Branch Address");
            TextField tfSalary = new TextField();
            tfSalary.setPromptText("Salary");
            Button btAdd = new Button("Add");
            Button btDelete = new Button("Delete");
            HBox hbButtons = new HBox(10);
            hbButtons.getChildren().addAll(btAdd, btDelete);
            hbButtons.setAlignment(Pos.CENTER);

            GridPane gridPane = new GridPane();
            gridPane.add(tfSSN, 0, 0);
            gridPane.add(tfName, 1, 0);
            gridPane.add(tfAge, 0, 1);
            gridPane.add(tfAddress, 1, 1);
            gridPane.add(tfPhone, 0, 2);
            gridPane.add(tfSupervisor, 1, 2);
            gridPane.add(tfHiringDate, 0, 3);
            gridPane.add(tfBranchAddress, 1, 3);
            gridPane.add(tfSalary, 0, 4);
            gridPane.add(hbButtons, 1, 4);
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            root.setBottom(gridPane);


            Stage stage = new Stage();
            stage.setScene(new Scene(root, 750, 650));
            stage.setTitle("Branch Employees");
            stage.show();

            /* Add a new Employee */
            btAdd.setOnAction(event -> {
                int ssn = Integer.parseInt(tfSSN.getText());
                String name = tfName.getText();
                int age = Integer.parseInt(tfAge.getText());
                String address = tfAddress.getText();
                String phone = tfPhone.getText();
                int supervisor = Integer.parseInt(tfSupervisor.getText());
                String hiringDate = tfHiringDate.getText();
                String branchAddress = tfBranchAddress.getText();
                double salary = Double.parseDouble(tfSalary.getText());
                BranchEmployee employee = new BranchEmployee(ssn, name, age, address, phone, supervisor, hiringDate, branchAddress, salary);

                if (employees.contains(employee)) {
                    Label label = new Label("This employee already exists");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage warning = new Stage();
                    warning.setScene(scene);
                    warning.setTitle("Error");
                    warning.show();
                    return;
                }

                table.getItems().add(employee);
                try {
                    String query1 = "insert into employee" +
                            " values(" + ssn + ", \'" + name + "\', " + age + ", \'" + address + "\', \'" + phone + "\', " +
                            supervisor + ", \'" + hiringDate + "\');";

                    String query2 = "insert into branchemployee" +
                            " values(" + ssn + ", \'" + branchAddress + "\', " + salary + ");";

                    PreparedStatement statement = con.prepareStatement(query1);
                    PreparedStatement statement2 = con.prepareStatement(query2);

                    statement.executeUpdate();
                    statement2.executeUpdate();
                    employees.add(employee);
                } catch (SQLException e) {
                    Label label = new Label("Error adding the employee\n");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage warning = new Stage();
                    warning.setScene(scene);
                    warning.setTitle("Error");
                    warning.show();
                }
            });

            /* Delete an Employee */
            btDelete.setOnAction(event -> {
                if (table.getSelectionModel().getSelectedItem() == null) {
                    Label label = new Label("Please select an employee to delete");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage warning = new Stage();
                    warning.setScene(scene);
                    warning.setTitle("Error");
                    warning.show();
                    return;
                } else {
                    Stage warning = new Stage();
                    GridPane gridPane1 = new GridPane();
                    Button btYes = new Button("Yes");
                    Button btNo = new Button("No");
                    HBox hbButtons1 = new HBox(10);
                    hbButtons1.getChildren().addAll(btNo, btYes);
                    hbButtons1.setAlignment(Pos.CENTER);
                    Label label = new Label("Are you sure you want to delete this employee?");
                    gridPane1.add(label, 0, 0);
                    gridPane1.add(hbButtons1, 0, 1);
                    gridPane1.setAlignment(Pos.CENTER);

                    btYes.setOnAction(event1 -> {
                        BranchEmployee employee = table.getSelectionModel().getSelectedItem();

                        try {
                            String deleteQuery1 = "delete from employee\n" +
                                    "where ssn = " + employee.getSsn() + ";";

                            PreparedStatement statement = con.prepareStatement(deleteQuery1);
                            statement.executeUpdate();
                            employees.remove(employee);
                            table.getItems().remove(employee);
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        warning.close();
                    });
                    btNo.setOnAction(event1 -> warning.close());
                    Scene scene = new Scene(gridPane1, 300, 200);
                    warning.setScene(scene);
                    warning.setTitle("Warning");
                    warning.show();
                }
            });

            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void branch() {

        /* To read data from the database and present them in a TableView,
         also we used setOnEditCommit to edit on data from the cells */
        try {
            ArrayList<Branch> branches = new ArrayList<>();
            String query = "select b.address, b.landline, e.employeeName as manager\n" +
                    "from branch b, employee e\n" +
                    "where b.manager = e.ssn;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /* Add the data to the TableView form the database */
            while (rs.next()) {
                String address = rs.getString("address");
                String landline = rs.getString("landline");
                String manager = rs.getString("manager");
                branches.add(new Branch(address, landline, manager));
            }

            TableView<Branch> table = new TableView<>();
            table.setEditable(true);

            TableColumn<Branch, String> addressCol = new TableColumn<>("Address");
            addressCol.setEditable(true);
            addressCol.setCellValueFactory(new PropertyValueFactory<Branch, String>("address"));
            table.getColumns().add(addressCol);
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(event -> {
                Branch branch = event.getRowValue();
                String oldAddress = branch.getAddress();
                String newAddress = event.getNewValue();
                if (branches.contains(new Branch(newAddress, "", ""))) {
                    Label label = new Label("This address already exists");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }
                branch.setAddress(newAddress);
                try {
                    String updateQuery = "update branch " +
                            "set address = \'" + newAddress + "\'" +
                            "where address = \'" + oldAddress + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            TableColumn<Branch, String> landlineCol = new TableColumn<>("Landline");
            landlineCol.setEditable(true);
            landlineCol.setCellValueFactory(new PropertyValueFactory<Branch, String>("landline"));
            table.getColumns().add(landlineCol);
            landlineCol.setCellFactory(TextFieldTableCell.forTableColumn());
            landlineCol.setOnEditCommit(event -> {
                Branch branch = event.getRowValue();
                String newLandLine = event.getNewValue();
                branch.setLandline(newLandLine);
                try {
                    String updateQuery = "update branch \n" +
                            "set landline = \'" + newLandLine + "\'\n" +
                            "where address = \'" + branch.getAddress() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            TableColumn<Branch, String> managerCol = new TableColumn<>("Manager");
            managerCol.setEditable(true);
            managerCol.setCellValueFactory(new PropertyValueFactory<Branch, String>("manager"));
            table.getColumns().add(managerCol);
            managerCol.setCellFactory(TextFieldTableCell.forTableColumn());
            managerCol.setOnEditCommit(event -> {
                Branch branch = event.getRowValue();
                String newManager = event.getNewValue();
                branch.setManager(newManager);
                try {
                    String updateQuery = "update branch \n" +
                            "set manager = \'" + newManager + "\'\n" +
                            "where address = \'" + branch.getAddress() + "\';";
                    PreparedStatement statement = con.prepareStatement(updateQuery);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            table.getItems().addAll(branches);

            BorderPane root = new BorderPane();
            root.setCenter(table);

            HBox hBox = new HBox(10);
            TextField tfAddress = new TextField();
            tfAddress.setPromptText("Address");
            TextField tfLandline = new TextField();
            tfLandline.setPromptText("Landline");
            TextField tfManager = new TextField();
            tfManager.setPromptText("Manager");
            Button btAdd = new Button("Add");
            btAdd.setOnAction(event -> {
                String address = tfAddress.getText();
                String landline = tfLandline.getText();
                String manager = tfManager.getText();
                Branch branch = new Branch(address, landline, manager);

                if (branches.contains(branch)) {
                    Label label = new Label("This branch already exists");
                    label.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(label, 300, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.show();
                    return;
                }

                try {
                    String insertQuery = "insert into branch()" +
                            " values(\'" + address + "\', \'" + landline + "\', " +
                            "(select e.ssn " +
                            "from employee e " +
                            "where e.employeeName = \'" + manager + "\'));";

                    PreparedStatement statement = con.prepareStatement(insertQuery);
                    statement.executeUpdate();
                    branches.add(branch);
                    table.getItems().add(branch);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            /* Deleting a branch */
            Button btDelete = new Button("Delete");
            btDelete.setOnAction(event -> {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Stage warning = new Stage();
                    GridPane gridPane = new GridPane();
                    Button btYes = new Button("Yes");
                    Button btNo = new Button("No");
                    HBox hbButtons = new HBox(10);
                    hbButtons.getChildren().addAll(btNo, btYes);
                    hbButtons.setAlignment(Pos.CENTER);
                    Label label = new Label("Are you sure you want to delete this branch?");
                    gridPane.add(label, 0, 0);
                    gridPane.add(hbButtons, 0, 1);
                    gridPane.setAlignment(Pos.CENTER);

                    btYes.setOnAction(event1 -> {
                        Branch branch = table.getSelectionModel().getSelectedItem();
                        branches.remove(branch);
                        table.getItems().remove(branch);
                        try {
                            String deleteQuery = "delete from branch\n" +
                                    "where address = \'" + branch.getAddress() + "\';";
                            PreparedStatement statement = con.prepareStatement(deleteQuery);
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        warning.close();
                    });
                    btNo.setOnAction(event1 -> warning.close());
                    Scene scene = new Scene(gridPane, 300, 200);
                    warning.setScene(scene);
                    warning.setTitle("Warning");
                    warning.show();
                }
            });

            hBox.getChildren().addAll(tfAddress, tfLandline, tfManager, btAdd, btDelete);
            hBox.setAlignment(Pos.CENTER);
            root.setBottom(hBox);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Branches");
            stage.show();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
