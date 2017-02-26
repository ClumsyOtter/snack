package snack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Snackfood {

	private int col = 0;
	private int row = 0;

	private static Random r = new Random();

	public Snackfood(int row, int col) {
		this.col = col;
		this.row = row;
	}
	/*
	 * 产生随机初始食物
	 */
	public Snackfood() {
		this(r.nextInt(Snakeyard.ROWS), r.nextInt(Snakeyard.COLS));
	}
	/*
	 * 获取
	 */
	public Rectangle getrec(){
		return new Rectangle( row*Snakeyard.BLOCK_SIZE, col*Snakeyard.BLOCK_SIZE, Snakeyard.BLOCK_SIZE, Snakeyard.BLOCK_SIZE);
	}		
	/*
	 * 画食物
	 */
	public void drawegg(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.green);
		g.fillRect(row * Snakeyard.BLOCK_SIZE, col * Snakeyard.BLOCK_SIZE, Snakeyard.BLOCK_SIZE, Snakeyard.BLOCK_SIZE);
		g.setColor(c);
	}
	public void regetloc() {	
		this.col = r.nextInt(col);
		this.row = r.nextInt(row);
		
	}
}
