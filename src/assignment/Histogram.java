/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Histogram {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
         
        //1. bedroom, 600, 600
        //2. Imgpro, 100, 122
        //3. yoda, 62, 123
        System.out.println("Type file name: ");
        String txtName = in.nextLine();        
        System.out.println("Height: ");
        int height = in.nextInt();
        System.out.println("Width: ");
        int width = in.nextInt();
        
        try{
            File fileName = new File("Images/" + txtName + ".raw");
            FileInputStream myInputFile = new FileInputStream(fileName);
           
            int[] array = new int[256];
            int value;
            int[] newImageSize = new int[(int)fileName.length()];
            int count = 0;
            while((value = myInputFile.read()) != -1){
                newImageSize[count] = value;
                array[value]++;
                count++;
            }
            
            int a = 0, b;
            int[] newArray = new int[256];
            for(int i = 0; i < 256; i++){
                a = array[i] + a;
                b = (a * 255)/(width * height);
                newArray[i] = b;
//                System.out.println(i + ": " + array[i] + " Running sum: " 
//                                   + a + " Normalized: " + a +"/" + width * height 
//                                   + " Multiply: "+ b);                
            }
            FileOutputStream output = new FileOutputStream("output/" + txtName + "_HISTOGRAM.raw");
            for(int i = 0; i < fileName.length(); i++){
                output.write(newArray[newImageSize[i]]);
            }
            output.close();   
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
    }
}
