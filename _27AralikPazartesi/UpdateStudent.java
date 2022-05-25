package _27AralikPazartesi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateStudent extends JFrame {

	private JPanel contentPane;
	private JTextField txtSurname;
	private JTextField txtName;
	private JTextField txtStudentId;
	JComboBox comboBox;
	JLabel lblID;
	
	public void getStu(Student stu){
		txtName.setText(stu.name);
		txtSurname.setText(stu.surname);
		lblID.setText(stu.idstudent+"");
		comboBox.setSelectedItem(stu.department);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(22, 64, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(22, 137, 46, 14);
		contentPane.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(125, 134, 86, 20);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(125, 61, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(22, 218, 62, 14);
		contentPane.add(lblDepartment);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(125, 214, 86, 22);
		contentPane.add(comboBox);
		
		JLabel lblID = new JLabel("ID Student");
		lblID.setBounds(22, 298, 62, 14);
		contentPane.add(lblID);
		
		txtStudentId = new JTextField();
		txtStudentId.setBounds(125, 295, 86, 20);
		contentPane.add(txtStudentId);
		txtStudentId.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student stu = new Student();
				stu.name = txtName.getText();
				stu.surname = txtSurname.getText();
				stu.department = comboBox.getSelectedItem().toString();
				stu.idstudent = Integer.parseInt(lblID.getText());
				
				DbConnection db = new DbConnection();
				try {
					
					db.updateStu(stu);
					StudentListPage slist = new StudentListPage();
					slist.setVisible(true);
					dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(314, 315, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StudentListPage slist;
				try {
					slist = new StudentListPage();
					slist.setVisible(true);
					dispose();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		btnBack.setBounds(314, 349, 89, 23);
		contentPane.add(btnBack);
	}

}
