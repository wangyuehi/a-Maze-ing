package newMaze;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

/**********************
 * 
 * @author wangyue
 *
 **********************/

public class ShowMaze2 {
	public static void main(String a[]){
		JFrame f = new JFrame(); 
		new ShowMaze2(f);
	}
	// 随机生成迷宫的类
	public JFrame showMaze;
	public MazeModel mazeModel;
	private JPanel MazeCanvas;
	private JLabel col;
	
//	public TextArea comments;
	private JTextField colNum;
	private JTextField rowNum;
	private JLabel row;
	private JButton exit;
	private JButton again;
	private JButton solveAll;
	private JButton solveOne;
	private JButton create;
	private JPanel Tools;
	public ArrayList<MazePoint> maze;
	static Thread myThread;
	private MazePoint now;
	
	private JFrame previous;
	
	ArrayList<ImageIcon> numbers;
	MazePoint[] path;
	CanvasPanel mazePanel;
	int choose = -1;
	final int RANDOM_DRAW = 0;// 初始随机迷宫
	final int STEP_DRAW = 1; // 执行一次后迷宫的图像
	final int ALL_DRAW = 2; // 执行到最后时将通路画出

	int runNumber=0;
	final int MAZE_X = 80; // 迷宫的x坐标
	final int MAZE_Y = 110; // 迷宫的y坐标

	

	public static int mazeRow, mazeCol;// 迷宫的行数和列数

	private int realx = 0, realy = 0;// 迷宫的真实大小
	private JTextArea comments;
	private JTextArea explainStack;//解释堆栈状态的文字部分
	private JLabel toolbacl;
	private JTextArea stackState;
	private int sizex = 0, sizey = 0;// 对应迷宫的方格大小
	
	boolean Created=false, ifEnd = false;
	
	final int MAZE_WIDTH = 550; // 绘制迷宫的宽度
	final int MAZE_HEIGHT =550; // 绘制迷宫的高度
	
	boolean enThread = true;// 控制线程的循环
	
	Image wallImagex, wallImagey,MouseImage, finalImage,duihaoImage,chahaoImage;
	final ImageIcon wallIconx,wallIcony;
	ImageIcon iconMouse, iconFinal,duihaoIcon,chahaoIcon;// 按钮的图片
	URL MouseURL, finalURL,duihaoURL,chahaoURL;

	public ShowMaze2(JFrame temp) {
		mazeRow = 10;
		mazeCol = 10;
		
		previous = temp;
		BorderLayout showMazeLayout = new BorderLayout();

		showMaze=new JFrame();
		showMaze.getContentPane().setLayout(null);
		{
			MazeCanvas = new JPanel();
			MazeCanvas.setLayout(null);
			mazePanel = new CanvasPanel();
			mazePanel.setBounds(500, 500, 206, 0);
			MazeCanvas.add(mazePanel);
			showMaze.getContentPane().add(MazeCanvas, "Center");
			MazeCanvas.setBackground(new java.awt.Color(210,255,222));
			MazeCanvas.setBounds(206, 0, 513, 517);

		}

		{
			Tools = new JPanel();
			showMaze.getContentPane().add(Tools, "West");
			Tools.setLayout(null);
			Tools.setBackground(new java.awt.Color(255,255,215));
			Tools.setFont(new java.awt.Font("华文彩云",0,12));
			Tools.setBounds(0, -5, 206, 527);
			{
				create = new JButton();
				Tools.add(create, "Center");
				create.setText("\u521b\u5efa\u8ff7\u5bab");
				create.setBounds(47, 215, 106, 32);
				create.setBorderPainted(false);
				create.setBackground(new java.awt.Color(128,255,128));
				create.setOpaque(false);
			}
			{
				solveOne = new JButton();
				Tools.add(solveOne, "North");
				solveOne.setText("\u5355\u6b65\u6f14\u793a");
				solveOne.setBounds(47, 264, 106, 31);
				solveOne.setBackground(new java.awt.Color(128,255,128));
				solveOne.setBorderPainted(false);
				solveOne.setOpaque(false);
			}
			{
				solveAll = new JButton();
				Tools.add(solveAll, "West");
				solveAll.setText("\u6574\u4f53\u6f14\u793a");
				solveAll.setBounds(47, 314, 106, 32);
				solveAll.setBackground(new java.awt.Color(128,255,128));
				solveAll.setBorderPainted(false);
				solveAll.setOpaque(false);
			}
			{
				again = new JButton();
				Tools.add(again, "East");
				again.setText("\u91cd\u65b0\u6f14\u793a");
				again.setBounds(47, 362, 106, 32);
				again.setBackground(new java.awt.Color(128,255,128));
				again.setBorderPainted(false);
				again.setOpaque(false);
			}
			{
				exit = new JButton();
				Tools.add(exit, "South");
				exit.setText("\u9000\u51fa\u6f14\u793a");
				exit.setBounds(47, 410, 106, 34);
				exit.setOpaque(false);
				exit.setBorderPainted(false);
				exit.setBackground(new java.awt.Color(128,255,128));
			}
			{
				row = new JLabel();
				Tools.add(row, "South");
				row.setText("\u884c\u6570\uff1a");
				row.setBounds(30, 91, 36, 17);
			}
			{
				col = new JLabel();
				Tools.add(col, "South");
				col.setText("\u5217\u6570\uff1a");
				col.setBounds(30, 135, 36, 17);
			}
			{
				rowNum = new JTextField();
				Tools.add(rowNum, "South");
				rowNum.setText("10");
				rowNum.setBounds(93, 88, 67, 24);
				rowNum.setDropMode(javax.swing.DropMode.USE_SELECTION);
			}
			{
				colNum = new JTextField();
				Tools.add(colNum, "South");
				colNum.setText("10");
				colNum.setBounds(93, 132, 67, 24);
			}
			{
				toolbacl = new JLabel();
				Tools.add(toolbacl);
				CardLayout toolbaclLayout = new CardLayout();
				toolbacl.setLayout(toolbaclLayout);
				toolbacl.setText("jLabel1");
				toolbacl.setIcon(new ImageIcon(getClass().getClassLoader().getResource("newMaze/toolpad.jpg")));
				toolbacl.setBounds(-5, -7, 211, 540);
			}
		}
		{
			stackState = new JTextArea();
			showMaze.getContentPane().add(stackState);
			stackState.setBounds(999, 0, 46, 522);
			stackState.setEditable(false);
		}
		{
			explainStack = new JTextArea();
			
			
			showMaze.getContentPane().add(explainStack);
			explainStack.setBounds(924, 17, 75, 505);
			explainStack.setBackground(new java.awt.Color(255,255,255));
		}
		{
			comments = new JTextArea();
			showMaze.getContentPane().add(comments);
			comments.setBounds(719, 0, 206, 517);
			comments.setBackground(new java.awt.Color(128,255,0));
		}
		{
			showMaze.setSize(1061, 555);
		}
		showMaze.setVisible(true);
		showMaze.setPreferredSize(new java.awt.Dimension(1061, 555));
		showMaze.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("newMaze/MouseIcon.jpg")).getImage());
		showMaze.getContentPane().setBackground(new java.awt.Color(255,255,255));

		URL wall1 = this.getClass().getResource("wallsX.jpg");
		wallIconx = new ImageIcon(wall1);
		wallImagex = wallIconx.getImage();
		URL wall2 = this.getClass().getResource("wallsy.jpg");
		wallIcony = new ImageIcon(wall2);
		wallImagey = wallIcony.getImage();
		MouseURL = this.getClass().getResource("xiaoguaiwaixingren.gif");
		iconMouse = new ImageIcon(MouseURL);
		finalURL = this.getClass().getResource("6221_11803755.GIF");
		iconFinal = new ImageIcon(finalURL);
		duihaoURL = this.getClass().getResource("duihao.jpg");
		duihaoIcon = new ImageIcon(duihaoURL);
		duihaoImage = duihaoIcon.getImage();
		chahaoURL = this.getClass().getResource("walls.jpg");
		chahaoIcon = new ImageIcon(duihaoURL);
		chahaoImage = duihaoIcon.getImage();
		
		initializeThread();

		exit.addMouseListener(new ExitMouseAdapter());
		again.addMouseListener(new AgainMouseAdapter());
		solveAll.addMouseListener(new SolveAllMouseAdapter());
		solveOne.addMouseListener(new SolveOneMouseAdapter());
		create.addMouseListener(new CreateMouseAdapter());

	}
	private void GetMaze(int x, int y) {//创建迷宫模型
		mazeModel = new MazeModel(x, y);
		maze = mazeModel.getRandomMaze();
		now = new MazePoint(0, 0);
		enThread = true;
		ifEnd = false;
	}
	private void initializeThread() {
		myThread = new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				while (enThread) {
					try {
						Thread.sleep(200);
					} catch (Exception e) {
						e.printStackTrace();
					}
					choose = 1;
					mazePanel.repaint();

					if (now.equal(realx - 1, realy - 1)) {// 现在已20*20的迷宫为例
						enThread = false;
						comments.setText(comments.getText()+"----成功到达终点----");
						runNumber = 2;
						choose = 2;
						mazePanel.repaint();
						ifEnd = true;
						myThread.suspend();
					}
				}

			}
		});
	}

	class CreateMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			comments.setText("----创建新迷宫----\n");
			explainStack.setText("");
			stackState.setText("");
			Created = true;
			mazeCol=new  Integer(colNum.getText()).intValue();
			mazeRow=new  Integer(rowNum.getText()).intValue();
			realx = mazeRow;
			realy = mazeCol;
			sizex = MAZE_WIDTH / (realx + 1);
			sizey = MAZE_HEIGHT / (realy + 1);
			GetMaze(realx, realy);

			mazePanel.setLayout(null);
			mazePanel.setBounds(0,0, MAZE_WIDTH, MAZE_HEIGHT);

			//showMaze.getContentPane().add(mazePanel, 0);
			MazeCanvas.add(mazePanel);
			choose = 0;
			mazePanel.repaint();
			
			
		}
		
	
	}
	
	
	class CanvasPanel extends JPanel {

		private static final long serialVersionUID = 6386167515595185903L;

		// 重载paint方法
		public void paint(Graphics g) { // 控制调用哪个画图方法
			/** 设置背景图片 */
			URL back = this.getClass().getResource(
					"mazepad.jpg");
			final ImageIcon backgroundIcon = new ImageIcon(back);

			g.drawImage(backgroundIcon.getImage(), 0, 0, null);

			if (choose == RANDOM_DRAW) {
				paintStart(g);// 初始随机迷宫
			} else if (choose == STEP_DRAW) {
				paintOneStep(g);// 执行一次后迷宫的图像
				paintMark(g);
			} else if (choose == ALL_DRAW) {// 执行到最后时将通路画出
				paintRoad(g);
				paintOneStep(g);
				paintMark(g);
			}paintMark(g);
		}

		int WALL_HEIGHT = 5;

		// 绘制迷宫
		public void paintMaze(Graphics g) {
			//把迷宫的基本形状，墙的位置画出来
			MazePoint temp;
			int x, y, up, down, left, right;
			for (int i = 0; i < maze.size(); i++) {
				temp = maze.get(i);
				x = temp.getX() * sizex + sizex;
				y = temp.getY() * sizey + sizey;
//-------------------当前这个点的联通状态，如果联通为1 不连通为0，
				up = temp.getUp();
				down = temp.getDown();
				left = temp.getLeft();
				right = temp.getRight();

				int sizeMin = Math
						.min(sizey / WALL_HEIGHT, sizex / WALL_HEIGHT);
//------------------不连通的地方画上墙
				if (up == 0)
					g.drawImage(wallImagex, x - sizex, y - sizey, sizex
							+ sizeMin+2, sizeMin+2, null);
				if (down == 0)
					g.drawImage(wallImagex, x - sizex, y, sizex + sizeMin+2,
							sizeMin+2, null);
				if (left == 0)
					g.drawImage(wallImagey, x - sizex, y - sizey, sizeMin+2, sizey
							+ sizeMin+2, null);
				if (right == 0)
					g.drawImage(wallImagey, x, y - sizey, sizeMin+2, sizey
							+ sizeMin+2, null);
			}
		}

		// 开始绘制
		public void paintStart(Graphics g) {
			paintMaze(g);

			MouseImage = iconMouse.getImage();
			finalImage = iconFinal.getImage();

			g.drawImage(MouseImage, sizex / 6, sizey / 6, sizex - sizex / 6,
					sizey - sizey / 6, null);

			g.drawImage(finalImage, maze.get(maze.size() - 1).getX() * sizex
					+ sizex / 6, maze.get(maze.size() - 1).getY() * sizey
					+ sizey / 6, sizex - sizex / 6, sizey - sizey / 6, null);
		}

		// 往后面走一步
		public void paintOneStep(Graphics g) {
			paintMaze(g);

			now = mazeModel.StepSolve(comments,stackState,explainStack);
		
			g.drawImage(MouseImage, now.getX() * sizex + sizex / 6, now.getY()
					* sizey + sizey / 6, sizex - sizex / 6, sizey - sizey / 6,
					null);

			if (now.getX() != (realx - 1) || now.getY() != (realy - 1))
				g.drawImage(finalImage, maze.get(maze.size() - 1).getX()
						* sizex + sizex / 6, maze.get(maze.size() - 1).getY()
						* sizey + sizey / 6, sizex - sizex / 6, sizey - sizey
						/ 6, null);
			paintMark(g);
		}
		public void paintMark(Graphics g){
			
			// ------------------------------------------自定义迷宫时调用的画图方法。。。。。有用吗?????
				MazePoint temp;
				int x, y;

				for (int i = 0; i < maze.size(); i++) {
					temp = maze.get(i);

					x = temp.getX() * sizex + 2*sizex/7;
					y = temp.getY() * sizey + 5*sizey/7;
				
					if (temp.getState() == 1) {
						
					
						g.setColor(Color.white);
					Font fn = new Font("TimesRoman",Font.BOLD,15);
						g.setFont(fn);
						g.drawString("("+temp.getX()+","+temp.getY()+")", x, y);
				
					}
					if (temp.getState() == 3) {
						g.setColor(Color.black);
						Font fn = new Font("TimesRoman",Font.BOLD,15);
						g.setFont(fn);
						g.drawString("("+temp.getX()+","+temp.getY()+")", x, y);
					
					}
					if (temp.getState() == 4) {
						g.setColor(Color.black);
						g.drawString("("+temp.getX()+","+temp.getY()+")", x, y);
						}
				}
				if (now.getX() != (realx - 1) || now.getY() != (realy - 1))
					g.drawImage(finalImage, maze.get(maze.size() - 1).getX() * sizex
							+ sizex / 6, maze.get(maze.size() - 1).getY() * sizey
							+ sizey / 6, sizex - sizex / 6, sizey - sizey / 6, null);
			
		}
//--------------------------------------走完迷宫之后画出通路
		public void paintRoad(Graphics g) {
			path = mazeModel.getStack().getStack();
			int count = mazeModel.getStack().getTop();
			for (int i = 0; i < count; i++) {
				g.setColor(Color.white);;
				g.drawLine(path[i].getX() * sizex + sizex / 2, path[i].getY()
						* sizey + sizey / 2, path[i + 1].getX() * sizex + sizex
						/ 2, path[i + 1].getY() * sizey + sizey / 2);
			}
			g.drawLine(path[count].getX() * sizex + sizex / 2,
					path[count].getY() * sizey + sizey / 2,
					maze.get(maze.size() - 1 - mazeModel.getWidth()).getX()
							* sizex + sizex / 2,
					maze.get(maze.size() - 1 - mazeModel.getWidth()).getY()
							* sizey + 3 * sizey / 2);
		}
	}
//*==============================================================*/
//	// 按钮的监听适配器
	class SolveOneMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (!Created) {
				JOptionPane.showMessageDialog(null, "请先生成一个迷宫");
			} else {
				if (now.getX() != (realx - 1) || now.getY() != (realy - 1)) {
					choose = 1;
					mazePanel.repaint();

				} else {
					choose = 2;
					comments.setText("----成功到达终点----"+comments.getText());
					mazePanel.repaint();
				}
			}

		}


		
	}

//	// 按钮的监听适配器
	class SolveAllMouseAdapter extends MouseAdapter {
		@SuppressWarnings("deprecation")
		public void mouseClicked(MouseEvent e) {
			if (!Created) {
				JOptionPane.showMessageDialog(null, "请先生成一个迷宫");
			} else if (!ifEnd) {
				runNumber++;
				if (runNumber == 1) {
					myThread.start();
				
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (runNumber % 2 == 1) {
					myThread.resume();
				} else {
					myThread.suspend();
				}
			}

	}}


	// 按钮的监听适配器
	class AgainMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			now = maze.get(0);
			mazeModel.setNowPoint(now);
		MazeModel.count=0;
		
			choose = 0;
			mazePanel.repaint();
			enThread = true;
			ifEnd = false;
			if (runNumber != 0)
				runNumber = 2;
		
		}

		
	}
//============================================================================
	// 按钮的监听适配器
	class ExitMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			previous.setVisible(true);
			showMaze.setVisible(false);


		}
	
}}
	
