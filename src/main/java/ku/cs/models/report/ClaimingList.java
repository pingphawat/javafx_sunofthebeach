package ku.cs.models.report;

import java.util.ArrayList;

public class ClaimingList {
    private ArrayList<Claiming> claimings;

    public ClaimingList(){
        claimings = new ArrayList<>();
    }

    public void addClaiming(Claiming claiming){
        claimings.add(claiming);
    }

    public ArrayList<Claiming> getAllClaimings(){
        return claimings;
    }
}
