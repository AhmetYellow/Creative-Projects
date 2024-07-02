import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Gradebook implements Comparator<StudentGrades>{
	
private List<StudentGrades> grades;


	public Gradebook() {
        this.grades = new ArrayList<>();
    }

	public void addGrade(StudentGrades sg) {
        grades.add(sg);
    }

	public double average() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (StudentGrades sg : grades) {
            sum += sg.totalScore();
        }
        return sum / grades.size();
    }

	public StudentGrades min() {
        if (grades.isEmpty()) {
            return null;
        }
        return Collections.min(grades, Comparator.comparingDouble(StudentGrades::totalScore));
    }

    public StudentGrades max() {
        if (grades.isEmpty()) {
            return null;
        }
        return Collections.max(grades, Comparator.comparingDouble(StudentGrades::totalScore));
    }

    public StudentGrades median() {
        if (grades.isEmpty()) {
            return null;
        }
        List<StudentGrades> sortedGrades = new ArrayList<>(grades);
        sortedGrades.sort(Comparator.comparingDouble(StudentGrades::totalScore));
        return sortedGrades.get(sortedGrades.size() / 2);
    }


	public int compare(StudentGrades left, StudentGrades right){
		
		return (int)(left.totalScore()-right.totalScore());

	}

	//provided
	public String toString(){
		String rv = "Grades: [ ";
		for(StudentGrades sg : grades){
			rv += "("+sg.getStudentName()+": "+sg.letterGrade()+"), ";
		}
		rv += "]\n";
		rv += "Max: "+max()+", Median: "+median()+", Average: "+average()+", Min: "+min();
		return rv;
	}

}