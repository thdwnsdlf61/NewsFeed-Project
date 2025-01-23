# NewsFeed-Project
### 📖 프로젝트 설명
- SNS의 주요 기능들을 스프링 프레임워크를 활용해서 만든 프로젝트 입니다.
---
## 📌 주요 기능
- 프로필 crud
- 게시글 crud
- 팔로우 기능
### ✏️ 기능 상세
---
## 🔧 기술 스택
- **`JAVA`**
- **`SPRING`**
- **`SPRINGBOOT JPA`**
- **`DATABASE` : `MySql`**
- **`Version Controll` : `Git`**
### ⚙️ 개발 환경
- **`intelliJ IDEA` `jdk17`**
---
## ERD DIAGRAM

---
## API 명세
| 기능 | url | HttpMethod | request | response | HttpStattus | HttpHeader |
|-----|-----|------------|---------|----------|-------------|---------------|
| 회원가입 | `/signup` | `POST` | { "userName": "String", "email": "String", "password": "String" } | { "userId": "Long", "userName": "String", "email": "String", "createdDate": "LocalDateTime" } | `201` | - |
| 유저 다건 조회 | `/users` | `GET` | - | { "userList": [ { "userId": "Long", "userName": "String", "nickName": "String" }, {...} ] } | `200` | { "Authorization": "Bearer <Token>" |
| 유저 단건 조회 | `/users/{userId}` | `GET` | - | { "userId": "Long", "userName": "String", "email": "String", "nickName": "String", "birthDate": "String", "profileComment": "String", "createdDate": "LocalDateTime" } | `200` | { "Authorization": "Bearer <Token>" |
| 프로필 수정 | `/users/profile` | `PATCH` | { "password": "String", "userName": "String", "email": "String", "nickName": "String", "birthDate": "String", "profileComment": "String" } | { "userId": "Long", "userName": "String", "email": "String", "nickName": "String", "birthDate": "String", "profileComment": "String", "updatedDate": "LocalDateTime" } | `200` | { "Authorization": "Bearer <Token>" |
| 비밀번호 수정 | `/users/password` | `PATCH` | { "oldPassword": "String", "newPassword": "String" } | - | `200` | { "Authorization": "Bearer <Token>" |
| 회원 탈퇴 | `/users/delete/{userId}` | `DELETE` | { "password": "String" } | - | `204` | { "Authorization": "Bearer <Token>" |
| 로그인 | `/login` | `POST` | { "email": "String", "password": "String" } | - | `200` | - | { "Authorization": "Bearer <Token>" |
| 로그아웃 | `/logout` | `DELETE` | - | - | `204` | { "Authorization": "Bearer <Token>" |
| 게시글 생성 | `/newsfeeds` | `POST` | { "title": "String", "contents": "String" } | { "newsFeedId": "Long", "title": "String", "author": "nickName" , "contents": "String", "createdDate": "LocalDateTime" } | `201` | { "Authorization": "Bearer <Token>" |
| 게시글 다건 조회 | `/newsfeeds` | `GET` | - | { "newsFeedList": [ { "title": "String", "author": "nickName", "createdDate": "LocalDateTime" }, {...} ] }  | `200` |
| 게시글 단건 조회 | `/newsfeeds/{newsFeedId}` | `GET` | - | { "newsFeedId": "Long", "title": "String", "author": "nickName", "contents": "String", "updatedDate": "LocalDateTime" } | `200` | - |
| 게시글 수정 | `/newsfeeds/update/{newsFeedId}` | `PATCH` | { "password": "String", "title": "String", "contents": "String" } | { "newsFeedId": "Long", "title": "String", "author": "nickName", "contents": "String", "updatedDate": "LocalDateTime" } | `200` | { "Authorization": "Bearer <Token>" |
| 게시글 삭제 | `/newsfeeds/delete/{newsFeedId}` | `DELETE` | { "password": "String" } | - | `204` | { "Authorization": "Bearer <Token>" |

---
