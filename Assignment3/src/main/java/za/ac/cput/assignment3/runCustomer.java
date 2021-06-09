/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

/**
 *
 * @author Taariq phillips - 220166153
 */
public class runCustomer {

    private ObjectInputStream input;
    Stakeholder stakeholder;
    private ArrayList<Customer> customerArray = new ArrayList<>();
    BufferedWriter customertxt;
    int age[];
    LocalDate today;
    LocalDate DOB;
    Period ageCalc;

    //Opens ser File
    public void openFile() {
        try {
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("*** ser file opened for reading ***");
        } catch (IOException ioe) {
            System.out.println("error opening ser file: " + ioe.getMessage());
        }
    }

    //Closes ser file
    public void closeFile() {
        try {
            input.close();
            System.out.println("*** file has been closed ***");
        } catch (IOException ioe) {
            System.out.println("error closing ser file: " + ioe.getMessage());
        }
    }

    //Reads and Displays Customers
    public void readFromCustomer() {
        try {

            while (true) {

                stakeholder = (Stakeholder) input.readObject();
                if (stakeholder instanceof Customer) {
                    customerArray.add((Customer) stakeholder);
                }

            }

        } catch (EOFException eofe) {
            System.out.println("Reading File");
        } catch (ClassNotFoundException ioe) {
            System.out.println("error reading from ser file: " + ioe);
        } catch (IOException ioe) {
            System.out.println("error reading from ser file: " + ioe);
        }

    }

    //sortsCustomers by ID
    public void sortCustomerID() {
        customerArray.sort(Comparator.comparing(Stakeholder::getStHolderId));
    }

    public void displayCust() {
        System.out.println("--------------------------------------------Customer-----------------------------------------");
        System.out.println("ID\t\tName\t\tSurname\t\tArea\t\tDate of Birth\tRent");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (int i = 0; i < customerArray.size(); i++) {
            System.out.println(customerArray.get(i));
        }
    }

    //Calculates Age
    public void age() {

        age = new int[customerArray.size()];
        for (int i = 0; i < customerArray.size(); i++) {
            today = LocalDate.now();
            DOB = LocalDate.parse(customerArray.get(i).getDateOfBirth());
            ageCalc = Period.between(DOB, today);
            age[i] = ageCalc.getYears();
        }

    }

    //Formats Date
    public void dateFormat() throws ParseException {
        DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < customerArray.size(); i++) {
            String arrayDate = customerArray.get(i).getDateOfBirth();
            Date list = f1.parse(arrayDate);
            DateFormat f2 = new SimpleDateFormat("dd MMM yyy");
            customerArray.get(i).setDateOfBirth(f2.format(list));
        }
    }

    //Calculates how many can rent
    public int rentTrue() {
        int rentTrue = 0;

        for (int i = 0; i < customerArray.size(); i++) {
            customerArray.get(i);
            if (customerArray.get(i).getCanRent() == true) {
                rentTrue++;
            }
        }
        return rentTrue;
    }

    //Calculates how many cannot rent
    public int rentFalse() {
        int rentFalse = 0;

        for (int i = 0; i < customerArray.size(); i++) {
            customerArray.get(i);
            if (customerArray.get(i).getCanRent() == false) {
                rentFalse++;
            }
        }
        return rentFalse;
    }

    //Opens Customer Text File
    public void openCustomertxt() {
        try {
            customertxt = new BufferedWriter(new FileWriter("customerOutFile.txt"));

        } catch (IOException ioe) {
            System.out.println("error reading from ser file: " + ioe);
        }
    }

    //Writes to Customer Text File
    public void writeCustomer() throws ParseException {
        try {
            customertxt.write("--------------------------------------Customer-----------------------------------------------");
            customertxt.newLine();
            customertxt.write("ID\t\tName\t\tSurname\t\t\tDate of Birth\t\tAge");
            customertxt.newLine();
            customertxt.write("---------------------------------------------------------------------------------------------");
            customertxt.newLine();

            for (int i = 0; i < customerArray.size(); i++) {
                customertxt.write(customerArray.get(i).getStHolderId() + "\t\t" + customerArray.get(i).getFirstName() + "\t\t" + customerArray.get(i).getSurName() + "   \t\t" + customerArray.get(i).getDateOfBirth() + "\t\t" + age[i]);
                customertxt.newLine();
            }

            customertxt.newLine();
            customertxt.write("Number of customers who can rent: " + rentTrue());
            customertxt.newLine();
            customertxt.write("Number of customers who cannot rent: " + rentFalse());
            customertxt.newLine();
            customertxt.close();
        } catch (IOException ioe) {
            System.out.println("error reading from ser file: " + ioe);
        }
        System.out.println("Customer file has been written");

    }
}


