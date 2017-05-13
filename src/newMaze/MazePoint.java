package newMaze;

import javax.swing.*;

import java.awt.*;


/**********************
 * 
 * @author wangyue
 *
 **********************/

/** �Թ��е�MazePoint������ */
public class MazePoint {

	
	public static final int RIGHT = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int UP = 3;

	//---------------��־��ǰ���Ƿ���ͨ����ͨΪ1
	/** �������ҵ���ͨ״̬ */
	private int left = 0;
	private int right = 0;
	private int up = 0;
	private int down = 0;

	private int row; // ����
	private int col; // ����
	public boolean visted;
	private int state = 0; // ״̬0Ϊ��ʼ��״̬��δ���״̬1Ϊ�ѵ��״̬2Ϊ����·����

	/** ���췽�� */
	public MazePoint(int x, int y) {//-------------��ǰ������
		this.row = x;
		this.col = y;
	}

	/** �ж����������Ƿ���ͬ */
	public boolean equal(int x, int y) {
		if (this.row == x && this.col == y)
			return true;
		return false;
	}

	/** ȷ������0Ϊ��ͨ��1Ϊ��ͨ */
	public int redirect(TextArea ta) {
		if (right == 1)
			{
			ta.setText(ta.getText()+ "��ǰ�㣺��"+this.getX()+","+this.getY()+"),�Ҳ���ͨ��\n");
			
			//System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),�Ҳ���ͨ��");
			return RIGHT;}
		else if (down == 1)
			{//System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),�²���ͨ��");
			ta.setText(ta.getText()+ "��ǰ�㣺��"+this.getX()+","+this.getY()+")���²���ͨ��\n");
			return DOWN;}
		else if (left == 1)
			{//System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),�����ͨ��");
			ta.setText(ta.getText()+ "��ǰ�㣺��"+this.getX()+","+this.getY()+"),�����ͨ��\n");
			return LEFT;}
		else
			{//System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),�ϲ���ͨ��");
			ta.setText(ta.getText()+ "��ǰ�㣺��"+this.getX()+","+this.getY()+"),�ϲ���ͨ��\n");
			return UP;}
	}

	/** �ж��ܷ�����direction�����ߵ�����mPoint */
	public boolean Allowed(MazePoint mPoint, int direction,JTextArea ta) {
		switch (direction) {
		case RIGHT:
			if (right == 1 && mPoint.getLeft() == 1)
				{
				ta.setText( "��ǰ�㣺��"+this.getX()+","+this.getY()+"),���������ƶ���\n"+ta.getText());
				System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),���������ƶ���\n");
				return true;}
			break;
		case DOWN:
			if (down == 1 && mPoint.getUp() == 1)
				{
				ta.setText( "��ǰ�㣺��"+this.getX()+","+this.getY()+"),���������ƶ���\n"+ta.getText());
				//System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),���������ƶ���");
				return true;}
			break;
		case LEFT:
			if (left == 1 && mPoint.getRight() == 1)

				{
				ta.setText("��ǰ�㣺��"+this.getX()+","+this.getY()+"),���������ƶ���\n"+ta.getText());
			//	System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),���������ƶ���");
				return true;
				}
			break;
		case UP:
			if (up == 1 && mPoint.getDown() == 1)
			{
				ta.setText("��ǰ�㣺��"+this.getX()+","+this.getY()+"),���������ƶ���\n"+ta.getText());
				System.out.println("��ǰ�㣺��"+this.getX()+","+this.getY()+"),������shang�ƶ���");
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
