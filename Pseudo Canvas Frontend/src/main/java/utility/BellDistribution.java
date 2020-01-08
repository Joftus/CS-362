package utility;

import java.util.ArrayList;
import java.util.List;

import model.Assignment;

public class BellDistribution {

    public static void bellDist(List<Assignment> assignments) {
    		int i, j;
    		// remove position in truple
    		List<Truple> list = new ArrayList<Truple>(); 
    		for (i = 0; i < assignments.size(); i++) list.add(new Truple(assignments.get(i).grade, assignments.get(i).userEmail));
    		float mean = mean(list);
    		List<Truple> innerLayer = new ArrayList<Truple>();
    		List<Truple> midLayer = new ArrayList<Truple>();
    		List<Truple> outerLayer = new ArrayList<Truple>();
    		List<Truple> outliers = new ArrayList<Truple>();
    		// 5 layers of distribuition checking
    		for (j = 0; j < list.size(); j++) {
    			Truple current = list.get(j);
    			if (current.grade < mean + 10 && current.grade > mean - 10) innerLayer.add(current);
    			if ((current.grade < mean + 20 && current.grade > mean + 10) || (current.grade > mean - 20 && current.grade < mean - 10)) midLayer.add(current);
    			if (current.grade < mean + 30 && current.grade > mean - 30 && !innerLayer.contains(current) && !midLayer.contains(current)) outerLayer.add(current);
    			if (!innerLayer.contains(current) && !midLayer.contains(current) && !outerLayer.contains(current)) outliers.add(current);
    		}
    		System.out.println("| out 10% | mid 10% |  in 10%  | mean | in 10% | mid 10% | out 10% |");
    		int outLen = " out 10% ".length();
    		int midLen = "  mid 10% ".length();
    		int inLen = " in 10%  ".length();
    		System.out.println("--------------------------------------------------------------------");
    		double out = 0, in = 0, mid = 0;
    		out = (((outerLayer.size() - midLayer.size() - innerLayer.size()) * 100) / assignments.size());
    		mid = ((midLayer.size() - innerLayer.size()) * 100) / assignments.size();
    		in = ((innerLayer.size() * 100) / assignments.size());
    		if (out < 0) out = 0;
    		if (mid < 0) mid = 0;
    		if (in < 0) in = 0;
    		String space1 = "", space2 = "", space3 = "";
    		for (int k = outLen - (out + "").length(); k > 0; k--) space1 = space1 + " ";
    		for (int l = midLen - (mid + "").length(); l > 0; l--) space2 = space2 + " ";
    		for (int m = inLen - (in + "").length(); m > 0; m--) space3 = space3 + " ";
    		System.out.println("     " + out +
    				space1 + mid +
    				space2 + in + 
    				space3 + mean + space3 + 
    				in + space2 + 
    				mid + space1 + 
    				out);
    		if (outliers.size() > 0) System.out.println("\n\n\nOutliers");
    		for (int n = 0; n < outliers.size(); n++) {
    			System.out.print("Name: " + outliers.get(n).name + ", ");
    			System.out.print("Grade: " + outliers.get(n).grade);
    			if (n + 1 != outliers.size()) System.out.print(" | ");
    		}
    		for (int o = 0; o < Format.pageSizeVert - 2; o++) System.out.println();
    }
    
    public static float mean(List<Truple> grades) {
		int total = 0;
		for (int i = 0; i < grades.size(); i++) {
			total += grades.get(i).grade;
		} 
		return total / grades.size();
	}
}
