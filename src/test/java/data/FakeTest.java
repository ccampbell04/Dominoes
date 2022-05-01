package data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FakeTest {

    LoadFile loadFile = new LoadFile();

    @Test
    void getFileDataFirstRowMock() {
        List<String> data = new ArrayList<>();
        data.add("FakeScanner,Scanner");
        data.add("Test,McTest");
        FakeScanner mockScanner = new FakeScanner("src/main/resources/customer.csv", data);
        loadFile.setPropertyReader(mockScanner);

        assertEquals("FakeScanner,Scanner",
                loadFile.getFileData("src/main/resources/customer.csv").get(0));
    }
}
