# Abysshop (REST API) <hr>

- `Stateful`하게 작성되었던 기존의 [Abysshop 프로젝트](https://github.com/Bam-j/abysshop)를 리팩토링하여 `Stateless(REST API 사용)`한 방식으로 재개발된 프로젝트입니다.


- `Abysshop`은 **온라인 쇼핑몰** 페이지로, `마인크래프트 사설 멀티플레이 서버`에서 사용되는 후원 물품들을 판매하는 쇼핑몰 페이지입니다.

<hr>

## 무엇이 바뀌었나?

|    항목     |            구버전             |                신버전                |
|:---------:|:--------------------------:|:---------------------------------:|
| 클라이언트 상태  |         `Stateful`         |      `Stateless (REST API)`       |
| 사용자 인증 방식 |      `Session 기반 인증`       |          `JWT 토큰 기반 인증`           |
| DB와 객체 매핑 |    `MyBatis SQLMapper`     |             `JPA ORM`             |
|   화면 구성   |         `JSP SSR`          |          `React.js CSR`           |
|    배포     | `Apache Tomcat`을 이용한 로컬 배포 | `Railway`플랫폼과 `Docker`를 이용한 통합 배포 |

이 외에도

- `RESTful`한 방식으로의 전체적인 코드(클래스, 요청 api) 리팩토링
- `Postman`을 사용한 API 개발 및 테스트 과정 도입
- 기존 `DTO` 객체를 `class`에서 `record`로 변경
- 프론트와 백엔드의 명확한 구분에 따른 `Docker` 도입
- DB table 구조 변경
- 스타일 시트 작성을 `CSS`에서 `SCSS`로 변경
- 실제 운영을 위한 `dev, prod yml` 분리와 `.env` 도입
- 배포 방식도 기존 톰캣 사용한 로컬 서버 배포에서 `Railway` 플랫폼 이용

등의 변화가 있었습니다.

<hr>

## ERD

![Image](https://github.com/user-attachments/assets/10c541da-4606-441c-b2d9-2b680ca8696a)

<hr>

- `abysshop`에 대한 더욱 자세한 설명은 [블로그](https://velog.io/@bami/abysshop-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%9B%B9-%EC%87%BC%ED%95%91%EB%AA%B0-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EA%B0%9C%EC%9A%94)에서 확인하실 수 있습니다. (현재 작성중!)