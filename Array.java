package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Array {
	public static void main(String[] args) {
		int[] arr1= {1,2,1,1,1,7,9};
		System.out.println(getMaxLength(arr1, 3));
		int []arr2= {1,2,3,3};
		System.out.println(maxLength(arr2,6));
		int [] arr3= {3,-2,-4,0,6};
		System.out.println(maxLength2(arr3,-2));
		int[] arr4= {1,2,5,3,4};
		testSort(arr4);
		for(int i=0;i<arr4.length;i++) {
			System.out.print(arr4[i]);
		}
		System.out.println();
		int [] arr5= {1,-2,3,5,-2,6,-1};
		System.out.println(Max(arr5));
		
		double [] arr6= {-2.5,4,0,3,0.5,8,-1};
		System.out.println(multiMax(arr6));
	}
	private static double multiMax(double[] arr) {
		if(arr.length==0||arr==null) {
			return 0;
		}
		double max=1;
		double min=1;
		double res=0;
		for(int i=0;i<arr.length;i++) {
			max=Math.max(Math.max(max*arr[i],min*arr[i]),arr[i]);
			min=Math.min(Math.min(max*arr[i],min*arr[i]),arr[i]);
			res=Math.max(max,res);

		}
		return res;
	}
	private static int  Max(int[] arr) {
		int min=0;
		int max=0;
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
			min=Math.min(sum, min);
			max=Math.max(sum,max);
		}
		return max-min;
	}
	private static void testSort(int[] arr) {
		int temp=0;
		for(int i=0;i<arr.length;i++) {
			while(arr[i]!=i+1) {
				temp=arr[arr[i]-1];
				arr[arr[i]-1]=arr[i];
				arr[i]=temp;
			}
		}
		
	}
	private static int maxLength2(int[] arr, int k) {
		if(arr.length==0||arr==null) {
			return 0;
		}	
		int sum=0;
		int[] helpArr=new int[arr.length+1];
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
			helpArr[i+1]=Math.max(sum, helpArr[i]);			
		}
		sum=0;
		int len=0;
		int pre=0;
		int res=0;
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
			pre=search(helpArr,sum-k);
			len=(pre==-1?0:i-pre+1);
			res=Math.max(len, res);
		}
		return res;
	}
	private static int search(int[] arr, int k) {
		int low=0,high=arr.length-1;
		int res=-1;
		int mid=0;
		while(low<=high) {
			mid=(low+high)/2;
			if(arr[mid]>=k) {
				res=mid;
				high=mid-1;
			}else {
				low=mid+1;
			}
		}		
		return res;
	}
	private static int maxLength(int[] arr, int k) {
		if(arr.length==0||arr==null) {
			return 0;
		}
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		map.put(0, -1);
		int sum=0;
		int len=0;
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
			if(!map.containsKey(sum)) {
				map.put(sum, i);
			}
			if(map.containsKey(sum-k)) {
				len=Math.max(i-map.get(sum-k),len);
			}
		}
		
		return len;
	}
	public static int getMaxLength(int[] arr,int k) {
		if(arr.length==0||k<=0||arr==null) {
			return 0;
		}
		int left=0,right=0;
		int len=0;
		int sum=arr[0];
		while(right<arr.length) {
			if(sum==k) {
				len=Math.max(len, right-left+1);
				sum-=arr[left++];
			}else if(sum<k) {
				right++;
				if(right==arr.length) {
					break;
				}
				sum+=arr[right];
			}else {
				sum-=arr[left++];
			}
		}
		return len;
	}
}
