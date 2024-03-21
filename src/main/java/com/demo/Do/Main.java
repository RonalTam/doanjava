package com.demo.Do;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;import org.apache.poi.ss.usermodel.Row;


import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;


@SuppressWarnings("unused")
public class Main {
	public static List<SanPham> sp = new ArrayList<>();

	public static void themSanPham(List<SanPham> sp) {
		Scanner sc = new Scanner(System.in);
		System.out.print("(?) Nhập số sản phẩm muốn thêm: ");
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			System.out.print("(?) Nhập mã sản phẩm: ");
			String maSP = sc.nextLine();
			System.out.print("(?) Nhập tên sản phẩm: ");
			String name = sc.nextLine();
			System.out.print("(?) Nhập số lượng sản phẩm: ");
			int soLuong = Integer.parseInt(sc.nextLine());
			System.out.print("(?) Nhập giá của sản phẩm: ");
			double gia = Double.parseDouble(sc.nextLine());

			SanPham a = new SanPham(maSP, name, soLuong, gia);
			sp.add(a);

		}
	}
	

	public static void ExcelWriter (List<SanPham> sp) {
//	    public static void main(String[] args) {
//	        // Tạo mảng sản phẩm
//	        SanPham[] products = {
//	                new SanPham("SP001", "Sản phẩm 1", 10, 100),
//	                new SanPham("SP002", "Sản phẩm 2", 20, 200),
//	                new SanPham("SP003", "Sản phẩm 3", 30, 300)
//	        };

	        // Tạo workbook mới
	        try (Workbook workbook = new XSSFWorkbook()) {
	            // Tạo một trang tính mới
	            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Products");

	            // Tạo tiêu đề cho các cột
	            Row headerRow = sheet.createRow(0);
	            String[] columns = {"Mã SP", "Tên sản phẩm", "Số lượng", "Giá"};
	            for (int i = 0; i < columns.length; i++) {
	                Cell cell = headerRow.createCell(i);
	                cell.setCellValue(columns[i]);
	            }

	            // Ghi thông tin sản phẩm vào các dòng tiếp theo
	            for (int i = 0; i < sp.size(); i++) {
	                Row row = sheet.createRow(i + 1);
	                row.createCell(0).setCellValue(sp.get(i).maSP);
	                row.createCell(1).setCellValue(sp.get(i).name);
	                row.createCell(2).setCellValue(sp.get(i).soLuong);
	                row.createCell(3).setCellValue(sp.get(i).gia);
	            }

	            // Lưu workbook vào file Excel
	            try (FileOutputStream fileOut = new FileOutputStream("D:/products1.xlsx")) {
	                workbook.write(fileOut);
	                System.out.println("File Excel đã được tạo thành công!");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public static List<SanPham> readDataFromExcel(String path) {
        List<SanPham> sanPhamList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            // Lấy số lượng sheet trong Workbook
            System.out.println("Đã lấy dữ liệu từ file!");
            for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
                int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
     
                for (int i = 1; i <= rowCount; i++) {
                    Row row = sheet.getRow(i);
                    String maSP = row.getCell(0).getStringCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    int soLuong = (int) row.getCell(2).getNumericCellValue();
                    double gia = row.getCell(3).getNumericCellValue();

                 
                    SanPham sanPham = new SanPham(maSP, name, soLuong, gia);
                    sanPhamList.add(sanPham);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }

	public static void hienThiSP(List<SanPham> sp) {
		if (sp == null || sp.isEmpty()) {
			System.out.println("(!) Chưa có sản phẩm. Vui lòng nhập sản phẩm.");
			System.out.println("");
		} else {
			System.out.println("");
			System.out.println("+--------------- Danh Sách Sản Phẩm --------------+ ");
			System.out.printf("|%s|%-20s|%-10s|%-10s|\n", " Mã SP", " Tên sản phẩm", " Giá", " Số lượng");
			System.out.printf("+------+--------------------+----------+----------+\n");
			for (SanPham sanPham : sp) {
				System.out.printf("|%-6s|%-20s|%-10s|%10s|\n", sanPham.maSP, sanPham.name, sanPham.gia,
						sanPham.soLuong);
			}
			System.out.printf("+------+--------------------+----------+----------+\n");
			System.out.println("");
		}
	}

	public static void xoaSP(List<SanPham> sp) {
		Scanner sc = new Scanner(System.in);
		if (sp == null || sp.isEmpty()) {
			System.out.println("(!) Chưa có sản phẩm. Vui lòng nhập sản phẩm.");
			System.out.println("");
		} else {
			System.out.print("(?) Nhập mã sản phẩm cần xóa: ");
			String id = sc.nextLine();

			List<SanPham> spPhu = new ArrayList<>();
			boolean check = false;
			for (SanPham sanPham : sp) {
				if (sanPham.maSP.equals(id)) {
					spPhu.add(sanPham);
					check = true;
				}
			}

			if (!check) {
				System.out.println("(!) Sản phẩm mã '" + id + "' không tồn tại.");
				System.out.println("");
			} else {
				sp.removeAll(spPhu);
				System.out.println("(!) Xóa sản phẩm thành công.");
				System.out.println("");
			}
		}
	}

	public static void suaSP(List<SanPham> sp) {
		Scanner sc = new Scanner(System.in);

		if (sp == null || sp.isEmpty()) {
			System.out.println("(!) Chưa có sản phẩm. Vui lòng nhập sản phẩm.");
			System.out.println("");
		} else {
			System.out.print("(?) Nhập mã của sản phẩm cần chỉnh sửa: ");
			String id = sc.nextLine();

			boolean check = false;
			for (SanPham sanPham : sp) {
				if (sanPham.maSP.equals(id)) {
					System.out.print("(?) Nhập mã sản phẩm: ");
					sanPham.maSP = sc.nextLine();
					System.out.print("(?) Nhập tên sản phẩm: ");
					sanPham.name = sc.nextLine();
					System.out.print("(?) Nhập số lượng sản phẩm: ");
					sanPham.soLuong = Integer.parseInt(sc.nextLine());
					System.out.print("(?) Nhập giá của sản phẩm: ");
					sanPham.gia = Double.parseDouble(sc.nextLine());

					check = true;
				}
			}
			if (!check) {
				System.out.println("(!) Sản phẩm mã '" + id + "' không tồn tại.");
				System.out.println("");
			} else {
				System.out.println("(!) Sửa sản phẩm thành công.");
				System.out.println("");
				hienThiSP(sp);
			}
		}
	}

	public static void timKiem(List<SanPham> sp) {
		Scanner sc = new Scanner(System.in);

		if (sp == null || sp.isEmpty()) {
			System.out.println("(!) Chưa có sản phẩm. Vui lòng nhập sản phẩm.");
			System.out.println("");
		} else {
			System.out.println("(?) Nhập mã sản phẩm cần tìm: ");
			String id = sc.nextLine();

			boolean check = false;

			for (SanPham sanPham : sp) {
				if (sanPham.maSP.equals(id)) {
					check = true;
				}
			}
			if (check == false) {
				System.out.println("(!) Sản phẩm mã '" + id + "' không tồn tại.");
				System.out.println("");
			} else {
				System.out.println("");
				System.out.println("+--------------- Danh Sách Sản Phẩm --------------+ ");
				System.out.printf("|%s|%-20s|%-10s|%-10s|\n", " Mã SP", " Tên sản phẩm", " Giá", " Số lượng");
				System.out.printf("+------+--------------------+----------+----------+\n");
				for (SanPham sanPham : sp) {
					if (sanPham.maSP.equals(id)) {
						System.out.printf("|%-6s|%-20s|%-10s|%10s|\n", sanPham.maSP, sanPham.name, sanPham.gia,
								sanPham.soLuong);
					}
				}
				System.out.printf("+------+--------------------+----------+----------+\n");
				System.out.println("");
			}
		}
	}

	public static void sapXepSP(List<SanPham> sp) {
		if (sp == null || sp.isEmpty()) {
			System.out.println("(!) Chưa có sản phẩm. Vui lòng nhập sản phẩm.");
			System.out.println("");
		} else {
			int n = sp.size();
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n - i - 1; j++) {
					if (sp.get(j).getGia() > sp.get(j + 1).getGia()) {
						SanPham temp = sp.get(j);
						sp.set(j, sp.get(j + 1));
						sp.set(j + 1, temp);
					}
				}
			}
			hienThiSP(sp);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sp = readDataFromExcel("D:/products1.xlsx");
		boolean thoatVongLap = true;

		while (thoatVongLap) {
			System.out.println("+------------- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM -------------+");
			System.out.println("|1. Thêm mặt hàng mới                                     |");
			System.out.println("|2. Hiển thị danh sách sản phẩm                           |");
			System.out.println("|3. Xóa một sản phẩm                                	  |");
			System.out.println("|4. Chỉnh sửa sản phẩm                                    |");
			System.out.println("|5. Tìm kiếm sản phẩm                                     |");
			System.out.println("|6. Sắp xếp lại danh sách sản phẩm                        |");
			System.out.println("|7. Ghi dữ liệu vao file txt                              |");
			System.out.println("|8. Xuất dữ liệu ra file txt                              |");
			System.out.println("|0. Thoát chương trình                                    |");
			System.out.println("+---------------------------------------------------------+");
			System.out.print("(?) Mời chọn chức năng: ");
			int chon = sc.nextInt();

			switch (chon) {
			case 1:
				themSanPham(sp);
				break;
			case 2:
				hienThiSP(sp);
				break;
			case 3:
				xoaSP(sp);
				break;
			case 4:
				suaSP(sp);
				break;
			case 5:
				timKiem(sp);
				break;
			case 6:
				sapXepSP(sp);
				break;
			case 7:
				ExcelWriter(sp);
				break;
			case 8:
				readDataFromExcel("D:/products1.xlsx");
				break;
			case 0:
				thoatVongLap = false;
				break;
			default:
				System.out.println("(!) Lựa chọn không hợp lệ. Vui lòng nhập số từ 0 đến 6!");
				System.out.println("");
			}
		}
	}

}
