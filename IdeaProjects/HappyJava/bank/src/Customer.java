// Customer.java
public class Customer {
    private String name; // 고객 이름
    private String customerId; // 고객 ID

    // 생성자: 고객 객체를 만들 때 이름과 ID를 받아서 초기화합니다.
    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    // getter: 외부에서 private 필드를 읽을 수 있게 해주는 메서드들
    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    // 객체 정보를 예쁘게 출력하기 위한 toString() 메서드 오버라이딩
    @Override
    public String toString() {
        return "고객 ID: " + customerId + ", 이름: " + name;
    }
}