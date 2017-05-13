package newMaze;

import java.awt.Color;

import javax.swing.JTextArea;

/**********************
 * 
 * @author wangyue
 *
 **********************/


/** 用来存储坐标信息的Stack堆栈类 */
public class Stack {

	private int top; // 标记栈顶元素的索引值，堆栈为空时值为-1，依次类推
	private int maxTop; // 栈顶元素的索引最大值，栈内元素索引范围为：0~MaxTop，数量为MaxStackSize
	private MazePoint[] stack; // 用来存储栈中元素的数组

	/** 构造方法，传参：MaxStackSize=最大元素个数 */
	public Stack(int MaxStackSize) {
		maxTop = MaxStackSize - 1;
		stack = new MazePoint[MaxStackSize];
		top = -1;
	}

	/** 返回该堆栈是否为空 */
	public boolean IsEmpty() {
		return top == -1;
	}

	/** 返回该堆栈是否已满 */
	public boolean IsFull() {
		return top == maxTop;
	}

	/** 返回栈顶元素 */
	public MazePoint Top() {
		return stack[top];
	}

	/** 将堆栈中的所有坐标清空 */
	public void setEmpty() {
		while (top >= 0) {
			stack[top].setState(0);
			top--;
		}
	}

	/** 返回栈内元素数目 */
	public int length() {
		return top + 1;// top初始值为-1
	}

	/** 添加一个坐标元素到栈内 */
	public void Add(MazePoint a) {
		stack[++top] = a;
	}

	/** 删除栈顶的坐标元素，返回栈顶元素 */
	public MazePoint Delete() {
		if (!IsEmpty()) {
			return stack[top--];
		} else {
			return null;
		}
	}

	/** 返回整个堆栈数组 */
	public MazePoint[] getStack() {
		return stack;
	}

	/** 返回栈顶元素的坐标 */
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
