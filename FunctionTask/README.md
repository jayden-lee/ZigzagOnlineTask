# Alter Date Format
> 12시간 단위의 시간 값과 N초를 입력받아서 N초 이후의 시간 값을 24시간 단위로 출력하는 문제 

## Sample Input & Output
| 시각 | N | 답 |
|---|---|---|
| PM 01:00:00 | 10 | 13:00:10 |
| PM 11:59:59 | 1 | 00:00:00 |
| AM 12:10:00 | 40 | 00:10:40 |
| AM 05:24:03 | 102392 | 09:50:35 |

## Run all the unit test classes
```
mvn test
```

## Testcase
1. AM 시간 값에 N초 후 출력값 검증
2. PM 시간 값에 N초 후 출력값 검증