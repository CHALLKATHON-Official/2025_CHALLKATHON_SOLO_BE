## 📁 Back-End 프로젝트 구조
```
com.example.solo
├── global                      # 전역 설정 및 유틸
│   ├── annotation             # 커스텀 애너테이션
│   ├── exception              # 전역 예외 정의 및 핸들러
│   ├── security               # Spring Security 설정
│   ├── swagger                # Swagger 문서화 설정
│   └── web                    # Web 설정
│
├── member                     # 회원 도메인
│   ├── application
│   │   ├── facade             # 회원 관련 유즈케이스 흐름 제어
│   │   └── service            # 회원 관련 비즈니스 로직
│   ├── controller
│   │   ├── swaggerDocs
│   │   │   └── api           # Swagger용 컨트롤러 그룹
│   │   └── constant          # Swagger 문서용 상수 정의
│   │   └── AuthController    # 인증 API 컨트롤러
│   └── domain
│       ├── dto               # DTO 클래스
│       ├── encrypt           # 비밀번호 암호화 로직
│       ├── entity            # 엔티티 클래스
│       └── repository        # JPA 리포지토리
│
├── schedule                   # 일정 도메인
│   ├── application
│   │   ├── facade             # 일정 관련 유즈케이스 흐름 제어
│   │   └── service            # 일정 관련 비즈니스 로직
│   ├── controller
│   │   ├── swaggerDocs
│   │   │   └── api           # Swagger용 컨트롤러 그룹
│   │   └── constant          # Swagger 문서용 상수 정의
│   │   └── ScheduleController # 일정 관련 API 컨트롤러
│   └── domain
│       ├── dto               # DTO 클래스
│       ├── entity            # 엔티티 클래스
│       └── repository        # JPA 리포지토리
│
└── TimeChartApplication       # SpringBoot 애플리케이션 시작 클래스
```
