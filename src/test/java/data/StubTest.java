package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubTest {

    ReadDelimitedFile loadDelimitedFile = new ReadDelimitedFile();

    @Test
    public void getFileDataFirstLineFirstColumnStub() {
        loadDelimitedFile.setLoadFile(new LoadStub());
        assertEquals("stubData@stub.com", loadDelimitedFile.getFileData("customer.csv").get(0)[0]);
    }

    @Test
    public void getFileDataFirstLineSizeStub() {
        loadDelimitedFile.setLoadFile(new LoadStub());
        assertEquals(3, loadDelimitedFile.getFileData("customer.csv").get(0).length);
    }

    @Test
    public void setFileDelimiterStub() {
        loadDelimitedFile.setLoadFile(new LoadStub());
        loadDelimitedFile.setFileDelimiter("@");
        assertEquals("stubData", loadDelimitedFile.getFileData("customer.csv").get(0)[0]);
    }
}
