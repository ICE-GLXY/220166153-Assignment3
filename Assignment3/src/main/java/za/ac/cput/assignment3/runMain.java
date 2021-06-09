/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;
import java.text.ParseException;


/**
 *
 * @author Taariq phillips - 220166153
 */
public class runMain 
{
     public static void main(String args[]) throws ParseException
    {
        //runs Methods in runCustomer
        runCustomer obj = new runCustomer();
        obj.openFile();
        obj.readFromCustomer();
        obj.sortCustomerID();
        obj.displayCust();
        obj.age();
        obj.openCustomertxt();
        obj.dateFormat();
        obj.writeCustomer();
        obj.closeFile();
        
        //runs Methods in runSupplier
        runSupplier sObj = new runSupplier();
        sObj.openSuppFile();
        sObj.readFromSupplier();
        sObj.sortSupplierName();
        sObj.displaySupp();
        sObj.openSuppliertxt();
        sObj.writeSupplier();
        sObj.closeSuppFile();
    }
}
