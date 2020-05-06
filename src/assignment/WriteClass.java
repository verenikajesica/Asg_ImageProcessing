/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
import java.io.*;
/**
 *
 * @author Student
 */
public class WriteClass {
     public static int hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
     
    public static void main(String [] args){
        try{
//            FileOutputStream myOutputFile = new FileOutputStream("myFile.txt");
//            myOutputFile.write(1);
//            myOutputFile.write(2);
//            myOutputFile.write('T');
//            myOutputFile.close();
//            FileInputStream myInputFile = new FileInputStream("yoda.txt");
            
            File f = new File("Images/yoda.tif");
            byte []array = new byte[(int)f.length()];
            InputStream ism = new FileInputStream(f);
            ism.read(array); 
            
                int value;
                int i = 0;
                
                System.out.println("-----------------------------Header Info-----------------------------");
                System.out.print("Byte order        :");
                System.out.printf("%02X", array[0]);System.out.printf("%02X", array[0]);
                
                System.out.println();
                System.out.print("Version           :");
                System.out.printf("%02X", array[2]);
                
                
                System.out.println();
                System.out.print("Offset            :");
                                
                String first_IFD = String.format("%02X",  array[4]); // Hec
                String second_IFD = String.format("%02X",  array[5]); // Hec
                String third_IFD = String.format("%02X",  array[6]); // Hec
                String four_IFD = String.format("%02X",  array[7]); // Hec
                String join_IFD_string = four_IFD + third_IFD + second_IFD + first_IFD;
                System.out.println(join_IFD_string);
                
                System.out.println();
                System.out.println("-----------------------------Data  Entry-----------------------------");
                System.out.println("Tag                                 Type            Count   Value");
                System.out.println("---------------------------------------------------------------------");
                                
                int dec_a = (int)(array[8]);
                String offsetA = String.valueOf(dec_a);
                int dec_b = (int)(array[9]);
                String offsetB = String.valueOf(dec_b);
                String joinOffsetString = offsetB + offsetA;//combine 2 strings
                int joinInt = Integer.valueOf(joinOffsetString);
                System.out.println(joinInt);
                
                int stripOffset = 0;
                int getWidth = 0, getHeight = 0;
                for(int j = 10; j < joinInt * 12; j+=1){
                    for(int k = i; k < j + 12; k+=12){
                        //find value
                        int values = 0;
                        for(int l = k + 8; l < j + 9; l+=8){
                            String firstValue = String.format("%02X",  array[l]); // Hec
                            String secondValue = String.format("%02X",  array[l+1]); // Hec
                            String thirdValue = String.format("%02X",  array[l+2]); // Hec
                            String fourValue = String.format("%02X",  array[l+3]); // Hec
                            String joinValueString = fourValue + thirdValue + secondValue + firstValue;
                            value = hex2Decimal(joinValueString);
                        }
                        
                        //find type
                        String type = " ";
                        for(int z = k + 2; z < k + 3; z+=2){
                            String firstType = String.format("%02X",  array[z]); // Hec
                            String secondType = String.format("%02X",  array[z+1]); // Hec
                            String joinType = secondType + firstType;
                        }
                    }
                }
                
                System.out.print("fe (New sub file type)              ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("100 (Image width)                   ");
                System.out.print("3(SHORT)        ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("101 (Image height)                  ");
                System.out.print("5(RATIONAL)     ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("102 (Bits per sample)               ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("103 (Compression)                   ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("106 (Photometric interpretation)    ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("111 (Strip offsets)                 ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("115 (Samples per pixel)             ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("116 (Rows per strip)                ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("117 (Strip byte counts)             ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("11a (X resolution)                  ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                System.out.println();
                System.out.print("11b (Y resolution)                  ");
                System.out.print("4(LONG)         ");
                System.out.print("1        ");
                System.out.print("0");
                
                FileInputStream myInputFile = new FileInputStream("Images/yoda.tif");
                System.out.println();
                System.out.println("-----------------------------Image  Data-----------------------------");
                while((value = myInputFile.read()) !=-1){                    
                //System.out.println(value);//print DEC                
                //System.out.print(Integer.toHexString(value));//print HEX                
                String hexFormat = String.format("%02X", value);                
                
                if(i < 14){
                    System.out.printf("%S" + " ", hexFormat);
                    i++;
                    
                }else{
                    System.out.println();
                    System.out.printf("%S" + " " , hexFormat);
                    i = 0;
                }
                
                }
                myInputFile.close();
                
        }catch(IOException ex){
            System.out.println("File outoput Error!");
        }
    }
}

