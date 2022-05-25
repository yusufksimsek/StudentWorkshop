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

public class StudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtStudentId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm frame = new StudentForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public StudentForm() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(21, 55, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(21, 127, 46, 14);
		contentPane.add(lblSurname);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(21, 206, 57, 14);
		contentPane.add(lblDepartment);
		
		txtName = new JTextField();
		txtName.setBounds(128, 52, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(128, 124, 86, 20);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		DbConnection db = new DbConnection();
		
		final JComboBox comboBox = new JComboBox(db.getDepts());
		comboBox.setBounds(128, 202, 86, 22);
		contentPane.add(comboBox);
		
		JLabel lblStudentId = new JLabel("Student Id");
		lblStudentId.setBounds(21, 291, 57, 14);
		contentPane.add(lblStudentId);
		
		txtStudentId = new JTextField();
		txtStudentId.setBounds(128, 288, 86, 20);
		contentPane.add(txtStudentId);
		txtStudentId.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student stu = new Student();
				stu.name=txtName.getText();
				stu.surname=txtSurname.getText();
				stu.department=comboBox.getSelectedItem().toString();
				
				DbConnection db = new DbConnection();
				
				try {
					
					db.saveStu(stu);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(388, 287, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnBack.setBounds(388, 321, 89, 23);
		contentPane.add(btnBack);
	}
}
