package ua.dp.gurskaya.mockusers.controller;

import ua.dp.gurskaya.mockusers.service.UsersService;

import java.util.Scanner;

public class UserController {
    public static void main(String[] args) {
        UsersService usersService = new UsersService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей");
        int input = scanner.nextInt();
        System.out.println("С каким расширением создать файл: Введите 1 (если txt) или 2 (если xls)");
        int result = scanner.nextInt();
        if (result == 1) {
            usersService.writeToTxt(input);
        } else {
            usersService.writeToXls(input);
        }
    }
}