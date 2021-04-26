import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class TextPad implements ActionListener{
	JFrame frame;
	JButton north=new JButton("North");
	JButton south=new JButton("South");
	JButton east=new JButton("East");
	JButton west=new JButton("West");
	JButton reset=new JButton("Reset");
	JPanel buttonPanel,bigPanel;
	JMenuBar menubar;
	GridLayout buttongrid,menugrid,bigpanelgrid;
	JMenu fontmenu, sizemenu, colormenu, bgcolormenu, outlinecolormenu;
	ArrayList<JMenuItem> fontitems, sizeitems, coloritems, bgcoloritems, outlinescoloritems;
	ArrayList<String> fontnames, bgcolornames, colornames, outlinecolornames;
	JTextArea textarea;
	String currentfont;
	int currentfontsize;
	ArrayList<Font>fontarray;
	ArrayList<Integer>fontsizes;
	ArrayList<Color> bordercolor, textcolor, outlinecolor, textbgcolor;
	
	
	
	public TextPad() {
		frame=new JFrame("Textpad (but better)");
		//frame.add(this);
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		//button stuff
		buttongrid=new GridLayout(1,4);
		buttonPanel=new JPanel();
		buttonPanel.setLayout(buttongrid);
		buttonPanel.add(north);
		buttonPanel.add(south);
		buttonPanel.add(east);
		buttonPanel.add(west);
		south.addActionListener(this);
		north.addActionListener(this);
		east.addActionListener(this);
		west.addActionListener(this);
		//menu stuff
		menubar = new JMenuBar();
		fontmenu = new JMenu("font");
		sizemenu= new JMenu("size");
		colormenu= new JMenu("color");
		bgcolormenu= new JMenu("bg color");
		outlinecolormenu= new JMenu("outline");
		menubar.add(fontmenu);
		menubar.add(sizemenu);
		menubar.add(colormenu);
		menubar.add(bgcolormenu);
		menubar.add(outlinecolormenu);
		menubar.add(fontmenu);
		menubar.setLayout(new GridLayout(1,6));
		
		//instantiating all the item options
		fontnames=new ArrayList<String>();
		fontnames.add("Times New Roman");
		fontnames.add("Arial");
		fontnames.add("Helvetica");
		
		fontsizes=new ArrayList<Integer>();
		fontsizes.add(18);
		fontsizes.add(24);
		fontsizes.add(36);
		
		outlinecolor=new ArrayList<Color>();
		outlinecolor.add(Color.magenta);
		outlinecolor.add(Color.blue);
		
		textcolor=new ArrayList<Color>();
		textcolor.add(Color.red);
		textcolor.add(Color.cyan);
		
		textbgcolor=new ArrayList<Color>();
		textbgcolor.add(Color.white);
		textbgcolor.add(Color.black);
		
		fontarray=new ArrayList<Font>();
		fontitems = new ArrayList<JMenuItem>();
		for(int i = 0; i < fontnames.size();i++) {
			fontarray.add(new Font(fontnames.get(i),Font.PLAIN, 18));
			fontitems.add(new JMenuItem(fontnames.get(i)));
			fontitems.get(i).setFont(new Font(fontnames.get(i),Font.PLAIN, 18));
			fontitems.get(i).addActionListener(this);
			fontmenu.add(fontitems.get(i));
		}
		
		coloritems = new ArrayList<JMenuItem>();
		for(int i = 0; i < textcolor.size();i++) {
			coloritems.add(new JMenuItem());
			coloritems.get(i).setBackground(textcolor.get(i));
			coloritems.get(i).addActionListener(this);
			colormenu.add(coloritems.get(i));
		}
		coloritems.add(new JMenuItem("Random"));
		coloritems.get(coloritems.size()-1).addActionListener(this);
		colormenu.add(coloritems.get(coloritems.size()-1));
		
		bgcoloritems = new ArrayList<JMenuItem>();
		for(int i = 0; i < textbgcolor.size();i++) {
			bgcoloritems.add(new JMenuItem());
			bgcoloritems.get(i).setBackground(textbgcolor.get(i));
			bgcoloritems.get(i).addActionListener(this);
			bgcolormenu.add(bgcoloritems.get(i));
		}
		bgcoloritems.add(new JMenuItem("Random"));
		bgcoloritems.get(bgcoloritems.size()-1).addActionListener(this);
		bgcolormenu.add(bgcoloritems.get(bgcoloritems.size()-1));
		
		outlinescoloritems = new ArrayList<JMenuItem>();
		for(int i = 0; i < outlinecolor.size();i++) {
			outlinescoloritems.add(new JMenuItem());
			outlinescoloritems.get(i).setBackground(outlinecolor.get(i));
			outlinescoloritems.get(i).addActionListener(this);
			outlinecolormenu.add(outlinescoloritems.get(i));
		}
		outlinescoloritems.add(new JMenuItem("None"));
		outlinescoloritems.get(outlinescoloritems.size()-1).addActionListener(this);
		outlinecolormenu.add(outlinescoloritems.get(outlinescoloritems.size()-1));
		
		outlinescoloritems.add(new JMenuItem("Random"));
		outlinescoloritems.get(outlinescoloritems.size()-1).addActionListener(this);
		outlinecolormenu.add(outlinescoloritems.get(outlinescoloritems.size()-1));
		
		sizeitems = new ArrayList<JMenuItem>();
		for(int i = 0; i < fontsizes.size();i++) {
			sizeitems.add(new JMenuItem(fontsizes.get(i)+""));
			sizeitems.get(i).setFont(new Font(fontnames.get(0),Font.PLAIN, fontsizes.get(i)));
			sizeitems.get(i).addActionListener(this);
			sizemenu.add(sizeitems.get(i));
		}
		currentfont=fontnames.get(0);
		currentfontsize=fontsizes.get(0);
		
		reset.addActionListener(this);
		menubar.add(reset);
		
		textarea = new JTextArea();
		textarea.setBackground(Color.GRAY);
		textarea.setForeground(Color.WHITE);
		textarea.setFont(fontarray.get(0));
		textarea.setMargin(new Insets(10, 10, 10, 10));
		
		bigPanel=new JPanel();
		bigPanel.setLayout(new GridLayout(1,2));
		bigPanel.add(buttonPanel);
		bigPanel.add(menubar);
		frame.add(bigPanel,BorderLayout.NORTH);
		frame.add(textarea,BorderLayout.CENTER);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		
		TextPad textpad = new TextPad();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==south) {
			frame.remove(bigPanel);
			buttonPanel.setLayout(new GridLayout(1,4));
			menubar.setLayout(new GridLayout(1,6));
			bigPanel.setLayout(new GridLayout(1,2));
			bigPanel.add(buttonPanel);
			bigPanel.add(menubar);
			frame.add(bigPanel,BorderLayout.SOUTH);
		}
		if(e.getSource()==north) {
			frame.remove(bigPanel);
			buttonPanel.setLayout(new GridLayout(1,4));
			menubar.setLayout(new GridLayout(1,6));
			bigPanel.setLayout(new GridLayout(1,2));
			bigPanel.add(buttonPanel);
			bigPanel.add(menubar);
			frame.add(bigPanel,BorderLayout.NORTH);
		}
		if(e.getSource()==east) {
			frame.remove(bigPanel);
			buttonPanel.setLayout(new GridLayout(4,1));
			menubar.setLayout(new GridLayout(6,1));
			bigPanel.setLayout(new GridLayout(2,1));
			bigPanel.add(buttonPanel);
			bigPanel.add(menubar);
			frame.add(bigPanel,BorderLayout.EAST);
		}
		if(e.getSource()==west) {
			frame.remove(bigPanel);
			buttonPanel.setLayout(new GridLayout(4,1));
			menubar.setLayout(new GridLayout(6,1));
			bigPanel.setLayout(new GridLayout(2,1));
			bigPanel.add(buttonPanel);
			bigPanel.add(menubar);
			frame.add(bigPanel,BorderLayout.WEST);
		}
		if(fontitems.contains(e.getSource())) {
			int i = fontitems.indexOf(e.getSource());
			currentfont=fontnames.get(i);
			if(i>=0) {
				System.out.println(currentfontsize);
				Font f = new Font(fontnames.get(i),Font.PLAIN, fontsizes.get(0));
				fontmenu.setFont(f);
				sizemenu.setFont(f);
				for(JMenuItem item:sizeitems) {
					item.setFont(f);
				}
				colormenu.setFont(f);
				for(JMenuItem item:coloritems) {
					item.setFont(f);
				}
				bgcolormenu.setFont(f);
				for(JMenuItem item:bgcoloritems) {
					item.setFont(f);
				}
				outlinecolormenu.setFont(f);
				for(JMenuItem item:outlinescoloritems) {
					item.setFont(f);
				}
				textarea.setFont(f);
			}
		}
		if(coloritems.contains(e.getSource())) {
			int i = coloritems.indexOf(e.getSource());
			if(i==coloritems.size()-1)
				textarea.setForeground(random());
			else
				textarea.setForeground(textcolor.get(i));
		}
		if(bgcoloritems.contains(e.getSource())) {
			int i = bgcoloritems.indexOf(e.getSource());
			if(i==coloritems.size()-1)
				textarea.setBackground(random());
			else
				textarea.setBackground(textbgcolor.get(i));
		}
		if(sizeitems.contains(e.getSource())) {
			int i = sizeitems.indexOf(e.getSource());
			currentfontsize=fontsizes.get(i);
			if(i>=0) 
				textarea.setFont(new Font(currentfont, Font.PLAIN,sizeitems.get(i).getFont().getSize()));
		}
		if(outlinescoloritems.contains(e.getSource())) {
			int i = outlinescoloritems.indexOf(e.getSource());
			if(i==outlinescoloritems.size()-1) {
				Color random=random();
				north.setBorder(new LineBorder(random));
				south.setBorder(new LineBorder(random));
				east.setBorder(new LineBorder(random));
				west.setBorder(new LineBorder(random));
				reset.setBorder(new LineBorder(random));
			}
			else if(i==outlinescoloritems.size()-2) {
				north.setBorder(null);
				south.setBorder(null);
				east.setBorder(null);
				west.setBorder(null);
				reset.setBorder(null);
			}
			else {
				north.setBorder(new LineBorder(outlinecolor.get(i)));
				south.setBorder(new LineBorder(outlinecolor.get(i)));
				east.setBorder(new LineBorder(outlinecolor.get(i)));
				west.setBorder(new LineBorder(outlinecolor.get(i)));
				reset.setBorder(new LineBorder(outlinecolor.get(i)));
			}
		}
		if(e.getSource()==reset) {
			//make north
			frame.remove(bigPanel);
			buttonPanel.setLayout(new GridLayout(1,4));
			menubar.setLayout(new GridLayout(1,6));
			bigPanel.setLayout(new GridLayout(1,2));
			bigPanel.add(buttonPanel);
			bigPanel.add(menubar);
			frame.add(bigPanel,BorderLayout.NORTH);
			
			//change textarea to default font, colors, size
			textarea.setBackground(Color.GRAY);
			textarea.setForeground(Color.WHITE);
			textarea.setFont(fontarray.get(0));
			
			//outline back in black *guitar noises*
			north.setBorder(new LineBorder(Color.BLACK));
			south.setBorder(new LineBorder(Color.BLACK));
			east.setBorder(new LineBorder(Color.BLACK));
			west.setBorder(new LineBorder(Color.BLACK));
			
			//reset buttons and text
			fontmenu.setFont(new Font(fontnames.get(0),Font.PLAIN, fontsizes.get(0)));
			sizemenu.setFont(new Font(fontnames.get(0),Font.PLAIN, fontsizes.get(0)));
			colormenu.setFont(new Font(fontnames.get(0),Font.PLAIN, fontsizes.get(0)));
			bgcolormenu.setFont(new Font(fontnames.get(0),Font.PLAIN, fontsizes.get(0)));
			outlinecolormenu.setFont(new Font(fontnames.get(0),Font.PLAIN, fontsizes.get(0)));
		}
		frame.revalidate();
	}
	public Color random() {
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		return new Color(r,g,b);
	}

}
