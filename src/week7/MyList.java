package week7;

public interface MyList {
	// 리스트가 가져야 할 모든 기능의 틀을 정의
	// 이걸 MyArrayList0, MyArrayList1이 상속 받아 구현
	// "implements MyList"
	
	public boolean isEmpty() ;
	
	public int indexOf(Object value) ;

	public void add(Object value) ;
	
	public void add(int index, Object value) ;
	
	public void clear() ;
	
	public boolean contains(Object  value) ;
	
	public boolean remove(Object value) ;

	public Object remove(int index) ;

	public int size() ;

	public Object get(int index) ;

	public void set(int index, Object value) ;

	public String toString() ;

}
