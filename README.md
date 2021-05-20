# Job Scheduling

## 근사 알고리즘

NP-완전 문제들을 해결하려면 아래의 3가지 중에서 1가지는 포기해야 한다.

1. 다항식 시간에 해를 찾는 것 

2. 모든 입력에 대해 해를 찾는 것 

3. 최적해를 찾는 것

근사 알고리즘은 NP-완전 문제를 해결하기 위해 3 번째 것을 포기한다.  

즉, 최적해에 아주 근사한 해를 찾아주는 것이 근사 알고리즘이다.

근사 알고리즘은 근사해를 찾는 대신에 다항식 시간의 복잡도를 가진다. 

근사 알고리즘은 근사해가 얼마나 최적해에 근사한지를 나타내는 근사 비율을 알고리즘과 함께 제시하여야 한다.

근사 비율은 근사해의 값과 최적해의 값의 비율로서,  1.0에 가까울수록 정확도가 높은 알고리즘이다. 





## 작업 스케줄링

n개의 작업,  각 작업의 수행 시간 t_i , i = 1, 2, 3, ⋯, n,  그리고 m개의 동일한 기계가 주어질 때,  모든 작업이 가장 빨리 종료되도록 작업을 기계에 배정하는 문제이다. 

단, 한 작업은 배정된 기계에서 연속적으로 수행되어야 한다. 

또한 기계는 1번에 하나의 작업만을 수행한다.





### greedy 알고리즘을 이용한 근사해

```
static int greedy(int[] machine, int[] job) {
        int m = machine.length;;
        for(int i= 0; i<m; i++)      //각 기계에 배정된 작업 종료 시간 초기화
            machine[i]=0;

        int j = job.length;
        for(int i = 0; i<j; i++) {
            int min = 0;
            for(int k=1; k<m; k++) {         //가장 일찍 끝나는 기계 찾음
                if (machine[k] < machine[min])
                    min = k;
            }
            machine[min] += job[i];  //작업을 가장 일찍끝나는 기계에 배정(종료시간에 작업i의 시간을 더함)
        }

        int last = 0;
        for(int i =1; i<m; i++)               //작업이 가장 늦게 끝나는 기계를 찾음
            if(machine[last]<machine[i])
                last=i;

        return machine[last];      //가장 늦게 끝나는 기계의 종료 시간 리턴
    }
```

각 기계에 배정된 작업 종료 시간을 0으로 초기화해준다.

전체 작업에 대해 for문을 돌며 최소값의 초기값을 첫번째 기계로 설정하며 for문과 if문을 이용하여 가장 일찍 끝나는 기계를 찾고 작업을 그 기계에 배정해준다.

모든 작업이 배정된 후 for문과 if문을 이용하여 값을 비교하며 작업이 가장 늦게 끝나는 기계를 찾는다.

가장 늦게 끝나는 기계의 종료 시간을 리턴해준다.





### 전체 코드

```
import java.util.*;

public class JobScheduling {

    static int greedy(int[] machine, int[] job) {
        int m = machine.length;;
        for(int i= 0; i<m; i++)      //각 기계에 배정된 작업 종료 시간 초기화
            machine[i]=0;

        int j = job.length;
        for(int i = 0; i<j; i++) {
            int min = 0;
            for(int k=1; k<m; k++) {         //가장 일찍 끝나는 기계 찾음
                if (machine[k] < machine[min])
                    min = k;
            }
            machine[min] += job[i];  //작업을 가장 일찍끝나는 기계에 배정(종료시간에 작업i의 시간을 더함)
        }

        int last = 0;
        for(int i =1; i<m; i++)               //작업이 가장 늦게 끝나는 기계를 찾음
            if(machine[last]<machine[i])
                last=i;

        return machine[last];      //가장 늦게 끝나는 기계의 종료 시간 리턴
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print ("작업의 개수 입력 : ");
        int j = scanner.nextInt();
        System.out.print("기계의 개수 입력 : ");
        int m = scanner.nextInt();

        int[] machine = new int[m];    //각 기계에 배정된 작업 종료 시간을 저장하는 배열
        int[] job = new int[j];    //각 작업의 수행시간을 저장하는 배열

        Random rd = new Random();
        for(int i=0; i<j; i++) {     //각 작업의 수행시간을 1~10의 랜덤한 값으로 배정
            job[i] = rd.nextInt(10) + 1;
            System.out.print(job[i]+" ");
        }
        System.out.println();

        int last_greedy=greedy(machine, job);
        System.out.println("greedy 알고리즘을 이용한 근사해 : "+last_greedy);

    }
}
```





### 실행 결과

![실행 4개](https://user-images.githubusercontent.com/80511975/118969653-464c9380-b9a8-11eb-8798-9116834cfd11.PNG)

![실행 8개](https://user-images.githubusercontent.com/80511975/118969692-53698280-b9a8-11eb-8ee7-a4fd2a3bf848.PNG)

![실행 16개](https://user-images.githubusercontent.com/80511975/118969744-63816200-b9a8-11eb-8cdd-4fae41bf5bd8.PNG)





### 실행 결과에 대한 최적해

아래의 경우와 다른 경우에 대한 최적해도 존재하지만 다른 경우에 대한 최적해의 값은 아래의 값들과 같다.

![최적해 4](https://user-images.githubusercontent.com/80511975/118976380-e659eb00-b9af-11eb-8680-24cdfb6212da.PNG)

![최적해 8](https://user-images.githubusercontent.com/80511975/118976414-f07be980-b9af-11eb-838f-fd0833ad6229.PNG)

![최적해 16](https://user-images.githubusercontent.com/80511975/118976462-feca0580-b9af-11eb-8110-ebb6caeb7385.PNG)





### 근사 비율

근사해를 OPT'라 하고, 최적해를 OPT라고 할 때,  OPT' ≤ 2xOPT 이다. 즉, 근사해는 최적해의 2배를 넘지 않는다.

실행 결과의 근사해와 최적해의 값의 비율

1. 작업의 개수 4개, 기계의 개수 2개에 대한 근사 비율

   2  10  1  8

   근사해 : 11, 최적해 : 11

   근사해 / 최적해 = 11 / 11 = 1

2. 작업의 개수 8개, 기계의 개수 2개에 대한 근사 비율

   2  3  1  9  4  10  4  6  

   근사해 : 22, 최적해 : 20

   근사해 / 최적해 = 22 / 20 = 1.1

3. 작업의 개수 16개, 기계의 개수 2개에 대한 근사 비율

   2  7  3  7  5  8  9  8  2  4  2  9  2  2  7  4  

   근사해 : 41, 최적해 : 41

   근사해 / 최적해 = 41 / 41 = 1





### 시간복잡도

작업의 개수 : n, 기계의 개수 : m

n개의 작업을 가장 빨리 끝나는 기계에 배정한다.

배정할 기계를 찾기 위해 for루프가 (m-1)번 수행된다.

모든 기계의 마지막 작업 종료 시간인 machine[i]를 살펴보아야 하므로 O(m) 시간이 걸린다.

n X O(m) + O(m) = O(nm)





### 성능 평가

기계의 개수를 2개, 5개, 10개로 설정하여 작업의 개수를 늘려가며 실행시간을 확인하였다.

![성능 평가](https://user-images.githubusercontent.com/80511975/118982640-96325700-b9b6-11eb-8494-a5a6a1cba903.PNG)

