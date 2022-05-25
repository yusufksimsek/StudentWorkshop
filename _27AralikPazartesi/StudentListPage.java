package _27AralikPazartesi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentListPage extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentListPage frame = new StudentListPage();
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
	public StudentListPage() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final DefaultTableModel model = new DefaultTableModel();
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DbConnection db = new DbConnection();
				int [] rows = table.getSelectedRows();
				
				for (int i = 0; i < rows.length; i++) {
					try {
						db.deleteStu(table.getValueAt(rows[i]-i, 0));
						model.removeRow(rows[i]-i);	
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				
				}
			}
		});
		btnDelete.setBounds(110, 327, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student stu = new Student();
				stu.idstudent = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				stu.name = table.getValueAt(table.getSelectedRow(), 1).toString();
				stu.surname = table.getValueAt(table.getSelectedRow(), 2).toString();
				stu.department = table.getValueAt(table.getSelectedRow(), 3).toString();
				
				UpdateStudent update;
				update = new UpdateStudent();
				update.getStu(stu);
				update.setVisible(true);
				dispose();
				
			}
		});
		btnUpdate.setBounds(209, 327, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(432, 356, 89, 23);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 48, 347, 257);
		contentPane.add(scrollPane);

		model.setColumnIdentifiers(new String[]{"ID","Name","Surname","Department"});
		DbConnection db = new DbConnection();
		
		ResultSet res = db.getList(); 
		
		while(res.next()){
			
			Object [] temp_row = new Object [res.getMetaData().getColumnCount()];
			for (int i = 0; i < temp_row.length; i++) {
				temp_row[i] = res.getObject(i+1);
			}
			model.addRow(temp_row);
		}
		
		table = new JTable();
		table.setModel(model);
		
		scrollPane.setViewportView(table);
	}
}
