// Account.java
public class Account {
    private String accountNumber; // 계좌번호
    private long balance;         // 잔액
    private Customer owner;       // 계좌 소유주 (Customer 객체)

    // 생성자: 계좌번호와 소유주 정보로 계좌를 생성하고, 잔액은 0으로 시작합니다.
    public Account(String accountNumber, Customer owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0;
    }

    // getter 메서드들
    public String getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }

    // 입금 메서드
    public void deposit(long amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println(amount + "원 입금이 완료되었습니다. 현재 잔액: " + this.balance + "원");
        } else {
            System.out.println("유효하지 않은 금액입니다.");
        }
    }

    // 출금 메서드 (성공하면 true, 실패하면 false 반환)
    public boolean withdraw(long amount) {
        if (amount <= 0) {
            System.out.println("유효하지 않은 금액입니다.");
            return false;
        }
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println(amount + "원 출금이 완료되었습니다. 현재 잔액: " + this.balance + "원");
            return true;
        } else {
            System.out.println("잔액이 부족합니다.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "계좌번호: " + accountNumber + " | 소유주: " + owner.getName() + " | 잔액: " + balance + "원";
    }
}