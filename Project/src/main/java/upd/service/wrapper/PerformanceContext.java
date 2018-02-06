package upd.service.wrapper;

import upd.model.Performance;
import upd.model.PerformancePerson;

import java.util.ArrayList;
import java.util.HashMap;

public class PerformanceContext {

    private Performance performance;
    private HashMap<String,ArrayList<Integer>> personList;

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public HashMap<String, ArrayList<Integer>> getPersonList() {
        return personList;
    }

    public void setPersonList(HashMap<String, ArrayList<Integer>> personList) {
        this.personList = personList;
    }
}
