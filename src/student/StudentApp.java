package student;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Display reminders of students having a birthday soon.
 * @author Narongsak Chobsri Student ID :5810545858
 */
public class StudentApp {

	/**
	 * Print the names (and birthdays) of students having a birtday in the
	 * specified month.
	 * @param students list of students
	 * @param month the month to use in selecting bithdays
	 */
	public void filterAndPrint( List<Student> students, int month ) {

		for(Student s : students ) {
			if (s.getBirthdate().getMonthValue() == month)
	                  System.out.println( s );
		}
	}
	
	public void filterAndPrint( List<Student> students, Predicate<Student> filter ) {	
		for(Student s : students ) {
			if (filter.test(s)) System.out.println(s);
		}
	}
	public void filterAndPrint( List<Student> students, Predicate<Student> filter ,Consumer<Student> action) {	
		for(Student s : students ) {
			if (filter.test(s)) action.accept(s);
		}
	}
	public void filterAndPrint( List<Student> students, Predicate<Student> filter ,Consumer<Student> action,Comparator<Student> sort) {	
	
		//students.stream().filter(filter).sorted(sort).forEach(action);
		
		students.sort(sort);
		
		for(Student s : students ) {
			if (filter.test(s)) action.accept(s);
		}
		   
	}
	
	
	public static void main(String[] args) {
		List<Student> students = Registrar.getInstance().getStudents();
		StudentApp app = new StudentApp();
		LocalDate date = LocalDate.now();
		Predicate<Student> test = month -> month.getBirthdate().getMonthValue() == date.getMonthValue();
		Consumer<Student>  action = student -> System.out.printf("%s %s will have birthday on %d %s.\n"
								,student.getFirstname(),student.getLastname(),student.getBirthdate().getDayOfMonth(),student.getBirthdate().getMonth());
		
		
		
		//app.filterAndPrint(students, 1 /* may */);
		//app.filterAndPrint(students, month ->  month.getBirthdate().getMonthValue() == 7);
		//app.filterAndPrint(students, month -> month.getBirthdate().getMonthValue() == 5, 
		//		student -> System.out.printf("%s %s will have birthday on %d %s.\n"
		//				,student.getFirstname(),student.getLastname(),student.getBirthdate().getDayOfMonth(),student.getBirthdate().getMonth()));
		//app.filterAndPrint(students, test, action);
		
		Comparator<Student> byName = (a,b) -> a.getFirstname().charAt(0) - b.getFirstname().charAt(0);
		Comparator<Student> byBirthday = (a,b) -> a.getBirthdate().getDayOfMonth() - b.getBirthdate().getDayOfMonth();
		
		System.out.println("----------------------- By Name -----------------------");
		app.filterAndPrint(students, test, action,byName);
		System.out.println("----------------------- By BirthDay -------------------");
		app.filterAndPrint(students, test, action, byBirthday);
		
		Predicate<Student> in2week = week -> week.getBirthdate().getDayOfYear() - 14 <= date.getDayOfYear() &&
				week.getBirthdate().getDayOfYear() >= date.getDayOfYear();

		System.out.println("----------------------- In 2 Weeks -------------------");
		app.filterAndPrint(students, in2week, action);
		
	}
}
