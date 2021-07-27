public class Student implements   Comparable<Student>{
    private String name;
    private String faculty;
    private int id;
    int getId(){return id;}
    Student(String name){
        this.faculty="";
        this.name=name;
    }
    String getName(){return name;}
    String getFaculty(){return faculty;}
    Student(int id){
        this.id=id;
        this.name="";
        this.faculty="";
    }
    Student(int id, String name, String faculty){
        this.name=name;
        this.faculty=faculty;
        this.id=id;
    }
    public int compareTo(Student s){
        if(this.getId()>s.getId()){
            return 1;
        }
        else{
            if(this.getId()<s.getId()){
                return -1;
            }
            return 0;
        }
    }

    @Override
    public String toString(){
        return (getName()+" "+getFaculty()+" "+getId());
    }
}