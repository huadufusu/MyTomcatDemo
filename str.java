package leetcode;

import java.security.GeneralSecurityException;
import java.util.HashMap;

public class str {
	public static void main(String[] args) {
	String str="A00B00";
	int k=2;
	//System.out.println(removeKZeros(str,k));
	String str1="2ab1";
	String str2="ab12";
	//System.out.println(isRotation(str1,str2));
	String str3="123abcabc";
	String from="abc";
	String to="X";
	//System.out.println(replaceStr(str3,from,to));
	String str4="aaabbadddffc";
	//System.out.println(getNum(str4));
	String str5="a_11_b_39_c_2";
	//System.out.println(getCharAt(str5,50));
	char[] ch= {'a','b','c'};
	System.out.println(DeleteMap(ch));
	}
	public static boolean DeleteMap(char[] ch){
		if(ch==null) {
			return true;
		}
		HashMap<String,String>hashmap=new HashMap<String,String>();
		for(int i=0;i<ch.length;i++) {
			if(hashmap.get(String.valueOf(ch[i]))==null) {
				hashmap.put(String.valueOf(ch[i]),String.valueOf(ch[i]));
			}else {
				return false;
			}			
		}
		
		return true;
	}

	
	public static String getNum(String str) {
		if(str==null||str.equals("")) {
			return null;
		}
		char[] ch=str.toCharArray();
		int count=1;
		String res=String.valueOf(ch[0]);
		for(int i=1;i<ch.length;i++) {
			if(ch[i]!=ch[i-1]) {				
				res=concat(res,String.valueOf(count),String.valueOf(ch[i]));
				count=1;
			}else {
				count++;
			}
		}
/*		if(count!=0) {
			res=res+"_"+count;
		}
		
		return res;*/
		return concat(res,String.valueOf(count),"");
	}
	public static char getCharAt(String str,int index) {
		if(str==null||str.equals("")) {
			return 0;
		}
		char[] ch=str.toCharArray();
		boolean flag=true;
		char cur=0;
		int sum=0,num=0;
		for(int i=0;i<ch.length;i++) {
			if(ch[i]=='_') {
				flag=!flag;
			}else if(flag) {
				sum+=num;
				if(sum>index) {
					return cur;
				}
				num=0;
				cur=ch[i];
			}else {
				num=10*num+ch[i]-'0';
			}
		}
		return sum+num>index?cur:0;
	}


	private static String concat(String res, String count, String chi) {
		System.out.println(chi);
		return res+"_"+count+(chi.equals("")?chi:"_"+chi);
	}



	public static String replaceStr(String str, String from, String to) {
		if(str==null||from==null||from.equals("")||str.equals("")) {
			return str;
		}
		int match=0;
		char[] ch=str.toCharArray();
		char[] fr=from.toCharArray();
		for(int i=0;i<ch.length;i++) {
			if(ch[i]==fr[match]) {
				match++;
				if(match==fr.length) {
					while(match--!=0)
						ch[i--]=0;
					match=0;
				}
			}else {
					if(ch[i]==fr[0]) {
						i--;
					}
					match=0;
				}
			}		
		System.out.println(String.valueOf(ch));	
		
		String res="";
		String cur="";
		boolean flag=true;
		for(int i=0;i<ch.length;i++) {
			if(ch[i]!=0) {
				cur=cur+String.valueOf(ch[i]);
			}
			if(ch[i]==0&&flag) {
				flag=false;
				res=res+cur+to;
				cur="";
			}			
		}
		if(!cur.equals("")) {
			res=res+cur;
		}
		
		return res;
		
	}



	public static boolean isRotation(String str1, String str2) {
		if(str1==null||str2==null||str1.length()!=str2.length()) {
			return false;
		}
		String str=str1+str2;
		System.out.println(str);
		
		return getIndexOf(str,str1)==-1?false:true;
	}



	public static int getIndexOf(String s, String m) {
		if(s==null||m==null||m.length()<1||s.length()<m.length()) {
			return -1;
		}
		char[]ss=s.toCharArray();
		char[]ms=m.toCharArray();
		int si=0,mi=0;
		int[]next=getNextArray(ms);
		while(si<ss.length&&mi<ms.length) {
			if(ss[mi]==ms[mi]) {
				si++;
				mi++;
			}else if(next[mi]==-1) {
				si++;
			}else {
				mi=next[mi];
			}
		}
		return mi==ms.length?si-mi:-1;
	}



	public static int[] getNextArray(char[] ms) {
		if(ms.length==1) {
			return new int[] {-1};
		}
		int[]next=new int[ms.length];
		next[0]=-1;
		next[1]=0;
		int pos=2;
		int cn=0;
		while(pos<next.length) {
			if(ms[pos-1]==ms[cn]) {
				next[pos++]=++cn;
			}else if(cn>0) {
				cn=next[cn];
			}else {
				next[pos++]=0;
			}
		}
		return next;
	}



	public static String removeKZeros(String str, int k) {
		if(str==null||k<1) {
			return str;
		}
		char[] ch=str.toCharArray();
		
		int count=0;
		int start=-1;
		for(int i=0;i<ch.length;i++) {
			if(ch[i]-'0'==0) {
				++count;
				start=start==-1?i:start;

				}
			else {
				if(count==k) {
					while(count--!=0)
						ch[start++]=0;
					}
				count=0;
				start=-1;
				}

			}
		if(count==k) {
			while(count--!=0)
				ch[start++]=0;

		}

		
		return String.valueOf(ch);
	}
}
