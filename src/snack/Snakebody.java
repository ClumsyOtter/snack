package snack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
public class Snakebody {

	private Node head = null;
	private Node tail = null;
	private int size = 0;
	Node n = new Node(10, 10, Dir.R);

	public Snakebody(Node node) {
		head = node;
		tail = node;
		size = 1;
	}

	public Snakebody() {
		head = n;
		tail = n;
		size = 1;
	}
	/*
	 * ��β��һ��
	 */
	public void addtail() {
		Node node = null;
		switch (tail.dir) {
		case L:
			node = new Node(tail.row + 1, tail.cols , tail.dir);
			break;
		case R:
			node = new Node( tail.row - 1, tail.cols , tail.dir);
			break;
		case U:
			node = new Node( tail.row, tail.cols  + 1, tail.dir);
			break;
		case D:
			node = new Node( tail.row, tail.cols - 1, tail.dir);
			break;
		}
		tail.next = node;
		node.prev = tail;
		tail = node;
		size++;
	}
	/*
	 * ����ͷ��һ��
	 */
	public void addhead(){
		Node node = null;
		switch (head.dir) {
		case L:
			node = new Node( head.row - 1,  head.cols , head.dir);
			break;
		case R:
			node = new Node( head.row + 1, head.cols   , head.dir);
			break;
		case U:
			node = new Node( head.row ,  head.cols - 1, head.dir);
			break;
		case D:
			node = new Node(head.row , head.cols + 1, head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	/*
	 * �߽ڶ���
	 */
	private class Node {

		int w = Snakeyard.BLOCK_SIZE;
		int h = Snakeyard.BLOCK_SIZE;
		int cols, row;
		Dir dir = Dir.R;
		Node next = null;
		Node prev = null;

		Node( int row,int cols,Dir dir) {
			this.cols = cols;
			this.row = row;
			this.dir = dir;
		}
		/*
		 * ���߽�
		 */
		public void drawnode(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.MAGENTA);
			g.fillRect(row * Snakeyard.BLOCK_SIZE,  cols* Snakeyard.BLOCK_SIZE, w, h);
			g.setColor(c);
		}
	};

	/*
	 * ������
	 */
	public void drawbody(Graphics g) {
		if( size <= 0)
			return;
		snakerun();
		for (Node n = head; n != null; n = n.next)
			n.drawnode(g);
	}
	/*
	 * ��ȡ�����¼�
	 */
	public void keyevent( KeyEvent e){
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT :
			if(head.dir != Dir.R)
				head.dir = Dir.L;
			break;
		case KeyEvent.VK_UP :
			if(head.dir != Dir.D)
				head.dir = Dir.U;
			break;
		case KeyEvent.VK_RIGHT :
			if(head.dir != Dir.L)
				head.dir = Dir.R;
			break;
		case KeyEvent.VK_DOWN :
			if(head.dir != Dir.U)
				head.dir = Dir.D;
			break;
		}
	}
	/*
	 * �߶�
	 * ��ͷ��һ����βɾ��һ��
	 */
	public void snakerun(){
		this.addhead();
		this.deletetail();
	}
	
	/*
	 * ɾ����β
	 * ����βnode�������ǰһ�ζ���
	 */
	private void deletetail() {
		if(size == 0){
			return;
		}
		tail = tail.prev;
		tail.next = null;
	}
	/*
	 * �Ե�
	 */
	public void eat( Snackfood sf){
		 if( this.getrec().intersects(sf.getrec()) ){
			 sf.regetloc();
			 this.addhead();
		 }		
	}

	/*
	 * �@ȡ����λ��
	 */
	
	public Rectangle getrec(){
		return new Rectangle(head.row*Snakeyard.BLOCK_SIZE, head.cols*Snakeyard.BLOCK_SIZE, Snakeyard.BLOCK_SIZE, Snakeyard.BLOCK_SIZE);
	}

}