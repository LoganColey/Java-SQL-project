package com.company;
import java.sql.SQLException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Main {
    public static String showCustomer(){
        String results = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:store");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CUSTOMERS");
            while (rs.next()) {
                System.out.print("CustomerID: " + rs.getString("customerid"));
                System.out.print(", Firstname: " + rs.getString("firstname"));
                System.out.print(", Lastname: " + rs.getString("lastname"));
                System.out.println(", Age: " + rs.getString("age"));

            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            ex.printStackTrace();
            System.exit(1);

        }
        return results;
    }

    public static String showOrder(){
        String results = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:store");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ORDERS");
            while (rs.next()) {
                System.out.print("OrderID: " + rs.getString("orderID"));
                System.out.print(", Order Number: " + rs.getString("orderNum"));
                System.out.println(", Customer: " + rs.getString("customerID"));

            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            ex.printStackTrace();
            System.exit(1);

        }
        return results;
    }

    public static String showFood(){
        String results = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:store");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM FOOD");
            while (rs.next()) {
                System.out.print("FoodID: " + rs.getString("foodid"));
                System.out.print(", Appetizer: " + rs.getString("appetizer"));
                System.out.print(", Entree: " + rs.getString("entree"));
                System.out.println(", Dessert: " + rs.getString("dessert"));
                System.out.println(", OrderID: " + rs.getString("orderID"));

            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            ex.printStackTrace();
            System.exit(1);

        }
        return results;
    }

    public static String addCustomer(){
        String confirm = "The Customer was put into the database";
        try {
            String url = "jdbc:postgresql:store";
            String username = "logancoley";
            String password = "3530Todd";

            Connection con = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO CUSTOMERS" + " (customerID, firstname, lastname, age)" + " values (?,?,?,?)";

            PreparedStatement psmt = con.prepareStatement(sql);

            Integer newCustomerID = showLastID() + 1;
            Scanner uInput = new Scanner(System.in);
            System.out.println("First Name:");
            String fn = uInput.nextLine();
            System.out.println("Last Name:");
            String ln = uInput.nextLine();
            System.out.println(("Age: "));
            Integer age = uInput.nextInt();

            psmt.setInt(1,newCustomerID);
            psmt.setString(2,fn);
            psmt.setString(3,ln);
            psmt.setInt(4,age);

            psmt.executeUpdate();
            return confirm;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.exit(1);
        }
        return confirm;

    }

    public static Integer showLastID(){
        Integer results = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:store");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CUSTOMERS");
            while (rs.next()) {
                results = rs.getInt(1);

            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            ex.printStackTrace();
            System.exit(1);

        }
        return results;
    }

    public static String placeOrder(){
        String confirm = "The Customer was put into the database";
        try {
            String url = "jdbc:postgresql:store";
            String username = "logancoley";
            String password = "3530Todd";
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO ORDERS" + "(orderID, ordernum, customerID)" + " values (?,?,?)";
            String sql2 = "INSERT INTO FOOD" + "(foodID,appetizer,entree,dessert,orderID)" + "values (?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            PreparedStatement psmt2 = con.prepareStatement(sql2);

            System.out.println(showCustomer());
            System.out.println("Please select the Customer ID for the Order ID");

            Scanner uInput = new Scanner(System.in);
            Integer oID = uInput.nextInt();
            Integer orderNum = uInput.nextInt();

            psmt.setInt(1,oID);
            psmt.setInt(2,orderNum);
            psmt.setInt(3, oID);

            Scanner fInput = new Scanner(System.in);
            System.out.println("Now what is the appetizer for that order?");
            String appetizer = fInput.nextLine();
            System.out.println("Entree?");
            String entree = fInput.nextLine();
            System.out.println("Now what Dessert do they want?");
            String dessert = fInput.nextLine();

            psmt2.setInt(1,oID);
            psmt2.setString(2,appetizer);
            psmt2.setString(3,entree);
            psmt2.setString(4,dessert);
            psmt2.setInt(5,oID);

            psmt.executeUpdate();
            psmt2.executeUpdate();
            return confirm;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.exit(1);
        }
        return confirm;

    }

    public static String updateCustomer(){
        String confirm = "";
        Scanner fInput = new Scanner(System.in);
        Scanner fnInput = new Scanner(System.in);
        Scanner lnInput = new Scanner(System.in);
        try{
            String url = "jdbc:postgresql:store";
            String username = "logancoley";
            String password = "3530Todd";
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "update CUSTOMERS set firstname = ?, lastname = ?, age = ? where customerID = ?";

            PreparedStatement psmt = con.prepareStatement(sql);

            System.out.println("What is the Customer ID you want too update");
            Integer id = fInput.nextInt();
            System.out.println("What is the new First Name?");
            String fi = fnInput.nextLine();
            System.out.println("What is the new Last Name?");
            String ln = lnInput.nextLine();
            System.out.println("What is the new age?");
            Integer age = fInput.nextInt();

            psmt.setString(1,fi);
            psmt.setString(2,ln);
            psmt.setInt(3,age);
            psmt.setInt(4,id);
            psmt.executeUpdate();
            con.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return confirm;
    }

    public static String deleteCustomer(){
        String confirm = "";
        try{
            String url = "jdbc:postgresql:store";
            String username = "logancoley";
            String password = "3530Todd";
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM CUSTOMERS" +  " WHERE customerID = ?";
            PreparedStatement smt = con.prepareStatement(sql);

            Scanner fInput = new Scanner(System.in);
            System.out.println("What is the Customer ID you want too delete?");
            Integer id = fInput.nextInt();
            smt.setInt(1,id);

            smt.executeQuery();
            smt.close();
            con.close();
        }
        catch(SQLException ex){
            System.out.println("It has been deleted");
        }
        return confirm;
    }


    public static void main(String[] args) {
        boolean x = true;
        Scanner uInput = new Scanner(System.in);
        while (x){
            System.out.println("Show Customer, Show Order, Show Food, Add Customer,Place Order");
            String input = uInput.nextLine();
            if (input.equals("Customer")){
                System.out.print(showCustomer());
            }
            else if (input.equals("Order")){
                System.out.print(showOrder());
            }
            else if (input.equals("Food")){
                System.out.print(showFood());
            }
            else if (input.equals("Add")){
                addCustomer();
            }
            else if (input.equals("Place")){
                placeOrder();
            }
            else if (input.equals("Q")){
                System.out.println("Thank you have a nice day!");
                x = false;
            }
            else if (input.equals("Update")){
                updateCustomer();
            }
            else if (input.equals("Delete")){
                deleteCustomer();
            }
            else{
                System.out.println("Please select a valid option");
            }
        }


    }
}
