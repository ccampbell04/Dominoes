import java.util.ArrayList;
import java.util.List;

public class AllCustomers {
    private List<Customer> listOfCustomers = new ArrayList<Customer>();
    private boolean customersLoaded = false;

    private List<String[]> loadRawCustomers(){
        ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();
        return readDelimitedFile.getFileData("customer.csv");
    }

    private List<Customer>  loadAsCustomers(){
        List<String[]> rawCustomers = loadRawCustomers();
        for (String[] rawCustomer : rawCustomers) {
            listOfCustomers.add( new Customer(rawCustomer[0],rawCustomer[1],rawCustomer[2],rawCustomer[3]));
        }
        customersLoaded = true;
        return listOfCustomers;
    }
    public List<Customer>  getListOfCustomers() {
        if (! customersLoaded){
            loadAsCustomers();
        }
        return listOfCustomers;
    }


}
