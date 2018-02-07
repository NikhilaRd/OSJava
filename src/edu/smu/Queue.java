package edu.smu;

import java.util.LinkedList;
import edu.smu.dataObject.ProcessControlBlock;

public class Queue {
	
	public static void addItem(LinkedList<ProcessControlBlock> queue, ProcessControlBlock pcb ){
		queue.add(pcb);
	}
	public static void addItem(LinkedList<ProcessControlBlock> queue, ProcessControlBlock pcb, int i){
		queue.add(i, pcb);
	}
	
	public static void removeItem(LinkedList<ProcessControlBlock> queue, ProcessControlBlock pcb ){
		queue.remove(pcb);
	}
	public static void removeItem(LinkedList<ProcessControlBlock> queue, ProcessControlBlock pcb, int i){
		queue.remove(i);
	}
	public static boolean removeItem(LinkedList<ProcessControlBlock> queue, String processId){
		ProcessControlBlock pcb = null;
		for(ProcessControlBlock pcb1: queue){
			if(processId.trim().equals(pcb1.getProcessID().trim())){
				pcb=pcb1;
				queue.remove(pcb);
				return true;
			}
		}
		System.out.println("Process ID: "+processId+" does not exist");
		return false;
	}
}
