package GUI;


import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel contentPanel;
    private JPanel menuPanel;
    private CardLayout cardLayout;
    
    public Dashboard() {
        initializeUI();
        setupMenu();
    }
    
    private void initializeUI() {
        setTitle("Hệ thống Quản lý Học sinh");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Lấy kích thước màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.width * 0.9); // 90% chiều rộng màn hình
        int height = (int)(screenSize.height * 0.9); // 90% chiều cao màn hình
        setSize(width, height);
        setMinimumSize(new Dimension(1280, 800)); // Kích thước tối thiểu
        setLocationRelativeTo(null);
        
        // Panel tiêu đề chứa logo, text và user info
        JPanel titlePanel = new JPanel(new BorderLayout(10, 5));
        titlePanel.setBackground(new Color(0, 102, 204));
        titlePanel.setPreferredSize(new Dimension(0, 70));
        
        // Panel bên trái chứa logo và text
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leftPanel.setBackground(new Color(0, 102, 204));
        
        // Thêm logo
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/logo.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(-1, 60, Image.SCALE_SMOOTH);
            JLabel lblLogo = new JLabel(new ImageIcon(scaledImage));
            leftPanel.add(lblLogo);
        } catch (Exception e) {
            System.err.println("Không thể tải logo: " + e.getMessage());
        }
        
        // Thêm text tiêu đề
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ HỌC SINH");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        leftPanel.add(lblTitle);
        
        // Panel bên phải chứa thông tin user và nút đăng xuất
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        userPanel.setBackground(new Color(0, 102, 204));
        
        // Thêm avatar user
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/User.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel lblAvatar = new JLabel(new ImageIcon(scaledImage));
            userPanel.add(lblAvatar);
        } catch (Exception e) {
            System.err.println("Không thể tải avatar: " + e.getMessage());
        }
        
        // Thêm thông tin user
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(new Color(0, 102, 204));
        
        JLabel lblUserName = new JLabel("Admin");
        lblUserName.setFont(new Font("Arial", Font.BOLD, 14));
        lblUserName.setForeground(Color.WHITE);
        
        JLabel lblRole = new JLabel("Quản trị viên");
        lblRole.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRole.setForeground(Color.WHITE);
        
        userInfoPanel.add(lblUserName);
        userInfoPanel.add(lblRole);
        
        // Nút đăng xuất
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 14));
        btnLogout.setBackground(new Color(255, 255, 255));
        btnLogout.setForeground(new Color(0, 102, 204));
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(e -> handleLogout());
        
        userPanel.add(userInfoPanel);
        userPanel.add(btnLogout);
        
        // Thêm vào titlePanel
        titlePanel.add(leftPanel, BorderLayout.WEST);
        titlePanel.add(userPanel, BorderLayout.EAST);
        
        // Panel chính sử dụng CardLayout
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        
        // Panel menu bên trái
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(300, 0)); // Tăng chiều rộng menu
        menuPanel.setBackground(new Color(51, 51, 51));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        
        // Panel chứa nội dung chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Thêm vào frame
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupMenu() {
        // Thêm các menu item
        addMenuItem("Trang chủ", "home", new DashboardPanel());
        addMenuItem("Quản lý học sinh", "students", new StudentPanel());
        addMenuItem("Quản lý giáo viên", "teachers", new TeacherPanel());
        addMenuItem("Quản lý lớp học", "classes", new ClassPanel());
        addMenuItem("Quản lý điểm", "grades", new GradePanel());
        addMenuItem("Báo cáo thống kê", "reports", new ReportPanel());
    }
    
    private void addMenuItem(String text, String name, JPanel panel) {
        JButton menuButton = new JButton(text);
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(new Color(51, 51, 51));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuButton.setMaximumSize(new Dimension(300, 50)); // Tăng kích thước nút
        menuButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng font chữ
        
        menuButton.addActionListener(e -> {
            cardLayout.show(contentPanel, name);
            // Đổi màu nút được chọn
            for (Component c : menuPanel.getComponents()) {
                if (c instanceof JButton) {
                    c.setBackground(new Color(51, 51, 51));
                }
            }
            menuButton.setBackground(new Color(0, 102, 204));
        });
        
        // Thêm padding cho nút
        menuButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        menuPanel.add(menuButton);
        menuPanel.add(Box.createVerticalStrut(5)); // Thêm khoảng cách giữa các nút
        contentPanel.add(panel, name);
        
        // Hiển thị trang chủ mặc định
        if (name.equals("home")) {
            cardLayout.show(contentPanel, name);
            menuButton.setBackground(new Color(0, 102, 204));
        }
    }
    
    // Thêm phương thức xử lý đăng xuất
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn đăng xuất?",
            "Xác nhận đăng xuất",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Đóng cửa sổ hiện tại
            this.dispose();
            // Hiển thị màn hình đăng nhập
            // LoginForm loginForm = new LoginForm();
            // loginForm.setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
} 