# 資料結構選擇說明

本說明以 **LibraryManagementSystem** 與 **OrderManagementPractice** 為例。

## 1. 保存全部書籍 / 訂單
- **資料結構**：`ArrayList<Book>` / `ArrayList<Order>`
- **原因**：需要動態新增、依索引存取、支援後續轉成陣列進行排序與搜尋。
- **未採用陣列**：初始大小未知，ArrayList 較彈性。
- **對應程式**：`LibraryManagementSystem.books`、`OrderManagementPractice.allOrders`

## 2. 依編號排序
- **演算法**：自行實作 Merge Sort（`BookAlgorithms.mergeSortById`）
- **原因**：穩定、時間 O(n log n)、適合中大型資料。
- **未採用 Selection/Insertion**：資料量可能較大時效率較差。
- **未採用 Arrays.sort**：題目要求自行實作。

## 3. 依編號快速查詢
- **演算法**：Binary Search（`BookAlgorithms.binarySearchById`）
- **原因**：資料已排序，O(log n) 效率高。
- **前提**：必須先排序，否則結果錯誤。
- **未採用 Sequential**：資料量大時較慢。

## 4. 依分類 / 顧客姓名找出全部
- **演算法**：Sequential Search（`findByCategory` / `findByCustomer`）
- **原因**：不需要排序，且可能回傳多筆，線性掃描最直接。
- **未採用 Binary**：分類/姓名通常未排序，且需找全部而非單一。

## 5. 待處理訂單（Queue）
- **資料結構**：`Deque` 作為 Queue（`waiting`）
- **原因**：先進先出，符合處理順序。
- **對應 method**：`processNext()`、`peekNext()`

## 6. 已完成訂單與復原（Stack）
- **資料結構**：`Deque` 作為 Stack（`completed`）
- **原因**：後進先出，方便「復原最近一筆」。
- **對應 method**：`processNext()` 中的 push，以及未來可擴充的 undo。

## 總結比較
| 功能 | 選擇 | 原因 |
|------|------|------|
| 主資料保存 | ArrayList | 動態、可轉陣列 |
| 排序 | Merge Sort | O(n log n)、穩定 |
| 已排序查詢 | Binary Search | O(log n) |
| 未排序多筆查詢 | Sequential Search | 簡單、完整 |
| 等待處理 | Queue | FIFO |
| 完成與復原 | Stack | LIFO |
