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
public class LowPassFiltering5x5 {
    public static void main(String[] args) {        
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
            
            double[][] data = {
                    {0.04 , 0.04 , 0.04 , 0.04 , 0.04}, // 0
                    {0.04 , 0.04 , 0.04 , 0.04 , 0.04}, // 1
                    {0.04 , 0.04 , 0.04 , 0.04 , 0.04}, // 2
                    {0.04 , 0.04 , 0.04 , 0.04 , 0.04}, // 3
                    {0.04 , 0.04 , 0.04 , 0.04 , 0.04}  // 4
            };
            
            int[][] newSize = new int [height][width];
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    newSize[i][j] = newImageSize[i][j];
                    if(i == 0 || j % width == 0 || j == width-1 || i == height-1){
                        newSize[i][j] = 255;
                    }
                }
            }
            
            int sum;
            double temp;
            int num_1 , num_2 , num_3;
            for(int i = 1; i < height - 1; i++){
                for(int j = 1; j < width - 1; j++){
                    sum = 0;

                    for(int a = -3; a < 2; a++){
                        for(int b = -3; b < 2; b++){
                            num_1 = i - a;
                            num_2 = j - b;
                            
                            if(num_1 >= width){
                                num_3 = 0;
                            }
                            else if(num_2 >= height){
                                num_3 = 0;
                            }
                            else if(num_1 >= height || num_2 >= width){
                                num_3 = 0;
                            }
                            else{
                                num_3 = newImageSize[i - a][j - b];
                            }
                            sum = (int)(data[a + 3][b + 3] * num_3) + sum;
                        }
                    }
                    if(sum < 0){
                        newSize[i][j] = 0;
                    }
                    else if(sum > 255){
                        newSize[i][j] = 255;
                    }
                    else {
                        newSize[i][j] = sum;
                    }
                }
            }

            FileOutputStream output = new FileOutputStream("output/" + txtName + " LowPass5x5.raw");  
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
