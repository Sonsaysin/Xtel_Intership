package interfaces;

public interface Payable {
    double calculateTuition();  // tính số tiền cần đóng
    void payTuition(double amount);   // thực hiện việc nhận số tiền sinh viên muốn đóng cập nhật và ktra

}