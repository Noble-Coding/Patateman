## Demo
- Youtube: https://youtu.be/A061etp1Djw
## Mô tả về các đối tượng trong trò chơi

- ![](readme/player.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi. 

- ![](readme/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt. Khi đã được đặt, Bomber không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2.5s, Bomb sẽ tự nổ.

- ![](readme/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber không thể di chuyển vào đối tượng này

- ![](readme/brick.png) *Brick* là đối tượng không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.

- ![](readme/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

*Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như sau:

- ![](readme/powerup_speed.png) *SpeedItem* Item này sẽ giúp Bomber được tăng tốc độ di chuyển.

- ![](readme/powerup_bombs.png) *BombItem* Item này giúp tăng số lượng Bomb có thể đặt thêm một.

- ![](readme/powerup_flamepass.png) *FlamePassItem* Khi sử dụng Item này Bomber sẽ được mặc giáp Vàng và có thể đi qua Flame mà không bị sao.

- ![](readme/powerup_flames.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ.

- ![](readme/powerup_life.png) *LifeItem* Khi sử dụng Item này Bomber sẽ được tăng thêm 1 mạng nữa.

*Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể sau đây:

- ![](readme/ballom.png) *Ballom* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với tốc độ chậm.

- ![](readme/oneal.png) *Oneal* biết đuổi Bomber khi lại gần, có tốc độ di chuyển tăng trong khi đuổi Bomber.

- ![](readme/pass.png) *Pass* di chuyển ngẫu nhiên với tốc độ khá nhanh. Khi bị tiêu diệt sẽ sinh ra thêm 2 *Ballom*.

- ![](readme/dahl.png) *Dahl* có tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm.

- ![](readme/doria.png) *Doria* biết đuổi Bomber khi lại gần nhưng phạm vi rộng hơn Oneal, có tốc độ di chuyển tăng và có thể di chuyển xuyên Brick trong khi đuổi Bomber .
