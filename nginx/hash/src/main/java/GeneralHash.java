/**
 * 普通Hash
 */
public class GeneralHash {
    public static void main(String[] args) {
        // 定义客户端IP
        String[] clients = {"192.168.31.121", "192.168.3.21", "192.168.1.11"};

        // 定义服务器数量
        int server = 5;

        // hash(ip) % server数量 = index
        for (String client : clients) {
            int hashCode = Math.abs(client.hashCode());
            int index = hashCode % server;
            System.out.println("客户端：\t" + client + "\t 分配到了服务器 " + (index + 1) + " 上");
        }
    }
}