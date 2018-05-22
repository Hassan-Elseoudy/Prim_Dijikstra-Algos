package projPKJ;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void testPrimAlgo() throws FileNotFoundException {
		PrimAlgo.readPrimAlgoFile();
	}
	public static void testDijikstraAlgo() throws FileNotFoundException {
		Dijikstra.readDijikstraAlgoFile();
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 117);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnPrimAlgo = new JRadioButton("Prim Algo");
		rdbtnPrimAlgo.setBounds(6, 43, 109, 23);
		contentPane.add(rdbtnPrimAlgo);
				
		JRadioButton rdbtnDijikstraAlgo = new JRadioButton("Dijikstra Algo");
		rdbtnDijikstraAlgo.setBounds(6, 17, 109, 23);
		contentPane.add(rdbtnDijikstraAlgo);
		
		rdbtnPrimAlgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnPrimAlgo.isSelected())
					rdbtnDijikstraAlgo.setSelected(false);
			}
		});
		
		rdbtnDijikstraAlgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnPrimAlgo.isSelected())
					rdbtnPrimAlgo.setSelected(false);
			}
		});

		JButton btnYalla = new JButton("Yalla!");
		btnYalla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnPrimAlgo.isSelected()) {
					try {
						testPrimAlgo();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(rdbtnDijikstraAlgo.isSelected()) {
					try {
						testDijikstraAlgo();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnYalla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnYalla.setBounds(141, 43, 89, 23);
		contentPane.add(btnYalla);
	}
}
