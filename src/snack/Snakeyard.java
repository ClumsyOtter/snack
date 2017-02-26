package snack;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Snakeyard extends Frame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6293997875810433225L;

	Image im = null;
	
	snakedelay sd = new snakedelay();
	public static final int ROWS = 40;
	public static final int COLS = 40;
	static final int BLOCK_SIZE = 15;
	
	Snakebody sb = new Snakebody();
	Snackfood sf = new Snackfood();

	/**
	 * @param创建贪吃蛇框架
	 * @param args
	 */
	public void snakeframe(){
		this.setLocation(400, 200);
		this.setSize(ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setVisible(true);
		this.addKeyListener(new getkey());
		new Thread(sd).start();
	}
	/*
	 * 
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		if(im == null){
			im = this.createImage(ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		}
		Graphics gr = im.getGraphics();
		paint(gr);
		g.drawImage(im,0,0,null);
	}
	/*
	 * 画表格
	 */	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.gray);
		g.fillRect(0, 0, ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		g.setColor(Color.orange);
		for(int i = 1; i < COLS; i++)
			g.drawLine(0,BLOCK_SIZE*i,ROWS*BLOCK_SIZE,BLOCK_SIZE*i);
		for(int i = 1; i < ROWS; i++)
			g.drawLine(BLOCK_SIZE*i,0,BLOCK_SIZE*i,ROWS*BLOCK_SIZE);
		sb.eat(sf);
		sb.drawbody(g);
		sf.drawegg(g);
		g.setColor(c);
	}
	public static void main(String[] args){
		new Snakeyard().snakeframe();	
	}
	/*
	 * 创建线程来延时
	 */
	public class snakedelay implements Runnable {

		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	/*
	 * 获取键盘
	 */
	private class getkey extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			sb.keyevent(e);
		}
	}
}
