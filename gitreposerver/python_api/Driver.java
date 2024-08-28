package demo.testJavaTriggerPython;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args){

        try{
            String pythonFileAddress = "C:\\Users\\eheinjc\\ESProject\\project-c6\\apiTest.py";
            String gitRepoUrl = "https://github.com/kevinniland/Ericsson-Coding";
            String[] argg = new String[] {"python", pythonFileAddress,gitRepoUrl};
            Process pr = Runtime.getRuntime().exec(argg);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
