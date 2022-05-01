package data;

import java.util.List;

public interface Load {

    public List<String> getFileData(String fileName);
    public List<String> getLastLines( String fileName,int numberOfLines);
}
