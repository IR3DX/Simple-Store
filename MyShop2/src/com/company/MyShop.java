package com.company;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MyShop extends JFrame implements ActionListener, MenuListener {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<User> admins = new ArrayList<>();
    static User current;
    static JPanel loginW = new JPanel(null);
    static JPanel mainW = new JPanel(null);
    static JPanel productW = new JPanel(null);
    static JPanel categoryW = new JPanel(null);
    static JPanel customerW = new JPanel(null);
    static JPanel orderW = new JPanel(null);
    static JPanel usersW = new JPanel(null);
    static JPanel formPP = new JPanel(null);
    static JTextField usernameF = new JTextField();
    static JTextField passwordF = new JPasswordField();
    static JTextField productSearchF = new JTextField();
    static JTextField formPPIDF = new JTextField();
    static JTextField formPPNameF = new JTextField();
    static JTextField formPPPriceF = new JTextField();
    static JTextField formPPQuantityF = new JTextField();
    static JTextField formPPDescriptionF = new JTextField();
    static JTextField IDCF = new JTextField();
    static JTextField firstNameCF = new JTextField();
    static JTextField lastNameCF = new JTextField();
    static JTextField telNumCF = new JTextField();
    static JTextField eMailCF = new JTextField();
    static JTextField categoryPidF = new JTextField();
    static JTextField categoryPNameF = new JTextField();
    static JTextField orderCID = new JTextField();
    static JTextField orderOID = new JTextField();
    static JTextField userPIDF = new JTextField();
    static JTextField userPNameF = new JTextField();
    static JTextField userPPasswordF = new JTextField();
    static JTextField userPFullNF = new JTextField();
    static JTextField userPTellF = new JTextField();
    static JTextField userPEmailF = new JTextField();
    static String[] formPPCategoryOptions = {"TV", "Phone", "Home appliances", "Car", "Accessories"};
    static JComboBox<String> formPPCategory;
    static {formPPCategory = new JComboBox<>(formPPCategoryOptions);}
    static JButton loginB = new JButton("Login");
    static JButton homeBproduct = new JButton("back");
    static JButton homeBcategory = new JButton("back");
    static JButton homeBcustomer = new JButton("back");
    static JButton homeBorder = new JButton("back");
    static JButton homeBusers = new JButton("back");
    static JButton backToLoginB = new JButton("Logout");
    static JButton addProductB = new JButton("Add product");
    static JButton removeProductB = new JButton("remove product");
    static JButton searchB = new JButton("search");
    static JButton formPPAdd = new JButton("Add");
    static JButton formPPCancel = new JButton("Cancel");
    static JButton cAddB = new JButton("Add");
    static JButton cDelB = new JButton("Remove");
    static JButton cClearB = new JButton("Clear");
    static JButton categgoryPInsertB= new JButton("Insert New Category");
    static JButton categgoryPDeleteB= new JButton("Delete Selected Category");
    static JButton orderInsertB = new JButton("Insert Order");
    static JButton orderProductRemoveB = new JButton("Remove product");
    static JButton orderClearB = new JButton("Clear");
    static JButton userAddB = new JButton("Add");
    static JButton userDelB = new JButton("Remove");
    static JButton userClearB = new JButton("Clear");
    static JMenuBar menu = new JMenuBar();
    static JMenu productM = new JMenu("Product");
    static JMenu categoryM = new JMenu("Category");
    static JMenu customerM = new JMenu("Customer");
    static JMenu orderM = new JMenu("Order");
    static JMenu usersM = new JMenu("Users");
    static String[] catagoriesColumns = {"ID", "Name"};
    static String[] usersColumns = {"ID", "username", "password", "fullname", "tel", "email"};
    static String[] customerColumns = {"ID", "First name", "Last name", "Tel", "Email"};
    static String[] productCustomerColumns = {"ID", "Name", "Price", "Quantity", "Quantity x Price"};
    static String[] productColumns = {"ID", "Name", "Price", "Quantity", "Description", "Category"};
    static String[][] productData = {{"0", "Samsung TV", "2000", "100", "Samsung smart TV", "TV"},
            {"1", "Charger HellCat", "50000", "10", "Charger HellCat Car", "Car"},
            {"2", "LG Fridge", "5000", "200", "LG Refrigerator", "Home appliances"},
            {"3", "Luna Microwave", "300", "100", "Luna multi-use kitchen microwave oven", "Home Appliances"},
            {"4", "iPhone X", "700", "50", "Apple iPhone X", "Phone"},
            {"5", "Huawei P10+", "700", "400", "Huawei P10+ phone", "Phone"}

    };
    static String[][] customerData = {{"0", "Shaker", "Shaker", "+9700000000", "Shaker@gmail.com"},
            {"1", "Ahmed", "Mohammed", "+9700000000", "Ahmed@gmail.com"},
            {"2", "Essam", "Shahla", "+9700000000", "Essam@gmail.com"},
            {"3", "Karam", "Saidam", "+9700000000", "Karam@gmail.com"},
            {"4", "Hamdy", "Ibrahim", "+9700000000", "Hamdy@Yahoo.org"},
            {"5", "Zoya", "Ranem", "+9700000000", "Zoya@outlook.net"}
    };
    static String[][] catagoriesData = {{"0", "TV"},
            {"1", "Car"},
            {"2", "Home Appliances"},
            {"3", "Phone"}
    };
    static String[][] userData = {{"0", "Shaker", "123","ShakerC", "+9700000000", "Shaker@gmail.com"},
            {"1", "Ahmed", "123", "AhmedC","+9700000000", "Ahmed@gmail.com"},
            {"2", "Essam", "123", "EssamC","+9700000000", "Essam@gmail.com"},
            {"3", "Karam", "123", "KaramC","+9700000000", "Karam@gmail.com"},
            {"4", "Hamdy", "123", "HamdyC","+9700000000", "Hamdy@Yahoo.org"},
            {"5", "Zoya", "123", "ZoyaC","+9700000000", "Zoya@outlook.net"}
    };

    static DefaultTableModel userTM = new DefaultTableModel(userData, usersColumns);
    static String[][] productCustomerData = {};
    static DefaultTableModel productTM = new DefaultTableModel(productData, productColumns);
    static DefaultTableModel customerTM = new DefaultTableModel(customerData, customerColumns);
    static JTable productT = new JTable(productTM) {
        public boolean isCellEditable(int data, int columns) {
            return false;
        }

        public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
            Component c = super.prepareRenderer(r, data, columns);
            if (data % 2 == 0) {
                c.setBackground(Color.WHITE);
            } else {
                c.setBackground(Color.LIGHT_GRAY);
            }
            if (isCellSelected(data, columns)) {
                c.setBackground(Color.YELLOW);
            }
            return c;
        }
    };
    static JTable customerT = new JTable(customerTM) {
        public boolean isCellEditable(int data, int columns) {
            return false;
        }

        public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
            Component c = super.prepareRenderer(r, data, columns);
            if (data % 2 == 0) {
                c.setBackground(Color.WHITE);
            } else {
                c.setBackground(Color.LIGHT_GRAY);
            }
            if (isCellSelected(data, columns)) {
                c.setBackground(Color.YELLOW);
            }
            return c;
        }
    };
    static JTable orderProductT = new JTable(productTM) {
        public boolean isCellEditable(int data, int columns) {
            return false;
        }

        public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
            Component c = super.prepareRenderer(r, data, columns);
            if (data % 2 == 0) {
                c.setBackground(Color.WHITE);
            } else {
                c.setBackground(Color.LIGHT_GRAY);
            }
            if (isCellSelected(data, columns)) {
                c.setBackground(Color.YELLOW);
            }
            return c;
        }
    };

    static DefaultTableModel categoryTM = new DefaultTableModel(catagoriesData, catagoriesColumns);
    static JTable categoryT = new JTable(categoryTM) {
        public boolean isCellEditable(int data, int columns) {
            return false;
        }

        public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
            Component c = super.prepareRenderer(r, data, columns);
            if (data % 2 == 0) {
                c.setBackground(Color.WHITE);
            } else {
                c.setBackground(Color.LIGHT_GRAY);
            }
            if (isCellSelected(data, columns)) {
                c.setBackground(Color.YELLOW);
            }
            return c;
        }
    };
    static DefaultTableModel productCustomerTM = new DefaultTableModel(productCustomerData, productCustomerColumns);
    static JTable productCustomerT = new JTable(productCustomerTM) {
        public boolean isCellEditable(int data, int columns) {
            return false;
        }

        public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
            Component c = super.prepareRenderer(r, data, columns);
            if (data % 2 == 0) {
                c.setBackground(Color.WHITE);
            } else {
                c.setBackground(Color.LIGHT_GRAY);
            }
            if (isCellSelected(data, columns)) {
                c.setBackground(Color.YELLOW);
            }
            return c;
        }
    };

    static JTable userT = new JTable(userTM) {
        public boolean isCellEditable(int data, int columns) {
            return false;
        }

        public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
            Component c = super.prepareRenderer(r, data, columns);
            if (data % 2 == 0) {
                c.setBackground(Color.WHITE);
            } else {
                c.setBackground(Color.LIGHT_GRAY);
            }
            if (isCellSelected(data, columns)) {
                c.setBackground(Color.YELLOW);
            }
            return c;
        }
    };

    public MyShop() {
        //***********************************************
        //Usernames & passwords

        admins.add(new User("admin", "admin"));
        users.add(new User("user", "user"));
        users.add(new User("Shaker", "123"));
        users.add(new User("Ahmed", "123"));
        users.add(new User("Essam", "123"));
        users.add(new User("Karam", "123"));
        users.add(new User("Hamdy", "123"));
        users.add(new User("Zoya", "123"));

        //***********************************************
        //Main window

        setSize(1000, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("My Store");
        productW.setVisible(false);
        categoryW.setVisible(false);
        customerW.setVisible(false);
        orderW.setVisible(false);
        usersW.setVisible(false);
        formPP.setVisible(false);

        //***********************************************
        //login panel

        add(loginW);
        loginW.setSize(1000, 600);
        JLabel loginL = new JLabel("Login");
        loginL.setBounds(450, 80, 150, 50);
        loginL.setFont(new Font("Verdana", Font.BOLD, 25));
        loginL.setForeground(Color.red);
        loginW.add(loginL);
        JLabel usernameL = new JLabel("Username");
        usernameL.setBounds(350, 150, 150, 50);
        usernameL.setFont(new Font("Verdana", Font.BOLD, 20));
        loginW.add(usernameL);
        JLabel passwordL = new JLabel("Password");
        passwordL.setBounds(350, 200, 150, 50);
        passwordL.setFont(new Font("Verdana", Font.BOLD, 20));
        loginW.add(passwordL);
        usernameF.setBounds(475, 165, 130, 25);
        loginW.add(usernameF);
        passwordF.setBounds(475, 215, 130, 25);
        loginW.add(passwordF);
        loginB.setBounds(410, 270, 150, 35);
        loginW.add(loginB);
        loginB.addActionListener(this);


        //***********************************************
        //Main menu

        JLabel pic = new JLabel();
        try {
            pic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("mslogo.png"))));
        } catch (Exception e) {
            System.out.println("Picture not loaded");
        }
        pic.setBounds(0, 0, 1000, 600);
        add(mainW);
        mainW.add(pic);
        mainW.setSize(1000, 600);
        menu.setBounds(350, 0, 275, 30);
        menu.add(productM);
        productM.addMenuListener(this);
        menu.add(categoryM);
        categoryM.addMenuListener(this);
        menu.add(customerM);
        customerM.addMenuListener(this);
        menu.add(orderM);
        orderM.addMenuListener(this);
        menu.add(usersM);
        usersM.addMenuListener(this);
        mainW.add(menu);
        backToLoginB.setBounds(50, 500, 150, 35);
        mainW.add(backToLoginB);
        backToLoginB.addActionListener(this);

        //***********************************************
        // product panel

        add(productW);
        productW.setSize(1000, 600);
        homeBproduct.setBounds(50, 500, 150, 35);
        addProductB.setBounds(50, 100, 150, 35);
        removeProductB.setBounds(50, 150, 150, 35);
        removeProductB.addActionListener(this);
        addProductB.addActionListener(this);
        productSearchF.setBounds(300, 50, 450, 25);
        searchB.setBounds(770, 50, 100, 25);
        productW.add(homeBproduct);
        productW.add(removeProductB);
        productW.add(addProductB);
        productW.add(productSearchF);
        productW.add(searchB);
        homeBproduct.addActionListener(this);
        productT.setPreferredScrollableViewportSize(new Dimension(725, 450));
        productT.setFillsViewportHeight(true);
        JScrollPane productTSP = new JScrollPane(productT);
        productTSP.setVisible(true);
        productTSP.setBounds(225, 100, 725, 450);
        productW.add(productTSP);
        searchB.addActionListener(this);

        //***********************************************
        // inner form (product)

        add(formPP);
        formPP.setSize(1000, 600);
        JLabel formPPIDL = new JLabel("ID:");
        formPPIDL.setBounds(380, 43, 120, 35);
        formPP.add(formPPIDL);
        formPPIDF.setBounds(450, 50, 150, 25);
        formPP.add(formPPIDF);
        JLabel formPPNameL = new JLabel("Name:");
        formPPNameL.setBounds(380, 90, 120, 35);
        formPP.add(formPPNameL);
        formPPNameF.setBounds(450, 95, 150, 25);
        formPP.add(formPPNameF);
        JLabel formPPPriceL = new JLabel("Price:");
        formPPPriceL.setBounds(380, 130, 120, 35);
        formPP.add(formPPPriceL);
        formPPPriceF.setBounds(450, 140, 150, 25);
        formPP.add(formPPPriceF);
        JLabel formPPQuantityL = new JLabel("Quantity:");
        formPPQuantityL.setBounds(380, 180, 120, 35);
        formPP.add(formPPQuantityL);
        formPPQuantityF.setBounds(450, 185, 150, 25);
        formPP.add(formPPQuantityF);
        JLabel formPPDescriptionL = new JLabel("Description:");
        formPPDescriptionL.setBounds(380, 225, 120, 35);
        formPP.add(formPPDescriptionL);
        formPPDescriptionF.setBounds(450, 230, 150, 25);
        formPP.add(formPPDescriptionF);
        JLabel formPPCategoryL = new JLabel("Category:");
        formPPCategoryL.setBounds(380, 270, 120, 35);
        formPP.add(formPPCategoryL);
        formPPCategory.setBounds(450, 275, 150, 25);
        formPP.add(formPPCategory);
        formPP.add(formPPAdd);
        formPPAdd.setBounds(505, 325, 87, 25);
        formPPAdd.setBackground(Color.GREEN);
        formPPAdd.setForeground(Color.WHITE);
        formPPAdd.addActionListener(this);
        formPP.add(formPPCancel);
        formPPCancel.setBackground(Color.RED);
        formPPCancel.setForeground(Color.WHITE);
        formPPCancel.setBounds(405, 325, 85, 25);
        formPPCancel.addActionListener(this);

        //***********************************************
        // category panel

        add(categoryW);
        categoryW.setSize(1000, 600);
        homeBcategory.setBounds(50, 500, 150, 35);
        categoryW.add(homeBcategory);
        homeBcategory.addActionListener(this);
        JLabel categoryIdL = new JLabel("ID:");
        categoryIdL.setBounds(100, 42, 120, 35);
        categoryW.add(categoryIdL);
        categoryPidF.setBounds(150, 43, 150, 25);
        categoryW.add(categoryPidF);
        JLabel categoryNameL= new JLabel("Name:");
        categoryNameL.setBounds(100, 102, 120, 35);
        categoryW.add(categoryNameL);
        categoryPNameF.setBounds(150, 103, 150, 25);
        categoryW.add(categoryPNameF);
        categgoryPInsertB.setBounds(100, 173, 200, 35);
        categgoryPInsertB.setBackground(Color.GREEN);
        categgoryPInsertB.setForeground(Color.WHITE);
        categgoryPInsertB.addActionListener(this);
        categoryW.add(categgoryPInsertB);
        categgoryPDeleteB.setBounds(100, 253, 200, 35);
        categgoryPDeleteB.setBackground(Color.RED);
        categgoryPDeleteB.setForeground(Color.WHITE);
        categgoryPDeleteB.addActionListener(this);
        categoryW.add(categgoryPDeleteB);
        categoryT.setPreferredScrollableViewportSize(new Dimension(425, 350));
        categoryT.setFillsViewportHeight(true);
        JScrollPane categoryTSP = new JScrollPane(categoryT);
        categoryTSP.setVisible(true);
        categoryTSP.setBounds(375, 43, 425, 350);
        categoryW.add(categoryTSP);

        //***********************************************
        // customer panel

        add(customerW);
        customerW.setSize(1000, 600);
        homeBcustomer.setBounds(50, 500, 150, 35);
        customerW.add(homeBcustomer);
        homeBcustomer.addActionListener(this);
        JLabel IDCL = new JLabel("ID:");
        IDCL.setBounds(185, 93, 120, 35);
        customerW.add(IDCL);
        IDCF.setBounds(260, 100, 150, 25);
        customerW.add(IDCF);
        JLabel FNCL = new JLabel("First name:");
        FNCL.setBounds(185, 140, 120, 35);
        customerW.add(FNCL);
        firstNameCF.setBounds(260, 145, 150, 25);
        customerW.add(firstNameCF);
        JLabel LNCL = new JLabel("Last name:");
        LNCL.setBounds(185, 180, 120, 35);
        customerW.add(LNCL);
        lastNameCF.setBounds(260, 190, 150, 25);
        customerW.add(lastNameCF);
        JLabel telNumCL = new JLabel("Telephone:");
        telNumCL.setBounds(185, 230, 120, 35);
        customerW.add(telNumCL);
        telNumCF.setBounds(260, 235, 150, 25);
        customerW.add(telNumCF);
        JLabel eMailCL = new JLabel("Email:");
        eMailCL.setBounds(185, 275, 120, 35);
        customerW.add(eMailCL);
        eMailCF.setBounds(260, 280, 150, 25);
        customerT.setBounds(450, 100, 200, 305);
        customerW.add(eMailCF);
        customerT.setPreferredScrollableViewportSize(new Dimension(500, 205));
        customerT.setFillsViewportHeight(true);
        JScrollPane customerTSP = new JScrollPane(customerT);
        customerTSP.setVisible(true);
        customerTSP.setBounds(450, 100, 500, 205);
        customerW.add(customerTSP);
        customerW.add(cAddB);
        customerW.add(cDelB);
        customerW.add(cClearB);
        cAddB.setBounds(847, 320, 100, 25);
        cAddB.setBackground(Color.GREEN);
        cAddB.setForeground(Color.WHITE);
        cDelB.setBounds(450, 320, 100, 25);
        cDelB.setBackground(Color.RED);
        cDelB.setForeground(Color.WHITE);
        cClearB.setBounds(260, 325, 150, 30);
        cClearB.setBackground(Color.ORANGE);
        cClearB.setForeground(Color.WHITE);
        cAddB.addActionListener(this);
        cDelB.addActionListener(this);
        cClearB.addActionListener(this);

        //***********************************************
        // order panel

        add(orderW);
        orderW.setSize(1000, 600);
        homeBorder.setBounds(50, 500, 150, 35);
        orderW.add(homeBorder);
        homeBorder.addActionListener(this);
        JLabel orderCustomerIDL = new JLabel("Customer ID:");
        JLabel orderOrderIDL = new JLabel("Order ID:");
        orderCustomerIDL.setBounds(700, 20, 150, 35);
        orderOrderIDL.setBounds(725, 65, 150, 35);
        orderW.add(orderCustomerIDL);
        orderW.add(orderOrderIDL);
        orderCID.setBounds(800, 25, 100, 25);
        orderOID.setBounds(800, 67, 100, 25);
        orderW.add(orderCID);
        orderW.add(orderOID);
        orderW.add(orderInsertB);
        orderW.add(orderProductRemoveB);
        orderW.add(orderClearB);
        orderInsertB.addActionListener(this);
        orderProductRemoveB.addActionListener(this);
        orderClearB.addActionListener(this);
        orderProductRemoveB.setBounds(750, 125, 150, 25);
        orderInsertB.setBounds(750, 165, 150, 25);
        orderClearB.setBounds(750, 205, 150, 25);
        orderInsertB.setBackground(Color.GREEN);
        orderInsertB.setForeground(Color.WHITE);
        orderProductRemoveB.setBackground(Color.RED);
        orderProductRemoveB.setForeground(Color.WHITE);
        orderClearB.setBackground(Color.ORANGE);
        orderClearB.setForeground(Color.WHITE);
        orderProductT.setPreferredScrollableViewportSize(new Dimension(725, 450));
        orderProductT.setFillsViewportHeight(true);
        JScrollPane orderProductTSP = new JScrollPane(orderProductT);
        orderProductTSP.setVisible(true);
        orderProductTSP.setBounds(30, 20, 450, 215);
        orderW.add(orderProductTSP);
        productCustomerT.setPreferredScrollableViewportSize(new Dimension(725, 200));
        productCustomerT.setFillsViewportHeight(true);
        JScrollPane productCustomerTSP = new JScrollPane(productCustomerT);
        productCustomerTSP.setVisible(true);
        productCustomerTSP.setBounds(30, 250, 450, 215);
        orderW.add(productCustomerTSP);

        //***********************************************
        // users panel

        add(usersW);
        usersW.setSize(1000, 600);
        homeBusers.setBounds(50, 500, 150, 35);
        usersW.add(homeBusers);
        homeBusers.addActionListener(this);
        JLabel IDUserL = new JLabel("ID:");
        IDUserL.setBounds(185, 93, 120, 35);
        usersW.add(IDUserL);
        userPIDF.setBounds(260, 100, 150, 25);
        usersW.add(userPIDF);
        JLabel NUserL = new JLabel("Username:");
        NUserL.setBounds(185, 140, 120, 35);
        usersW.add(NUserL);
        userPNameF.setBounds(260, 145, 150, 25);
        usersW.add(userPNameF);
        JLabel PasswordUserL = new JLabel("Password:");
        PasswordUserL.setBounds(185, 180, 120, 35);
        usersW.add(PasswordUserL);
        userPPasswordF.setBounds(260, 190, 150, 25);
        usersW.add(userPPasswordF);
        JLabel FullNUserL= new JLabel("FullName:");
        FullNUserL.setBounds(185, 230, 120, 35);
        usersW.add(FullNUserL);
        userPFullNF.setBounds(260, 230, 150, 25);
        usersW.add(userPFullNF);
        JLabel telNumUserL = new JLabel("Tel:");
        telNumUserL.setBounds(185, 280, 120, 35);
        usersW.add(telNumUserL);
        userPTellF.setBounds(260, 280, 150, 25);
        usersW.add(userPTellF);
        JLabel eMailUserL = new JLabel("Email:");
        eMailUserL.setBounds(185, 325, 120, 35);
        usersW.add(eMailUserL);
        userPEmailF.setBounds(260, 325, 150, 25);
        usersW.add(userPEmailF);
        userT.setBounds(450, 100, 200, 305);
        userT.setPreferredScrollableViewportSize(new Dimension(500, 205));
        userT.setFillsViewportHeight(true);
        JScrollPane userTSP = new JScrollPane(userT);
        userTSP.setVisible(true);
        userTSP.setBounds(450, 100, 500, 205);
        usersW.add(userTSP);
        userAddB.setBounds(847, 320, 100, 25);
        userAddB.setBackground(Color.GREEN);
        userAddB.setForeground(Color.WHITE);
        usersW.add(userAddB);
        userDelB.setBounds(450, 320, 100, 25);
        userDelB.setBackground(Color.RED);
        userDelB.setForeground(Color.WHITE);
        usersW.add(userDelB);
        userClearB.setBounds(260, 375, 150, 30);
        userClearB.setBackground(Color.ORANGE);
        userClearB.setForeground(Color.WHITE);
        usersW.add(userClearB);
        userAddB.addActionListener(this);
        userDelB.addActionListener(this);
        userClearB.addActionListener(this);

        //***********************************************
        //display frame

        setVisible(true);

        //***********************************************
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginB)) {
            current = new User(usernameF.getText(), passwordF.getText());
            if (!users.contains(current) && !admins.contains(current)) {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (admins.contains(current)) {
                loginW.setVisible(false);
                mainW.setVisible(true);
                usersM.setVisible(true);
            } else {
                loginW.setVisible(false);
                mainW.setVisible(true);
                usersM.setVisible(false);
            }
        }
        if (e.getSource().equals(homeBproduct)) {
            productW.setVisible(false);
            mainW.setVisible(true);
        }
        if (e.getSource().equals(homeBcategory)) {
            categoryW.setVisible(false);
            mainW.setVisible(true);
        }
        if (e.getSource().equals(homeBcustomer)) {
            customerW.setVisible(false);
            mainW.setVisible(true);
        }
        if (e.getSource().equals(homeBorder)) {
            orderW.setVisible(false);
            mainW.setVisible(true);
        }
        if (e.getSource().equals(homeBusers)) {
            usersW.setVisible(false);
            mainW.setVisible(true);
        }
        if (e.getSource().equals(backToLoginB)) {
            mainW.setVisible(false);
            loginW.setVisible(true);
        }
        if (e.getSource().equals(removeProductB)) {
            if (productT.getSelectedRowCount() == 1) {
                productTM.removeRow(productT.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Product deleted Succesfully!", "Done!", JOptionPane.INFORMATION_MESSAGE);
            } else if (productT.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is Empty", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single product for deletion", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource().equals(addProductB)) {
            productW.setVisible(false);
            formPP.setVisible(true);
        }
        if (e.getSource().equals(formPPCancel)) {
            formPP.setVisible(false);
            productW.setVisible(true);
        }
        if (e.getSource().equals(formPPAdd)) {
            formPP.setVisible(false);
            productW.setVisible(true);
            String ID = formPPIDF.getText();
            String name = formPPNameF.getText();
            String price = formPPPriceF.getText();
            String quantity = formPPQuantityF.getText();
            String Description = formPPDescriptionF.getText();
            String Category = (String) formPPCategory.getSelectedItem();
            Object[] rowData = {ID, name, price, quantity, Description, Category};
            if(ID.equals("") || name.equals("") || price.equals("") || quantity.equals("") || Description.equals("")){
                JOptionPane.showMessageDialog(null, "Please check if all the fields are filled", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                productTM.addRow(rowData);
                productT.revalidate();
                productT.repaint();
            }
        }
        if (e.getSource().equals(searchB)) {
            String searchString = productSearchF.getText();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(productTM);
            productT.setRowSorter(tr);
            if (searchString.length() == 0) {
                tr.setRowFilter(null);
            } else {
                tr.setRowFilter(RowFilter.regexFilter(searchString));
            }
        }
        if (e.getSource().equals(cAddB)) {
            String ID = IDCF.getText();
            String firstName = firstNameCF.getText();
            String lastName = lastNameCF.getText();
            String telNum = telNumCF.getText();
            String eMail = eMailCF.getText();
            Object[] rowData = {ID, firstName, lastName, telNum, eMail};
            if(ID.equals("") || firstName.equals("") || lastName.equals("") || telNum.equals("") || eMail.equals("") ){
                JOptionPane.showMessageDialog(null, "Please check if all the fields are filled", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                customerTM.addRow(rowData);
                customerT.revalidate();
                customerT.repaint();
            }
        }
        if (e.getSource().equals(cDelB)) {
            if (customerT.getSelectedRowCount() == 1) {
                customerTM.removeRow(customerT.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Customer deleted Succesfully!", "Done!", JOptionPane.INFORMATION_MESSAGE);
            } else if (customerT.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is Empty", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single customer for deletion", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource().equals(cClearB)) {
            IDCF.setText("");
            firstNameCF.setText("");
            lastNameCF.setText("");
            telNumCF.setText("");
            eMailCF.setText("");
        }
        if (e.getSource().equals(categgoryPInsertB)) {
            String ID= categoryPidF.getText();
            String name= categoryPNameF.getText();
            Object[] rowData = {ID,name};
            if(ID.equals("") || name.equals("")){
                JOptionPane.showMessageDialog(null, "Please check if all the fields are filled", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                categoryTM.addRow(rowData);
                categoryT.revalidate();
                categoryT.repaint();
            }
        }
        if (e.getSource().equals(categgoryPDeleteB)) {
            if(categoryT.getSelectedRowCount() == 1) {
                categoryTM.removeRow(categoryT.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Category deleted Succesfully!", "Done!", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(categoryT.getRowCount()==0)
                JOptionPane.showMessageDialog(null, "Table is Empty", "Error", JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Please select a single Category for deletion", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (e.getSource().equals(orderProductRemoveB)) {
            if (productCustomerT.getSelectedRowCount() == 1) {
                productTM.removeRow(productCustomerT.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Product deleted Succesfully!", "Done!", JOptionPane.INFORMATION_MESSAGE);
            } else if (productCustomerT.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is Empty", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single product for deletion", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource().equals(orderClearB)) {
            orderOID.setText("");
            orderCID.setText("");
        }
        if (e.getSource().equals(orderInsertB)) {
            String ID = JOptionPane.showInputDialog(null, "Please enter an ID for the product you want to order", "input", JOptionPane.QUESTION_MESSAGE);
            String Name = JOptionPane.showInputDialog(null, "Please enter a name for the product you want to order", "input", JOptionPane.QUESTION_MESSAGE);
            String price = JOptionPane.showInputDialog(null, "Please enter an price for the product you want to order", "input", JOptionPane.QUESTION_MESSAGE);
            String quantity = JOptionPane.showInputDialog(null, "Please enter a quantity for the product you want to order", "input", JOptionPane.QUESTION_MESSAGE);
            int priceAll = Integer.parseInt(price) * Integer.parseInt(quantity);
            Object[] rowData = {ID, Name, price, quantity, priceAll};
            if(ID.equals("") || Name.equals("") || price.equals("") || quantity.equals("") || quantity.equals("0") || price.equals("0") /*|| Integer.parseInt(quantity) > Integer.parseInt(productT.getValueAt(productT.getSelectedRow(), 3).toString())*/){
                JOptionPane.showMessageDialog(null, "input Error", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                productCustomerTM.addRow(rowData);
                productCustomerT.revalidate();
                productCustomerT.repaint();
            }
        }
        if (e.getSource().equals(userAddB)) {
            String ID = userPIDF.getText();
            String Name = userPNameF.getText();
            String Password = userPPasswordF.getText();
            String FullName = userPFullNF.getText();
            String telNum = userPTellF.getText();
            String eMail = userPEmailF.getText();
            Object[] rowData = {ID, Name, Password, FullName, telNum,eMail};
            if(ID.equals("") || Name.equals("") || Password.equals("") || FullName.equals("") || telNum.equals("") || eMail.equals("") ){
                JOptionPane.showMessageDialog(null, "Please check if all the fields are filled", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                users.add(new User(Name, Password));
                userTM.addRow(rowData);
                userT.revalidate();
                userT.repaint();
            }
        }
        if (e.getSource().equals(userDelB)) {
            if (userT.getSelectedRowCount() == 1) {
                userTM.removeRow(userT.getSelectedRow());
                //users.remove((new User(customerT.getValueAt(customerT.getSelectedRow(), 2).toString(), customerT.getValueAt(customerT.getSelectedRow(), 3).toString())));
                //users.remove(userT.getSelectedRow());
                JOptionPane.showMessageDialog(null, "user deleted Succesfully!", "Done!", JOptionPane.INFORMATION_MESSAGE);
            } else if (userT.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is Empty", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single user for deletion", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource().equals(userClearB)) {
            userPIDF.setText("");
            userPNameF.setText("");
            userPPasswordF.setText("");
            userPFullNF.setText("");
            userPTellF.setText("");
            userPEmailF.setText("");
        }
    }


    @Override
    public void menuSelected(MenuEvent e) {
        if(e.getSource().equals(productM)){
            mainW.setVisible(false);
            productW.setVisible(true);
        }
        if(e.getSource().equals(categoryM)){
            mainW.setVisible(false);
            categoryW.setVisible(true);
        }
        if(e.getSource().equals(customerM)){
            mainW.setVisible(false);
            customerW.setVisible(true);
        }
        if(e.getSource().equals(orderM)){
            mainW.setVisible(false);
            orderW.setVisible(true);
        }
        if(e.getSource().equals(usersM)){
            mainW.setVisible(false);
            usersW.setVisible(true);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
