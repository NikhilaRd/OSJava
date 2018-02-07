package edu.smu.util;

import java.util.LinkedList;

import edu.smu.dataObject.ProcessControlBlock;

public class GenericUtility {
	public static double calculateAverage(LinkedList<ProcessControlBlock> pcbList){
		pcbList.get(0).setWaitTime(0);
		int sum =0;
		System.out.println(pcbList.get(0).getWaitTime());
		for(int i=1;i<pcbList.size();i++){
			pcbList.get(i).setWaitTime(
					pcbList.get(i-1).getWaitTime()+pcbList.get(i-1).getArrivalTime()
					+pcbList.get(i-1).getBurstTime()-pcbList.get(i).getArrivalTime());
			int waitTime = pcbList.get(i).getWaitTime();
			System.out.println(waitTime);
			sum+=waitTime;
		}
		return (double)sum/pcbList.size();	
		}

}
