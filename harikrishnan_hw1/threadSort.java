import java.lang.*;
import java.io.*;
import java.util.Scanner; 

class sorter extends Thread{
	int[] sortArray;
	
	public sorter(int[] sortArray) {
		this.sortArray= sortArray;
	}

	public void run() {
	int temp;
	for(int i=1; i<5; i++) {
	for(int j=i; j>0; j--) {
	if(sortArray[j]<sortArray[j-1]) {
		temp=sortArray[j];
		sortArray[j]=sortArray[j-1];
		sortArray[j-1]=temp;
			}
		}
	}	
	}
}

class merger extends Thread{
	int[] result= new int[10];
	public void finalMerger(int[] a, int[] b){
	int i=0,j=0,r=0;
	while(i<a.length && j<b.length){
	if(a[i]<=b[j]){
	result[r]=a[i];
	i++;
	r++;
	}
	else{
	result[r]=b[j];
	j++;
	r++;
	}
	}
	while(i<a.length){
	result[r]=a[i];
	r++;
	i++;
	}
	while(j<b.length){
	result[r]=b[j];
	r++;
	j++;
}
}
}

public class threadSort{
	public static void main(String[] args) {
	int n,i,j;
	int[] sortArray= new int[] {34,12,66,73,45,22,90,43,21,95};
	n=10;
	for(i=0;i<10;i++)
	System.out.println(sortArray[i]);
	int[] arr1= new int[n/2];
	int[] arr2= new int[n/2];
	for(i=0;i<n/2;i++)
	arr1[i]=sortArray[i];
	for(i=5,j=0;i<n;i++,j++)
	arr2[j]=sortArray[i];
	sorter thread1= new sorter(arr1);
	thread1.start();
	sorter thread2= new sorter(arr2); 
	thread2.start();
	try {
 thread1.join( );
 thread2.join( );
 } catch (InterruptedException e) {}
	merger f = new merger();
	f.finalMerger(arr1,arr2);
	System.out.print("The final sorted array is:  ");
	for(i=0;i<n;i++)
	System.out.println(f.result[i]);
	}	
}

