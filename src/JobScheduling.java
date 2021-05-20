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
