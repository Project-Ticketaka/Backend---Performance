# 👁️‍🗨️ 개요

공연 데이터 검색, 상세 조회, 예매 시스템에 관련된 로직을 처리하는 서버입니다.

<br>

<br>

# 🌆 기능 및 아키텍처

## 🔎 공연 데이터 검색 및 상세 조회

<br>

<details>
<summary> 📝 API 명세서 </summary>
<div markdown="1">

## getPrfByKeyword

검색할 항목(제목, 위치 등)을 선택 후 유저가 검색한 키워드를 내포한 공연목록을 불러옴

<br>

### URL

- GET `/performance/search?keyword=${keyword}&page=0`

### 응답 예시

- ✅ 성공

  ```json
  {
      "code": 200,
      "description": "성공",
      "data": {
          "content": [
              {
                  "prfId": "PF204058",
                  "title": "민쩌미, 사랑해요 엄마! [대구(앵콜)]",
                  "start_date": "2023-02-18",
                  "end_date": "2023-02-19",
                  "viewingAge": "24개월 이상",
                  "genre": "뮤지컬",
                  "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF204058_221212_160446.gif",
                  "facilityName": "엑스코(exco)"
              },...
          ],
          "pageable": {
              "sort": {
                  "empty": false,
                  "sorted": true,
                  "unsorted": false
              },
              "offset": 0,
              "pageNumber": 0,
              "pageSize": 20,
              "paged": true,
              "unpaged": false
          },
          "size": 20,
          "number": 0,
          "numberOfElements": 4,
          "sort": {
              "empty": false,
              "sorted": true,
              "unsorted": false
          },
          "first": true,
          "last": true,
          "empty": false
      }
  }
  ```

- 실패

  - ⚠️ 조회 데이터 無

    ```json
    {
      "code": 202,
      "description": "검색어에 해당하는 공연을 찾을 수 없습니다."
    }
    ```

## getPerformanceByRank

메인화면에 보이는 실시간 랭킹에 해당하는 공연목록을 불러옴

<br>

### URL

- GET `/performance/rank`

### 응답 예시

- ✅ 성공
  `json
    {
        "code": 200,
        "description": "성공",
        "data": [
            {
                "prfId": "PF213115",
                "title": "해적",
                "startDate": "2023-03-07",
                "endDate": "2023-06-11",
                "viewingAge": "만 13세 이상",
                "genre": "뮤지컬",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF213115_230210_093645.gif",
                "facilityName": "서경대학교 공연예술센터",
                "rnum": "1"
            },
            {
                "prfId": "PF215820",
                "title": "ATEEZ WORLD TOUR: THE FELLOWSHIP BREAK THE WALL [서울]",
                "startDate": "2023-04-28",
                "endDate": "2023-04-29",
                "viewingAge": "만 7세 이상",
                "genre": "대중음악",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF215820_230329_111030.jpg",
                "facilityName": "잠실종합운동장",
                "rnum": "2"
            },
            {
                "prfId": "PF215619",
                "title": "베토벤: Beethoven Secret SEASON 2",
                "startDate": "2023-04-14",
                "endDate": "2023-05-15",
                "viewingAge": "만 7세 이상",
                "genre": "뮤지컬",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF215619_230324_135717.gif",
                "facilityName": "세종문화회관",
                "rnum": "3"
            },
            {
                "prfId": "PF215888",
                "title": "김윤아 단독 콘서트, 행복한 사랑은 없네",
                "startDate": "2023-04-21",
                "endDate": "2023-04-30",
                "viewingAge": "만 7세 이상",
                "genre": "대중음악",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF215888_230329_153111.gif",
                "facilityName": "이화여자대학교 삼성홀",
                "rnum": "4"
            },
            {
                "prfId": "PF209933",
                "title": "파우스트",
                "startDate": "2023-03-31",
                "endDate": "2023-04-29",
                "viewingAge": "만 13세 이상",
                "genre": "연극",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF209933_230118_103335.png",
                "facilityName": "LG아트센터 서울",
                "rnum": "5"
            },
            {
                "prfId": "PF215872",
                "title": "장기하 단독공연, 해!",
                "startDate": "2023-04-21",
                "endDate": "2023-04-30",
                "viewingAge": "만 7세 이상",
                "genre": "대중음악",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF215872_230329_144358.jpg",
                "facilityName": "무신사 개러지(구. 왓챠홀)",
                "rnum": "6"
            },
            {
                "prfId": "PF215750",
                "title": "LUCY 콘서트: INSERT COIN: parade (앵콜)",
                "startDate": "2023-04-22",
                "endDate": "2023-04-23",
                "viewingAge": "만 7세 이상",
                "genre": "대중음악",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF215750_230328_111511.gif",
                "facilityName": "올림픽공원",
                "rnum": "7"
            },
            {
                "prfId": "PF213616",
                "title": "데스노트",
                "startDate": "2023-03-28",
                "endDate": "2023-06-18",
                "viewingAge": "만 13세 이상",
                "genre": "뮤지컬",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF213616_230220_120613.png",
                "facilityName": "샤롯데씨어터",
                "rnum": "8"
            },
            {
                "prfId": "PF212579",
                "title": "모차르트 마술피리",
                "startDate": "2023-03-30",
                "endDate": "2023-04-02",
                "viewingAge": "만 7세 이상",
                "genre": "서양음악(클래식)",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF212579_230202_163336.jpg",
                "facilityName": "세종문화회관",
                "rnum": "9"
            },
            {
                "prfId": "PF215621",
                "title": "SUPER JUNIOR WORLD TOUR, SUPER SHOW 9: ROAD_SHOW",
                "startDate": "2023-04-15",
                "endDate": "2023-04-16",
                "viewingAge": "만 7세 이상",
                "genre": "대중음악",
                "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF215621_230324_140732.jpg",
                "facilityName": "잠실종합운동장",
                "rnum": "10"
            }
        ]
    }
    `
  <br>

---

<br>

## getPrfByCat

### URL

- GET `/performance/cat?genre=${genre}&page=0`

### 응답 예시

- ✅ 성공

  ```json
  {
      "code": 200,
      "description": "성공",
      "data": {
          "content": [
              {
                  "prfId": "PF202744",
                  "title": "엔톡 라이브 플러스, Pathe Live: 타르튀프",
                  "start_date": "2023-02-25",
                  "end_date": "2023-03-03",
                  "viewingAge": "만 16세 이상",
                  "genre": "연극",
                  "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF202744_221123_092648.jpg",
                  "facilityName": "국립극장"
              },...
          ],
          "pageable": {
              "sort": {
                  "empty": false,
                  "sorted": true,
                  "unsorted": false
              },
              "offset": 0,
              "pageNumber": 0,
              "pageSize": 20,
              "paged": true,
              "unpaged": false
          },
          "size": 20,
          "number": 0,
          "numberOfElements": 4,
          "sort": {
              "empty": false,
              "sorted": true,
              "unsorted": false
          },
          "first": true,
          "last": true,
          "empty": false
      }
  }
  ```

- 실패

  - ⚠️ 조회 데이터 無

    ```json
    {
      "code": 202,
      "description": "검색어에 해당하는 공연을 찾을 수 없습니다."
    }
    ```

## getPerformance

### URL

- GET  `/performance?p=${prf_id}`

### 응답 예시

- ✅ 성공
  ```json
  {
    "code": 200,
    "description": "성공",
    "data": {
      "performanceDetailInfo": {
        "prfId": "PF202566",
        "title": "브로드웨이 42번가 [부산]",
        "startDate": "2023-02-03",
        "endDate": "2023-02-05",
        "cast": "송일국, 이종혁, 정영주, 배해선, 신영숙, 전수경, 홍지민 등",
        "crew": null,
        "runtime": "2시간 40분",
        "proComp": "(주)CJ ENM, (주)샘컴퍼니",
        "viewingAge": "만 7세 이상",
        "ticketPrice": [
          {
            "seatType": "R석",
            "price": 140000
          },
          {
            "seatType": "OP석",
            "price": 130000
          },
          {
            "seatType": "S석",
            "price": 100000
          },
          {
            "seatType": "A석",
            "price": 70000
          }
        ],
        "poster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF202566_221121_111658.gif",
        "story": null,
        "genre": "뮤지컬",
        "styUrls": [
          "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF202566_221121_1116580.jpg"
        ],
        "state": "공연예정"
      },
      "prfSessionList": [
        {
          "prfSessionId": 5320,
          "prfSessionDate": "2023-02-03",
          "prfSessionTime": "19:30"
        },
        {
          "prfSessionId": 5321,
          "prfSessionDate": "2023-02-04",
          "prfSessionTime": "14:00"
        },
        {
          "prfSessionId": 5322,
          "prfSessionDate": "2023-02-04",
          "prfSessionTime": "19:00"
        },
        {
          "prfSessionId": 5323,
          "prfSessionDate": "2023-02-05",
          "prfSessionTime": "14:00"
        }
      ],
      "facilityDTO": {
        "facilityName": "부산문화회관",
        "telNo": "051-607-6000",
        "relateUrl": "http://culture.busan.go.kr/Main.bs",
        "address": "부산광역시 남구 유엔평화로76번길 1 (대연동)",
        "latitude": "35.1272750",
        "longitude": "129.0936139"
      }
    }
  }
  ```

## getPrfSession

### URL

- GET `/performance/session/:prf_session_id`

- ✅ 성공

```json
{
  "code": 200,
  "description": "성공",
  "data": {
    "remainingSeat": 185,
    "totalSeat": 200
  }
}
```

</div>
</details>

<br>

### 검색

- Spring Data JPA를 사용하여 검색 기능을 구현

- 글로벌 로딩 전략을 지연 로딩으로 설정하고 필요 엔티티 조회 시 페치 조인을 사용하여 검색기능 최적화
  ![Untitled](https://user-images.githubusercontent.com/80504636/231211693-2d94f6bc-1925-41dd-8f01-e1406bcaf509.png)
- 공연 장르에 인덱스를 걸어 검색속도를 향상시킴

  ![Untitled 1](https://user-images.githubusercontent.com/80504636/231211621-ddd53f94-82d9-408e-bb13-ced9f7803ac5.png)

- Top10 공연 조회의 경우 당일 순위 데이터가 업데이트되는 시간과 API 호출 시간의 간극으로 인한 데이터의 부재를 방지하고자 당일과 전일의 순위를 저장하고 당일 데이터가 없을 시 전일 데이터를 가져오도록 로직 구성
  ![Untitled 2](https://user-images.githubusercontent.com/80504636/231211629-0578a379-f33b-4340-854c-7adc8b9a44cb.png)

<br>

---

<br>

### 상세 조회

- 공연 상세 정보는 공연 기본키값을 기반하여 공연시설 정보와 공연 회차 정보를 한번에 조회하여 응답
  ![Untitled 3](https://user-images.githubusercontent.com/80504636/231211639-f6496224-7bad-4402-b8a8-67eeb567df5b.png)
- 공연 회차 정보는 잔여좌석 정보에 대한 읽기와 쓰기가 잦은 빈도로 발생하는 특성상 RDBMS에서 직접 질의하여 가지고 올 시 속도가 늦을 뿐 아니라 데이터의 지속성이 지켜지지 않을(데드락 또는 소켓 연결 오류 등으로 인해) 소지가 있다. 이를 방지하고자 레디스를 데이터 관리의 주체로 사용하고 읽기에 Cache-aside, 쓰기에 Write-behind 캐싱 전략을 사용하여 RDBMS와의 관계를 구축하였다.
  ![Untitled 4](https://user-images.githubusercontent.com/80504636/231211649-9fcf21ca-45e7-4349-ac7a-345286cb536a.png)

<br>

---

<br>

## 🎫 공연 예매 시스템

<br>

<details>
<summary> 📝 API 명세서 </summary>
<div markdown="1">

## checkReservation

예약하기 버튼을 눌렀을 때 유저가 선택한 회차에 선택한 인원 수 만큼 예약이 가능한지 확인 후 결제하기 페이지로 넘어감

### URL

- POST `/performance/rsv/check`
- Headers
  - Authorization: login token

### 요청 예시

```json
{
  "prfSessionId": 1,
  "count": 2
}
```

### 응답 예시

- ✅ 성공

  ```json
  {
    "code": 200,
    "description": "성공"
  }
  ```

<br>

- 실패
  - ⚠️ 잔여좌석 없음
    ```json
    {
      "code": 202,
      "description": "예약 가능한 좌석이 없습니다."
    }
    ```

<br>

## withdrawReservation

결제 페이지에서 취소를 눌렀을 때
또는 브라우저 상태 변경(종료, 새로고침, 이동 등) 이벤트가 발생할 때 대기열에서 사용자 삭제

### URL

- POST `/performance/rsv/withdraw`
- Headers
  - Authorization: login token

### 요청 예시

```json
{
  "prfSessionId": 1,
  "count": 2
}
```

### 응답 예시

- ✅ 성공

  ```json
  {
    "code": 200,
    "description": "성공"
  }
  ```

## createReservation

예약 생성 (browser → performance)

### URL

- POST `/performance/rsv/create`
- Headers
  - Authorization: login token

### 요청 예시

```json
{
  "performanceId": "PF132236",
  "prfTitle": "브로드웨이 42번가 [부산]",
  "prfPoster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF132236_160704_142630.gif",
  "prfSessionId": 2,
  "price": 10000
}
```

### 응답 예시

- ✅ 성공

  ```json
  {
    "code": 200,
    "description": "성공"
  }
  ```

<br>

- 실패 - ⚠️ 예약 불가능
  `json
        {
            "code": 400,
            "description": "예약을 완료할 수 없습니다."
        }
        `
      - ⚠️ 예약 실패
          ```json
          {
              "code": 500,
              "description": "예약생성이 실패하였습니다."
          }
          ```
  </div>
  </details>

<br>

### 예매 과정

- Ticketaka의 예매 과정은 2단계로 진행된다.
  ![예매 과정](https://user-images.githubusercontent.com/80504636/231211661-9b84a01b-31c0-4c14-9a5a-41e12332de2a.png)

1. 먼저 사용자가 예매하기 버튼을 누르면 선택된 공연 회차의 잔여 좌석 수에서 대기열에 있는 모든 사용자의 희망 예매 인원 수를 감하여 남은 좌석이 사용자의 희망 예매 인원 수보다 많다면 사용자를 예매 대기열에 추가한다.

2. 사용자가 결제를 완료하면 예매를 완료한 후 대기열에서 사용자를 삭제하며 사용자가 3분안에 결제를 완료하지 않거나 결제를 취소한다면 예매를 완료하지 않고 예매 대기열에서 사용자를 삭제한다.

<br>

---

<br>

### 예매 시스템

![예매 시스템](https://user-images.githubusercontent.com/80504636/231211665-a9152bc7-ff0d-4f83-94f8-199a8633404a.png)

- 예매 시스템은 다음의 3가지 프로세스로 구성되어있다.

  1. 예매 대기열 관리

     - Redisson 라이브러리와 Redis를 사용하여 예매 과정에 사용되는 대기열을 관리.

     - Redis hash 자료구조를 사용하여 각 회차의 대기열을 구현하고 member ID를 key, 희망 예매 인원을 value로 대기열의 엔트리를 구성

     - 예매 대기열의 각 사용자에게 3분이라는 예매 가능 시간을 설정하기 위해선 Redisson이 제공하는 Map entry eviction 기능을 사용하여 대기열 맵의 각 엔트리에 ttl을 설정해줌

     ![Untitled 7](https://user-images.githubusercontent.com/80504636/231211675-aa02f7ff-73fb-44dd-9e4e-a52def703518.png)

  2. DB 동기화

     - Redisson의 RMapCache 인터페이스를 사용하여 각 공연 회차의 잔여 좌석 정보를 MariaDB에 동기화 시킴

     ![Untitled 8](https://user-images.githubusercontent.com/80504636/231211678-332ab3c8-8cac-4e6e-978e-99af251029c7.png)

  3. 예매 서버 통신

     - Spring Cloud Open Feign 라이브러리를 사용하여 Reservation 서버에 예매 정보를 송신

     ![Untitled 9](https://user-images.githubusercontent.com/80504636/231211682-5c9b6ac8-3f01-4629-8d78-602fe4d5732c.png)

<br>

---

<br>

### 동시성 처리

- 동시성 처리를 위해 Redisson의 RLock 인터페이스를 사용하여 pub/sub 방식의 분산락을 구현
  ![Untitled 10](https://user-images.githubusercontent.com/80504636/231211685-e3c93cd4-b555-4770-9d8f-0133c660f5cc.png)
- 스프링 어노테이션을 사용하여 분산락 처리 로직을 비즈니스 로직으로 부터 관심사 분리
  ![Untitled 11](https://user-images.githubusercontent.com/80504636/231211687-a2a6dcf9-4721-4641-adde-925ae6345cbd.png)
- Pub/sub 방식
  ![Pub/sub](https://user-images.githubusercontent.com/80504636/231211690-1fdb436c-ce83-476b-8075-38b9b990c8f5.png)
  - 레디스에 자원에 접근하여 락 점유를 시도하여 실패한 모든 쓰레드는 채널에 구독을 하고 대기 상태에 들어간다.

  - 채널은 락이 해제되면 해당 채널을 구독하고 있는 클라이언트들에게 락 해제 알림을 보낸다.

  - 알림을 받은 모든 쓰레드는 대기 상태를 해제하고 락 점유를 시도한다. 실패 시 다시 락 해제 알림을 대기하며 타임아웃 시까지 반복한다.

  - 타임아웃 시 최종적으로 false를 반환하고 락 획득 실패를 알린다.

  - 이 방식은 레 디스에 지속적으로 획득 가능 여부를 체크하지 않아도 되어 레디스가 받는 부하가 적다.

<br>

# 🪵 개발환경

- Java 11

- Spring Boot

  - Spring Cloud Open Feign

  - Spring Data JPA

  - Redisson

- MariaDB:10.3

- Redis:7.0.10

<br>

# 💬 회고

## 프로젝트 진행시 주안점

- 페치 조인과 인덱스를 사용하여 검색 최적화

- 레디스를 사용하여 지연시간이 적고 동시성을 보장하는 예매 시스템의 구축

<br>

## 한계점 및 개선 사항

- 레디스가 단일 서버이기에 장애 상황에 대처할 방법이 없다. 레디스를 클러스터링 하여 장애복구 시스템을 구축한다면 해결 가능하다.

- 현재 공연 조회 기능과 예매 기능이 같은 서버에서 동작하고 있으며 두 기능 모두 Ticketaka 서비스에서 가장 많이 호출되는 기능이다. 현 구조에서는 한 기능의 장애가 다른 기능에도 여파를 미칠 수 있어 두 기능을 별도의 서버로 분리한다면 서로의 장애로 인해 영향받지 않는 구조를 구축할 수 있다.
