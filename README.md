# BuyHome
本專案實作一個購物車 APP。
本 APP 於測試環境下進行 google pay 付款，故不會真正扣款，敬請放心。

(請注意，本專案因使用 Firebase 之 SHA-1 憑證，故無法於無憑證之電腦上以 Android Studio 正常運行登入功能。)

# Demo 影片
[![Watch the video](https://img.youtube.com/vi/q9nhjUszLP4/mq2.jpg)](https://youtu.be/q9nhjUszLP4)

# 使用技術
- MVVM / ViewModel / Fragment / Navigation
- View Binding / Data Binding / LiveDate
- RecyclerView / ListView / Custom Adapter
- ViewPager / CardView
- Firebase / Google Sign-In API
- Google Pay API
- SharedPreferences

# 專案架構說明
本專案以畫面底部的 Bottom Navigation 來切換三個 Fragment 頁面，分別為"Home"、"Offer"、"Message"，而右上方的兩個 Menu 按鈕可進入"購物車"與"會員資訊"頁面，此兩頁面為獨立的 Activity。
- 會員登入頁：首次進入會員資訊頁面或購物車頁面時會要求登入，此部份使用 Firebase 並串接 Google Sing-in API。
- 會員資訊頁面： 登入後會自動抓取使用者的 Google 帳號之頭像、名稱與信箱並顯示於資料中，頭像可透過選擇相簿圖片或拍照來更換。採用 MVVM 架構，以 Navigation Graph 來處理頁面關係與 Back Stack；使用 ViewModel 處理主要業務邏輯以及與 Model 的互動；使用 View Binding 處理 View 的操作以提升效能。
- 購物車頁面：採用 MVVM 架構，並使用多種 Architecture components。以 Navigation Graph 來處理頁面關係與 Back Stack；使用 ViewModel 處理主要業務邏輯以及與 Model 的互動；在 RecyclerView 呈現商品清單的部份，在需要隨著資料變化而更新 UI 的地方使用了 LiveData。付款頁面串接 Google Pay API，於測試環境下付款，不會真正扣款。
- Home 頁面：主要的商品展示頁。上方展示圖片為 ViewPager ，可左右滑動；中間商品展示為客製化的 GridView；下方商品展示為客製化的 ListView。點擊商品可進入商品頁，可點選加入購物車，若已加入購物車則會提示"此商品已在購物車"。
- Offer 頁面：顯示優惠碼。使用公開版權之圖片排版。
- Message 頁面：顯示 APP 相關資訊。點擊信箱按鈕可連結到撰寫 email 的 APP，並將收件人設為作者，而點擊 APP 的詳細說明則可連到外部網頁之詳細說明。
- 資料儲存：商品與個人資訊統一於一個類別中處理，採用 SharedPreferences 在進入 APP 與更改資料時做讀寫，並提供操作資料的方法給其他 Activity 之 ViewModel 來操作這些資料。

# 前期 Use Case Diagram
本專案在前期以 User Story 與 Use Case 進行需求擷取，接著繪製 Use Case Diagram 來列出需求，然後以最小可行性產品（minimum viable product, MVP）之觀點來選擇需優先實現之功能。

https://drive.google.com/file/d/1Zwagh6wkngjrX78LidiK7FE3wnKIEjUo/view

# 前期 UI/UX 設計
本專案從 wireframe 到 mockup 最後產出 prototype，在每個階段盡可能釐清介面使用上的各種需求。

https://www.figma.com/file/5ssuhubHwbXXJydusxcE5z/%E8%B3%BC%E7%89%A9%E8%BB%8A

# 專案協作版本
本專案初始版本是由我與另兩位夥伴一起採 GitHub flow 協作完成，而後自己完全獨立製作了現在的版本，因此當前版本皆為我獨立製作。

## 協作流程：

![image](https://github.com/Lucien-Hsu/BuyHome/blob/master/%E8%A3%BD%E4%BD%9C%E6%B5%81%E7%A8%8B.png)


對協作版本與協作過程有興趣者可參考以下連結。

https://github.com/betty8398/BuyHomeLogin
