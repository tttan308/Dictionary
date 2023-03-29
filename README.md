# BÀI TẬP GENERICS AND COLLECTIONS
### Giáo viên hướng dẫn: Nguyễn Lê Hoàng Dũng
### Thông tin sinh viên: Trần Thái Tân - 21120553 - tantran.300803@gmail.com

## 1. Hướng dẫn cách chạy chương trình
- **Cách 1:** Vào thư mục Release, chọn file Dictionary.jar để chạy (cần tải jdk trước đó).
![image](https://user-images.githubusercontent.com/92630949/228587865-a9b6e7af-c59c-4802-b234-a44cfa7dc1f8.png)
- **Cách 2:** Mở terminal trên thư mục Release, chạy lệnh _java -jar Dictionary.jar_
![image](https://user-images.githubusercontent.com/92630949/228588424-ebcf8102-108f-42ec-998c-71dd6963333d.png)

## 2. Hướng dẫn sử dụng các chương trình
### a. Tra cứu từ và hiển thị nghĩa của từ
- Nhập từ cần tra vào ô tìm kiếm (như ảnh) rồi ấn *Enter*, nghĩa của từ sẽ xuất hiện bên dưới.
![image](https://user-images.githubusercontent.com/92630949/228589492-9a6e308b-0d34-45f5-9804-ee3dc42fcb5f.png)
- Đối với những từ có nghĩa dài vượt quá giới hạn kích thước của khung chương trình hiện tại, có thể dùng thanh scroll phía bên phải cùng để kéo xuống xem tiếp.
- Ngoài ra bên cạnh ô tìm kiếm có icon tìm kiếm, có thể ấn vào icon để tra từ thay vì nhấn *Enter*.
![image](https://user-images.githubusercontent.com/92630949/228590390-6deca710-47ff-4e53-92b6-b67e827d6f35.png)
- Đối với những từ chưa có trong từ điển, chương trình sẽ thông báo không tìm thấy:
![image](https://user-images.githubusercontent.com/92630949/228591554-2a2858d6-efbd-4a36-877e-45fc96c42cc2.png)

### b. Chuyển đổi ngôn ngữ tra cứu
- Góc phải trên cùng của chương trình có chức năng chuyển đổi ngôn ngữ tra cứu, ấn vào hình quả địa cầu để chuyển đổi ngôn ngữ.
![image](https://user-images.githubusercontent.com/92630949/228590938-c59ae1d8-81a1-422e-8222-e12fb643c592.png)
![image](https://user-images.githubusercontent.com/92630949/228590984-439d6da4-db61-4b74-b2f6-d861052a6b68.png)
- Tra cứu một từ trong từ điển Tiếng Việt:
![image](https://user-images.githubusercontent.com/92630949/228591131-edf87ba9-baf3-42aa-9e22-d570d0dc7978.png)
- Tra cứu một từ Tiếng Anh trong từ điển Tiếng Việt (sẽ không tìm thấy):
![image](https://user-images.githubusercontent.com/92630949/228591269-4ec8188f-9287-4217-b42a-aca6a16b06c0.png)

### c. Thêm từ mới (cùng với nghĩa) vào từ điển đã chọn
- Chọn vào logo có dấu + phía bên phải như hình:
![image](https://user-images.githubusercontent.com/92630949/228592330-847ffb9e-66de-4780-b0c4-f63de668cfe9.png)
- Chương trình sẽ tạo ra một hộp thoại mới, người dùng nhập những thông tin cần thêm của từ mới vào đó:
![image](https://user-images.githubusercontent.com/92630949/228593034-cc2921ee-ed3d-4f8e-9e34-9692bbc71fc3.png)
- Nhấn Add new word để thêm từ mới, nếu thành công, chương trình sẽ thông báo *Add new word successfully*
![image](https://user-images.githubusercontent.com/92630949/228593295-a2d0da6b-90e5-47f3-ae78-253d5ed8ff6c.png)
- Tra lại từ mới vừa thêm: </br>
![image](https://user-images.githubusercontent.com/92630949/228593482-8d9e026c-f228-4c9f-b05d-300a3d72fada.png)
- Nếu cố tình thêm lại từ có sẵn, chương trình sẽ thông báo từ đấy đã tồn tại và không thêm vào
![image](https://user-images.githubusercontent.com/92630949/228594941-4b5dc824-74d7-4f41-b838-22cdf889d88d.png)
- Chương trình thêm vào từ điển hiện đang chọn, vì thế nếu đổi từ điển thì không thể tra được từ vừa thêm
![image](https://user-images.githubusercontent.com/92630949/228595230-c4b23374-295c-4b63-bf65-6687708d2472.png)

### d. Xóa một từ (cùng với nghĩa) ra khỏi từ điển đã chọn
### e. Lưu lại từ yêu thích. Sắp xếp danh sách từ yêu thích theo thứ tự A-Z hoặc Z-A.
### f. Thống kê tần suất tra cứu các từ đã tra từ ngày Date1 đến ngày Date2.
