package _07_合成复用原则;

/**
 * @Classname PrincipleOfCompositeReuse 合成复用原则
 * @Description
 * @Date 2020/6/25 10:55
 * @Created by Harman
 */
abstract class DBConnection{
    public abstract String getConnection();
}

class MySQLConnection extends DBConnection{
    @Override
    public String getConnection() {
        return "MySQL数据库连接";
    }
}

class OracleConnection extends DBConnection{
    @Override
    public String getConnection() {
        return "Oracle数据库连接";
    }
}

public class PrincipleOfCompositeReuse {
    public static void main(String[] args) {
        DBConnection connection = new MySQLConnection();
        System.out.println(connection.getConnection());
    }
}
