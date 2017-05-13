package newMaze;

import javax.swing.*;

import java.awt.*;


/**********************
 * 
 * @author wangyue
 *
 **********************/

/** 迷宫中的MazePoint坐标类 */
public class MazePoint {

	
	public static final int RIGHT = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int UP = 3;

	//---------------标志当前点是否联通，联通为1
	/** 上下左右的连通状态 */
	private int left = 0;
	private int right = 0;
	private int up = 0;
	private int down = 0;

	private int row; // 列数
	private int col; // 行数
	public boolean visted;
	private int state = 0; // 状态0为初始化状态，未到达。状态1为已到达。状态2为返回路径。

	/** 构造方法 */
	public MazePoint(int x, int y) {//-------------当前点坐标
		this.row = x;
		this.col = y;
	}

	/** 判断两个坐标是否相同 */
	public boolean equal(int x, int y) {
		if (this.row == x && this.col == y)
			return true;
		return false;
	}

	/** 确定方向，0为不通，1为连通 */
	public int redirect(TextArea ta) {
		if (right == 1)
			{
			ta.setText(ta.getText()+ "当前点：（"+this.getX()+","+this.getY()+"),右侧联通。\n");
			
			//System.out.println("当前点：（"+this.getX()+","+this.getY()+"),右侧联通。");
			return RIGHT;}
		else if (down == 1)
			{//System.out.println("当前点：（"+this.getX()+","+this.getY()+"),下侧联通。");
			ta.setText(ta.getText()+ "当前点：（"+this.getX()+","+this.getY()+")，下侧联通。\n");
			return DOWN;}
		else if (left == 1)
			{//System.out.println("当前点：（"+this.getX()+","+this.getY()+"),左侧联通。");
			ta.setText(ta.getText()+ "当前点：（"+this.getX()+","+this.getY()+"),左侧联通。\n");
			return LEFT;}
		else
			{//System.out.println("当前点：（"+this.getX()+","+this.getY()+"),上侧联通。");
			ta.setText(ta.getText()+ "当前点：（"+this.getX()+","+this.getY()+"),上侧联通。\n");
			return UP;}
	}

	/** 判断能否沿着direction方向走到坐标mPoint */
	public boolean Allowed(MazePoint mPoint, int direction,JTextArea ta) {
		switch (direction) {
		case RIGHT:
			if (right == 1 && mPoint.getLeft() == 1)
				{
				ta.setText( "当前点：（"+this.getX()+","+this.getY()+"),可以向右移动。\n"+ta.getText());
				System.out.println("当前点：（"+this.getX()+","+this.getY()+"),可以向右移动。\n");
				return true;}
			break;
		case DOWN:
			if (down == 1 && mPoint.getUp() == 1)
				{
				ta.setText( "当前点：（"+this.getX()+","+this.getY()+"),可以向下移动。\n"+ta.getText());
				//System.out.println("当前点：（"+this.getX()+","+this.getY()+"),可以向下移动。");
				return true;}
			break;
		case LEFT:
			if (left == 1 && mPoint.getRight() == 1)

				{
				ta.setText("当前点：（"+this.getX()+","+this.getY()+"),可以向左移动。\n"+ta.getText());
			//	System.out.println("当前点：（"+this.getX()+","+this.getY()+"),可以向左移动。");
				return true;
				}
			break;
		case UP:
			if (up == 1 && mPoint.getDown() == 1)
			{
				ta.setText("当前点：（"+this.getX()+","+this.getY()+"),可以向上移动。\n"+ta.getText());
				System.out.println("当前点：（"+this.getX()+","+this.getY()+"),可以向shang移动。");
				return true;}
			break;
		}
		return false;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getX() {
		return row;
	}

	public void setX(int x) {
		this.row = x;
	}

	public int getY() {
		return col;
	}

	public void setY(int y) {
		this.col = y;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
