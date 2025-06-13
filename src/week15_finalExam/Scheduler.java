package week15_finalExam;

import java.util.ArrayList;
import java.util.LinkedList;

public class Scheduler {

	class Schedule implements Comparable {
		int time;       // 시간 (0~23시)
		String task;    // 할 일(Task)

		public Schedule(int t, String str) {
			time = t;
			task = str;
		}

		@Override
		public boolean equals(Object that) {
			return (time == ((Schedule) that).time);
		}

		@Override
		public int compareTo(Object that) {
			if (time > ((Schedule) that).time)
				return 1;
			else if (time < ((Schedule) that).time)
				return -1;
			else return 0;
		}

		public String toString() {
			return "" + time + ":00 " + task + "  ";
		}
	}

	ArrayList<LinkedList<Schedule>> schedule;

	public Scheduler() {
		schedule = new ArrayList<>(31);
		for (int i = 0; i < 31; i++) {
			schedule.add(new LinkedList<>());
		}
	}

	// 한 달 전체의 스케줄 출력 (스케줄 있는 날만 출력)
	public void monthPlan() {
		System.out.println("\n<Monthly Schedule>");
		for (int i = 0; i < schedule.size(); i++) {
			LinkedList<Schedule> dayList = schedule.get(i);
			if (!dayList.isEmpty()) {
				System.out.print("Day " + (i + 1) + " => [");
				for (int j = 0; j < dayList.size(); j++) {
					System.out.print(dayList.get(j));
					if (j != dayList.size() - 1) System.out.print(" , ");
				}
				System.out.println(" ]");
			}
		}
	}

	// 특정 날짜의 스케줄만 출력
	public void dayPlan(int date) {
		System.out.print("Day " + date + " => [");
		LinkedList<Schedule> dayList = schedule.get(date - 1);
		for (int i = 0; i < dayList.size(); i++) {
			System.out.print(dayList.get(i));
			if (i != dayList.size() - 1) System.out.print(" , ");
		}
		System.out.println(" ]");
	}

	// 새로운 스케줄 추가 후 시간 순서에 맞게 삽입
	public void addNSort(int d, int t, String tk) {
		// 날짜/시간이 유효하지 않으면 무시
		if (d < 1 || d > 31 || t < 0 || t > 23) return;

		Schedule newTask = new Schedule(t, tk);

		// 예외 : schedule 리스트가 비어있다면 초기화
		if (schedule == null) {
			schedule = new ArrayList<>(31);
			for (int i = 0; i < 31; i++) {
				schedule.add(new LinkedList<>());
			}
		}

		// 해당 날짜 리스트에서 적절한 위치에 삽입 (정렬 유지)
		LinkedList<Schedule> dayList = schedule.get(d - 1);
		int idx = 0;
		for (Schedule s : dayList) {
			if (newTask.compareTo(s) < 0) break;  // 시간 순으로 앞에 삽입
			idx++;
		}
		dayList.add(idx, newTask);
	}

	// 특정 날짜와 시간의 스케줄 제거
	public void finish(int d, int t) {
		if (d < 1 || d > 31) return;

		LinkedList<Schedule> dayList = schedule.get(d - 1);
		Schedule target = new Schedule(t, "");  // 시간만 같으면 equals가 true
		dayList.remove(target);  // 해당 시간의 스케줄 제거
	}

	// 입력한 task 이름으로 한 달 전체에서 검색
	public void findAll(String t) {
		System.out.print("Search Result : " + t);
		int count = 0;
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < schedule.size(); i++) {
			for (Schedule s : schedule.get(i)) {
				if (s.task.equals(t)) {
					result.append("\nDay ").append(i + 1)
							.append(" ").append(s.time).append(":00 ").append(s.task);
					count++;
				}
			}
		}
		System.out.print(" => " + count + " schedule(s) found!");
		System.out.println(result);
		System.out.println();
	}

	public static void main(String[] args) {
		Object [][] input = {{1, 6, "jogging"},{12, 8, "beakfast-meeting"},{1, 9, "biz-meeting"},  // {date, time, task}
				{1, 12, "lunch"},{12, 14, "hospital"},{23, 18, "tennis"},{11, 21, "movie"},
				{3, 12, "lunch"},{22, 16, "hospital"},{7, 18, "tennis"},{17, 9, "movie"},
				{3, 6, "jogging"},{22, 8, "beakfast-meeting"},{7, 9, "biz-meeting"}
		};
		
		Scheduler sch = new Scheduler();
		
		for (int i=0;i<input.length;i++) {
			sch.addNSort((int)input[i][0],(int)input[i][1],(String)input[i][2]);
		}
		sch.monthPlan();
		
		System.out.println("\n>> After add a few more tasks...");
		sch.addNSort(17, 8, "jogging");
		sch.addNSort(12, 7, "tennis");
		sch.addNSort(12, 25, "tennis"); // wrong input => must be ignored!
		sch.addNSort(32, 2, "tennis");  // wrong input => must be ignored!
		sch.monthPlan();
		
		System.out.println("\n<Day Plan>");
		sch.dayPlan(3);
		sch.dayPlan(22);
		
		System.out.println("\n<Search Task>");
		sch.findAll("tennis");
		sch.findAll("hospital");
		sch.findAll("Study");

		System.out.println("\n>> After finish a few tasks...");
		sch.finish(1,6);
		sch.finish(23,18);
		sch.finish(22,16);
		sch.finish(22,17);
		sch.monthPlan();
	}
}