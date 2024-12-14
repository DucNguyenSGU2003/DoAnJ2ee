-- Tạo database UniversityCharityDB
CREATE DATABASE IF NOT EXISTS UniversityCharityDB;
USE UniversityCharityDB;

-- Tạo bảng policies (Điều khoản chính sách)
CREATE TABLE policies (
    policy_id INT AUTO_INCREMENT,  -- Khóa chính tự động tăng
    campaign_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng campaigns
    policy_description TEXT NOT NULL,  -- Mô tả chính sách
    eligibility_criteria TEXT NOT NULL,  -- Tiêu chí điều kiện
    approval_required VARCHAR(50) NOT NULL,  -- Yêu cầu duyệt
    created_at DATE,  -- Ngày tạo
    updated_at DATE,  -- Ngày cập nhật
    PRIMARY KEY (policy_id, campaign_id)  -- Khóa chính bao gồm cả policy_id và campaign_id
);

-- Tạo bảng policy_violations (Vi phạm chính sách)
CREATE TABLE policy_violations (
    violation_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    policy_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng policies
    violation_description TEXT NOT NULL,  -- Mô tả vi phạm
    violation_date DATE NOT NULL,  -- Ngày vi phạm
    status VARCHAR(50) NOT NULL,  -- Trạng thái vi phạm
    created_at DATE NOT NULL,  -- Ngày tạo bản ghi
    updated_at DATE NOT NULL  -- Ngày cập nhật bản ghi
);

-- Tạo bảng violation_actions (Hành động xử lý vi phạm)
CREATE TABLE violation_actions (
    action_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    violation_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng policy_violations
    action_description TEXT NOT NULL,  -- Mô tả hành động xử lý
    action_date DATE NOT NULL,  -- Ngày thực hiện hành động xử lý
    status VARCHAR(50) NOT NULL,  -- Trạng thái xử lý
    created_at DATE NOT NULL  -- Ngày tạo bản ghi
);

-- Tạo bảng campaigns (Chiến dịch từ thiện)
CREATE TABLE campaigns (
    campaign_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    title VARCHAR(255) NOT NULL,  -- Tiêu đề chiến dịch
    description LONGTEXT NOT NULL,  -- Mô tả chiến dịch (đã thay đổi thành LONGTEXT)
    target_amount BIGINT NOT NULL DEFAULT 0,  -- Mục tiêu quyên góp
    current_amount BIGINT NOT NULL DEFAULT 0,  -- Số tiền hiện tại đã quyên góp
    created_at DATE NOT NULL,  -- Ngày tạo chiến dịch
    start_date DATE NOT NULL,  -- Ngày bắt đầu chiến dịch
    end_date DATE NOT NULL,  -- Ngày kết thúc chiến dịch
    created_by INT NOT NULL,  -- Người tạo chiến dịch (Khóa ngoại từ users)
    status VARCHAR(255) NOT NULL  -- Trạng thái chiến dịch
);

-- Tạo bảng campaign_reports (Báo cáo chiến dịch)
CREATE TABLE campaign_reports (
    report_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    campaign_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng campaigns
    total_donations BIGINT NOT NULL,  -- Tổng số tiền quyên góp
    total_recipients INT NOT NULL,  -- Tổng số người nhận hỗ trợ
    results_summary TEXT NOT NULL,  -- Tổng kết kết quả
    lessons_learned TEXT NOT NULL,  -- Những bài học rút ra
    report_date DATE NOT NULL,  -- Ngày báo cáo
    created_at DATE NOT NULL,  -- Ngày tạo bản ghi
    updated_at DATE NOT NULL  -- Ngày cập nhật bản ghi
);

-- Tạo bảng fund_allocations (Phân bổ quỹ)
CREATE TABLE fund_allocations (
    allocation_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    report_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng campaign_reports
    category NVARCHAR(500) NOT NULL,  -- Loại phân bổ quỹ (VD: học bổng, mua vật dụng)
    amount BIGINT NOT NULL,  -- Số tiền được phân bổ
    description TEXT NOT NULL,  -- Mô tả chi tiết phân bổ
    created_at DATE NOT NULL,  -- Ngày tạo bản ghi
    user_id INT  -- Khóa ngoại tham chiếu tới bảng users
);

-- Tạo bảng images (Hình ảnh của chiến dịch)
CREATE TABLE images (
    image_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    campaign_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng campaigns
    description LONGTEXT NOT NULL,  -- Đường dẫn của hình ảnh (đã thay đổi từ image_path thành description)
    image_type VARCHAR(50) NOT NULL  -- Loại hình ảnh (vd: JPG, PNG)
);

-- Tạo bảng donations (Thông tin quyên góp)
CREATE TABLE donations (
    donation_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    campaign_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng campaigns
    user_id INT NOT NULL,  -- Khóa ngoại tham chiếu tới bảng users
    amount BIGINT NOT NULL,  -- Số tiền quyên góp
    payment_method VARCHAR(50) NOT NULL,  -- Phương thức thanh toán (cash_payment, online_payment)
    donation_date DATETIME NOT NULL  -- Ngày quyên góp
);

-- Tạo bảng transactions (Giao dịch quyên góp) với mối quan hệ một-một
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    donation_id INT NOT NULL UNIQUE,  -- Khóa ngoại tham chiếu tới bảng donations và là UNIQUE để đảm bảo quan hệ 1-1
    transaction_code VARCHAR(255) NOT NULL,  -- Mã giao dịch (vnp_TransactionNo)
    payment_gateway VARCHAR(50) NOT NULL,  -- Cổng thanh toán (VNPay, MoMo, PayPel, ...)
    transaction_date DATETIME NOT NULL,  -- Ngày giao dịch (vnp_PayDate)
    transaction_status VARCHAR(255) NOT NULL,  -- Trạng thái giao dịch (vnp_TransactionStatus)
    amount BIGINT NOT NULL,  -- Số tiền giao dịch (vnp_Amount)
    response_code VARCHAR(255) NULL,  -- Mã phản hồi từ cổng thanh toán (vnp_ResponseCode)
    transaction_description VARCHAR(255) NULL,  -- Mô tả giao dịch (vnp_OrderInfo)
);

-- Tạo bảng users (Người dùng)
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,  -- Khóa chính tự động tăng
    name VARCHAR(255) NULL,  -- Tên người dùng
    email VARCHAR(255) NULL,  -- Email người dùng
    phone VARCHAR(50) NULL,  -- Số điện thoại (tùy chọn)
    password VARCHAR(255) NULL,  -- Mật khẩu
    role VARCHAR(255) NULL,  -- Vai trò người dùng (admin, user, etc.)
    status TINYINT(1) NOT NULL DEFAULT 1  -- Trạng thái người dùng (1: Hoạt động, 0: Vô hiệu)
);

-- Thêm các khóa ngoại (Foreign Key Constraints)
ALTER TABLE policies 
ADD FOREIGN KEY (campaign_id) REFERENCES campaigns (campaign_id);

ALTER TABLE policy_violations 
ADD FOREIGN KEY (policy_id) REFERENCES policies (policy_id);

ALTER TABLE violation_actions 
ADD FOREIGN KEY (violation_id) REFERENCES policy_violations (violation_id);

ALTER TABLE campaigns 
ADD FOREIGN KEY (created_by) REFERENCES users (user_id);

ALTER TABLE images 
ADD FOREIGN KEY (campaign_id) REFERENCES campaigns (campaign_id);

ALTER TABLE campaign_reports 
ADD FOREIGN KEY (campaign_id) REFERENCES campaigns (campaign_id);

ALTER TABLE fund_allocations 
ADD FOREIGN KEY (report_id) REFERENCES campaign_reports (report_id);

ALTER TABLE fund_allocations 
ADD FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE donations 
ADD FOREIGN KEY (campaign_id) REFERENCES campaigns (campaign_id);

ALTER TABLE donations 
ADD FOREIGN KEY (user_id) REFERENCES users (user_id);

-- Khóa ngoại và ràng buộc một-một giữa donations và transactions
ALTER TABLE transactions 
ADD CONSTRAINT fk_donation_transaction 
FOREIGN KEY (donation_id) REFERENCES donations (donation_id) 
ON DELETE CASCADE ON UPDATE CASCADE;

-- Ràng buộc duy nhất cho email và name trong bảng users
ALTER TABLE users ADD CONSTRAINT unique_email UNIQUE (email);
ALTER TABLE users ADD CONSTRAINT unique_name UNIQUE (name);
