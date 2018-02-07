package edu.smu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import edu.smu.dataObject.ProcessControlBlock;
import edu.smu.schedulingAlgorithms.FirstComeFirstServe;
import edu.smu.schedulingAlgorithms.NonPreemptivePriority;
import edu.smu.schedulingAlgorithms.RoundRobin;
import edu.smu.schedulingAlgorithms.ShortestJobFirst;

public class ProcessManagement {

	private static final String FILE_NAME = "/home/nikhila/Documents/OS/PCB.txt";
	
	public static void main(String[] args) {
		
		LinkedList<ProcessControlBlock> readyQueue = new LinkedList<ProcessControlBlock>();
		LinkedList<ProcessControlBlock> waitingQueue = new LinkedList<ProcessControlBlock>();
		BufferedReader br = null;
		FileReader fr = null;
		BufferedReader brInput = null;

		try {

			brInput = new BufferedReader(new InputStreamReader(System.in));
			fr = new FileReader(FILE_NAME);
			br = new BufferedReader(fr);
			String currentLine;

			br = new BufferedReader(new FileReader(FILE_NAME));

			while ((currentLine = br.readLine()) != null) {
				String[] pcbArray = currentLine.split("\\|");
				if(pcbArray.length==4){
					try{
						ProcessControlBlock pcb = new ProcessControlBlock();
						pcb.setProcessID(pcbArray[0]);
						pcb.setBurstTime(Integer.parseInt(pcbArray[1]));
						pcb.setPriority(Integer.parseInt(pcbArray[2]));
						pcb.setArrivalTime(Integer.parseInt(pcbArray[3]));
						System.out.println(pcb);
						Queue.addItem(readyQueue, pcb);
					} catch(NumberFormatException nfe){
						System.out.println("PCB is invalid " + currentLine);
					}
				} else {
					System.out.println("PCB is invalid: " + currentLine);
				}
			}
			
			int input1 = 0;
			int input2 = 0;
			do{
				do {
					try{
						System.out.println("Please Enter a choice: 1. Part 1 PCB - Ready Queue operations"
								+ "\n2. Part 1 PCB - Waiting Queue operations"
								+ "\n3. Part 2 - Schedulers"
								+ "\n4. Display PCBs in Ready Queue and Waiting Queue");
						input1 = Integer.parseInt(brInput.readLine());
						if(input1<1 || input1>4){
							System.out.println("Invalid input. Please enter a valid input");
						}
					}catch(NumberFormatException nfe){
						System.out.println("Invalid input. Please enter a valid input");
						//nfe.printStackTrace();
					}
				}while(input1<1 || input1>4);

				switch(input1){
				
					case 1:
						//part 1 - PCB Ready Queue
						do {
							try{
								System.out.print("Please Enter a choice: \n1. Add a PCB to ready Queue"
										+ "\n2. Delete a queue from PCB");
								input2 = Integer.parseInt(brInput.readLine());
								if(input2!=1&&input2!=2){
									System.out.println("Invalid input. Please enter a valid input");
								}
							}catch(NumberFormatException nfe){
								System.out.println("Invalid input. Please enter a valid input");
								//nfe.printStackTrace();
							}
						}while(input2!=1&&input2!=2);
						switch(input2){
							case 1:
								ProcessControlBlock pcb = inputPCB(brInput);
								
								int index = -1;
								do {
									try{
										System.out.print("Enter the position where PCB needs to be added:");
										index = Integer.parseInt(brInput.readLine());
										if(index>=readyQueue.size()){
											System.out.println("Invalid input. Index:"+index+" cannot be greater than queue size:"+readyQueue.size());
										}
									}catch(NumberFormatException nfe){
										System.out.println("Invalid input. Please enter a valid input");
										//nfe.printStackTrace();
									}
								}while(index>=readyQueue.size());
								Queue.addItem(readyQueue, pcb,index);
								System.out.println("PCB Added successfully");
								System.out.println(readyQueue);
								break;
							case 2:
								System.out.print("Please Enter Process ID: ");
								if(Queue.removeItem(readyQueue, brInput.readLine()))
									System.out.println("PCB Removed successfully");
								System.out.println(readyQueue);
								break;
						}
						break;
					case 2:
						//part 1 - PCB Ready Queue
						do {
							try{
								System.out.print("Please Enter a choice: \n1. Add a PCB to Waiting Queue"
										+ "\n2. Delete a queue from PCB");
								input2 = Integer.parseInt(brInput.readLine());
								if(input2!=1&&input2!=2){
									System.out.println("Invalid input. Please enter a valid input");
								}
							}catch(NumberFormatException nfe){
								System.out.println("Invalid input. Please enter a valid input");
								//nfe.printStackTrace();
							}
						}while(input2!=1&&input2!=2);
						switch(input2){
							case 1:
								ProcessControlBlock pcb = inputPCB(brInput);
								
								int index = -1;
								do {
									try{
										System.out.print("Enter the position where PCB needs to be added:");
										index = Integer.parseInt(brInput.readLine());
										if(index!=0 && index>=waitingQueue.size()){
											System.out.println("Invalid input. Index:"+index+" cannot be greater than queue size:"+waitingQueue.size());
										}
									}catch(NumberFormatException nfe){
										System.out.println("Invalid input. Please enter a valid input");
										//nfe.printStackTrace();
									}
								}while(index!=0 && index>=waitingQueue.size());
								Queue.addItem(waitingQueue, pcb,index);
								System.out.println("PCB Added successfully");
								System.out.println(waitingQueue);
								break;
							case 2:
								System.out.print("Please Enter Process ID: ");
								if(Queue.removeItem(waitingQueue, brInput.readLine()))
									System.out.println("PCB Removed successfully");
								System.out.println(waitingQueue);
								break;
						}
						break;
					case 3: 
						//Part 2 - Schedulers
						System.out.print("Please enter Time Quantum for Round Robin: ");
						String s = brInput.readLine();
						int quantum = Integer.parseInt(s);
						ShortestJobFirst.execute(readyQueue);
						FirstComeFirstServe.execute(readyQueue);
						NonPreemptivePriority.execute(readyQueue);
						RoundRobin.execute(readyQueue, quantum);
						break;
					case 4:
						System.out.println("Ready Queue"+readyQueue);
						System.out.println("Waiting Queue"+waitingQueue);
				}
			}while(true);
		} catch (IOException e) {
			System.out.println("Exception while reading file: "+FILE_NAME);
		} finally {

			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	private static ProcessControlBlock inputPCB(BufferedReader brInput){
		ProcessControlBlock pcb = new ProcessControlBlock();
		boolean flag = false;
		System.out.println("Please enter PCB values:");
		try {
			System.out.print("Please Enter Process ID: ");
			pcb.setProcessID(brInput.readLine());
			do{
				try {
					System.out.print("Please Enter Burst Time for the process: ");
					pcb.setBurstTime(Integer.parseInt(brInput.readLine()));
					flag = false;
				} catch (NumberFormatException e) {
					flag = true;
					System.out.println("Invalid Input.");
				}
			}while(flag);
			do{
				try {
					System.out.print("Please Enter Priority for the process: ");
					pcb.setPriority(Integer.parseInt(brInput.readLine()));
					flag = false;
				} catch (NumberFormatException e) {
					flag = true;
					System.out.println("Invalid Input.");
				}
			}while(flag);
			do{
				try {
					System.out.print("Please Enter Arrival Time for the process: ");
					pcb.setArrivalTime(Integer.parseInt(brInput.readLine()));
					flag = false;
				} catch (NumberFormatException e) {
					flag = true;
					System.out.println("Invalid Input.");
				}
			}while(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pcb;
	}
}