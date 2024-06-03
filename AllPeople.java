
import java.util.ArrayList;

public class AllPeople {

    private ArrayList<User> all;
private ArrayList<Stores> allStore;
    public AllPeople(ArrayList<User> all){
        this.all=all;
    }
    public AllPeople(){
        this.all=new ArrayList<>();
        this.allStore=new ArrayList<>();
    }

    public ArrayList<User> getAll() {
        return all;
    }

    public ArrayList<Stores> getAllStore() {
        return allStore;
    }
}
