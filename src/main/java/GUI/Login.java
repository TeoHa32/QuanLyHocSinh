package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import GUI.DashboardPanel;
import GUI.Dashboard;

public class Login extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnForgotPassword;
    
    public Login() {
        // Thiết lập cơ bản cho frame
        setTitle("Hệ Thống Quản Lý Học Sinh");
        setSize(1000, 600); // Tăng kích thước frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Panel chính sử dụng BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Panel bên trái chứa form đăng nhập
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(400, 0)); // Chiều rộng cố định
        
        // Logo và tiêu đề
        JLabel lblLogo = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/logo.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Không thể tải logo: " + e.getMessage());
        }
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(new Color(0, 102, 204));
        
        // Panel đăng nhập
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 1, 10, 15));
        loginPanel.setMaximumSize(new Dimension(300, 200));
        loginPanel.setBackground(Color.WHITE);
        
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        loginPanel.add(lblUsername);
        loginPanel.add(txtUsername);
        loginPanel.add(lblPassword);
        loginPanel.add(txtPassword);
        
        // Panel nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        btnLogin = new JButton("Đăng Nhập");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(new EmptyBorder(10, 30, 10, 30));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnForgotPassword = new JButton("Quên Mật Khẩu?");
        btnForgotPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        btnForgotPassword.setBorderPainted(false);
        btnForgotPassword.setContentAreaFilled(false);
        btnForgotPassword.setForeground(new Color(0, 102, 204));
        btnForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        buttonPanel.add(btnLogin);
        
        // Panel bên phải chứa hình nền
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = new ImageIcon(getClass().getResource("/Login.jpg")).getImage();
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (Exception e) {
                    System.err.println("Không thể tải hình nền: " + e.getMessage());
                }
            }
        };
        
        // Thêm các thành phần vào panel trái
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(lblLogo);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(lblTitle);
        leftPanel.add(Box.createVerticalStrut(40));
        leftPanel.add(loginPanel);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(buttonPanel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(btnForgotPassword);
        btnForgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Thêm các panel vào panel chính
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        
        // Thêm panel chính vào frame
        add(mainPanel);
        
        // Thêm sự kiện
        initializeEvents();
    }
    
    private void initializeEvents() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra đăng nhập
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                
                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(Login.this, "Đăng nhập thành công!");
                    
                    // Tạo và hiển thị Dashboard
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Dashboard dashboard = new Dashboard();
                                dashboard.setVisible(true);
                                dispose(); // Đóng cửa sổ login
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(
                                    Login.this,
                                    "Lỗi khi mở Dashboard: " + ex.getMessage(),
                                    "Lỗi",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(Login.this, 
                            "Tên đăng nhập hoặc mật khẩu không đúng!", 
                            "Lỗi đăng nhập", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnForgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Login.this, 
                        "Vui lòng liên hệ quản trị viên để lấy lại mật khẩu.", 
                        "Quên mật khẩu", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    public static void main(String[] args) {
        try {
            // Sử dụng look and feel hiện đại
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}