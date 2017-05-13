package newMaze;

import java.awt.Color;

import javax.swing.JTextArea;

/**********************
 * 
 * @author wangyue
 *
 **********************/


/** �����洢������Ϣ��Stack��ջ�� */
public class Stack {

	private int top; // ���ջ��Ԫ�ص�����ֵ����ջΪ��ʱֵΪ-1����������
	private int maxTop; // ջ��Ԫ�ص��������ֵ��ջ��Ԫ��������ΧΪ��0~MaxTop������ΪMaxStackSize
	private MazePoint[] stack; // �����洢ջ��Ԫ�ص�����

	/** ���췽�������Σ�MaxStackSize=���Ԫ�ظ��� */
	public Stack(int MaxStackSize) {
		maxTop = MaxStackSize - 1;
		stack = new MazePoint[MaxStackSize];
		top = -1;
	}

	/** ���ظö�ջ�Ƿ�Ϊ�� */
	public boolean IsEmpty() {
		return top == -1;
	}

	/** ���ظö�ջ�Ƿ����� */
	public boolean IsFull() {
		return top == maxTop;
	}

	/** ����ջ��Ԫ�� */
	public MazePoint Top() {
		return stack[top];
	}

	/** ����ջ�е������������ */
	public void setEmpty() {
		while (top >= 0) {
			stack[top].setState(0);
			top--;
		}
	}

	/** ����ջ��Ԫ����Ŀ */
	public int length() {
		return top + 1;// top��ʼֵΪ-1
	}

	/** ���һ������Ԫ�ص�ջ�� */
	public void Add(MazePoint a) {
		stack[++top] = a;
	}

	/** ɾ��ջ��������Ԫ�أ�����ջ��Ԫ�� */
	public MazePoint Delete() {
		if (!IsEmpty()) {
			return stack[top--];
		} else {
			return null;
		}
	}

	/** ����������ջ���� */
	public MazePoint[] getStack() {
		return stack;
	}

	/** ����ջ��Ԫ�ص����� */
	public int getTop() {
		return top;
	}
	
	public void printStack(JTextArea stackState){
		//print the Stack elements at the present
		//in a reverse order
		stackState.setText("*top*\n");
		
		
	for(int i=top;i>=0;i--)
	{
		
	stackState.setText(stackState.getText()+"("+stack[i].getX()+","+stack[i].getY()+")\n");
		
			
	}
		
		
	}

}
