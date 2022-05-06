# swRepository 소공프로젝트
-개발환경: springBoot, jdk11, h2 DataBase, lombok, jpa

src->main->java->hello->se

domain package는 EntityClass와 ClassDTO가 있는 package

repository package는 data들을 DB에 저장

기본적으로 JPA의 ORM기술을 사용
DB Table과 java class를 매핑, query는 JPQL을 사용


**5월 6일**
모든 페이지를 controller과 연결, test수행 완료
예약date를 입력하고 "예약하기"를 누르면 DB에 data가 들어가게 함.

