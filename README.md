# Sparta_Spring_todoApp
 스파르타 내일배움캠프 스프링 숙련 주차 개인과제 레포지토리

## api

### 할 일 생성
@PostMapping("/api/todo")

### 할 일 단건 조회
@GetMapping("/api/todo/{todoId}")

### 할 일 단건 수정
@PutMapping("/api/todo/{todoId}")

### 할 일 삭제
@DeleteMapping("/api/todo/{todoId}")

----------------------------------------------------

### 댓글 작성 
@PostMappinga("/api/comment/")
  
### 댓글 단건 조회
@GetMapping("/api/comment/{commentId}")

### 댓글 전체 조회 (todoId)
@GetMapping("/api/comment/todo/{todoId}")

### 댓글 수정
@PutMapping("/api/comment/{commentId}")

### 댓글 삭제
@DeleteMapping("/api/comment/{commentId}")

-----------------------------------------------------

### 유저 생성 
@PostMapping("/api/users/register")

### 유저 단건 조회 
@GetMapping("/api/users/{userId}")

### 유저 수정
@PutMapping("/api/users/update/{userId}")

### 유저 삭제
@DeleteMapping("/api/users/delete/{userId}")

### 유저 로그인 
@PostMapping("/api/users/register")

----------------------------------------------------


## ERD
https://www.erdcloud.com/d/r8eCCjNi42iZyrQ45
![image](https://github.com/user-attachments/assets/179c824a-e312-4349-89b3-ec52398a2487)
