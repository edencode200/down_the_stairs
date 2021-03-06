package project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

/*
 * 遊戲選單頁面
 */
public class MenuPanel extends JPanel implements ActionListener {
	private JLabel logo;
	private JButton jbutton_start, jbutton_ranking, jbutton_messageboard, jbutton_logout, jbutton_quit, jbutton_setting;
	User usern = new User();
	
	MenuPanel(User loginuser) {
		usern = loginuser;
		
		jbutton_start = new JButton(new ImageIcon(getClass().getResource("/img/play.png")));
		jbutton_start.setBounds(208, 265, 183, 55);
		jbutton_start.addActionListener(this);
		jbutton_start.setBorderPainted(false);// 去邊框
		jbutton_start.setContentAreaFilled(false);// 去底色
		this.add(jbutton_start);
		
		jbutton_ranking = new JButton(new ImageIcon(getClass().getResource("/img/ranking.png")));
		jbutton_ranking.setBounds(200, 340, 92, 92);
		jbutton_ranking.addActionListener(this);
		jbutton_ranking.setBorderPainted(false);
		jbutton_ranking.setContentAreaFilled(false);
		this.add(jbutton_ranking);
		
		jbutton_messageboard = new JButton(new ImageIcon(getClass().getResource("/img/msgboard.png")));
		jbutton_messageboard.setBounds(308, 340, 92, 92);
		jbutton_messageboard.addActionListener(this);
		jbutton_messageboard.setBorderPainted(false);
		jbutton_messageboard.setContentAreaFilled(false);
		this.add(jbutton_messageboard);
		
		jbutton_logout = new JButton(new ImageIcon(getClass().getResource("/img/logout.png")));
		jbutton_logout.setBounds(200, 448, 92, 92);
		jbutton_logout.addActionListener(this);
		jbutton_logout.setBorderPainted(false);
		jbutton_logout.setContentAreaFilled(false);
		if(!(usern.getName()).equals("Guest")) {
			this.add(jbutton_logout);
		}
		
		
		jbutton_quit = new JButton(new ImageIcon(getClass().getResource("/img/exit.png")));
		if(!(usern.getName()).equals("Guest")) {
			jbutton_quit.setBounds(308, 448, 92, 92);
		}else {
			jbutton_quit.setBounds(254, 448, 92, 92);
		}
		jbutton_quit.addActionListener(this);
		jbutton_quit.setBorderPainted(false);
		jbutton_quit.setContentAreaFilled(false);
		this.add(jbutton_quit);
		
		jbutton_setting = new JButton(new ImageIcon(getClass().getResource("/img/setting.png")));
		jbutton_setting.setBounds(200, 473, 92, 92);
		jbutton_setting.addActionListener(this);
		jbutton_setting.setBorderPainted(false);
		jbutton_setting.setContentAreaFilled(false);
		//this.add(jbutton_setting,0);

		logo = new JLabel();
		logo.setBounds(184,30,231,231);
		logo.setIcon(new ImageIcon(getClass().getResource("/img/logo.png")));
		this.add(logo);
		
		this.setSize(600, 600);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.black);
	} // MenuPanel() end
	
	public void actionPerformed(ActionEvent evt) { 
		if (evt.getSource() == jbutton_start) {
			User user = new User();
			if (!usern.getName().equals("Guest")) {
				JDBC_test2 jdbc = new JDBC_test2();
				user = jdbc.login(usern);
			} else {
				user = usern;
			}
			new GamePlay(user);
		} else if(evt.getSource() == jbutton_ranking)
			new RankingBoard();
		else if (evt.getSource() == jbutton_messageboard)
			new MessageBoard(usern);
		else if (evt.getSource() == jbutton_logout)
			// TODO:show an optionpane to ask if player is logout or not
			new MainFrame(1,usern);
		else if (evt.getSource() == jbutton_quit) {
			int option = JOptionPane.showConfirmDialog(null, "確定要退出遊戲嗎?", "DownTheStairs", JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				System.exit(0);
				
			}
		}
	}
}