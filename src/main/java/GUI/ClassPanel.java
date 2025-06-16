package GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassPanel extends JPanel {
    
    private JTable classTable;
    private JTextField txtSearch;
    
    public ClassPanel() {
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
        
        JLabel lblTitle = new JLabel("Quản lý lớp học");
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
        
        searchBox.add(new JLabel("Tìm kiếm:"));
        searchBox.add(txtSearch);
        searchBox.add(btnSearch);
        
        // Panel lọc
        JPanel filterBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        filterBox.setBackground(Color.WHITE);
        
        JComboBox<String> cboGrade = new JComboBox<>(new String[]{
            "Tất cả khối", "Khối 10", "Khối 11", "Khối 12"
        });
        cboGrade.setPreferredSize(new Dimension(150, 35));
        
        filterBox.add(new JLabel("Khối:"));
        filterBox.add(cboGrade);
        
        panel.add(searchBox, BorderLayout.WEST);
        panel.add(filterBox, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Tạo bảng với các cột
        String[] columns = {
            "Mã lớp", "Tên lớp", "Khối", "Sĩ số", 
            "Giáo viên chủ nhiệm", "Phòng học", "Năm học"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        classTable = new JTable(model);
        
        // Tùy chỉnh bảng
        classTable.setRowHeight(35);
        classTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
        classTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        classTable.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Thêm dữ liệu mẫu
        Object[][] sampleData = {
            {"L001", "10A1", "10", "45", "Nguyễn Văn A", "P.201", "2023-2024"},
            {"L002", "10A2", "10", "42", "Trần Thị B", "P.202", "2023-2024"},
            {"L003", "11A1", "11", "40", "Phạm Thị D", "P.301", "2023-2024"},
            {"L004", "11A2", "11", "43", "Lê Văn E", "P.302", "2023-2024"},
            {"L005", "12A1", "12", "38", "Hoàng Văn F", "P.401", "2023-2024"}
        };
        
        for (Object[] row : sampleData) {
            model.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(classTable);
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
        JButton btnAdd = createIconButton("Thêm lớp mới", "add.png", new Color(0, 153, 51));
        JButton btnView = createIconButton("Xem chi tiết", "see.png", new Color(51, 153, 255));
        JButton btnEdit = createIconButton("Sửa", "updates.png", new Color(255, 153, 51));
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
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new addClass().setVisible(true);
            }
          
            
                
        });
        btnEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 new addClass().setVisible(true);
            }
            
            
            
        });
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