package MysqlData;

import Dao.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import Dao.*;


public class ImportDataFromFile {
    public static void main(String[] args) {
        try {
            File mysqlActRecord = new File("/home/ibm/Documents/pieres/mysqlactrecord");
            File mysqlMovie = new File("/home/ibm/Documents/pieres/mysqlmovie");
            File mysqlComment = new File("/home/ibm/Documents/pieres/moviecomment");
            JDBC.setURL("3306","datawarehouse");
            JDBC.setUserAndPassword("root","ibm");


            Scanner scannerAct=new Scanner(new FileInputStream(mysqlActRecord));
            Scanner scannerMovie=new Scanner(new FileInputStream(mysqlMovie));
            Scanner scannerComment=new Scanner(new FileInputStream(mysqlComment));
            while(scannerAct.hasNext())
            {
                try {
                    String line = scannerAct.nextLine();
                    String[] data = line.split("\t");
                    Dao.getInstance().insertActRecord(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            while(scannerMovie.hasNext()) {
                try {
                    String line = scannerAct.nextLine();
                    String[] data = line.split("\t");
                    Dao.getInstance().insertMovie(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            while(scannerComment.hasNext()) {
                try {
                    String line = scannerAct.nextLine();
                    String[] data = line.split("\t");
                    Dao.getInstance().insertComment(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
