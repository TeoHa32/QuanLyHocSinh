package GUI;
 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Form extends JFrame {
    
    private JTextField txtStudentId;
    private JTextField txtFullName;
    private JTextField txtBirthDate;
    private JComboBox<String> cboGender;
    private JComboBox<String> cboClass;
    private JComboBox<String> cboEthnicity;
    private JComboBox<String> cboReligion;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtParentName;
    private JTextField txtParentPhone;
    
    public Form() {
        // Thiết lập cơ bản cho frame
        setTitle("Thông tin học sinh");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 102, 204));
        titlePanel.setPreferredSize(new Dimension(0, 60));
        
        JLabel lblTitle = new JLabel("THÔNG TIN HỌC SINH");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        
        titlePanel.add(lblTitle);
        
        // Form nhập liệu
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Thông tin cơ bản
        JLabel lblBasicInfo = new JLabel("Thông tin cơ bản");
        lblBasicInfo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        formPanel.add(lblBasicInfo, gbc);
        
        // Mã học sinh
        JLabel lblStudentId = new JLabel("Mã học sinh:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(lblStudentId, gbc);
        
        txtStudentId = new JTextField();
        txtStudentId.setEditable(false);
        txtStudentId.setText("HS" + System.currentTimeMillis() % 10000); // Tạo mã ngẫu nhiên
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(txtStudentId, gbc);
        
        // Họ tên
        JLabel lblFullName = new JLabel("Họ và tên:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblFullName, gbc);
        
        txtFullName = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(txtFullName, gbc);
        
        // Ngày sinh
        JLabel lblBirthDate = new JLabel("Ngày sinh:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblBirthDate, gbc);
        
        txtBirthDate = new JTextField();
        txtBirthDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(txtBirthDate, gbc);
        
        // Giới tính
        JLabel lblGender = new JLabel("Giới tính:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblGender, gbc);
        
        cboGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(cboGender, gbc);
        
        // Lớp
        JLabel lblClass = new JLabel("Lớp:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblClass, gbc);
        
        cboClass = new JComboBox<>(new String[]{"10A1", "10A2", "10A3", "11A1", "11A2", "12A1"});
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(cboClass, gbc);
        
        // Dân tộc
        JLabel lblEthnicity = new JLabel("Dân tộc:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(lblEthnicity, gbc);
        
        cboEthnicity = new JComboBox<>(new String[]{"Kinh", "Tày", "Thái", "Mường", "Khmer", "Hoa", "Nùng", "Khác"});
        gbc.gridx = 3;
        gbc.gridy = 1;
        formPanel.add(cboEthnicity, gbc);
        
        // Tôn giáo
        JLabel lblReligion = new JLabel("Tôn giáo:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(lblReligion, gbc);
        
        cboReligion = new JComboBox<>(new String[]{"Không", "Phật giáo", "Công giáo", "Cao đài", "Hòa hảo", "Khác"});
        gbc.gridx = 3;
        gbc.gridy = 2;
        formPanel.add(cboReligion, gbc);
        
        // Địa chỉ
        JLabel lblAddress = new JLabel("Địa chỉ:");
        gbc.gridx = 2;
        gbc.gridy = 3;
        formPanel.add(lblAddress, gbc);
        
        txtAddress = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 3;
        formPanel.add(txtAddress, gbc);
        
        // Điện thoại
        JLabel lblPhone = new JLabel("Điện thoại:");
        gbc.gridx = 2;
        gbc.gridy = 4;
        formPanel.add(lblPhone, gbc);
        
        txtPhone = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 4;
        formPanel.add(txtPhone, gbc);
        
        // Email
        JLabel lblEmail = new JLabel("Email:");
        gbc.gridx = 2;
        gbc.gridy = 5;
        formPanel.add(lblEmail, gbc);
        
        txtEmail = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 5;
        formPanel.add(txtEmail, gbc);
        
        // Thông tin phụ huynh
        JLabel lblParentInfo = new JLabel("Thông tin phụ huynh");
        lblParentInfo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        formPanel.add(lblParentInfo, gbc);
        
        // Tên phụ huynh
        JLabel lblParentName = new JLabel("Họ tên phụ huynh:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        formPanel.add(lblParentName, gbc);
        
        txtParentName = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(txtParentName, gbc);
        
        // SĐT phụ huynh
        JLabel lblParentPhone = new JLabel("Điện thoại phụ huynh:");
        gbc.gridx = 2;
        gbc.gridy = 7;
        formPanel.add(lblParentPhone, gbc);
        
        txtParentPhone = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 7;
        formPanel.add(txtParentPhone, gbc);
        
        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnSave = new JButton("Lưu");
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBackground(new Color(0, 153, 51));
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(new Dimension(100, 40));
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setBackground(new Color(204, 0, 0));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setPreferredSize(new Dimension(100, 40));
        
        buttonPanel.add(btnSave);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(btnCancel);
        
        // Thêm sự kiện
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra dữ liệu
                if (txtFullName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Form.this, 
                            "Vui lòng nhập họ tên học sinh!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Lưu dữ liệu
                JOptionPane.showMessageDialog(Form.this, 
                        "Lưu thông tin học sinh thành công!", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        // Thêm các panel vào panel chính
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(formPanel), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Thêm panel chính vào frame
        add(mainPanel);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Form().setVisible(true);
            }
        });
    }
}