package week10;

import java.util.LinkedList;

public class MyScheduler {
	public static class Task implements Comparable<Task>{
		int time;
		String task;
		
		Task(int tm, String tk){
			time = tm;
			task = tk;
		}

		@Override
		public boolean equals(Object that) {
			return (this.time==((Task)that).time)&& (this.task.equals(((Task)that).task));
		}

		public String toString() {
			return ""+time + ":00 "+task;
		}

		@Override
		public int compareTo(Task that) {
			if ( this.time > that.time)
				return 1;
			else if (this.time < that.time)
				return -1;
			else 
				return 0;
		}
	}

	LinkedList<Task> list;


	public MyScheduler(){
		list = new LinkedList<>();

		
	}
	
	public void register(Task p) {  // add in time-based, ascending order
		if (list.isEmpty()) {
			list.add(0, p);
		}
		else {
			int i = 0;
			while(i < list.size() && list.get(i).compareTo(p) < 0) { // <0 === ==-1
				i++;
			}
			list.add(i, p);
		}
	}

	public void done(Task p) {  // remove the given task
		list.remove(p);
	}
	
	public void showSchedule() {
//		list.showList();
		System.out.println(list.toString());
	}
	
	public static void main(String[] args) {
		MyScheduler ms = new MyScheduler();
		
		ms.showSchedule();
		
		ms.register(new Task(10, "Seminar"));
		ms.register(new Task(19, "Party"));
		ms.register(new Task(7, "Swimming"));
		ms.showSchedule();
		
		ms.register(new Task(9, "Tea Meeting"));
		ms.register(new Task(13, "Lunch"));
		ms.done(new Task(7, "Swimming"));
		ms.showSchedule();
		
		ms.done(new Task(9, "Tea Meeting"));
		ms.register(new Task(17, "Tennis"));
		ms.showSchedule();
		
		ms.done(new Task(9, "Seminar"));
		ms.showSchedule();
		ms.done(new Task(10, "Seminar"));
		ms.showSchedule();

	}

}
