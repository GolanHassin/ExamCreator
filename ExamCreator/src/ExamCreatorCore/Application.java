package ExamCreatorCore;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The application class is the class with all the core functionality of what the java application is supposed to do.
 * It loads the Tests and stores them in memory.
 * @version 0.1.0
 * @author dphaighton
 */
public class Application 
{
    private List<Test> tests;
    
    private File file;
    
    /**
     * 
     * @param file the file where all the tests are found
     * @throws IOException if the file does not exist
     */
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
     * The list of tests
     * 
     * @return the tests
     */
    public List<Test> getTests()
    {
        return tests;
    }
    
    /**This returns the specified test at an index
     * 
     * @param index the index for the test
     * @return the test found at index in the list of tests
     */
    public Test getTest(int index)
    {
        return tests.get(index);
    }
    
    /**
     * This will add and save a new test to the list of tests
     * @param test the test to be added
     */
    public void addNewTest(Test test) throws IOException
    {
        test.save(file);
        tests.add(test);
        
    }
    /**This will remove and delete the specified test
     * 
     * @param index the index in the list on where to find the test
     */
    public void removeTest(int index)
    {
        tests.remove(index).delete();
        
    }
    
    
    
    
}
