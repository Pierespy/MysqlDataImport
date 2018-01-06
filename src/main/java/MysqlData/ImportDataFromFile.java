package MysqlData;

import Dao.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ImportDataFromFile {
    public static void main(String[] args) {
        try {
            File mysqlActRecord = new File("");
            File mysqlMovie = new File("");
            File mysqlComment = new File("");
            Dao.JDBC.setURL("3306","");
            Dao.JDBC.setUserAndPassword("root","ibm");


            Scanner scannerAct=new Scanner(new FileInputStream(mysqlActRecord));
            Scanner scannerMovie=new Scanner(new FileInputStream(mysqlMovie));
            Scanner scannerComment=new Scanner(new FileInputStream(mysqlComment));
            while(scannerAct.hasNext())
            {
                String line=scannerAct.nextLine();
                String[] data=line.split("\t");
                Dao.getInstance().insertActRecord(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]));
            }
            while(scannerMovie.hasNext())
            {
                String line=scannerAct.nextLine();
                String[] data=line.split("\t");
                Dao.getInstance().insertMovie(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7]));
            }
            while(scannerComment.hasNext())
            {
                String line=scannerAct.nextLine();
                String[] data=line.split("\t");
                Dao.getInstance().insertComment(data[0],data[1],data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
