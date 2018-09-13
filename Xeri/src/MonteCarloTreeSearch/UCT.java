package MonteCarloTreeSearch;

import java.util.ArrayList;

public class UCT {

    public static double uctValue(int totalVisit, double nodeWinScore, int nodeVisit) {
        if (nodeVisit == 0) {
            return Integer.MAX_VALUE;
        }
        return (nodeWinScore / (double) nodeVisit) + 1.41 * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
    }

    static Node findBestNodeWithUCT(Node node) {
    	ArrayList<Node> childArray = node.getChildArray();
    	int parentVisit = node.getState().getVisitCount();
    	double maxUCBValue = uctValue(parentVisit,childArray.get(0).getState().getWinScore(),childArray.get(0).getState().getVisitCount());
    	Node nodeWithMaxUCB = childArray.get(0);

        
        for (int i = 1; i < childArray.size(); i++) {
        	double tmp = uctValue(parentVisit,childArray.get(i).getState().getWinScore(),childArray.get(i).getState().getVisitCount());
			if(maxUCBValue < tmp) {
				maxUCBValue = tmp;
				nodeWithMaxUCB = childArray.get(i);
			}
		}
        
        return nodeWithMaxUCB;
    }
    
}
