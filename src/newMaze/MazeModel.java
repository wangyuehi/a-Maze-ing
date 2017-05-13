package newMaze;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.JTextField;


/**********************
 * 
 * @author wangyue
 *
 **********************/

public class MazeModel {

	int width = 0;
	int height = 0;

	private Random myRandom = new Random();

	private MazePoint nowPoint;// �Թ��ĵ�ǰ����
	private MazePoint previous;//��ǰ�������һ������
	private Stack pathStack;// �洢�Թ�����Ķ�ջ
	private MazePoint[] offset;// ƫ��������
	private ArrayList<MazePoint> maze;// �洢�Թ���ͼ������

	private int myDirection = 0;// �����ѡ��
	boolean isDelete = false;// �Ƿ�ɾ��

	public static final int LAST_OPTION = 3;
	public static int count=0;//��ǰ�߹��Ĳ���
	/** ��ʼ���Թ�ģ�� */
	public MazeModel(int width, int height) {
		this.width = width;
		this.height = height;

		maze = new ArrayList<MazePoint>();
		nowPoint = new MazePoint(0, 0);
		pathStack = new Stack(width * height);

		offset = new MazePoint[4];// ���ÿ��Ʒ����ƫ����
		offset[0] = new MazePoint(1, 0);// ����
		offset[1] = new MazePoint(0, 1);// ����
		offset[2] = new MazePoint(-1, 0);// ����
		offset[3] = new MazePoint(0, -1);// ����

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Stack getStack() {
		return pathStack;
	}

	public ArrayList<MazePoint> initializeMaze() {//��ʼ��һ���Թ��ĵ㣬����list
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				MazePoint point = new MazePoint(w, h);
				maze.add(point);
			}
		}
		maze.get(0).setState(1);
		return maze;
	}

	public ArrayList<MazePoint> resetMaze() {// �����Թ�����

		for (int i = 0; i < maze.size(); i++) {//���������Թ�״̬
			maze.get(i).setState(0);
		}
		maze.get(0).setState(1);
		pathStack = new Stack(width * height);
		return maze;
	}

	public ArrayList<MazePoint> getRandomMaze() {
		//System.out.println("getMaze");
		count=0;
		
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				MazePoint point = new MazePoint(w, h);
				maze.add(point);
			}
		}
		return setRandomMaze();
	}

	/** ��������Թ����㷨������������� */
	private ArrayList<MazePoint> setRandomMaze() {
		int top = 0;
		int x = 0;
		int y = 0;
		ArrayList<MazePoint> pathStack = new ArrayList<MazePoint>();
		pathStack.add(maze.get(x + y * width));
		while (top >= 0) {
			int[] nowPosDir = new int[] { -1, -1, -1, -1 };
			int times = 0;
			boolean nowMovable = false;// ��ǰ�����ܷ�ǰ������һ��������
			MazePoint topPoint = pathStack.get(top);// ȡ�����������еĵ�top��Ԫ�أ�top��0��ʼ
			x = topPoint.getX();
			y = topPoint.getY();
			topPoint.visted = true;// ��Ǹ�����Ϊ���ѷ���

			loop: while (times < 4) {
				int dir = myRandom.nextInt(4);//-------------------java�ġ�ramdom�࣬nextInt��n�����ɴ�0��n�������
				if (nowPosDir[dir] == dir)
					continue;
				else
					nowPosDir[dir] = dir;//-------------------------����ǰ�ĵ�ѡ��һ������

				switch (dir) {
				case 0:
					
					if ((x - 1) >= 0
							&& maze.get(x - 1 + y * width).visted == false) {
						maze.get(x + y * width).setLeft(1);
						maze.get(x - 1 + y * width).setRight(1);
						pathStack.add(maze.get(x - 1 + y * width));
						top++;
						nowMovable = true;
						break loop;
					}
					break;
				case 1:
					
					if ((x + 1) < width
							&& maze.get(x + 1 + y * width).visted == false) {

						maze.get(x + y * width).setRight(1);
						maze.get(x + 1 + y * width).setLeft(1);
						pathStack.add(maze.get(x + 1 + y * width));
						top++;
						nowMovable = true;
						break loop;
					}
					break;
				case 2:
					
					if ((y - 1) >= 0
							&& maze.get(x + (y - 1) * width).visted == false) {
						maze.get(x + y * width).setUp(1);
						maze.get(x + (y - 1) * width).setDown(1);
						pathStack.add(maze.get(x + (y - 1) * width));
						top++;
						nowMovable = true;
						break loop;
					}
					break;
				case 3:
				
					if ((y + 1) < height
							&& maze.get(x + (y + 1) * width).visted == false) {
						maze.get(x + y * width).setDown(1);
						maze.get(x + (y + 1) * width).setUp(1);
						pathStack.add(maze.get(x + (y + 1) * width));
						top++;
						nowMovable = true;
						break loop;
					}
					break;
				}
				times += 1;
			}
			if (!nowMovable) {// ����ĸ����򶼲������ƶ������pointArray��ȡ���������λ��
				pathStack.remove(top);
				top--;
			}

		}
		nowPoint = maze.get(0);
		nowPoint.setState(1);

		return maze;
	}

	public MazePoint StepSolve(JTextArea ta,JTextArea stackState,JTextArea explainStack) {// ����ִ��
		if (!nowPoint.equal(width - 1, height - 1)) {
			int r = 0;
			int c = 0;
			while (myDirection <= LAST_OPTION) {
				if (nowPoint.getX() == 0 && myDirection == 2
						|| nowPoint.getX() == width - 1 && myDirection == 0
						|| nowPoint.getY() == 0 && myDirection == 3
						|| nowPoint.getY() == height - 1 && myDirection == 1) {
					// �ж��Ƿ��ڱ߽�
					myDirection++;
				} else {
					r = nowPoint.getX() + offset[myDirection].getX();
					c = nowPoint.getY() + offset[myDirection].getY();
					if (r + c * width >= 0) {
						if (maze.get(r + c * width).getState() == 0
								&& nowPoint.Allowed(maze.get(r + c * width),
										myDirection,ta)) {

							break;
						}
					}
					myDirection++;
				}
			}
			if (myDirection <= LAST_OPTION) {// �ƶ���maze(r+c*width)
				nowPoint.setState(1);//------------------��־Ϊ�߹���·��
				previous = nowPoint;
				pathStack.Add(nowPoint);//-----------��ջ
				nowPoint = maze.get(r + c * width);
				myDirection = 0;
				isDelete = false;
				
				String newText= "        ��"+(++count)+"����("+previous.getX()+","+previous.getY()+")"+"---->("+maze.get(r+c*width).getX()+","+maze.get(r+c*width).getY()+"),\n";
				String str=ta.getText();
				ta.setText(newText+str);
				
				String newText1= "("+previous.getX()+","+previous.getY()+")"+"��ջ����\n";
				String str1=explainStack.getText();
				explainStack.setText(newText1+str1);
				
				pathStack.printStack(stackState);
				
			
			} else {
				MazePoint next = new MazePoint(0, 0);
				next = pathStack.Delete();
				
				
				isDelete = true;
				next.setState(4);//����Ϊ����·��
				if (next.getY() == nowPoint.getY()){
					myDirection = 2 + next.getX() - nowPoint.getX();//����Ϊ������ң�����ǰ�����һ����λ�ÿ��ң��������󣬷�������
		         
					String newText="      ��"+(++count)+"����"+"("+nowPoint.getX()+","+nowPoint.getY()+")"+ "---->("+next.getX()+","+next.getY()+").\n";
					String str=ta.getText();
					ta.setText(newText+str);
					
					String newText1= "("+nowPoint.getX()+","+nowPoint.getY()+")"+"��ջ,��\n";
					String str1=explainStack.getText();
					explainStack.setText(newText1+str1);
					
					pathStack.printStack(stackState);
					}		
				else
				{myDirection = 3 + next.getY() - nowPoint.getY();//����Ϊ������ң�����ǰ�����һ����λ�ÿ��ң��������󣬷�������
					
					String newText="      ��"+(++count)+"����"+"("+nowPoint.getX()+","+nowPoint.getY()+")"+ "---->("+next.getX()+","+next.getY()+").\n";
					String str=ta.getText();
					ta.setText(newText+str);
					
					String newText1= "("+nowPoint.getX()+","+nowPoint.getY()+")"+"��ջ����\n";
					String str1=explainStack.getText();
					explainStack.setText(newText1+str1);
					
				}
				nowPoint = next;
			}

		}

		return nowPoint;
	}


	public boolean getIsDelete() {// ����
		return isDelete;

	}

	public MazePoint StepSolveUser() {
		if (!nowPoint.equal(width - 1, height - 1)) {
			int r = 0;
			int c = 0;
			while (myDirection <= LAST_OPTION) {

				if (nowPoint.getX() == 0
						&& myDirection == 2// ���ڱ߽�ʱ�߷����޶�
						|| nowPoint.getX() == width - 1 && myDirection == 0
						|| nowPoint.getY() == 0 && myDirection == 3
						|| nowPoint.getY() == height - 1 && myDirection == 1) {
					myDirection++;
				} else {
					r = nowPoint.getX() + offset[myDirection].getX();
					c = nowPoint.getY() + offset[myDirection].getY();
					if (r + c * width >= 0) {
						if (maze.get(r + c * width).getState() == 0) {
							break;
						}
					}
					myDirection++;
				}
			}
			if (myDirection <= LAST_OPTION) {// �ƶ���maze(r+c*width)
				isDelete = false;
				pathStack.Add(nowPoint);
				nowPoint = maze.get(r + c * width);
				nowPoint.setState(1);
				myDirection = 0;
			} else {
				isDelete = true;
				MazePoint next = new MazePoint(0, 0);
				next = pathStack.Delete();
				if (next == null)
					return null;
				else {
					next.setState(4);
					if (next.getY() == nowPoint.getY())
						myDirection = 2 + next.getX() - nowPoint.getX();
					else
						myDirection = 3 + next.getY() - nowPoint.getY();
					nowPoint = next;

				}
			}

		}

		return nowPoint;
	}
//wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
//	public void SolveAll() {//-----------------------------------------------------�ظ�ʹ�õ������õ�������ʾ
//		while (!nowPoint.equal(width - 1, height - 1)) {
//			StepSolve();
//		}
//	}



	public void setNowPoint(MazePoint x) {
		myDirection = 0;
		pathStack.setEmpty();
		nowPoint = x;
	}

	public MazePoint getNowPoint() {
		return nowPoint;
	}

	public int getDirection() {
		return myDirection;
	}

}
