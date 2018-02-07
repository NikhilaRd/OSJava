package edu.smu.schedulingAlgorithms;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import edu.smu.dataObject.ProcessControlBlock;

public class RoundRobin {
	
	public static void execute(LinkedList<ProcessControlBlock> pcbList, int slice){
		System.out.println("----------Round Robin----------");
		LinkedList<ProcessControlBlock> tempList = new LinkedList<ProcessControlBlock>();
		tempList.addAll(pcbList);
		int temp = 0;
		int sum = 0;
		Collections.sort(tempList, new Comparator<ProcessControlBlock>(){
			@Override
			public int compare(ProcessControlBlock pcb1, ProcessControlBlock pcb2){
				int res = pcb1.getArrivalTime()-pcb2.getArrivalTime();
				if(res == 0){
					res = pcb1.getBurstTime()-pcb2.getBurstTime();
				}
				return res;
			}
		});
		int bt[] = new int[tempList.size()];
		int max=tempList.get(0).getBurstTime();
		for(int i=0;i<tempList.size();i++){
			bt[i]=tempList.get(i).getBurstTime();
			if(max<bt[i]){
				max=bt[i];
			}
		}
		
		for(int j=0;j<max/slice+1;j++){
			int initTemp = temp;
			for(int k=0;k<tempList.size();k++){				
				if(bt[k]!=0 && temp>=tempList.get(k).getArrivalTime()){
					if(bt[k]<=slice){
						int intitialTemp = temp;
						temp+=bt[k];
						bt[k] = 0;
						int waitTime = temp-tempList.get(k).getBurstTime()-tempList.get(k).getArrivalTime();
						tempList.get(k).setWaitTime(waitTime);
						sum+=waitTime;
						System.out.println(tempList.get(k).getProcessID()+"("+intitialTemp+","+(temp)+") complete");
						//System.out.println("WaitTime of:"+waitTime+" "+temp+" "+pcbList.get(k).getProcessID()+" is: "+waitTime);
					} else {
						bt[k] -=slice;
						System.out.print(tempList.get(k).getProcessID()+"("+temp+","+(temp+=slice)+") ");
					}
				}
			}
			if(temp==initTemp){
				temp++;
			}
		}
		sum =0;
		for(ProcessControlBlock pcb:tempList){
			sum+=pcb.getWaitTime();
		}
		
		System.out.println("Avg Wait Time: "+(double)sum/tempList.size());
	}

}
