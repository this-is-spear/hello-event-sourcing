

## Axon framework

### 설명

이벤트 소싱 패턴을 지원하는 프레임워크다.
Axon 서버를 쉽게 사용할 수 있는 유틸 함수를 제공하고 있다. 
Axon 서버는 커맨드를 실행하고 이벤트를 발행하도록 도와준다.

### TODO LIST

- [X] : https://docs.axoniq.io/bikerental-demo/main/
- [ ] : https://docs.axoniq.io/playbook/
- [ ] : https://docs.axoniq.io/dead-letter-queue-guide/development/implementing/

### 주요 기능

#### Axon framework로 command, query 관련 인터페이스 제공

명령어 처리 방법

1. 명령어를 생성해 CommandGateway 를 통해 명령어를 전달한다. 이때 명령어에는 TargetAggregateIdentifier 로 에그리게이트 ID를 마킹한다.
2. CommandHandler 로 명령어를 처리한다. 이때 AggregateIdentifier 로 애그리게이트를 찾아 명령어를 처리한다.
3. AggregateLifecycle.apply() 로 이벤트를 발행한다.
4. EventSourcingHandler 로 이벤트를 처리한다.

> 명령어와 이벤트 처리가 분할된다.

쿼리 처리 방법

1. 등록한 queryName 으로 QueryGateway 를 통해 쿼리를 전달한다.
2. queryName 에 맞는 QueryHandler 로 쿼리를 처리한다.
3. 쿼리 결과를 반환한다.

> 이 때 명령어로 상태 변경이 발생한 경우 EventHandler 를 통해 상태 변경을 감지하고 데이터베이스를 갱신한다.

### Axon server 콘솔 제공

> 무료 버전이라 큰 기능은 없다.

콘솔을 제공하는데 다음과 같은 기능을 제공한다.

|시스템 맵 제공|Axon 서버 모니터링 기능 제공|
|---|---|
|<img width="100%" alt="스크린샷 2025-01-12 오후 11 41 45" src="https://github.com/user-attachments/assets/261acba7-3915-4251-bc98-821fac0b44b7" />|<img width="100%" alt="스크린샷 2025-01-12 오후 11 44 05" src="https://github.com/user-attachments/assets/1530d2f0-d4cc-42d5-b7fc-cb493599a859" />|

커맨드 및 쿼리 모델 관련 정보도 제공한다. 실행 시간이 긴 명령어(또는 쿼리)도 모니터링 가능해보인다.

|커맨드 모델|이벤트 상세 조회|
|---|---|
|<img width="100%" src="https://github.com/user-attachments/assets/f96561b0-0bbf-402c-be2e-8abb29a2a65e" />|<img width="100%" src="https://github.com/user-attachments/assets/853cdfda-d4cd-4073-bae1-285e5fc518b2" />|

이벤트 분석 가능하다. 필터링도 제공해서 유용해보인다.

|이벤트 리스트 조회|이벤트 상세 조회|
|---|---|
|<img width="100%" alt="스크린샷 2025-01-12 오후 11 42 35" src="https://github.com/user-attachments/assets/d504aa8f-2082-4d44-ba4e-20b5cc3183ef" />|<img width="100%" alt="스크린샷 2025-01-12 오후 11 42 49" src="https://github.com/user-attachments/assets/04a822c0-1213-4ce2-8870-c5a21e13c09e" />|

## 경험했던 것

### 프로젝션 엔진 뜻을 이해할 수 있었다.

[도메인 주도 설계 첫걸음](https://product.kyobobook.co.kr/detail/S000061352142) 책에서 프로젝션 엔진에 대해서 모호한 추측만 했는데 구현하면서 어떤 역할을 하고 어떻게 동작하는지 이해하게 됐다.

<img width="543" alt="스크린샷 2025-01-12 오후 11 55 48" src="https://github.com/user-attachments/assets/fac6f3f8-8da1-45dc-a2ce-350fa9be4f73" />

첫 번째는 동기식 프로젝션 형태도 확인할 수 있었다.

<img width="542" alt="스크린샷 2025-01-13 오전 12 01 56" src="https://github.com/user-attachments/assets/bd1be2d8-26e8-4424-83c7-7404c8fad552" />

EventSourcingHandler 애너테이션을 이용해 자신 애그리게이트를 지속적으로 업데이트하고 있다.

![image](https://github.com/user-attachments/assets/6b2d1eb7-ddf7-444c-9682-8b95bbcdfdb2)

그러나 실상은 비동기식 프로젝션 형태에 가깝다.

<img width="541" alt="스크린샷 2025-01-13 오전 12 11 46" src="https://github.com/user-attachments/assets/5bc0b321-0ab7-46f6-a6db-12b600cc534d" />

지속적으로 변경되는 애그리게이트를 조회하지 않고 프로젝션 엔진으로 수정되는 데이터베이스를 조회하고 있기 떄문이다.

![image](https://github.com/user-attachments/assets/5945d6ac-3052-4f2f-91fd-c0968515baf0)

Axon 서버는 다음과 같은 역할을 하고 있다.

- 이벤트 스토리지 역할
- 커맨드, 쿼리 라우팅 역할

덕분에 프로젝션 엔진 역할을 할 수 있다고 생각한다.






