package edu.smu.schedulingAlgorithms;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import edu.smu.dataObject.ProcessControlBlock;
import edu.smu.util.GenericUtility;

public class NonPreemptivePriority {
	

	/**
	 * 
	 * @param pcbList
	 */
	public static void execute(LinkedList<ProcessControlBlock> pcbList){
		System.out.println("----------Non-Preemptive Priority----------");
		LinkedList<ProcessControlBlock> newList = new LinkedList<ProcessControlBlock>();
		LinkedList<ProcessControlBlock> newList1 = new LinkedList<ProcessControlBlock>();
		LinkedList<ProcessControlBlock> resultList = new LinkedList<ProcessControlBlock>();
		newList.addAll(pcbList);
		int temp=0;
		
		while(newList.size()>0){
			newList1.clear();
			for(ProcessControlBlock pcb: newList){
				if(pcb.getArrivalTime()<=temp){
					newList1.add(pcb);
				}
			}
			if(newList1.size()==0){
				temp++;
				continue;
			}
			Collections.sort(newList1, new Comparator<ProcessControlBlock>(){
				@Override
				public int compare(ProcessControlBlock pcb1, ProcessControlBlock pcb2){
					return pcb1.getPriority()-pcb2.getPriority();
				}
			});
			int init=temp;
			temp+=newList1.get(0).getBurstTime();
			resultList.add(newList1.get(0));
			newList.remove(newList1.get(0));
			System.out.print(newList1.get(0).getProcessID()+"("+init+","+temp+") ");
		}
		System.out.println();
		//System.out.println("Before "+pcbList);
		//System.out.println("After SJF: "+resultList);
		System.out.println("Avg Wait Time: "+GenericUtility.calculateAverage(resultList));
	}
}
