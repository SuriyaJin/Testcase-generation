package com.ebox.WebTest;

import java.util.Date;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WebTestEngine {

	private static StringBuffer testCase;
	private static File file;
	private static String fileName;
	private BufferedWriter testCaseWriter;
	private static List<TestCase> testCaseList;
	private static Integer totalCount;
	private String path;
		
	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		WebTestEngine.fileName = fileName;
	}

	public List<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public static void setTestCaseList(List<TestCase> testCaseList) {
		WebTestEngine.testCaseList = testCaseList;
	}

	public void generateTest() throws IOException {
		totalCount = 0;
		testCase = new StringBuffer();
		testCase.append("import java.io.File;" + System.getProperty("line.separator") +
                "import java.util.concurrent.TimeUnit;" + System.getProperty("line.separator") +
                "import org.junit.*;" + System.getProperty("line.separator") +
                "import static org.junit.Assert.*;" + System.getProperty("line.separator") +
                "import org.openqa.selenium.*;" + System.getProperty("line.separator") +
                "import org.openqa.selenium.firefox.FirefoxBinary;" + System.getProperty("line.separator") +
                "import org.openqa.selenium.firefox.FirefoxDriver;" + System.getProperty("line.separator") +
                "import org.openqa.selenium.firefox.FirefoxProfile;" + System.getProperty("line.separator") +
                "" + System.getProperty("line.separator") +
                "public class WebTest {" + System.getProperty("line.separator") +
                "  private WebDriver driver;" + System.getProperty("line.separator") +
                "  private String baseUrl;" + System.getProperty("line.separator") +
                "  private boolean acceptNextAlert = true;" + System.getProperty("line.separator") +
                "  private StringBuffer verificationErrors = new StringBuffer();" + System.getProperty("line.separator") +
                "  public int passCount=0;" + System.getProperty("line.separator") +
                "  public String text;" + System.getProperty("line.separator") +
                "  @Before" + System.getProperty("line.separator") +
                "  public void setUp() throws Exception {" + System.getProperty("line.separator") +
                "	  File pathToBinary = new File(\"F:\\\\Firefox\\\\firefox.exe\");" + System.getProperty("line.separator") +
                "	  FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);" + System.getProperty("line.separator") +
                "	  FirefoxProfile firefoxProfile = new FirefoxProfile();" + System.getProperty("line.separator") +
                "	  driver = new FirefoxDriver(ffBinary,firefoxProfile);" + System.getProperty("line.separator") +
                "	  baseUrl = \"#BASE_URL#\";" + System.getProperty("line.separator") +
                "	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);" + System.getProperty("line.separator") +
                "  }" + System.getProperty("line.separator") +
                "" + System.getProperty("line.separator") +
                "  @Test" + System.getProperty("line.separator") +
                "  public void testWeb() throws Exception {" + System.getProperty("line.separator") +
                "    driver.get(baseUrl + \"/index.html\");" + System.getProperty("line.separator"));
		fileName = new Date().getTime()+"WebTest.java";
		fileName.replace(" ", "");
		path = "G:\\Workspace JAVA\\Projects\\Testcase files\\";
		beginGeneration();
		onFinish();
		file = new File(path+fileName);
		try {
			testCaseWriter = new BufferedWriter(new FileWriter(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		testCaseWriter.write(testCase.toString());
		testCaseWriter.close();
		testCase.delete(0, testCase.length());
	}
	
	public static void beginGeneration() {
		for(TestCase test : testCaseList){
			switch(test.getSelector()) {
				case "Id":generateBasedOnId(test);break;
				case "Name":break;
				case "Css Selector":break;
				case "X-Path":break;
				case "Tag":break;
				case "Class Name":break;
			}
		}
	}
	
	public static void generateBasedOnId(TestCase test) {
		switch(test.getAction()) {
			case "Click":generateClick(test);break;
			case "CheckTrue":break;
			case "CheckFalse":break;
			case "Send Keys":break;
			case "Select Options":break;
			case "Verify Text":break;
			case "Contains Text":break;
		}
	}
	
	public static void generateClick(TestCase test) {
		testCase.append("try{" + System.getProperty("line.separator")
					   +"driver.findElement(By.id(\""+test.getSelectorValue()+"\")).click();" + System.getProperty("line.separator")
					   +"}catch(Exception | Error e){" + System.getProperty("line.separator")
					   +"verificationErrors.append(\"Element with id '"+test.getSelectorValue()+"' not found\\n\");" + System.getProperty("line.separator")
					   +"}"+System.getProperty("line.separator"));
	}
	
	public static void onFinish() {
		testCase.append("}" + System.getProperty("line.separator") +
                "\n" + System.getProperty("line.separator") +
                "  public int totalCount="+totalCount+";" + System.getProperty("line.separator") +
                "  @After" + System.getProperty("line.separator") +
                "  public void tearDown() throws Exception {" + System.getProperty("line.separator") +
                "  System.out.println(\"#\"+Math.round((passCount/(float)totalCount)*100));" + System.getProperty("line.separator") +
                "    driver.quit();" + System.getProperty("line.separator") +
                "    String verificationErrorString = verificationErrors.toString();" + System.getProperty("line.separator") +
                "    if (!\"\".equals(verificationErrorString)) {" + System.getProperty("line.separator") +
                "      fail(verificationErrorString);" + System.getProperty("line.separator") +
                "    }\n" + System.getProperty("line.separator") +
                "  }\n" + System.getProperty("line.separator") +
                "\n" + System.getProperty("line.separator") +
                "  private boolean isElementPresent(By by) {" + System.getProperty("line.separator") +
                "    try {\n" + System.getProperty("line.separator") +
                "      driver.findElement(by);" + System.getProperty("line.separator") +
                "      return true;" + System.getProperty("line.separator") +
                "    } catch (NoSuchElementException e) {" + System.getProperty("line.separator") +
                "      return false;" + System.getProperty("line.separator") +
                "    }\n" + System.getProperty("line.separator") +
                "  }\n" + System.getProperty("line.separator") +
                "\n" + System.getProperty("line.separator") +
                "  @SuppressWarnings(\"unused\")" + System.getProperty("line.separator") +
                "private boolean isAlertPresent() {" + System.getProperty("line.separator") +
                "    try {\n" + System.getProperty("line.separator") +
                "      driver.switchTo().alert();" + System.getProperty("line.separator") +
                "      return true;" + System.getProperty("line.separator") +
                "    } catch (NoAlertPresentException e) {" + System.getProperty("line.separator") +
                "      return false;" + System.getProperty("line.separator") +
                "    }\n" + System.getProperty("line.separator") +
                "  }\n" + System.getProperty("line.separator") +
                "\n" + System.getProperty("line.separator") +
                "  @SuppressWarnings(\"unused\")" + System.getProperty("line.separator") +
                "private String closeAlertAndGetItsText() {" + System.getProperty("line.separator") +
                "    try {" + System.getProperty("line.separator") +
                "      Alert alert = driver.switchTo().alert();" + System.getProperty("line.separator") +
                "      String alertText = alert.getText();" + System.getProperty("line.separator") +
                "      if (acceptNextAlert) {" + System.getProperty("line.separator") +
                "        alert.accept();" + System.getProperty("line.separator") +
                "      } else {" + System.getProperty("line.separator") +
                "        alert.dismiss();" + System.getProperty("line.separator") +
                "      }\n" + System.getProperty("line.separator") +
                "      return alertText;" + System.getProperty("line.separator") +
                "    } finally {" + System.getProperty("line.separator") +
                "      acceptNextAlert = true;" + System.getProperty("line.separator") +
                "    }\n" + System.getProperty("line.separator") +
                "  }\n" + System.getProperty("line.separator") +
                "}");
	}
}