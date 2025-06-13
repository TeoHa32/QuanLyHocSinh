package GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GradePanel extends JPanel {
    
    private JTable gradeTable;
    private JTextField txtSearch;
    
    public GradePanel() {
        setLayout(new BorderLayout(30, 30));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Panel tiêu đề
        JPanel titlePanel = createTitlePanel();
        
        // Panel tìm kiếm và lọc
        JPanel searchPanel = createSearchPanel();
        
        // Panel bảng dữ liệu
        JPanel tablePanel = createTablePanel();
        
        // Panel phân trang và nút chức năng
        JPanel bottomPanel = createBottomPanel();
        
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel lblTitle = new JLabel("Quản lý điểm số");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        
        panel.add(lblTitle, BorderLayout.WEST);
        return panel;
    }
    
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel tìm kiếm
        JPanel searchBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchBox.setBackground(Color.WHITE);
        
        txtSearch = new JTextField(25);
        txtSearch.setPreferredSize(new Dimension(200, 35));
        
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.setPreferredSize(new Dimension(100, 35));
        btnSearch.setBackground(new Color(0, 102, 204));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFocusPainted(false);
        
        searchBox.add(new JLabel("Tìm kiếm"));
        searchBox.add(txtSearch);
        searchBox.add(btnSearch);
        
        // Panel lọc
        JPanel filterBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        filterBox.setBackground(Color.WHITE);
        
        String[] subjects = {"Môn", "Toán", "Lý", "Hóa", "Văn", "Anh"};
        String[] classes = {"Tất cả lớp", "10A1", "10A2", "11A1", "11A2"};
        String[] semesters = {"Học kỳ 1", "Học kỳ 2"};
        
        JComboBox<String> cboSubject = new JComboBox<>(subjects);
        JComboBox<String> cboClass = new JComboBox<>(classes);
        JComboBox<String> cboSemester = new JComboBox<>(semesters);
        
        for (JComboBox<String> cbo : new JComboBox[]{cboSubject, cboClass, cboSemester}) {
            cbo.setPreferredSize(new Dimension(130, 35));
        }
        
        filterBox.add(new JLabel("Môn học:"));
        filterBox.add(cboSubject);
        filterBox.add(Box.createHorizontalStrut(10));
        filterBox.add(new JLabel("Lớp:"));
        filterBox.add(cboClass);
        filterBox.add(Box.createHorizontalStrut(10));
        filterBox.add(new JLabel("Học kỳ:"));
        filterBox.add(cboSemester);
        
        panel.add(searchBox, BorderLayout.WEST);
        panel.add(filterBox, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Tạo bảng với các cột
        String[] columns = {
            "Mã HS", "Họ tên", "Lớp", "Môn học", 
            "Điểm miệng", "Điểm 15p", "Điểm 1 tiết", "Điểm cuối kỳ", "Điểm TB",
            "Học lực", "Học kỳ", "Năm học, "
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        gradeTable = new JTable(model);
        
        // Tùy chỉnh bảng
        gradeTable.setRowHeight(35);
        gradeTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
        gradeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        gradeTable.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Thêm dữ liệu mẫu
        Object[][] sampleData = {
            {"HS001", "Nguyễn Văn A", "10A1", "Toán", "8.5", "9.0", "8.0", "8.5", "8.5", "Giỏi", "1", "2023-2024"},
            {"HS002", "Trần Thị B", "10A1", "Toán", "7.5", "8.0", "8.5", "7.5", "7.8", "Khá","1", "2023-2024"},
            {"HS003", "Lê Văn C", "10A2", "Văn", "9.0", "8.5", "9.0", "8.0", "8.5", "Giỏi", "1", "2023-2024"},
            {"HS004", "Phạm Thị D", "10A2", "Anh", "8.0", "8.5", "7.5", "8.0", "8.0", "Giỏi", "1", "2023-2024"},
            {"HS005", "Hoàng Văn E", "10A1", "Lý", "7.0", "7.5", "8.0", "7.5", "7.5", "Khá", "1", "2023-2024"}
        };
        
        for (Object[] row : sampleData) {
            model.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(gradeTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Panel nút chức năng
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setBackground(Color.WHITE);
        
        // Tạo các button với icon
        JButton btnAdd = createIconButton("Nhập điểm", "add.png", new Color(0, 153, 51));
        JButton btnView = createIconButton("Xem chi tiết", "see.png", new Color(51, 153, 255));
        JButton btnEdit = createIconButton("Sửa điểm", "updates.png", new Color(255, 153, 51));
        JButton btnDelete = createIconButton("Xóa", "delete.png", new Color(255, 51, 51));
        JButton btnImport = createIconButton("Nhập Excel", "import.png", new Color(102, 51, 153));
        JButton btnExport = createIconButton("Xuất Excel", "export.png", new Color(51, 102, 153));
        
        // Thêm các button vào panel
        actionPanel.add(btnAdd);
        actionPanel.add(btnView);
        actionPanel.add(btnEdit);
        actionPanel.add(btnDelete);
        actionPanel.add(btnImport);
        actionPanel.add(btnExport);
        
        panel.add(actionPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    // Thêm phương thức hỗ trợ tạo button với icon
    private JButton createIconButton(String text, String iconName, Color bgColor) {
        JButton button = new JButton(text);
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/" + iconName));
            Image img = originalIcon.getImage();
            Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newImg);
            
            button.setIcon(icon);
            button.setHorizontalTextPosition(JButton.RIGHT);
            button.setIconTextGap(10);
        } catch (Exception e) {
            System.out.println("Không thể tải icon: " + iconName);
        }
        
        button.setPreferredSize(new Dimension(150, 35));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setMargin(new Insets(0, 10, 0, 10));
        
        return button;
    }
} 