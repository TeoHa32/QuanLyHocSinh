package GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportPanel extends JPanel {
    
    private JTable reportTable;
    private JComboBox<String> cboNamHoc;
    private JComboBox<String> cboHocKy;
    private JComboBox<String> cboLoaiBaoCao;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public ReportPanel() {
        setLayout(new BorderLayout(30, 30));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Panel tiêu đề
        JPanel titlePanel = createTitlePanel();
        
        // Panel lọc
        JPanel filterPanel = createFilterPanel();
        
        // Panel nội dung chính
        JPanel contentPanel = createContentPanel();
        
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(filterPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel lblTitle = new JLabel("Báo cáo thống kê");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        
        panel.add(lblTitle, BorderLayout.WEST);
        return panel;
    }
    
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        String[] semesters = {"Học kỳ 1", "Học kỳ 2"};
        String[] years = {"2023-2024", "2022-2023", "2021-2022"};
        
        JComboBox<String> cboSemester = new JComboBox<>(semesters);
        JComboBox<String> cboYear = new JComboBox<>(years);

        // Tạo các button với icon và kích thước lớn hơn
        JButton btnView = createIconButton("Xem báo cáo", "see.png");
        JButton btnExport = createIconButton("Xuất báo cáo", "export.png");
        
        // Tùy chỉnh style cho combobox
        for (JComboBox<String> cbo : new JComboBox[]{cboSemester, cboYear}) {
            cbo.setPreferredSize(new Dimension(150, 35));
            cbo.setForeground(Color.BLACK);
            cbo.setBackground(Color.WHITE);
            cbo.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        
        // Tùy chỉnh style cho label
        JLabel lblSemester = new JLabel("Học kỳ:");
        JLabel lblYear = new JLabel("Năm học:");
        for (JLabel lbl : new JLabel[]{lblSemester, lblYear}) {
            lbl.setFont(new Font("Arial", Font.PLAIN, 14));
            lbl.setForeground(Color.BLACK);
        }
        
        panel.add(lblSemester);
        panel.add(cboSemester);
        panel.add(lblYear);
        panel.add(cboYear);
        panel.add(btnView);
        panel.add(btnExport);
        
        return panel;
    }
    
    // Phương thức hỗ trợ tạo button với icon
    private JButton createIconButton(String text, String iconName) {
        JButton button = new JButton(text);
        try {
            // Load và resize icon
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
        
        // Tăng kích thước button
        button.setPreferredSize(new Dimension(150, 35));  // Tăng từ 120 lên 150
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Thêm padding cho button
        button.setMargin(new Insets(0, 10, 0, 10));
        
        return button;
    }
    
    private JPanel createContentPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBackground(Color.WHITE);
        
        // Thống kê học lực
        panel.add(createReportCard("Thống kê học lực", new String[]{
            "Giỏi: 25%",
            "Khá: 45%",
            "Trung bình: 20%",
            "Yếu: 10%"
        }, new Color(0, 102, 204)));
        
        // Thống kê hạnh kiểm
        panel.add(createReportCard("Thống kê hạnh kiểm", new String[]{
            "Tốt: 60%",
            "Khá: 30%",
            "Trung bình: 8%",
            "Yếu: 2%"
        }, new Color(0, 153, 51)));
        
        // Thống kê điểm trung bình môn
        panel.add(createReportCard("Điểm trung bình các môn", new String[]{
            "Toán: 7.5",
            "Văn: 7.2",
            "Anh: 7.8",
            "Lý: 7.0",
            "Hóa: 7.3"
        }, new Color(204, 102, 0)));
        
        return panel;
    }
    
    private JPanel createReportCard(String title, String[] items, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(color);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitle);
        
        panel.add(Box.createVerticalStrut(15));
        
        // Panel cho dữ liệu
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setBackground(Color.WHITE);
        dataPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Thêm các mục thống kê
        for (String item : items) {
            JLabel lblItem = new JLabel(item);
            lblItem.setFont(new Font("Arial", Font.PLAIN, 14));
            lblItem.setAlignmentX(Component.LEFT_ALIGNMENT);
            dataPanel.add(lblItem);
            dataPanel.add(Box.createVerticalStrut(10));
        }
        
        // Tạo biểu đồ tương ứng và lưu vào biến final
        final ChartPanel finalChartPanel;
        switch (title) {
            case "Thống kê học lực":
                finalChartPanel = createHocLucBarChart();
                break;
            case "Thống kê hạnh kiểm":
                finalChartPanel = createHanhKiemBarChart();
                break;
            case "Điểm trung bình các môn":
                finalChartPanel = createDiemTBBarChart();
                break;
            default:
                finalChartPanel = null;
        }
        
        // Thêm sự kiện click để mở dialog chi tiết
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetailDialog(title, finalChartPanel);
            }
        });
        
        panel.add(dataPanel);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return panel;
    }
    
    private void showDetailDialog(String title, ChartPanel chartPanel) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), title, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(null);
        
        // Tạo bản sao của biểu đồ với kích thước lớn hơn
        ChartPanel largeChartPanel = new ChartPanel(chartPanel.getChart());
        largeChartPanel.setPreferredSize(new Dimension(750, 500));
        
        dialog.add(largeChartPanel, BorderLayout.CENTER);
        
        // Panel nút với icon và kích thước lớn hơn
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));  // Tăng khoảng cách giữa các button
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnExport = createIconButton("Xuất báo cáo", "export.png");
        JButton btnClose = createIconButton("Đóng", "delete.png");
        
        buttonPanel.add(btnExport);
        buttonPanel.add(btnClose);
        
        btnClose.addActionListener(e -> dialog.dispose());
        btnExport.addActionListener(e -> {
            // Xử lý xuất báo cáo
        });
        
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private ChartPanel createHocLucBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(25, "Học lực", "Giỏi");
        dataset.addValue(45, "Học lực", "Khá");
        dataset.addValue(20, "Học lực", "Trung bình");
        dataset.addValue(10, "Học lực", "Yếu");
        
        JFreeChart chart = ChartFactory.createBarChart(
            "Phân bố học lực",
            "Xếp loại",
            "Phần trăm (%)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        customizeBarChart(chart, new Color(0, 102, 204));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 200));
        return chartPanel;
    }
    
    private ChartPanel createHanhKiemBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(60, "Hạnh kiểm", "Tốt");
        dataset.addValue(30, "Hạnh kiểm", "Khá");
        dataset.addValue(8, "Hạnh kiểm", "Trung bình");
        dataset.addValue(2, "Hạnh kiểm", "Yếu");
        
        JFreeChart chart = ChartFactory.createBarChart(
            "Phân bố hạnh kiểm",
            "Xếp loại",
            "Phần trăm (%)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        customizeBarChart(chart, new Color(0, 153, 51));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 200));
        return chartPanel;
    }
    
    private ChartPanel createDiemTBBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(7.5, "Điểm TB", "Toán");
        dataset.addValue(7.2, "Điểm TB", "Văn");
        dataset.addValue(7.8, "Điểm TB", "Anh");
        dataset.addValue(7.0, "Điểm TB", "Lý");
        dataset.addValue(7.3, "Điểm TB", "Hóa");
        
        JFreeChart chart = ChartFactory.createBarChart(
            "Điểm trung bình các môn",
            "Môn học",
            "Điểm trung bình",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        customizeBarChart(chart, new Color(204, 102, 0));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 200));
        return chartPanel;
    }
    
    // Phương thức hỗ trợ tùy chỉnh style cho biểu đồ cột
    private void customizeBarChart(JFreeChart chart, Color color) {
        chart.setBackgroundPaint(Color.WHITE);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, color);
        renderer.setDrawBarOutline(true);
        renderer.setItemMargin(0.1);
        
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));
    }
    
    private JPanel createTongKetPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Tạo bảng tổng kết
        String[] columns = {
            "Lớp", "Sĩ số", "Số lượng đạt", "Tỷ lệ đạt", 
            "Học sinh giỏi", "Học sinh khá", "Học sinh TB", "Học sinh yếu"
        };
        
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        // Thêm dữ liệu mẫu
        Object[][] sampleData = {
            {"10A1", 45, 43, "95.6%", 15, 20, 8, 2},
            {"10A2", 42, 40, "95.2%", 12, 18, 10, 2},
            {"11A1", 40, 38, "95.0%", 10, 20, 8, 2},
            {"11A2", 43, 41, "95.3%", 13, 19, 9, 2},
            {"12A1", 38, 37, "97.4%", 14, 16, 7, 1}
        };
        
        for (Object[] row : sampleData) {
            model.addRow(row);
        }
        
        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        // Tạo biểu đồ
        JPanel chartsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        chartsPanel.setBackground(Color.WHITE);
        chartsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Biểu đồ phân bố học lực
        ChartPanel hocLucChart = createHocLucChart(sampleData);
        
        // Biểu đồ tỷ lệ đạt
        ChartPanel tyLeChart = createTyLeChart(sampleData);
        
        chartsPanel.add(hocLucChart);
        chartsPanel.add(tyLeChart);
        
        // Panel chính
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(chartsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private ChartPanel createHocLucChart(Object[][] data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Thêm dữ liệu cho biểu đồ
        for (Object[] row : data) {
            String lop = (String) row[0];
            dataset.addValue((Number) row[4], "Giỏi", lop);
            dataset.addValue((Number) row[5], "Khá", lop);
            dataset.addValue((Number) row[6], "Trung bình", lop);
            dataset.addValue((Number) row[7], "Yếu", lop);
        }
        
        JFreeChart chart = ChartFactory.createBarChart(
            "Phân bố học lực theo lớp", // Tiêu đề
            "Lớp",                      // Label trục x
            "Số lượng học sinh",        // Label trục y
            dataset,                    // Dataset
            PlotOrientation.VERTICAL,   // Orientation
            true,                       // Legend
            true,                       // Tooltips
            false                       // URLs
        );
        
        // Tùy chỉnh style
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        return chartPanel;
    }
    
    private ChartPanel createTyLeChart(Object[][] data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Thêm dữ liệu cho biểu đồ
        for (Object[] row : data) {
            String lop = (String) row[0];
            int siSo = (int) row[1];
            int dat = (int) row[2];
            int khongDat = siSo - dat;
            
            dataset.addValue(dat, "Đạt", lop);
            dataset.addValue(khongDat, "Không đạt", lop);
        }
        
        JFreeChart chart = ChartFactory.createBarChart(
            "Tỷ lệ đạt theo lớp",      // Tiêu đề
            "Lớp",                      // Label trục x
            "Số lượng học sinh",        // Label trục y
            dataset,                    // Dataset
            PlotOrientation.VERTICAL,   // Orientation
            true,                       // Legend
            true,                       // Tooltips
            false                       // URLs
        );
        
        // Tùy chỉnh style
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        return chartPanel;
    }
    
    private JPanel createHocLucPanel() {
        // Tương tự như createTongKetPanel nhưng với nội dung khác
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Thống kê học lực", SwingConstants.CENTER));
        return panel;
    }
    
    private JPanel createHanhKiemPanel() {
        // Tương tự như createTongKetPanel nhưng với nội dung khác
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Thống kê hạnh kiểm", SwingConstants.CENTER));
        return panel;
    }
    
    private JPanel createDiemMonPanel() {
        // Tương tự như createTongKetPanel nhưng với nội dung khác
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Thống kê điểm trung bình môn", SwingConstants.CENTER));
        return panel;
    }
} 