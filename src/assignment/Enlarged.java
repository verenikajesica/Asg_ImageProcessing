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
public class Enlarged {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
//        Picture-500X500
//        huruf-600x600
//        Triangle-400x400
        System.out.println("Type file name: ");
        String txtName = in.nextLine();        
        System.out.println("Height: ");
        int height = in.nextInt();
        System.out.println("Width: ");
        int width = in.nextInt();
        
        int countHeight = 0 , countWidth = 0;
        int[][] newImageSize = new int[height][width];
        try{
            File fileName = new File("Images/" + txtName + ".raw");
            FileInputStream myInputFile = new FileInputStream(fileName);
            int value;
            while((value = myInputFile.read()) != -1){
                if(countWidth == width){
                    countWidth = 0;
                    countHeight++;
                }
                newImageSize[countHeight][countWidth] = value;
                countWidth++;
            }
            
            int[][] newSize = new int[height*2][width*2];
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    for(int a = 0; a < 2; a++){
                        for(int b = 0; b < 2; b++){
                            newSize[(i * 2) + a][(j * 2) + b] = newImageSize[i][j];
                        }
                    }
                }
            }
            
            FileOutputStream output = new FileOutputStream("output/" + txtName + " Enlarged.raw");  
            for(int i = 0; i < (height * 2); i++){
                for(int j = 0; j < (width * 2); j++){
                    output.write(newSize[i][j]);
                }
            }
            output.close();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
    }
}
