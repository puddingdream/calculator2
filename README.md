# 🧮 Calculator Project (Version 2)
> **자바 객체지향 원리를 적용한 스마트 계산기**

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Build](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge)

## 📌 프로젝트 소개
단순한 사칙연산을 넘어, **객체지향 설계(OOP)**를 바탕으로 데이터의 유지보수와 확장성을 고려하여 제작된 계산기입니다.  
사용자로부터 숫자와 연산자를 입력받아 결과를 계산하며, 계산 내역을 저장하고 관리하는 기능을 포함하고 있습니다.

---

## ✨ 핵심 기능 (Features)

### 1. 사칙연산 지원
* 더하기(`+`), 빼기(`-`), 곱하기(`*`), 나누기(`/`)의 기본 연산을 정확하게 수행합니다.
* 나누기 연산 시 분모가 `0`인 경우에 대한 **예외 처리**가 완벽하게 되어 있습니다.

### 2. 계산 이력 관리 (History Management)
* `List` 자료구조를 활용하여 계산된 결과값들을 순차적으로 저장합니다.
* 저장된 데이터 중 가장 먼저 계산된 기록을 삭제(FIFO)하거나 전체 내역을 조회할 수 있습니다.

### 3. 스트림(Stream) 기반 데이터 가공
* 저장된 결과값 중 특정 값보다 큰 결과들만 필터링하여 출력하는 **Modern Java Stream API** 기능을 탑재했습니다.

---

## 🚀 기술 스택 및 성능 강점 (Technical Strength)

### ✅ 객체지향 설계 (Encapsulation)
필드를 `private`으로 선언하고 `Getter/Setter`를 통해 접근하게 하여 **캡슐화**를 실현했습니다. 데이터 오염을 방지하고 객체 스스로가 상태를 관리합니다.

### ✅ 견고한 예외 처리 (Exception Handling)
`try-catch`와 `while(true)` 루프를 결합하여 사용자가 잘못된 값을 입력하더라도 프로그램이 강제 종료되지 않고 재입력을 유도하는 **안정성**을 확보했습니다.

### ✅ 효율적인 버퍼 관리
`Scanner` 사용 시 발생할 수 있는 버퍼 찌꺼기 문제를 `nextLine()`과 `Integer.parseInt()` 조합으로 해결하여 **입력 무결성**을 보장합니다.

---

# 🛒 Java Console Commerce System
> **자바의 객체지향 특성과 함수형 인터페이스를 활용한 실시간 재고 관리 시스템**

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/status-completed-blue?style=for-the-badge)

## ✨ 핵심 기능 (Core Features)

### 1. 실시간 재고 선점 시스템 (Inventory Reservation)
* **Add to Cart**: 장바구니에 담는 즉시 원본 재고(`stock`)를 차감하여 결제 시점의 재고 부족 오류를 원천 차단합니다.
* **Smart Restore**: 장바구니 상품 삭제나 전체 비우기 시, 담겼던 수량만큼 창고 재고를 즉시 복구합니다.
* **Safe Removal**: `Stream` 필터링을 통해 삭제 대상을 먼저 확보한 후 재고를 반환하는 **2단계 안전 삭제 로직**을 적용했습니다.

### 2. 함수형 동적 메뉴 시스템 (Dynamic Menu)
* **Supplier<Boolean> 활용**: 각 메뉴는 스스로의 노출 조건을 내포하여 장바구니 상태에 따라 메뉴를 동적으로 제어합니다.
* **Lazy Evaluation**: 메뉴판 출력 시점에 실시간 상태를 체크하여 UI를 구성하는 **지연 실행** 방식을 채택했습니다.
* **Method Delegation**: 기본값 제공 패턴을 통해 중복 코드 없이 항상 노출되는 메뉴와 조건부 메뉴를 효율적으로 관리합니다.

### 3. 고도화된 상품 검색 및 조회
* **Keyword Search**: Stream API를 활용해 한글/영문 키워드 포함 여부를 판단하는 고성능 검색 기능을 제공합니다.
* **DRY 원칙 준수**: 카테고리 조회와 검색 결과 처리를 공통 메서드(`selectAndAddCart`)로 추출하여 코드 중복을 제거했습니다.

---

## 🚀 기술 스택 및 설계 포인트 (Technical Focus)

### ✅ 객체 참조(Object Reference)의 활용
별도의 ID 검색 로직 없이, 장바구니 내 상품 객체에 직접 접근하여 재고를 수정합니다. 이는 자바의 **참조 타입 특성**을 활용한 효율적인 데이터 동기화 방식입니다.

### ✅ 방어적 프로그래밍 (Defensive Programming)
* `containsKey()`를 이용한 사전 검증으로 `NullPointerException`을 방지합니다.
* **단락 평가(Short-circuit)**를 활용하여 유효하지 않은 데이터 접근을 런타임 이전에 차단합니다.

---

## 📂 프로젝트 구조 (Project Structure)

```plaintext
src/
├── main/
│   ├── CommerceSystem.java  # 시스템 메인 루프 및 핵심 비즈니스 로직
│   ├── Menu.java            # Supplier를 활용한 동적 메뉴 관리 객체
│   ├── Warehouse.java       # 상품 데이터베이스 및 카테고리 저장소
│   ├── Category.java        # 카테고리별 상품 관리 도메인
│   ├── Product.java         # 상품 정보 및 재고 관리 엔티티
│   └── Cart.java            # 장바구니 관리 및 재고 복구 로직
└── utils/
    └── ScannerUtil.java     # 입력 유효성 검사 및 버퍼 관리 도구
