

## Axon framework

### 설명

이벤트 소싱 패턴을 지원하는 프레임워크다.
Axon 서버를 쉽게 사용할 수 있는 유틸 함수를 제공하고 있다. 
Axon 서버는 커맨드를 실행하고 이벤트를 발행하도록 도와준다.

### 주요 기능

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
