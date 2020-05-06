/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Halftoning {
     public static void main(String[] args) {
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
        
//        int height = 600;
//        int width = 600;
        
        int countHeight = 0; 
        int countWidth = 0;
        int[][] newImageSize = new int[height][width];
        try{
            InputStream myInputFile = new FileInputStream("Images/" + txtName + ".raw");
             
            int value;
            while((value = myInputFile.read()) != -1){
                if(countWidth == width){
                    countWidth = 0;
                    countHeight++;
                }
                newImageSize[countHeight][countWidth] = value;
                countWidth++;
            }
            
            //3x3
            int[][] pattern={
                {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0}, // pattern 0
                {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 255}, // pattern 1
                {255 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 255}, // pattern 2
                {255 , 0 , 255 , 0 , 0 , 0 , 0 , 0 , 255}, // pattern 3
                {255 , 0 , 255 , 0 , 0 , 0 , 255 , 0 , 255}, // pattern 4
                {255 , 0 , 255 , 0 , 0 , 0 , 255 , 255 , 255}, // pattern 5
                {255 , 0 , 255 , 255 , 0 , 0 , 255 , 255 , 255}, // pattern 6
                {255 , 255 , 255 , 255 , 0 , 0 , 255 , 255 , 255}, // pattern 7
                {255 , 255 , 255 , 255 , 0 , 255 , 255 , 255 , 255}, // pattern 8
                {255 , 255 , 255 , 255 , 255 , 255 , 255 , 255 , 255}, // pattern 9             
            };
            
            int[][] newSize = new int[height * 3][width * 3];
            int count = 0, count_1 = 0, count_2 = 0,
                count_3 = 0, count_4 = 0, count_5 = 0;
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    if(newImageSize[i][j] <=25){
                        for(int k = 0; k < 3; k++){
                            for(int l = 0; l < 3; l++){
                                System.out.println("count_3: " + (l + count_3));
                                //System.out.println("count_1: " + (l+count));
                                count_1++;
                                //System.out.println("count_5: " + count_5);
                                count_5++;
                                if(count_1 % 9 == 0){
                                    count += 3;
                                }
                                if(count_5 == 41){
                                    count_5 = 0;
                                }
                                if(count_1 % (width*3) == 0){
                                    count = 0;
                                    count_1 = 0;
                                    count_3 += 3;
                                }
                            }
                        }
                    }
                    //sdnajdnandaknda
                }
            }
            System.out.println("Successful");
            FileOutputStream output = new FileOutputStream("output/" + txtName + "_HALFTONING.raw");
            for(int i = 0; i < height*3; i++ ){
                for(int j = 0; j < width*3; j++){
                    output.write(newSize[i][j]);
                }
            }
            output.close();
        }
        catch(Exception ex){
            System.out.println("Exception: " + ex);
        }       
     }
}
