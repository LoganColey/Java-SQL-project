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

    public static void main(String[] args) {
        boolean x = true;
        Scanner uInput = new Scanner(System.in);
        while (x){
            System.out.println("Show Customer, Show Order, Show Food");
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
        }


    }
}
