import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class game extends JFrame implements ItemListener, ActionListener
{

Icon ic1,ic2,ic3,icon,ic4,ic22;
int []options=new int [8];int cnt=0;
int []optionsblack=new int [8];int cntb=0;
Checkbox c1,c2;
JButton u;

String X[]=new String[65];
String DISP=new String("");
boolean undo;
boolean undop=false;

JLabel l1,l2;
JButton b[]=new JButton[65];
JButton reset;
static int S=8;
static int movecount;
int [][]matrix=new int[(S*S)+1][S*S+1];
int []visit=new int [S*S+1];
int []moves=new int [S*S+1];
boolean win=true;
boolean black;

  int [][] a = new int[S][S];

public void showButton(){
int x,y,j,alt=1;
x=10; y=10;j=0;
for(int i=1;i<=64;i++,x+=50,j++,alt++){
 b[i]=new JButton();

if(j==8)
{j=0; y+=50; x=10;alt-=1;}
if(alt%2==0)
{b[i].setIcon(ic4);}
 b[i].setBounds(x,y,50,50);
add(b[i]);
b[i].addActionListener(this);
}//eof for


reset=new JButton("RESET");
reset.setBounds(200,410,100,50);
add(reset);
reset.addActionListener(this);
u=new JButton("UNDO");
u.setBounds(100,410,100,50);
if(undo)
{
add(u);
u.addActionListener(this);
}
}//eof showButton
game(){
super("Knights Tour By Rhea Shruthi Shwetha  ");

CheckboxGroup cbg=new CheckboxGroup();
c1=new Checkbox("with undo",cbg,false);
c2=new Checkbox("without undo",cbg,false);
c1.setBounds(120,80,100,40);
c2.setBounds(120,150,100,40);
add(c1); add(c2);
c1.addItemListener(this);
c2.addItemListener(this);



//state=true;type=true;set=true;
ic1=new ImageIcon("visited.png");
ic2=new ImageIcon("current.png");
ic3=new ImageIcon("option.png");
ic4=new ImageIcon("black.png");

setLayout(null);
setSize(500,500);
setVisible(true);
make_graph();
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}//eof constructor
public void itemStateChanged(ItemEvent e){
 if(c1.getState())
  { 
 undo=true;
 }

 else if(c2.getState())
  { 
	undo = false;
  }
remove(c1);remove(c2);
 repaint(0,0,500,500);
 showButton();
}//
void undopressed()
{
	undop=true;
	visit[moves[movecount-1]]=0; //horse
	visit[moves[movecount-2]]=0; //orange and were options with it
	
	if(black)
	{
	b[moves[movecount-1]].setIcon(null);
	}	
	else
	b[moves[movecount-1]].setIcon(ic4);
	movecount-=2;
	 for (int i=1 ;i<=64;i++)
		{ 
			
			if (b[i].getIcon()==ic3&&black)
			{b[i].setIcon(ic4);}
			else if (b[i].getIcon()==ic3)
			b[i].setIcon(null);
			
		}
	
	b[moves[movecount-1]].setIcon(ic2);
	options(moves[movecount]);
}
	
public void make_graph()
{
	int up=-S;
	int down=S;
	int left=-1;
	int right=1;
	int move;
	int p;
	for ( p =1;p<=(S*S);p++)
	{
	move=p+ up+up+left; validate1(move,p) ;
	move=p+ up+up+right; validate1(move,p);
	move=p+ down+down+left;validate4(move,p);
	move=p+ down+down+right;validate4(move,p);
	move=p+left+left+down;validate3(move,p);
	move=p+left+left+up; validate2(move,p);
	move=p+right+right+up;validate2(move,p);
	move=p+right+right+down;validate3(move,p);
	}
	display();
	movecount=1;
	int c;
	for (int i =1;i<=64;i++)
	{
		if(i<=8)
		{c=1;	}
		else if(i<=16)
		{c=2;	}
		else if(i<=24)
		{c=3;	}
		else if(i<=32)
		{c=4;	}
		else if(i<=40)
		{c=5;	}
		else if(i<=48)
		{c=6;	}
		else if(i<=56)
		{c=7;	}
		else 
		{c=8;	}
		switch (i%8)
		{
			case 1: X[i]=c+"a";break;
			case 2:	X[i]=c+"b";break;
			case 3:X[i]=c+"c";break;
			case 4:X[i]=c+"d";break;
			case 5:X[i]=c+"e";break;
			case 6:X[i]=c+"f";break;
			case 7:X[i]=c+"g";break;
			case 0:X[i]=c+"h";break;

		}
		
		
	}
	
	
}


public void actionPerformed(ActionEvent e){


/********************************/
 
if(e.getSource()==u&&movecount!=1&&undop==false)
{
undopressed ();
}
if(e.getSource()==reset){
int alt=1;
for(int i=1,j=0;i<=64;i++,j++,alt++){
if(j==8)
{alt-=1;j=0;}
if(alt%2==0)
{b[i].setIcon(ic4);}
else
{b[i].setIcon(null);}
movecount=1;
}
 for(int i=1;i<=64;i++){
 visit[i]=0;     


  }//eof for  
}

else{ 
  for(int i=1;i<=64;i++){
      if(e.getSource()==b[i]){
	undop=false;
	options(i);

       }


}//eof actionperformed
}

}


void validate1(int m,int pos)
{
	int br=(pos/S)*S-S;
	if(pos%S==0)
    {
        br= br - S;
    }
	int bl=br-S;
		if (m<=br&&m>bl&&m>=1&&m<=S*S)
			{
				matrix[pos][m]=1;
				//matrix[m][pos]=1;
			}

}
 void validate2(int m,int pos)
{
	int br=(pos/S)*S;
	if(pos%S==0)
    {
        br= br - S;
    }
	int bl=br-S;
		if (m<=br&&m>bl&&m>=1&&m<=S*S)
			{
				matrix[pos][m]=1;
				//matrix[m][pos]=1;
			}


}
void validate3(int m,int pos)
{
	int br=(pos/S)*S+2*S;
	if(pos%S==0)
    {
        br= br - S;
    }
	int bl=br-S;
		if (m<=br&&m>bl&&m>=1&&m<=S*S)
			{System.out.println("pos and m "+m +pos);
				matrix[pos][m]=1;
				//matrix[m][pos]=1;
			}
}
void validate4(int m,int pos)
{
	int br=(pos/S)*S+3*S;
	if(pos%S==0)
    {
        br= br - S;
    }
	int bl=br-S;
		System.out.println(" m =  "+m +"p =  "+pos);
		if (m<=br&&m>bl&&m>=1&&m<=S*S)
			{
				matrix[pos][m]=1;
				//matrix[m][pos]=1;
			}
}
void options (int p )
{	if(movecount!=1)
	{
	 for (int i=1 ;i<=64;i++)
		{ 
			if(b[i].getIcon()==ic2)		//ic1:orange ic2:horse ic3:blue ic4:black
			{b[i].setIcon(ic1);}
			else if (b[i].getIcon()==ic3&&black)
			{b[i].setIcon(ic4);}
			if (b[i].getIcon()==ic3)
			b[i].setIcon(null);
			
		}
	}
	if(b[p].getIcon()==ic4||(b[p].getIcon()==ic1&&black==true) )
	black=false ;
	else black=true;
		
	 if (movecount==1)
	{ System.out.println("moved to " +p);visit[p]=1;b[p].setIcon(ic2);moves[movecount++]=p;}
	else if (matrix[moves[movecount-1]][p]==1&&visit[p]==0)
		{ System.out.println("moved to " +p);visit[p]=1;b[p].setIcon(ic2);moves[movecount++]=p;}
	else 
		{System.out.println ("That was not a legal move ");win=false;finishGame();
		}

	

	boolean op=false;
	
	for (int i =1;i<=S*S;i++)
	{
		if (matrix[p][i]==1&&visit[i]==0)
		{ System.out.println("option: " +i);   //filling option array and coloring 
		
		 b[i].setIcon(ic3);op=true;
		}
		if ( movecount>64)
		 {finishGame();	} 		//successfully finishes game

	}if ( op==false)				//if he runs out of options 
		{win = false ;finishGame();}
	
	
	


}


void display ()
{
for (int i=0;i<=S*S;i++)
{
	System.out.print(i%10+ " ");
}System.out.println();
for (int i=1;i<=S*S;i++)
	{
		System.out.print(i%10+" ");
		for (int j=1;j<=S*S;j++)
		{
			System.out.print(matrix[i][j]+" ");
		}System.out.println();
	}
}
void finishGame()
{
	if (!win)
	{System.out.println("You Lose");
	for (int i =1;i<movecount; i++)
		{
			System.out.print(X[moves[i]]+" ");
			DISP=DISP+" "+X[moves[i]];
		}
	}
	else
		{System.out.println("You Won ");
		System.out.println("Your moves");
		for (int i =1;i<X.length; i++)
		{
			System.out.print(X[moves[i]]+" ");
			DISP=DISP+" "+X[moves[i]];
			
		}
		}
JPanel A = new JPanel();
			if(!win)
			JOptionPane.showMessageDialog(A," YOU LOST!!");
			else 
			JOptionPane.showMessageDialog(A," YOU WON!! ");
   			 		
	
			int response = JOptionPane.showConfirmDialog(A , "DO YOU WANT TO SEE YOUR MOVES ??","Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
				if (response == JOptionPane.YES_OPTION) 
					{
					
			JOptionPane.showMessageDialog(A," YOUR MOVES "+DISP);
			System.exit(0);
					}
				
			else
			{
			System.exit(0);
			}
			
		
		
	
}



public static void main(String []args){
new game();

}
}

