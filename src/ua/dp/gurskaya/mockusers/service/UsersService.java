package ua.dp.gurskaya.mockusers.service;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ua.dp.gurskaya.mockusers.dao.UsersDao;
import ua.dp.gurskaya.mockusers.entity.Users;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UsersService {

    private File createUsersFile(String extension) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH mm ss");
        String time = dateFormat.format(currentDate);
        File usersFile = new File("resources" + File.separator + "Users " + time + "." + extension);
        try {
            usersFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersFile;
    }

    public void writeToTxt(int numberOfUsers) {
        UsersDao usersDao = new UsersDao();
        List<Users> users = usersDao.generateUsers(numberOfUsers);
        File file = createUsersFile("txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Users user : users) {
                bufferedWriter.write(String.valueOf(user));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToXls(int numberOfUsers) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Mock Users");

        int rowNumber = 0;
        Row row = sheet.createRow(rowNumber);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Gender");
        row.createCell(3).setCellValue("Age");
        row.createCell(4).setCellValue("Driving License");

        UsersDao usersDao = new UsersDao();
        List<Users> users = usersDao.generateUsers(numberOfUsers);

        for (Users user : users) {
            fillColumns(sheet, ++rowNumber, user);
        }
        File file = createUsersFile("xls");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillColumns(HSSFSheet sheet, int rowNumber, Users users) {
        Row row = sheet.createRow(rowNumber);
        row.createCell(0).setCellValue(users.getId());
        row.createCell(1).setCellValue(users.getName());
        row.createCell(2).setCellValue(users.getGender());
        row.createCell(3).setCellValue(users.getAge());
        row.createCell(4).setCellValue(users.hasDrivingLicense());
    }
}