package edu.smu.schedulingAlgorithms;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import edu.smu.dataObject.ProcessControlBlock;
import edu.smu.util.GenericUtility;

public class FirstComeFirstServe {
	

	public static void execute(LinkedList<ProcessControlBlock> pcbList){
		System.out.println("----------First Come First Serve----------");
		LinkedList<ProcessControlBlock> newList = new LinkedList<ProcessControlBlock>();
		newList.addAll(pcbList);
		Collections.sort(newList, new Comparator<ProcessControlBlock>(){
			@Override
			public int compare(ProcessControlBlock pcb1, ProcessControlBlock pcb2){
				int res = pcb1.getArrivalTime()-pcb2.getArrivalTime();
				if(res == 0){
					res = pcb1.getBurstTime()-pcb2.getBurstTime();
				}
				return res;
			}
		});
		int temp=0;
		for(ProcessControlBlock pcb: newList){
			System.out.print(pcb.getProcessID()+"("+temp+","+(temp+=pcb.getBurstTime())+") ");
		}
		System.out.println();
		//System.out.println("Before "+pcbList);
		//System.out.println("After FCFS: "+newList);
		System.out.println("Avg Wait Time: "+GenericUtility.calculateAverage(newList));
	}
}
