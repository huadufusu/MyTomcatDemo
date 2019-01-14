package leetcode;

import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.Queue;

import javax.sql.rowset.serial.SerialArray;
import javax.sql.rowset.serial.SerialBlob;

public class Node {
	public Node left;
	public Node right;
	public int value;
	public Node(int value) {
		this.value=value;
	}
	public static void printEdge1(Node head) {
		if(head==null) {
			return;
		}
		int height=getHeight(head,0);
		Node[][] edgeMap=new Node[height][2];
		setEdgeMap(head,0,edgeMap);
		for(int i=0;i<edgeMap.length;i++) {
			System.out.print(edgeMap[i][0].value+" ");
		}
		printLeafNotMap(head,0,edgeMap);
		for(int i=edgeMap.length-1;i>=0;i--) {
			if(edgeMap[i][0]!=edgeMap[i][1]) {
				System.out.print(edgeMap[i][1].value+" ");
			}
		}
	}
	private static void  printLeafNotMap(Node head, int i, Node[][] edgeMap) {
		if(head==null) {
			return;
		}
		if(head.left==null&&head.right==null&&
				head!=edgeMap[i][0]&&head!=edgeMap[i][1]) {
			System.out.print(head.value+" ");
		}

		printLeafNotMap(head.left, i+1, edgeMap);
		printLeafNotMap(head.right, i+1, edgeMap);
		
	}
	private static void setEdgeMap(Node head, int i, Node[][] edgeMap) {
		if(head==null) {
			return;
		}
		edgeMap[i][0]=(edgeMap[i][0]==null?head:edgeMap[i][0]);
		edgeMap[i][1]=head;
		setEdgeMap(head.left, i+1, edgeMap);
		setEdgeMap(head.right, i+1, edgeMap);
		
	}
	private static int getHeight(Node head, int i) {
		if(head==null) {
			return i;
		}		
		return Math.max(getHeight(head.left,i+1), getHeight(head.right,i+1));
	}

	public static  String serialByPre(Node head){
		if(head==null) {
			return "#!";
		}
		String res=head.value+"!";
		res+=serialByPre(head.left);
		res+=serialByPre(head.right);
		return res;
	}
	public static Node reconByPreString(String preStr) {
		String[] values=preStr.split("!");
		Queue<String>queue=new LinkedList<String>();
		for(int i=0;i!=values.length;i++) {
			queue.offer(values[i]);
		}
		return reconPreOrder(queue);
	}
	public static Node reconPreOrder(Queue<String>queue){
		String value=queue.poll();
		if(value.equals("#")) {
			return null;
		}
		Node head=new Node(Integer.valueOf(value));
		head.left=reconPreOrder(queue);
		head.right=reconPreOrder(queue);
		return head;		
	}
	public static String serialByLevel(Node head){
		if(head==null) {
			return "#!";
		}
		String res=head.value+"!";
		Queue<Node>queue=new LinkedList<Node>();
		queue.offer(head);
		while(!queue.isEmpty()) {
			head=queue.poll();
			if(head.left!=null) {
				res+=head.left.value+"!";
				queue.offer(head.left);
			}else {
				res+="#!";
			}
			if(head.right!=null) {
				res+=head.right.value+"!";
				queue.offer(head.right);
			}else {
				res+="#!";
			}
		}
		return res;
	}
	public static Node reconByLevelString(String levelStr) {
		String[] values=levelStr.split("!");
		Queue<Node>queue=new LinkedList<Node>();
		int index=0;
		Node head=generateNodeByString(values[index++]);
		if(head!=null) {
			queue.offer(head);
		}
		Node node=null;
		while(!queue.isEmpty()) {
			node=queue.poll();
			node.left=generateNodeByString(values[index++]);
			node.right=generateNodeByString(values[index++]);
			if(node.left!=null) {
				queue.offer(node.left);
			}
			if(node.right!=null) {
				queue.offer(node.right);
			}
		}
		return head;
	}
	public static Node generateNodeByString(String val) {
		if(val.equals("#")) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}
	public static void morrisIn(Node head) {
		if(head==null) {
			return;
		}
		Node cur1=head;
		Node cur2=null;
		while(cur1!=null) {
			cur2=cur1.left;
			if(cur2!=null) {
				while(cur2.right!=null&&cur2.right!=cur1) {
				cur2=cur2.right;			
			}
			if(cur2.right==null) {
				cur2.right=cur1;
				cur1=cur1.left;
				continue;
			}else {
				cur2.right=null;
			}
			}
			System.out.print(cur1.value+" ");
			cur1=cur1.right;
		
	}
	}
	public static void morrisPre(Node head) {
		if(head==null) {
			return;
		}
		Node cur1=head;
		Node cur2=null;
		while(cur1!=null) {
			cur2=cur1.left;
			if(cur2!=null) {
				while(cur2.right!=null&&cur2.right!=cur1) {
				cur2=cur2.right;			
			}
				if(cur2.right==null) {
					cur2.right=cur1;
					System.out.print(cur1.value+" ");
					cur1=cur1.left;
					continue;
				}else {
					cur2.right=null;
				}
			}else {
				System.out.print(cur1.value+" ");
			}	
			
			cur1=cur1.right;
		}
		
	}

	public static void main(String[] args) {
		Node n1=new Node(1);Node n2=new Node(2);Node n3=new Node(3);Node n4=new Node(4);
		Node n5=new Node(5);Node n6=new Node(6);Node n7=new Node(7);Node n8=new Node(8);
		Node n9=new Node(9);Node n10=new Node(10);Node n11=new Node(11);Node n12=new Node(12);
		Node n13=new Node(13);Node n14=new Node(14);Node n15=new Node(15);Node n16=new Node(16);
		n1.left=n2;n1.right=n3;
		n2.right=n4;n3.left=n5;n3.right=n6;
		n4.left=n7;n4.right=n8;
		n5.left=n9;n5.right=n10;
		n8.right=n11;n9.left=n12;
		n11.left=n13;n11.right=n14;
		n12.left=n15;n12.right=n16;
		printEdge1(n1);
		
		System.out.println();
		System.out.println(serialByPre(n1));
		System.out.println(serialByPre(reconByPreString(serialByPre(n1))));
		System.out.println(serialByLevel(n1));
		System.out.println(serialByLevel(reconByLevelString(serialByLevel(n1))));
		
		morrisIn(n9);
		morrisPre(n9);
		
	}
}
