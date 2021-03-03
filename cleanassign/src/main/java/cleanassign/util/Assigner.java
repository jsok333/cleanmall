package cleanassign.util;

import cleanassign.CleanassignCompleted;

public class Assigner {
	
	private static String[][] driverBank = 
	{
		{"백영곤", "010-2345-6789", "34가4567"},
		{"안채우", "010-3345-7789", "44나4567"},
		{"임광현", "010-4345-8789", "54다4567"},
		{"장윤정", "010-5345-9789", "64라4567"},
		{"옥준삼", "010-6345-0789", "74마4567"},
		{"유승오", "010-7345-1789", "84사4567"}
	};
	
	public static CleanassignCompleted get택시할당됨() {
		CleanassignCompleted 할당확인됨 = new CleanassignCompleted();
		
		int randDriver = (int)(Math.random() * 6);
		할당확인됨.set택시기사이름(driverBank[randDriver][0]);
		할당확인됨.set택시기사전화번호(driverBank[randDriver][1]);
        할당확인됨.set택시번호(driverBank[randDriver][2]);
        return 할당확인됨;
	}

}
