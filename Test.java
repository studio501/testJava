
import java.util.Random;
import java.lang.reflect.Field;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
//import java.util.TableModelListener;
import javax.swing.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.*;
//import java.lang.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.util.zip.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.net.*;
import java.sql.*;

public class Test
{
	private String name;
	private int age;
	
	public Test(String name,int age)
	{
		this.name = name;
		this.age=age;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj==null) return false;
		if(this.getClass()!=obj.getClass()) return false;
		Test temp = (Test)obj;
		return this.age==temp.age && this.name.equals(temp.name);
	}
	
	@Override
	public int hashCode()
	{
		return this.name.hashCode() + 2*new Integer(this.age).hashCode();
	}
	
	public void moveDish(int lv,char from,char inter,char to)
	{
		if(lv==1) System.out.println("move "+from+" 1 "+to);
		else
		{
			moveDish(lv-1,from,to,inter);
			System.out.println("move "+from+" "+lv+" to "+to);
			moveDish(lv-1,inter,from,to);
		}
	}
	
	public static void myprint(String str)
	{
		System.out.println(str);
	}
	public static void main(String[] args)
	{
		//test1();
		//test2();
		//test3();
		//test4();
		//test5();
		//test6();
		//test7();
		//test8();
		//test9();
		//test10();
		//test11();
		//test12();
		//test13();//JFrame 相关
		//test14();
		//test15();
		//test16();
		//test17();
		//test18();
		//test19();//获取内网中所有ip地址
		//test20();//获取指定url的内容并输出到本地文件
		//test21();//获取主机ip和名字
		//test22();//单向通信程序
		//test23();//网络广播程序
		//test24();//聊天室程序
		//test25();//传递对象
		//test26();//传递图片
		//test27();//数据库相关操作
		test28();
		
	}
	public static void test28()
	{
		class drawCircle extends JFrame
		{
			private int OVAL_WIDTH=80;
			private int OVAL_HEIGHT=80;
			public drawCircle(String title)
			{
				super(title);
				initalize();
			}
			public void initalize()
			{
				setSize(300,200);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setContentPane(new DrawPanel());
				setVisible(true);
			}
			
			class DrawPanel extends JPanel
			{
				public void paint(Graphics g)
				{
					super.paint(g);
					// g.drawOval(10,10,OVAL_WIDTH,OVAL_HEIGHT);
					// g.drawOval(80,10,OVAL_WIDTH,OVAL_HEIGHT);
					// g.drawOval(150,10,OVAL_WIDTH,OVAL_HEIGHT);
					// g.drawOval(50,70,OVAL_WIDTH,OVAL_HEIGHT);
					// g.drawOval(120,70,OVAL_WIDTH,OVAL_HEIGHT);
					
					// g.drawArc(100,100,100,50,270,200);
					// g.drawLine(10,10,50,10);
					// int xs[]={100,150,50};
					// int ys[]={100,150,150};
					// g.drawPolygon(xs,ys,3);
					
					g.drawRect(10,10,100,50);
					g.drawRoundRect(10,70,100,50,10,10);
					g.fillRect(120,10,100,50);
					g.fillRoundRect(120,70,100,50,10,10);
				}
			}
		}
		
		new drawCircle("hahah");
	}
	public static void test27()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/table1";
			Connection conn = DriverManager.getConnection(url,"root","Inryygy007");
			Statement st = conn.createStatement();
			
			// String sql = "create table if not exists user_db(username text,password text,sex text,age int)";
			// st.executeUpdate(sql);
			
			// sql = "insert into user_db(username,password,sex,age) values('tw','222','male','26')";
			// st.executeUpdate(sql);
			
			// sql = "insert into user_db(username,password,sex,age) values(?,?,?,?)";
			// PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setString(1,"lisi");
			// ps.setString(2,"aaa");
			// ps.setString(3,"male");
			// ps.setInt(4,27);
			// ps.executeUpdate();
			String username,pwd,sex;
			int age;
			
			String sql = "select * from user_db";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				myprint(rs.getString("username")+" "+rs.getString("password")+" "+
				rs.getString("sex")+" "+rs.getInt("age"));
			}
			
			sql = "update user_db set age = 200 where sex = 'male' ";
			st.executeUpdate(sql);
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test26()
	{
		class myServer
		{
			private ServerSocket server;
			private int index = 0;
			public void createSocket()
			{
				try
				{
					server = new ServerSocket(2016);
					
					while(true)
					{
						++index;
						myprint("等待客户端连接");
						Socket socket = server.accept();
						new Thread(new Runnable(){
							public void run()
							{
								receiveFile(socket,index);
							}
						}).start();
						
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			public void receiveFile(Socket socket,int n)
			{
				byte[] inputByte = null;
				int length=0;
				DataInputStream dis=null;
				FileOutputStream fos = null;
				
				try
				{
					dis = new DataInputStream(socket.getInputStream());
					myprint(String.format("./cc%d.png",n));
					File file = new File(String.format("./cc%d.png",n));
					fos = new FileOutputStream(file);
					inputByte = new byte[1024];
					while((length = dis.read(inputByte,0,inputByte.length))>0)
					{
						myprint("in while");
						fos.write(inputByte,0,length);
						fos.flush();
					}
					fos.close();
					socket.close();
					dis.close();
					myprint("完成接收");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		class myClient
		{
			Socket socket;
			DataOutputStream dos=null;
			FileInputStream fis = null;
			
			public myClient(String fileName)
			{
				try
				{
					socket = new Socket("127.0.0.1",2016);
					dos = new DataOutputStream(socket.getOutputStream());
					File file = new File("./"+fileName);
					fis = new FileInputStream(file);
					byte[] sendByte = new byte[1024];
					int length = 0;
					while((length=fis.read(sendByte,0,sendByte.length))>0)
					{
						
						dos.write(sendByte,0,length);
						dos.flush();
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		new Thread(new Runnable(){
			public void run()
			{
				new myServer().createSocket();
			}
		}).start();
		
		try
		{
			Thread.sleep(10);
			new myClient("arrow.png");
			Thread.sleep(400);
			new myClient("bd_logo1.png");
			Thread.sleep(400);
			new myClient("google-search.png");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test25()
	{
		class User implements Serializable
		{
			public String name;
			public String pwd;
			
			public User(String name,String pwd)
			{
				this.name=name;
				this.pwd=pwd;
			}
		}
		
		class myServer
		{
			private ServerSocket server;
			
			public void createSocket()
			{
				try
				{
					server = new ServerSocket(2016);
					
					while(true)
					{
						
						myprint("等待客户端连接");
						Socket socket = server.accept();
						invoke(socket);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			public void invoke(Socket socket)
			{
				new Thread(new Runnable(){
					public void run()
					{
						ObjectInputStream is = null;
						ObjectOutputStream os = null;
						try
						{
							is = new ObjectInputStream(socket.getInputStream());
							os = new ObjectOutputStream(socket.getOutputStream());
							
							Object obj = is.readObject();
							User ur = (User)obj;
							myprint("user is :"+ur.name+"  "+ur.pwd);
							
							ur.name=ur.name+"_new";
							ur.pwd=ur.pwd+"_new";
							os.writeObject(ur);
							os.flush();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
		
		class myClient
		{
			Socket socket;
			ObjectInputStream is=null;
			ObjectOutputStream os = null;
			
			public myClient(int i)
			{
				try
				{
					socket = new Socket("127.0.0.1",2016);
					os = new ObjectOutputStream(socket.getOutputStream());
					User ur = new User(String.format("user%d",i),"pppp");
					os.writeObject(ur);
					
					is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
					Object obj = is.readObject();
					if(obj!=null)
					{
						ur = (User)obj;
						myprint("new use : "+ur.name+"  "+ur.pwd );
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		new Thread(new Runnable(){
			public void run()
			{
				new myServer().createSocket();
			}
		}).start();
		for (int i=0;i<100;++i)
			new myClient(i);
	}
	public static void test24()
	{
		class chatServer
		{
			private java.util.Hashtable<String,Socket> map = new java.util.Hashtable<String,Socket>();
			private ServerSocket server;
			private Socket socket;
			private java.util.List<Client> clients = new java.util.ArrayList<Client>();
			private boolean bStart = false;
			private int index = 0;
			public void createSocket()
			{
				try
				{
					server = new ServerSocket(2016);
					bStart = true;
					while(bStart)
					{
						++index;
						myprint("等待客户端连接");
						socket = server.accept();
						Client c = new Client(socket);
						myprint(socket.getInetAddress().getHostAddress()+" connected "+index+" clients");
						clients.add(c);
						new Thread(c).start();
						
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			class Client implements Runnable
			{
				DataInputStream dis = null;
				DataOutputStream dos = null;
				Socket s = null;
				boolean bStart = false;
				
				Client(Socket s)
				{
					this.s=s;
					try
					{
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					bStart = true;
				}
				
				public void sendToEveryClient(String str)
				{
					try
					{
						dos.writeUTF(str);
						dos.flush();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				public void run()
				{
					try
					{
						while(bStart && !s.isClosed())
						{
							String str = dis.readUTF();
							myprint(str);
							for(int i=0;i<clients.size();++i)
							{
								Client c = clients.get(i);
								
								c.sendToEveryClient(str);
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			
		}
		
		
		class chatClient extends JFrame
		{
			String ipStr;
			String name;
			
			TextArea taContent = new TextArea();
			JTextField tfTxt = new JTextField(20);
			
			JButton send = new JButton("发送");
			JButton connect = new JButton("连接");
			JButton clear = new JButton("清空");
			
			boolean live = false;
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			
			Socket s = null;
			DataOutputStream dos = null;
			DataInputStream dis = null;
			
			boolean bConnected = false;
			Thread t = new Thread(new RectToServer());
			
			public void launchFrame()
			{
				taContent.setEditable(false);
				p2.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
				p2.add(send);
				p2.add(connect);
				p2.add(clear);
				
				Container con = this.getContentPane();
				
				con.add(taContent,"North");
				con.add(tfTxt,"Center");
				con.add(p2,"South");
				
				this.setSize(300,500);
				this.setLocation(400,200);
				//this.setTitle("chat client");
				this.setVisible(true);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				connect.addActionListener(new Connect());
				send.addActionListener(new SendMsg());
				clear.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ee)
					{
						taContent.setText("");
					}
				});
			}
			
			public void connectToServer()
			{
				try
				{
					s = new Socket(ipStr,2016);
					dos = new DataOutputStream(s.getOutputStream());
					dis = new DataInputStream(s.getInputStream());
					
					bConnected = true;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			public void disConnect()
			{
				bConnected = false;
				try
				{
					if(s!=null) s.close();
					if(dos!=null) dos.close();
					if(dis!=null) dis.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			class SendMsg implements ActionListener
			{
				public void actionPerformed(ActionEvent ee)
				{
					if(connect.getActionCommand()=="连接")
					{
						JOptionPane.showMessageDialog(chatClient.this,"没有找到指定服务器","错误提示",1);
					}
					else
					{
						String str = tfTxt.getText();
						str = name+": "+str;
						tfTxt.setText("");
						
						try
						{
							dos.writeUTF(str);
							dos.flush();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
			
			 class Connect implements ActionListener
			{
				public void actionPerformed(ActionEvent ee)
				{
					if(ee.getActionCommand()=="连接")
					{
						connectToServer();
						try
						{
							t.start();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						connect.setText("断开连接");
					}
					else if(ee.getActionCommand()=="断开连接")
					{
						disConnect();
						connect.setText("连接");
					}
				}
			}
			
			 class RectToServer implements Runnable
			{
				public void run()
				{
					try
					{
						while(bConnected)
						{
							String str = dis.readUTF();
							taContent.append(str+"\n");
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			
			public chatClient(String ipStr,String name)
			{
				super(name);
				this.ipStr = ipStr;
				this.name = name;
				
				this.launchFrame();
			}
			
		}
		
		Thread t = new Thread(new Runnable(){
			public void run()
			{
				chatServer cs = new chatServer();
				cs.createSocket();
			}
		});
		t.start();
		
		chatClient c1 = new chatClient("192.168.7.234","tw1"); //192.168.7.234
		chatClient c2 = new chatClient("192.168.8.233","tw2");
		chatClient c3 = new chatClient("192.168.7.235","tw3");//192.168.7.235
	}
	public static void test23()
	{
		class Weather extends Thread
		{
			String weather = "节目预报: 八点中操场集合";
			int port = 2016;
			InetAddress iadress = null;
			MulticastSocket socket = null;
			
			public Weather()
			{
				try
				{
					iadress = InetAddress.getByName("224.255.10.0");
					socket = new MulticastSocket(port);
					socket.setTimeToLive(1);
					socket.joinGroup(iadress);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			public void run()
			{
				while(true)
				{
					DatagramPacket packet = null;
					byte data[] = weather.getBytes();
					packet = new DatagramPacket(data,data.length,iadress,port);
					myprint(new String(data));
					try
					{
						socket.send(packet);
						sleep(3000);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		class Receive extends JFrame implements Runnable,ActionListener
		{
			int port;
			InetAddress group = null;
			MulticastSocket socket = null;
			JButton ince = new JButton("开始接收");
			JButton stop = new JButton("停止接收");
			JTextArea inceAr = new JTextArea(10,10);
			JTextArea inced = new JTextArea(10,10);
			Thread thread;
			boolean b = false;
			public Receive()
			{
				super("广播接收器");
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				thread = new Thread(this);
				ince.addActionListener(this);
				stop.addActionListener(this);
				inceAr.setForeground(Color.blue);
				JPanel north = new JPanel();
				north.add(ince);
				north.add(stop);
				add(north,BorderLayout.NORTH);
				JPanel center = new JPanel();
				center.setLayout(new GridLayout(1,2));
				center.add(inceAr);
				center.add(inced);
				add(center,BorderLayout.CENTER);
				validate();
				port = 2016;
				try
				{
					group = InetAddress.getByName("224.255.10.0");
					socket = new MulticastSocket(port);
					socket.joinGroup(group);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				setBounds(100,100,360,380);
				setSize(460,200);
				setVisible(true);
			}
			
			public void run()
			{
				while(true)
				{
					byte data[] = new byte[1024];
					DatagramPacket packet = null;
					packet = new DatagramPacket(data,data.length,group,port);
					try
					{
						socket.receive(packet);
						String message = new String(packet.getData(),0,packet.getLength());
						inceAr.setText("正在接收的内容: \n" + message);
						inced.append(message+"\n");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					if(b) break;
				}
			}
			
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==ince)
				{
					ince.setBackground(Color.red);
					stop.setBackground(Color.yellow);
					if(!(thread.isAlive()))
						thread = new Thread(this);
					thread.start();
					b=false;
				}
				else if(e.getSource()==stop)
				{
					ince.setBackground(Color.yellow);
					stop.setBackground(Color.red);
					b=true;
				}
			}
		}
		
		Weather w = new Weather();
		w.start();
		
		Receive r = new Receive();
		
	}
	public static void test22()
	{
		class myTcp
		{
			private BufferedReader reader;
			private ServerSocket server;
			private Socket socket;
			
			public void getserver()
			{
				
				try
				{
					server = new ServerSocket(2016);
					server.setSoTimeout(10000);
					myprint("服务器socket创建成功");
					while(true)
					{
						myprint("等待客户端连接");
						socket = server.accept();
						reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						getClientMessage();
					}
				}
				catch(SocketTimeoutException e)
				{
					myprint("连接超时");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			private void getClientMessage()
			{
				try
				{
					while(true) myprint("客户机 : "+reader.readLine());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				try
				{
					if(reader!=null) reader.close();
					if(socket!=null) socket.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		class myClient extends JFrame
		{
			private PrintWriter writer;
			Socket socket;
			private JTextArea ta = new JTextArea();
			private JTextField tf = new JTextField();
			Container cc;
			public myClient(String title)
			{
				super(title);
				cc=this.getContentPane();
				cc.add(ta,"North");
				cc.add(tf,"South");
				
				tf.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String str = tf.getText();
						if (str!=null&&writer!=null)
						{
							writer.println(str);
							ta.append(str+'\n');
							tf.setText("");
						}
					}
				});
				setBounds(500,500,340,220);
				setVisible(true);
				
			}
			
			private void connect()
			{
				ta.append("尝试连接\n");
				try
				{
					socket = new Socket("127.0.0.1",2016);
					writer = new PrintWriter(socket.getOutputStream(),true);
					ta.append("完成链接\n");
					
					InetAddress netAdr = socket.getInetAddress();
					String netIp = netAdr.getHostAddress();
					int netPort = socket.getPort();
					
					myprint("远程 : "+netIp+" "+netPort);
					InetAddress localAdr = socket.getLocalAddress();
					String localIp = localAdr.getHostAddress();
					int localPort = socket.getLocalPort();
					myprint("客户 : "+localIp+" "+localPort);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		Thread tha = new Thread(new Runnable(){
			public void run()
			{
				myTcp tcp = new myTcp();
				tcp.getserver();
			}
		});
		tha.start();
		
		
		myClient clien = new myClient("向服务器发送消息");
		clien.connect();
		
		
		
	}
	public static void test21()
	{
		InetAddress ip;
		try
		{
			ip = InetAddress.getLocalHost();
			myprint(ip.getHostName()+"----->"+ip.getHostAddress());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test20()
	{
		String urlString = "http://www.baidu.com";
		URL url = null;
		URLConnection conn = null;
		java.util.Collection<String> urlCollection = new java.util.ArrayList<String>();
		try
		{
			url = new URL(urlString);
			conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			
			InputStreamReader in = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(in);
			String nextLine = br.readLine();
			
			//FileOutputStream fs = new FileOutputStream("3.txt",true);
			//DataOutputStream ds = new DataOutputStream(fs);
			//ds.writeUTF("使用 writeUTF 写入数据");
			
			FileWriter writer = new FileWriter("1.txt",true);
			
			
			while(nextLine!=null)
			{
				writer.write(nextLine);
				writer.flush();
				//fs.write(nextLine);
				//ds.writeUTF(nextLine);
				//urlCollection.add(nextLine);
				nextLine=br.readLine();
			}
			writer.close();
			
			// Iterator it = urlCollection.iterator();
			// while(it.hasNext())
			 // myprint((String)it.next());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void test19()
	{
		class Ip
		{
			HashMap<String,String> ping;
			public HashMap<String,String> getPing()
			{
				return ping;
			}
			
			//int threadCount = 0;
			public Ip()
			{
				ping = new HashMap<String,String>();
			}
			
			public void Ping(String ip) throws Exception
			{
				
				//while(threadCount>30) Thread.sleep(50);
				//++threadCount;
				PingIp p = new PingIp(ip);
				p.start();
			}
			public void PingAll()throws Exception
			{
				InetAddress host = InetAddress.getLocalHost();
				String hostAddress = host.getHostAddress();
				myprint("本机ip: "+hostAddress);
				int k=0;
				k = hostAddress.lastIndexOf(".");
				String ss = hostAddress.substring(0,k+1);
				for(int i=1;i<=255;++i)
				{
					String iip = ss+i;
					Ping(iip);
				}
				//while(threadCount>0) Thread.sleep(50);
			}
			
			class PingIp extends Thread
			{
				public String ip;
				public PingIp(String ip)
				{
					this.ip = ip;
				}
				public void run()
				{
					try
					{
						Process p = Runtime.getRuntime().exec("ping "+ip+" -w 300 -n 1");
						
						InputStreamReader ir = new InputStreamReader(p.getInputStream());
						LineNumberReader input = new LineNumberReader(ir);
						//for(int i=1;i<7;++i) input.readLine();
						String line = input.readLine();
						while(line!=null)
						{
							//myprint(line);
							if(line!=null&&!line.equals(""))
							{
								if(line.substring(0,2).equals("来自")||(line.length()>10&&line.substring(0,10).equals("Reply from")))
								{
									
									ping.put(ip,"true");
								}
							}
							line = input.readLine();
						}
						
						//--threadCount;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		try
		{
			Ip ip = new Ip();
			ip.PingAll();
			
			Thread.sleep(2000);
			for(Map.Entry<String,String> entry : ip.getPing().entrySet())
			{
				myprint(entry.getKey());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	public static void test18()
	{
		Thread thd = new Thread(new Runnable(){
			public void run()
			{
				for(int i=1;i<6;++i)
				{
					try
					{
						Thread.sleep(100);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					myprint("紧急情况 "+i+" 出发");
				}
			}
		});
		thd.start();
		
		for (int i=1;i<6;++i)
		{
			try
			{
				Thread.sleep(10);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			myprint("正常情况 "+i+" 出发");
			try
			{
				thd.join();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void test17()
	{
		class threadSafe implements Runnable
		{
			int num = 10;
			public synchronized boolean doit()
			{
				if(num>0)
				{
					try
					{
						Thread.sleep(100);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					myprint("还有车票 "+num--);
					return true;
				}
				else return false;
			}
			public void run()
			{
				while(true)
				{
					if(!doit()) break;
				}
			}
		}
		
		threadSafe t = new threadSafe();
		Thread ta = new Thread(t);
		Thread tb = new Thread(t);
		Thread tc = new Thread(t);
		Thread td = new Thread(t);
		
		ta.start();
		tb.start();
		tc.start();
		td.start();
	}
	public static void test16()
	{
		class ThreadList
		{
			private ThreadGroup getRootThreadGroups()
			{
				ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
				while(true)
				{
					if(rootGroup.getParent()!=null) rootGroup = rootGroup.getParent();
					else break;
				}
				return rootGroup;
			}
			
			public List<String> getThreads(ThreadGroup group)
			{
				List<String> threadList = new java.util.ArrayList<String>();
				Thread[] threads = new Thread[group.activeCount()];
				int count = group.enumerate(threads,false);
				for(int i=0;i<count;++i) threadList.add(group.getName()+"线程组: "+threads[i].getName());
				return threadList;
			}
			public List<String> getThreadGroups(ThreadGroup group)
			{
				List<String> threadList = getThreads(group);
				ThreadGroup[] gps = new ThreadGroup[group.activeGroupCount()];
				int count = group.enumerate(gps,false);
				for(int i=0;i<count;++i) threadList.addAll(getThreads(gps[i]));
				return threadList;
			}
		}
		ThreadList tl = new ThreadList();
		for(String str : tl.getThreadGroups(tl.getRootThreadGroups()))
			myprint(str);
	}
	public static void test15()
	{
		class ThreadState implements Runnable
		{
			public synchronized void waitForASecond()throws InterruptedException
			{
				wait(1000*2);
			}
			public synchronized void waitForever()throws InterruptedException
			{
				wait();
			}
			public synchronized void notifyNow()throws InterruptedException
			{
				notify();
			}
			public void run()
			{
				try
				{
					waitForASecond();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				for(int i=0;i<100;++i)
					myprint("print i : "+i);
			}
		}
		
		ThreadState state = new ThreadState();
		Thread thd = new Thread(state);
		myprint("新建线程: "+thd.getState());
		thd.start();
		myprint("启动线程: "+thd.getState());
		try
		{
			thd.sleep(1000);
			myprint("计时等待: "+thd.getState());
			state.notifyNow();
			myprint("唤醒线程: "+thd.getState());
			thd.sleep(1000);
			myprint("终止线程: "+thd.getState());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void test14()
	{
		Thread a = new Thread("tha"){
			int ct = 100;
			public void run()
			{
				while(ct>0)
				{
					--ct;
					myprint("run tha... "+ct);
				}
				
			}
		};
		a.start();
		
		int ct=100;
		while(ct>0)
		{
			--ct;
			myprint("run in main... "+ct);
		}
		
	}
	public static void test13()
	{
		class myIcon implements Icon
		{
			private int width;
			private int height;
			public int getIconHeight()
			{
				return this.height;
			}
			public int getIconWidth()
			{
				return this.width;
			}
			public myIcon(int width,int height)
			{
				myprint("in myIcon constructor");
				this.width = width;
				this.height = height;
			}
			public void paintIcon(Component arg0,Graphics arg1,int x,int y)
			{
				arg1.fillOval(x,y,width,height);
			}
		}
		
		class myJDialog extends JDialog
		{
			public myJDialog(JFrame jfIns)
			{
				super(jfIns,"JDialog 窗体",true);
				Container ct = getContentPane();
				ct.add(new JLabel("这是一个对话框"));
				setSize(400,200);
				setResizable(false);
			}
		}
		
		class myFrame extends JFrame
		{
			private Icon i1,i2;
			final private Thread t;
			final private Thread tha;
			final private Thread thb;
			
			public myFrame()
			{
				super("sndmfakjn");
				Container ct = getContentPane();
				ct.setLayout(null);
				JLabel jl = new JLabel("JFrame 窗体");
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				ct.add(jl);
				jl.setBounds(400,10,540,258);
				JButton bl = new JButton("弹出对话框");
				bl.setBounds(10,10,100,20);
				bl.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						myJDialog jd = new myJDialog(myFrame.this);
						jd.setVisible(true);
						
					}
				});
				ct.add(bl);
				setVisible(true);
				setSize(500,300);
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				//setResizable(false);
				
				//JLabel jf = new JLabel("测试",new myIcon(150,150),SwingConstants.CENTER);
				//jf.setVisible(true);
				//jf.setLocation(20,20);
				//jf.setHorizontalAlignment(SwingConstants.CENTER);
				//jf.setBounds(10,10,100,20);
				//ct.add(jf);
				
				JProgressBar pb1 = new JProgressBar();
				JProgressBar pb2 = new JProgressBar();
				pb1.setBounds(300,600,100,20);
				pb2.setBounds(300,700,100,20);
				ct.add(pb1);
				ct.add(pb2);
				pb1.setStringPainted(true);
				pb2.setStringPainted(true);
				
				tha = new Thread(new Runnable(){
					int count = 0;
					public void run()
					{
						while(true)
						{
							pb1.setValue(++count);
							try
							{
								tha.sleep(100);
								thb.join();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							if(count>=100) break;
						}
					}
				});
				
				tha.start();
				thb = new Thread(new Runnable(){
					int count = 0;
					public void run()
					{
						while(true)
						{
							pb2.setValue(++count);
							try
							{
								thb.sleep(100);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							if(count >=100) break;
						}
					}
				});
				thb.start();
				
				
				URL url1 = myFrame.class.getResource("bd_logo1.png");
				URL url2 = myFrame.class.getResource("google-search.png");
				i1 = new ImageIcon(url1);
				i2 = new ImageIcon(url2);
				try
				{
					// URL url = myFrame.class.getResource("bd_logo1.png"); //new URL("bd_logo1.png");
					// Icon icon = new ImageIcon(url);
					jl.setIcon(i1);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				t = new Thread(new Runnable(){
					int count = 200;
					public void run()
					{
						while(count>0)
						{
							jl.setBounds(400 - (200-count),10,540,258);
							try
							{
								//t.sleep(1000);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							count-=4;
							if(count<=0) count = 200;
						}
					}
				});
				t.start();
				
				JLabel lb1 = new JLabel("备注 ");
				ct.add(lb1);
				lb1.setBounds(50,200,100,50);
				JScrollPane sp = new JScrollPane();
				ct.add(sp);
				sp.setBounds(150,200,200,500);
				
				JTextArea ta = new JTextArea();
				ta.setLineWrap(true);
				ta.setRows(3);
				ta.setColumns(15);
				sp.setViewportView(ta);
				
				ta.addKeyListener(new KeyListener(){
					public void keyPressed(KeyEvent e)
					{
						String kt = KeyEvent.getKeyText(e.getKeyCode());
						if(e.isActionKey()) myprint("您按下的是动作键"+ kt);
						else
						{
							myprint("按下非动作键"+kt);
							
						}
					}
					
					public void keyTyped(KeyEvent e)
					{
						myprint("此次输入的是 "+e.getKeyChar());
					}
					
					public void keyReleased(KeyEvent e)
					{
						String kt = KeyEvent.getKeyText(e.getKeyCode());
						myprint("您释放的是 "+kt);
					}
				});
				
				jl.addMouseListener(new MouseListener(){
					public void mouseEntered(MouseEvent e)
					{
						myprint("光标移入组件");
						jl.setIcon(i2);
					}
					public void mousePressed(MouseEvent e)
					{
						myprint("鼠标按钮按下");
						int i = e.getButton();
						if(i==MouseEvent.BUTTON1) myprint("左键");
						else if(i==MouseEvent.BUTTON2) myprint("滚轮");
						else myprint("右键");
					}
					public void mouseReleased(MouseEvent e)
					{
						myprint("鼠标按钮释放");
						int i = e.getButton();
						if(i==MouseEvent.BUTTON1) myprint("左键");
						else if(i==MouseEvent.BUTTON2) myprint("滚轮");
						else myprint("右键");
					}
					public void mouseClicked(MouseEvent e)
					{
						myprint("单击了鼠标按钮");
						int i = e.getButton();
						if(i==MouseEvent.BUTTON1) myprint("左键");
						else if(i==MouseEvent.BUTTON2) myprint("滚轮");
						else myprint("右键");
						
						myprint("单击次数: "+e.getClickCount());
					}
					public void mouseExited(MouseEvent e)
					{
						jl.setIcon(i1);
						myprint("光标移出组件");
					}
				});
				
				this.addWindowFocusListener(new WindowFocusListener(){
					public void windowGainedFocus(WindowEvent e)
					{
						myprint("容器获得了焦点");
					}
					
					public void windowLostFocus(WindowEvent e)
					{
						myprint("容器失去了焦点");
					}
				});
				
				this.addWindowStateListener(new WindowStateListener(){
					public void windowStateChanged(WindowEvent e)
					{
						int oldstate = e.getOldState();
						int newstate = e.getNewState();
						String from="",to="";
						switch(oldstate)
						{
							case Frame.NORMAL:
							from = "正常化";
							break;
							case Frame.MAXIMIZED_BOTH:
							from ="最大化";
							break;
							default:
							from="图标化";
						}
						switch(newstate)
						{
							case Frame.NORMAL:
							to = "正常化";
							break;
							case Frame.MAXIMIZED_BOTH:
							to ="最大化";
							break;
							default:
							to="图标化";
						}
						myprint(from+"-------------->"+to);
					}
				});
				
				this.addWindowListener(new WindowListener(){
					public void windowActivated(WindowEvent e)
					{
						myprint("窗口被激活");
					}
					
					public void windowOpened(WindowEvent e)
					{
						myprint("窗口被打开");
					}
					public void windowIconified(WindowEvent e)
					{
						myprint("窗口被图标化");
					}
					public void windowDeiconified(WindowEvent e)
					{
						myprint("窗口正常化");
					}
					public void windowClosing(WindowEvent e)
					{
						myprint("窗口将要被关闭");
					}
					public void windowDeactivated(WindowEvent e)
					{
						myprint("窗口不再处于激活状态");
					}
					public void windowClosed(WindowEvent e)
					{
						myprint("窗口已经被关闭");
					}
				});
				
				String lbs[]={"a","b","d","e","f"};
				//DefaultComboBoxModel model = new DefaultComboBoxModel(lbs);
				JComboBox<String> cb = new JComboBox<String>(lbs);
				cb.setBounds(0,400,100,100);
				ct.add(cb);
				
				cb.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e)
					{
						int sc = e.getStateChange();
						String item = e.getItem().toString();
						if(sc==ItemEvent.SELECTED)
						{
							myprint(String.format("由选中%s 触发",item));
						}
						else if(sc==ItemEvent.DESELECTED)
						{
							myprint(String.format("由取消%s 触发",item));
							
						}
						else myprint("由其它原因触发");
					}
					
				});
				
				String[] columnName = {"A","B"};
				String[][] rowValues = {{"A1","B1"},{"A2","B2"},{"A3","B3"},{"A4","B4"},{"A5","B5"}};
				DefaultTableModel tableModele = new DefaultTableModel(rowValues,columnName);
				JTable tb = new JTable(tableModele);
				tb.setBounds(500,300,200,200);
				ct.add(tb);
				
				JButton ab = new JButton("添加");
				ab.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String[] rv = {"aa","bb"};
						tableModele.addRow(rv);
					}
				});
				ab.setBounds(700,300,200,100);
				ct.add(ab);
				
				JButton db = new JButton("删除");
				db.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						int[] sr = tb.getSelectedRows();
						for(int row=0;row<sr.length;++row) tableModele.removeRow(sr[row]-row);
					}
				});
				db.setBounds(700,400,200,100);
				ct.add(db);
				
				tableModele.addTableModelListener(new TableModelListener(){
					public void tableChanged(TableModelEvent e)
					{
						int type = e.getType();
						int row = e.getFirstRow()+1;
						int column = e.getColumn()+1;
						if(type == TableModelEvent.INSERT)
						{
							myprint("此次事件由插入行触发"+row);
						}
						else if(type==TableModelEvent.UPDATE)
							myprint(String.format("事件由修改%d行 %d列触发",row,column));
						else if(type==TableModelEvent.DELETE)
							myprint(String.format("事件由删除%d行触发",row));
						else myprint("事件由其它原因触发");
					}
				});
			}
		}
		
		
		myFrame mjf = new myFrame();
		
		
	}
	public static void test12()
	{
		JFrame jf = new JFrame("hahah");
		jf.setVisible(true);
		jf.setSize(200,150);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Container ct = jf.getContentPane();
		JLabel jl = new JLabel("这是一个JFrame 窗体");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		ct.add(jl);
		ct.setBackground(Color.red);
		
		JDialog jd = new JDialog(jf,"第一个JDialog窗体",true);
		jd.setVisible(true);
		jd.setSize(100,100);
		
	}
	public static void test11()
	{
		class myZip
		{
			private void zip(String zipFileName,File inputFile) throws Exception
			{
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
				zip(out,inputFile,"");
				myprint("压缩中...");
				out.close();
			}
			
			private void zip(ZipOutputStream out,File f,String base) throws Exception
			{
				if(f.isDirectory())
				{
					File[] fl = f.listFiles();
					out.putNextEntry(new ZipEntry(base+"/"));
					base = base.length() == 0?"":base+"/";
					myprint("in zip func 1 base is :"+base);
					for(int i=0;i<fl.length;++i) zip(out,fl[i],base+fl[i]);
				}
				else
				{
					out.putNextEntry(new ZipEntry(base));
					FileInputStream in = new FileInputStream(f);
					int b;
					myprint("压缩文件: "+base);
					while((b=in.read())!=-1) out.write(b);
					in.close();
				}
				
			}
		}
		
		if (false)
		{
			myZip book = new myZip();
			try
			{
				book.zip("afterZip.zip",new File("testZip"));
				myprint("压缩完成");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		
		ZipInputStream zin;
		try
		{
			zin = new ZipInputStream(new FileInputStream("afterZip.zip"));
			ZipEntry entry = zin.getNextEntry();
			while(((entry = zin.getNextEntry())!=null))
			{
				myprint("entry name :"+entry.getName());
				File file = new File(entry.getName());
				myprint("file name :"+file.getName()+" "+file.getAbsolutePath()+" "+file.getCanonicalPath());
				if(!file.exists()) 
				{
					file.getParentFile().mkdirs();
					if(!(entry.getName().endsWith("/")))
						file.createNewFile();
					else file.mkdirs();
					
				}
				zin.closeEntry();
				myprint(entry.getName()+" 解压成功");
			}
			zin.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test10()
	{
		try
		{
			FileOutputStream fs = new FileOutputStream("2.txt",true);
			DataOutputStream ds = new DataOutputStream(fs);
			ds.writeUTF("使用 writeUTF 写入数据");
			ds.writeChars("使用 writeChars 写入数据");
			ds.writeBytes("使用 writeBytes 写入数据");
			ds.close();
			
			FileInputStream fis = new FileInputStream("2.txt");
			DataInputStream dis = new DataInputStream(fis);
			myprint(dis.readUTF());
			dis.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test9()
	{
		String content[] = {"回实现对文件的续写。 ","中写入字符数据的字符流输出流对象",
		"会抛异常 ","闭后在写的话，会抛IOException"} ;
		try
		{
			File file = new File("1.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bufw = new BufferedWriter(fw);
			for(int k=0;k<content.length;++k)
			{
				bufw.write(content[k]);
				bufw.newLine();
			}
			bufw.close();
			fw.close();
			
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			String s = null;
			int i =0 ;
			while((s=bufr.readLine())!=null)
			{
				++i;
				myprint(s);
			}
			bufr.close();
			fr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void test8()
	{
		try
		{
			FileReader fin = new FileReader("1.txt");
			char byt[] = new char[1024*2*2*1024];
			int len = fin.read(byt);
			myprint(new String(byt,0,len));
			fin.close();
			
			FileWriter fout = new FileWriter("1.txt");
			byte[] bt = "over write!!!".getBytes();
			fout.write("over write!!!哈哈");
			fout.close();
			
			File f = new File("1.txt");
			f.delete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test7()
	{
		try
		{
			FileInputStream fin = new FileInputStream("1.txt");
			byte byt[] = new byte[1024*2*2*1024];
			int len = fin.read(byt);
			myprint(new String(byt,0,len));
			fin.close();
			
			FileOutputStream fout = new FileOutputStream("1.txt");
			byte[] bt = "over write!!!".getBytes();
			fout.write("over write!!!".getBytes());
			fout.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test6()
	{
		char[] temp = new char[1024];
		try
		{
			FileWriter writer = new FileWriter("1.txt",true);
			writer.write("hahahhhhh");
			writer.flush();
			writer.close();
			
			FileReader rd = new FileReader("1.txt");
			StringBuilder sb = new StringBuilder();
			while(rd.read(temp)!=-1) sb.append(temp);
			System.out.println(sb.toString());
			rd.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		File file = new File("1.txt");
		if(file.exists())
		{
			
			myprint("文件名: "+file.getName()+" 是否可读: "+file.canRead()+" 长度: "+file.length()+
			" 绝对路径:"+file.getAbsolutePath());
			myprint("是文件夹? "+file.isDirectory()+" 最后修改: "+file.lastModified());
			
			
			
			file.delete();
			System.out.println("文件已经删除");
		}
		
		Frame f = new Frame();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt)
			{
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false); //setMultiSelectionEnabled
		int result = chooser.showOpenDialog(f);
		if(result == JFileChooser.APPROVE_OPTION)
		{
			File[] lf = chooser.getSelectedFile().listFiles(new java.io.FileFilter(){
				@Override
				public boolean accept(File pathname)
				{
					if (pathname.getName().endsWith(".txt") || pathname.getName().startsWith("newFile_"))
						return true;
					else
						return false;
				}
			});
			
			int findex = 0;
			for(File file1 : lf)
			{
				++findex;
				String fn = file1.getName();
				
				myprint("rename before file name is :"+fn);
				//fn.replaceAll(new String("新建文本文档"),new String(""));
				//myprint("after rename : "+fn);
				//file1.renameTo(new File(fn.replaceAll("newFile_","")+".txt"));
				file1.renameTo(new File(String.format("./../Zend/%s",fn)));
			}
		}
		
		
		
	}
	public static void test5()
	{
		class GD extends Frame
		{
			public void paint(Graphics g)
			{
				setXY();
				Point p = new Point(cx/2,cy/2);
				g.drawLine(0,0,cx,cy);
				g.draw3DRect(cx/2,cy/2,cx/3,cy/3,false);
				int o1= p.x-40,o2=(p.y-40),o3=80,o4=40;
				//Rectangle rect = new Rectangle((p.x-40),(p.y-40),80,40);
				int[] xp = {(p.x-40),(p.x+90),(p.x+200),p.x-40};
				int[] yp = {p.y-40,p.y+140,p.y+60,p.y-40};
				g.drawArc(o1,o2,o3,o4*2,270,90);
				g.drawPolygon(xp,yp,3);
			}
			
			public void setXY()
			{
				Dimension d = getSize();
				cx=d.width/2;
				cy=d.height/2;
			}
			
			public GD(String str)
			{
				super(str);
				addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent evt)
					{
						setVisible(false);
						dispose();
						System.exit(0);
					}
				});
			}
			private int cx;
			private int cy;
		}
		GD f = new GD("Graphics 绘图");
		f.setSize(500,300);
		f.setVisible(true);
	}
	public static void test4()
	{
		class SD extends Frame
		{
			public SD()
			{
				super();
				init();
			}
			
			Scrollbar slider;
			Label lable;
			void init()
			{
				setLayout(new GridLayout(1,3));
				slider = new Scrollbar(Scrollbar.HORIZONTAL,0,1,0,100);
				lable = new Label("0~100");
				lable.setBackground(Color.cyan);
				add(lable);
				add(slider);
				addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent evt)
					{
						setVisible(false);
						dispose();
						System.exit(0);
					}
				});
				setSize(300,50);
				setVisible(true);
				
			}
		}
		new SD();
	}
	public static void test3()
	{
		class CanvasDemo extends Panel
		{
			private myCanvas mc;
			private Frame f;
			public CanvasDemo()
			{
				f = new Frame();
				mc = new myCanvas();
				mc.repaint(0,0,100,100);
				add("Center",mc);
				f.add(mc);
				f.setSize(300,200);
				f.setLocation(400,400);
				f.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent evt)
					{
						f.setVisible(false);
						f.dispose();
						System.exit(0);
					}
				});
				f.setVisible(true);
				f.setBackground(Color.GREEN);
				
				f.add(new TextField("好"));
			}
			
			class myCanvas extends Canvas
			{
				public void paint(Graphics g)
				{
					g.setColor(Color.RED);
					g.drawRect(100,40,100,100);
					g.drawString("来个后空翻",100,50);
				}
			}
		}
		
		new CanvasDemo();
	}
	
	public static void test2()
	{
		String a="a",b="B",c="c",d="d",e="e";
		List<String> list = new LinkedList<String>();
		list.add(a);
		list.set(0,c);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		
		Iterator<String> it = list.iterator();
		while(it.hasNext())
			System.out.print(it.next()+" ");
		System.out.print("\n");
		Set<String> set = new HashSet<String>();
		set.addAll(list);
		Iterator<String> ist = set.iterator();
		while(ist.hasNext())
			System.out.print(ist.next()+" ");
		System.out.print("\n");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("1","apple");
		map.put("2","car");
		map.put("3","bird");
		map.put("4","cat");
		map.put("5","number");
		for(int i=1;i<=5;++i)
			System.out.println(map.get(""+i));
		
		Map<Integer,String> map1 = new HashMap<Integer,String>();
		map1.put(1,"apple");
		for(int i=1;i<=1;++i)
			System.out.println(map1.get(i));
		

		class firstFrame extends Frame
		{
			public firstFrame(String str)
			{
				super(str);
			}
			
			public void init()
			{
				int w=850,h=480;
				this.setSize(w,h);
				setBackground(Color.BLUE);
				setVisible(true);
				//setOpacity(0.5f);
				
				
				setLocation(1920/2 - w/2,1080/2 - h/2);
				addWindowListener(new MywindowAdapter());
				setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
			}
			
			class MywindowAdapter extends WindowAdapter
			{
				public void windowClosing(WindowEvent we)
				{
					firstFrame.this.dispose();
					System.exit(0);
				}
			}
		}
		
		firstFrame f = new firstFrame("hahhah");
		f.init();
		
		Panel p = new Panel();
		p.setSize(50,50);
		p.setBackground(Color.RED);
		//p.setLocation(50,50);
		f.add(p);
		
		Button bt = new Button("button");
		bt.setLocation(100,50);
		//bt.setSize(50,20);
		f.add(bt);
		
		Choice ColorChooser = new Choice();
		ColorChooser.setSize(100,100);
		//ColorChooser.setLocation(200,300);
		ColorChooser.add("Green");
		ColorChooser.add("Red");
		ColorChooser.add("Blue");
		f.add(ColorChooser);
		
		//f.setLayout(new GridLayout(3, 1));
		Checkbox c1 = new Checkbox("one");
		c1.setSize(100,100);
		//c1.setLocation(100,200);
		f.add(c1);
	
		Label l1 = new Label("HHHHHHHHHHHHHHHHHHHHH");
		f.add(l1);
		
		CheckboxGroup cbg = new CheckboxGroup();
		f.add(new Checkbox("one",cbg,true));
		f.add(new Checkbox("two",cbg,false));
		f.add(new Checkbox("three",cbg,false));
		f.add(new Checkbox("four",cbg,false));
		
		class myCanvas extends Canvas
		{
			public void paint(Graphics g)
			{
				g.setColor(Color.RED);
				g.drawRect(100,40,100,100);
				g.drawString("Canvas",100,50);
			}
		}
		
		Panel panel_ = new Panel();
		
		
		myCanvas mc = new myCanvas();
		mc.repaint(0,0,100,100);
		panel_.add("Center",mc);
		f.add(mc);
		
		f.add(new TextField("gooooooooooooooooooooooooooood"));
		f.add(new Scrollbar(Scrollbar.VERTICAL,0,1,0,255));

		//f.add(wd);
		
		//practice p255
		class myWind
		{
			public myWind()
			{
				
			}
		}
	}
	public static void test1()
	{
		System.out.println("Hello World!");
		
		Test1 t11 = new Test1();
		t11.saySomething();
		
		for(int i=0;i<10;++i) System.out.println(new Random().nextInt(3));
		for(int i=0;i<10;++i) System.out.println(new Random().nextBoolean());
		
		Test t1 = new Test("tw",10);
		Test t2 = new Test("ta",10);
		Test t3 = t1;
		System.out.println("t1 == t2 ? "+ t1.equals(t2));
		System.out.println("t1 == t3 ? "+ t1.equals(t3));
		System.out.println("t1 custom hashCode  "+ t1.hashCode());
		System.out.println("t2 custom hashCode  "+ t2.hashCode());
		
		t1.moveDish(4,'a','b','c');
		t2.moveDish(3,'A','B','C');
		
		Singleton.getInstance().saySomething();
		Singleton.getInstance().saySomething();
		Singleton.getInstance().saySomething();
		
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle(10.0,9.9);
		System.out.println("r1 area "+ r1.getArea());
		System.out.println("r2 area "+ r2.getArea());
		
		Circle c1 = new Circle();
		System.out.println("c1 area  "+ c1.getArea(10));
		System.out.println("c1 circumference "+ c1.getCircumference(10));
		
		TWCircle c2 = new TWCircle();
		System.out.println("c2 area  "+ c2.getArea(8));
		System.out.println("c2 circumference "+ c2.getCircumference(8));
		c2.commitAttack();
		c2.commitAttack(9999);
		System.out.println(c2.getClass().getSimpleName());
		c2.printName();
		Class<?> clzz = c2.getClass();
		System.out.println("canonical name is : " + clzz.getCanonicalName());
		
		try{
			Field pName = clzz.getDeclaredField("name");
			pName.setAccessible(true);
			pName.set(c2,"tw");
			c2.printName();
		}catch(SecurityException e)
		{
			e.printStackTrace();
		}catch(NoSuchFieldException e)
		{
			e.printStackTrace();
		}catch(IllegalArgumentException e)
		{
			e.printStackTrace();
		}catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		TWCircle.innerClass in = c2.doit();
		c2.ouf();
		TWCircle.innerClass in1 = c2.new innerClass();
		c2.sell(99);
		c2.noNameClass();
		
		class oini extends TWCircle.innerClass
		{
			public oini(TWCircle ddd)
			{
				ddd.super();
			}
		}
		
		oini i_ = new oini(c2);
		
		class AlarmClock
		{
			private int delay;
			private boolean flag;
			public AlarmClock(int delay,boolean flag)
			{
				this.delay=delay;
				this.flag=flag;
			}
			
			public void start()
			{
				class Printer implements ActionListener
				{
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						SimpleDateFormat format = new SimpleDateFormat("k:m:s");
						String result = format.format(new Date());
						System.out.println("cur time is : "+result);
						if(flag)
						{
							Toolkit.getDefaultToolkit().beep();
						}
					}
				}
				new Timer(delay,new Printer()).start();
			}
		}
		
		//AlarmClock ck1 = new AlarmClock(1000,true);
		//ck1.start();
		//JOptionPane.showMessageDialog(null,"are you sure exit?");
		class MaxMin
		{
			public MaxMin(){}
			private MaxMin(int a,int b,int c,String d){}
			public MaxMin(String... strings) throws NumberFormatException
			{
				for(int i=0;i<strings.length;++i)
					System.out.println(strings[i]);
			}
			class Result
			{
				private double max;
				private double min;
				public Result(double max,double min)
				{
					this.max = max;
					this.min= min;
				}
				public double getMax(){return max;}
				public double getMin(){return min;}
			}
			
			public  Result getResult(double[] array)
			{
				double max = Double.MIN_VALUE;
				double min = Double.MAX_VALUE;
				for(double i : array)
				{
					if(i>max) max = i;
					if(i<min) min = i;
				}
				return new Result(max,min);
			}
			public int a;
			private float b;
			protected String c;
			
			public void func1()
			{
				System.out.println("func1");
			}
			
			public void func2(int a,int b)
			{
				System.out.println("func2 "+a+" "+b);
			}
			
			private void func3()
			{
				System.out.println("func3");
			}
		}
		
		
		MaxMin mm = new MaxMin();
		MaxMin.Result mr = mm.getResult(new double[]{11.08,44.0,22.8,99.9,54.32,77.61});
		System.out.println("max is "+ mr.getMax()+" min is "+mr.getMin());
		System.out.println(mr.getClass().getPackage());
		System.out.println(mr.getClass().getName());
		System.out.println(mr.getClass().getMethods());
		try
		{
			System.out.println(mm.getClass().getDeclaredConstructor().isVarArgs());
			System.out.println(mm.getClass().getDeclaredConstructor().getParameterTypes());
			System.out.println(mm.getClass().getDeclaredConstructor().getModifiers());
			System.out.println(Modifier.toString( mm.getClass().getDeclaredConstructor().getModifiers()) );
			
			Constructor[] dc = mm.getClass().getDeclaredConstructors();
			for(int i=0;i<dc.length;++i)
			{
				Constructor ct = dc[i];
				System.out.println("is var args "+ct.isVarArgs());
				Class[] pt = ct.getParameterTypes();
				for(int j=0;j<pt.length;++j) System.out.println("param type is "+pt[j]);
				
				System.out.println("possible exception is ");
				Class[] et = ct.getExceptionTypes();
				for(int j=0;j<et.length;++j) System.out.println(" exception is "+et[j]);
			}
			Field df[] = mm.getClass().getDeclaredFields();
			for(int i=0;i<df.length;++i)
			{
				Field fd = df[i];
				System.out.println("filed name is "+fd.getName()+" type is "+fd.getType());
				boolean isTurn = true;
				while(isTurn)
				{
					try
					{
						isTurn = false;
						if(fd.getType().equals(int.class))
						{
							fd.setInt(mm,123456);
							//System.out.println("after modify :" +fd.get(mm));
						}
						else if(fd.getType().equals(float.class))
						{
							fd.set(mm,0.0134f);
						}
						else if(fd.getType().equals(String.class))
						{
							fd.set(mm,"Hello world");
						}
						
						System.out.println("after modify :" +fd.get(mm));
					}
					catch(Exception e)
					{
						System.out.println("set accessible true in exception");
						fd.setAccessible(true);
						isTurn = true;
					}
				}
				
			}
			
			Method[] dm = mm.getClass().getDeclaredMethods();
			for(int i=0;i<dm.length;++i)
			{
				Method md = dm[i];
				System.out.println("method name is "+md.getName());
				System.out.println("method is with args"+md.isVarArgs());
				Class[] pt = md.getParameterTypes();
				if (pt.length>0)
				{
					for(int j=0;j<pt.length;++j)
					{
						System.out.print(""+pt[j]+" ");
					}
					System.out.print("\n");
				}
				else
					System.out.print("null\n");
				boolean isTurn = true;
				while(isTurn)
				{
					try
					{
						isTurn = false;
						if(i==1) md.invoke(mm);
						else if(i==2) md.invoke(mm,1,2);
						else if(i==3) md.invoke(mm);
					}
					catch(Exception e)
					{
						System.out.println("set accessible true in exception");
						md.setAccessible(true);
						isTurn = true;
					}
				}
				
			}
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		// catch(NoSuchFieldException e)
		// {
			// e.printStackTrace();
		// }
		
		try
		{
			Method equals = String.class.getDeclaredMethod("equals",Object.class);
			System.out.println("is equal "+equals.invoke(new String("hahah"),"hahahq")) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		class Nothing
		{
			@SuppressWarnings("unchecked")
			public String toString(Object object)
			{
				Class clazz = object.getClass();
				StringBuilder sb = new StringBuilder();
				sb.append("this is my class hahahahha ~");
				sb.append(clazz.getSimpleName());
				return sb.toString();
			}
		}
		
		System.out.println(new Nothing().toString(new java.util.Date()));
		
		//practice p248
		
	}
	
}