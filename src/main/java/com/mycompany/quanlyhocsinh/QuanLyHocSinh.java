/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlyhocsinh;

import GUI.Login;
import java.sql.Connection;
import DTO.connection;
import GUI.*;
import GUI.StudentPanel;
import javax.swing.JFrame;
/**
 *
 * @author dinhh
 */
public class QuanLyHocSinh {
    public static void main(String[] args) {
//        new StudentPanel().setVisible(true);
        JFrame frame = new JFrame("Ứng dụng quản lý học sinh");
        // Gắn panel vào frame
        frame.setContentPane(new ClassPanel());
        // Cài đặt kích thước, đóng cửa sổ, hiển thị
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
