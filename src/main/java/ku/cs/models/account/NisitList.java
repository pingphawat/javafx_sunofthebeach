package ku.cs.models.account;

import ku.cs.services.Filterer;
import java.util.ArrayList;

public class NisitList {
    private ArrayList<Nisit> nisits;

    public NisitList(){
        nisits = new ArrayList<>();
    }

    public void addNisit(Nisit nisit){
        nisits.add(nisit);
    }

    public ArrayList<Nisit> getAllNisits(){
        return nisits;
    }

    public NisitList filterBy(Filterer<Nisit> filterer){
        NisitList filtered = new NisitList();
        for(Nisit nisit: nisits){
            if(filterer.filter(nisit)){
                filtered.addNisit(nisit);
            }
        }
        return filtered;
    }

    public Nisit findAccount(String username) {
        Nisit found = null;
        for (Nisit nisit : nisits) {
            if (username.equals(nisit.getUsername())) {
                found = nisit;
                return found;
            }
        }
        return found;
    }
}
