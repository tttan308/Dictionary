# BÀI TẬP GENERICS AND COLLECTIONS
### Giáo viên hướng dẫn: Nguyễn Lê Hoàng Dũng
### Thông tin sinh viên: Trần Thái Tân - 21120553 - tantran.300803@gmail.com

## 1. Hướng dẫn cách chạy chương trình
- **Cách 1:** Vào thư mục Release, chọn file Dictionary.jar để chạy (cần tải jdk trước đó). </br>
![image](https://user-images.githubusercontent.com/92630949/228587865-a9b6e7af-c59c-4802-b234-a44cfa7dc1f8.png)</br>
- **Cách 2:** Mở terminal trên thư mục Release, chạy lệnh _java -jar Dictionary.jar_ </br>
![image](https://user-images.githubusercontent.com/92630949/228588424-ebcf8102-108f-42ec-998c-71dd6963333d.png)</br>

## 2. Hướng dẫn sử dụng các chương trình
### a. Tra cứu từ và hiển thị nghĩa của từ
- Nhập từ cần tra vào ô tìm kiếm (như ảnh) rồi ấn *Enter*, nghĩa của từ sẽ xuất hiện bên dưới.</br>
![image](https://user-images.githubusercontent.com/92630949/228589492-9a6e308b-0d34-45f5-9804-ee3dc42fcb5f.png)</br>
- Đối với những từ có nghĩa dài vượt quá giới hạn kích thước của khung chương trình hiện tại, có thể dùng thanh scroll phía bên phải cùng để kéo xuống xem tiếp.</br>
- Ngoài ra bên cạnh ô tìm kiếm có icon tìm kiếm, có thể ấn vào icon để tra từ thay vì nhấn *Enter*.</br>
![image](https://user-images.githubusercontent.com/92630949/228590390-6deca710-47ff-4e53-92b6-b67e827d6f35.png)</br>
- Đối với những từ chưa có trong từ điển, chương trình sẽ thông báo không tìm thấy:</br>
![image](https://user-images.githubusercontent.com/92630949/228591554-2a2858d6-efbd-4a36-877e-45fc96c42cc2.png)</br>

### b. Chuyển đổi ngôn ngữ tra cứu
- Góc phải trên cùng của chương trình có chức năng chuyển đổi ngôn ngữ tra cứu, ấn vào hình quả địa cầu để chuyển đổi ngôn ngữ.</br>
![image](https://user-images.githubusercontent.com/92630949/228590938-c59ae1d8-81a1-422e-8222-e12fb643c592.png)</br>
![image](https://user-images.githubusercontent.com/92630949/228590984-439d6da4-db61-4b74-b2f6-d861052a6b68.png)</br>
- Tra cứu một từ trong từ điển Tiếng Việt:</br>
![image](https://user-images.githubusercontent.com/92630949/228591131-edf87ba9-baf3-42aa-9e22-d570d0dc7978.png)</br>
- Tra cứu một từ Tiếng Anh trong từ điển Tiếng Việt (sẽ không tìm thấy):</br>
![image](https://user-images.githubusercontent.com/92630949/228591269-4ec8188f-9287-4217-b42a-aca6a16b06c0.png)</br>

### c. Thêm từ mới (cùng với nghĩa) vào từ điển đã chọn
- Chọn vào logo có dấu + phía bên phải như hình:
![image](https://user-images.githubusercontent.com/92630949/228592330-847ffb9e-66de-4780-b0c4-f63de668cfe9.png)</br>
- Chương trình sẽ tạo ra một hộp thoại mới, người dùng nhập những thông tin cần thêm của từ mới vào đó:</br>
![image](https://user-images.githubusercontent.com/92630949/228593034-cc2921ee-ed3d-4f8e-9e34-9692bbc71fc3.png)</br>
- Nhấn Add new word để thêm từ mới, nếu thành công, chương trình sẽ thông báo *Add new word successfully*</br>
![image](https://user-images.githubusercontent.com/92630949/228593295-a2d0da6b-90e5-47f3-ae78-253d5ed8ff6c.png)</br>
- Tra lại từ mới vừa thêm: </br>
![image](https://user-images.githubusercontent.com/92630949/228593482-8d9e026c-f228-4c9f-b05d-300a3d72fada.png)</br>
- Nếu cố tình thêm lại từ có sẵn, chương trình sẽ thông báo từ đấy đã tồn tại và không thêm vào</br>
![image](https://user-images.githubusercontent.com/92630949/228594941-4b5dc824-74d7-4f41-b838-22cdf889d88d.png)</br>
- Chương trình thêm vào từ điển hiện đang chọn, vì thế nếu đổi từ điển thì không thể tra được từ vừa thêm</br>
![image](https://user-images.githubusercontent.com/92630949/228595230-c4b23374-295c-4b63-bf65-6687708d2472.png)</br>

### d. Xóa một từ (cùng với nghĩa) ra khỏi từ điển đã chọn
- Trong khi tra từ, nếu muốn xóa một từ (cùng với nghĩa) ra khỏi từ điển đã chọn, chọn logo có chữ X bên phải có từ, chương trình sẽ thông báo xóa thành công</br>
![image](https://user-images.githubusercontent.com/92630949/228601425-f4410a91-53f2-446c-bd71-ee5d23ce09c9.png)</br>
- Tra lại từ đó</br>
![image](https://user-images.githubusercontent.com/92630949/228601540-d278f83a-cb48-47a5-b0b5-9ac4e7acf548.png)</br>

### e. Lưu lại từ yêu thích. Sắp xếp danh sách từ yêu thích theo thứ tự A-Z hoặc Z-A.
- Chọn logo ở góc trái của chương trình, sẽ có 2 chức năng, chọn chức năng _Favourite list word_ để xem danh sách cách từ yêu thích </br>
![image](https://user-images.githubusercontent.com/92630949/228602221-6b9c94a0-18c2-46f8-b59a-0789d16127b5.png)</br>
- Danh sách các từ yêu thích được hiển thị như bảng sau:</br>
![image](https://user-images.githubusercontent.com/92630949/228602462-0a1f655a-e3e4-4ed8-b43d-7508f98fcf7d.png)</br>
- Có thể chuyển danh sách các từ yêu thích ở từ điển khác</br>
![image](https://user-images.githubusercontent.com/92630949/228602749-e029faaa-6a0a-4d87-9c17-ff107afe35b5.png)</br>
- Để sắp xếp theo thứ tự từ A-Z, nhân vào ô _Word_</br>
![image](https://user-images.githubusercontent.com/92630949/228602914-4dea005b-7f6e-4ba7-b27d-be2505ef75b7.png)</br>
- Nhấn thêm lần nữa để sắp xếp Z-A</br>
![image](https://user-images.githubusercontent.com/92630949/228603031-918f7493-fe52-4103-bc9b-4999bf01ee23.png)</br></br>
- Tương tự đối với từ điển tiêng Việt</br>
### f. Thống kê tần suất tra cứu các từ đã tra từ ngày Date1 đến ngày Date2.
- Chọn logo ở góc trái của chương trình, sẽ có 2 chức năng, chọn chức năng _Statistics for lookup word_ để xem danh sách cách từ yêu thích </br>
![image](https://user-images.githubusercontent.com/92630949/228602221-6b9c94a0-18c2-46f8-b59a-0789d16127b5.png)</br>
- Chương trình sẽ yêu cầu nhập ngày bắt đầu và ngày kết thúc, nhập đúng định dạng dd/mm/yyyy, nếu không chương trình sẽ báo lỗi</br>
![image](https://user-images.githubusercontent.com/92630949/228603576-ea801899-e326-40b2-ac6b-c477094ed5ad.png)</br>
![image](https://user-images.githubusercontent.com/92630949/228603677-ef267791-d444-4f7c-8345-eaeda4b6d8d8.png)</br>
- Ấn vào Statistics để xem bảng thống kê</br>
![image](https://user-images.githubusercontent.com/92630949/228603825-e00138da-6259-4162-bc08-b933fc977b99.png)</br>
- Có thể tùy chọn chuyển qua lại giữa các từ điển</br>
![image](https://user-images.githubusercontent.com/92630949/228605058-211e615b-e924-43fc-81fe-f6890746e204.png)</br>

## 3. Tài liệu tham khảo
- Slide bài giảng - thầy Nguyễn Văn Khiết.
- Tài liệu generics & collection - thầy Nguyễn Lê Hoàng Dũng.
- Nguồn ảnh: https://www.flaticon.com/
- https://stackoverflow.com/questions/13927650/how-to-take-date-in-text-field
