package edu.cmu.cs.lti.ark.ssl.pos.multicore;

import mpi.*;

public class HelloEclipseWorld {
	public static final int TAG = 0;
	
	public static void main(String[] args) {
		mpi(args);
	}
	
	public static void mpi(String[] args) {
		char[] msgArray = new char[32];
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		if (rank == 0) {
			System.out.println(rank + " : I am the master.");
			for (int i = 1; i < size; i ++ ) {
				String msg = "Hello : " + i;
				msgArray = msg.toCharArray();
				MPI.COMM_WORLD.Send(msgArray, 0, msgArray.length, MPI.CHAR, i, TAG);
			}
		} else {
			MPI.COMM_WORLD.Recv(msgArray, 0, msgArray.length, MPI.CHAR, 0, TAG);			
			System.out.println("Slave:" + new String(msgArray));	
		}
		MPI.Finalize();
	}
}