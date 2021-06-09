/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

import java.io.*;
import java.util.*;

/**
 *
 * @author Taariq phillips - 220166153
 */
public class runSupplier {

    private ObjectInputStream input;
    Stakeholder stakeholder;
    private ArrayList<Supplier> supplierArray = new ArrayList<>();
    BufferedWriter suppliertxt;

    //Opens Ser File
    public void openSuppFile() {
        try {
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("*** ser file opened for reading ***");
        } catch (IOException ioe) {
            System.out.println("error opening ser file: " + ioe.getMessage());
        }
    }

    //Closes Ser File
    public void closeSuppFile() {
        try {
            input.close();
            System.out.println("*** file has been closed ***");
        } catch (IOException ioe) {
            System.out.println("error closing ser file: " + ioe.getMessage());
        }
    }

    //Reads and Displays Supplier
    public void readFromSupplier() {
        try {

            while (true) {

                stakeholder = (Stakeholder) input.readObject();
                if (stakeholder instanceof Supplier) {
                    supplierArray.add((Supplier) stakeholder);
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

    //Sorts Suppliers by Name
    public void sortSupplierName() {
        supplierArray.sort(Comparator.comparing(Supplier::getName));
    }

    public void displaySupp() {
        System.out.println("--------------------------------------------Supplier-----------------------------------------");
        System.out.println("ID\tName\t\t\tProd Type\tDescription");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (int i = 0; i < supplierArray.size(); i++) {
            System.out.println(supplierArray.get(i));
        }
    }

    //Opens Supplier Text File 
    public void openSuppliertxt() {
        try {
            suppliertxt = new BufferedWriter(new FileWriter("supplierOutFile.txt"));

        } catch (IOException ioe) {
            System.out.println("error reading from ser file: " + ioe);
        }
    }

    //Writes to Supplier Text File
    public void writeSupplier() {
        try {
            suppliertxt.write("-----------------------------Supplier----------------------------------------");
            suppliertxt.newLine();
            suppliertxt.write("ID\tName\t\t\tProd Type\tDescription");
            suppliertxt.newLine();
            suppliertxt.write("-----------------------------------------------------------------------------");
            suppliertxt.newLine();

            for (int i = 0; i < supplierArray.size(); i++) {
                suppliertxt.write(supplierArray.get(i).getStHolderId() + "\t" + supplierArray.get(i).getName() + "      \t" + supplierArray.get(i).getProductType() + "\t\t" + supplierArray.get(i).getProductDescription());
                suppliertxt.newLine();
            }
            suppliertxt.close();
        } catch (IOException ioe) {
            System.out.println("error reading from ser file: " + ioe);
        }
        System.out.println("Supplier file has been written");

    }

}
