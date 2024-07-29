package hrm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    ADMIN(), // Vai trò quản trị viên, có quyền cao nhất trong hệ thống.
    USER(); // Vai trò người dùng bình thường, có quyền hạn giới hạn.

    public final long value = 0; // Biến này lưu giá trị số tương ứng với mỗi vai trò.
}
