package problem3;

public class Sorting {
//implementation of bubble sort, recursive bubble sort, selection sort, insertion sort, merge sort and quick sort,
	public static void main(String[] args) {
		int[] arr = {4, 77, 98, 30, 20, 50, 77, 22, 49,2};
		quickSort(arr, 0, arr.length-1);
		for(int i = 0; i< arr.length; i++) {
			System.out.println(arr[i]);
		}
		

	}
	static void bubble1(int[] arr) {
		for(int i = 0; i<arr.length-1; i++) {
			for(int j = 0; j< arr.length-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}

			}
			
		}
		
	}
	static void bubble2(int[] arr, int end) {
		if(0 == end) {
			return;
		}
		for(int i = 0; i<end; i++) {
			if(arr[i]>arr[i+1]) {
				int temp = arr[i];
				arr[i]=arr[i+1];
				arr[i+1]=temp;
			}
		}
		bubble2(arr,end -1);
	}
	static void selection(int[] arr){
		for(int i = 0;i<arr.length ; i++) {
			int min = i;
			for(int j=i; j<arr.length;j++) {
				if(arr[j]<arr[min]) {
					min = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
			
		}
	}
	static void insertion(int[] arr) {
		for(int i=0;i < arr.length;i++) {
			order(arr, i);
		}
	}
	
	static void order(int[] arr, int pos) {
		int temp = arr[pos];
		int i = pos;
		while(i>0 && temp < arr[i-1]) {
			arr[i] = arr[i-1];
			i--;
			}
		
		arr[i] = temp;
		
		}
	
	static void mergeSort(int [] arr, int start, int end) {
		if(start == end) {
			return;
		}
		int [] temp = new int [end - start + 1];
		int mid = start + (end-start)/2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		merge(arr, temp, start, mid, end);
		return;
		
	}
	
	
	static void merge(int[] arr, int[] temp, int start, int mid, int end) {
		int i = start;
		int j = mid+1;
		int k = 0;
		while(i<=mid && j<=end) {
			if(arr[i]<arr[j]) {
				temp[k++] = arr[i++];
			}
			else {
				temp[k++]=arr[j++];
			}
		}
		
		while(i<=mid) {
			temp[k++]=arr[i++];
		}
		while(j<=end) {
				temp[k++]=arr[j++];
			}
		
		for(int l = 0; l < temp.length; l++) {
			arr[l+start] = temp[l];
		}
	}	
	
	static void quickSort(int arr[], int start, int end) {
		if(start < end) {
			int pivot = partition(arr, start, end);
			quickSort(arr, start, pivot-1);
			quickSort(arr, pivot+1, end);
		}
	}
	
	
	
	
	static int partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		System.out.println("pivot");
		System.out.println(pivot);
		int l = start;
		int r = end-1;
		while(l < r) {
			while(arr[l]<pivot && l<r) {
				l++;
			}
			while(arr[r]>pivot && l<r) {
				r--;
			}
			int temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
		}
		
		arr[end] = arr[r];
		arr[r] = pivot;
		for(int i = 0; i < arr.length -1; i++) {
			System.out.println(arr[i]);
		}
		return r;
	}

}

