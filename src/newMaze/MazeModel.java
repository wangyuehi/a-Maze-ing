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

	private MazePoint nowPoint;// 迷宫的当前坐标
	private MazePoint previous;//当前坐标的上一个坐标
	private Stack pathStack;// 存储迷宫坐标的堆栈
	private MazePoint[] offset;// 偏移量数组
	private ArrayList<MazePoint> maze;// 存储迷宫地图的数组

	private int myDirection = 0;// 方向的选择
	boolean isDelete = false;// 是否删除

	public static final int LAST_OPTION = 3;
	public static int count=0;//当前走过的步数
	/** 初始化迷宫模型 */
	public MazeModel(int width, int height) {
		this.width = width;
		this.height = height;

		maze = new ArrayList<MazePoint>();
		nowPoint = new MazePoint(0, 0);
		pathStack = new Stack(width * height);

		offset = new MazePoint[4];// 设置控制方向的偏移量
		offset[0] = new MazePoint(1, 0);// 向右
		offset[1] = new MazePoint(0, 1);// 向下
		offset[2] = new MazePoint(-1, 0);// 向左
		offset[3] = new MazePoint(0, -1);// 向上

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

	public ArrayList<MazePoint> initializeMaze() {//初始化一堆迷宫的点，加入list
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				MazePoint point = new MazePoint(w, h);
				maze.add(point);
			}
		}
		maze.get(0).setState(1);
		return maze;
	}

	public ArrayList<MazePoint> resetMaze() {// 返回迷宫点类

		for (int i = 0; i < maze.size(); i++) {//重新设置迷宫状态
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

	/** 随机生成迷宫的算法，基于深度搜索 */
	private ArrayList<MazePoint> setRandomMaze() {
		int top = 0;
		int x = 0;
		int y = 0;
		ArrayList<MazePoint> pathStack = new ArrayList<MazePoint>();
		pathStack.add(maze.get(x + y * width));
		while (top >= 0) {
			int[] nowPosDir = new int[] { -1, -1, -1, -1 };
			int times = 0;
			boolean nowMovable = false;// 当前坐标能否前进至下一相邻坐标
			MazePoint topPoint = pathStack.get(top);// 取出坐标数组中的第top个元素，top从0开始
			x = topPoint.getX();
			y = topPoint.getY();
			topPoint.visted = true;// 标记该坐标为：已访问

			loop: while (times < 4) {
				int dir = myRandom.nextInt(4);//-------------------java的、ramdom类，nextInt（n）生成从0到n的随机数
				if (nowPosDir[dir] == dir)
					continue;
				else
					nowPosDir[dir] = dir;//-------------------------给当前的点选择一个方向

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
			if (!nowMovable) {// 如果四个方向都不可以移动，则从pointArray中取出这个坐标位置
				pathStack.remove(top);
				top--;
			}

		}
		nowPoint = maze.get(0);
		nowPoint.setState(1);

		return maze;
	}

	public MazePoint StepSolve(JTextArea ta,JTextArea stackState,JTextArea explainStack) {// 单步执行
		if (!nowPoint.equal(width - 1, height - 1)) {
			int r = 0;
			int c = 0;
			while (myDirection <= LAST_OPTION) {
				if (nowPoint.getX() == 0 && myDirection == 2
						|| nowPoint.getX() == width - 1 && myDirection == 0
						|| nowPoint.getY() == 0 && myDirection == 3
						|| nowPoint.getY() == height - 1 && myDirection == 1) {
					// 判断是否处于边界
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
			if (myDirection <= LAST_OPTION) {// 移动到maze(r+c*width)
				nowPoint.setState(1);//------------------标志为走过的路径
				previous = nowPoint;
				pathStack.Add(nowPoint);//-----------入栈
				nowPoint = maze.get(r + c * width);
				myDirection = 0;
				isDelete = false;
				
				String newText= "        第"+(++count)+"步：("+previous.getX()+","+previous.getY()+")"+"---->("+maze.get(r+c*width).getX()+","+maze.get(r+c*width).getY()+"),\n";
				String str=ta.getText();
				ta.setText(newText+str);
				
				String newText1= "("+previous.getX()+","+previous.getY()+")"+"入栈，→\n";
				String str1=explainStack.getText();
				explainStack.setText(newText1+str1);
				
				pathStack.printStack(stackState);
				
			
			} else {
				MazePoint next = new MazePoint(0, 0);
				next = pathStack.Delete();
				
				
				isDelete = true;
				next.setState(4);//设置为回溯路径
				if (next.getY() == nowPoint.getY()){
					myDirection = 2 + next.getX() - nowPoint.getX();//方向为左或者右，若当前点比下一个点位置靠右，就是往左，否则往右
		         
					String newText="      第"+(++count)+"步："+"("+nowPoint.getX()+","+nowPoint.getY()+")"+ "---->("+next.getX()+","+next.getY()+").\n";
					String str=ta.getText();
					ta.setText(newText+str);
					
					String newText1= "("+nowPoint.getX()+","+nowPoint.getY()+")"+"弹栈,→\n";
					String str1=explainStack.getText();
					explainStack.setText(newText1+str1);
					
					pathStack.printStack(stackState);
					}		
				else
				{myDirection = 3 + next.getY() - nowPoint.getY();//方向为左或者右，若当前点比下一个点位置靠右，就是往左，否则往右
					
					String newText="      第"+(++count)+"步："+"("+nowPoint.getX()+","+nowPoint.getY()+")"+ "---->("+next.getX()+","+next.getY()+").\n";
					String str=ta.getText();
					ta.setText(newText+str);
					
					String newText1= "("+nowPoint.getX()+","+nowPoint.getY()+")"+"弹栈，→\n";
					String str1=explainStack.getText();
					explainStack.setText(newText1+str1);
					
				}
				nowPoint = next;
			}

		}

		return nowPoint;
	}


	public boolean getIsDelete() {// 返回
		return isDelete;

	}

	public MazePoint StepSolveUser() {
		if (!nowPoint.equal(width - 1, height - 1)) {
			int r = 0;
			int c = 0;
			while (myDirection <= LAST_OPTION) {

				if (nowPoint.getX() == 0
						&& myDirection == 2// 处于边界时走法的限定
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
			if (myDirection <= LAST_OPTION) {// 移动到maze(r+c*width)
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
//	public void SolveAll() {//-----------------------------------------------------重复使用单步，得到整体演示
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
