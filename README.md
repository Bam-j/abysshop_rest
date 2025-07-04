# Abysshop (REST API)

- `Abysshop`은 **포인트제 온라인 쇼핑몰**로 마인크래프트 사설 멀티플레이 서버 `Abyssblock`의 후원 및 유료 재화 판매를 위한 웹 쇼핑몰입니다.

- 기존 세션 기반 인증 방식을 사용하던 [구버전](https://github.com/Bam-j/abysshop)의 페이지를 JWT 웹 토큰 인증 기반으로 변경하고 REST API, React 도입 등을 통해 사용자에게 더 좋은 경험을 제공하고자 하였습니다. 

<hr>

![Image](https://github.com/user-attachments/assets/72dccc69-29e9-4e72-b654-7bf11b334524)


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

## Docs
- [프로젝트에 대한 전체적인 설명(개인 블로그)](https://velog.io/@bami/abysshop-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%99%84%EC%84%B1-With-REST-API) <br/>
- [프로젝트 구현(개인 블로그)](https://velog.io/@bami/abysshop-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%99%84%EC%84%B1-With-REST-API#%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EA%B5%AC%ED%98%84) <br/>
- [API 명세(Notion)](https://motley-broker-ff4.notion.site/API-2040e305159480ffa11af06b04b479de) <br/>
- [도메인 모델링(Notion)](https://motley-broker-ff4.notion.site/JPA-2040e3051594802aae09ca8325f1e4e3) <br/>

구버전에 대한 내용은 다음 링크에서 확인하실 수 있습니다.
- [구버전 프로젝트 설명(개인 블로그)](https://velog.io/@bami/abysshop-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%9B%B9-%EC%87%BC%ED%95%91%EB%AA%B0-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EA%B0%9C%EC%9A%94) <br/>
- [구버전 GitHub Repository](https://github.com/Bam-j/abysshop) <br/>
