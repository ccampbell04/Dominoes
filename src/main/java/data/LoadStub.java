package data;

import java.util.ArrayList;
import java.util.List;

public class LoadStub implements Load{
    @Override
    public List<String> getFileData(String fileName) {
        List<String> stubData = new ArrayList<String>();
        stubData.add("stubData@stub.com, Stub McStub, StubPassword1");
        stubData.add("tester@stub.com, Tester McTest, TestPassword1");
        return stubData;
    }

    @Override
    public List<String> getLastLines(String fileName, int numberOfLines) {
        return null;
    }
}
