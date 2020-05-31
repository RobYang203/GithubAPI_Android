# Connect Github API using MVVM
## 功能
* 開啟app 顯示從 Github API 傳送過來的會員列表資料
* 一次 20 筆 ，共 100筆
* 點擊項目可查看會員資料
* 會員資料有
    * 大頭貼
    * 姓名
    * 簡介
    * 帳號
    * 位置
    * 個人網站URL


###  API
建立 service 、 Repository 、 APIManager 來連線到 Github API

###  List
* 使用 **RecyclerView** 來建立List
* 建立 **GithubListAdapter** 來管理List資料流動
* **GithubListItem** 顯示單筆會員列表
* **GithubListBottom** 撈取分頁資料的loading畫面

### User Information
* 以 **dataBinding**的方式跟資料作連結
* **GithubUserInfoViewMode** 管控`User Information`頁面資料

### Pagination
* 偵測 **RecyclerView** 的滑動
* 當滑動到最底部時， 撈取下一頁的資料
* 下一頁資料會接續原始資料後面
* 當資料有100筆之後，不再撈取資料

