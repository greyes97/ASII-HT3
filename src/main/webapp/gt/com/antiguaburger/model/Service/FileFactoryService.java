package webapp.gt.com.antiguaburger.model.Service;

public class FileFactoryService {

    public IFileService getFile(String whatFile){
        switch (whatFile){
            case "json":return new JsonFile();
            case "xml": return new XmlFile();
            case "txt": return new TxtFile();
            case "csv": return new CsvFile();
            default: return null;
        }
    }

}
