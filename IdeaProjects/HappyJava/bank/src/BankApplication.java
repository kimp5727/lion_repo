// BankApplication.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApplication {

    // 고객과 계좌 데이터를 저장할 List (간단한 데이터베이스 역할)
    private static List<Customer> customers = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // 고객 ID와 계좌번호를 자동으로 생성하기 위한 변수
    private static long customerSequence = 0;
    private static int accountSequence = 0;


    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("\n===== MIni Bank System =====");
            System.out.println("1.고객 등록 | 2.계좌 개설 | 3.입금 | 4.출금 | 5.전체 계좌 조회 | 6.종료");
            System.out.println("============================");
            System.out.print("선택> ");

            int selectNo = Integer.parseInt(scanner.nextLine());

            switch (selectNo) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    viewAllAccounts();
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    // 1. 고객 등록
    private static void registerCustomer() {
        System.out.println("\n--- 고객 등록 ---");
        System.out.print("이름: ");
        String name = scanner.nextLine();

        // 고객 ID 자동 생성 (CUS + 숫자)
        String customerId = "CUS" + ++customerSequence;
        Customer newCustomer = new Customer(name, customerId);
        customers.add(newCustomer);

        System.out.println("고객 등록이 완료되었습니다. 고객 ID: " + customerId);
    }

    // 2. 계좌 개설
    private static void createAccount() {
        System.out.println("\n--- 계좌 개설 ---");
        System.out.print("계좌를 개설할 고객 ID를 입력하세요: ");
        String customerId = scanner.nextLine();

        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("해당 ID의 고객이 존재하지 않습니다.");
            return;
        }

        // 계좌번호 자동 발급 (은행코드-일련번호)
        String accountNumber = "110-" + String.format("%03d", ++accountSequence);
        Account newAccount = new Account(accountNumber, customer);
        accounts.add(newAccount);

        System.out.println(customer.getName() + "님의 계좌가 개설되었습니다. 계좌번호: " + accountNumber);
    }

    // 3. 입금
    private static void deposit() {
        System.out.println("\n--- 입금 ---");
        System.out.print("계좌번호: ");
        String accountNumber = scanner.nextLine();
        System.out.print("입금액: ");
        long amount = Long.parseLong(scanner.nextLine());

        Account account = findAccountByNumber(accountNumber);

        if (account == null) {
            System.out.println("계좌를 찾을 수 없습니다.");
            return;
        }
        account.deposit(amount);
    }

    // 4. 출금
    private static void withdraw() {
        System.out.println("\n--- 출금 ---");
        System.out.print("계좌번호: ");
        String accountNumber = scanner.nextLine();
        System.out.print("출금액: ");
        long amount = Long.parseLong(scanner.nextLine());

        Account account = findAccountByNumber(accountNumber);

        if (account == null) {
            System.out.println("계좌를 찾을 수 없습니다.");
            return;
        }
        account.withdraw(amount);
    }

    // 5. 전체 계좌 목록 조회
    private static void viewAllAccounts() {
        System.out.println("\n--- 전체 계좌 목록 ---");
        if (accounts.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        for (Account account : accounts) {
            System.out.println(account); // account.toString()이 자동으로 호출됨
        }
    }


    //--- Helper Methods (도우미 메서드) ---

    // 고객 ID로 고객 찾기
    private static Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null; // 못 찾으면 null 반환
    }

    // 계좌번호로 계좌 찾기
    private static Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null; // 못 찾으면 null 반환
    }
}