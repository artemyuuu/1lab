

public class Student {
    private String name;
    private String group;
    private int course;
    private double averageMark;

    public Student(String group, String name, int course, double averageMark) {
        this.group = group;
        this.name = name;
        this.course = course;
        this.averageMark = averageMark;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Student{name: " + name + ", group: " + group + ", course: " + course + ", average mark: " + averageMark + "}";
    }

}
