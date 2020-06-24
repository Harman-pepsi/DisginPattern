package _05_迪米特原则;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Classname PrincipleOfDimiter 迪米特原则
 * @Description
 * @Date 2020/6/24 20:13
 * @Created by Harman
 */
class Course{
    private String id;

    public Course(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                '}';
    }
}

class TeamLeader{
    public void checkNumberOfCourse(){
        List<Course> list = new ArrayList<Course>();
        for (int i = 0; i < 20; i++) {
            list.add(new Course(UUID.randomUUID().toString().replace("-","")));
        }
        System.out.println("课程数量:" + list.size());
        System.out.println("课程:");
        for (Course course : list) {
            System.out.println(course);
        }
    }
}

class Boss{
    public void commendCheckNumber(TeamLeader leader){
        leader.checkNumberOfCourse();
    }
}

public class PrincipleOfDimiter {
    public static void main(String[] args) {
        Boss boss = new Boss();
        boss.commendCheckNumber(new TeamLeader());
    }
}
