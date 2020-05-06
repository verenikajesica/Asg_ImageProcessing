/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Dithering4x4 {
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
            
            int[][] dithering4x4 ={
                { 0 , 128 , 32 , 160},
                { 192 , 64 , 224 , 96},
                { 48 , 176 , 16 , 144},
                { 240 , 112 , 208 , 80}
            };
            
            int count = 0, a = 0, b = 0;
            boolean onOff = true;
            while(onOff){
                for(int i = 0; i < width; i++){
                    if(a == 4){
                        a = 0;
                    }
                    if(newImageSize[count][i] > dithering4x4[0][a]){
                        newImageSize[count][i] = 225;
                    }
                    else{
                        newImageSize[count][i] = 0;
                    } a++;                      
                } 
                //1
                count++;
                if(count == height){
                    break;
                }
                a = 0;
                
                //2
                for(int i = 0; i < width; i++){
                    if(a == 4){
                        a = 0;
                    }
                    if(newImageSize[count][i] > dithering4x4[1][a]){
                        newImageSize[count][i] = 225;
                    }
                    else{
                        newImageSize[count][i] = 0;
                    } a++;
                }
                count++;
                if (count == height){
                    break;
                }
                a = 0;
                
                //3
                for(int i = 0; i < width; i++){
                    if(a == 4){
                        a = 0;
                    }
                    if(newImageSize[count][i] > dithering4x4[2][a]){
                        newImageSize[count][i] = 225;
                    }
                    else{
                        newImageSize[count][i] = 0;
                    } a++;
                }
                count++;
                if(count == height){
                    break;
                }
                a = 0;
                
                //4
                for(int i = 0; i < width; i++){
                    if(a == 4){
                        a = 0;
                    }
                    if(newImageSize[count][i] > dithering4x4[3][a]){
                        newImageSize[count][i] = 225;
                    }
                    else{
                        newImageSize[count][i] = 0;
                    } a++;
                }
                count++;
                if(count == height){
                    break;
                }
                a = 0;
            }
            
            FileOutputStream output = new FileOutputStream("output/" + txtName + "_dithering4x4.raw");  
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    output.write(newImageSize[i][j]);
                }
            }
            output.close();
            
//            FileInfo fi = new FileInfo();
//            fi.width = width;
//            fi.height = height;
//            fi.directory = "output/";
//            fi.fileName = "test_dithering4x4(" +txtName+ ").raw";
//            new FileOpener(fi).open();
            
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
        
    }
}
