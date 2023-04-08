# mangoMusic
SemiProject

### :sparkles:소개
망고뮤직(Mango Music)은 음악을 듣는 것을 넘어 구매하여 소유하는 개념에서 출발한 프로젝트 입니다.
뮤직 플레이어 + 소유권 판매, 구매 플랫폼

### :cloud:기능 요약
1. 웹사이트 이용자는 듣고 싶은 음원을 찾아 음원 스트리밍 서비스를 이용할 수 있습니다.
2. 이용자는 거래소 통하여 자신이 가지고 있는 음원을 올릴 수 있고, 망고뮤직의 망고 포인트를 통하여 판매 또는 구매 할 수 있습니다.

### :date:개발 기간
2023년 2월 27일 ~ 2023년 3월 24일

### :family: 멤버 구성
- 김준원(팀장)
- 서정민
- 정기진
- 황세원

### :smoking:기술
- IntelliJ, HTML, CSS, JavaScript, mustache, Ajax, jQuery
- Spring Boot, Oracle,

### : ERD
![image](https://user-images.githubusercontent.com/108416216/230706540-8ef834fe-8464-45cf-bc58-f99a0ca9e357.png)


### :clipboard:주요 기능
회원관리
|기능명|상세|
|------|---|
|회원가입|아이디, 비밀번호, 이름, 이메일, 성별 총 5가지 정보를 입력하여 회원가입을 할 수 있다.|
|아이디 찾기, 비밀번호 찾기|아이디는 이름과 이메일을 입력 시 새 창으로 아이디를 알려주고, 비밀번호는 아이디, 이름, 이메일을 입력 시 비밀번호 변경할 수 있다.|
|로그인|회원가입 시 입력한 정보와 아이디, 비밀번호가 일치하면 로그인 가능하다.|
|마이페이지|포인트 충전, 환급, 닉네임 변경, 프로필 사진 변경, 구매 내역, 충전 내역, 팔로우 추가 및 목록을 확인할 수 있다.|
|팔로워페이지|팔로우 목록을 통해 팔로워 페이지로 이동이 가능하다. 팔로우 페이지에서는 팔로워의 라이브러리를 볼 수 있다.|

채팅기능
|기능명|상세|
|------|---|
|채팅방 목록|채팅 이력이 있는 회원의 닉네임이 채팅 목록으로 보이며, 최근 순으로 정렬하여 보여준다.|
|메시지 전송|상대방에게 비동기적으로 메시지를 전송할 수 있다.|
|닉네임 검색|닉네임으로 회원 검색 후, 해당 회원 채팅방에 입장하여 메시지를 전송할 수 있다.|

음원 스트리밍
|기능명|상세|
|------|---|
|음악재생|index는 인기트랙, top앨범, 음악추천 기능이 있으며, 음악은 index 또는 검색페이지에서 youtube API를 통해 재생 할 수 있다.|
|플레이리스트|플레이리스트 추가를 통해 음악을 검색하고 내 라이브러리에 추가 할 수 있다.|

거래소
|기능명|상세|
|------|---|
|글 작성|판매 또는 구매 글 작성이 가능하며, 음악 파일 첨부할 수 있다.|
|상세페이지|등록된 음악을 재생시킬 수 있으며, 구매 시 다운로드 버튼이 활성화 된다.|
|댓글|댓글을 작성 및 삭제 가능하다.|

고객센터
|기능명|상세|
|------|---|
|공지사항|관리자가 작성한 공지사항을 확인할 수 있다.|
|Q&A|망고뮤직의 궁금한 점에 대해 게시판 작성 가능하며 다른 게시글에 댓글 작성 가능하고, 자신이 작성한 글에 대해 수정, 삭제 가능하다.|

관리자 페이지
|기능명|상세|
|------|---|
|로그인|로그인 시 관리자의 계정으로만 접근할 수 있다.|
|회원관리|회원 관리 페이지에서 회원 검색이 가능하며, 회원 정보를 조회할 수 있다.|
|포인트 관리|회원의 포인트 목을 확인할 수 있다.|
|공지사항|공지사항페이지를 불러올 수 있으며, 공지 작성할 수 있다.|


### :movie_camera: 시연화면
1. index
![인덱스](https://user-images.githubusercontent.com/119032790/227850509-e7531660-d7f0-4b6d-b356-68b3e79fed7c.png)

#### 회원

2. 로그인
![로그인](https://user-images.githubusercontent.com/119032790/227850255-4e8cf8de-6fe3-4212-a853-8f41ee95ae85.png)

3. 회원가입
![회원가입](https://user-images.githubusercontent.com/119032790/227850304-5a0c7ab1-9490-4fd5-a9f4-c52076992b58.png)

4. 아이디찾기, 비밀번호 찾
![아이디 찾기](https://user-images.githubusercontent.com/119032790/227850378-1dd0b43d-47e0-4bfd-b8a0-0cc838904d2d.png)

![아이디 확인](https://user-images.githubusercontent.com/119032790/227850383-410f7cd0-710f-4b1b-a172-ed5cf6751af3.png)

![비밀번호 변경](https://user-images.githubusercontent.com/119032790/227850582-f58987cd-e512-4144-807c-1e7ee643ce5b.png)

![비밀번호 찾기](https://user-images.githubusercontent.com/119032790/227850585-a1d3b58e-9d0c-4ca6-b7ca-6626482d0536.png)

5. 마이페이지
![마이페이지 채팅X](https://user-images.githubusercontent.com/119032790/227851095-307ea344-79e2-4088-9dc7-855229e30775.png)

6. 채팅
![마이페이지 채팅창](https://user-images.githubusercontent.com/119032790/227851072-cb2290cf-bd57-4333-a19b-7dad08b0959d.png)

7. 공지사항 & Q&A
![Q A 목록](https://user-images.githubusercontent.com/119032790/227851364-28605f6b-9e30-4c58-b553-2db9369a6917.png)

![Q A 상세](https://user-images.githubusercontent.com/119032790/227851437-197297d0-e046-4202-80c2-eb82113a7153.png)

8. 거래소
![거래소 음원 등록](https://user-images.githubusercontent.com/119032790/227851563-5f65cc64-2d43-41ea-8b79-988c4e749cc8.png)

![거래소 이동](https://user-images.githubusercontent.com/119032790/227851468-d43d4fc4-af72-4c12-9ac7-981495882853.png)

9. 플레이 리스트
![플레이 리스트 추가](https://user-images.githubusercontent.com/119032790/227851676-bcca82e1-3555-4cf5-ac59-cedab5d5d8bb.png)

10. 음악 검색
![음원 검색](https://user-images.githubusercontent.com/119032790/227851768-eee96e25-0738-4c93-b26c-30e7953a9ce9.png)


#### 관리자

1. 회원 목록 & 상세정보
![관리자페이지 회원목록 상세정보](https://user-images.githubusercontent.com/119032790/227851864-b1baac82-0b16-46dc-9ac5-2a0410ea441f.png)

2. 회원 포인트 관리
![포인트 관리](https://user-images.githubusercontent.com/119032790/227851912-e2805f48-5c6d-4b9d-80bf-e868c3107d99.png)



