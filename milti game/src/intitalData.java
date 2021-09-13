import java.util.ArrayList;
public class intitalData implements java.io.Serializable{
   private ArrayList<String> names = new ArrayList<String>();



    public void initialData(){


    }
    public void initialData(ArrayList<String> b){


    }
    public void newName (String Name){
        names.add(Name);
    }
    public String getName ( int n){
        return names.get(n);
    }
    public ArrayList<String> getNames(){return names;}
    public int getSize(){return names.size();}
    public void remove ( int i){names.remove(i);}
}
