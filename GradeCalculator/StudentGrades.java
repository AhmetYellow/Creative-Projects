import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StudentGrades {
    
private double participation;
private double midterm;
private double finalExam;

private Collection<Double> labs = new ArrayList<>();
private Collection<Double> exercises = new ArrayList<>();
private Collection<Double> projects = new ArrayList<>();


private List<Double> readings = new ArrayList<Double>();


private double participationWeight;
private double readingsWeight;
private double labsWeight;
private double exercisesWeight;
private double projectsWeight;
private double midtermWeight;
private double finalExamWeight;

private String studentName;
private String gNumber;


public StudentGrades(String name, String gNumber, double[] weights){

    this.studentName = name;
    this.gNumber = gNumber;
    setWeights(weights);

}

public void setParticipation(double participation){

    this.participation = participation;
}
public void setMidterm(double midterm){

    this.midterm = midterm;
}
public void setFinalExam(double finalExam){

    this.finalExam = finalExam;
}
public void setStudentName(String studentName){

    this.studentName = studentName;
}
public void setGNumber(String gNumber){

    this.gNumber = gNumber;
}

public double getParticipation(){

    return participation;

}
public double getMidterm(){

    return midterm;

}
public double getFinalExam(){

    return finalExam;

}
public String getStudentName(){

    return studentName;
}
public String getGNumber(){

    return gNumber;
}

public void addReading(double d){

    readings.add(d);

}
public void addLab(double d){

    labs.add(d);

}
public void addExercise(double d){

    exercises.add(d);

}
public void addProject(double d){

    projects.add(d);

}

public void setWeights(double[] weights){

    participationWeight = weights[0];
    readingsWeight = weights[1];
    labsWeight = weights[2];
    exercisesWeight = weights[3];
    projectsWeight = weights[4];
    midtermWeight = weights[5];
    finalExamWeight = weights[6];

}

/* lowest 15 scores get dropped so I have to sort it then remove the last 15 scores
 * after that, while dividing, i do -15 from # of scores
 */
public double unweightedReadingsScore(){
    
    Collections.sort(readings);
    
    if(readings.size() < 16){
        return 100;
    }

    double addedScores = 0;
    int counter = 0;

    for(int i = 15; i < readings.size(); i++){
        addedScores += readings.get(i);
        counter++;
    }
    return addedScores / counter;

}

public double unweightedLabsScore(){
    
    if(labs.size() == 0){
        return 100;
    }

    double addedScores = 0;
    for (double score : labs) {
        
        addedScores += score;

    }

    return addedScores / labs.size();

}
public double unweightedExercisesScore(){

    if(exercises.size() == 0){
        return 100;
    }

    double addedScores = 0;
    for (double score : exercises) {
        
        addedScores += score;

    }

    return addedScores / exercises.size();

}
public double unweightedProjectsScore(){

    if(projects.size() == 0){
        return 100;
    }

    double addedScores = 0;
    for (double score : projects) {
        
        addedScores += score;

    }

    return addedScores / projects.size();

}

public boolean finalReplacesMidterm(){

    return getFinalExam() > getMidterm();
        
}

public boolean finalIsPassing(){

    return getFinalExam() >= 60.0;

}
/*To calculate the total score, simply multiply the score from each category by the 
corresponding weight and add the results together, keeping in mind that the final replaces the
 midterm if the final is greater.  */
public double totalScore(){

    if(finalReplacesMidterm()){
        double midtermTotal = getFinalExam() * midtermWeight;
        double finalExamTotal = getFinalExam() * finalExamWeight;
        double participationTotal = getParticipation() * participationWeight;
        double readingsTotal = unweightedReadingsScore() * readingsWeight;
        double labsTotal = unweightedLabsScore() * labsWeight;
        double exercisesTotal = unweightedExercisesScore() * exercisesWeight;
        double projectsTotal = unweightedProjectsScore() * projectsWeight;
        double gradeTotal = midtermTotal + finalExamTotal + participationTotal + readingsTotal + labsTotal + exercisesTotal + projectsTotal;
        return gradeTotal;
    }

    else{
            double midtermTotal = getMidterm() * midtermWeight;
            double finalExamTotal = getFinalExam() * finalExamWeight;
            double participationTotal = getParticipation() * participationWeight;
            double readingsTotal = unweightedReadingsScore() * readingsWeight;
            double labsTotal = unweightedLabsScore() * labsWeight;
            double exercisesTotal = unweightedExercisesScore() * exercisesWeight;
            double projectsTotal = unweightedProjectsScore() * projectsWeight;
            double gradeTotal = midtermTotal + finalExamTotal + participationTotal + readingsTotal + labsTotal + exercisesTotal + projectsTotal;
            return gradeTotal;
    }

}

public String letterGrade() {
    // Check if the final exam score is passing
    if (!finalIsPassing()) {
        return "F";
    }

    // Calculate the total score and determine the letter grade
    double score = totalScore();
    if (score >= 98) return "A+";
    else if (score >= 92) return "A";
    else if (score >= 90) return "A-";
    else if (score >= 88) return "B+";
    else if (score >= 82) return "B";
    else if (score >= 80) return "B-";
    else if (score >= 78) return "C+";
    else if (score >= 72) return "C";
    else if (score >= 70) return "C-";
    else if (score >= 60) return "D";
    else return "F";
}

@Override
public String toString(){
		String rv = "Name: "+getStudentName()+"\n";
		rv += "G#: "+getGNumber()+"\n";
		rv += "Participation: "+getParticipation()+"\n";
		rv += "Readings: "+unweightedReadingsScore()+", "+readings+"\n";
		rv += "Labs: "+unweightedLabsScore()+", "+labs+"\n";
		rv += "Exercises: "+unweightedExercisesScore()+", "+exercises+"\n";
		rv += "Projects: "+unweightedProjectsScore()+", "+projects+"\n";
		rv += "Midterm: "+getMidterm()+"\n";
		rv += "Final Exam: "+getFinalExam()+"\n";
		rv += totalScore()+", "+letterGrade()+"\n";
		return rv;
	}
}
