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
