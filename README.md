

## Axon framework

### 설명

이벤트 소싱 패턴을 지원하는 프레임워크다.
Axon 서버를 쉽게 사용할 수 있는 유틸 함수를 제공하고 있다. 
Axon 서버는 커맨드를 실행하고 이벤트를 발행하도록 도와준다.

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






