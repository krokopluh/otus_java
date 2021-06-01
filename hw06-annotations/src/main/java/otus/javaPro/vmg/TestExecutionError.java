package otus.javaPro.vmg;

public class TestExecutionError {

    private final String testCaseName;
    private final String errorText;


    public TestExecutionError(String testCaseName, String errorText) {
        this.testCaseName = testCaseName;
        this.errorText = errorText;
    }

    public String getErrorInfo(){
        return String.format("-----\n %s failed with following error:\n %s\n", testCaseName,errorText);
    }

}
