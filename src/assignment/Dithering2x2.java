/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Dithering2x2 {
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
            
        int countHeight = 0, countWidth = 0;
        
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
            
            int[][] dithering2x2 ={
                {0, 128},
                {192, 64}
            };
            
            int count = 0, a = 0, b = 0;
            while (count != height){
                if (count % 2 == 0){
                    for(int j = 0; j < width; j++){
                        if (j % 2 ==0){
                            a = 0;
                        }else{
                            a = 1;
                        }
                        
                        if(newImageSize[count][j] > dithering2x2[0][a]){
                            newImageSize[count][j] = 255;
                        }else{
                            newImageSize[count][j] = 0;
                        }
                    }
                }
                else{
                    for(int j = 0; j < width; j++){
                        if(j % 2 == 0){
                            a = 0;
                        }else{
                            a = 1;
                        }
                        
                        if(newImageSize[count][j] > dithering2x2[1][a]){
                            newImageSize[count][j] = 255;
                        }else{
                            newImageSize[count][j] = 0;
                        }
                    }
                } count++;
            }
            
            FileOutputStream output = new FileOutputStream("output/" + txtName + "_dithering2x2.raw"); 
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    output.write(newImageSize[i][j]);
                }
            }
            output.close();                    
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
        
    }
}
