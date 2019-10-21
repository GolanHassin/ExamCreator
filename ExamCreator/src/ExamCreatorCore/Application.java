/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamCreatorCore;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author David
 */
public class Application 
{
    private ArrayList<Test> tests;
    
    private File file;
    
    public Application(File file) throws IOException
    {
        this.file = file;
        File[] files = file.listFiles();
        tests = new ArrayList(files.length);
        for(File f: files)
        {
            tests.add(new Test(f));
        }
        
    }
    
    /**
     * returns the arrayList of tests
     * 
     * @return the tests
     */
    public ArrayList<Test> getTests()
    {
        return tests;
    }
    
    public Test getTest(int index)
    {
        return tests.get(index);
    }
    
    
    public void addNewTest(Test test)
    {
        tests.add(test);
    }
    
    public void removeTest(int index)
    {
        tests.remove(index).delete();
        
    }
    
    
    
    
}
