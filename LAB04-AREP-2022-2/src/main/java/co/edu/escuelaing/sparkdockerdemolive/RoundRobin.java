package co.edu.escuelaing.sparkdockerdemolive;

public class RoundRobin {
	
	private static final Object mutex = new Object();
	
	private static int[] ports = new int[]{35001, 35002, 35003};
	private static int turns = ports.length;
	private static int currentTurn = -1;
	
	public static void addServicePorts(int[] ports) {
		synchronized(mutex) {
			//this.ports = ports;
			currentTurn = -1;
			//turns = ports.lenght;
		}
	}
	
	public static int getNext() {
		synchronized(mutex) {
			if (currentTurn == turns - 1) {
				currentTurn = 0;
			}
			else {
				currentTurn += 1;
			}
		}
		return ports[currentTurn];
	}
}