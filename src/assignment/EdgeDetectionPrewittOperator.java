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
public class EdgeDetectionPrewittOperator {
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
            
            int[][] newSize = new int[height][width];
            int count = 0;
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    newSize[i][j] = newImageSize[i][j];
                    if(i == 0 || j % width == 0 || j == width-1 || i == height-1){
                        newSize[i][j] = 255;
                    }
                }
            }
            
            int[] sum = new int [9];
            for(int i = 1; i < height - 1; i++){
                for(int j = 1; j < width - 1; j++){
                    count = 0;
                    for(int a = -1; a < 2; a++){
                        for(int b = -1; b < 2; b++){
                            sum[count] = newImageSize[i - a][j - b];
                            count++;
                        }
                    }
                    newSize[i][j] = ((sum[8] + sum[7] + sum[6]) - (sum[0] 
                                    + sum[1] + sum[2])) + ((sum[2] + sum[5] 
                                    + sum[8]) - (sum[0] - sum[3] - sum[6]));
                }
            }
            
            FileOutputStream output = new FileOutputStream("output/" + txtName + " Edge Detection PrewittOperator.raw");  
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
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
