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
public class HighPassFiltering {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
//        chess-300X400
//        yoda-123X62
//        Imgpro-122X100
//        bedroom-600X600
//        Picture-500X500
//        planet-400x300
//        shape-400x400 
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
            
            int[][] kernel3x3 = {
                {-1 , -1 , -1},
                {-1 , 8 , -1},
                {-1 , -1 , -1}
            };
            
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
            
            int sumZero, max = 0, min = 0;
            for(int i = 1; i < height - 1; i++){
                for(int j = 1; j < width - 1; j++){
                    sumZero = 0;
                    for(int a = -1; a < 2; a++){
                        for(int b = -1; b < 2; b++){
                            sumZero = (kernel3x3[a + 1][b + 1] * newImageSize[i - a][j - b]) + sumZero;
                            if(sumZero > max){
                                max = sumZero;
                            }
                            if(sumZero < min){
                                min = sumZero;
                            }
                        }
                    }
                }
            }
            
            int sum;
            int total;
            total = max + min;
            Math.abs(total);
            for(int i = 1; i < height - 1; i++){
                for(int j = 1; j < width - 1; j++){
                    sum = 0;
                    for(int a = -1; a < 2; a++){
                        for(int b = -1; b < 2; b++){
                            sum = (kernel3x3[a + 1][b + 1] * newImageSize[i - a][j - b]) + sum;
                        }
                    }
                    if(sum < 0){
                        newSize[i][j] = (255 * (sum + Math.abs(min))/total);
                    }
                    else {
                        newSize[i][j] = sum;
                    }
                }
            }
            FileOutputStream output = new FileOutputStream("output/" + txtName + " HighPass3x3.raw");  
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
